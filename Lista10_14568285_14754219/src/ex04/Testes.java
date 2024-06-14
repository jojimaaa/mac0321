package ex04;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Testes {

    @Test
    public void testValidarValidade() {
        assertTrue(CartaoUtilRefactored.validarValidade("12/2024"));
        assertFalse(CartaoUtilRefactored.validarValidade("12/2019"));
        assertFalse(CartaoUtilRefactored.validarValidade("12/2020"));
    }

    @Test
    public void testFormatNumber() {
        assertEquals("1234567890", CartaoUtilRefactored.formatNumber("1234-5678-90"));
        assertEquals("1234567890", CartaoUtilRefactored.formatNumber("1234 5678 90"));
        assertEquals("1234567890", CartaoUtilRefactored.formatNumber("1234.5678.90"));
    }

    @Test
    public void testValidarNumero() {
        assertTrue(CartaoUtilRefactored.validarNumero("4556737586899855"));
        assertFalse(CartaoUtilRefactored.validarNumero("4556737586899856"));
    }

    @Test
    public void testValidar() {
        CartaoUtilRefactored cartaoUtil = new CartaoUtilRefactored();
        assertEquals(CartaoUtilRefactored.CARTAO_OK, cartaoUtil.validar(CartaoUtilRefactored.VISA, "4556737586899855", "12/2024"));
        assertEquals(CartaoUtilRefactored.CARTAO_ERRO, cartaoUtil.validar(CartaoUtilRefactored.VISA, "4556737586899856", "12/2024"));
        assertEquals(CartaoUtilRefactored.CARTAO_ERRO, cartaoUtil.validar(CartaoUtilRefactored.VISA, "4556737586899855", "12/2019"));
        assertEquals(CartaoUtilRefactored.CARTAO_ERRO, cartaoUtil.validar(CartaoUtilRefactored.VISA, "4556737586899855", "12/2020"));
    }
}
