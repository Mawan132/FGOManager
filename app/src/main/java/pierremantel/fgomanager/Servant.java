package pierremantel.fgomanager;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "servant_table")
public class Servant implements Serializable {

    @PrimaryKey
    @NonNull
    private Integer id;

    private  String iconUrl;
    private String name;
    private String rarity;

    public Servant(@NonNull Integer id, String iconUrl, String name, String rarity) {
        this.id = id;
        this.iconUrl=iconUrl;
        this.name=name;
        this.rarity=rarity;
    }


    @NonNull
    public Integer getId() {
        return id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getName() {
        return name;
    }

    public String getRarity() {
        return rarity;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
