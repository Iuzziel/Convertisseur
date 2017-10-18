package afpa.convertisseur;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import afpa.convertisseur.metier.Convert;
import afpa.convertisseur.modele.Monnaie;
import afpa.convertisseur.modele.MonnaieManager;

public class Convertisseur extends AppCompatActivity {
    @NonNull
    private List<String> devises = new ArrayList<String>();
    private List<String> devisesBDD = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertisseur);

        chargeDevises();
        chargeDevisesBDD();
        chargerSpinner(R.id.spinDeviseDepart);
        chargerSpinner(R.id.spinDeviseArrivee);
        chargerSpinnerBDD(R.id.spBDD);
    }

    public void onClick(View view) {
        EditText editText = (EditText) findViewById(R.id.editMontant);
        Spinner sSource = (Spinner) findViewById(R.id.spinDeviseDepart);
        Spinner sCible = (Spinner) findViewById(R.id.spinDeviseArrivee);

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.setMinimumFractionDigits(0);

        try {
            String source = sSource.getSelectedItem().toString();
            String cible = sCible.getSelectedItem().toString();
            if (!(source.equals("")) && !(cible.equals("")) && !(source.equals(cible))) {
                Double montant = Double.valueOf(editText.getText().toString());
                double result = Convert.convertir(source, cible, montant);
                Toast.makeText(getBaseContext(), String.valueOf(result), Toast.LENGTH_LONG).show();
                TextView textView = (TextView) findViewById(R.id.tvResultat);
                textView.setText(String.valueOf(df.format(result)));
            } else {
                Toast.makeText(getBaseContext(), "Selectionnez les devises",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    // Chargement depuis la classe Convert et sa Map
    public void onQuit(View view) {
        System.exit(0);
    }

    private void chargeDevises() {
        Set key = Convert.getConversionTable().keySet();

        for (Object aKey : key)
            devises.add(aKey.toString());
    }

    private void chargerSpinner(int idView) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, devises);

        Spinner spin = (Spinner) findViewById(idView);
        spin.setAdapter(adapter);
    }

    // Test SQLite
    private void chargeDevisesBDD() {
        MonnaieManager monnaieManager = new MonnaieManager(this);

        Monnaie livre = new Monnaie("Livre", Double.valueOf(0.6404));
        Monnaie euro = new Monnaie("Euro", Double.valueOf(0.7697));
        Monnaie dirham = new Monnaie("Dirham", Double.valueOf(8.5656));
        Monnaie yen = new Monnaie("Yen", Double.valueOf(76.6908));
        Monnaie francsCFA = new Monnaie("Francs CFA", Double.valueOf(503.17));
        Monnaie dollarsUS = new Monnaie("Dollars US", Double.valueOf(1.0));

        monnaieManager.open();
        monnaieManager.insertMonnaie(livre);
        monnaieManager.insertMonnaie(euro);
        monnaieManager.insertMonnaie(dirham);
        monnaieManager.insertMonnaie(yen);
        monnaieManager.insertMonnaie(francsCFA);
        monnaieManager.insertMonnaie(dollarsUS);

        Cursor c = monnaieManager.getAllMonnaie();
        Map<String, Double> mapMonnaie = new HashMap<String, Double>();

        c.moveToFirst();
        do {
            mapMonnaie.put(c.getString(1), c.getDouble(2));
        } while (c.moveToNext());
        c.close();

        Toast.makeText(this, String.valueOf("Depuis la bdd :"
                + monnaieManager.getMonnaieWithTitre("Livre").getLabel()
                + " "
                + monnaieManager.getMonnaieWithTitre("Livre").getValeur()), Toast.LENGTH_LONG).show();

        monnaieManager.close();

        Set key = mapMonnaie.keySet();

        for (Object aKey : key)
            devisesBDD.add(aKey.toString());
        Collections.sort(devisesBDD);
    }

    private void chargerSpinnerBDD(int idView) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, devisesBDD);

        Spinner spin = (Spinner) findViewById(idView);
        spin.setAdapter(adapter);
    }
}
