package artur.renata.dreamlock.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.ChildKey;

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

        Button btn_apagar = v.findViewById(R.id.btn_removeUser);
        TextView nomeView = v.findViewById(R.id.nome_userView);
        nomeView.setText(id.nome);

        TextView idView = v.findViewById(R.id.id_tagView);
        idView.setText(id.id);

        ImageButton btn_add = (ImageButton) v.findViewById(R.id.btn_add);
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
        DatabaseReference delLocalUser = database.getReference().child("Usuarios").child(id.nome);

        delLocalUser.removeValue();

        database.getReference().child("Salas").child("sala1").child("Acessos").child(id.nome).removeValue();
        database.getReference().child("Salas").child("sala2").child("Acessos").child(id.nome).removeValue();
        database.getReference().child("Salas").child("sala3").child("Acessos").child(id.nome).removeValue();

        ids.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, ids.size());


        DatabaseReference delGlobalUser = database.getReference().child("Salas");
        delGlobalUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childDataSnapshot : snapshot.getChildren()){

                    if(childDataSnapshot.child("Acessos").child(id.nome).exists()){
                       DatabaseReference temp = childDataSnapshot.child("Acessos").child(id.nome).getRef();
                       temp.removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}