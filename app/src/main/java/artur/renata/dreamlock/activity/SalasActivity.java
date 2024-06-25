package artur.renata.dreamlock.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.adapter.AdapterAcesso;
import artur.renata.dreamlock.adapter.AdapterSalas;
import artur.renata.dreamlock.adapter.adapterUser;
import artur.renata.dreamlock.model.idModel;

public class SalasActivity extends AppCompatActivity {


    List<idModel> ids = new ArrayList<>();
    AdapterAcesso Acesso;

    String Sala;
    //connect database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference banco = database.getReference();
    static int NEW_ITEM_REQUEST = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas);

        Bundle extras = getIntent().getExtras();

        TextView nomeSala = findViewById(R.id.salaNumero);
        nomeSala.setText(extras.getString("nome"));

        Sala = extras.getString("nome");
        lerDadosAcesso(Sala);


        RecyclerView rvItensID = findViewById(R.id.listaNomes);
        Acesso = new AdapterAcesso(this, ids);
        rvItensID.setAdapter(Acesso);
        rvItensID.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItensID.setLayoutManager(layoutManager);

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

    protected void lerDadosAcesso(String salaN){

        DatabaseReference reference = database.getReference().child("Salas").child(salaN.toLowerCase()).child("Acessos");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot childDataSnapshot : snapshot.getChildren()){


                    idModel id = new idModel();
                    id.nome = childDataSnapshot.getKey();
                    id.id = childDataSnapshot.getValue().toString();
                    id.sala = salaN;
                    ids.add(id);
                }
                Acesso.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}