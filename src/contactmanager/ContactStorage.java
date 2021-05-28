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
import java.util.ArrayList;
import java.util.HashMap;
public interface ContactStorage {
    
    abstract void save();
    
    abstract ArrayList<Contact> list();
    
    abstract void UpdateContact(Contact c,int a);
    
    abstract void DeleteContact(int indexContact);
    
    abstract HashMap<Integer, Contact> Search(String n,int indexx);
    
    
    
}
