import java.util.*;

class CardCollection {
    private final Map<String, List<String>> cardMap;

    public CardCollection() {
        cardMap = new HashMap<>();
    }

    public void addCard(String symbol, String cardName) {
        cardMap.computeIfAbsent(symbol, k -> new ArrayList<>()).add(cardName);
    }

    public List<String> getCardsBySymbol(String symbol) {
        return cardMap.getOrDefault(symbol, Collections.emptyList());
    }
}

public class CardCollectionApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection collection = new CardCollection();

        collection.addCard("Hearts", "Ace of Hearts");
        collection.addCard("Hearts", "King of Hearts");
        collection.addCard("Spades", "Queen of Spades");
        collection.addCard("Diamonds", "Jack of Diamonds");
        collection.addCard("Clubs", "10 of Clubs");

        System.out.print("Enter the symbol to search for cards (e.g., Hearts, Spades): ");
        String symbol = scanner.nextLine();

        List<String> cards = collection.getCardsBySymbol(symbol);
        if (cards.isEmpty()) {
            System.out.println("No cards found for the symbol: " + symbol);
        } else {
            System.out.println("Cards for " + symbol + ": " + cards);
        }
    }
}
