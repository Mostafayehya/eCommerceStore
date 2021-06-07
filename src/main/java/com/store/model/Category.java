package com.store.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Category generated by hbm2java
 */
@Entity
@Table(name="category"
    ,catalog="ecommerce"
    , uniqueConstraints = @UniqueConstraint(columnNames="name") 
)
public class Category  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Set<Subcategory> subcategories = new HashSet<Subcategory>(0);

    public Category() {
    }

	
    public Category(String name) {
        this.name = name;
    }
    public Category(String name, Set<Subcategory> subcategories) {
       this.name = name;
       this.subcategories = subcategories;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="name", unique=true, nullable=false, length=50)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="category")
    public Set<Subcategory> getSubcategories() {
        return this.subcategories;
    }
    
    public void setSubcategories(Set<Subcategory> subcategories) {
        this.subcategories = subcategories;
    }




}


