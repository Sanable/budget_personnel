public class Depenses extends Transactions {
    private Categorie categorie;
    public Depenses(int id, double montant, String date, String description, Categorie categorie) {
        super(id, montant, date, description);
        this.categorie = categorie;
    }
    public String toString() {
        return "Dépense ID : " + id + "\nMontant: " + montant + "\nDate: " + date + "\nDescription: " + description + "\nCatégorie: " + categorie.getNom();
    }
    public Categorie getCategorie() {
        return categorie;
    }
}