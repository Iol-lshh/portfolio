package sup.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.border.MatteBorder;

import aaaaaaaaaaaaaaa.InitData;
import sup.menuManagement.MenuManagementDBControl;
import java.awt.Font;
import java.awt.CardLayout;



public class MenuMainView extends JFrame {
	
	JPanel panel_8;
	JPanel contentPane;
	MenuMainController viewMenu;
	Basket basket;
	OrderMenu orderMenu;
	JPanel anjuPane;
	JPanel drinkPane;
	JLabel sumPrice;
	String tableNum;
	JPanel panel_5;
	JScrollPane anjuScroll;
	JScrollPane drinkScroll;
	JPanel panel_2;
	JPanel panel_left;
	public MenuMainView(String tableNum, MenuMainController viewMenu) {

		JPanel panel = new JPanel();
		panel_2 = new JPanel();
		JButton foodBtn = new JButton("안주");
		foodBtn.setBackground(new Color(210, 105, 30));
		foodBtn.setFont(new Font("궁서", Font.PLAIN, 20));
		foodBtn.setBorderPainted(false);
		JButton drinkBtn = new JButton("술");
		drinkBtn.setBorderPainted(false);
		drinkBtn.setBackground(Color.ORANGE);
		drinkBtn.setFont(new Font("궁서", Font.PLAIN, 21));
		this.tableNum = tableNum;
		this.viewMenu = viewMenu;
		viewMenu.menus = new MenuManagementDBControl().list2();


		setBounds(200, 40, 1000, 700);
		setTitle("메뉴판");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);


		panel_8 = new JPanel();
		panel_left = new JPanel();
		JPanel panel_1 = new JPanel();
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		contentPane.add(panel_left);
		panel_left.setLayout(new BorderLayout(0, 0));
		panel_left.add(panel_1, BorderLayout.NORTH);
		foodBtn.setEnabled(false);
		foodBtn.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {

				drinkBtn.setEnabled(true);
				foodBtn.setEnabled(false);
				anjuScroll.setVisible(true);
				drinkScroll.setVisible(false);

			}
		});
		panel_1.setLayout(new BorderLayout(0, 0));
		panel.add(foodBtn);
		anjuPane = new JPanel();
		anjuScroll = new JScrollPane(anjuPane);
		panel_2.add(anjuScroll);
		anjuPane.setBackground(new Color(210, 105, 30));
		anjuPane.setLayout(new GridLayout(2,0,3,3));
		drinkPane = new JPanel();
		drinkScroll = new JScrollPane(drinkPane);
		drinkScroll.setVisible(false);
		panel_2.add(drinkScroll);

		drinkPane.setBackground(Color.ORANGE);
		drinkPane.setLayout(new GridLayout(2,0,3,3));

		panel.add(drinkBtn);


		drinkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				foodBtn.setEnabled(true);
				drinkBtn.setEnabled(false);

				drinkScroll.setVisible(true);
				anjuScroll.setVisible(false);
			}
		});

		panel_1.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));


		panel_left.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new CardLayout(0, 0));

		JPanel panel_right = new JPanel();
		contentPane.add(panel_right);
		panel_right.setLayout(new BorderLayout(0, 0));

		panel_5 = new JPanel();
		panel_right.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JPanel panel_7 = new JPanel();
		panel_5.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("\uCD1D\uD569");
		lblNewLabel_1.setBorder(null);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setPreferredSize(new Dimension(30, 30));
		panel_7.add(lblNewLabel_1, BorderLayout.WEST);

		JLabel lblNewLabel_2 = new JLabel("\uC6D0");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setPreferredSize(new Dimension(30, 30));
		panel_7.add(lblNewLabel_2, BorderLayout.EAST);

		sumPrice = new JLabel("0");
		sumPrice.setBorder(new EmptyBorder(0, 0, 0, 3));
		sumPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_7.add(sumPrice, BorderLayout.CENTER);

		JButton orderBtn = new JButton("\uC8FC\uBB38 \uC644\uB8CC");
		panel_7.add(orderBtn, BorderLayout.SOUTH);
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewMenu.sendOrderList(tableNum);
				//            viewMenu.sendMessage();
			}
		});
		orderBtn.setPreferredSize(new Dimension(150, 23));

		panel_5.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new GridLayout(15, 1, 0, 0));

		JLabel lblNewLabel_3 = new JLabel("\uC7A5\uBC14\uAD6C\uB2C8");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 26));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_right.add(lblNewLabel_3, BorderLayout.NORTH);


		setVisible(true);
	}


}

class MenuBtn extends JLabel implements MouseListener {
	
	ImageIcon image1;
	Menu menu;
	JPanel panel8;
	JLabel sumPrice;
	JLabel menuImg;
	JLabel menuName;
	JLabel menuPrice;
	JPanel menuNP;
	MenuMainController viewMenu;
	OrderMenu orderMenu;
	Basket basket;

	public MenuBtn(Menu menu, JPanel panel8,JLabel sumPrice, MenuMainController viewMenu, OrderMenu orderMenu) {
		this.menu = menu;
		this.panel8 = panel8;
		this.sumPrice = sumPrice;
		this.viewMenu= viewMenu;
		this.orderMenu = orderMenu;

		setLayout(new BorderLayout());
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		
		File f =  new File(menu.getImageF());
		
		try {
			BufferedImage bf = ImageIO.read(f);
			image1 = new ImageIcon(bf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Image originImg = image1.getImage();

		if(menu.menu_catag.equals("술")) {
			setAlignmentY(Component.CENTER_ALIGNMENT);
			setPreferredSize(new Dimension(180,200));
			Image changedImg = originImg.getScaledInstance(175, 160, Image.SCALE_SMOOTH);
			menuImg = new JLabel(new ImageIcon(changedImg));
		} else {
			setAlignmentY(Component.CENTER_ALIGNMENT);
			setPreferredSize(new Dimension(250,180));
			Image changedImg = originImg.getScaledInstance(250, 180, Image.SCALE_SMOOTH);
			menuImg = new JLabel(new ImageIcon(changedImg));
		}
		
		
		menuName= new JLabel(menu.menu_name);
		menuPrice = new JLabel("("+menu.menu_price+" 원)");
		menuNP = new JPanel();
		menuNP.setLayout(new GridLayout(2,1));
		menuNP.add(menuName);
		menuNP.add(menuPrice);

		menuName.setHorizontalAlignment(JLabel.CENTER);
		menuPrice.setHorizontalAlignment(JLabel.CENTER);


		add(menuImg,"Center");
		add(menuNP,"South");
		setVisible(true);
		addMouseListener(this);
		System.out.println("dfjdjfkdjfdkf"+this.panel8);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		if(viewMenu.checkOrder(menu)) {
			
			viewMenu.addMenuPrice(sumPrice, menu);
			
			orderMenu = new OrderMenu(1);
			orderMenu.setMenuName(menu.menu_name);
			orderMenu.setPrice(menu.menu_price);
			orderMenu.setType(menu.getMenu_catag());
			orderMenu.setPickup(1);
			orderMenu.setCnt("1");
			
			basket = new Basket(menu,panel8,sumPrice,orderMenu,viewMenu); // basket = 주문 하나
			viewMenu.baskets.add(basket);								  // baksets = 장바구니
			viewMenu.orderMenus.add(orderMenu);
			
		}else {
			viewMenu.addOrderCnt(menu, sumPrice, basket);
		}
		panel8.setVisible(false);
		panel8.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}


