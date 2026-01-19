import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ObradaAkcija {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int odabirAkcije(int max) {
        boolean validnaAkcija = false;
        int akcija = 1;
        do {
            try {
                String unos = br.readLine().trim();
                akcija = Integer.parseInt(unos);
                if (akcija < 1 || akcija > max) {
                    throw new IllegalArgumentException("Neispravan unos! Odaberite jednu od ponudjenih akcija (1-4)");
                }
                validnaAkcija = true;
            } catch (NumberFormatException e) {
                System.out.println("Neispravan unos! Ocekivani unos je broj (1-4):");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Neispravan unos!");
            }
        } while (!validnaAkcija);
        return akcija;
    }

    public static void dodavanjePolaznika(EvidencijskaLista lista) throws IOException {
        String ime = null;
        String prezime = null;
        String email = null;
        boolean jedinstveniEmail = false;

        dodajPolaznikaLoop:
        while(true) {

            System.out.printf(" 1. Unesite ime polaznika: %s\r\n", ((ime == null) ? "NEDEFINIRANO" : ime));
            System.out.printf(" 2. Unesite prezime polaznika: %s\r\n", ((prezime == null) ? "NEDEFINIRANO" : prezime));
            System.out.printf(" 3. Unesite e-mail polaznika: %s\r\n", ((email == null) ? "NEDEFINIRAN" : email));
            if(!jedinstveniEmail && email != null) {
                System.out.println("Polaznik sa unesenom E-mail adresom je vec evidentiran!");
            }
            System.out.println(" 4. Odustani");
            if(ime != null && prezime != null && email != null && jedinstveniEmail) {
                System.out.println(" 5. Dodaj polaznika");
            }

            System.out.println("Odaberite jednu od ponudjenih akcija (1-5):");
            int akcijaDodavanje = ObradaAkcija.odabirAkcije(5);

            switch (akcijaDodavanje) {
                case 1:
                    ime = unosImePrezime('i');
                    break;
                case 2:
                    prezime = unosImePrezime('p');
                    break;
                case 3:
                    email = unosEmail();
                    jedinstveniEmail = lista.validacijaEmaila(ime, prezime, email);
                    break;
                case 4:
                    break dodajPolaznikaLoop;
                case 5:
                    if(ime != null && prezime != null && email != null && jedinstveniEmail) {
                        lista.dodajPolaznika(ime, prezime, email);
                        //System.out.println("Polaznik uspjesno dodan u evidenciju!");
                    } else {
                        if (!(ime != null && prezime != null && email != null)) {
                            System.out.println("Neuspjesno dodavanje novog polaznika! Niste unesli sve potrebne podatke!");
                        }
                        if (!jedinstveniEmail) {
                            System.out.println("Neuspjesno dodavanje novog polaznika! Polaznik sa unesenom E-mail adresom je vec evidentiran!");
                        }
                        break;
                    }
                    break dodajPolaznikaLoop;
                default:
            }

        }
    }

    public static void dodavanjePolaznikaSet(EvidencijskaLista lista) throws IOException {
        String ime = null;
        String prezime = null;
        String email = null;
        boolean jedinstveniEmail = false;

        dodajPolaznikaLoop:
        while(true) {

            System.out.printf(" 1. Unesite ime polaznika: %s\r\n", ((ime == null) ? "NEDEFINIRANO" : ime));
            System.out.printf(" 2. Unesite prezime polaznika: %s\r\n", ((prezime == null) ? "NEDEFINIRANO" : prezime));
            System.out.printf(" 3. Unesite e-mail polaznika: %s\r\n", ((email == null) ? "NEDEFINIRAN" : email));
            if (!jedinstveniEmail && email != null) {
                System.out.println("Polaznik sa unesenom E-mail adresom je vec evidentiran!");
            }
            System.out.println(" 4. Odustani");
            if(ime != null && prezime != null && email != null && jedinstveniEmail) {
                System.out.println(" 5. Dodaj polaznika");
            }

            System.out.println("Odaberite jednu od ponudjenih akcija (1-5):");
            int akcijaDodavanje = ObradaAkcija.odabirAkcije(5);

            switch (akcijaDodavanje) {
                case 1:
                    ime = unosImePrezime('i');
                    if (ime != null && prezime != null && email != null) {
                        jedinstveniEmail = lista.validacijaEmaila(ime, prezime, email);
                    }
                    break;
                case 2:
                    prezime = unosImePrezime('p');
                    if (ime != null && prezime != null && email != null) {
                        jedinstveniEmail = lista.validacijaEmaila(ime, prezime, email);
                    }
                    break;
                case 3:
                    email = unosEmail();
                    if (ime != null && prezime != null && email != null) {
                        jedinstveniEmail = lista.validacijaEmaila(ime, prezime, email);
                    }
                    break;
                case 4:
                    break dodajPolaznikaLoop;
                case 5:
                    if(ime != null && prezime != null && email != null && jedinstveniEmail) {
                        lista.dodajPolaznika(ime, prezime, email);
                        //System.out.println("Polaznik uspjesno dodan u evidenciju!");
                    } else {
                        if (!(ime != null && prezime != null && email != null)) {
                            System.out.println("Neuspjesno dodavanje novog polaznika! Niste unesli sve potrebne podatke!");
                        }
                        if (!jedinstveniEmail) {
                            System.out.println("Neuspjesno dodavanje novog polaznika! Polaznik sa unesenom E-mail adresom je vec evidentiran!");
                        }
                        break;
                    }
                    break dodajPolaznikaLoop;
                default:
            }

        }
    }

    private static String unosImePrezime(char podatak) {
        String ime_prezime = null;
        boolean validanUnos = false;
        String poruka = podatak == 'i' ? "\r\nUpisite ime polaznika:" : "\r\nUpisite prezime polaznika:";
        System.out.println(poruka);
        do {
            try {
                String unos = br.readLine().trim();
                if (unos.isEmpty()) {
                    //String
                    throw new NeispravniPodaciException("Ime je obavezan podatak:");
                }
                if (!unos.matches("^[A-ZĆČĐŠŽa-zćčđšž\\s\\-]{2,}")) {
                    throw new NeispravniPodaciException("Unesite ispravno ime:");
                }
                ime_prezime = unos;
                validanUnos = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validanUnos);

        return ime_prezime;
    }

    private static String unosEmail() {
        String email = null;
        boolean validanUnos = false;
        System.out.println("\r\nUpisite E-mail polaznika (formata example@domena.com)");
        do {
            try {
                String unos = br.readLine().trim();
                if (unos.isEmpty()) {

                    throw new NeispravniPodaciException("E-mail je obavezan podatak:");
                }
                if (!unos.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
                    throw new NeispravniPodaciException("Unesite ispravno formatirani E-mail (pr. example@domena.com):");
                }
                email = unos;
                validanUnos = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (NeispravniPodaciException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Greska!");
            }
        } while (!validanUnos);

        return email;
    }

    public static void ispisEvidentiranihPolaznika(EvidencijskaLista lista, SmjerSortiranjaEnum smjer) {
        if(lista.getSize() == 0) {
            System.out.println("Evidencijska lista polaznika je prazna!");
            return;
        }
        System.out.println(lista.ispisTablicePolaznika(smjer));
    }

    public static void pretrazivanjePolaznika(EvidencijskaLista lista) {
        //System.out.println("Unesite e-mail polaznika:");
        String email = unosEmail();

        lista.pronadjiPolaznika(email);

    }

    public static SmjerSortiranjaEnum prilagodbaSortiranja(SmjerSortiranjaEnum smjer) {
        prilagodbaSortiranjaLoop:
        while(true) {
            System.out.println("Odaberite smjer sortiranja polaznika u ispisu (odabirom pridruzenog rednog broja):");
            System.out.println("Trenutno postavljno: " + smjer.getOpis());
            System.out.println(" 1. Uzlazno");
            System.out.println(" 2. Silazno");
            System.out.println(" 3. Nasumicno");
            System.out.println(" 4. Odustani");

            int smjerSortiranja = odabirAkcije(4);

            switch (smjerSortiranja) {
                case 1:
                    return SmjerSortiranjaEnum.ASC;
                case 2:
                    return SmjerSortiranjaEnum.DESC;
                case 3:
                    return SmjerSortiranjaEnum.SHUFFLE;
                case 4:
                    break prilagodbaSortiranjaLoop;
                default:
            }
        }
        return SmjerSortiranjaEnum.ASC;
    }

//    private static boolean validirajJedinstvenostPolaznika(EvidencijskaLista lista, String ime, String prezime, String email) {
//        if(ime != null && prezime != null && email != null) {
//            return lista.validacijaEmaila(ime, prezime, email);
//        }
//        return false;
//    }


}
