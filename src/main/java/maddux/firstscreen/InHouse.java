package maddux.firstscreen;

public class InHouse extends Part {

    private int machineId;

    public InHouse(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }
    public int getMachineId() {
        return machineId;
    }
}
