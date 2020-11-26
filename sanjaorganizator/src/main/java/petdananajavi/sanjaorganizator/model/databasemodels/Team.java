package petdananajavi.sanjaorganizator.model.databasemodels;

import petdananajavi.sanjaorganizator.model.databasemodels.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private long id;
    private String name;
    private String city;
    private List<Player> playerList;

    public Team(){
        playerList = new ArrayList<>();
    }

    public Team(int id, String name, String city){
        this.id = id;
        this.name = name;
        this.city = city;
        playerList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

}
