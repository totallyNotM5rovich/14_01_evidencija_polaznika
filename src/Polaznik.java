import java.util.Objects;

public class Polaznik implements Comparable<Polaznik> {
    private String ime;
    private String prezime;
    private String email;

    public Polaznik(String ime, String prezime, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
    }

    public String ucitajPodatke() {
        return String.format("%s/%s/%s", ime, prezime, email);
    };

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Ime: " + ime + ", prezime: " + prezime + ", email: " + email;
    }

    /**
     * Zadtak koji zahtjeva implementaciju pomocu TreeSet zahtjeva da elementi budu sortirani prema prezimenu uzlazno, te je potrebno otkomentirati ovu metodu i zakomentirati sljedecu.
     */
//    @Override
//    public int compareTo(Polaznik o) {
//        return this.getPrezime().compareTo(o.getPrezime());
//    }

    /**
     * Zadtak koji zahtjeva implementaciju pomocu TreeMap zahtjeva da elementi budu sortirani prema emailu uzlazno, te je potrebno otkomentirati ovu metodu i zakomentirati proslu.
     */
    @Override
    public int compareTo(Polaznik o) {
        return this.getEmail().compareTo(o.getEmail());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Polaznik polaznik)) return false;
        return Objects.equals(email, polaznik.email);
    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }
}
