package androidbook.ch03.highrankwidget.contactsbook;

public class GalleryContactsBook {
    private String Name;
    private String PhoneNumber;
    private String Email;

    public GalleryContactsBook (){}

    public GalleryContactsBook (String name, String phoneNumber, String email) {
        super();
        Name = name;
        PhoneNumber = phoneNumber;
        Email = email;
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
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
}
