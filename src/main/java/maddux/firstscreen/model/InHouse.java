package maddux.firstscreen.model;

/**
 * Models an inhouse part
 * author Sage Maddux
 */

public class InHouse extends Part {
    /**
     * Machine ID for the part
     */

    private int machineId;

    /**
     * constructor for a new instance of an inhouse object.
     * @param id Id for Part
     * @param name Name for Part
     * @param price Price of part
     * @param stock inventory level of the Part
     * @param min min level for the part
     * @param max max level for the part
     * @param machineId machine ID for the part
     */

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     *  The getter for the machine ID
     * @return
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * The setter for the machine id.
     * @param machineId
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }


}
