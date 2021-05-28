
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import static java.nio.file.Paths.get;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.text.html.HTML.Tag.I;

public class ContactStorageCsv implements ContactStorage {

    File f;
    Scanner sc = new Scanner(System.in);
    String filename = "~/Desktop/java/";
    String name;
    String phone;
    String email;
    String address;
    ArrayList<Contact> c = new ArrayList<>();

    public Contact CreerContact() {

        while (name == null || name.equals("")) {
            System.out.println("Entrer votre nom et votre prénom : ");
            name = sc.nextLine();
        }

        while (phone == null || phone.equals("")) {
            System.out.println("Entrer votre numéro de téléphone :");
            phone = sc.nextLine();
        }

        System.out.println("Entrer votre mail : (optionnel)");
        email = sc.nextLine();

        System.out.println("Entrer votre adresse : (optionnel)");
        address = sc.nextLine();

        Contact con = new Contact(name, phone, email, address);
        return con;

    }

    @Override
    public void save() {
        f = new File(filename + "contact.csv");
        ArrayList<Contact> cont = new ArrayList<>();
        Contact contact = CreerContact();

        cont.add(contact);

        for (int i = 0; i < cont.size(); i++) {
            Contact c1 = cont.get(i);

            try {
                
                if (!f.getParentFile().exists()){
                    f.getParentFile().mkdirs();
                }
                if (!f.exists()) {
                    f.createNewFile();
                    PrintWriter fw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));;
                    fw.append(c1.getName() + ";" + c1.getPhone() + ";" + c1.getEmail() + ";" + c1.getAddress());
                    fw.append(System.lineSeparator());
                    System.out.println("\nContact sauvegardé avec succés ...");
                    fw.flush();
                } else {
                    PrintWriter fw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
                    fw.append(c1.getName() + ";" + c1.getPhone() + ";" + c1.getEmail() + ";" + c1.getAddress());
                    fw.append(System.lineSeparator());
                    System.out.println("\nContact sauvegardé avec succés ...");
                    fw.flush();
                }

            } catch (IOException ex) {
                Logger.getLogger(ContactStorageCsv.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    @Override
    public ArrayList<Contact> list() {
        try {
            ArrayList<Contact> conta = new ArrayList<>();
            ArrayList<String[]> st = new ArrayList<>();
            String line = "";
            FileReader fr = new FileReader(filename + "contact.csv");
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null) {
                String[] s = line.split(";", 4);
                st.add(s);
            }

            for (int i = 0; i < st.size(); i++) {
                String[] so = st.get(i);
                Contact cc = new Contact(so[0], so[1], so[2], so[3]);
                c.add(cc);
            }

            return c;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public void UpdateContact(Contact c, int index) {
        String newName;
        String newPhone;
        String newEmail;
        String newAddress;
        File f = new File(filename + "contact.csv");
        ArrayList<Contact> arrayC = new ArrayList<>();
        arrayC = list();
        System.out.println("Entrer la proprièté à laquelle vous voulez modifier sa valeur :\n "
                + "\t ->name \t ->phone\n"
                + "\t ->email \t ->adresse");
        String valuee = sc.nextLine();
        f.delete();
        for (int i = 0; i < arrayC.size(); i++) {
            Contact ct = arrayC.get(i);
            if (arrayC.get(i) == arrayC.get(index - 1)) {
                if (valuee.equalsIgnoreCase("name")) {
                    System.out.println("Entrer le nouveau nom de ( " + ct.getName() + " ) : ");
                    newName = sc.nextLine();
                    if(newName.isEmpty()){
                        ct.getName();
                    }else{
                        arrayC.get(index - 1).setName(newName);
                    }
                } else if (valuee.equalsIgnoreCase("phone")) {
                    System.out.println("Entrer le nouveau numero de (" + ct.getPhone() + ") : ");
                    newPhone = sc.nextLine();
                    if(newPhone.isEmpty()){
                        ct.getPhone();
                    }else{
                        arrayC.get(index - 1).setName(newPhone);
                    }
                } else if (valuee.equalsIgnoreCase("email")) {
                    System.out.println("Entrer le nouveau email de (" + ct.getEmail() + ") : ");
                    newEmail = sc.nextLine();
                    if(newEmail.isEmpty()){
                        ct.getEmail();
                    }else{
                        arrayC.get(index - 1).setEmail(newEmail);
                    }
                } else if (valuee.equalsIgnoreCase("adresse")) {
                    System.out.println("Entrer la nouvelle adresse de (" + ct.getAddress() + ") : ");
                    newAddress = sc.nextLine();
                    if(newAddress.isEmpty()){
                        ct.getAddress();
                    }else{
                        arrayC.get(index - 1).setAddress(newAddress);
                    }
                } else {
                    System.err.println("Proprété inexistante");
                }
                arrayC.get(i);
            } else {
                arrayC.remove(index);
            }
        }

        try {
            f = new File(filename + "contact.csv");
            f.createNewFile();
            BufferedWriter fw = new BufferedWriter(new FileWriter(f));
            for (int j = 0; j < arrayC.size(); j++) {
                c = arrayC.get(j);
                fw.append(c.getName() + ";" + c.getPhone() + ";" + c.getEmail() + ";" + c.getAddress());
                fw.append(System.lineSeparator());
            }
            System.out.println("\nContact modifié avec succés ...");
            fw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ContactStorageCsv.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void DeleteContact(int indexContact) {
        File f = new File(filename + "contact.csv");
        ArrayList<Contact> arrayC = new ArrayList<>();
        arrayC = list();
        f.delete();

        
        System.out.println("\n*****Voulez vous supprimer le contact " + arrayC.get(indexContact - 1).getName() + "*****\n\t 1-OUI \t 2-NON ");
        int a = sc.nextInt();
        if (a == 1) {
            for (int i = 0; i < arrayC.size(); i++) {
                if (arrayC.get(i) == arrayC.get(indexContact - 1)) {
                    arrayC.remove(indexContact - 1);
                } else {
                    arrayC.get(indexContact);
                }
                arrayC.remove(indexContact - 1);
            }

            try {
                f = new File(filename + "contact.csv");
                f.createNewFile();
                BufferedWriter fw = new BufferedWriter(new FileWriter(f));
                for (int j = 0; j < arrayC.size(); j++) {
                    Contact c = arrayC.get(j);
                    fw.append(c.getName() + ";" + c.getPhone() + ";" + c.getEmail() + ";" + c.getAddress());
                    fw.append(System.lineSeparator());
                }
                System.out.println("\nContact supprimé avec succés ...");
                fw.flush();
            } catch (IOException ex) {
                Logger.getLogger(ContactStorageCsv.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (a == 2) {
            System.out.println("Au revoir");
        } else {
            System.err.println("Vous devez entrer que 1 ou 2");
        }

    }

    @Override
    public HashMap<Integer, Contact> Search(String n,int index) {
        ArrayList<Contact> arrayc = new ArrayList<>();
        HashMap h = new HashMap();
        arrayc = list();
        n = sc.nextLine();
        for (int i = 0; i < arrayc.size(); i++) {
            Contact cc = arrayc.get(i);
            if (cc.getName().contains(n) || cc.getPhone().contains(n) || cc.getEmail().contains(n) || cc.getAddress().contains(n) ) {
                h.put(i+1, cc);
            }
        }
        return h;
    }

}
