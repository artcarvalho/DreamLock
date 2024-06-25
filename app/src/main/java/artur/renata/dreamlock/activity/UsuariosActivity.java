package artur.renata.dreamlock.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import artur.renata.dreamlock.adapter.adapterUser;
import artur.renata.dreamlock.model.idModel;

public class UsuariosActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;
    List<idModel> ids = new ArrayList<>();
    adapterUser UserList;

    //connect database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference banco = database.getReference();


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

        lerDados_user();


        RecyclerView rvItensID = findViewById(R.id.listaPessoas);
        UserList = new adapterUser(this, ids);
        rvItensID.setAdapter(UserList);
        rvItensID.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItensID.setLayoutManager(layoutManager);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                idModel id = new idModel();
                id.nome = data.getStringExtra("nome");
                id.id = data.getStringExtra("id");
                id.sala = null;


                banco.child("Usuarios").child(id.nome).child("ID").setValue(id.id);

                ids.add(id);
                UserList.notifyItemInserted(ids.size()-1);
            }
        }


    }

    protected void lerDados_user(){

        DatabaseReference reference = database.getReference().child("Usuarios");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot childDataSnapshot : snapshot.getChildren()){
                    //testeEt.setText(childDataSnapshot.getKey());
                    idModel id = new idModel();
                    id.nome = childDataSnapshot.getKey();
                    id.id = childDataSnapshot.child("ID").getValue().toString();
                    ids.add(id);
                }
                UserList.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}



//notas


//conseguir manter os dados salvos

