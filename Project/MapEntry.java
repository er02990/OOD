import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MapEntry {

	private JFrame frmCreateAMap;
	private JTextField heightField;
	private JTextField widthField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MapEntry window = new MapEntry();
					window.frmCreateAMap.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MapEntry() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateAMap = new JFrame();
		frmCreateAMap.setTitle("Create a Map");
		frmCreateAMap.setBounds(100, 100, 450, 300);
		frmCreateAMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateAMap.getContentPane().setLayout(null);
		
		JTextArea txtPrompt = new JTextArea();
		txtPrompt.setEditable(false);
		txtPrompt.setBackground(UIManager.getColor("Button.background"));
		txtPrompt.setText("Please enter the dimensions for \r\nthe map you would like to create.");
		txtPrompt.setBounds(10, 11, 269, 40);
		frmCreateAMap.getContentPane().add(txtPrompt);
		
		heightField = new JTextField();
		heightField.setBounds(95, 80, 96, 20);
		frmCreateAMap.getContentPane().add(heightField);
		heightField.setColumns(10);
		
		widthField = new JTextField();
		widthField.setBounds(95, 140, 96, 20);
		frmCreateAMap.getContentPane().add(widthField);
		widthField.setColumns(10);
		
		JTextArea txtrUnitsOfHeight = new JTextArea();
		txtrUnitsOfHeight.setBackground(UIManager.getColor("Button.background"));
		txtrUnitsOfHeight.setText("units of height");
		txtrUnitsOfHeight.setBounds(201, 78, 125, 22);
		frmCreateAMap.getContentPane().add(txtrUnitsOfHeight);
		
		JTextArea txtrUnitsOfWeight = new JTextArea();
		txtrUnitsOfWeight.setBackground(UIManager.getColor("Button.background"));
		txtrUnitsOfWeight.setText("units of width");
		txtrUnitsOfWeight.setBounds(201, 138, 125, 22);
		frmCreateAMap.getContentPane().add(txtrUnitsOfWeight);
		
		JTextArea errMsg = new JTextArea();
		errMsg.setEditable(false);
		errMsg.setForeground(Color.RED);
		errMsg.setBackground(UIManager.getColor("Button.background"));
		errMsg.setBounds(10, 225, 414, 53);
		frmCreateAMap.getContentPane().add(errMsg);
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int h = Integer.parseInt(heightField.getText());
					int w = Integer.parseInt(widthField.getText());
					if (h > 100 || h < 1 || w > 100 || w < 1) {
						errMsg.setText("Invalid input. Height and width can only be from 1 to 100 inclusive.");
					}
					else {
						frmCreateAMap.dispose();
						ActualMap am = new ActualMap(h,w);
						am.setVisible(true);
					}
					}
					catch (NumberFormatException exception) {
						errMsg.setText("Invalid input. Please enter basic integers with no spaces.");
					}
			}
		});
		btnCreate.setBounds(170, 171, 89, 23);
		frmCreateAMap.getContentPane().add(btnCreate);
		
		JButton btnGSU = new JButton("GSU");
		btnGSU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmCreateAMap.dispose();
				GSUMap am = new GSUMap();
				am.setVisible(true);
					
					
					
			}
		});
		btnGSU.setBounds(170, 200, 89, 23);
		frmCreateAMap.getContentPane().add(btnGSU);
		
		
	}
	
	

}
