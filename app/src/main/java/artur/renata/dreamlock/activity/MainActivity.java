package artur.renata.dreamlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import artur.renata.dreamlock.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnUser = (FloatingActionButton) findViewById(R.id.botaoVerIds);

        btnUser.setOnClickListener(new View.OnClickListener() { //clique para acessar a guida dos usuarios
           @Override
           public void onClick (View v){
               Intent i = new Intent(MainActivity.this, UsuariosActivity.class);
               startActivity(i);
           }
        });

        ImageButton btnSearch = (ImageButton) findViewById(R.id.botaoPesquisa);
        btnSearch.setOnClickListener(new View.OnClickListener(){ //iniciar procura pelas salas através do input colocado na barra de pesquisa
            @Override
            public void onClick(View v){

            }
        });

    }
}