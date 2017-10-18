package afpa.convertisseur.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MonnaieSQLite extends SQLiteOpenHelper {
    private static final String TABLE_MONNAIE = "table_monnaie";
    private static final String COL_ID = "ID";
    private static final String COL_LABEL = "LABEL";
    private static final String COL_VALEUR = "VALEUR";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_MONNAIE + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_LABEL + " TEXT NOT NULL, "
            + COL_VALEUR + " DOUBLE NOT NULL);";

    public MonnaieSQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //on crée la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut faire ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
        //comme ça lorsque je change la version les id repartent de 0
        db.execSQL("DROP TABLE " + TABLE_MONNAIE + ";");
        onCreate(db);
    }
}
