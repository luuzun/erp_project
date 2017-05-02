package erp_project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import erp_project.init.Config;
import erp_project.init.InitSettingService;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainApp extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnInit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainApp frame = new MainApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnInit = new JButton("초기화");
		btnInit.addActionListener(this);
		contentPane.add(btnInit);
		
		JButton btnBackUp = new JButton("백업");
		contentPane.add(btnBackUp);
		
		JButton btnRestore = new JButton("복원");
		contentPane.add(btnRestore);
		System.out.println(Config.DATAFILES_PATH);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnInit) {
			actionPerformedBtnInit(e);
		}
	}
	protected void actionPerformedBtnInit(ActionEvent e) {
		InitSettingService initSettingService = new InitSettingService();
		initSettingService.initSet();
	}
}
