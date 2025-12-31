package tests.model;

public class StudentJSON {

    private String name;
    private Integer age;
    private boolean isStudent;
    private StudentJSONInner courses;

    public StudentJSONInner getCourses() {
        return courses;
    }

    public void setCourses(StudentJSONInner courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
    }
}
