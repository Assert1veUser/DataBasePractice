package ru.mirea.markinaa.databasepractice.DTO;

public class Rack {
    private String id;
    private String watts;
    private String idRoom;
    private String numberOfSection;
    private String wattsUsed;

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

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getNumberOfSection() {
        return numberOfSection;
    }

    public void setNumberOfSection(String numberOfSection) {
        this.numberOfSection = numberOfSection;
    }

    public String getWattsUsed() {
        return wattsUsed;
    }

    public void setWattsUsed(String wattsUsed) {
        this.wattsUsed = wattsUsed;
    }
}
