package com.store.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Payment generated by hbm2java
 */
@Entity
@Table(name="payment"
    ,catalog="ecommerce"
)
public class Payment  implements java.io.Serializable {


     private int id;
     private Order order;
     private double amount;
     private String method;
     private String txCode;

    public Payment() {
    }

    public Payment(int id, Order order, double amount, String method, String txCode) {
       this.id = id;
       this.order = order;
       this.amount = amount;
       this.method = method;
       this.txCode = txCode;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    public Order getOrder() {
        return this.order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }

    
    @Column(name="amount", nullable=false, precision=22, scale=0)
    public double getAmount() {
        return this.amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }

    
    @Column(name="method", nullable=false, length=45)
    public String getMethod() {
        return this.method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }

    
    @Column(name="tx_code", nullable=false, length=200)
    public String getTxCode() {
        return this.txCode;
    }
    
    public void setTxCode(String txCode) {
        this.txCode = txCode;
    }




}


