import java.sql.*;
import java.util.ArrayList;


public class ConnexionBD{
    protected String url;
    protected String utilisateur;
    protected String motDePasse;
    protected Connection connexion;

    public ConnexionBD(String url, String utilisateur, String motDePasse){
        this.url = url;
        this.utilisateur = utilisateur;
        this.motDePasse = motDePasse;
    }
    public String getUrl(){
    return url; 
    }

    public String getUtilisateur(){
        return utilisateur;
    }

    public String getMotDePasse(){
        return motDePasse;
    }

    public void connecter(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie !");
        }catch (SQLException e){
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
        catch (ClassNotFoundException e){
            System.err.println("Pilote JDBC introuvable : " + e.getMessage());
        }
    }

    public void deconnecter(){
        try{
            if(connexion != null && !connexion.isClosed()){
                connexion.close();
                System.out.println("Déconnexion réussie");
            }
        } catch (SQLException e){
            System.err.println("Erreur de déconnexion : " + e.getMessage());
        }
    }

    public void sauvegarderTransaction(Transactions transaction) {
        String categorie = "";
        String type = "";
        if (transaction instanceof Depenses) {
            categorie = ((Depenses) transaction).getCategorie().getNom();
            type = "depense";
        } else {
            categorie = ((Revenus) transaction).getSource();
            type = "revenu";
        }
        String sql = "INSERT INTO transactions (montant, date, description, categorie, type) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            pstmt.setDouble(1, transaction.getMontant());
            pstmt.setString(2, transaction.getDate());
            pstmt.setString(3, transaction.getDescription());
            pstmt.setString(4, categorie);
            pstmt.setString(5, type);
            pstmt.executeUpdate();
            System.out.println("Transaction sauvegardée");
        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }

    public ArrayList<Transactions> chargerTransactions() {
        ArrayList<Transactions> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try (PreparedStatement pstmt = connexion.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                double montant = rs.getDouble("montant");
                String date = rs.getString("date");
                String description = rs.getString("description");
                String categorie = rs.getString("categorie");
                String type = rs.getString("type");
                if (type.equals("depense")) {
                    transactions.add(new Depenses(id, montant, date, description, new Categorie(0, categorie)));
                } else {
                    transactions.add(new Revenus(id, montant, date, description, categorie));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors du chargement : " + e.getMessage());
        }
        return transactions;
    }

    public void viderTable() {
        try {
            PreparedStatement pstmt = connexion.prepareStatement("DELETE FROM transactions");
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}