package petdananajavi.sanjaorganizator.model;

import petdananajavi.sanjaorganizator.model.databasemodels.Player;
import petdananajavi.sanjaorganizator.model.databasemodels.Team;

import java.util.HashMap;
import java.util.Map;

public class Match {
    private Team host;
    private Team guest;
    private int hostPoints;
    private int guestPoints;
    private long id;

    private Map<Long, Integer> pointsMap;
    private Map<Long, Integer> assistsMap;
    private Map<Long, Integer> jumpsMap;
    private boolean isFinished;

    public Match(long id, Team host, Team guest) {
        this.id = id;
        this.host = host;
        this.guest = guest;
        pointsMap = new HashMap<>();
        assistsMap = new HashMap<>();
        jumpsMap = new HashMap<>();
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Map<Long, Integer> getPointsMap() {
        return pointsMap;
    }

    public void setPointsMap(Map<Long, Integer> pointsMap) {
        this.pointsMap = pointsMap;
    }

    public Map<Long, Integer> getAssistsMap() {
        return assistsMap;
    }

    public void setAssistsMap(Map<Long, Integer>assistsMap) {
        this.assistsMap = assistsMap;
    }

    public Map<Long, Integer> getJumpsMap() {
        return jumpsMap;
    }

    public void setJumpsMap(Map<Long, Integer> jumpsMap) {
        this.jumpsMap = jumpsMap;
    }

    public Team getHost() {
        return host;
    }

    public void setHost(Team host) {
        this.host = host;
    }

    public Team getGuest() {
        return guest;
    }

    public void setGuest(Team guest) {
        this.guest = guest;
    }

    public int getHostPoints() {
        return hostPoints;
    }

    public void setHostPoints(int hostPoints) {
        this.hostPoints = hostPoints;
    }

    public int getGuestPoints() {
        return guestPoints;
    }

    public void setGuestPoints(int guestPoints) {
        this.guestPoints = guestPoints;
    }
}
