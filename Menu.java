package edu.psu.abington.ist.ist242;

import java.util.ArrayList;

public class Menu {

    //Class Level Variables - Protect the data
    private int menuId;
    private String menuItem;
    private double price;

    //Constructor Method
    public Menu(int _menuId, String _menuItem, double price){
        this.menuId = _menuId;
        this.menuItem = _menuItem;
        this.price = price;
    }
    public Menu(){};

    //Setters and Getters
    public int getmenuId() { return menuId; }
    public void setmenuId(int _menuId) {this.menuId = _menuId;}

    public String getmenuItem() { return menuItem; }
    public void setmenuItem(String _menuItem) {this.menuItem = _menuItem;}

    public double getprice() { return price; }
    public void setPrice(double price) {this.price = price;}

    public static Menu getMenubyId(ArrayList<Menu> mList, int id){
        for(Menu menu: mList){
            if(menu.getmenuId() == id)
                return menu;
        }
        return new Menu();
    }

    public static void listMenu(ArrayList<Menu> mList){
        for (Menu menu: mList){
            System.out.println(menu.getmenuId() + " " + menu.getmenuItem() + " " + menu.getprice());
        }
    }

}
