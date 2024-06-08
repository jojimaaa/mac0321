package ex02;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

/*
 * Não há teste para o método readFromCSV pois essa função é chamada no setup,
 * nenhum teste ocorreria caso ela não estivesse funcionando corretamente.
 */

public class TestCSVImpl {

    private StudentDaoImplCSV simulation;

    @BeforeEach
    public void setup() {
        simulation = new StudentDaoImplCSV();
        simulation.readFromCSV("src\\ex02\\lista.csv");
    }

    @Test
    public void testGetAllStudents() {
        ArrayList<Student> expected = new ArrayList<Student>();
        expected.add(new Student("jose", 1));
        expected.add(new Student("carlos", 2));
        expected.add(new Student("pedro", 3));
        expected.add(new Student("artur", 4));
        expected.add(new Student("arthur", 5));
        assertTrue(simulation.equals(expected));
    }

    @Test
    public void testGetStudent() {
        Student expected = new Student("jose", 1);
        assertEquals(expected.getName(), simulation.getStudent(1).getName());
        assertEquals(expected.getRollNo(), simulation.getStudent(1).getRollNo());
    }

    @Test
    public void testUpdateStudent() {
        Student expected = new Student("jesus", 1);
        simulation.updateStudent(new Student("jesus", 1));
        assertEquals(expected.getName(), simulation.getStudent(1).getName());
        assertEquals(expected.getRollNo(), simulation.getStudent(1).getRollNo());
    }

    @Test
    public void testDeleteStudent() {
        simulation.deleteStudent(new Student("jose", 1));
        ArrayList<Student> expected = new ArrayList<Student>();
        expected.add(new Student("carlos", 2));
        expected.add(new Student("pedro", 3));
        expected.add(new Student("artur", 4));
        expected.add(new Student("arthur", 5));
        assertTrue(simulation.equals(expected));
    }

    @Test
    public void testWriteToCSV() {
        simulation.writeToCSV("src\\ex02\\outputList.csv");
        StudentDaoImplCSV expected = new StudentDaoImplCSV();
        StudentDaoImplCSV output = new StudentDaoImplCSV();

        output.readFromCSV("src\\ex02\\outputList.csv");
        expected.readFromCSV("src\\ex02\\expected.csv");
        output.getClass().equals(expected.getAllStudents());
    }
}
