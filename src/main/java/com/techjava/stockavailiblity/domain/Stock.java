package com.techjava.stockavailiblity.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="stock_data",schema = "dbo")
public class Stock implements  java.io.Serializable {

    private static final long serialVersionUID = 2809537289460877771L;

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id",unique = true,nullable = false)
    private Long id;

    /** The stockId . */
    @Column(name="stockId")
    private String stockId;

    /** The productId. */
    @Column(name="productId",nullable = false)
    private String productId;

    /** The quantity.*/
    @Column(name="quantity",nullable = false)
    private int quantity;

    /** The modifiedDate. */
    private Date modifiedDate;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
