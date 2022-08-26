/**
 * HashMap application in wildlife sanctuary.
 * Name: Ruoqing Song
 * ID: A17191150
 * Email: r2song@ucsd.edu
 * Sources used: Zybooks, Lecture slides, Lecture handouts
 *
 * This file contains the methods that help the sanctuary to keep track
 * of their animal numbers and species.
 */
import java.util.HashMap;
import java.util.Set;

/**
 * A class that contains method to keep track of animals.
 * Instance Variables:
 * maxAnimals - The maximum number of animals that the sanctuary can support
 * maxSpecies - The maximum number of species that the sanctuary can support
 */
public class Sanctuary {
    
    HashMap<String, Integer> sanctuary;
    int maxAnimals;
    int maxSpecies;

    /**
     * Constructor to initialize the HashMap with no elements
     * @param maxAnimals Max animal capacity
     * @param maxSpecies Max animal species
     */
    public Sanctuary(int maxAnimals, int maxSpecies) {
    if (maxAnimals < 0 || maxSpecies < 0){
        String errorMsg = "Argument is less than 0";
        throw new IllegalArgumentException(errorMsg);
    }
    sanctuary = new HashMap<String, Integer>(); 
    this.maxAnimals = maxAnimals;
    this.maxSpecies = maxSpecies;
    }

    /**
     * Return the number of animals of the given species
     * @param species the species of the animal
     * @return the number of the animal
     */
    public int getNum(String species) {
    if (species == null){
        String errorMsg = "Argument is null";
        throw new IllegalArgumentException(errorMsg);
    }
    if (sanctuary.containsKey(species) == true){
        return sanctuary.get(species);
    }
    return 0;
    }

    /**
     * Return the tital number of animals
     * @return the total number of the animal
     */
    public int getTotalAnimals() {
    int totalAnimals = 0;
    for (int i : sanctuary.values()){
        totalAnimals += i;
    }
    return totalAnimals;
    }

    /**
     * Return the total species of animals
     * @return the total species of animals
     */
    public int getTotalSpecies() {
        return sanctuary.size();
    }

    /**
     * Update the number after rescue animals
     * @param species the species of the animal
     * @param num the number of the animal
     * @return the number of the animal that exceed the max capacity
     */
    public int rescue(String species, int num) {
    if (num == 0){
        String errorMsg = "Argument is 0";
        throw new IllegalArgumentException(errorMsg);
    }
    if (species == null){
        String errorMsg2 = "Argument is null";
        throw new IllegalArgumentException(errorMsg2);
    }

    //Can only add when not exceed maxAnimals
    if (this.getTotalAnimals() < maxAnimals){
        int available = maxAnimals - this.getTotalAnimals();
        //when add less than available seats
        if (num <= available){
            //when adding to an existing species
            if (sanctuary.containsKey(species) == true){
                int numAdd = getNum(species) + num;
                sanctuary.put(species,numAdd);
            }
            //when adding an new species
            if (sanctuary.containsKey(species) == false){
                //only when total species not exceed the max
                if (this.getTotalSpecies() < maxSpecies){
                    sanctuary.put(species,num);
                }
            }
        }
        //when add more than available seats
        if (num > available){
            //when adding to an existing species
            if (sanctuary.containsKey(species) == true){
                int numAdd = getNum(species) + available;
                sanctuary.put(species,numAdd); 
            }
            //when adding an new species
            if (sanctuary.containsKey(species) == false){
                //only when total species not exceed the max
                if (this.getTotalSpecies() < maxSpecies){
                    sanctuary.put(species,available);
                }
            }
        int rest = num - available;
        return rest; 
        }
    }
    return 0;
    }

    /**
     * Remove num animals of species from the sanctuary
     * @param species the species of the animal
     * @param num the number of the animal
     */
    public void release(String species, int num) {
        if (num <= 0){
            String errorMsg1 = "Argument is smaller than 0";
            throw new IllegalArgumentException(errorMsg1);
        }
        if (num > this.getNum(species)){
            String errorMsg2 = "Argument is bigger than total number";
            throw new IllegalArgumentException(errorMsg2);
        }
        if (species == null ) {
            String errorMsg3 = "Argument is null";
            throw new IllegalArgumentException(errorMsg3);
        }
        if (sanctuary.containsKey(species) == false){
            String errorMsg4 = "Argument does not exist";
            throw new IllegalArgumentException(errorMsg4);
        }
        int updatedNum = getNum(species) - num;
        sanctuary.put(species, updatedNum);
        if (updatedNum == 0){
            sanctuary.remove(species);
        }
    }
}
