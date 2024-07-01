package artur.renata.dreamlock.activity;

import static android.nfc.NfcAdapter.FLAG_READER_NFC_A;
import static androidx.core.content.IntentCompat.getParcelableExtra;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
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

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        NfcAdapter.ReaderCallback callBack = new NfcAdapter.ReaderCallback() {
            @Override
            public void onTagDiscovered(Tag tag) {
                EditText idView = findViewById(R.id.idText);
                idView.setText(getHex(tag.getId()));
            }
        };
        
        nfcAdapter.enableReaderMode(this, callBack, FLAG_READER_NFC_A, null);



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
                EditText etID = findViewById(R.id.idText);
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


    private String getHex(byte [] inarray) {
        int i, j, in;
        String [] hex = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
        String out= "";

        for(j = 0 ; j < inarray.length ; ++j)
        {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
            out+=" ";
        }
        return out;
    }


}

