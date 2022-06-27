package hwi.game;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class ChangeGameDesign extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeGameDesign frame = new ChangeGameDesign();
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
	public ChangeGameDesign() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(220, 70, 950, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(628, 461, 90, 51);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(692, 424, 90, 51);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("New button");
		btnNewButton_1_1.setBounds(688, 371, 90, 51);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_1_1 = new JButton("New button");
		btnNewButton_1_1_1.setBounds(678, 317, 90, 51);
		contentPane.add(btnNewButton_1_1_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(514, 474, 90, 51);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("New button");
		btnNewButton_2_1.setBounds(363, 474, 90, 51);
		contentPane.add(btnNewButton_2_1);
		
		JButton btnNewButton_2_1_1 = new JButton("New button");
		btnNewButton_2_1_1.setBounds(678, 230, 90, 51);
		contentPane.add(btnNewButton_2_1_1);
		
		JButton btnNewButton_2_1_1_1 = new JButton("New button");
		btnNewButton_2_1_1_1.setBounds(692, 173, 90, 51);
		contentPane.add(btnNewButton_2_1_1_1);
		
		JButton btnNewButton_2_1_1_1_1 = new JButton("New button");
		btnNewButton_2_1_1_1_1.setBounds(692, 118, 100, 51);
		contentPane.add(btnNewButton_2_1_1_1_1);
		
		JButton btnNewButton_2_1_2 = new JButton("New button");
		btnNewButton_2_1_2.setBounds(154, 118, 100, 56);
		contentPane.add(btnNewButton_2_1_2);
		
		JButton btnNewButton_2_1_2_1 = new JButton("New button");
		btnNewButton_2_1_2_1.setBounds(164, 175, 100, 56);
		contentPane.add(btnNewButton_2_1_2_1);
		
		JButton btnNewButton_2_1_2_2 = new JButton("New button");
		btnNewButton_2_1_2_2.setBounds(164, 230, 100, 56);
		contentPane.add(btnNewButton_2_1_2_2);
		
		JButton btnNewButton_2_1_2_3 = new JButton("New button");
		btnNewButton_2_1_2_3.setBounds(164, 314, 100, 56);
		contentPane.add(btnNewButton_2_1_2_3);
		
		JButton btnNewButton_2_1_2_3_1 = new JButton("New button");
		btnNewButton_2_1_2_3_1.setBounds(154, 371, 100, 56);
		contentPane.add(btnNewButton_2_1_2_3_1);
		
		JButton btnNewButton_2_1_2_3_1_1 = new JButton("New button");
		btnNewButton_2_1_2_3_1_1.setBounds(154, 409, 100, 56);
		contentPane.add(btnNewButton_2_1_2_3_1_1);
		
		JButton btnNewButton_2_1_2_3_1_1_1 = new JButton("New button");
		btnNewButton_2_1_2_3_1_1_1.setBounds(213, 461, 100, 56);
		contentPane.add(btnNewButton_2_1_2_3_1_1_1);
	}
}
