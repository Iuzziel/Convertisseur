package afpa.convertisseur;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(@NonNull View view) {
        Toast.makeText(getBaseContext(), String.valueOf(view.getParent()), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, Convertisseur.class);
        startActivity(intent);
    }

    public void onQuit(View view) {
        Log.i("Fin", "Fermeture de l'application.");
        System.exit(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // Instanciation du menu XML spécifier en un objet Menu
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // On teste l’Id de l’item cliqué et on déclenche une action
        switch (item.getItemId()) {
            case R.id.opt_convert:
                Intent intent = new Intent(this, Convertisseur.class);
                startActivity(intent);
                return true;
            case R.id.item2:
                // action 2
                return true;
            case R.id.quitter:
                finish();
                return true;
            default:
                return false;
        }
    }
}
