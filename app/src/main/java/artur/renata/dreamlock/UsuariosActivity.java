package artur.renata.dreamlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class UsuariosActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        FloatingActionButton btnUserAdd = (FloatingActionButton) findViewById(R.id.botaoAddIds);

        btnUserAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UsuariosActivity.this, CadastroActivity.class);
                startActivityForResult(i, NEW_ITEM_REQUEST);

            }
        });


    }
}