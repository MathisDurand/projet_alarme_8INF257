package uqac.dim.projet_alarme_8inf257;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

class MiniJeuxPassPhraseActivity extends AppCompatActivity {

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
            startActivity(new Intent(MiniJeuxPassPhraseActivity.this, MainActivity.class));
    }
}


