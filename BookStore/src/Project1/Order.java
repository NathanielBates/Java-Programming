/*
Name: Nathaniel Bates
Course: CNT 4714 - Fall 2017
Assignment title: Program 1 - Event-driven Programming
Date: Sunday September 10, 2017
 */

package Project1;

import java.text.DecimalFormat;

// Order object is created based off of the quantity of a particular book being
// ordered. 2 types of strings are generated, one for viewing the current order,
// and one that is used for the transactions.txt file.

public class Order {
    
    Book book = null;
    int quantity = 0;
    double total = 0.00;
    double discount = 0.00;
    String bookOrderString = "";
    String invoiceString = "";
    
    // Constructor
    public Order(Book book, int quantity, double total, double percentDiscount){
        DecimalFormat disString = new DecimalFormat("##");
        DecimalFormat totString = new DecimalFormat("0.00");
        this.book = book;
        this.quantity = quantity;
        this.total = total;
        this.discount = percentDiscount;
        
        this.bookOrderString = book.isbn + " " + book.title + " $" + totString.format(book.price) 
                + " " + quantity + " " + disString.format(percentDiscount * 100) + 
                "% $" + totString.format(total);
                
        this.invoiceString = book.isbn + ", " + book.title + ", " + totString.format(book.price) +
                ", " + quantity + ", " + discount +", " + totString.format(total);
        
    }          
}
