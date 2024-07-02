package artur.renata.dreamlock.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import artur.renata.dreamlock.R;
import artur.renata.dreamlock.adapter.AdapterLog;
import artur.renata.dreamlock.model.LogModel;

public class LogActivity extends AppCompatActivity {
    AdapterLog adapterLog;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String salaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salalog);

        Bundle extras = getIntent().getExtras();
        salaN = extras.getString("sala");

        TextView sala = findViewById(R.id.salaNumero);
        sala.setText(salaN);

        lerLogs(salaN);


    }



    protected void lerLogs(String salaN){

        DatabaseReference histRef = database.getReference().child("Historico").child(salaN.toLowerCase());
        histRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<LogModel> logList = new ArrayList<>();

                for (DataSnapshot childDataSnapshot : snapshot.getChildren()){
                    LogModel log = new LogModel();
                    log.dataHora = childDataSnapshot.getKey();
                    log.id = childDataSnapshot.getValue().toString();
                    logList.add(log);

                }

                RecyclerView rvLog = findViewById(R.id.logRecycler);
                adapterLog = new AdapterLog(LogActivity.this, logList);
                rvLog.setAdapter(adapterLog);
                rvLog.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(LogActivity.this);
                rvLog.setLayoutManager(layoutManager);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}

