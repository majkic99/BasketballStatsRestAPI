package petdananajavi.sanjaorganizator.model.overviewobjects;

import petdananajavi.sanjaorganizator.model.databasemodels.Team;

public class TeamResultsOverview {

    private Team team;
    private long pointsScored;
    private long pointsReceived;
    private double winRate;

    public TeamResultsOverview(Team team, long pointsScored, long pointsReceived, double winRate) {
        this.team = team;
        this.pointsScored = pointsScored;
        this.pointsReceived = pointsReceived;
        this.winRate = winRate;
    }


    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public long getPointsScored() {
        return pointsScored;
    }

    public void setPointsScored(int pointsScored) {
        this.pointsScored = pointsScored;
    }

    public long getPointsReceived() {
        return pointsReceived;
    }

    public void setPointsReceived(int pointsReceived) {
        this.pointsReceived = pointsReceived;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(int winRate) {
        this.winRate = winRate;
    }
}
