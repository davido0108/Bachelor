package chat.domain;

public class BuyTicket extends Entity<Long>{
    Long id;
    Bilet bilet;

    public BuyTicket(Long id, Bilet bilet) {
        this.id = id;
        this.bilet = bilet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bilet getBilet() {
        return bilet;
    }

    public void setBilet(Bilet bilet) {
        this.bilet = bilet;
    }
}
