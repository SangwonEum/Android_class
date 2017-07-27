package androidbook.ch03.highrankwidget.contactsbook;

public class GridViewContactsBook {
    private String Name;
    private String PhoneNumber;

    public GridViewContactsBook (){}

    public GridViewContactsBook (String name, String phoneNumber) {
        super();
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
