package uqac.dim.projet_alarme_8inf257;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;


public class PassphraseActivity extends AppCompatActivity {
    EditText inputText;
    Button buttonVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.minijeupassphrase);
        inputText = findViewById(R.id.userInputText);
        buttonVal = findViewById(R.id.buttonValider);
        buttonVal.setOnClickListener(view -> TestPhrase());
    }



    private void TestPhrase(){
        if(inputText.getText().length() >10)
            startActivity(new Intent(PassphraseActivity.this, MainActivity.class));
    }
}


