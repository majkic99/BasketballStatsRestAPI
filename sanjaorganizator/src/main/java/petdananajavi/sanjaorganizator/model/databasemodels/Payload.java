package petdananajavi.sanjaorganizator.model.databasemodels;

public class Payload {
    private long hostId;
    private long playerId;
    private long guestId;
    private int value;


    public Payload(long hostId, long playerId, long guestId, int value) {
        this.hostId = hostId;
        this.playerId = playerId;
        this.guestId = guestId;
        this.value = value;
    }

    public Payload() {
    }

    @Override
    public String toString() {
        return "Payload{" +
                "hostId=" + hostId +
                ", playerId=" + playerId +
                ", guestId=" + guestId +
                ", value=" + value +
                '}';
    }

    public long getHostId() {
        return hostId;
    }

    public void setHostId(long hostId) {
        this.hostId = hostId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public long getGuestId() {
        return guestId;
    }

    public void setGuestId(long guestId) {
        this.guestId = guestId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
