package ru.mirea.markinaa.databasepractice.DTO;

public class CheckServer {
    private String idServer;
    private String idRoom;
    private String idCheck;
    private String amountOfDays;
    private String price;
    private String numberOfServers;

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

    public String getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(String idCheck) {
        this.idCheck = idCheck;
    }

    public String getAmountOfDays() {
        return amountOfDays;
    }

    public void setAmountOfDays(String amountOfDays) {
        this.amountOfDays = amountOfDays;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNumberOfServers() {
        return numberOfServers;
    }

    public void setNumberOfServers(String numberOfServers) {
        this.numberOfServers = numberOfServers;
    }
}
