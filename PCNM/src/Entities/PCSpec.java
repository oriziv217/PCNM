package Entities;
import java.io.Serializable;

/**
 *
 * @author Sivan Yehuda
 */
public class PCSpec {
    private String ID;
    private String name;
    private String description;
    private int score;
    private boolean status;

    public PCSpec(String ID, String name, String description, int score, boolean status) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.score = score;
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
