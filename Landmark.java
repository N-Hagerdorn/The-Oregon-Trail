package sample;

/**
 * Landmark
 * The parent class for Obstacle and Fort
 *
 * @author N-Hagerdorn
 * @since 3/30/2020
 */
public class Landmark {

    private String name;
    private int location;

    public Landmark(String name, int location){
        this.name = name;
        this.location = location;
    }
    /**
     *
     * @return the location as an int of miles
     */
    public int getLocation() {
        return location;
    }

    /**
     *
     * @return the string name.
     */
    public String getName() {
        return name;
    }

    private void setLocation(int location){
        this.location = location;
    }
}
