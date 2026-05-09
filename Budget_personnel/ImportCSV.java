import java.io.*;
import java.util.ArrayList;

public class ImportCSV {
    public String cheminFichier;
    public ImportCSV(String cheminFichier) {
        this.cheminFichier = cheminFichier;
    }


    public Categorie detecterCategorie(String description){
        if(description.equals("Metro") || description.equals("Bus") || description.equals("Train")){
            return new Categorie(1, "Transport");
        }

        if(description.equals("Courses") || description.equals("Restaurant") || description.equals("Lidl")){
            return new Categorie(2, "Alimentation");
        }

        if(description.equals("Electricite") || description.equals("Eau") || description.equals("Internet")){
            return new Categorie(3, "Factures");
        }

        if(description.equals("Salaire") || description.equals("Prime") || description.equals("Investissement")){
            return new Categorie(4, "Revenu");
        }

        if(description.equals("Netflix") || description.equals("Cinema") || description.equals("Spotify")){
            return new Categorie(5, "Loisirs");
        }

        return new Categorie(0, "Autres");
    }


    public ArrayList<Transactions> importer(){
        ArrayList<Transactions> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader (new FileReader(cheminFichier))){
            String ligne;
            br.readLine();
            while((ligne = br.readLine()) != null){
                String[] valeurs = ligne.split(",");
                if(valeurs[4].equals("depense")){
                    transactions.add(new Depenses(Integer.parseInt(valeurs[0]), Double.parseDouble(valeurs[1]), valeurs[2], valeurs[3], detecterCategorie(valeurs[3])));
                }
                else{
                    transactions.add(new Revenus(Integer.parseInt(valeurs[0]), Double.parseDouble(valeurs[1]), valeurs[2], valeurs[3], valeurs[3]));
                }
            }
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
        return transactions;
    }

}

