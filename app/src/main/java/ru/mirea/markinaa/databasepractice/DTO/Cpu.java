package ru.mirea.markinaa.databasepractice.DTO;

public class Cpu {
    private String id;
    private String model;
    private String cores;
    private String threads;
    private String frequency;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCores() {
        return cores;
    }

    public void setCores(String cores) {
        this.cores = cores;
    }

    public String getThreads() {
        return threads;
    }

    public void setThreads(String threads) {
        this.threads = threads;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }



}
