package Entities;
import java.io.Serializable;

/**
 * This class represents a record in PC-Specification table
 * @author Ori Ziv
 * @author Sivan Yehuda
 */
public class PCSpec implements Serializable {
    private int ID;
    private String name;
    private String description;
    private int warranty;
    private float price;
    private int score;
    private Status status;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWarranty() {
        return warranty;
    }

    public void setWarranty(int warranty) {
        this.warranty = warranty;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PCSpec() {
        ID = 0;
        name = "";
        description = "";
        warranty = 0;
        price = 0;
        score = 0;
        status = Status.Error;
    }

    public PCSpec(String name, String description, int warranty, float price, int score, Status status) {
        this();
        this.name = name;
        this.description = description;
        this.warranty = warranty;
        this.price = price;
        this.score = score;
        this.status = status;
    }

    public PCSpec(int ID, String name, String description, int warranty, float price, int score, Status status) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.warranty = warranty;
        this.price = price;
        this.score = score;
        this.status = status;
    }

    @Override
    public String toString() {
        String sts = "Error";
        
        switch (this.status) {
            case ENABLE:
                sts = "Enabled";
                break;
            case DISABLE:
                sts = "Disabled";
                break;
            case SUSPENDED:
                sts = "Suspended";
                break;
        }
        
        return ID + "," + name + "," + description + "," + warranty + "," + price + "," + score + "," + sts;
    }
}
