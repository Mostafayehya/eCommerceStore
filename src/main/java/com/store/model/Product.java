package com.store.model;// default package
// Generated Jun 5, 2021, 12:33:40 AM by Hibernate Tools 6.0.0.Alpha2


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Product generated by hbm2java
 */
@Entity
@Table(name="product"
    ,catalog="ecommerce"
)
public class Product  implements java.io.Serializable {

     private Integer id;
     private SubCategory subCategory;
     private User user;
     private String name;
     private String description;
     private int quantity;
     private String img;
     private double price;
     private boolean isOnSale;
     private int subcategoryId;
     private Set<ProdImages> prodImageses = new HashSet<ProdImages>(0);
     private Set<OrderItem> orderItems = new HashSet<OrderItem>(0);
     private Set<Review> reviews = new HashSet<Review>(0);
     private Set<CartItem> cartItems = new HashSet<CartItem>(0);

    public Product() {
    }

	
    public Product(SubCategory subCategory, User user, String name, String description, int quantity, String img, double price, boolean isOnSale, int subcategoryId) {
        this.subCategory = subCategory;
        this.user = user;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
        this.price = price;
        this.isOnSale = isOnSale;
        this.subcategoryId = subcategoryId;
    }
    public Product(SubCategory subCategory, User user, String name, String description, int quantity, String img, double price, boolean isOnSale, int subcategoryId, Set<ProdImages> prodImageses, Set<OrderItem> orderItems, Set<Review> reviews, Set<CartItem> cartItems) {
       this.subCategory = subCategory;
       this.user = user;
       this.name = name;
       this.description = description;
       this.quantity = quantity;
       this.img = img;
       this.price = price;
       this.isOnSale = isOnSale;
       this.subcategoryId = subcategoryId;
       this.prodImageses = prodImageses;
       this.orderItems = orderItems;
       this.reviews = reviews;
       this.cartItems = cartItems;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sub_category_id", nullable=false)
    public SubCategory getSubCategory() {
        return this.subCategory;
    }
    
    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="seller_id", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="name", nullable=false, length=256)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="description", nullable=false, length=1000)
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    
    @Column(name="quantity", nullable=false)
    public int getQuantity() {
        return this.quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    @Column(name="img", nullable=false, length=256)
    public String getImg() {
        return this.img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }

    
    @Column(name="price", nullable=false, precision=22, scale=0)
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }

    
    @Column(name="isOnSale", nullable=false)
    public boolean isIsOnSale() {
        return this.isOnSale;
    }
    
    public void setIsOnSale(boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    
    @Column(name="subcategory_id", nullable=false)
    public int getSubcategoryId() {
        return this.subcategoryId;
    }
    
    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    public Set<ProdImages> getProdImageses() {
        return this.prodImageses;
    }
    
    public void setProdImageses(Set<ProdImages> prodImageses) {
        this.prodImageses = prodImageses;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    public Set<OrderItem> getOrderItems() {
        return this.orderItems;
    }
    
    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    public Set<Review> getReviews() {
        return this.reviews;
    }
    
    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="product")
    public Set<CartItem> getCartItems() {
        return this.cartItems;
    }
    
    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }




}


