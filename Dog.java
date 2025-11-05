// Dog class extends Pet, adding breed as a unique attribute.
public class Dog extends Pet {
    private String breed; // Stores the breed of the dog

    // Constructor initialises all attributes including the breed.
    public Dog(String name, int age, String colour, double weight, String breed) {
        super(name, age, colour, weight);
        this.breed = breed;
    }

    // Getter method for breed.
    public String getBreed() {
        return breed;
    }

    // Setter method for breed.
    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Overridden speak method to return dog sound
    @Override
    public String speak() {
        return "Woof! I am " + getName() + ", a " + getAge() + " year old " + breed;
    }

    // Returns the pet as dog
    @Override
    public String getType() {
        return "Dog";
    }

    // Formats the dog's information for storage.
    @Override
    public String toFileString() {
        return "Dog," + getName() + "," + getAge() + "," + getColour() + "," + getWeight() + "," + breed;
    }

    // Returns string including breed and pet type.
    @Override
    public String toString() {
        return super.toString() + ", Breed: " + breed + ", Type: Dog";
    }
}
