public class Student implements Comparable<Student> {
    private String name;
    private float gpa;

    public Student(String name, float gpa) {
        this.name = name;
        this.gpa = gpa;
    }
    public Student() { }
    public int compareTo(Student other) {
        return Float.compare(this.gpa, other.gpa);
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return Float.compare(gpa, other.gpa) == 0 && name.equals(other.name);
    }
    public int hashCode() {
        return java.util.Objects.hash(name, gpa);
    }
    public String getName() { return name; }
    public float getGpa() { return gpa; }
    public String toString() {
        return "Student{name='" + name + "', gpa=" + gpa + "}";
    }
}
        