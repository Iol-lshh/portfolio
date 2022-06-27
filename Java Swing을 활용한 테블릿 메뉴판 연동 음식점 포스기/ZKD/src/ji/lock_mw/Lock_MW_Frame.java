package ji.lock_mw;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

//import an.tableBoard.an_tableBoardSexRateControl;
//import an.tableBoard.an_tableBoardView;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class Lock_MW_Frame extends JFrame {

	Lock_MW_Controller lockMWController;

	private JPanel contentPane;
	String tableNum;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Lock_MW_Frame frame = new Lock_MW_Frame();
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
	public Lock_MW_Frame() {
		init();
	}
	
	public Lock_MW_Frame(Lock_MW_Controller lockMWController,String tableNum) {
		this.lockMWController = lockMWController;
		this.tableNum = tableNum;
		init();
		setVisible(true);
	}
	void init() {
		setBounds(550, 300, 274, 162);
		setTitle("성비등록");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel lockPan = new JPanel();
		contentPane.add(lockPan, BorderLayout.NORTH);
		lockPan.setLayout(new BorderLayout(0, 0));



//		JButton lockBnt = new JButton("락 해제");
//		lockPan.add(lockBnt, BorderLayout.SOUTH);
//		
//		lockBnt.addActionListener(new ActionListener() {		
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				//버튼을 누르면 텍스트를 바꾸자
//			}
//		});

		JPanel lock_Gender_Pan = new JPanel();
		contentPane.add(lock_Gender_Pan, BorderLayout.SOUTH);

		JPanel genderPan = new JPanel();
		contentPane.add(genderPan, BorderLayout.CENTER);
		genderPan.setLayout(new GridLayout(0, 4, 0, 0));

		
		JLabel manSpinnerLab = new JLabel("남");
		manSpinnerLab.setHorizontalAlignment(SwingConstants.CENTER);
		genderPan.add(manSpinnerLab);
		

		SpinnerModel manModel = new SpinnerNumberModel(0, 0, 10, 1); // 초기값, 최소값, 최대값, 증가값

		JSpinner manSpinner = new JSpinner(manModel);
		genderPan.add(manSpinner);
//		System.out.println(manSpinner.getModel().getValue());
		
		JLabel womanSpinnerLab = new JLabel("여");
		womanSpinnerLab.setHorizontalAlignment(SwingConstants.CENTER);
		genderPan.add(womanSpinnerLab);
		

		SpinnerModel womanModel = new SpinnerNumberModel(0, 0, 10, 1);

		JSpinner womanSpinner = new JSpinner(womanModel);
		genderPan.add(womanSpinner);
		
		JButton lock_Gender_Chk_Bnt = new JButton("확인");
		lock_Gender_Pan.add(lock_Gender_Chk_Bnt);
		
		switch (tableNum) {
		
		case "테이블_1":
			if(!lockMWController.boardControl.boardView.manCnt1.getText().equals("")) {
//				System.out.println(lockMWController.boardControl.boardView.lblNewLabel.getText());
				manModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.manCnt1.getText()));
				womanModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.womanCnt1.getText()));
			}
			break;
		
		case "테이블_2":
//			System.out.println(boardView.lblNewLabel_4);
			if(!lockMWController.boardControl.boardView.manCnt2.getText().equals("")) {
			manModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.manCnt2.getText()));
			womanModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.womanCnt2.getText()));
			}
			break;
			
		case "테이블_3":
			if(!lockMWController.boardControl.boardView.manCnt3.getText().equals("")) {
			manModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.manCnt3.getText()));
			womanModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.womanCnt3.getText()));
			}
			break;

		case "테이블_4":
			if(!lockMWController.boardControl.boardView.manCnt4.getText().equals("")) {
			manModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.manCnt4.getText()));
			womanModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.womanCnt4.getText()));
			}
			break;
		
		case "테이블_5":
			if(!lockMWController.boardControl.boardView.manCnt5.getText().equals("")) {
			manModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.manCnt5.getText()));
			womanModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.womanCnt5.getText()));
			}
			break;
			
		case "테이블_6":
			if(!lockMWController.boardControl.boardView.manCnt6.getText().equals("")) {
			manModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.manCnt6.getText()));
			womanModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.womanCnt6.getText()));
			}
			break;
			
		case "테이블_7":
			if(!lockMWController.boardControl.boardView.manCnt7.getText().equals("")) {
			manModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.manCnt7.getText()));
			womanModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.womanCnt7.getText()));
			}
			break;
			
		case "테이블_8":
			if(!lockMWController.boardControl.boardView.manCnt8.getText().equals("")) {
			manModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.manCnt8.getText()));
			womanModel.setValue(Integer.parseInt(lockMWController.boardControl.boardView.womanCnt8.getText()));
			}
			break;
		}
		
		lock_Gender_Chk_Bnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				lockMWController.register(manSpinner.getModel().getValue().toString(),
				womanSpinner.getModel().getValue().toString());
				
				lockMWController.setMW(lockMWController.tableNum,(Integer)manSpinner.getValue(),(Integer)womanSpinner.getValue());
				dispose();//확인버튼 누르면 창 닫기 				
			}
		});
		
	}
}


