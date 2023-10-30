package chat.domain;

public class Bilet extends Entity<Long>{
    String nume;
    int nrloc_dorite;

    public Bilet(String nume, int nrloc_dorite) {
        this.nume = nume;
        this.nrloc_dorite = nrloc_dorite;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getNrloc_dorite() {
        return nrloc_dorite;
    }

    public void setNrloc_dorite(int nrloc_dorite) {
        this.nrloc_dorite = nrloc_dorite;
    }


    public String toString(){
        return "Nume: " + this.nume + "are: "+ nrloc_dorite + " locuri rezervate";
    }


}
