package ru.mirea.markinaa.databasepractice.DTO;

public class CheckService {
    private String isService;
    private String idCheck;
    private String idRoom;
    private String amountOfDays;
    private String price;
    private String numberOfService;

    public String getIsService() {
        return isService;
    }

    public void setIsService(String isService) {
        this.isService = isService;
    }

    public String getIdCheck() {
        return idCheck;
    }

    public void setIdCheck(String idCheck) {
        this.idCheck = idCheck;
    }

    public String getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(String idRoom) {
        this.idRoom = idRoom;
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

    public String getNumberOfService() {
        return numberOfService;
    }

    public void setNumberOfService(String numberOfService) {
        this.numberOfService = numberOfService;
    }
}
