package ex02;
import ex01.*;
import static org.junit.Assert.*;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.swing.*;

import org.junit.jupiter.api.*;


/*
 * 
 * Testes de unidade, como o próprio nome da diz, testam cada uma das pequenas unidades do código,
 * separando testes específicos para cada método e classe. São testes mais isolados e focados.
 * 
 * Testes de aceitação simulam ação do usuário, testando a interação do programa com o usuário, e não o funcionamento
 * de métodos ou classes isolados. Focam mais em testar o funcionamento do programa como um todo e simular a
 * interação usuário-programa.
 * 
 * */

public class TesteDeAceitacaoFatorialApp{
	
	private FatorialController controller;
	private FatorialView view;
	private JTextField textField;
	private JButton button;
	private JLabel result;
	private JFrame frame;
	private Robot robot;
	
	
	@BeforeEach
	public void setup() throws Exception {
		controller = new FatorialController();
		view = new FatorialView(controller);
		textField = view.getTextField();
		button = view.getButton();
		result = view.getLabel();
		frame = view.getFrame();
		SwingUtilities.invokeLater(() -> view.setVisibility());
		robot = new Robot();
		robot.setAutoDelay(100);
	}
	
	@Test
	public void TesteEntradaValida() throws InterruptedException {
		robot.mouseMove(frame.getX()+180, frame.getY()+50);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		
        robot.keyPress(KeyEvent.VK_6);
        robot.keyRelease(KeyEvent.VK_6);
        
        robot.mouseMove(frame.getX() + 100, frame.getY() + 100);
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		
        Thread.sleep(100);
        
		assertEquals("Resultado: 720", result.getText());
	}
	
	@Test
	public void TesteEntradaNegativa() throws InterruptedException {
		robot.mouseMove(frame.getX()+180, frame.getY()+50);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		
		robot.keyPress(KeyEvent.VK_MINUS);
		robot.keyRelease(KeyEvent.VK_MINUS);
        robot.keyPress(KeyEvent.VK_6);
        robot.keyRelease(KeyEvent.VK_6);
        
        robot.mouseMove(frame.getX() + 100, frame.getY() + 100);
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		
        Thread.sleep(100);
		assertEquals("Erro: Número não pode ser negativo.", result.getText());
	}
	
	@Test
	public void TesteOverflow() throws InterruptedException {
		robot.mouseMove(frame.getX()+180, frame.getY()+50);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		
		robot.keyPress(KeyEvent.VK_6);
        robot.keyRelease(KeyEvent.VK_6);
        robot.keyPress(KeyEvent.VK_6);
        robot.keyRelease(KeyEvent.VK_6);
        
        robot.mouseMove(frame.getX() + 100, frame.getY() + 100);
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		
        Thread.sleep(100);
		assertEquals("Erro: Excedeu o limite de Integer (32bits)", result.getText());
	}
	
	@Test
	public void TesteEntradaInvalida() throws InterruptedException {
		robot.mouseMove(frame.getX()+180, frame.getY()+50);
		robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		
		robot.keyPress(KeyEvent.VK_O);
        robot.keyRelease(KeyEvent.VK_O);
        robot.keyPress(KeyEvent.VK_I);
        robot.keyRelease(KeyEvent.VK_I);
        
        robot.mouseMove(frame.getX() + 100, frame.getY() + 100);
        robot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
		
        Thread.sleep(100);
		assertEquals("Erro: Entrada em formato inválido.", result.getText());
	}
	
}