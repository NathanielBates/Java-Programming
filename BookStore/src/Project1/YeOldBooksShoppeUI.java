/*
Name: Nathaniel Bates
Course: CNT 4714 - Fall 2017
Assignment title: Program 1 - Event-driven Programming
Date: Sunday September 10, 2017
 */

package Project1;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class YeOldBooksShoppeUI extends javax.swing.JFrame {
    
    // ArrayList inventory contains the books from the text file along with all 
    // the pertinent information for each book.  ArrayList orders contains the
    // individual order information for each book per invoice.  For each invoice
    // there will be one ArrayList of orders and the static variables will 
    // reflect the subtotal and number of items for the invoice.  orderNumber
    // is used for tracking.
    
    static ArrayList <Book> inventory = new ArrayList();
    static ArrayList <Order> orders = new ArrayList();
    static int orderNumber = 0;
    static double Subtotal = 0.00;
    static int numItems = 0;
   
    public YeOldBooksShoppeUI() {
        
    // The gui is initialized and the inventory.txt file is read in and stored
    // in the ArrayList.  If the file is not found, a messagebox pops up.
    // The split method separates the relevant information and each book is 
    // stored in a Book object that is added to the array list.
    
        initComponents();        
        String newBook;
        String [] info;
        double price;
        
        try{
              Scanner books = new Scanner(new File("inventory.txt"));
              while(books.hasNextLine()){
                  newBook = books.nextLine();
                  info = newBook.split(",");
                  price = Double.parseDouble(info[2]);
                  Book addedBook = new Book(info[0], info[1], price);
                  inventory.add(addedBook);                         
              }                                 
                }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "File Not Found.", "Alert", JOptionPane.ERROR_MESSAGE);
        } 
    
    // The initial format of the gui is altered with the following lines of code
    // conforming to project requirements.
    
        confirm.setEnabled(false);
        confirm.setText("Confirm Item #" + (orderNumber + 1));
        view.setEnabled(false);
        finish.setEnabled(false);
        label2.setText("Enter book id for item #" + (orderNumber + 1) + ":");
        label3.setText("Enter quantity for item #" + (orderNumber + 1) + ":");
        label4.setText("Item #" + (orderNumber + 1) + " info:");
        label5.setText("Order subtotal for " + orderNumber + " item(s):");
        getContentPane().setBackground(new java.awt.Color(153, 153, 255));
    }
   
    // NetBeans generated code for the gui is as follows:
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numberItems = new javax.swing.JTextField();
        bookISBN = new javax.swing.JTextField();
        bookQuantity = new javax.swing.JTextField();
        orderInfo = new javax.swing.JTextField();
        newSubtotal = new javax.swing.JTextField();
        label1 = new javax.swing.JLabel();
        label2 = new javax.swing.JLabel();
        label3 = new javax.swing.JLabel();
        label4 = new javax.swing.JLabel();
        label5 = new javax.swing.JLabel();
        process = new javax.swing.JButton();
        view = new javax.swing.JButton();
        confirm = new javax.swing.JButton();
        finish = new javax.swing.JButton();
        newOrder = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ye Olde Book Shoppe");
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        label1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label1.setText("Enter number of items to order:");

        label2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label2.setText("Enter book id for item #1:");

        label3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label3.setText("Enter quantity for item #1:");

        label4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label4.setText("Item #1 info:");

        label5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        label5.setText("Order subtotal for 0 item(s):");

        process.setBackground(new java.awt.Color(0, 51, 153));
        process.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        process.setForeground(new java.awt.Color(255, 255, 255));
        process.setText("Process Item #1");
        process.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processActionPerformed(evt);
            }
        });

        view.setBackground(new java.awt.Color(0, 51, 153));
        view.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        view.setForeground(new java.awt.Color(255, 255, 255));
        view.setText("View Order");
        view.setMaximumSize(new java.awt.Dimension(111, 23));
        view.setMinimumSize(new java.awt.Dimension(111, 23));
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });

        confirm.setBackground(new java.awt.Color(0, 51, 153));
        confirm.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        confirm.setForeground(new java.awt.Color(255, 255, 255));
        confirm.setText("Confirm Item #1");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        finish.setBackground(new java.awt.Color(0, 51, 153));
        finish.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        finish.setForeground(new java.awt.Color(255, 255, 255));
        finish.setText("Finish Order");
        finish.setMaximumSize(new java.awt.Dimension(111, 23));
        finish.setMinimumSize(new java.awt.Dimension(111, 23));
        finish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishActionPerformed(evt);
            }
        });

        newOrder.setBackground(new java.awt.Color(0, 51, 153));
        newOrder.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        newOrder.setForeground(new java.awt.Color(255, 255, 255));
        newOrder.setText("New Order");
        newOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderActionPerformed(evt);
            }
        });

        exit.setBackground(new java.awt.Color(0, 51, 153));
        exit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 255, 255));
        exit.setText("Exit");
        exit.setDefaultCapable(false);
        exit.setMaximumSize(new java.awt.Dimension(111, 23));
        exit.setMinimumSize(new java.awt.Dimension(111, 23));
        exit.setPreferredSize(new java.awt.Dimension(111, 23));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Papyrus", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setText("Ye Old Book Shoppe");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(process, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(115, 115, 115)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(newOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(106, 106, 106)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(bookQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(numberItems, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(orderInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(newSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                                        .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(151, 151, 151))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1)
                    .addComponent(numberItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3)
                    .addComponent(bookQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(process)
                    .addComponent(confirm)
                    .addComponent(view, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(newOrder)
                        .addComponent(finish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Upon clicking the exit button, the application closes.
    
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed
    
    // Upon clicking the process item button, warnings are generated if the 
    // text boxes are not filled in properly.  Information regarding the book to
    // be ordered is extracted and the Book object is returned so the user can
    // see it's information.  All relevent information is added to orders
    // ArrayList, subtotal is updated, any discount applied, and buttons 
    // altered to reflect project requirements.
    
    private void processActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processActionPerformed
       
       String bookCount = numberItems.getText();
       if(bookCount.trim().isEmpty()){
           JOptionPane.showMessageDialog(null, "Please enter the number of items.", "Alert", JOptionPane.ERROR_MESSAGE);
           return;
       }         
       numItems = Integer.parseInt(bookCount);
       if(numItems < 1){
           JOptionPane.showMessageDialog(null, "Please purchase 1 or more items.", "Alert", JOptionPane.ERROR_MESSAGE);
           return;
       }
     
       String bookId = bookISBN.getText();
       if(bookId.trim().isEmpty()){
           JOptionPane.showMessageDialog(null, "Please enter the book ISBN.", "Alert", JOptionPane.ERROR_MESSAGE);
           return;
       }       
       
       Book retBook = findBook(bookId);
       if(retBook == null){
           String message = "Book ID " + bookId + " not on file.\n";
           JOptionPane.showMessageDialog(null, message, "Alert", JOptionPane.ERROR_MESSAGE);
           return;
       }
       
       String quantity = bookQuantity.getText();
       if(quantity.trim().isEmpty())
       {
           JOptionPane.showMessageDialog(null, "Please enter the quantity.", "Alert", JOptionPane.ERROR_MESSAGE);
           return;
       }
       int quantityBooks = Integer.parseInt(quantity);
       
       double percentOff = percentReduction(quantityBooks);
       double totalPrice = calculatePrice(retBook, quantityBooks, percentOff);
       Subtotal += totalPrice;
          
       orders.add(new Order(retBook, quantityBooks, totalPrice, percentOff));
       confirm.setEnabled(true);  
       process.setEnabled(false);
       orderInfo.setText(orders.get(orderNumber).bookOrderString);
       
    }//GEN-LAST:event_processActionPerformed
    
    // Upon clicking the confirm item button, a messagebox appears noting that 
    // the order was accepted.  The gui is set to allow for the another order and
    // the labels/text fields are altered to as per project requirements.
    // If the total number of items order equals the initial number the customer
    // said they would order, then only a finishing the order, creating a new
    // order, or exiting the application is allowed.
    
    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        
        JOptionPane.showMessageDialog(null, "item #" + (orderNumber + 1) + " accepted!", "Message", JOptionPane.INFORMATION_MESSAGE);
        orderNumber++;
        view.setEnabled(true);
        finish.setEnabled(true);
        bookISBN.setText("");
        bookQuantity.setText("");
        DecimalFormat totprice = new DecimalFormat("0.00");
        newSubtotal.setText("$" + totprice.format(Subtotal)); 
        if(orderNumber == numItems)
        {    
            confirm.setEnabled(false);
            process.setEnabled(false);
            label5.setText("Order subtotal for " + orderNumber + " item(s):");
            return;
        }
        process.setEnabled(true);
        process.setText("Process Item #" + (orderNumber + 1));
        confirm.setEnabled(false);
        confirm.setText("Confirm Item #" + (orderNumber + 1));
        label2.setText("Enter book id for item #" + (orderNumber + 1) + ":");
        label3.setText("Enter quantity for item #" + (orderNumber + 1) + ":");
        label4.setText("Item #" + (orderNumber + 1) + " info:");
        label5.setText("Order subtotal for " + orderNumber + " item(s):");
        numberItems.setEnabled(false);          
    }//GEN-LAST:event_confirmActionPerformed
    
    // Upon clicking the view order button, a messagebox appears showing the 
    // user what items have been order with pricing information, etc.
    
    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed
        
        String order = "";
        int i = 1;
        for(Order a : orders)
        {
            order += i + ". " + a.bookOrderString + "\n";
            i++;
        }
        JOptionPane.showMessageDialog(null, order);
    }//GEN-LAST:event_viewActionPerformed
    
    // Upon clicking the finish button, the invoice object is created and a
    // messagebox appears showing the user the final invoice. The transaction
    // is written to the transactions.txt file, and the gui is reset to its 
    // initial state.
    
    private void finishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishActionPerformed
        
        Invoice customerInvoice = new Invoice(orders, Subtotal, orderNumber);
        String message = customerInvoice.finalInvoice();
        JOptionPane.showMessageDialog(null, message);
        try {
            customerInvoice.writeInvoice();
            resetData(); 
        }  
       catch (IOException ex) { 
            JOptionPane.showMessageDialog(null, "File not found and could not be created.", "Alert", JOptionPane.ERROR_MESSAGE); 
       }              
    }//GEN-LAST:event_finishActionPerformed
    
    // Upon clicking the new order button, a messagebox appears alerting the user
    // that all of the information gathered thus far will be erased. If user clicks
    // yes, then all information is cleared, gui is reset, and the user can start a new order.
    
    private void newOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to start a new order?\n"
                + "This will erase your initial order.", "WARNING",
        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
        resetData();   
        }       
    }//GEN-LAST:event_newOrderActionPerformed

    // The gui is initialized and the user can place an order.
    
    public static void main(String args[]) {    
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new YeOldBooksShoppeUI().setVisible(true);
            }
        });         
    }
    
    // Based on the ID entered by the user, method returns the book object 
    // associated with the ID. If it is not found, then null is returned.
    
    public static Book findBook(String isbn) {
        
        Book book = null;
        boolean found = false;
        for(Book b : inventory){
            if(b.isbn != null && b.isbn.equals(isbn)) {
                book = b;
                found = true;
            }
        }
        if (found)
            return book;
        else 
            return null;       
    }

    // Method returns the percentage of the discount that can be applied to the
    // cost of a particular book that the user orders.
    
    public double percentReduction(int quantity)
    {
       if(quantity > 0 && quantity <= 4)
           return 0.00;
       if(quantity >= 5 && quantity <= 9)
           return 0.10;
       if(quantity >= 10 && quantity <= 14)
           return 0.15;
       else
           return 0.20;     
    }
    
    // Method calculates the pretax subtotal for the book being ordered.
    
    public double calculatePrice(Book book, int quantity, double percent)
    {
        double price = book.price * quantity;
        double finalPrice = price - (price * percent);
        return finalPrice;
    }
    
    // Method resets all of the variables and returns the gui to its initial state.
    
    public void resetData()
    {
        orders.clear();
        Subtotal = 0.00;
        orderNumber = 0;
        numItems = 0;
        confirm.setEnabled(false);
        confirm.setText("Confirm Item #" + (orderNumber + 1));
        numberItems.setEnabled(true);
        numberItems.setText("");
        bookISBN.setText("");
        bookQuantity.setText("");
        orderInfo.setText("");
        view.setEnabled(false);
        finish.setEnabled(false);
        newSubtotal.setText("");
        process.setEnabled(true);
        process.setText("Process Item #" + (orderNumber + 1));
        label2.setText("Enter book id for item #" + (orderNumber + 1) + ":");
        label3.setText("Enter quantity for item #" + (orderNumber + 1) + ":");
        label4.setText("Item #" + (orderNumber + 1) + " info:");
        label5.setText("Order subtotal for " + orderNumber + " item(s):"); 
        
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField bookISBN;
    private javax.swing.JTextField bookQuantity;
    private javax.swing.JButton confirm;
    private javax.swing.JButton exit;
    private javax.swing.JButton finish;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel label1;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel label3;
    private javax.swing.JLabel label4;
    private javax.swing.JLabel label5;
    private javax.swing.JButton newOrder;
    private javax.swing.JTextField newSubtotal;
    private javax.swing.JTextField numberItems;
    private javax.swing.JTextField orderInfo;
    private javax.swing.JButton process;
    private javax.swing.JButton view;
    // End of variables declaration//GEN-END:variables
}
