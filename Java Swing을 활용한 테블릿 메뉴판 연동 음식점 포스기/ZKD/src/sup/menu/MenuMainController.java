package sup.menu;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;

import aaaaaaaaaaaaaaa.InitData;
import hong.client.DefaultClient;
import hong.client.ReceiverObjFromClient;
import hong.server.MessageObject;
import hong.table.TableMainController;
import sup.bills.Bills;
import sup.bills.BillsControl;
import sup.menuManagement.MenuManagementDBControl;
                                                                  
public class MenuMainController {
	
   public ArrayList<Menu> menus;                                 
   public MenuMainView menuGui;      
   
   DefaultClient dfc;          
   ArrayList<OrderMenu> orderMenus;                              
   ArrayList<Basket> baskets;                                    
   ArrayList<MenuBtn> menuBtns;
   
   int tot;
   
   String ip;
   String tableNum;
   
   TableMainController tbControl;
   
   
   public MenuMainController(String tableNum,DefaultClient dfc, TableMainController tbControl) {
      this.dfc=dfc;
      this.tbControl = tbControl;
      init(tableNum);
   }

   void init(String tableNum) {
	  this.tableNum = tableNum;
      menuGui = new MenuMainView(tableNum,this);
      menuBtns = new ArrayList<MenuBtn>();
      insertDrinkList();
      insertFoodList();
      orderMenus = new ArrayList<OrderMenu>();
      baskets = new ArrayList<Basket>();
   }
   
   public void getMenuList() {
      menus = new MenuManagementDBControl().list2();
   }
   
   public boolean checkOrder(Menu menu) {
      boolean chk= true;
      for (OrderMenu ordermenu : orderMenus) {
         if(menu.menu_name.equals(ordermenu.menuName)&&menu.menu_price==ordermenu.price) {
            System.out.println("이름: "+menu.menu_name);
            return false;
         }
      }
      return chk;
   }
   public void menuChange() {
	 
	  menus = new MenuManagementDBControl().list2();
	  menuGui.anjuScroll.remove(menuGui.anjuPane);
	  menuGui.drinkScroll.remove(menuGui.drinkPane);
	  menuGui.panel_2.remove(menuGui.anjuPane);
	  menuGui.panel_2.remove(menuGui.drinkPane);
	  menuGui.panel_left.remove(menuGui.panel_2);
	  menuGui.anjuPane = new JPanel();
	  menuGui.anjuPane.setBackground(new Color(210, 105, 30));
	  menuGui.anjuPane.setLayout(new GridLayout(2,0,3,3));
	  menuGui.drinkPane = new JPanel();
	  menuGui.drinkPane.setBackground(Color.ORANGE);
	  menuGui.drinkPane.setLayout(new GridLayout(2,0,3,3));
	  menuGui.anjuScroll = new JScrollPane(menuGui.anjuPane);
	  menuGui.drinkScroll = new JScrollPane(menuGui.drinkPane);
	  menuGui.panel_2 = new JPanel();
	  menuGui.panel_2.setLayout(new CardLayout(0, 0));
      insertFoodList();
      insertDrinkList();
      menuGui.panel_2.add(menuGui.anjuScroll);
      menuGui.panel_2.add(menuGui.drinkScroll);
      menuGui.panel_left.add(menuGui.panel_2,BorderLayout.CENTER);
      menuGui.anjuScroll.setVisible(false);
      menuGui.anjuScroll.setVisible(true);
      menuGui.panel_left.setVisible(false);
      menuGui.panel_left.setVisible(true);
      
      
   }
   public void addOrderCnt(Menu menu, JLabel sumPrice, Basket basket) {
      int i=0;
      for (OrderMenu orderMenu : orderMenus) {
         if(menu.menu_name.equals(orderMenu.menuName)&&menu.menu_price==orderMenu.price) {
            orderMenu.setCnt(orderMenu.cnt+1);
            baskets.get(i).menuCnt.setValue(orderMenu.cnt);
         }
         i++;
      }
      sumPrice(sumPrice);
   }
   public void addMenuPrice(JLabel sumPrice,Menu menu) {
      tot = Integer.parseInt(sumPrice.getText())+menu.menu_price;
      sumPrice.setText(tot+"");
   }
   
   void change_panel_8() {
      for (MenuBtn menuBtn : menuBtns) {
         menuBtn.panel8 = menuGui.panel_8;
      }
   }
   public void sumPrice(JLabel sumPrice) {
      tot = 0;
      
      for (OrderMenu orderMenu : orderMenus) {
         tot += orderMenu.sum;
      }
      
      sumPrice.setText(tot+"");
   }
   
   public void insertFoodList() {
      
      for (Menu menu : menus) {
         if(menu.getMenu_catag().equals("안주")) {
            MenuBtn menuBtn = new MenuBtn(menu, menuGui.panel_8, menuGui.sumPrice, this, menuGui.orderMenu);
            menuGui.anjuPane.add(menuBtn);
         }
      }
      
   }
   
   public void insertDrinkList() {
      for (Menu menu : menus) {
         if(menu.getMenu_catag().equals("술")) {
            MenuBtn menuBtn = new MenuBtn(menu ,menuGui.panel_8, menuGui.sumPrice, this,  menuGui.orderMenu);
            menuGui.drinkPane.add(menuBtn);
            menuBtns.add(menuBtn);
         }
      }
      
   }
   
   public void sendOrderList(String tableNum) {
      for (OrderMenu orderMenu : orderMenus) {
         if(orderMenu.cnt != 0) {
            new OrderListDBControl(InitData.ip).insert(orderMenu, tableNum);
         }
      }
      
      dfc.sendMessage(tableNum, "주문","주방","카운터");
      tbControl.billsControl.bills.setOrderMenus(new OrderListDBControl(InitData.ip).list(tableNum));
      tbControl.billsControl.changeOrderMenu();
      closeMenuMain();

   }
   
   public void addBills(String tableNum) {
      tbControl.billsControl.addOrderList();
   }
   
   public void closeMenuMain() {
      menuGui.dispose();
   }
   

   
}
