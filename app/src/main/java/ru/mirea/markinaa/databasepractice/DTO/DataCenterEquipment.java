package ru.mirea.markinaa.databasepractice.DTO;

public class DataCenterEquipment {
    private String idServer;
    private String idRoom;
    private  String total;
    private String numberOfAvailable;

    public String getIdServer() {
        return idServer;
    }

    public void setIdServer(String idServer) {
        this.idServer = idServer;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNumberOfAvailable() {
        return numberOfAvailable;
    }

    public void setNumberOfAvailable(String numberOfAvailable) {
        this.numberOfAvailable = numberOfAvailable;
    }
}
