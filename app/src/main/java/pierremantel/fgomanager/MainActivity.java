package pierremantel.fgomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bt_servlist;
    Button bt_ownlist;
    Button bt_reslist;
    public static final String EXTRA_MESSAGE = "com.example.fgoresourcemanager";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_servlist = (Button) findViewById(R.id.buttonServList);
        bt_ownlist = (Button) findViewById(R.id.buttonOwned);
        bt_reslist = (Button) findViewById(R.id.buttonResource);

        bt_servlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListServantactivity.class);
                startActivity(intent);
            }
        });
    }
}
