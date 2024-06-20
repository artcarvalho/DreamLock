package artur.renata.dreamlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import artur.renata.dreamlock.R;

public class MainActivity extends AppCompatActivity {

    //banco de dados
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton btnUser = (FloatingActionButton) findViewById(R.id.botaoVerIds);


        //somente teste para conectar ao banco de dados
        reference.child("Salas").child("002").child("Nome").setValue("Sala");
        reference.child("Salas").child("002").child("Acessos").setValue("artur");

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