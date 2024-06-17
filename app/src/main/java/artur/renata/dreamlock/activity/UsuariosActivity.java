package artur.renata.dreamlock.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.adapter.adapter_user;
import artur.renata.dreamlock.model.idModel;

public class UsuariosActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;
    List<idModel> ids = new ArrayList<>();
    adapter_user user;

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


        RecyclerView rvItensID = findViewById(R.id.listaPessoas);
        user = new adapter_user(this, ids);
        rvItensID.setAdapter(user);
        rvItensID.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItensID.setLayoutManager(layoutManager);

        //DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItensID.getContext(), DividerItemDecoration.VERTICAL);
        //rvItensID.addItemDecoration(dividerItemDecoration); //isso só decora


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == NEW_ITEM_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                idModel id = new idModel();
                id.nome = data.getStringExtra("nome");
                id.id = data.getStringExtra("id");
                ids.add(id);
                user.notifyItemInserted(ids.size()-1);
            }
        }


    }
}



//notas


//conseguir manter os dados salvos

