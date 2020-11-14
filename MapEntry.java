import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//this class represents to first page that is opened when the application is started
//it is a simple window with a button that closes this window and opens the map window

public class MapEntry {

	private JFrame frmCreateAMap;

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
		
		JButton btnGSU = new JButton("CREATE");
		btnGSU.addActionListener(new ActionListener() {
			//this is the method that is called when the CREATE button is pressed
			//it closes this window and opens a map window
			public void actionPerformed(ActionEvent e) {
				frmCreateAMap.dispose();
				GSUMap am = new GSUMap();
				am.setVisible(true);
					
					
			}
		});
		btnGSU.setBounds(170, 106, 89, 23);
		frmCreateAMap.getContentPane().add(btnGSU);
		
		
	}
	
	

}
