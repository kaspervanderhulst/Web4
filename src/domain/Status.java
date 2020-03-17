package domain;

public enum Status {
    ONLINE("online"),OFFLINE("offline"),AWAY("away");

    private String description;

    Status(String description){this.description = description;}

    Status(){}

    public String getDescription() {
        return description;
    }
}
