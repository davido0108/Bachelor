package domain;

import javafx.beans.property.IntegerProperty;

public class Spectacol extends Entity<Long>{
    private String artist, locatie;
    private String date;
    private Integer nrloc_d, nrloc_v;

    public Spectacol(String artist, String locatie, String date, Integer nrloc_d, Integer nrloc_v) {
        this.artist = artist;
        this.locatie = locatie;
        this.date = date;
        this.nrloc_d = nrloc_d;
        this.nrloc_v = nrloc_v;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getLocuriDisponibile() {
        return nrloc_d;
    }

    public void setNrloc_d(Integer nrloc_d) {
        this.nrloc_d = nrloc_d;
    }

    public Integer getLocuriVandute() {
        return nrloc_v;
    }

    public void setNrloc_v(Integer nrloc_v) {
        this.nrloc_v = nrloc_v;
    }

    @Override
    public String toString() {
        return "Spectacolul la care canta " +
                "artistul: " + artist +
                ", are locatia: " + locatie +
                ", la data: " + date +
                ", mai are " + nrloc_d +
                " locuri disponibile, si s au vandut: " + nrloc_v + " bilete";
    }
}
