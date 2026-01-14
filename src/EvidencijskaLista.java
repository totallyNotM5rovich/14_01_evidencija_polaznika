import java.io.*;
import java.util.ArrayList;

public class EvidencijskaLista {
    private final File podaci = new File("./src/data.txt");
    private final ArrayList<Polaznik> polaznici;

    public EvidencijskaLista() {
        this.polaznici = new ArrayList<>();

        if(podaci.exists()) {
            ucitajPodatke();
        }
    }

    private void ucitajPodatke() {
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

    private void spremiPodatke() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(podaci))) {
            for (Polaznik polaznik : polaznici) {
                pw.println(polaznik.ucitajPodatke());
            }
        }
    }

    public void dodajPolaznika(String ime, String prezime, String email) throws IOException {
        polaznici.add(new Polaznik(ime, prezime, email));
        spremiPodatke();
    }

    public int getSize() {
        return polaznici.size();
    }

    public void pronadjiPolaznika(String email) {
        for (Polaznik polaznik : polaznici) {
            if(polaznik.getEmail().equals(email)) {
                System.out.println(polaznik.toString());
                return;
            }
        }
        System.out.println("U evidencijskoj listi nema polaznika sa unesenom e-mail adresom.");
    }

    public String ispisTablicePolaznika() {
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

        for (int i = 0; i < polaznici.size(); i++) {
            String redniBroj = String.format(" %d.", (i+1));
            tablica.append(redniBroj);
            for (int j = redniBroj.length(); j<najduziRedniBroj + 3; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polaznici.get(i).getIme());
            for (int j = polaznici.get(i).getIme().length(); j<najduzeIme + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polaznici.get(i).getPrezime());
            for (int j = polaznici.get(i).getPrezime().length(); j<najduzePrezime + 1; j++) {
                tablica.append(' ');
            }
            tablica.append(okomitaLinija + " " + polaznici.get(i).getEmail() + "\r\n");
        }

        return tablica.toString();
    }
}
