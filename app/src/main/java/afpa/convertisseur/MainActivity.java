package afpa.convertisseur;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        Toast.makeText(getBaseContext(), String.valueOf(view.getParent()), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Convertisseur.class);
        startActivity(intent);
    }

    public void onQuit(View view) {
        Log.i("Fin", "Fermeture de l'application.");
        System.exit(0);
    }
}
