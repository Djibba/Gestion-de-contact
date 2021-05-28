/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contactmanager;

/**
 *
 * @author EliteBook
 */
import java.util.*;

public class ContactManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code applicsation logic here
        Scanner sc = new Scanner(System.in);
        boolean b = true;
        System.out.println("\t***************GESTION DES CONTACTS***************\n");
        do {
            int n = 0;
            int a = 0;
            int br = 0;
            System.out.println("\t\t Choisissez une option : \n"
                    + "\t\t\t 1-Liste des contacts \n"
                    + "\t\t\t 2-Enregistrer un nouveau contact\n"
                    + "\t\t\t 3-Rechercher un contact\n"
                    + "\t\t\t 4-Quitter");

            ContactStorageCsv c = new ContactStorageCsv();
            do {

                boolean r;

                try {

                    n = sc.nextInt();
                    r = true;

                } catch (InputMismatchException e) {
                    sc.next();
                    System.err.println("Vous devez saisir seulement un nombre (1 ou 0 ou 3 ou 4)");
                    r = false;
                }

                switch (n) {
                    case 1:
                        System.out.println("----------Liste des contacts----------\n");
                        ArrayList<Contact> con = new ArrayList<>();
                        con = c.list();
                        int index = 0;
                        Contact cc = null;

                        for (int i = 0; i < con.size(); i++) {
                            cc = con.get(i);
                            System.out.println(i + 1 + "- " + cc.getName() + " " + cc.getPhone());
                        }
                        System.out.println("\n------------------------------------\n" + "\t1- Afficher un contact\n\t0- Sortir de la liste ");
                        do {
                            boolean b2;
                            try {
                                a = sc.nextInt();
                                b2 = true;
                            } catch (InputMismatchException e) {
                                sc.next();
                                b2 = false;
                            }
                            boolean b1 = false;
                            do {
                                if (a == 1) {
                                    System.out.println("\n--------Entre l'index du contact à afficher-----------");
                                    index = sc.nextInt();
                                    if (index > con.size()) {
                                        System.out.println("Contact inexistant");
                                    } else {
                                        while (index != 0) {
                                            cc = con.get(index - 1);
                                            System.out.println(cc.displayContact());
                                            break;
                                        }
                                        System.out.println("\n---------------------------------------\n" + "\t1- Modifier le contact\n"
                                                + "\t2- Supprimer le contact" + "\n\t3- Quitter");
                                        int choix = sc.nextInt();
                                        switch (choix) {
                                            case 1:
                                                c.UpdateContact(cc, index);
                                                break;
                                            case 2:
                                                c.DeleteContact(index);
                                                break;
                                            case 3:
                                                break;
                                            default:
                                                System.err.println("Opération échouée");
                                        }

                                    }

                                    b1 = true;
                                } else if (a == 0) {
                                    b1 = false;
                                } else {
                                    System.out.println("Entrer 1 ou 0");
                                    b1 = true;
                                }
                                break;
                            } while (b1);
                        } while (a != 1 && a != 0);
                        break;

                    case 2:
                        System.out.println("----------Creer un nouveau contact----------\n");
                        c.save();
                        break;

                    case 3:
                        int indexx = 0;
                        System.out.println("Entrer la chaine à rechercher");
                        String nn = sc.nextLine();
                        HashMap<Integer, Contact> h = new HashMap();
                        h = c.Search(nn, indexx);
                        Contact ccc = null;
                        int aa = 0;
                        for (Map.Entry<Integer, Contact> entry : h.entrySet()) {
                            Integer key = entry.getKey();
                            key = aa;
                            Contact value = entry.getValue();
                            System.out.println(key+1 + "- " + value.getName() + " " + value.getPhone());
                            aa++;
                            
                        }
                        System.out.println("\n------------------------------------\n" + "\t1- Afficher un contact\n\t0- Sortir de la liste de recherche ");
                        do {
                            boolean b3;
                            try {
                                a = sc.nextInt();
                                b3 = true;
                            } catch (InputMismatchException e) {
                                sc.next();
                                b3 = false;
                            }
                            boolean b4 = false;
                            do {
                                if (a == 1) {
                                    System.out.println("\n--------Entre l'index du contact à afficher-----------");
                                    indexx = sc.nextInt();
                                    if (indexx > h.size()) {
                                        System.out.println("Contact inexistant");
                                    } else {
                                        while (indexx != 0) {
                                            System.out.println(h.get(indexx).displayContact());
                                            break;
                                        }
                                        System.out.println("\n---------------------------------------\n" + "\t1- Modifier le contact\n"
                                                + "\t2- Supprimer le contact" + "\n\t3- Quitter");
                                        int choix = sc.nextInt();

                                        switch (choix) {
                                            case 1:
                                                c.UpdateContact(ccc, indexx);
                                                break;
                                            case 2:
                                                c.DeleteContact(indexx);
                                                break;
                                            case 3:
                                                break;
                                            default:
                                                System.err.println("Opération échouée");
                                        }
                                        b4 = true;
                                    }

                                } else if (a == 0) {
                                    b4 = false;
                                } else {
                                    System.out.println("Entrer 1 ou 0");
                                    b4 = true;
                                }
                                break;
                            } while (b4);
                        } while (a != 1 && a != 0);
                        break;
                    case 4:
                        System.out.println("Au revoir");
                        System.exit(0);
                    default:
                        System.err.println("Entrer l'option 1 ou  2 ou 3 ou 4 pour le choix");
                }
            } while (n != 1 && n != 2 && n != 3 && n != 4);

            System.out.println("\n\t (1) Pour revenir au menu \t (0) Pour sortir");
            do {
                boolean bool;
                try {
                    br = sc.nextInt();
                    bool = true;
                } catch (InputMismatchException e) {
                    sc.next();
                    System.err.println("Entrer 1 ou 0");
                    bool = false;
                }

                switch (br) {
                    case 1:
                        b = true;
                        break;
                    case 0:
                        b = false;
                        break;
                    default:
                        System.err.println("Vous devez entrer 1 ou 0");
                }
            } while (br != 1 && br != 0);
        } while (b);
    }

}
