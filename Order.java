package edu.psu.abington.ist.ist242;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.ArrayList;

public class Order {

    //Class Level Variables - Protect the data
    private int orderId;
    private Customer cust;
    private ArrayList<Menu> orderItems;

    //Constructor Method
    public Order(){};
    public Order(int orderId){
        this.orderId = orderId;
        this.orderItems = new ArrayList<Menu>();
    };
    public Order(int _orderId, Customer cust, ArrayList<Menu> orderItems){
        this.orderId = _orderId;
        this.cust = cust;
        this.orderItems = orderItems;
    }

    //Setters and Getters
    public int getorderId() { return orderId; }
    public void setorderId(int _orderId) {this.orderId = _orderId;}

    public void setCustomer(Customer cust) {this.cust = cust;};
    public Customer getCustomer() {return cust;};

    public ArrayList<Menu> getOrderItems(){return orderItems;};
    public ArrayList<Menu> setOrderItems(ArrayList<Menu> orderItems){return this.orderItems = orderItems;};

    public void addItem(Menu item) { orderItems.add(item);};

    public static Order getOrderbyId(ArrayList<Order> oList, int id){
        for (Order order: oList){
            if(order.getorderId() == id)
                return order;
        }
        return new Order();
    }

    public void printOrder(){
        System.out.println("Order for " + cust.getCustomerName());
        double total = 0;
        for(int i = 0; i < orderItems.size(); i++){
            total = total + orderItems.get(i).getprice();
            System.out.println(orderItems.get(i).getmenuItem() + " " + orderItems.get(i).getprice());
        }
        System.out.println("Order total $" + total);
    }
}
