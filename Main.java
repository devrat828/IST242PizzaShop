package edu.psu.abington.ist.ist242;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    int cCount = 1;
    int tCount = 1;
    int oCount = 1;
    public static void main(String[] args) {

        Main main = new Main();

        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        final char CUST_ORDERS = 'X';
        final char HELP_CODE = '?';
        char userAction;
        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, Print all 'X' Order, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();

        Menu menu1 = new Menu(1, "Plain", 10.00);
        Menu menu2 = new Menu(2, "Meat", 15.00);
        Menu menu3 = new Menu(3, "Extra", 18.00);
        Menu menu4 = new Menu(4, "Veg",12.00);

        mList.add(menu1);
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);

        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {
            switch(userAction) {
                case CUST_CODE : cList.add(main.addCustomer());
                    break;
                case CUST_PRNT : Customer.printCustomer(cList);
                    break;
                case MENU_CODE : Menu.listMenu(mList);
                    break;
                case ORDE_CODE : oList.add(main.addOrders(cList,mList));
                    break;
                case CUST_ORDERS :
                    for (Order order: oList) {
                        order.printOrder();
                    }
                    break;
                case TRAN_CODE :
                    tList.add(main.addTransaction(oList));
                    Transaction.listTransactions(tList);
                    break;
            }

            userAction = getAction(PROMPT_ACTION);
        }
    }

    public static char getAction(String prompt) {
        Scanner scnr = new Scanner(System.in);
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    public Customer addCustomer(){
        Customer cust = new Customer(cCount++);
        Scanner scnr = new Scanner(System.in);
        System.out.println("Please Enter your Name: ");
        cust.setCustomerName(scnr.nextLine());
        System.out.println("Please Enter your Phone: ");
        cust.setCustomerPhoneNumber(scnr.nextLine());
        return cust;
    }

    public Order addOrders(ArrayList<Customer> cList, ArrayList<Menu> mList){
        Order order = new Order(oCount++);
        Scanner scan = new Scanner(System.in);
        Customer.printCustomer(cList);
        System.out.println("Please enter a customer id from the list: ");
        order.setCustomer(Customer.getCustomerbyId(cList,scan.nextInt()));
        Menu.listMenu(mList);
        System.out.println("How many items do you want to add:");
        int items = scan.nextInt();
        for(int i = 0; i < items; i++){
            System.out.println("Menu id: ");
            order.addItem(Menu.getMenubyId(mList,scan.nextInt()));
        }
        System.out.println("Order added!");
        return order;
    }

    public Transaction addTransaction(ArrayList<Order> oList){
        Scanner scan = new Scanner(System.in);
        for (Order order: oList) {
            order.printOrder();
        }
        System.out.println("Choose an order id to make transaction: ");
        Order order = Order.getOrderbyId(oList,scan.nextInt());
        System.out.println("Choose payment type 'C'ash or 'Cr'edit: ");
        String ans = scan.next();
        Transaction transaction;
        if(ans.equalsIgnoreCase("C" )) {
            transaction = new Transaction(tCount++, order, PaymentType.cash);
            //tList.add(trans1);
        }
        else{
            transaction = new Transaction(tCount++, order, PaymentType.credit);
            //tList.add(trans2);
            CreditPayment();
        }
        return transaction;
    }

    public static void CreditPayment() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter credit card details");
        System.out.print("Name on credit card: ");
        String name = scan.nextLine();
        boolean valid = true;

        while (valid) {
            System.out.print("\nCredit card number(XXXXXXXXXX): ");
            String number = scan.nextLine();
            try {
                Integer.parseInt(number);
                valid = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid card number!");
            }
        }

        valid = true;
        while (valid) {
            System.out.print("\nExpiration Date (MMYY): ");
            String date = scan.nextLine();
            try {
                Integer.parseInt(date);
                valid = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid Expiration date!");
            }
        }

        valid = true;
        while (valid) {
            System.out.print("\nCVV number (XXX): ");
            String cvv = scan.nextLine();
            try {
                Integer.parseInt(cvv);
                valid = false;
            } catch (NumberFormatException e) {
                System.out.println("Invalid cvv number!");
            }
        }
    }
}
