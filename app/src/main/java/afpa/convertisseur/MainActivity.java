package afpa.convertisseur;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View View) {
        // traitement déclenché par le clic sur le bouton
        // par exemple affichage d’une boite popup
        Toast.makeText(getBaseContext(), "Message", Toast.LENGTH_LONG).show();
    }
}
