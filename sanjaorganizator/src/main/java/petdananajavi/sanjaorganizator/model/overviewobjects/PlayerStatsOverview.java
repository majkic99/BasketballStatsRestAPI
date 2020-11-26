package petdananajavi.sanjaorganizator.model.overviewobjects;

import petdananajavi.sanjaorganizator.model.databasemodels.Player;

public class PlayerStatsOverview {
    private Player player;
    private int points;
    private int jumps;
    private int assists;
    private int gamesPlayed;

    public PlayerStatsOverview(int points, int jumps, int assists, int gamesPlayed, Player player) {
        this.points = points;
        this.jumps = jumps;
        this.assists = assists;
        this.gamesPlayed = gamesPlayed;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getJumps() {
        return jumps;
    }

    public void setJumps(int jumps) {
        this.jumps = jumps;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
