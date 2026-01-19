//Napiši program za evidenciju polaznika na tečaju koji osigurava jedinstvenost e-mail adresa polaznika. Program treba omogućiti unos polaznika i njihovih podataka te provjeriti jesu li e-mail adrese jedinstvene.
//
//Koristi klasu Polaznik s atributima: ime, prezime i e-mail
//Koristi HashMap<String, Polaznik> za pohranu polaznika, gdje će ključ biti e-mail adresa, a vrijednost objekt klase Polaznik.
//Napravi glavnu klasu EvidencijaPolaznika koja sadrži main metodu.
//Omogući korisniku unos novih polaznika (ime, prezime, e-mail).
//Pri dodavanju novog polaznika, provjeri je li e-mail adresa već prisutna u evidenciji polaznika. Ako je e-mail adresa već prisutna, ispiši odgovarajuću poruku i ne dopusti unos polaznika s istom e-mail adresom.
//Omogući ispis svih polaznika na tečaju nakon unosa.
//
//Što bi trebalo izmijeniti u rješenju ako dodamo novi zahtjev?
//Svi polaznici moraju biti cijelo vrijeme sortirani po emailu uzlazno


import java.io.*;
import java.util.*;

/**
 * Implementacija evidencijske liste pomocu Java mapa (klasa HashMap i TreeMap).
 * Klasa TreeMap osigurava da elementi budu sortirani.
 * Zakomentirati i odkomentirati odgovarajucu deklaraciju i inicijalizaciju.
 */
public class EvidencijskaListaMap extends EvidencijskaLista {
    //private final HashMap<String, Polaznik> polaznici;
    private final TreeMap<String, Polaznik> polaznici;

    public EvidencijskaListaMap() {
        //this.polaznici = new HashMap<>();
        this.polaznici = new TreeMap<>();

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

                polaznici.put(argumenti[2], new Polaznik(argumenti[0], argumenti[1], argumenti[2]));
            }
        } catch (IOException e) {
            System.out.println("Greska prilikom dohvacanja podataka: " + e.getMessage());
        }
    }

    @Override
    protected void spremiPodatke() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(podaci))) {
            for(Map.Entry<String, Polaznik> polaznikEntry: polaznici.entrySet()) {
                pw.println(polaznikEntry.getValue().ucitajPodatke());
            }
        }
    }

    @Override
    public void dodajPolaznika(String ime, String prezime, String email) throws IOException {
        Polaznik polaznikZaDodavanje = new Polaznik(ime, prezime, email);

        if(polaznici.containsKey(email)) {
            System.out.println("Polaznik sa unesenom e-mail adresom je vec evidentiran u evidencijskoj listi!");
        } else {
            polaznici.put(email, polaznikZaDodavanje);
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
        for(Map.Entry<String, Polaznik> polaznikEntry: polaznici.entrySet()) {
            if(polaznikEntry.getValue().getEmail().equals(email)) {
                System.out.println(polaznikEntry.getValue().toString());
                return;
            }
        }
        System.out.println("U evidencijskoj listi nema polaznika sa unesenom e-mail adresom.");
    }

    @Override
    public String ispisTablicePolaznika(SmjerSortiranjaEnum smjer) {
        //Polaznik[] polazniciLista = polaznici.values().toArray(new Polaznik[0]);
        List<Polaznik> polazniciLista = new ArrayList<>(polaznici.values());

        if(smjer == SmjerSortiranjaEnum.DESC) {
            Collections.reverse(polazniciLista);
        }
        if(smjer == SmjerSortiranjaEnum.SHUFFLE) {
            Collections.shuffle(polazniciLista);
        }

        int najduziRedniBroj = Integer.toString(polazniciLista.size()).length();
        int najduzeIme = 3;
        int najduzePrezime = 7;
        int najduziMail = 6;

        for (Polaznik polaznik : polazniciLista) {
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

        for (int i = 0; i < polazniciLista.size(); i++) {
            String redniBroj = String.format(" %d.", (i+1));
            tablica.append(redniBroj);
            for (int j = redniBroj.length(); j<najduziRedniBroj + 3; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polazniciLista.get(i).getIme());
            for (int j = polazniciLista.get(i).getIme().length(); j<najduzeIme + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polazniciLista.get(i).getPrezime());
            for (int j = polazniciLista.get(i).getPrezime().length(); j<najduzePrezime + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polazniciLista.get(i).getEmail() + "\r\n");
        }
        return tablica.toString();
    }

    @Override
    public boolean validacijaEmaila(String ime, String prezime, String email) {
        return !polaznici.containsKey(email);
    }
}
