public class Categorie {
    protected int id;
    protected String nom;
    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    public String toString() {
        return "Catégorie ID : " + id + "\nNom: " + nom;
    }
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }   
}
