package maddux.firstscreen.model;

/**
 * Models an outsourced part.
 * author Sage Maddux
 */

public class Outsourced extends Part {
    /**
     * Company name for the part.
     */
    private String companyName;

    /**
     *  The constructor for the new instance of an outsourced part.
     * @param id the ID for the part
     * @param name the name for the part
     * @param price the price of the part
     * @param stock the inventory level of the part
     * @param min minimum level for the part
     * @param max maximum level for the part
     * @param companyName company name for the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * the gettter for the companyName
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     *  The setter for the company name
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
