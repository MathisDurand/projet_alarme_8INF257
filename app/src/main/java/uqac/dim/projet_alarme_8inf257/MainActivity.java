package uqac.dim.projet_alarme_8inf257;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCreerAlarme = (Button) findViewById(R.id.main_to_creeralarme);
        btnCreerAlarme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreerAlarmActivity.class);
                startActivity(intent);
            }
        });

        Button btnParametre = (Button) findViewById(R.id.main_to_parametre);
        btnParametre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Parametre.class);
                startActivity(intent);
            }
        });
    }

}