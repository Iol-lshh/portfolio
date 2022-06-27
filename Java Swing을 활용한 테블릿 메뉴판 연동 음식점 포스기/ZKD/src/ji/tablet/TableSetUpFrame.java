package ji.tablet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TableSetUpFrame extends JFrame  {

   TableSetController tableSetController;
   ButtonGroup bg1;
   public JButton pass;
   public JButton table_SetUp_Bnt;
   private JPanel contentPane;
   private JTextField nickNameTextField ;
   JLabel result;
   
   /**
    * Launch the application.
    */
//   public static void main(String[] args) {
//      EventQueue.invokeLater(new Runnable() {
//         public void run() {
//            try {
//               TableSetUpFrame frame = new TableSetUpFrame();
//               frame.setVisible(true);
//            } catch (Exception e) {
//               e.printStackTrace();
//            }
//         }
//      });
//   }

   /**
    * Create the frame.
    */
   
   public TableSetUpFrame() {
      init();
   }
   public TableSetUpFrame(TableSetController tableSetController) {
      this.tableSetController=tableSetController;
      init();
      setVisible(true);
   }
   void init() {
      setBounds(400, 200, 612, 210);
      setTitle("´Ð³×ÀÓ/ÄÁ¼Á");
      setResizable(false);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JPanel nickNameSetUpPanel = new JPanel();
      contentPane.add(nickNameSetUpPanel, BorderLayout.NORTH);
      nickNameSetUpPanel.setLayout(new BorderLayout(0, 0));
      
      JLabel youLab = new JLabel("´ç½ÅÀÇ ");
      nickNameSetUpPanel.add(youLab, BorderLayout.WEST);
      
      JLabel areLab = new JLabel(" ÀÔ´Ï´Ù.");
      nickNameSetUpPanel.add(areLab, BorderLayout.EAST);
      
      JPanel panel_5 = new JPanel();
      nickNameSetUpPanel.add(panel_5, BorderLayout.CENTER);
      panel_5.setLayout(new BorderLayout(0, 0));
      
      JLabel nickNamelab = new JLabel("´Ð³×ÀÓÀº ");
      panel_5.add(nickNamelab, BorderLayout.WEST);
      
      nickNameTextField = new JTextField("GUEST");
      panel_5.add(nickNameTextField, BorderLayout.CENTER);
      nickNameTextField.setColumns(10);
      
       JPanel panel = new JPanel();
         nickNameSetUpPanel.add(panel, BorderLayout.SOUTH);
         panel.setLayout(new GridLayout(0, 2, 1, 0));

         JLabel nickN_regex = new JLabel("           * ÇÑ±Û·Î 5±ÛÀÚ ÀÌÇÏ·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.");
         nickN_regex.setHorizontalAlignment(SwingConstants.LEFT);
         panel.add(nickN_regex);
      
      JPanel cenceptSepUpPanel = new JPanel();
      contentPane.add(cenceptSepUpPanel, BorderLayout.CENTER);
      cenceptSepUpPanel.setLayout(new BorderLayout(0, 0));
      
      JPanel panel_3 = new JPanel();
      cenceptSepUpPanel.add(panel_3, BorderLayout.NORTH);
      
      JLabel concept = new JLabel("¿À´ÃÀÇ À½ÁÖ ÄÁ¼ÁÀ» ¼±ÅÃÇØÁÖ¼¼¿ä !");
      panel_3.add(concept);
      
      JPanel conceptBntPanel = new JPanel();
      cenceptSepUpPanel.add(conceptBntPanel, BorderLayout.CENTER);
      
      JToggleButton conceptBnt1 = new JToggleButton("¹æÇØ¹Þ±â ½ÈÀ½"); //´­·ÁÁ®ÀÖ´Â ¹öÆ° »ý¼ºÇÏ±â//true´Â ´­·ÁÀÖ´Ù.
      conceptBnt1.setBounds(20, 210, 70, 30);
      conceptBntPanel.add(conceptBnt1);
      JToggleButton conceptBnt2 = new JToggleButton("°¡º±°Ô ÇÑÀÜ¸¸");
      conceptBnt2.setBounds(90, 210, 70, 30);
      conceptBntPanel.add(conceptBnt2);
      JToggleButton conceptBnt3 = new JToggleButton("´Þ¸®°í½ÍÀº ³¯");
      conceptBnt3.setBounds(160, 210, 70, 30);
      conceptBntPanel.add(conceptBnt3);
      bg1 = new ButtonGroup(); //¹öÆ°À» ¹­¾îÁÖ´Â °Í - Åä±Û1À» ´­·¶À»¶§ Åä±Û2°¡ Ç®·ÈÀ¸¸é ÁÁ°Ú¾î¼­
      bg1.add(conceptBnt1);
      bg1.add(conceptBnt2);
      bg1.add(conceptBnt3);
      
      
      JPanel panel_2 = new JPanel();
      contentPane.add(panel_2, BorderLayout.SOUTH);
      panel_2.setLayout(new BorderLayout(0, 0));
      
      //È®ÀÎ¹öÆ° ´©¸£¸é ´Ð³ØÀÓ°ú ÄÁ¼ÁÀÌ DB¿¡ ÀÚ·á ³Ö°í Å×ÀÌºíÅÂºí¸´¿¡ ¶ß°Ô ÇÏ±â
      table_SetUp_Bnt = new JButton("È®ÀÎ");
      table_SetUp_Bnt.setEnabled(false);
      table_SetUp_Bnt.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
             String strNN = nickNameTextField.getText();

                  if (Pattern.matches("[¤¡-ÆR]{1,5}", strNN)) {
                  String tT_concept= "";
            tableSetController.tableMaincontroller.openBtn();
      
            if(conceptBnt1.isSelected())tT_concept+=conceptBnt1.getText();
            else if(conceptBnt2.isSelected())tT_concept+=conceptBnt2.getText();
            else if(conceptBnt3.isSelected())tT_concept+=conceptBnt3.getText();
            tableSetController.setNickNameNConcept(strNN, tT_concept ,tableSetController.clientName);
            tableSetController.refreshTable(strNN, tT_concept);
            dispose();//È®ÀÎ ¹öÆ° ´©¸£¸é Ã¢ ´Ý±â
                  }else if(!Pattern.matches("[¤¡-ÆR]{1,5}", strNN)){
                      result.setText("Çü½Ä¿¡ ¸ÂÁö¾Ê½À´Ï´Ù.");
                      result.setForeground(Color.RED);
                   }
            }
         });
      panel_2.add(table_SetUp_Bnt);
      
      result = new JLabel(" ");
         result.setHorizontalAlignment(SwingConstants.LEFT);
         panel.add(result);
      
      //°Ç³Ê¶Ù±â ¹öÆ° ´©¸£¸é Ã¢ÀÌ ²¨Áö°Ô ¸¸µé±â
      pass = new JButton("°Ç³Ê¶Ù±â");
      pass.setEnabled(false);      
      panel_2.add(pass, BorderLayout.EAST);
      
      pass.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) 
         
         {
            tableSetController.tableMaincontroller.openBtn();

               tableSetController.setNickNameNConcept("GUEST", "   ", tableSetController.clientName);
               tableSetController.refreshTable("GUEST", "   ");
               dispose();// °Ç³Ê¶Ù±â ¹öÆ° ´©¸£¸é Ã¢ ´Ý±â
         }
      });
      
      
   }

}