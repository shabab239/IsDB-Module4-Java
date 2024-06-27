package model;

/**
 *
 * @author Shabab-1281539
 */
public class Employee {

    private Long id;
    private String name;
    private String email;
    private String cell;
    private String address;

    public Employee() {
    }

    public Employee(Long id) {
        this.id = id;
    }

    public Employee(String name, String email, String cell, String address) {
        this.name = name;
        this.email = email;
        this.cell = cell;
        this.address = address;
    }

    public Employee(Long id, String name, String email, String cell, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cell = cell;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name=" + name + ", email=" + email + ", cell=" + cell + ", address=" + address + '}';
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
