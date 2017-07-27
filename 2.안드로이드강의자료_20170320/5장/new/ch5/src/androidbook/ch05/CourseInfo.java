package androidbook.ch06;

public class CourseInfo {
    private String name;
    private String subject;

    public String getName() {
        return name;
    }
    public String getSubject() {
        return subject;
    }
    public CourseInfo(String name, String subject) {
        super();
        this.name = name;
        this.subject = subject;
    }
    public String toString() {
        return String.format("name=%s, subject=%s", name, subject);
    }
}
