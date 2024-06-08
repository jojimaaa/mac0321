package ex02;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImplCSV implements StudentDao {

    ArrayList<Student> students;

    public StudentDaoImplCSV() {
        this.students = new ArrayList<Student>();
    }


    @SuppressWarnings("resource")
    public void readFromCSV(String address) throws IllegalArgumentException {
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(address));
            while ((line = br.readLine()) != null) {
                if(line.startsWith("#")) continue;
                String[] data = line.split(",");
                for (Student s : students) {
                    if(s.getRollNo() == Integer.parseInt(data[1])) {
                        throw new IllegalArgumentException("Student with rollNo " + data[1] + " already exists.");
                    }
                    if(s.getName().equals(data[0])) {
                        throw new IllegalArgumentException("Student with name " + data[0] + " already exists.");
                    }
                }
                students.add(new Student(data[0], Integer.parseInt(data[1])));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Student> getAllStudents() {
        return this.students;
    }

    @Override
    public Student getStudent(int rollNo) {
        if(rollNo <= 0) throw new IllegalArgumentException("Invalid rollNo.");
        return this.students.get(rollNo-1);
    }

    @Override
    public void updateStudent(Student student) {
        for (Student t : students) {
            if(t.getRollNo() == student.getRollNo()) {
                t.setName(student.getName());
                return;
            }
            if(t.getName().equals(student.getName())) {
                t.setRollNo(student.getRollNo());
                return;
            }
        }
    }

    @Override
    public void deleteStudent(Student student) {
        for (Student t : students) {
            if(t.getRollNo() == student.getRollNo() && t.getName().equals(student.getName())) {
                students.remove(t);
                return;
            }
        }
    }

    public void writeToCSV(String address) {
            try {
                PrintWriter writer = new PrintWriter("src\\ex02\\outputList.csv", "UTF-8");
                writer.println("#nome,rollno");
                for (Student student : this.students) {
                    writer.println(student.getName() + "," + student.getRollNo());
                }
                writer.close();
            } catch (FileNotFoundException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
    }


    public boolean equals(ArrayList<Student> expected){
        if(expected.size() != this.students.size()) return false;
        for (int i = 0; i < expected.size(); i++) {
            if(!expected.get(i).getName().equals(this.students.get(i).getName())) return false;
            if(expected.get(i).getRollNo() != this.students.get(i).getRollNo()) return false;
        }
        return true;
    }
}
