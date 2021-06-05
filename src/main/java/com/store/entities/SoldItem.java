package com.store.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * SoldItem generated by hbm2java
 */
@Entity
@Table(name="sold_item"
    ,catalog="ecommerce"
)
public class SoldItem  implements java.io.Serializable {


     private int id;
     private Order order;
     private OrderItem orderItem;
     private User user;

    public SoldItem() {
    }

    public SoldItem(int id, Order order, OrderItem orderItem, User user) {
       this.id = id;
       this.order = order;
       this.orderItem = orderItem;
       this.user = user;
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

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="order_item_id", nullable=false)
    public OrderItem getOrderItem() {
        return this.orderItem;
    }
    
    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seller_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }




}

