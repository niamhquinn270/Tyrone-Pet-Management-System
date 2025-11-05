// Abstract Pet class representing common attributes for all pets.
public abstract class Pet {
    private String name;    // Pet name
    private int age;        // Pet age
    private String colour;  // Pet colour
    private double weight;  // Pet weight (kg)

    // Constructor initialises all the common pet attributes.
    public Pet(String name, int age, String colour, double weight) {
        this.name = name;
        this.age = age;
        this.colour = colour;
        this.weight = weight;
    }

    // Getter and setter methods for each attribute.
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getColour() { return colour; }
    public void setColour(String colour) { this.colour = colour; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    // Abstract methods that must be in the subclasses.
    public abstract String speak();
    public abstract String getType();
    public abstract String toFileString();

    // Returns a string of pet information.
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + " years, Colour: " + colour + ", Weight: " + weight + " kg";
    }
}
