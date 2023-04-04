package lsdi.Models;

public class Performace {
    private double cpu;
    private double memory;

    public Performace() {
    }

    public Performace(double cpu, double memory, double disk) {
        this.cpu = cpu;
        this.memory = memory;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double memory) {
        this.memory = memory;
    }

    @Override
    public String toString() {
        return "Performace{" +
                "cpu=" + cpu +
                ", memory=" + memory +
                '}';
    }
}
