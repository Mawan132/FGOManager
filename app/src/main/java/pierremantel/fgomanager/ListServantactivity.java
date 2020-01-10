package pierremantel.fgomanager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//The list of servants
public class ListServantactivity extends AppCompatActivity {

    public static final int NEW_SERVANT_ACTIVITY_REQUEST_CODE = 1;
    public static final int CLEAR_REQUEST_CODE = 1919191;


    private ServantViewModel mServantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_servantactivity);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ServantListAdapter adapter = new ServantListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        mServantViewModel = new ViewModelProvider(this).get(ServantViewModel.class);

        mServantViewModel.getAllServants().observe(this, new Observer<List<Servant>>() {
            @Override
            public void onChanged(@Nullable final List<Servant> servants) {
                // Update the cached copy of the servants in the adapter.
                adapter.setServants(servants);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListServantactivity.this, UpdaterActivity.class);
                startActivityForResult(intent, NEW_SERVANT_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Retour d'une demande d'update ( d'une GetServantFromWebTask)
        if (requestCode == NEW_SERVANT_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<Servant> servants = (ArrayList<Servant>) data.getSerializableExtra("Servant_list");
            Iterator<Servant> iterator = servants.iterator();
            while (iterator.hasNext()) {
                Servant servant = iterator.next();
                mServantViewModel.insert(servant);
            }
        //Retour d'une demande de clear Database
        } else if (requestCode == NEW_SERVANT_ACTIVITY_REQUEST_CODE && resultCode == CLEAR_REQUEST_CODE) {
            mServantViewModel.delete();
        //En cas d'erreur
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.problem,
                    Toast.LENGTH_LONG).show();
        }
    }
}

