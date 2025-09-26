package task3;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIAL;

    public String generatePassword(int length) {
        StringBuilder password = new StringBuilder();

        // the password have to use at least one char from the all of them
        password.append(getRandomChar(LOWERCASE));
        password.append(getRandomChar(UPPERCASE));
        password.append(getRandomChar(DIGITS));
        password.append(getRandomChar(SPECIAL));

        // random chars till the end
        for (int i = 4; i < length; i++) {
            password.append(getRandomChar(ALL_CHARS));
        }


        return shuffleString(password.toString());
    }

    private char getRandomChar(String characterSet) {
        int index = (int) (Math.random() * characterSet.length());
        return characterSet.charAt(index);
    }

    // random them all
    private String shuffleString(String input) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = (int) (Math.random() * characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}