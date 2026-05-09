public abstract class Transactions {
    protected int id;
    protected double montant;
    protected String date;
    protected String description;
    public Transactions(int id, double montant, String date, String description) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public double getMontant() {
        return montant;
    }

    public String getDate() {
        return date;
    }   

    public String getDescription() {
        return description;
    }

}
