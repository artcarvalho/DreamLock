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


    private String getHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = bytes.length - 1; i >= 0; --i) {
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString().toUpperCase();

    }
}