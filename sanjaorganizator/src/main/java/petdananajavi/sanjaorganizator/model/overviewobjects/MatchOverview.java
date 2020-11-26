package petdananajavi.sanjaorganizator.model.overviewobjects;

import petdananajavi.sanjaorganizator.model.databasemodels.Team;

public class MatchOverview {
    private String host;
    private String guest;
    private int hostPoints;
    private int guestPoints;
    private long id;
    private boolean isFinished;

    public MatchOverview(Team host, Team guest, int hostPoints, int guestPoints, long id, boolean isFinished) {
        this.host = host.getName();
        this.guest = guest.getName();
        this.hostPoints = hostPoints;
        this.guestPoints = guestPoints;
        this.id = id;
        this.isFinished = isFinished;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getHost() {
        return host;
    }

    public void setHost(Team host) {
        this.host = host.getName();
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(Team guest) {
        this.guest = guest.getName();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
