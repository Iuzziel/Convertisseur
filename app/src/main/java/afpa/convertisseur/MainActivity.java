package afpa.convertisseur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import afpa.convertisseur.metier.Convert;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View View) {
        EditText editText = (EditText) findViewById(R.id.editMontant);
        Spinner sSource = (Spinner) findViewById(R.id.spinDeviseDepart);
        Spinner sCible = (Spinner) findViewById(R.id.spinDeviseArrivee);
        try {
            String source = sSource.getSelectedItem().toString();
            String cible = sCible.getSelectedItem().toString();
            if (!(source.equals("")) && !(cible.equals("")) && !(source.equals(cible))) {
                Double montant = Double.valueOf(editText.getText().toString());
                double result = Convert.convertir(source, cible, montant);
                Toast.makeText(getBaseContext(), String.valueOf(result), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getBaseContext(), "Selectionnez les devises", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    public void onQuit(View view) {
        Toast.makeText(getBaseContext(), "Fermeture de l'application", Toast.LENGTH_LONG).show();
        System.exit(0);
    }
}
