package petdananajavi.sanjaorganizator.model.databasemodels;

import petdananajavi.sanjaorganizator.model.enums.EventType;

public class Event {
    private long game;
    private EventType type;
    private Payload payload;

    public Event() {
    }

    public Event(long game, EventType eventType, Payload payload) {
        this.game = game;
        this.type = eventType;
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "Event{" +
                "game=" + game +
                ", type=" + type +
                ", payload=" + payload +
                '}';
    }

    public long getGame() {
        return game;
    }

    public void setGame(long game) {
        this.game = game;
    }

    public EventType getEventType() {
        return type;
    }

    public void setEventType(EventType eventType) {
        this.type = eventType;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
}
