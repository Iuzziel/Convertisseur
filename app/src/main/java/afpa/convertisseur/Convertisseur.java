package afpa.convertisseur;

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
import java.util.List;
import java.util.Set;

import afpa.convertisseur.metier.Convert;

public class Convertisseur extends AppCompatActivity {
    @NonNull
    private List<String> devises = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertisseur);

        chargeDevises();
        chargerSpinner(R.id.spinDeviseDepart);
        chargerSpinner(R.id.spinDeviseArrivee);
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

    public void onQuit(View view) {
        System.exit(0);
    }

    public void chargeDevises() {
        Set key = Convert.getConversionTable().keySet();

        for (Object aKey : key)
            devises.add(aKey.toString());
    }

    public void chargerSpinner(int idView) {
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, devises);

        Spinner spin = (Spinner) findViewById(idView);
        spin.setAdapter(adapter);
    }
}
