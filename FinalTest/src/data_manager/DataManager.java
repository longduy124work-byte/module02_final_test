package data_manager;
import model.Contact;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static File file = new File("src/data/contacts.csv");

    public static List<Contact> getList() {
        ArrayList<Contact> list = new ArrayList<>();
        try {
            String data = "";
            if(!file.exists()) {
                throw new FileNotFoundException("Không tìm thấy file");
            }
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            while((data = buffer.readLine()) != null) {
                data = data.trim();
                if(data.isEmpty()) {
                    continue;
                }
                String[] parts = data.split(",");
                String phoneNumber = parts[0];
                String group = parts[1];
                String name = parts[2];
                String gender = parts[3];
                String address = parts[4];
                String birth = parts[5];
                String email = parts[6];
                Contact contact = new Contact(phoneNumber, group, name, gender, address, birth, email);
                list.add(contact);
            }
            return list;

        } catch(FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static void saveData(List<Contact> contacts) {
        String data = "";
        try {
            FileWriter writer = new FileWriter(file);
            BufferedWriter buffer = new BufferedWriter(writer);

            //header
            buffer.write("Số điện thoại,Nhóm,Họ tên,Giới tính,Địa chỉ,Ngày sinh,Email");
            buffer.newLine();

            for(Contact s: contacts) {
                data += s.getPhoneNumber() + "," + s.getGroup() + "," + s.getName() + "," + s.getGender()+ "," + s.getAddress() + "," + s.getBirth() + "," + s.getEmail() + "\n";
            }
            buffer.write(data);
            buffer.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
