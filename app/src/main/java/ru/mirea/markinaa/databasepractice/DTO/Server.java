package ru.mirea.markinaa.databasepractice.DTO;

public class Server {
    private String id;
    private String name;
    private String price;
    private String idCpu;
    private String idRam;
    private String idDrive;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIdCpu() {
        return idCpu;
    }

    public void setIdCpu(String idCpu) {
        this.idCpu = idCpu;
    }

    public String getIdRam() {
        return idRam;
    }

    public void setIdRam(String idRam) {
        this.idRam = idRam;
    }

    public String getIdDrive() {
        return idDrive;
    }

    public void setIdDrive(String idDrive) {
        this.idDrive = idDrive;
    }
}
