package buisness;
// Generated 30.10.2016 16:42:46 by Hibernate Tools 4.3.1



/**
 * LineItem generated by hbm2java
 */
public class LineItem  implements java.io.Serializable {


     private Integer lineitemId;
     private Invoice invoice;
     private int bookId;
     private int quantity;

    public LineItem() {
    }

    public LineItem(Invoice invoice, int bookId, int quantity) {
       this.invoice = invoice;
       this.bookId = bookId;
       this.quantity = quantity;
    }
   
    public Integer getLineitemId() {
        return this.lineitemId;
    }
    
    public void setLineitemId(Integer lineitemId) {
        this.lineitemId = lineitemId;
    }
    public Invoice getInvoice() {
        return this.invoice;
    }
    
    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
    public int getBookId() {
        return this.bookId;
    }
    
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}


