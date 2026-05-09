import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez le chemin de votre fichier CSV");
        String chemin = scanner.nextLine();
        ImportCSV importeur = new ImportCSV(chemin);

        ArrayList<Transactions> liste = importeur.importer();

        for (int i = 0; i < liste.size(); i++){
            System.out.println(liste.get(i));
        }
        Rapport rapport = new Rapport(1, "2025-01-03", 0, "", liste);
        rapport.generer();
        System.out.println(rapport);

        ConnexionBD bd = new ConnexionBD("jdbc:mysql://localhost:3306/budget_personnel", "root", "");
        bd.connecter();
        bd.viderTable();

        for (int i = 0; i < liste.size(); i++){
            bd.sauvegarderTransaction(liste.get(i));
        }

        System.out.println("\nTransactions chargées depuis la base de données :");
        ArrayList<Transactions> listeDepuisBD = bd.chargerTransactions();
        for (int i = 0; i < listeDepuisBD.size(); i++) {
            System.out.println(listeDepuisBD.get(i));
        }

        bd.deconnecter();
        scanner.close();

    }
}