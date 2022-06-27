package sup.menuManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sup.menu.Menu;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Menu_modify_Frame extends JDialog {
   File imageF;
   JPanel contentPane;
   JLabel image_label;
   JTextField textField;
   JTextField textField_1;
   JComboBox comboBox;
   MMcontroller mmCon;
   ImageIcon img;
   Menu menu;
//   public static void main(String[] args) {
//      new Menu_modify_Frame(new MMcontroller("localhost", 7777));
//   }
   public Menu_modify_Frame(MMcontroller mmCon, Menu menu, MenuManagementMain mmGui) {
	   super(mmGui,"¸Þ´º¼öÁ¤",true);
      this.mmCon = mmCon;
      this.menu=menu;
      setBounds(410, 390, 600, 250);
      setResizable(false);
   
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(new GridLayout(1, 2, 0, 0));
      
      
      
      image_label = new JLabel("");
      image_label.setHorizontalAlignment(SwingConstants.CENTER);
      contentPane.add(image_label);
      
      JPanel panel_update = new JPanel();
      contentPane.add(panel_update);
      panel_update.setLayout(new GridLayout(5, 2, 0, 0));
      
      JLabel manuNameLabel = new JLabel("\uBA54\uB274 \uC774\uB984");
      manuNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
      panel_update.add(manuNameLabel);
      
      textField = new JTextField(menu.getMenu_name());
      panel_update.add(textField);
      textField.setColumns(10);
      
      JLabel menuPriceLabel_1 = new JLabel("\uAC00\uACA9");
      menuPriceLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_update.add(menuPriceLabel_1);
      
      textField_1 = new JTextField(menu.getMenu_price()+"");
      panel_update.add(textField_1);
      textField_1.setColumns(10);
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(5, 2, 0, 0));
      
      JLabel menuCatagLabel = new JLabel("\uCE74\uD14C\uACE0\uB9AC");
      menuCatagLabel.setHorizontalAlignment(SwingConstants.CENTER);
      panel_update.add(menuCatagLabel);
      
      JComboBox comboBox = new JComboBox();
      comboBox.setModel(new DefaultComboBoxModel(new String[] {"\uC548\uC8FC", "\uC220"}));
      comboBox.setSelectedItem(menu.getMenu_catag());
      panel_update.add(comboBox);
      
      try {
         
    	  imageF = new File(menu.getImageF());
          BufferedImage bf = ImageIO.read(imageF);
          ImageIcon img = new ImageIcon(bf);
          
          Image changedImg = img.getImage();
          Image img2 = changedImg.getScaledInstance(260, 200, Image.SCALE_SMOOTH);
          image_label.setIcon(new ImageIcon(img2));
         
      } catch (Exception e2) {
         // TODO Auto-generated catch block
         e2.printStackTrace();
      }
      JButton imageloadBtn = new JButton("\uD30C\uC77C \uB85C\uB4DC");
      imageloadBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               imageF = mmCon.fileOpenDlg();
               BufferedImage bf = ImageIO.read(imageF);
               img = new ImageIcon(bf);
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
      panel_update.add(panel_1);
      panel_1.setLayout(new GridLayout(2, 1, 0, 0));
      
      JLabel err1_1 = new JLabel("");
      err1_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(err1_1);
      
      JLabel err1_2 = new JLabel("");
      err1_2.setHorizontalAlignment(SwingConstants.CENTER);
      panel_1.add(err1_2);
      
      panel_update.add(imageloadBtn);
      
      
      JPanel panel_2 = new JPanel();
      panel_update.add(panel_2);
      panel_2.setLayout(new GridLayout(0, 1, 0, 0));
      
      JLabel err2_1 = new JLabel("");
      err2_1.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(err2_1);
      JLabel err2_2 = new JLabel("");
      err2_2.setHorizontalAlignment(SwingConstants.CENTER);
      panel_2.add(err2_2);
      
      
      JButton applyButton = new JButton("\uC801\uC6A9");
      applyButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            boolean chk = true;
            if(!Pattern.matches("[¤¡-ÆRA-Za-z0-9]{1,6}", textField.getText())||textField.getText().equals("")) {
               err1_1.setText("6±ÛÀÚ¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
               err1_1.setForeground(Color.red);
               err1_2.setText("Æ¯¼ö¹®ÀÚ ÀÔ·Â ºÒ°¡");
               err1_2.setForeground(Color.red);
               chk = false;
            }else {
               err1_1.setText("");
               err1_2.setText("");
            }
            if(!Pattern.matches("[0-9]{1,7}", textField_1.getText())||textField_1.getText().equals("")) {
               err2_1.setText("¼ýÀÚ¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
               err2_2.setText("Å«¼ö´Â ÀÔ·Â ºÒ°¡");
               err2_1.setForeground(Color.red);
               err2_2.setForeground(Color.red);
               chk = false;
            }else {
               err2_1.setText("");
               err2_2.setText("");
            }
            if(chk) {
               mmCon.update_menu(menu.getMenu_name(),new Menu(               
                           textField.getText(),                      
                           (String) comboBox.getModel().getSelectedItem(), 
                           Integer.parseInt(textField_1.getText()) ,      
                           imageF.getPath()));                        
               mmGui.menuTable = mmCon.refreshMeneegementList(mmGui.model, mmGui.menuTable);
               Menu_modify_Frame.this.dispose();
               
               mmCon.adminView.adminControl.adminloginControl.boardView.boardControl.client.sendMessage("", "¸Þ´º°»½Å", "Å×ÀÌºí_1","Å×ÀÌºí_2","Å×ÀÌºí_3","Å×ÀÌºí_4","Å×ÀÌºí_5",
                     "Å×ÀÌºí_6","Å×ÀÌºí_7","Å×ÀÌºí_8");
            }
         }
      });
      
      panel_update.add(applyButton);
      setVisible(true);
   }

}