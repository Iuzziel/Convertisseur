package afpa.convertisseur.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MonnaieManager {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "convertisseur.db";
    private static final String TABLE_MONNAIE = "table_monnaie";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_LABEL = "LABEL";
    private static final int NUM_COL_LABEL = 1;
    private static final String COL_VALEUR = "VALEUR";
    private static final int NUM_COL_VALEUR = 2;

    private SQLiteDatabase bdd;

    private MonnaieSQLite bddMonnaieSQLite;

    public MonnaieManager(Context context) {
        //On crée la BDD et sa table
        bddMonnaieSQLite = new MonnaieSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open() {
        //on ouvre la BDD en écriture
        bdd = bddMonnaieSQLite.getWritableDatabase();
    }

    public void close() {
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return bdd;
    }

    public long insertMonnaie(Monnaie monnaie) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_LABEL, monnaie.getLabel());
        values.put(COL_VALEUR, monnaie.getValeur());
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_MONNAIE, null, values);
    }

    public int updateMonnaie(int id, Monnaie monnaie) {
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_LABEL, monnaie.getLabel());
        values.put(COL_VALEUR, monnaie.getValeur());
        return bdd.update(TABLE_MONNAIE, values, COL_ID + " = " + id, null);
    }

    public int removeMonnaieWithID(int id) {
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_MONNAIE, COL_ID + " = " + id, null);
    }

    public Monnaie getMonnaieWithTitre(String label) {
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_MONNAIE, new String[]{COL_ID, COL_LABEL, COL_VALEUR}, COL_LABEL + " LIKE \"" + label + "\"", null, null, null, null);
        return cursorToMonnaie(c);
    }

    public Cursor getAllMonnaie() {
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_MONNAIE, new String[]{COL_ID, COL_LABEL, COL_VALEUR}, null, null, null, null, COL_LABEL + " DESC");
        return c;
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Monnaie cursorToMonnaie(Cursor c) {
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé une monnaie
        Monnaie monnaie = new Monnaie();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        monnaie.setId(c.getInt(NUM_COL_ID));
        monnaie.setLabel(c.getString(NUM_COL_LABEL));
        monnaie.setValeur(c.getDouble(NUM_COL_VALEUR));
        //On ferme le cursor
        c.close();

        //On retourne le monnaie
        return monnaie;
    }
}
