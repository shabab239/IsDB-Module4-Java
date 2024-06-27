package model;

/**
 *
 * @author Shabab-1281539
 */
public class User {

    private Long id;
    private String name;
    private String email;
    private String address;
    private String cell;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String name, String email, String address, String cell) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.cell = cell;
    }

    public User(String name, String email, String address, String cell) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + ", cell=" + cell + '}';
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

}
