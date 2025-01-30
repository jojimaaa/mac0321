package ex01;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FatorialView {
    private JFrame frame;
    private JTextField inputField;
    private JButton calcularButton;
    private JLabel resultadoLabel;
    private FatorialController controller;

    public FatorialView(FatorialController controller) {
        this.controller = controller;
        frame = new JFrame("Calculadora de Fatorial");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(null);

        JLabel inputLabel = new JLabel("Entre com um número:");
        inputLabel.setBounds(10, 10, 150, 25);
        frame.add(inputLabel);

        inputField = new JTextField();
        inputField.setBounds(160, 10, 100, 25);
        frame.add(inputField);

        calcularButton = new JButton("Calcular");
        calcularButton.setBounds(10, 50, 250, 25);
        frame.add(calcularButton);

        resultadoLabel = new JLabel("Resultado:");
        resultadoLabel.setBounds(10, 90, 250, 25);
        frame.add(resultadoLabel);

        
        ActionListener actionListener = new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		calcularFatorial();
        	}
        };
        
        calcularButton.addActionListener(actionListener);
    }

    public JTextField getTextField() {
    	return this.inputField;
    }
    
    public JButton getButton() {
    	return this.calcularButton;
    }
    
    public JLabel getLabel() {
    	return this.resultadoLabel;
    }
    
    public JFrame getFrame() {
    	return this.frame;
    }
    
    public void setVisibility() {
    	frame.setVisible(true);
    }
    
    private void calcularFatorial() {
        try {
            int numero = Integer.parseInt(inputField.getText());
            int resultado = controller.calcularFatorial(numero);
            resultadoLabel.setText("Resultado: " + resultado);
        } catch (NumberFormatException ex) {
            resultadoLabel.setText("Erro: Entrada em formato inválido.");
        } catch (IllegalArgumentException ex) {
            resultadoLabel.setText("Erro: " + ex.getMessage());
        }
    }


}
