package model;

import java.util.Date;

/**
 *
 * @author Shabab Ahmed
 */
public class Sales {

    private Long id;
    private String name;
    private Double salesPrice;
    private Integer quantity;
    private Double totalPrice;
    private Date date;

    public Sales() {
    }

    public Sales(Long id, String name, Double salesPrice, Integer quantity, Double totalPrice, Date date) {
        this.id = id;
        this.name = name;
        this.salesPrice = salesPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
