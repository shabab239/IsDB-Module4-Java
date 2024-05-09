package animal;

import animal.Animal;

/**
 *
 * @author Shabab-1281539
 */
public class Dog extends Animal {

    private String breed;

    @Override
    public void makeSound() {
        System.out.println("Woof");
        super.makeSound();
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

}
