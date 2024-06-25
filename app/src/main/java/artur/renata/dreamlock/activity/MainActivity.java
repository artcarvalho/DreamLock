package artur.renata.dreamlock.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.adapter.AdapterSalas;
import artur.renata.dreamlock.adapter.adapterUser;
import artur.renata.dreamlock.model.idModel;
import artur.renata.dreamlock.model.salaModel;

public class MainActivity extends AppCompatActivity {

    List<salaModel> salasList = new ArrayList<>();
    AdapterSalas salasA;
    salaModel sala1 = new salaModel();
    salaModel sala2 = new salaModel();
    salaModel sala3 = new salaModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sala1.nome = "Sala1";
        sala2.nome = "Sala2";
        sala3.nome = "Sala3";

        salasList.add(sala1);
        salasList.add(sala2);
        salasList.add(sala3);


        RecyclerView rvItens = findViewById(R.id.listaSalas);
        salasA = new AdapterSalas(this, salasList);
        rvItens.setAdapter(salasA);
        rvItens.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

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