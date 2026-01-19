//15.1.2026
//
//        Napiši program za evidenciju polaznika na tečaju koji također provjerava i sprječava dodavanje duplikata polaznika na tečaj. Program treba omogućiti unos polaznika i njihovih podataka te provjeriti jesu li polaznici jedinstveni na tečaju.
//
//        Koristi klasu Polaznik s atributima: ime, prezime i e-mail ✔
//        Koristi klasu HashSet<Polaznik> za pohranu polaznika kako bi se osigurala jedinstvenost ✔
//        Napravi glavnu klasu EvidencijaPolaznika koja sadrži main metodu ✔
//        Omogući korisniku unos novih polaznika (ime, prezime, e-mail) ✔
//        Pri dodavanju novog polaznika, provjeri je li polaznik već prisutan na tečaju (usporedba po e-mail adresi) ✔
//        Ispisuj odgovarajuće poruke o uspješnom ili neuspješnom dodavanju polaznika na tečaj ✔
//        Omogući ispis svih polaznika na tečaju nakon unosa ✔
//
//        Što bi trebalo izmijeniti u rješenju ako dodamo novi zahtjev?
//        Svi polaznici moraju biti cijelo vrijeme sortirani po prezimenu uzlazno



import java.io.*;
import java.util.*;

/**
 * Implementacija evidencijske liste pomocu Java skupova (klasa HashSet i TreeSet).
 * Klasa HashSet efikasnija prilikom dodavanja elemenata, ali elementi nisu sortirani.
 * Klasa TreeMap manje efikasna prilikom dodavanja elemenata, ali su elementi sortirani.
 * Zakomentirati i odkomentirati odgovarajucu deklaraciju i inicijalizaciju.
 */
public class EvidencijskaListaSet extends EvidencijskaLista{
    //private final HashSet<Polaznik> polaznici;
    private final TreeSet<Polaznik> polaznici;


    public EvidencijskaListaSet() {
        //this.polaznici = new HashSet<>();
        this.polaznici = new TreeSet<>();

        if(podaci.exists()) {
            ucitajPodatke();
        }
    }

    @Override
    protected void ucitajPodatke() {
        try (BufferedReader br = new BufferedReader(new FileReader(podaci))) {
            String polaznik;

            while ((polaznik = br.readLine()) != null) {
                String[] argumenti = polaznik.split("/");

                polaznici.add(new Polaznik(argumenti[0], argumenti[1], argumenti[2]));
            }
        } catch (IOException e) {
            System.out.println("Greska prilikom dohvacanja podataka: " + e.getMessage());
        }
    }

    @Override
    protected void spremiPodatke() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(podaci))) {
            for (Polaznik polaznik : polaznici) {
                pw.println(polaznik.ucitajPodatke());
            }
        }
    }

    @Override
    public void dodajPolaznika(String ime, String prezime, String email) throws IOException {
        Polaznik polaznikZaDodavanje = new Polaznik(ime, prezime, email);

        if(polaznici.contains(polaznikZaDodavanje)) {
            System.out.println("Polaznik sa unesenom e-mail adresom je vec evidentiran u evidencijskoj listi!");
        } else {
            polaznici.add(polaznikZaDodavanje);
            System.out.println("Polaznik uspjesno dodan u evidencijsku listu.");
        }

        spremiPodatke();
    }

    @Override
    public int getSize() {
        return polaznici.size();
    }

    @Override
    public void pronadjiPolaznika(String email) {
        for (Polaznik polaznik : polaznici) {
            if(polaznik.getEmail().equals(email)) {
                System.out.println(polaznik.toString());
                return;
            }
        }
        System.out.println("U evidencijskoj listi nema polaznika sa unesenom e-mail adresom.");
    }

    @Override
    public String ispisTablicePolaznika() {
        Polaznik[] polazniciLista = polaznici.toArray(new Polaznik[0]);

        int najduziRedniBroj = Integer.toString(polaznici.size()).length();
        int najduzeIme = 3;
        int najduzePrezime = 7;
        int najduziMail = 6;

        for (Polaznik polaznik : polaznici) {
            if(polaznik.getIme().length() > najduzeIme) {
                najduzeIme = polaznik.getIme().length();
            }
            if(polaznik.getPrezime().length() > najduzePrezime) {
                najduzePrezime = polaznik.getPrezime().length();
            }
            if(polaznik.getEmail().length() > najduziMail) {
                najduziMail = polaznik.getEmail().length();
            }
        }

        char vodoravnaLinija = '\u2501';
        char okomitaLinija = '\u2503';
        char tSpojnica = '\u2533';
        char krizanje = '\u254b';


        StringBuilder tablica = new StringBuilder();
        for (int i=0; i<najduziRedniBroj + 3; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(tSpojnica);
        for(int i=0; i<najduzeIme + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(tSpojnica);
        for(int i=0; i<najduzePrezime + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(tSpojnica);
        for(int i=0; i<najduziMail + 2; i++) {
            tablica.append(vodoravnaLinija);
        }



        tablica.append("\r\n # ");
        for (int i=1; i<najduziRedniBroj+1; i++) {
            tablica.append(' ');
        }
        tablica.append(okomitaLinija + " IME");
        for (int i=3; i<najduzeIme + 1; i++) {
            tablica.append(' ');
        }
        tablica.append(okomitaLinija + " PREZIME");
        for (int i=7; i<najduzePrezime + 1; i++) {
            tablica.append(' ');
        }
        tablica.append(okomitaLinija + " E-MAIL\r\n");


        for (int i=0; i<najduziRedniBroj + 3; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(krizanje);
        for(int i=0; i<najduzeIme + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(krizanje);
        for(int i=0; i<najduzePrezime + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append(krizanje);
        for(int i=0; i<najduziMail + 2; i++) {
            tablica.append(vodoravnaLinija);
        }
        tablica.append("\r\n");

        for (int i = 0; i < polazniciLista.length; i++) {
            String redniBroj = String.format(" %d.", (i+1));
            tablica.append(redniBroj);
            for (int j = redniBroj.length(); j<najduziRedniBroj + 3; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polazniciLista[i].getIme());
            for (int j = polazniciLista[i].getIme().length(); j<najduzeIme + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polazniciLista[i].getPrezime());
            for (int j = polazniciLista[i].getPrezime().length(); j<najduzePrezime + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polazniciLista[i].getEmail() + "\r\n");
        }
        return tablica.toString();
    }


    @Override
    public boolean validacijaEmaila(String ime, String prezime, String email) {

        return !polaznici.contains(new Polaznik(ime, prezime, email));
    }
}