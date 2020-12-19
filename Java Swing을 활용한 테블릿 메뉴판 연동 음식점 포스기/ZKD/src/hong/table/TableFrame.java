package hong.table;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import hong.client.DefaultClient;
import hong.server.MessageObject;
import ji.tablet.TableSetController;
import ji.tablet.TabletDAO;
import ji.tablet.TabletDTO;

import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TableFrame extends JFrame {
   TableFrame frame;
   private JPanel contentPane;
   ArrayList<TablePane> tablePanes;

   TableSetController tableSetController;
   TableMainController tableMainController;
   static DefaultClient defaultClient;
   JPanel tableListPanel;
   JPanel panel_3;

   JButton setUpBtn;
   JButton billBtn;
   JButton menuPanBtn;
   JButton staffCallBtn;

   TablePane tablePane;
   JScrollPane scrollPane;
   //////////////////// myTable 정보를 받아올 애들
   public JLabel nickNameLab;// 닉네임
   JLabel conceptLab;// 컨셉
   JLabel manCntLab;// 남자 수
   JLabel womanCntLab;// 여자 수
   JLabel tableNumberLab;// 테이블 번호
   ////////////////////
   // 이 녀석을 놓고, 초기화 시킬 수 있어야 한다!
   HashMap<String, TablePane> otherTablePanes = new HashMap<String, TableFrame.TablePane>();

   ////////////////////
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               TableFrame frame = new TableFrame(defaultClient);
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
   public void refreshMyTableSetting(String nickName, String t_concept) {
      // TableInform my=tableMainController.getTableMainInform().getMyTableInform();
      TabletDTO tabletDTO = new TabletDAO().detail(tableMainController.clientName);
      System.out.println(tabletDTO.getTT_nickname());
      nickNameLab.setText(tabletDTO.getTT_nickname());// 닉네임
      conceptLab.setText(tabletDTO.getTT_concept());
      ;// 컨셉
      manCntLab.setText("" + tabletDTO.getTT_man());// 남자 수
      womanCntLab.setText("" + tabletDTO.getTT_woman());// 여자 수
      tableNumberLab.setText(tabletDTO.getTT_name());// 테이블 번호
      tableMainController.defaultClient.sendMessage("수정", "테이블상태변경", "테이블_1", "테이블_2", "테이블_3", "테이블_4", "테이블_5",
            "테이블_6", "테이블_7", "테이블_8");
   }

   public TableFrame(DefaultClient defaultClient) {
      this.defaultClient = defaultClient;
      init();
   }

   TableFrame(TableMainController tableMainController) {
      this.tableMainController = tableMainController;
      this.defaultClient = tableMainController.defaultClient;
      init();
      setVisible(true);
   }

   void init() {
      tablePanes = new ArrayList<TablePane>();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(220, 70, 950, 600);
      setTitle("테이블 오더");
      setResizable(false);
      setBackground(Color.WHITE);
      contentPane = new JPanel();
      contentPane.setForeground(Color.WHITE);
      contentPane.setBackground(Color.WHITE);
      contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
      contentPane.setLayout(new BorderLayout(10, 10));
      setContentPane(contentPane);

      JPanel myTableInformPan = new JPanel();
      myTableInformPan.setBackground(Color.WHITE);
      contentPane.add(myTableInformPan, BorderLayout.NORTH);
      myTableInformPan.setLayout(new BorderLayout(0, 0));

      // 내 테이블 정보
      JPanel left = new JPanel();
      left.setBackground(Color.WHITE);
      myTableInformPan.add(left, BorderLayout.CENTER);
      left.setLayout(new BoxLayout(left, BoxLayout.X_AXIS));

      JLabel brandNameLab = new JLabel("저같드 포차  ");
      brandNameLab.setFont(new Font("맑은고딕", Font.BOLD, 30));
      left.add(brandNameLab);

      JLabel n = new JLabel(" N: ");
      n.setFont(new Font("맑은고딕", Font.BOLD, 20));
      left.add(n);

      nickNameLab = new JLabel("(닉네임)");
      nickNameLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
      left.add(nickNameLab);

      JLabel c = new JLabel(" C: ");
      c.setFont(new Font("맑은고딕", Font.BOLD, 20));
      left.add(c);

      conceptLab = new JLabel("(컨셉)");
      conceptLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
      left.add(conceptLab);

      JLabel manLab = new JLabel("     남   ");
      manLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
      manLab.setHorizontalAlignment(SwingConstants.RIGHT);
      left.add(manLab);

      manCntLab = new JLabel("(남인원) ");
      manCntLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
      left.add(manCntLab);

      JLabel womanLab = new JLabel("     여   ");
      womanLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
      womanLab.setHorizontalAlignment(SwingConstants.RIGHT);
      left.add(womanLab);

      womanCntLab = new JLabel("(여인원)");
      womanCntLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
      left.add(womanCntLab);

      JPanel panel_5 = new JPanel();
      panel_5.setBackground(Color.WHITE);
      myTableInformPan.add(panel_5, BorderLayout.EAST);
      panel_5.setLayout(new BorderLayout(0, 0));

      JPanel panel = new JPanel();
      panel.setBackground(Color.WHITE);
      panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
      panel_5.add(panel, BorderLayout.EAST);

      JLabel noLab = new JLabel("no. ");
      noLab.setFont(new Font("맑은고딕", Font.BOLD, 27));
      noLab.setHorizontalAlignment(SwingConstants.RIGHT);
      panel.add(noLab);

      tableNumberLab = new JLabel("(테이블 번호)");
      tableNumberLab.setHorizontalAlignment(SwingConstants.RIGHT);
      tableNumberLab.setFont(new Font("맑은고딕", Font.BOLD, 27));
      panel.add(tableNumberLab);

      JPanel panel_1 = new JPanel();
      panel_1.setBackground(Color.WHITE);
      contentPane.add(panel_1, BorderLayout.CENTER);
      panel_1.setLayout(new BorderLayout(10, 10));

      // 직원호출부터 계산서 설정까지 모든 버튼이 들어감. (테블릿의 오른쪽)
      JPanel panel_2 = new JPanel();
      panel_1.add(panel_2, BorderLayout.EAST);
      panel_2.setLayout(new GridLayout(3, 1, 0, 0));

      staffCallBtn = new JButton("직원 호출");
      staffCallBtn.setBackground(new Color(255, 69, 0));
      staffCallBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tableMainController.callWaiter();
         }
      });
      staffCallBtn.setFont(new Font("맑은고딕", Font.BOLD, 30));
      staffCallBtn.setEnabled(false);
      panel_2.add(staffCallBtn);

      menuPanBtn = new JButton("메뉴판");
      menuPanBtn.setFont(new Font("맑은고딕", Font.BOLD, 30));
      menuPanBtn.setBackground(new Color(255, 99, 71));
      menuPanBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tableMainController.orderMenu();
         }
      });
      menuPanBtn.setEnabled(false);
      panel_2.add(menuPanBtn);

      JPanel panel_4 = new JPanel();
      panel_2.add(panel_4);
      panel_4.setLayout(new GridLayout(2, 1, 0, 0));

      billBtn = new JButton("계산서");
      billBtn.setBackground(new Color(255, 160, 122));
      billBtn.setFont(new Font("맑은고딕", Font.BOLD, 30));
      billBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tableMainController.openBill();
         }
      });
      panel_4.add(billBtn);
      billBtn.setEnabled(false);

      setUpBtn = new JButton("설정");
      setUpBtn.setBackground(new Color(255, 160, 122));
      setUpBtn.setFont(new Font("맑은고딕", Font.BOLD, 30));
      setUpBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tableMainController.tableSetting();
         }
      });
      setUpBtn.setEnabled(false);
      panel_4.add(setUpBtn, BorderLayout.EAST);

      // (테블릿의 왼쪽)
      panel_3 = new JPanel();
      panel_1.add(panel_3, BorderLayout.CENTER);

      panel_3.setLayout(new BorderLayout(10, 10));

      scrollPane = new JScrollPane();
      scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
      panel_3.add(scrollPane, BorderLayout.CENTER);

      tableListPanel = new JPanel();
      tableListPanel.setBackground(Color.WHITE);
      tableListPanel.setForeground(Color.WHITE);
      scrollPane.setViewportView(tableListPanel);
      tableListPanel.setLayout(new GridLayout(7, 1));

   }

   void mkTablePane(String tableName, String nickName, String concept, int manCnt, int womanCnt, String doSomthing) {
      tablePanes.add(new TablePane(tableName, nickName, concept, manCnt, womanCnt, doSomthing));
   }

   // 태블릿 메인의 고객 테이블 현황판의 상태테이블 정보 패널 객체
   class TablePane extends JPanel {
      public JButton urGameBnt;
      int manCnt;
      int womanCnt;
      String tableName;
      Image change;

      public TablePane(String tableName) {
         this.tableName = tableName;
         init("존예", "달리고 싶은 날", 0, 3, "게임대기");
      }

      public TablePane(String tableName, String nickName, String concept, int manCnt, int womanCnt,
            String doSomthing) {
         this.tableName = tableName;

         init(nickName, concept, manCnt, womanCnt, doSomthing);
      }

      public void setBackColor() {
         int tmp = 3;
         if (manCnt != 0 && womanCnt != 0)
            tmp = 2;
         else if (manCnt != 0)
            tmp = 1;
         else if (womanCnt != 0)
            tmp = 0;
         Color color = new Color[] { Color.PINK, new Color(7, 200, 255), new Color(0, 254, 0), Color.WHITE }[tmp];
         setBackground(color);
      }

      void init(String nickName, String concept, int manCnt, int womanCnt, String doSomthing) {
         this.manCnt = manCnt;
         this.womanCnt = womanCnt;
         setBackColor();
         ////////// 현황판_1 (핑크)
         setAlignmentY(Component.CENTER_ALIGNMENT);
         setPreferredSize(new Dimension(350, 100));// 너의 테이블 정보 판 크기
         setBorder(new LineBorder(new Color(0, 0, 0), 2));

         // 0
         tableListPanel.add(this);
         setLayout(new BorderLayout(10, 10));// 0

         // 1
         JPanel urNGCPan = new JPanel();
         urNGCPan.setOpaque(false);
         add(urNGCPan, BorderLayout.WEST);
         urNGCPan.setLayout(new BorderLayout(0, 0));

         // 2
         JPanel urNickConG = new JPanel();
         add(urNickConG, BorderLayout.CENTER);
         urNickConG.setOpaque(false);
         urNickConG.setLayout(new GridLayout(2, 0, 0, 0));

         // 2-1 남녀성비 판
         JPanel urGCPan = new JPanel();
         urGCPan.setOpaque(false);
         urGCPan.setLayout(new BoxLayout(urGCPan, BoxLayout.X_AXIS));
         urNickConG.add(urGCPan);

         // 2-1-1 현황판 남자 인원수 표시
         // JLabel urManLab = new JLabel("남 ");
         // urManLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
         // urGCPan.add(urManLab);

         // 2-1-2
         JLabel urManCntLab = new JLabel("남  " + manCnt);
         urManCntLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
         urGCPan.add(urManCntLab);

         // 2-1-3 현황판 여자 인원수 표시
         // JLabel urWomanLab = new JLabel(" 여 ");
         // urWomanLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
         // urGCPan.add(urWomanLab);

         // 2-1-4
         JLabel urWomanCntLab = new JLabel("   여  " + womanCnt);
         urWomanCntLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
         urGCPan.add(urWomanCntLab);

         // 2-2
         JPanel NickCon = new JPanel();
         NickCon.setLayout(new GridLayout(0, 2, 0, 0));
         NickCon.setOpaque(false);
         urNickConG.add(NickCon);

         // 2-2-1 닉네임 표시
         JLabel urNickLab = new JLabel("닉네임 : " + nickName);
         urNickLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
         NickCon.add(urNickLab);

         // 2-2-2 현황판 컨셉 표시
         JLabel urConceptLab = new JLabel("컨셉 : " + concept);
         urConceptLab.setFont(new Font("맑은고딕", Font.BOLD, 20));
         NickCon.add(urConceptLab);

         // 3 악어버튼 판
         JPanel urGamePan = new JPanel();
         urGamePan.setOpaque(false);
         add(urGamePan, BorderLayout.EAST);
         urGamePan.setLayout(new BorderLayout(0, 0));

         // 3-1 게임 버튼
         ImageIcon croco = new ImageIcon("img/움직이는 악어.gif");
         urGameBnt = new JButton(croco);
         urGameBnt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

               tableMainController.letsGame(tableName);
            }
         });
         if (doSomthing.equals("게임중")) {
            urGameBnt.setEnabled(false);
         } else {
            urGameBnt.setEnabled(true);
         }
         urGameBnt.setOpaque(false);
         urGameBnt.setBackground(Color.black);
         urGamePan.add(urGameBnt);

         // 1-1 아이콘 판
         JPanel urIcon = new JPanel();
         urIcon.setOpaque(false);
         urNGCPan.add(urIcon, BorderLayout.CENTER);
         urIcon.setLayout(new BorderLayout(0, 0));
         urIcon.setBorder(new EmptyBorder(5, 20, 5, 30));

         if (manCnt > 0 && womanCnt > 0) {
            ImageIcon icon = new ImageIcon("img/혼성.png");
            Image img = icon.getImage();
            change = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
         } else if (manCnt > 0) {
            ImageIcon icon = new ImageIcon("img/남자.png");
            Image img = icon.getImage();
            change = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
         } else if (womanCnt > 0) {
            ImageIcon icon = new ImageIcon("img/여자.png");
            Image img = icon.getImage();
            change = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
         } else {
            ImageIcon icon = new ImageIcon("img/없음.png");
            Image img = icon.getImage();
            change = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            urGameBnt.setEnabled(false);
         }
         // 1-1-1 아이콘 라벨
         JLabel urIconLab = new JLabel(new ImageIcon(change));
         urIconLab.setOpaque(false);
         urIcon.add(urIconLab);
      }
   }
}