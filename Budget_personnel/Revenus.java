public class Revenus extends Transactions {
    private String source;
    public Revenus(int id, double montant, String date, String description, String source) {
        super(id, montant, date, description);
        this.source = source;
    }

    public String toString() {
        return "Revenu ID : " + id + "\nMontant: " + montant + "\nDate: " + date + "\nDescription: " + description + "\nSource: " + source;
    }

    public String getSource() {
        return source;
    }
    
}
