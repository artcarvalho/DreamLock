package artur.renata.dreamlock.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.activity.UsuariosActivity;
import artur.renata.dreamlock.model.idModel;

public class AdapterUser extends RecyclerView.Adapter{

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference banco = database.getReference();

    UsuariosActivity usuariosActivity;

    List<idModel> ids;

    String sala;
    public AdapterUser(UsuariosActivity usuariosActivity, List<idModel> ids){
        this.usuariosActivity = usuariosActivity;
        this.ids = ids;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(usuariosActivity);
        View v = inflater.inflate(R.layout.lista_usuarios,parent,false);
        return new ViewHolder_user(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int pos = position;
        idModel id = ids.get(pos);
        View v = holder.itemView;

        TextView nomeView = v.findViewById(R.id.nome_userView);
        nomeView.setText(id.nome);

        TextView idView = v.findViewById(R.id.id_tagView);
        idView.setText(id.id);

        ConstraintLayout btn_add = (ConstraintLayout) v.findViewById((R.id.btn_adicionar));
        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Bundle extras = usuariosActivity.getIntent().getExtras();
                sala = extras.getString("sala").toLowerCase();

                DatabaseReference add = database.getReference().child("Salas").child(sala).child("Acessos").child(id.nome);
                add.setValue(id.id);

                usuariosActivity.setResult(Activity.RESULT_OK);
                usuariosActivity.finish();
            }
        });

        Button btn_apagar = v.findViewById(R.id.btn_removeUser);
        btn_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                removeAt(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }
    public void removeAt(int position) {
        idModel id = ids.get(position);
        DatabaseReference del = database.getReference().child("Usuarios").child(id.nome);
        //futuramente excluir usuario de todas as salas e dar um jeito de atualizar a lista pra redesenhar
        del.removeValue();

    }
}