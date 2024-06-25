package artur.renata.dreamlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import artur.renata.dreamlock.R;

public class SalasActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas);

        Bundle extras = getIntent().getExtras();

        TextView nomeSala = findViewById(R.id.salaNumero);
        nomeSala.setText(extras.getString("nome"));

        FloatingActionButton btnAddUser = (FloatingActionButton) findViewById(R.id.botaoAddNome);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalasActivity.this, UsuariosActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        FloatingActionButton  btnLog = (FloatingActionButton) findViewById(R.id.botaoVerLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalasActivity.this, LogActivity.class);
                startActivity(i);
            }
        });


    }
}