package maddux.firstscreen.model;

public class Outsourced extends Part {
    private String CompanyName;
    public Outsourced(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
        this.CompanyName = CompanyName;
    }

    public String getCompanyName() {
        return CompanyName;
    }
    public void setCompanyName(String companyName) {
        this.CompanyName = CompanyName;
    }
}
