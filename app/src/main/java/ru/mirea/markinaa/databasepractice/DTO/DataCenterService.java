package ru.mirea.markinaa.databasepractice.DTO;

public class DataCenterService {
    private String idService;
    private  String idRoom;
    private String equantity;

    public String getIdService() {
        return idService;
    }

    public void setIdService(String idService) {
        this.idService = idService;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
    }

    public String getEquantity() {
        return equantity;
    }

    public void setEquantity(String equantity) {
        this.equantity = equantity;
    }
}
