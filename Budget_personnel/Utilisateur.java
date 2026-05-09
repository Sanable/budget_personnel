public class Utilisateur {
    protected String nom;
    protected int id;
    protected String email;
    public Utilisateur(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public String toString() {
        return "Utilisateur ID : " + id + "\nNom: " + nom + "\nEmail: " + email;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }   
}
