package afpa.convertisseur.modele;

public class Monnaie {

    private int id;
    private String label;
    private double valeur;

    public Monnaie() {
    }

    public Monnaie(String label, double valeur) {
        this.label = label;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public String toString() {
        return "ID : " + id + "\nISBN : " + label + "\nTitre : " + valeur;
    }
}
