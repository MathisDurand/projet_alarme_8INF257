package uqac.dim.projet_alarme_8inf257;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Parametre extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametre);
    }
    public void alarme(View activity_main) {
        Intent intent = new Intent(Parametre.this, MainActivity.class);
        startActivity(intent);
    }

}

