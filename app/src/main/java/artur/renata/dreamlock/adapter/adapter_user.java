package artur.renata.dreamlock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.activity.UsuariosActivity;
import artur.renata.dreamlock.model.idModel;

public class adapter_user extends RecyclerView.Adapter{

    UsuariosActivity usuarios;
    List<idModel> ids;

    public adapter_user(UsuariosActivity usuarios, List<idModel> ids){
        this.usuarios = usuarios;
        this.ids = ids;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(usuarios);
        View v = inflater.inflate(R.layout.lista_usuarios,parent,false);
        return new viewHolder_user(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        idModel id = ids.get(position);
        View v = holder.itemView;

        TextView nomeView = v.findViewById(R.id.nome_userView);
        nomeView.setText(id.nome);

        TextView idView = v.findViewById(R.id.id_tagView);
        idView.setText(id.id);
    }

    @Override
    public int getItemCount() {
        return ids.size();
    }
}
