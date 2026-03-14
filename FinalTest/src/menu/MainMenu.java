package menu;
import lib.Input;
import model.Contact;
import service.ContactManager;
import data_manager.DataManager;
import utils.ValidateUtils;

import java.util.List;

public class MainMenu {
    private ContactManager contactManager;
    private DataManager dataManager;

    public MainMenu() {
        this.contactManager = new ContactManager();
        this.dataManager = new DataManager();
    }
    public void showMain() {
        int choice;
        do {
            System.out.println("====== Chương trình quản lý danh bạ ======");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xoá");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi từ file");
            System.out.println("8. Thoát");
            System.out.println("Chọn chức năng: ");
            choice = Input.inputNumber();

            switch(choice) {
                case 1:
                contactManager.display();
                    break;
                case 2:
                    showAddMenu();
                    break;
                case 3:
                    showEditMenu();
                    break;
                case 4:
                showDeleteMenu();
                    break;
                case 5:
                showSearchMenu();
                    break;
                case 6:
                    showReadFileMenu();
                    break;
                case 7:
                    showWriteFileMenu();
                    break;
                case 8:
                    System.out.println("Exit the program");
                    break;
                default:
                    System.out.println("Enter choices in the menu: ");
                    break;
            }
        } while(choice != 8);

    }

    public void showAddMenu() {
        System.out.println("====== Add Menu =======");
        String phoneNumber = inputPhone();
        String email;
        do {
            System.out.print("Nhập email: ");
            email = Input.inputString();

            if (!ValidateUtils.isValidEmail(email)) {
                System.out.println("Email không hợp lệ!");
            }

        } while(!ValidateUtils.isValidEmail(email));
        System.out.println("Enter contact group: ");
        String group = Input.inputString();
        System.out.println("Enter gender: ");
        String gender = Input.inputString();
        System.out.println("Enter name: ");
        String name = Input.inputString();
        System.out.println("Enter address: ");
        String address = Input.inputString();
        System.out.println("Enter birth of date: ");
        String birth = Input.inputString();
        Contact contact = new Contact(phoneNumber, group, name, gender, address,birth, email);
        contactManager.add(contact);
        System.out.println("Add contact successfully!");
    }

    public void showEditMenu() {
        System.out.println("===== Cập nhật danh bạ =====");
        while (true) {
            System.out.print("Nhập số điện thoại cần sửa: ");
            String phone = Input.inputString();
            if (phone.isEmpty()) {
                return;
            }

            Contact contact = contactManager.findByPhone(phone);

            if (contact == null) {
                System.out.println("Không tìm được danh bạ với số điện thoại trên.");
                continue;
            }

            System.out.println("Nhập thông tin mới:");
            System.out.print("Nhóm: ");
            String group = Input.inputString();
            System.out.print("Họ tên: ");
            String name = Input.inputString();
            System.out.print("Giới tính: ");
            String gender = Input.inputString();
            System.out.print("Địa chỉ: ");
            String address = Input.inputString();
            System.out.print("Ngày sinh: ");
            String birth = Input.inputString();
            String email;
            do {
                System.out.print("Email: ");
                email = Input.inputString();

                if (!ValidateUtils.isValidEmail(email)) {
                    System.out.println("Email không hợp lệ!");
                }

            } while (!ValidateUtils.isValidEmail(email));
            Contact newContact = new Contact(phone, group, name, gender, address, birth, email);
            contactManager.edit(phone, newContact);
            System.out.println("Cập nhật danh bạ thành công!");
            return;
        }
    }

    public String inputPhone() {
        String phone;

        do {
            System.out.print("Nhập số điện thoại: ");
            phone = Input.inputString();

            if (!ValidateUtils.isValidPhone(phone)) {
                System.out.println("Số điện thoại không hợp lệ!");
            }

        } while(!ValidateUtils.isValidPhone(phone));

        return phone;
    }

    public void showDeleteMenu() {
        System.out.println("===== Xóa danh bạ =====");
        while (true) {
            System.out.print("Nhập số điện thoại cần xóa: ");
            String phone = Input.inputString();

            if (phone.isEmpty()) {
                return;
            }

            Contact contact = contactManager.findByPhone(phone);

            if (contact == null) {
                System.out.println("Không tìm được danh bạ với số điện thoại trên.");
                continue;
            }

            System.out.println("Bạn có chắc muốn xóa danh bạ này?");
            System.out.println(contact);
            System.out.print("Nhập Y để xác nhận: ");
            String confirm = Input.inputString();

            if (confirm.equalsIgnoreCase("Y")) {
                contactManager.delete(phone);
                System.out.println("Xóa danh bạ thành công!");
            } else {
                System.out.println("Đã hủy thao tác xóa.");
            }
            return;
        }
    }

    public void showSearchMenu() {
        System.out.println("===== Tìm kiếm danh bạ =====");
        System.out.print("Nhập từ khóa (SĐT hoặc Họ tên): ");
        String keyword = Input.inputString().toLowerCase();
        List<Contact> result = contactManager.search(keyword);
        if(result.isEmpty()){
            System.out.println("Không tìm thấy danh bạ.");
            return;
        }

        System.out.println("Kết quả tìm kiếm:");

        for(Contact c : result){
            System.out.println(c);
        }
    }

    public void showReadFileMenu() {
        System.out.println("===== Đọc danh bạ từ file =====");
        System.out.print("Thao tác này sẽ xóa toàn bộ danh bạ trong bộ nhớ. Tiếp tục? (Y/N): ");
        String confirm = Input.inputString();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Đã hủy thao tác.");
            return;
        }

        List<Contact> contacts = dataManager.getList();

        contactManager.setContacts(contacts);

        System.out.println("Đã tải danh bạ từ file thành công!");
    }

    public void showWriteFileMenu() {

        System.out.println("===== Ghi danh bạ vào file =====");

        System.out.print("Thao tác này sẽ ghi đè file. Tiếp tục? (Y/N): ");
        String confirm = Input.inputString();

        if (!confirm.equalsIgnoreCase("Y")) {
            System.out.println("Đã hủy thao tác.");
            return;
        }
        dataManager.saveData(contactManager.getContacts());
        System.out.println("Đã lưu danh bạ vào file thành công!");
    }
}
