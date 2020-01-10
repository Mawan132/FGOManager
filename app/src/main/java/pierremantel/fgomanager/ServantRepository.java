package pierremantel.fgomanager;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class ServantRepository {

    private ServantDao mServantDao;
    private LiveData<List<Servant>> mAllServants;


    ServantRepository(Application application) {
        ServantRoomDatabase db = ServantRoomDatabase.getDatabase(application);
        mServantDao = db.servantDao();
        mAllServants = mServantDao.getAllServant();
    }

    LiveData<List<Servant>> getAllServants() {
        return mAllServants;
    }

    void insert(final Servant serv) {
        ServantRoomDatabase.databaseWriteExecutor.execute(() -> {
            mServantDao.insert(serv);
        });
    }

    void delete() {
        ServantRoomDatabase.databaseWriteExecutor.execute(()-> {
            mServantDao.deleteAll();
        });
    }
}