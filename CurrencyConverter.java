import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONObject;
public class CurrencyConverter {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Step 1: Get user input
            System.out.print("Enter amount: ");
            double amount = sc.nextDouble();

            System.out.print("Enter base currency (e.g., USD, EUR, INR): ");
            String baseCurrency = sc.next().toUpperCase();

            System.out.print("Enter target currency (e.g., USD, EUR, INR): ");
            String targetCurrency = sc.next().toUpperCase();

            // Step 2: Fetch exchange rate from API
            String urlStr = "https://api.exchangerate.host/latest?base=" 
                             + baseCurrency + "&symbols=" + targetCurrency;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            Scanner apiScanner = new Scanner(conn.getInputStream());
            StringBuilder response = new StringBuilder();
            while (apiScanner.hasNext()) {
                response.append(apiScanner.nextLine());
            }
            apiScanner.close();

            // Step 3: Parse JSON response
            JSONObject jsonObj = new JSONObject(response.toString());
            double rate = jsonObj.getJSONObject("rates").getDouble(targetCurrency);

            // Step 4: Convert and display
            double convertedAmount = amount * rate;
            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}

