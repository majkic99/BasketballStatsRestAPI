package petdananajavi.sanjaorganizator.model.databasemodels;

import petdananajavi.sanjaorganizator.model.enums.PlayerPosition;

public class Player {

    private long id;
    private long teamId;
    private String firstName;
    private String lastName;
    private int number;
    private int height;
    private int age;
    private PlayerPosition position;

    public Player(){

    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public Player(long id, long teamId, String firstName, String lastName, int number, int height, int age, PlayerPosition position) {
        this.id = id;
        this.teamId = teamId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.height = height;
        this.age = age;
        this.position = position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTeamId() {
        return teamId;
    }

    public void setTeamId(long teamId) {
        this.teamId = teamId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }
}
