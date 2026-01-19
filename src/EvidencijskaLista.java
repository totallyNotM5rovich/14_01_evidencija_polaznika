import java.io.*;
import java.util.*;

public abstract class EvidencijskaLista {
    protected final File podaci = new File("./src/data.txt");

    protected abstract void ucitajPodatke();

    protected abstract void spremiPodatke() throws IOException;

    public abstract void dodajPolaznika(String ime, String prezime, String email) throws IOException;

    public abstract void pronadjiPolaznika(String email);

    public abstract String ispisTablicePolaznika(SmjerSortiranjaEnum smjer);

    public abstract boolean validacijaEmaila(String ime, String prezime, String email);

    public abstract int getSize();

}
