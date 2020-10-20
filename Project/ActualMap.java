import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class ActualMap extends JFrame {
	
	private int h;
	private int w;
	
	private DeliverRobot[][] map;
	//l

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	

	public ActualMap(int h, int w) {
		this.h = h;
		this.w = w;
		map = new DeliverRobot[h][w];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnCreateRobot = new JButton("CREATE ROBOT");
		btnCreateRobot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RobotCreation rc = new RobotCreation(h, w, map);
				rc.setVisible(true);
			}
		});
		btnCreateRobot.setBounds(275, 11, 149, 23);
		getContentPane().add(btnCreateRobot);
		
	}
	
	

}
