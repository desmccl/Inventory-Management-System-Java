package com.example.newc4823;

/**this is the outsourced class, it etxends from part to get relevant information.*/
public class Outsourced extends Part{
    private String companyName;


    /**
     * this sets the information for an outsourced part, the super pulls from the part class.
     */
    public Outsourced(String name, double price, int stock, int min, int max, String companyName) {
        super(Inventory.nextPartId++, name, price, stock, min, max);
        this.companyName = companyName;
    }

    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }



    /**this returns the company name.*/
    public String getCompanyName() {
        return companyName;
    }

    /**this sets the company name.*/
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

