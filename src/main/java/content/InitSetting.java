package content;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import setting.InitSettingService;

public class InitSetting extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InitSetting frame = new InitSetting();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public InitSetting() {
		setTitle("Erp_Database Setting");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 130);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(30, 5, 30, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 10, 10));
		
		JPanel pInit = new JPanel();
		contentPane.add(pInit);
		pInit.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnInit = new JButton("초기화");
		btnInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedBtnInit(arg0);
			}
		});
		pInit.add(btnInit);
		
		JPanel pBackup = new JPanel();
		contentPane.add(pBackup);
		pBackup.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnBackup = new JButton("백업");
		btnBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnBackup(e);
			}
		});
		pBackup.add(btnBackup);
		
		JPanel pRestore = new JPanel();
		contentPane.add(pRestore);
		pRestore.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnRestore = new JButton("복원");
		btnRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnRestore(e);
			}
		});
		pRestore.add(btnRestore);
	}

	protected void actionPerformedBtnInit(ActionEvent arg0) {
		InitSettingService init = InitSettingService.getInstance();
		init.initSetting();
	}
	protected void actionPerformedBtnBackup(ActionEvent e) {
		InitSettingService init = InitSettingService.getInstance();
		init.backUp();
	}
	protected void actionPerformedBtnRestore(ActionEvent e) {
		InitSettingService init = InitSettingService.getInstance();
		init.restore();
	}
}