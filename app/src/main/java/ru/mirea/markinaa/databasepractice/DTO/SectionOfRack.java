package ru.mirea.markinaa.databasepractice.DTO;

public class SectionOfRack {
    private String id;
    private String watts;
    private String idRack;
    private String idClient;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWatts() {
        return watts;
    }

    public void setWatts(String watts) {
        this.watts = watts;
    }

    public String getIdRack() {
        return idRack;
    }

    public void setIdRack(String idRack) {
        this.idRack = idRack;
    }

    public String getIdClient() {
        return idClient;
    }

    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
}
