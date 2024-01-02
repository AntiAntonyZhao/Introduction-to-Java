public class Course {
    public String code;
    public int capacity;
    public SLinkedList<Student>[] studentTable;
    public int size;
    public SLinkedList<Student> waitlist;


    public Course(String code) {
        this.code = code;
        this.studentTable = new SLinkedList[10];
        this.size = 0;
        this.waitlist = new SLinkedList<Student>();
        this.capacity = 10;
    }

    public Course(String code, int capacity) {
        this.code = code;
        this.studentTable = new SLinkedList[capacity];
        this.size = 0;
        this.waitlist = new SLinkedList<>();
        this.capacity = capacity;
    }

    public void changeArrayLength(int m) {
        // insert your solution here
    }

    public boolean put(Student s) {
        // insert your solution here and modify the return statement
        return false;
    }

    public Student get(int id) {
        // insert your solution here and modify the return statement
        return null;
    }


    public Student remove(int id) {
        // insert your solution here and modify the return statement
        return null;
    }

    public int getCourseSize() {
        // insert your solution here and modify the return statement
        return -1;
    }


    public int[] getRegisteredIDs() {
        // insert your solution here and modify the return statement
        return null;
    }

    public Student[] getRegisteredStudents() {
        // insert your solution here and modify the return statement
        return null;
    }

    public int[] getWaitlistedIDs() {
        // insert your solution here and modify the return statement
        return null;
    }

    public Student[] getWaitlistedStudents() {
        // insert your solution here and modify the return statement
        return null;
    }

    public String toString() {
        String s = "Course: "+ this.code +"\n";
        s += "--------\n";
        for (int i = 0; i < this.studentTable.length; i++) {
            s += "|"+i+"     |\n";
            s += "|  ------> ";
            SLinkedList<Student> list = this.studentTable[i];
            if (list != null) {
                for (int j = 0; j < list.size(); j++) {
                    Student student = list.get(j);
                    s +=  student.id + ": "+ student.name +" --> ";
                }
            }
            s += "\n--------\n\n";
        }

        return s;
    }
}

