import java.util.ArrayList;

public class Rapport {
    protected int id;
    protected String date;
    protected double score;
    protected String resume;
    protected ArrayList<Transactions> transactions;

    public Rapport(int id, String date, double score, String resume, ArrayList<Transactions> transactions) {
        this.id = id;
        this.date = date;
        this.score = score;
        this.resume = resume;
        this.transactions = transactions;
    }

    public String toString() {
        return "Rapport ID : " + id + "\nDate: " + date + "\nScore: " + score + "\nRésumé: " + resume;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public double getScore() {
        return score;
    }

    public String getResume() {
        return resume;
    }

    public void generer() {
        System.out.println("Nombre de transactions : " + transactions.size());
        double totalDepenses=0;
        double totalRevenus=0;
        for(int i=0; i<transactions.size(); i++){
            if(transactions.get(i) instanceof Depenses){
                totalDepenses += transactions.get(i).getMontant();
            }
            else{
                totalRevenus += transactions.get(i).getMontant();
            }
        }
        this.score=(totalRevenus - totalDepenses)/totalRevenus*100;
        this.resume="Total des dépenses : " + totalDepenses + " | Total des revenus : " + totalRevenus + " | Score de budget : " + score;
        String conseils = "";
        if (score < 50) {
            conseils += "\nConseil : Vos dépenses dépassent la moitié de vos revenus, faites attention à votre budget";
        }
        if (score < 0) {
            conseils += "\nConseil : Vous dépensez plus que vous ne gagnez, veillez à réduire vos dépenses au prochain mois !";
        }
        this.resume += conseils;
    }
}
