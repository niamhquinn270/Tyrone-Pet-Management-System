import java.io.*;

public class Clinic {
    private String clinicName;        // Name of the clinic
    private Pet[] pets;               // Array to store pet objects
    private int petCount;             // Current number of pets
    private final int MAX_PETS = 100; // Maximum number of pets allowed

    // Constructor to initialise the clinic and load pet data from file
    public Clinic(String clinicName) {
        this.clinicName = clinicName;
        pets = new Pet[MAX_PETS];
        petCount = 0;
        loadFromFile();
    }

    // Adds a pet to the clinic if there is room
    public boolean addPet(Pet pet) {
        if (petCount >= MAX_PETS)
            return false;
        pets[petCount] = pet;
        petCount++;
        return true;
    }

    // Deletes a pet by name
    public boolean deletePet(String name) {
        int index = findPetIndexByName(name);
        if (index == -1)
            return false;
        for (int i = index; i < petCount - 1; i++) {
            pets[i] = pets[i + 1];
        }
        pets[petCount - 1] = null;
        petCount--;
        return true;
    }

    // Modifies the details of an existing pet
    public boolean modifyPet(String name, Pet updatedPet) {
        int index = findPetIndexByName(name);
        if (index == -1)
            return false;
        pets[index] = updatedPet;
        return true;
    }

    // Displays all registered pets and their information
    public void viewPets() {
        if (petCount == 0) {
            System.out.println("No pets registered in the clinic.");
            return;
        }
        for (int i = 0; i < petCount; i++) {
            System.out.println(pets[i]);
            System.out.println(pets[i].speak());
            System.out.println();
        }
    }

    // Searches for a pet by name and returns the pet if found
    public Pet searchPetByName(String name) {
        int index = findPetIndexByName(name);
        if (index != -1)
            return pets[index];
        return null;
    }

    // Searches and displays pets by colour
    public void searchPetsByColour(String colour) {
        boolean found = false;
        for (int i = 0; i < petCount; i++) {
            if (pets[i].getColour().equalsIgnoreCase(colour)) {
                System.out.println(pets[i]);
                System.out.println(pets[i].speak());
                System.out.println();
                found = true;
            }
        }
        if (!found)
            System.out.println("No pet found with colour " + colour);
    }

    // Generates and prints a report for the clinic
    public void reportClinic() {
        int cats = 0, dogs = 0;
        double totalAge = 0; // Sum of ages for average calculation

        // Arrays to inspect colours and their frequencies.
        String[] distinctColours = new String[100];
        int[] colourFreq = new int[100];
        int distinctCount = 0;

        // Process each pet for, counting type, total age, and colour frequency.
        for (int i = 0; i < petCount; i++) {
            if (pets[i].getType().equalsIgnoreCase("Cat"))
                cats++;
            else if (pets[i].getType().equalsIgnoreCase("Dog"))
                dogs++;
            totalAge += pets[i].getAge();

            String colour = pets[i].getColour().toLowerCase();
            boolean found = false;
            for (int j = 0; j < distinctCount; j++) {
                if (distinctColours[j].equals(colour)) {
                    colourFreq[j]++;
                    found = true;
                    break;
                }
            }
            if (!found) {
                distinctColours[distinctCount] = colour;
                colourFreq[distinctCount] = 1;
                distinctCount++;
            }
        }

        // Find the dominant colour (highest frequency)
        String dominantColour = "None";
        int maxCount = 0;
        for (int i = 0; i < distinctCount; i++) {
            if (colourFreq[i] > maxCount) {
                maxCount = colourFreq[i];
                dominantColour = distinctColours[i];
            }
        }

        // Calculate average age; format average to two decimal places
        double averageAge = petCount > 0 ? totalAge / petCount : 0.0;

        // Print the report details
        System.out.println("Clinic: " + clinicName);
        System.out.println("Total Pets: " + petCount);
        System.out.println("Number of Cats: " + cats);
        System.out.println("Number of Dogs: " + dogs);
        System.out.println("Dominant Colour: " + dominantColour);
        System.out.println("Average Age: " + String.format("%.2f", averageAge));
    }

    // Saves clinic and pet details to files.
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ClinicsDetails.txt"))) {
            bw.write("Clinic Name: " + clinicName);
            bw.newLine();
            bw.write("Total Pets: " + petCount);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving clinic details: " + e.getMessage());
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("PetDetails.txt"))) {
            for (int i = 0; i < petCount; i++) {
                if (!pets[i].getName().isEmpty() && !pets[i].getColour().isEmpty()) {
                    if (pets[i] instanceof Cat) {
                        Cat cat = (Cat) pets[i];
                        if (cat.getBreed().isEmpty())
                            continue;
                    } else if (pets[i] instanceof Dog) {
                        Dog dog = (Dog) pets[i];
                        if (dog.getBreed().isEmpty())
                            continue;
                    }
                    bw.write(pets[i].toFileString());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving pet details: " + e.getMessage());
        }
    }

    // Loads pet details from file into the pets array.
    public void loadFromFile() {
        File file = new File("PetDetails.txt");
        if (!file.exists()) {
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 6)
                    continue;
                String type = parts[0];
                String name = parts[1];
                int age = Integer.parseInt(parts[2]);
                String colour = parts[3];
                double weight = Double.parseDouble(parts[4]);
                String breed = parts[5];
                Pet pet = null;
                if (type.equalsIgnoreCase("Cat"))
                    pet = new Cat(name, age, colour, weight, breed);
                else if (type.equalsIgnoreCase("Dog"))
                    pet = new Dog(name, age, colour, weight, breed);
                if (pet != null && petCount < MAX_PETS)
                    pets[petCount++] = pet;
            }
            System.out.println("Loaded " + petCount + " pet records from file.");
        } catch (IOException e) {
            System.out.println("Error loading pet details: " + e.getMessage());
        }
    }

    // Returns the index of a pet by its name; returns -1 if its not found.
    private int findPetIndexByName(String name) {
        for (int i = 0; i < petCount; i++) {
            if (pets[i].getName().equalsIgnoreCase(name))
                return i;
        }
        return -1;
    }
}



