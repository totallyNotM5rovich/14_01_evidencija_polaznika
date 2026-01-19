import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        /**
         * Potrebno prilagoditi vrijednost varijable implementacija za odabir zeljene implementazije.
         */
        ImplementacijaEnum implementacija = ImplementacijaEnum.MAP;


        EvidencijskaLista lista = null;
        switch (implementacija) {
            case LIST:
                lista = new EvidencijskaListaList();
                break;
            case SET:
                lista = new EvidencijskaListaSet();
                break;
            case MAP:
                lista = new EvidencijskaListaMap();
                break;
        }

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
                    switch (implementacija) {
                        case LIST, MAP:
                            ObradaAkcija.dodavanjePolaznika(lista);
                            break;
                        case SET:
                            ObradaAkcija.dodavanjePolaznikaSet(lista);
                            break;
                    }
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