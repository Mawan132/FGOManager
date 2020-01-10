package pierremantel.fgomanager;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ServantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Servant servant);

    @Query("DELETE FROM servant_table")
    void deleteAll();

    @Query("SELECT * FROM servant_table")
    LiveData<List<Servant>> getAllServant();




}
