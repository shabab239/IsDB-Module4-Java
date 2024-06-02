package model;

import java.util.Date;

/**
 *
 * @author Shabab Ahmed
 */
public class Stock {

    private Long id;
    private String name;
    private Integer quantity;
    private Date date;

    public Stock() {
    }

    public Stock(Long id, String name, Integer quantity, Date date) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
