# Tyrone Pet Clinic Management System
This Java application simulates a pet clinic system for Tyrone Pet Clinic. It allows users to manage pet records through a console interface, demonstrating object-oriented programming, file persistence, and user-driven interaction.

## Features
- Add, delete, and modify pet records
- Search pets by name or colour
- View all pets with dynamic output from each pet's `speak()` method
- Generate a full clinic report including species count, dominant colour, and average age
- Save and load pet data from text files
- Input validation and error handling

## Object-Oriented Design
- `Pet` (abstract): Base class with shared attributes (name, age, colour, weight) and abstract methods (`speak()`, `getType()`, `toFileString()`)
- `Dog` and `Cat`: Subclasses with breed-specific logic and overridden methods
- `Clinic`: Manages pet storage, search, reporting, and file I/O
- `ConsoleInterface`: Handles user input and menu navigation
- `Main`: Entry point that launches the console interface

## Data Format
Pet records are stored in `PetDetails.txt` using CSV format:

Each line follows this structure:

Type,Name,Age,Colour,Weight,Breed

Example: Rex, 4, Black, 20kg, Labrador

Clinic metadata is stored in `ClinicsDetails.txt`:
Clinic Name: Tyrone Pet Clinic Total Pets: 5


## Sample Report Output
Clinic: Tyrone Pet Clinic Total Pets: 5 Number of Cats: 3 Number of Dogs: 2 Dominant Colour: brown Average Age: 4.60

## How to Run
Compile and run the program using:
javac *.java java Main

The console menu will guide you through all available actions.

## Author
Created by Niamh Quinn as part of coursework at Ulster University, showcasing object-oriented programming, file handling, and user-centered design in Java.







