
import java.util.Random;

/**
 *
 * @author Shabab (1281539)
 */
public class RandomCharGenerate {

    public static void main(String[] args) {
        String randomPassword = "";

        for (int i = 0; i < 7; i++) {
            if (i == 3) {
                randomPassword += "-";
                continue;
            }
            randomPassword += getRandomCharOrNumber();
        }

        System.out.println(randomPassword);

    }

    public static char getRandomCharOrNumber() {
        Random random = new Random();
        int choice = random.nextInt(1, 4);

        char result = ' ';
        switch (choice) {
            case 1:
                int asciiValueNumber = random.nextInt(48, 58);
                result = (char) asciiValueNumber;
                break;
            case 2:
                int asciiValueUppercase = random.nextInt(65, 91);
                result = (char) asciiValueUppercase;
                break;
            case 3:
                int asciiValueLowercase = random.nextInt(97, 123);
                result = (char) asciiValueLowercase;
                break;
        }
        return result;
    }

}
