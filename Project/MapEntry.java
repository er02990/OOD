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
		
		JTextArea errMsg = new JTextArea();
		errMsg.setEditable(false);
		errMsg.setForeground(Color.RED);
		errMsg.setBackground(UIManager.getColor("Button.background"));
		errMsg.setBounds(10, 225, 414, 53);
		frmCreateAMap.getContentPane().add(errMsg);
		
		JButton btnGSU = new JButton("CREATE");
		btnGSU.addActionListener(new ActionListener() {
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
