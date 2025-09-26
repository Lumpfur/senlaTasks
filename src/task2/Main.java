package task2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyRateService rateService = new CurrencyRateService();

        System.out.println("Конвертер валют с использованием реального курса с Центрального Банка!");

        while (true) {
            System.out.print("Введите код валюты (USD, EUR, etc.) или 'off' для выхода: ");
            String input = scanner.nextLine().toUpperCase();

            if ("OFF".equals(input)) {
                System.out.println("Программа завершена.");
                break;
            }
            processCurrencyConversion(input, scanner, rateService);
        }
        scanner.close();
    }

    private static void processCurrencyConversion(String currencyCode, Scanner scanner, CurrencyRateService rateService) {
        try {
            double rate = rateService.getCurrencyRate(currencyCode);
            performConversion(currencyCode, rate, scanner);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
            offerFixedRateFallback(scanner, rateService);
        }
    }

    private static void performConversion(String resentedValue, double rate, Scanner scanner) {
        System.out.printf("Курс %s: %.2f RUB\n", resentedValue, rate);

        System.out.print("Введите сумму для конвертации: ");
        if (scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            double result = amount * rate;
            System.out.printf("%.2f %s = %.2f RUB\n\n", amount, resentedValue, result);
        } else {
            System.out.println("Ошибка: введите число!");
        }
        scanner.nextLine(); // cleaning
    }
    //using case 'no connection'
    private static void offerFixedRateFallback(Scanner scanner, CurrencyRateService rateService) {
        System.out.println("Извините, произошла ошибка, вы можете использовать список фиксированных валют или написать любое слово для перезапуска цикла, написав любое слово. ");
        System.out.print("Выбирайте (EUR, USD, CNY, GBP, JPY): ");
        String choose = scanner.nextLine().toUpperCase();
        String fixedRateChoose = choose;
        if ("USD".equalsIgnoreCase(fixedRateChoose) | "EUR".equalsIgnoreCase(choose) | "CNY".equalsIgnoreCase(choose) | "GBP".equalsIgnoreCase(choose) | "JPY".equalsIgnoreCase(choose)) {
            double fixedRate = rateService.getFixedRate(fixedRateChoose);

            if (fixedRate > 0) {
                performConversion(fixedRateChoose, fixedRate, scanner);
            } else {
                System.out.println("Валюта " + fixedRateChoose + " не найдена в фиксированных курсах\n");
            }
        } else {
            System.out.println("Продолжаем работу...\n");
        }
    }
}