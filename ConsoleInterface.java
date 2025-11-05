import java.util.Scanner;

public class ConsoleInterface {
    private Clinic clinic;    // Manages clinic
    private Scanner scanner;  // Reads user input

    // Constructor creates a fixed-name clinic and initialises the scanner.
    public ConsoleInterface() {
        scanner = new Scanner(System.in);
        clinic = new Clinic("Tyrone Pet Clinic");
    }

    // Displays the main menu and processes all user choices.
    public void showMainMenu() {
        int choice = 0;
        while (choice != 8) {
            System.out.println("\n-*-*- Tyrone Pet Clinic Management -*-*-");
            System.out.println("1. Add a pet");
            System.out.println("2. Delete a pet");
            System.out.println("3. Modify pet details");
            System.out.println("4. View all pets");
            System.out.println("5. View a full clinic report");
            System.out.println("6. Search for a pet by name");
            System.out.println("7. Search for a pet by colour");
            System.out.println("8. Exit the system");
            System.out.print("Enter your choice (number): ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please try again! Enter a number.");
                continue;
            }

            switch (choice) {
                case 1: addPet(); break;
                case 2: deletePet(); break;
                case 3: modifyPet(); break;
                case 4: clinic.viewPets(); break;
                case 5: clinic.reportClinic(); break;
                case 6: searchPetByName(); break;
                case 7: searchPetsByColour(); break;
                case 8:
                    clinic.saveToFile();
                    System.out.println("Exiting the system. All pet data is saved.");
                    break;
                default:
                    System.out.println("Error! Please try again.");
            }
        }
    }

    // Prompts the user to add a new pet and validates input.
    private void addPet() {
        System.out.println("\nSelect the pet type:");
        System.out.println("1. Cat");
        System.out.println("2. Dog");
        System.out.print("Enter your choice: ");

        int typeChoice;
        try {
            typeChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Please try again! Enter a number.");
            return;
        }

        System.out.print("Enter pet name: ");
        String name = scanner.nextLine();
        System.out.print("Enter pet age (years): ");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error, input is not valid!");
            return;
        }
        System.out.print("Enter pet colour: ");
        String colour = scanner.nextLine();
        System.out.print("Enter pet weight (kg): ");
        double weight;
        try {
            weight = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error! Input is not valid!");
            return;
        }
        System.out.print("Enter pet breed: ");
        String breed = scanner.nextLine();

        if (name.isEmpty() || colour.isEmpty() || breed.isEmpty()) {
            System.out.println("Error! Name, colour and breed cannot be empty. Pet not added :(");
            return;
        }

        Pet pet;
        if (typeChoice == 1) {
            pet = new Cat(name, age, colour, weight, breed);
        } else if (typeChoice == 2) {
            pet = new Dog(name, age, colour, weight, breed);
        } else {
            System.out.println("Error! Please enter 1 or 2.");
            return;
        }

        if (clinic.addPet(pet))
            System.out.println("Pet added successfully.");
        else
            System.out.println("Clinic is full. Capacity has been reached.");
    }

    // Deletes a pet based on the name provided by the user.
    private void deletePet() {
        System.out.print("Enter the name of the pet to delete: ");
        String name = scanner.nextLine();
        if (clinic.deletePet(name))
            System.out.println("Pet deleted successfully.");
        else
            System.out.println("No pet found with that name. Check your spelling!");
    }

    // Modifies an existing pet's information based on user input.
    private void modifyPet() {
        System.out.print("Enter the name of the pet to modify: ");
        String name = scanner.nextLine();
        Pet pet = clinic.searchPetByName(name);
        if (pet == null) {
            System.out.println("No pet was found with that name.");
            return;
        }
        System.out.print("Enter new name (or press Enter to skip): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty())
            pet.setName(newName);

        System.out.print("Enter new age (or press Enter to skip): ");
        String ageInput = scanner.nextLine();
        if (!ageInput.isEmpty()) {
            try {
                int newAge = Integer.parseInt(ageInput);
                pet.setAge(newAge);
            } catch (NumberFormatException e) {
                System.out.println("Age input not valid; skipping step.");
            }
        }

        System.out.print("Enter new colour (or press Enter to skip): ");
        String newColour = scanner.nextLine();
        if (!newColour.isEmpty())
            pet.setColour(newColour);

        System.out.print("Enter new weight (or press Enter to skip): ");
        String weightInput = scanner.nextLine();
        if (!weightInput.isEmpty()) {
            try {
                double newWeight = Double.parseDouble(weightInput);
                pet.setWeight(newWeight);
            } catch (NumberFormatException e) {
                System.out.println("Weight input not valid; skipping step.");
            }
        }

        // Updates the breed if the pet is Cat or Dog.
        if (pet instanceof Cat) {
            Cat cat = (Cat) pet;
            System.out.print("Enter new breed (or press Enter to skip): ");
            String newBreed = scanner.nextLine();
            if (!newBreed.isEmpty())
                cat.setBreed(newBreed);
        } else if (pet instanceof Dog) {
            Dog dog = (Dog) pet;
            System.out.print("Enter new breed (or press Enter to skip): ");
            String newBreed = scanner.nextLine();
            if (!newBreed.isEmpty())
                dog.setBreed(newBreed);
        }
        System.out.println("Pet details updated successfully.");
    }

    // Searches for a pet by name and displays its information
    private void searchPetByName() {
        System.out.print("Enter a pet name to search: ");
        String name = scanner.nextLine();
        Pet pet = clinic.searchPetByName(name);
        if (pet != null) {
            System.out.println(pet);
            System.out.println(pet.speak());
        } else {
            System.out.println("No pet found with that name :(");
        }
    }

    // Searches for pets by colour.
    private void searchPetsByColour() {
        System.out.print("Enter pet colour to search: ");
        String colour = scanner.nextLine();
        clinic.searchPetsByColour(colour);
    }
}
