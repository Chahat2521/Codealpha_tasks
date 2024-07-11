import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }
}

public class StockTradingPlatform {
    private Map<String, Stock> marketData;
    private Map<String, Integer> portfolio;
    private double cash;

    public StockTradingPlatform() {
        marketData = new HashMap<>();
        portfolio = new HashMap<>();
        cash = 10000.0; // initial cash

        // sample market data
        marketData.put("APPL", new Stock("AAPL", 150.0));
        marketData.put("GOOG", new Stock("GOOG", 2500.0));
        marketData.put("AMAZ", new Stock("AMAZ", 200.0));
    }

    public void displayMarketData() {
        System.out.println("Market Data:");
        for (Stock stock : marketData.values()) {
            System.out.println(stock.getSymbol() + ": " + stock.getPrice());
        }
    }

    public void buyStock(String symbol, int quantity) {
        if (marketData.containsKey(symbol)) {
            Stock stock = marketData.get(symbol);
            double totalCost = stock.getPrice() * quantity;
            if (totalCost <= cash) {
                cash -= totalCost;
                portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + quantity);
                System.out.println("Bought " + quantity + " shares of " + symbol);
            } else {
                System.out.println("Insufficient cash");
            }
        } else {
            System.out.println("Stock not found");
        }
    }

    public void sellStock(String symbol, int quantity) {
        if (portfolio.containsKey(symbol)) {
            int currentQuantity = portfolio.get(symbol);
            if (currentQuantity >= quantity) {
                Stock stock = marketData.get(symbol);
                double totalRevenue = stock.getPrice() * quantity;
                cash += totalRevenue;
                portfolio.put(symbol, currentQuantity - quantity);
                System.out.println("Sold " + quantity + " shares of " + symbol);
            } else {
                System.out.println("Not enough shares to sell");
            }
        } else {
            System.out.println("Stock not in portfolio");
        }
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            Stock stock = marketData.get(entry.getKey());
            System.out.println(entry.getKey() + ": " + entry.getValue() + " shares, value: "
                    + stock.getPrice() * entry.getValue());
        }
        System.out.println("Cash: " + cash);
    }

    public static void main(String[] args) {
        StockTradingPlatform platform = new StockTradingPlatform();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. Display Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    platform.displayMarketData();
                    break;
                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = scanner.next();
                    System.out.print("Enter quantity: ");
                    int buyQuantity = scanner.nextInt();
                    platform.buyStock(buySymbol, buyQuantity);
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = scanner.next();
                    System.out.print("Enter quantity: ");
                    int sellQuantity = scanner.nextInt();
                    platform.sellStock(sellSymbol, sellQuantity);
                    break;
                case 4:
                    platform.displayPortfolio();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}