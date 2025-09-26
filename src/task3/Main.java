package task3;
import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        PasswordGenerator generator = new PasswordGenerator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Генератор паролей ===");

        while(true) {

            int length = 0;
            boolean validInput = false;

            // checking the length of the input
            while (!validInput) {
                System.out.print("Введите длину пароля или 'off' для выхода(8-12): ");
                String input = scanner.nextLine();
                if ("OFF".equals(input.toUpperCase())) {
                    System.out.println("Конец программы.");
                    scanner.close();
                    return;
                }

                try {
                    length = Integer.parseInt(input);
                    if (length >= 8 && length <= 12) {
                        validInput = true;
                    } else {
                        System.out.println("Ошибка: длина должна быть от 8 до 12!");
                        continue;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: введите число!");
                    continue;
                }
            }

            String password = generator.generatePassword(length);
            System.out.println("Сгенерированный пароль: " + password);
        }



    }
}