package artur.renata.dreamlock.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.activity.LogActivity;
import artur.renata.dreamlock.model.IdModel;
import artur.renata.dreamlock.model.LogModel;

public class AdapterLog extends RecyclerView.Adapter {

    LogActivity logActivity;
    List<LogModel> logList;

    public AdapterLog(LogActivity logActivity, List<LogModel> logList){
        this.logActivity = logActivity;
        this.logList = logList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(logActivity);
        View v = inflater.inflate(R.layout.lista_log,parent,false);
        return new ViewHolder_user(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int pos = position;
        LogModel log = logList.get(pos);
        View v = holder.itemView;

        TextView etID = v.findViewById(R.id.idVar);
        etID.setText(log.id);

        TextView etData = v.findViewById(R.id.dataVar);
        etData.setText(log.dataHora);

    }

    @Override
    public int getItemCount() {
        return logList.size();
    }
}
