package buisness;
// Generated 30.10.2016 16:42:46 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Invoice generated by hbm2java
 */
public class Invoice  implements java.io.Serializable {


     private Integer invoiceId;
     private User user;
     private Date invoiceDate;
     private Integer totalAmount;
     private String isProcessed;
     private String delivery;
     private String address;
     private Integer deliveryCost;
     private Set<LineItem> lineItems = new HashSet<LineItem>(0);

    public Invoice() {
    }

	
    public Invoice(User user, Date invoiceDate, String address, Integer totalAmount) {
        this.user = user;
        this.invoiceDate = invoiceDate;
        this.totalAmount = totalAmount;
        this.address = address;
    }
    
    public Invoice(User user, Date invoiceDate, String address, Integer totalAmount, String isProcessed, String delivery, Integer deliveryCost, Set<LineItem> lineItems) {
       this.user = user;
       this.invoiceDate = invoiceDate;
       this.totalAmount = totalAmount;
       this.isProcessed = isProcessed;
       this.delivery = delivery;
       this.deliveryCost = deliveryCost;
       this.lineItems = lineItems;
       this.address = address;
    }
   
    public Integer getInvoiceId() {
        return this.invoiceId;
    }
    
    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Date getInvoiceDate() {
        return this.invoiceDate;
    }
    
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
    public Integer getTotalAmount() {
        return this.totalAmount;
    }
    
    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getIsProcessed() {
        return this.isProcessed;
    }
    
    public void setIsProcessed(String isProcessed) {
        this.isProcessed = isProcessed;
    }
    public String getDelivery() {
        return this.delivery;
    }
    
     public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
    public Integer getDeliveryCost() {
        return this.deliveryCost;
    }
    
    public void setDeliveryCost(Integer deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
    public Set<LineItem> getLineItems() {
        return this.lineItems;
    }
    
    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }




}


