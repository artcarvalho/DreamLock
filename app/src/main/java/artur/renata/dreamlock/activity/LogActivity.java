package artur.renata.dreamlock.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import artur.renata.dreamlock.R;

public class LogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salalog);

        Bundle extras = getIntent().getExtras();

        TextView sala = findViewById(R.id.salaNumero);
        sala.setText(extras.getString("sala"));


        //futuramente, ler os dados toda vez que uma bagacinha passar no leitor do esp (problema para o futuro);


    }
}