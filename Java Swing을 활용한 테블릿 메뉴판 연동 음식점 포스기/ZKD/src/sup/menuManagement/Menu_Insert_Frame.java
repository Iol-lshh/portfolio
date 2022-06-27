package sup.menuManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sup.menu.Menu;
import sup.menu.MenuMainView;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

public class Menu_Insert_Frame extends JDialog {
   File imageF;
   JLabel image_label;
   private JPanel contentPane;
   private JTextField menu_nameF;
   private JTextField menu_priceF;
   private JTextField menu_catagF;
   

   public Menu_Insert_Frame(MenuManagementMain menuGui, MMcontroller control) {
	   super(menuGui,"¸Þ´ºÃß°¡",true);
      setBounds(410, 390, 600, 250);
      System.out.println("dhkdkkkd");
      setResizable(false);
      System.out.println("dhkdkkkd2");
      System.out.println("dhkdkkkd3");
      contentPane = new JPanel();
      System.out.println("dhkdkkkd4");
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      System.out.println("dhkdkkkd5");
      setContentPane(contentPane);
      contentPane.setLayout(new GridLayout(1, 2, 0, 0));
      
      JPanel panel_2 = new JPanel();
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(5, 2, 0, 0));
      
      image_label = new JLabel();
      image_label.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(image_label);
      
      JLabel menu_nameL = new JLabel("\uBA54\uB274 \uC774\uB984");
      menu_nameL.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(menu_nameL);
      
      menu_nameF = new JTextField();
      panel.add(menu_nameF);
      menu_nameF.setColumns(10);
      
      JLabel menu_priceL = new JLabel("\uBA54\uB274 \uAC00\uACA9");
      menu_priceL.setHorizontalAlignment(SwingConstants.CENTER);
      
      panel.add(menu_priceL);
      menu_priceF = new JTextField();
      panel.add(menu_priceF);
      menu_priceF.setColumns(10);
      
      JLabel menu_catagL = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
      menu_catagL.setHorizontalAlignment(SwingConstants.CENTER);
      panel.add(menu_catagL);
      
      JComboBox comboBox = new JComboBox();
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC548\uC8FC", "\uC220"}));
      panel.add(comboBox);
      
      JButton imageloadBtn = new JButton("\uD30C\uC77C \uB85C\uB4DC");
      imageloadBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               imageF = control.fileOpenDlg();
               BufferedImage bf = ImageIO.read(imageF);
               ImageIcon img = new ImageIcon(bf);
               Image changedImg = img.getImage();
               Image img2 = changedImg.getScaledInstance(260, 200, Image.SCALE_SMOOTH);
               image_label.setIcon(new ImageIcon(img2));
            } catch (IOException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      });
      
      JPanel panel_1 = new JPanel();
      panel.add(panel_1);
      panel_1.setLayout(new GridLayout(2, 1, 0, 0));
      
      JLabel err1_1 = new JLabel("");
      err1_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(err1_1);
      
      JLabel err1_2 = new JLabel("");
      err1_2.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(err1_2);
      
      panel.add(imageloadBtn);
      
      JPanel panel_3 = new JPanel();
      panel.add(panel_3);
      panel_3.setLayout(new GridLayout(0, 1, 0, 0));
      
      JLabel err2_1 = new JLabel("");
      err2_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(err2_1);
      JLabel err2_2 = new JLabel("");
      err2_2.setHorizontalAlignment(SwingConstants.CENTER);
      panel_3.add(err2_2);
      
      
      JButton menu_create_btn = new JButton("\uD655\uC778");
      panel.add(menu_create_btn);
      menu_create_btn.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            boolean chk = true;
            if(!Pattern.matches("[¤¡-ÆRA-Za-z0-9]{1,6}", menu_nameF.getText())||(menu_nameF.getText().equals(""))) {
               err1_1.setText("6±ÛÀÚ¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
               err1_1.setForeground(Color.red);
               err1_2.setText("Æ¯¼ö¹®ÀÚ ÀÔ·Â ºÒ°¡");
               err1_2.setForeground(Color.red);
               chk = false;
            }else {
               err1_1.setText("");
               err1_2.setText("");
            }
            if(!Pattern.matches("[0-9]{1,7}", menu_priceF.getText())||(menu_priceF.getText().equals(""))) {
               err2_1.setText("¼ýÀÚ¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
               err2_2.setText("Å«¼ö´Â ÀÔ·Â ºÒ°¡.");
               err2_1.setForeground(Color.red);
               err2_2.setForeground(Color.red);
               chk = false;
            }else {
               err2_1.setText("");
               err2_2.setText("");
            }
            if(imageF == null) {
				JOptionPane.showMessageDialog(Menu_Insert_Frame.this, "»çÁøÀ» Ãß°¡ ÇØÁÖ¼¼¿ä.");
				chk = false;
			}
            for (Menu menu : control.MenuList) {
				if(menu.getMenu_name().equals(menu_nameF.getText())) {
					JOptionPane.showMessageDialog(Menu_Insert_Frame.this, "°°Àº ÀÌ¸§ÀÇ ¸Þ´º´Â Ãß°¡ ºÒ°¡ÇÕ´Ï´Ù.");
					chk = false;
				}
			}
			
            if(chk) {
               control.create_menu(menu_nameF.getText(), (String) comboBox.getModel().getSelectedItem(), 
                     Integer.parseInt(menu_priceF.getText()), imageF);
//               System.out.println("control  : "+control);
//               System.out.println("adminView  : "+control.adminView);
//               System.out.println("adminControl  : "+control.adminView.adminControl);
//               System.out.println("adminloginControl  : "+control.adminView.adminControl.adminloginControl);
//               System.out.println("boardView  : "+control.adminView.adminControl.adminloginControl.boardView);
//               System.out.println("boardControl  : "+control.adminView.adminControl.adminloginControl.boardView.boardControl);
               control.adminView.adminControl.adminloginControl.boardView.boardControl.client.sendMessage("", "¸Þ´º°»½Å", "Å×ÀÌºí_1","Å×ÀÌºí_2","Å×ÀÌºí_3","Å×ÀÌºí_4","Å×ÀÌºí_5",
                     "Å×ÀÌºí_6","Å×ÀÌºí_7","Å×ÀÌºí_8");
               menuGui.menuTable = control.refreshMeneegementList(menuGui.model, menuGui.menuTable);
               dispose();
            }
         }
      });
      
      
      contentPane.add(panel_2);
      contentPane.add(panel);
      setVisible(true);

   }
}