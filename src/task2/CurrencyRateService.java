package task2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class CurrencyRateService {

    public double getCurrencyRate(String currencyCode) throws Exception {
        String apiUrl = "https://www.cbr-xml-daily.ru/daily_json.js";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            return parseRateFromJson(response.toString(), currencyCode);
        }
    }

    // the most interesting one
    private double parseRateFromJson(String json, String currencyCode) throws Exception {
        String search = "\"" + currencyCode + "\":"; //form of the value-pull
        int currencyIndex = json.indexOf(search);
        //searching for the value
        if (currencyIndex == -1) {
            throw new Exception("Валюта не найдена");
        }
        //searching the value
        int valueIndex = json.indexOf("Value", currencyIndex); //searing for the word 'Value'
        int colonIndex = json.indexOf(":", valueIndex); //after the 'Value' we search for the ':'
        int commaIndex = json.indexOf(",", colonIndex); //and finally we search for the ',' after the ':'

        // just in case there is no founded one
        if (colonIndex == -1 || commaIndex == -1) {
            throw new Exception("Ошибка формата данных");
        }

        // deleting the space between ':' and ','
        String valueStr = json.substring(colonIndex + 1, commaIndex).trim();
        return Double.parseDouble(valueStr); //from string to double
    }
    // case 'not connection'
    public double getFixedRate(String fixedRateChoose) {
        return switch (fixedRateChoose) {
            case "USD" -> 90.15;
            case "EUR" -> 97.85;
            case "CNY" -> 11.91;
            case "GBP" -> 113.45;
            case "JPY" -> 0.58;
            default -> 0;
        };
    }
}