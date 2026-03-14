package service;
import data_manager.DataManager;
import lib.Input;
import model.Contact;
import utils.ValidateUtils;

import java.util.ArrayList;
import java.util.List;

public class ContactManager {
    private static List<Contact> contacts;
    private static DataManager dataManager;

    public ContactManager() {
        contacts = dataManager.getList();
        dataManager = new DataManager();
    }

    public static void add(Contact contact) {
        if (!ValidateUtils.isValidPhone(contact.getPhoneNumber())) {
            System.out.println("Phone number invalid");
            return;
        }
        if (!ValidateUtils.isValidEmail(contact.getEmail())) {
            System.out.println("Email invalid");
            return;
        }
        contacts.add(contact);
        dataManager.saveData(contacts);
    }

    public boolean edit(String phone, Contact newData) {

        for (Contact c : contacts) {
            if (c.getPhoneNumber().equals(phone)) {
                c.setGroup(newData.getGroup());
                c.setName(newData.getName());
                c.setGender(newData.getGender());
                c.setAddress(newData.getAddress());
                c.setBirth(newData.getBirth());
                c.setEmail(newData.getEmail());
                dataManager.saveData(contacts);
                return true;
            }
        }
        return false;
    }

    public Contact findByPhone(String phone) {
        for (Contact c : contacts) {
            if (c.getPhoneNumber().equals(phone)) {
                return c;
            }
        }
        return null;
    }

    public boolean delete(String phone) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getPhoneNumber().equals(phone)) {
                contacts.remove(i);
                dataManager.saveData(contacts);
                return true;
            }
        }
        return false;
    }

    public void display() {
        if (contacts.isEmpty()) {
            System.out.println("Danh bạ trống.");
            return;
        }
        int count = 0;

        for (Contact c : contacts) {
            System.out.println(c);
            count++;
            if (count % 5 == 0) {
                System.out.println("Nhấn Enter để xem tiếp...");
                Input.inputString();
            }
        }
    }

    public List<Contact> search(String keyword) {
        List<Contact> result = new ArrayList<>();
        for(Contact c : contacts){
            if(c.getPhoneNumber().contains(keyword) ||
                    c.getName().toLowerCase().contains(keyword)){

                result.add(c);
            }
        }

        return result;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

}
