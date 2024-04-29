
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
        double numberOrChar = Math.random();

        if (numberOrChar < 0.3) {
            int asciiValueNumber = (int) (Math.random() * (57 - 48 + 1)) + 48;
            return (char) asciiValueNumber;
        }
        if (numberOrChar > 0.4 && numberOrChar < 0.7) {
            int asciiValueUppercaseChar = (int) (Math.random() * (90 - 65 + 1)) + 65;
            return (char) asciiValueUppercaseChar;
        }
        int asciiValueLowercaseChar = (int) (Math.random() * (122 - 97 + 1)) + 97;
        return (char) asciiValueLowercaseChar;
    }

}
