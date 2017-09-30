/*
Name: Nathaniel Bates
Course: CNT 4714 - Fall 2017
Assignment title: Program 1 - Event-driven Programming
Date: Sunday September 10, 2017
 */

package Project1;


// Book class contains the relevant information for each book.
// Used to create book objects which are stored in the inventory ArrayList.

public class Book {
    
    String isbn;
    String title;
    double price;
    
    // Constructor
    public Book(String isbn, String title, double price)
    {
        this.isbn = isbn;
        this.title = title;
        this.price = price;
    }
    
}
