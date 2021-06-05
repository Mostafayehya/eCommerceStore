package com.store.db.domain;// default package
// Generated Jun 5, 2021, 12:33:40 AM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CartItem generated by hbm2java
 */
@Entity
@Table(name="cart_item"
    ,catalog="ecommerce"
)
public class CartItem  implements java.io.Serializable {


     private CartItemId id;
     private Product product;
     private User user;
     private int quantity;

    public CartItem() {
    }

    public CartItem(CartItemId id, Product product, User user, int quantity) {
       this.id = id;
       this.product = product;
       this.user = user;
       this.quantity = quantity;
    }
   
     @EmbeddedId

    
    @AttributeOverrides( {
        @AttributeOverride(name="customerId", column=@Column(name="customer_id", nullable=false) ), 
        @AttributeOverride(name="prodId", column=@Column(name="prod_id", nullable=false) ) } )
    public CartItemId getId() {
        return this.id;
    }
    
    public void setId(CartItemId id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="prod_id", nullable=false, insertable=false, updatable=false)
    public Product getProduct() {
        return this.product;
    }
    
    public void setProduct(Product product) {
        this.product = product;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false, insertable=false, updatable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="quantity", nullable=false)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }




}

