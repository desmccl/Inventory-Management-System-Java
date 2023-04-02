package com.example.newc4823;

/**this is the inhouse class, it extends from the parts class.*/
public class InHouse extends Part{
    private int machineId;


    /**this sets the information for an inhouse part, the super pulls from the part class.*/
    public InHouse(String name, double price, int stock, int min, int max, int machineId) {
        super(Inventory.nextPartId++, name, price, stock, min, max);
        this.machineId = machineId;
    }

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**this returns the machineId.*/
    public int getMachineId() {
        return machineId;
    }

    /**this sets the machineId.*/
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}


