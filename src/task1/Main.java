package task1;
import java.util.Scanner;

class Main {
    // Список слов для отгадывания
    private static final String[] WORDS = {
            "КОМПЬЮТЕР", "ОКНО", "АЛГОРИТМ", "ИНТЕРНЕТ", "БИБЛИОТЕКА",
            "МАГАЗИН", "НЕБО", "КОМНАТА", "СТОЛИЦА", "ПОГОДА"
    };

    private String secretWord;      // the word
    private char[] guessedLetters;  // guessed chars (с пропусками)
    private int lives;              // amount of lives
    private String usedLetters;     // already used chars

    public Main() {
        // choosing the random X word
        secretWord = WORDS[(int) (Math.random() * WORDS.length)];
        guessedLetters = new char[secretWord.length()];
        lives = 6;
        usedLetters = "";

        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== ИГРА 'ВИСЕЛИЦА' ===");
        System.out.println("Угадайте слово! У вас " + lives + " жизней.");

        while (lives > 0 && !isWordGuessed()) {
            printGameStatus();
            System.out.print("Введите букву: ");
            String input = scanner.nextLine().toUpperCase();

            // checking the input
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Ошибка! Введите одну букву.");
                continue;
            }

            char letter = input.charAt(0);


            if (usedLetters.indexOf(letter) != -1) {
                System.out.println("Вы уже вводили эту букву!");
                continue;
            }

            usedLetters += letter;
            processLetter(letter);
        }

        // the end of the game
        printGameResult();
        scanner.close();
    }

    private void processLetter(char letter) {
        boolean found = false;

        // checking if there is a char in the word
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessedLetters[i] = letter;
                found = true;
            }
        }

        if (found) {
            System.out.println("Правильно! Буква '" + letter + "' есть в слове.");
        } else {
            lives--;
            System.out.println("Неправильно! Буквы '" + letter + "' нет в слове.");
            System.out.println("Осталось жизней: " + lives);
        }
    }

    private boolean isWordGuessed() {
        for (char c : guessedLetters) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private void printGameStatus() {
        System.out.println("\nСлово: " + String.valueOf(guessedLetters));
        System.out.println("Использованные буквы: " + (usedLetters.isEmpty() ? "нет" : usedLetters));
        System.out.println("Жизни: " + lives);
    }

    private void printGameResult() {
        System.out.println("\n=== ИГРА ОКОНЧЕНА ===");
        System.out.println("Загаданное слово: " + secretWord);

        if (isWordGuessed()) {
            System.out.println("Поздравляем! Вы выиграли!");
        } else {
            System.out.println("Вы проиграли! Попробуйте еще раз.");
        }
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.play();
    }
}