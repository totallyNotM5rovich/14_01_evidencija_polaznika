//Napiši program za evidenciju polaznika na tečaju. Program treba omogućiti unos polaznika i njihovih podataka te pružiti osnovne funkcije za upravljanje evidencijom.
//        Napravi klasu Polaznik koja ima sljedeće atribute:
//        Ime polaznika
//        Prezime polaznika
//        E-mail adresa polaznika
//        Napravi glavnu klasu EvidencijaPolaznika koja sadrži main metodu.
//        Omogući korisniku unos novih polaznika (ime, prezime, e-mail).
//        Omogući ispis svih polaznika na tečaju.
//        Omogući pretraživanje polaznika po e-mail adresi.
//        Za rješavanje koristite klasu ArrayList

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

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        EvidencijskaLista lista = new EvidencijskaLista();

        String[] akcije = {" 1. Unesi novog polaznika", " 2. Ispis evidentiranih polaznika", " 3. Pretrazivanje polaznika", " 4. Izlaz"};

        beskonacnaPetlja:
        while (true) {

            System.out.println("Evidencija polaznika tecaja");
            for (String akcija : akcije) {
                System.out.println(akcija);
            }
            System.out.println("Odaberite jednu od ponudjenih akcija (1-4):");
            int akcija = ObradaAkcija.odabirAkcije(4);

            switch (akcija) {
                case 1:
                    ObradaAkcija.dodavanjePolaznika(lista);
                    break;
                case 2:
                    ObradaAkcija.ispisEvidentiranihPolaznika(lista);
                    break;
                case 3:
                    ObradaAkcija.pretrazivanjePolaznika(lista);
                    break;
                case 4:
                    break beskonacnaPetlja;
                default:
            }
        }
    }
}