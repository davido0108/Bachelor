package Network.Dto;

public class SpectacolDTO {
    private String artist;
    private String locatie;
    private String date;
    private Integer nrloc_d;
    private Integer nrloc_v;


    public SpectacolDTO(String artist, String locatie, String date, Integer nrloc_d, Integer nrloc_v) {
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

    public Integer getNrloc_d() {
        return nrloc_d;
    }

    public void setNrloc_d(Integer nrloc_d) {
        this.nrloc_d = nrloc_d;
    }

    public Integer getNrloc_v() {
        return nrloc_v;
    }

    public void setNrloc_v(Integer nrloc_v) {
        this.nrloc_v = nrloc_v;
    }
}
