/*
Name: Nathaniel Bates
Course: CNT 4714 - Fall 2017
Assignment title: Program 1 - Event-driven Programming
Date: Sunday September 10, 2017
*/

package Project1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

// Upon completion of an order, the invoice object is created.

public class Invoice {
 
    Date date = null;
    ArrayList<Order> orders = new ArrayList<>();
    double subtotal;
    int numItems;
    double taxRate = 6;
    double taxAmount = 0.00;
    double orderTotal = 0.00;
    String thankYouMessage = "Thanks for shopping at Ye Olde Book Shoppe!\n";
    
    // Constructor
    public Invoice(ArrayList<Order> orders, double subtotal, int numItems)
    {
        this.orders = orders;
        this.numItems = numItems;
        this.subtotal = subtotal;
        this.date = new Date();
        this.taxAmount = subtotal * (taxRate / 100);
        this.orderTotal = subtotal + this.taxAmount;
    }
    
    // Method lists all of the books the user ordered and appears on the invoice.
    
    public String itemList()
    {   
        String invoicedItems = "";
        int i = 1;
        for(Order a : this.orders)
        {
            invoicedItems += i + ". " + a.bookOrderString + "\n";
            i++;
        }
        
        return invoicedItems;
    }
    
    // Method returns a large string which is used to show the final invoice to
    // the user by a messagebox.
    
    public String finalInvoice()
    {
        String invoicedItems = itemList();
        DecimalFormat one = new DecimalFormat("0.00");
        SimpleDateFormat today = new SimpleDateFormat("M/dd/yy hh:mm:ss a z");
        
        String customerInvoice = "Date: " + today.format(this.date) + "\n\nNumber of line items: " 
                + this.numItems + "\n\nItem# / ID / Title / Price / Qty / Disc % / Subtotal\n\n"
                + invoicedItems + "\nOrder subtotal: $" + one.format(this.subtotal) + "\n\n"
                + "Tax rate: " + this.taxRate + "%\n\nTax Amount: $"
                + one.format(this.taxAmount) + "\n\nOrder total: $" + one.format(this.orderTotal)
                + "\n\n" + thankYouMessage + "\n";
        
        return customerInvoice;
    }
    
    // Method writes the invoice to the transactions.txt file. Each book order
    // on the invoice includes a timestamp based on the time the order was
    // completed. This is reflected in the transactions.txt file as per project
    // requirements.  If the file cannot be created, user is alerted.
    
    public void writeInvoice() throws FileNotFoundException, IOException{

        String line;
        SimpleDateFormat regDate = new SimpleDateFormat ("MM/dd/yy hh:mm:ss a z");
        SimpleDateFormat timeStamp = new SimpleDateFormat ("yyMMddhhmmss"); 
        
        try {
            File file = new File("transactions.txt");

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file,true);
            try (BufferedWriter bw = new BufferedWriter(fw)) {
                for(Order o : this.orders){
                    line = timeStamp.format(this.date) + ", " + o.invoiceString+", " + regDate.format(this.date) + "\n";
                    bw.write(line);
                }
            }

        } catch(IOException ioe){
            JOptionPane.showMessageDialog(null, "File not found and could not be created.", "Alert", JOptionPane.ERROR_MESSAGE); 
        }    

}

}
