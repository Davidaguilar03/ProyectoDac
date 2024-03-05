package co.edu.uptc.services;

import co.edu.uptc.models.*;

import java.util.ArrayList;

public class GenerateStrings {
    private PropiertiesService p;
    private int numberOfNames;
    private int numberOfLastNames;
    private String separator;
    private ArrayList<Person> persons;
    private ArrayList<String> lastNames;
    private ArrayList<String> stringsCads;
    private ArrayList<String> currentStringCads;
    private String namesPath;
    private String lastNamesPath;;

    // Constructor
    public GenerateStrings() {
        p = new PropiertiesService();
        persons = new ArrayList<>();
        lastNames = new ArrayList<>();
        stringsCads = new ArrayList<>();
        currentStringCads = new ArrayList<>();
        separator = ",";
    }

    public ArrayList<String> generateAllCombinations() {
        generateNamesCombinations();
        return stringsCads;
    }

    private void generateNamesCombinations() {
        if (currentStringCads.size() == numberOfNames) {
            generateLastNamesCombinations(0);
            return;
        }

        for (Person person : persons) {
            if (!currentStringCads.contains(person.getName()) && arePersonsCompatible(person)) {
                currentStringCads.add(person.getName()+separator);
                generateNamesCombinations();
                currentStringCads.remove(currentStringCads.size() - 1);
            }
        }
    }

    private void generateLastNamesCombinations(int currentIndex) {
        if (currentIndex == numberOfLastNames) {
            StringBuilder combination = new StringBuilder();
            for (String name : currentStringCads) {
                combination.append(name);
            }
            stringsCads.add(combination.toString().trim());
            return;
        }

        for (String apellido : lastNames) {
            currentStringCads.add(apellido+separator);
            generateLastNamesCombinations(currentIndex + 1);
            currentStringCads.remove(currentStringCads.size() - 1);
        }
    }

    private boolean arePersonsCompatible(Person newPerson) {
        for (String name : currentStringCads) {
            Person existingPerson = findPersonByName(name);
            if (existingPerson != null && !existingPerson.getGender().equals(newPerson.getGender())) {
                return false;
            }
        }
        return true;
    }

    private Person findPersonByName(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    // Persistencia

    public void saveData(ArrayList<String> auxList) {
        Myfile file = new Myfile(p.readProperties("outputPath"));
        file.open('w');
        for (String string : auxList) {
            file.save(string);
        }
        file.close();
    }

    // Lee los archivos y los almacena en local
    public void readData() {
        readNamesData();
        readLastNamesData();
    }

    // Lee el archivo de Nombres y lo almacena en el local
    private void readNamesData() {
        Myfile f = new Myfile(namesPath);
        f.open('r');
        ArrayList<String> auxPersons = f.read();
        for (String string : auxPersons) {
            String[] cad = string.split(",");
            Person auxPerson = new Person(cad[0], cad[1]);
            persons.add(auxPerson);
        }

    }

    // Lee el archivo de Apellidos y lo almacena en local
    private void readLastNamesData() {
        Myfile file = new Myfile(lastNamesPath);
        file.open('r');
        lastNames = file.read();
    }

    // Gets y Sets

    public int getNumberOfNames() {
        return numberOfNames;
    }

    public void setNumberOfNames(int numberOfNames) {
        this.numberOfNames = numberOfNames;
    }

    public int getNumberOfLastNames() {
        return numberOfLastNames;
    }

    public void setNumberOfLastNames(int numberOfLastNames) {
        this.numberOfLastNames = numberOfLastNames;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    public String getNamesPath() {
        return namesPath;
    }

    public void setNamesPath(String namesPath) {
        this.namesPath = namesPath;
    }

    public String getLastNamesPath() {
        return lastNamesPath;
    }

    public void setLastNamesPath(String lastNamesPath) {
        this.lastNamesPath = lastNamesPath;
    }


    
}
