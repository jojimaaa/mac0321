package ex01.A;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestesFatorial {
    @Test
    public void testFatorial(){
        assertEquals(120, Fatorial.CalculaFatorial(5));
    }

    @Test
    public void testFatorialZero(){
        assertEquals(1, Fatorial.CalculaFatorial(0));
    }

    @Test
    public void testFatorialNegativo(){
        assertEquals(-1, Fatorial.CalculaFatorial(-5));
    }
}
