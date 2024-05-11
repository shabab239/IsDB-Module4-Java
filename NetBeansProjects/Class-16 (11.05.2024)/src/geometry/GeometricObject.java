package geometry;

import java.util.Date;

/**
 *
 * @author Shabab-1281539
 */
public abstract class GeometricObject {

    private String color = "White";
    private boolean filled;
    private Date dateCreated;

    public GeometricObject() {
    }

    public GeometricObject(boolean filled, Date dateCreated) {
        this.filled = filled;
        this.dateCreated = dateCreated;
    }
    
    public abstract double getArea();
    
    public abstract double getPerimeter();

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
    
    

}
