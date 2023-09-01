package Main;

import Main.User.Customer;

import java.io.*;
import java.util.LinkedList;

public class CustomerSavedData {
    public static LinkedList<Customer> customerList = new LinkedList<>();
    public static void ReadCustomerData() throws IOException {
        BufferedReader Reader = new BufferedReader(new FileReader("C:\\Projects\\Uber\\project\\src\\Main\\UserData\\Customer.txt"));
        String line;
        while((line = Reader.readLine()) != null){
            Customer nCustomer = new Customer();
            nCustomer.Name = line;
            nCustomer.Age = Short.parseShort(Reader.readLine());
            nCustomer.Uber_Mail = Reader.readLine();
            nCustomer.Uber_Password = Reader.readLine();
            nCustomer.ReservedBusRide = Integer.parseInt(Reader.readLine());
            nCustomer.paymentMethodtype = Reader.readLine();
            //nCustomer.payer = Reader.readLine();
            if(nCustomer.paymentMethodtype.equals("paypal")){
                nCustomer.payer = new paypal(Reader.readLine(), Reader.readLine());
            } else if(nCustomer.paymentMethodtype.equals("credit card")){
                nCustomer.payer = new credit_card(Reader.readLine(), Reader.readLine());
            }
            customerList.add(nCustomer);


        }
        Reader.close();
    }
    
    public static void WriteCustomerData() throws IOException {
        BufferedWriter Writer = new BufferedWriter(new FileWriter("C:\\Projects\\Uber\\project\\src\\Main\\UserData\\Customer.txt"));
        for(int i = 0; i < customerList.size(); i++){
            
                Writer.append(customerList.get(i).Name + "\n" + customerList.get(i).Age);
                Writer.append("\n" + customerList.get(i).Uber_Mail + "\n" + customerList.get(i).Uber_Password);
                Writer.append("\n" + customerList.get(i).ReservedBusRide+ "\n");
                Writer.append("\n" + customerList.get(i).payer.getAccountNumber() + "\n" + customerList.get(i).payer.getAccountPassword()+ "\n");

        }
        Writer.close();
    }
}
