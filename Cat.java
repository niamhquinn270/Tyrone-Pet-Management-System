// Cat class extends Pet and adds breed
public class Cat extends Pet {
    private String breed; // stores the cat's breed

    public Cat(String name, int age, String colour, double weight, String breed) {
        super(name, age, colour, weight);
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    // Returns the cat's sound and information
    @Override
    public String speak() {
        return "Meow! I am " + getName() + ", a " + getAge() + " year old " + breed;
    }

    // Specifies the type of pet.
    @Override
    public String getType() {
        return "Cat";
    }

    // Formats the cat's details for file storage.
    @Override
    public String toFileString() {
        return "Cat," + getName() + "," + getAge() + "," + getColour() + "," + getWeight() + "," + breed;
    }

    // Returns a string representation with other details.
    @Override
    public String toString() {
        return super.toString() + ", Breed: " + breed + ", Type: Cat";
    }
}

