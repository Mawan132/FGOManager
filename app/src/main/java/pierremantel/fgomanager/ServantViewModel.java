package pierremantel.fgomanager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ServantViewModel extends AndroidViewModel {
    private ServantRepository mRepository;


    private LiveData<List<Servant>> mAllServants;

    public ServantViewModel(Application application) {
        super(application);
        mRepository = new ServantRepository(application);
        mAllServants = mRepository.getAllServants();
    }

    LiveData<List<Servant>> getAllServants() {
        return mAllServants;
    }

    void insert(Servant servant) {
        mRepository.insert(servant);
    }

    void delete() { mRepository.delete();}
}

