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
public class Contact {

    private String name;
    private String phone;
    private String email;
    private String address;

    public Contact(String name, String phone, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String displayContact() {
        //Champs non vide
        String s = "Nom: " + this.name + "\n"
                + "Phone: " + this.phone + "\n"
                + "Email: " + this.email + "\n"
                + "Adresse: " + this.address;

        //Email et address vide
        String s1 = "Nom: " + this.name + "\n"
                + "Phone: " + this.phone;

        //adresse videS
        String s2 = "Nom: " + this.name + "\n"
                + "Phone: " + this.phone + "\n"
                + "Email: " + this.email;

        //email vide
        String s3 = "Nom: " + this.name + "\n"
                + "Phone: " + this.phone + "\n"
                + "Adresse: " + this.address;

        if ((this.email == null || this.email.equals("")) && (this.address == null || this.address.equals(""))) {
            return s1;
        } else if (this.email == null || this.email.equals("")) {
            return s3;
        } else if (this.address == null || this.address.equals("")) {
            return s2;
        } else {
            return s;
        }
    }
}
