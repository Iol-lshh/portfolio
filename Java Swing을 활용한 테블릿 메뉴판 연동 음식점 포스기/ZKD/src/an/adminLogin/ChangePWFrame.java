package an.adminLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import an.adminMain.AdminMainView;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class ChangePWFrame extends JDialog {

   private JPanel contentPane;
   JPasswordField password;
   AdminLoginControl control;
   JLabel result;
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {

            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   public ChangePWFrame(AdminMainView adminView,AdminLoginControl control) {
	   super(adminView,"관리자 비밀번호 재설정",true);
      this.control = control;
      setBounds(410, 40, 600, 100);
      setTitle("관리자 비밀번호 재설정");
      setResizable(false);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JPanel panel = new JPanel();
      contentPane.add(panel, BorderLayout.CENTER);
      panel.setLayout(new BorderLayout(0, 0));

      JPanel panel_2 = new JPanel();
      panel.add(panel_2, BorderLayout.CENTER);
      panel_2.setLayout(new BorderLayout(0, 0));

      JLabel lblNewLabel = new JLabel("비밀번호 변경 :");
      panel_2.add(lblNewLabel, BorderLayout.WEST);

      password = new JPasswordField(10);
      panel_2.add(password, BorderLayout.CENTER);
      
      JPanel panel_1 = new JPanel();
      panel.add(panel_1, BorderLayout.SOUTH);
      panel_1.setLayout(new GridLayout(0, 1, 2, 0));
      
      JLabel pW_regex = new JLabel("숫자 6자 이하로 입력하세요.");
      pW_regex.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(pW_regex);
      
      JButton btnNewButton = new JButton("변경");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) {

            String strPw = String.valueOf(password.getPassword());
            
            AdminLogDTO dto = new AdminLogDAO().detail(strPw);
            
            if(Pattern.matches("[0-9]{1,6}", strPw)) {
               System.out.println("배아파");
               control.changePW(strPw);
               dispose();
            }else if(!Pattern.matches("[0-9]{1,6}", strPw)) {
               System.out.println("ㅠㅠ");
               result.setText("형식에 맞지않습니다.");
               result.setForeground(Color.RED);
            }
            
            System.out.println(strPw);
            
         }
         
      });
      panel_2.add(btnNewButton, BorderLayout.EAST);
      
      result = new JLabel(" ");
      result.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(result);
      
      setVisible(true);
      
   }

}