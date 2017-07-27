package androidbook.ch03.highrankwidget.contactsbook;

public class ListViewContactsBook {
    private String Name;
    private String PhoneNumber;

    public ListViewContactsBook(){}

    public ListViewContactsBook(String name, String phoneNumber) {
        Name = name;
        PhoneNumber = phoneNumber;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
