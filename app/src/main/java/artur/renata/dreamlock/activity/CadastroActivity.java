package artur.renata.dreamlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import artur.renata.dreamlock.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Button btnSalvar = findViewById(R.id.botaoSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //pegando nome
                TextInputEditText etNome = findViewById(R.id.inputNome);
                String nome = etNome.getText().toString();
                //caso nao tenha colocado nome
                if(nome.isEmpty()){
                    Toast.makeText(CadastroActivity.this, "É necessário Inserir um nome", Toast.LENGTH_LONG).show();
                    return;
                }

                //pegando ID
                TextInputEditText etID = findViewById(R.id.inputId);
                String id = etID.getText().toString();
                //caso nao tenha colocado nome
                if(id.isEmpty()){
                    Toast.makeText(CadastroActivity.this, "É necessário ler uma Tag para obter o ID", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent i = new Intent();
                i.putExtra("nome", nome);
                i.putExtra("id", id);
                setResult(Activity.RESULT_OK, i);
                finish();


            }
        });
    }



}