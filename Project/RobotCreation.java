import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class RobotCreation extends JFrame {

	private JPanel contentPane;
	private JTextField southField;
	private JTextField eastField;
	private JTextField nameField;
	private DeliverRobot dr;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public RobotCreation(int h, int w, ArrayList<DeliverRobot> robots, JPanel j, JComboBox<String> combox) {
		setTitle("Create a Robot");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		southField = new JTextField();
		southField.setBounds(46, 74, 96, 20);
		contentPane.add(southField);
		southField.setColumns(10);
		
		eastField = new JTextField();
		eastField.setBounds(46, 139, 96, 20);
		contentPane.add(eastField);
		eastField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(46, 193, 96, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		JRadioButton Power = new JRadioButton("On");
		Power.setBounds(46, 230, 109, 23);
		contentPane.add(Power);
		
		JTextArea promptText = new JTextArea();
		promptText.setEditable(false);
		promptText.setBackground(UIManager.getColor("Button.background"));
		promptText.setText("Please specify the starting information for the \r\nrobot you want to create.");
		promptText.setBounds(10, 11, 414, 52);
		contentPane.add(promptText);
		
		JTextArea unitsSouthText = new JTextArea();
		unitsSouthText.setBackground(UIManager.getColor("Button.background"));
		unitsSouthText.setEditable(false);
		unitsSouthText.setText("units south of \r\nnorthmost");
		unitsSouthText.setBounds(152, 72, 116, 52);
		contentPane.add(unitsSouthText);
		
		JTextArea unitsEastText = new JTextArea();
		unitsEastText.setBackground(UIManager.getColor("Button.background"));
		unitsEastText.setEditable(false);
		unitsEastText.setText("units east of\r\nwestmost");
		unitsEastText.setBounds(152, 137, 116, 49);
		contentPane.add(unitsEastText);
		
		JTextArea botName = new JTextArea();
		botName.setBackground(UIManager.getColor("Button.background"));
		botName.setEditable(false);
		botName.setText("Name of Robot");
		botName.setBounds(170, 72, 116, 49);
		contentPane.add(botName);
		
		JTextArea errMsg = new JTextArea();
		errMsg.setForeground(Color.RED);
		errMsg.setBackground(UIManager.getColor("Button.background"));
		errMsg.setEditable(false);
		errMsg.setBounds(152, 192, 272, 58);
		contentPane.add(errMsg);
		
		
		
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ps = Integer.parseInt(southField.getText());
					int pe = Integer.parseInt(eastField.getText());
					String name = nameField.getText();
			
					if (ps < 0 || ps >= h || pe < 0 || pe >= w) {
						errMsg.setText("Invalid input. Position is out of bounds.");
					}
					else {
						RobotFactory rf = RobotFactory.getFactory();
						if (Power.isSelected()) {
							dr = rf.makeRobot(ps, pe, true, name);
							
						}
						else {
							dr = rf.makeRobot(ps, pe, false, name);
						}
						
						boolean taken = false;
						//checks position
						for(DeliverRobot var : robots) {
							if(var.getE() == w && var.getS() == h) {
								taken = true;
							}
						}
						
						
						if(taken) {
							errMsg.setText("Invalid input. A robot is already located at that\n position.");
						}
						else {
							robots.add(dr);
							combox.addItem(name);
							j.repaint();
							dispose();
						}
					}
				}
				catch (NumberFormatException exception) {
					errMsg.setText("Invalid input. Please enter basic integers with no spaces.");
				}
			}
		});
		btnCreate.setBounds(312, 73, 89, 23);
		contentPane.add(btnCreate);
	}
	
}
