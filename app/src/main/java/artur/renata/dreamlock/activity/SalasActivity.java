package artur.renata.dreamlock.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import artur.renata.dreamlock.model.IdModel;

public class SalasActivity extends AppCompatActivity {

    AdapterAcesso acesso;

    String sala;
    //connect database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference banco = database.getReference();
    static int NEW_USER_REQUEST = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas);


        Bundle extras = getIntent().getExtras();

        TextView nomeSala = findViewById(R.id.salaNumero);
        nomeSala.setText(extras.getString("nome"));

        sala = extras.getString("nome");

        lerDadosAcesso(sala);



        FloatingActionButton btnAddUser = (FloatingActionButton) findViewById(R.id.botaoAddNome);
        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalasActivity.this, UsuariosActivity.class);
                i.putExtra("sala", sala);
                startActivityForResult(i, NEW_USER_REQUEST);
            }
        });

        FloatingActionButton  btnLog = (FloatingActionButton) findViewById(R.id.botaoVerLog);
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SalasActivity.this, LogActivity.class);
                i.putExtra("sala", sala);
                startActivity(i);
            }
        });



    }

    protected void lerDadosAcesso(String salaN){

        DatabaseReference reference = database.getReference().child("Salas").child(salaN.toLowerCase()).child("Acessos");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<IdModel> ids = new ArrayList<>();

                for (DataSnapshot childDataSnapshot : snapshot.getChildren()){
                    IdModel id = new IdModel();
                    id.nome = childDataSnapshot.getKey();
                    id.id = childDataSnapshot.getValue().toString();
                    id.sala = salaN.toLowerCase();
                    ids.add(id);

                }

                RecyclerView rvItensID = findViewById(R.id.listaNomes);
                acesso = new AdapterAcesso(SalasActivity.this, ids);
                rvItensID.setAdapter(acesso);
                rvItensID.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SalasActivity.this);
                rvItensID.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_USER_REQUEST) {
            lerDadosAcesso(sala);
        }
    }
}