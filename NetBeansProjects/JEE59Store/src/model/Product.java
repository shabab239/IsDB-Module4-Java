package model;

/**
 *
 * @author Shabab Ahmed
 */
public class Product {

    private Long id;
    private String name;
    private Double unitPrice;
    private Integer quantity;
    private Double salesPrice;

    public Product() {
    }

    public Product(Long id, String name, Double unitPrice, Integer quantity, Double salesPrice) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.salesPrice = salesPrice;
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

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Double salesPrice) {
        this.salesPrice = salesPrice;
    }

}
