package artur.renata.dreamlock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.activity.SalasActivity;
import artur.renata.dreamlock.model.IdModel;

public class AdapterAcesso extends RecyclerView.Adapter {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference banco = database.getReference();

    SalasActivity sala;
    List<IdModel> ids;

    public AdapterAcesso(SalasActivity sala, List<IdModel> ids){
        this.sala = sala;
        this.ids = ids;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(sala);
        View v = inflater.inflate(R.layout.lista_usuarios,parent,false);
        return new ViewHolder_user(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int pos = position;
        IdModel id = ids.get(pos);
        View v = holder.itemView;

        TextView nomeView = v.findViewById(R.id.nome_userView);
        nomeView.setText(id.nome);

        ImageButton btn_add = (ImageButton) v.findViewById(R.id.btn_add);
        btn_add.setVisibility(View.INVISIBLE);

        TextView idView = v.findViewById(R.id.id_tagView);
        idView.setText(id.id);
        Button btn_apagar = v.findViewById(R.id.btn_removeUser);
        btn_apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference del = database.getReference().child("Salas").child(id.sala).child("Acessos").child(id.nome);
                del.removeValue();

                ids.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, ids.size());

            }
        });
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }

}
