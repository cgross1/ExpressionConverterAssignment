package Project1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ConverterGUI implements ActionListener {
	
	//global variables so actionPerformed can see ?
	private JButton buttonPreToPost;
	private JButton buttonPostToPre;
	private JTextField inputField;
	private JTextField outputField;
	
	//gui constructor
	public ConverterGUI() {
		
		JFrame frame = new JFrame();
		
		buttonPreToPost = new JButton("Prefix to Postfix");
		buttonPostToPre = new JButton("Postfix to Prefix");
	
		buttonPreToPost.addActionListener(this);
		buttonPostToPre.addActionListener(this);
		
		JLabel inputLabel = new JLabel("Enter Expression");
		JLabel resultLabel = new JLabel("Result");
		
		inputField = new JTextField(30);
		outputField = new JTextField(30);
		outputField.setEditable(false);
		
		/*
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel overallPanel = new JPanel();
		
		overallPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new flowLayout(0,2));
		panel1.add(inputLabel);
		panel1.add(inputField);
		panel2.add(buttonPreToPost);
		panel2.add(buttonPostToPre);
		panel3.add(resultLabel);
		panel3.add(outputField);
		
		overallPanel.add(panel1);
		overallPanel.add(panel2);
		overallPanel.add(panel3);
		

		frame.add(overallPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Expression Converter");
		frame.pack();
		frame.setVisible(true);
		*/
		
		
		JPanel panel = new JPanel();
		
		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new GridLayout(0,1));
		panel.add(inputLabel);
		panel.add(inputField);
		panel.add(buttonPreToPost);
		panel.add(buttonPostToPre);
		panel.add(resultLabel);
		panel.add(outputField);
		

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Expression Converter");
		frame.pack();
		frame.setVisible(true);
		
		
		/* 2 column grid
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		JPanel overallPanel = new JPanel();
		
		overallPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		panel.setLayout(new flowLayout(0,2));
		panel1.add(inputLabel);
		panel1.add(inputField);
		panel2.add(buttonPreToPost);
		panel2.add(buttonPostToPre);
		panel3.add(resultLabel);
		panel3.add(outputField);
		
		overallPanel.add(panel1);
		overallPanel.add(panel2);
		overallPanel.add(panel3);
		

		frame.add(overallPanel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Expression Converter");
		frame.pack();
		frame.setVisible(true);
		*/
	}

	public static void main(String[] args) {
		new ConverterGUI();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==buttonPreToPost) {
			
			//catch custom exception here?
			String result = null;
			try {
				result = ExpressionConversion.preToPost(inputField.getText());
			} catch (SyntaxError e1) {
				//e1.printStackTrace();
				displayErrorMessage();
			} catch (EmptyStackException e2) {
				System.out.println("empty stack exception");
				displayErrorMessage();
			}
			
			outputField.setText(result);
		}
		
		else if (e.getSource()==buttonPostToPre) {
			
			String result = null;
			try {
				result = ExpressionConversion.postToPre(inputField.getText());
			} catch (SyntaxError e1) {
				//e1.printStackTrace();
				displayErrorMessage();
			} catch (EmptyStackException e2) {
				System.out.println("empty stack exception");
				displayErrorMessage();
			}
			
			outputField.setText(result);
		}
		
	}
	
	public void displayErrorMessage() {
		JFrame errorFrame = new JFrame();
		JOptionPane.showMessageDialog(errorFrame,"Error: Invalid Syntax.","ALERT",JOptionPane.WARNING_MESSAGE);
	}

}
