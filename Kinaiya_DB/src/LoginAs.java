import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginAs {

	private JFrame loginas;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginAs window = new LoginAs();
					window.loginas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginAs() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		loginas = new JFrame();
		loginas.setBounds(100, 100, 1140, 624);
		loginas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginas.getContentPane().setLayout(null);
		loginas.setVisible(true);
		
		JButton btn_loginasuser = new JButton("");
		btn_loginasuser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginas.dispose();
				new LoginUser();
			}
		});
		
		loginas.setVisible(true);
		Image lguser = new ImageIcon(this.getClass().getResource("/LOGINUSER.png")).getImage();
		btn_loginasuser.setIcon(new ImageIcon(lguser));
		btn_loginasuser.setBounds(448, 360, 219, 51);
		loginas.getContentPane().add(btn_loginasuser);
		
	    
		JButton btn_loginasadmin = new JButton("");
		btn_loginasadmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginas.dispose();
				new LoginAdmin();
			}
		});
		Image lgadmin = new ImageIcon(this.getClass().getResource("/LoginAD.png")).getImage();
		btn_loginasadmin.setIcon(new ImageIcon(lgadmin));
		btn_loginasadmin.setBounds(448, 437, 219, 51);
		loginas.getContentPane().add(btn_loginasadmin);
		
		JLabel lblbg = new JLabel("");
		Image lgbg = new ImageIcon(this.getClass().getResource("/BG1.png")).getImage();
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setIcon(new ImageIcon(lgbg));
		lblbg.setBounds(0, 0, 1140, 624);
		loginas.getContentPane().add(lblbg);
		
		
		
	}
}
