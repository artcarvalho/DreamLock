package artur.renata.dreamlock.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.activity.MainActivity;
import artur.renata.dreamlock.activity.SalasActivity;
import artur.renata.dreamlock.model.SalaModel;

public class AdapterSalas extends RecyclerView.Adapter{

    MainActivity mainActivity;
    List<SalaModel> salas;


    public AdapterSalas(MainActivity mainActivity, List<SalaModel> salas){
        this.mainActivity = mainActivity;
        this.salas = salas;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.listas_stylesala,parent,false);
        return new ViewHolder_user(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int pos = position;
        SalaModel sala = salas.get(pos);
        View v = holder.itemView;

        TextView NomeSalaView = v.findViewById(R.id.nomeSala);
        NomeSalaView.setText(sala.nome);

        ConstraintLayout constraintLayoutSala = v.findViewById(R.id.constraintLayoutSala);

        constraintLayoutSala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mainActivity, SalasActivity.class);
                i.putExtra("nome", sala.nome);
                mainActivity.startActivity(i);
            }
        });

    }
    @Override
    public int getItemCount() {
        return salas.size();
    }

}
