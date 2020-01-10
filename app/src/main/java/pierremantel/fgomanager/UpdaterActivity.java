package pierremantel.fgomanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static pierremantel.fgomanager.ListServantactivity.CLEAR_REQUEST_CODE;

public class UpdaterActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private Button updateBtn;
    private Button clearBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updater);

        updateBtn = (Button) findViewById(R.id.btnUpdate);
        clearBtn = (Button) findViewById(R.id.btnClear);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateDatabase(v);
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickClearDatabase(v);
            }
        });
    }

    public void onClickUpdateDatabase(View v) {
        Toast.makeText(
                getApplicationContext(),
                R.string.update_database_start,
                Toast.LENGTH_LONG).show();
        new GetServantFromWebTask(this).execute();

    }

    public void onClickClearDatabase(View v) {
        Toast.makeText(
                getApplicationContext(),
                R.string.clear_database,
                Toast.LENGTH_LONG).show();
        Intent resultIntent = new Intent();
        setResult(CLEAR_REQUEST_CODE, resultIntent);
        finish();
    }

}
