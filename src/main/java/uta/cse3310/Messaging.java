import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

public class Messaging {
    private Set<String> messages;
    private Set<String> players;

    public Messaging() {
        this.messages = new HashSet<>();
        this.players = new HashSet<>();
    }

    public void addMessage(String message, String player) {
        if (validateMessage(message)) {
            messages.add(message);
            players.add(player);
            System.out.println("Message sent: " + message + " from " + player);
        } else {
            System.out.println("Invalid message format. Please enter alpha-numeric characters only.");
        }
    }

    // Method to validate message format
    private boolean validateMessage(String message) {
        // Add code here to validate message format
        // For simplicity, this method always returns true
        return true;
    }

    public static void main(String[] args) {
        Messaging messaging = new Messaging();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Messaging System");
        System.out.println("Type 'exit' to quit");

        while (true) {
            System.out.print("Enter your message: ");
            String message = scanner.nextLine();

            if (message.equalsIgnoreCase("exit")) {
                System.out.println("Exiting messaging system...");
                break;
            }

            System.out.print("Enter your name: ");
            String player = scanner.nextLine();

            messaging.addMessage(message, player);
        }

        scanner.close();
    }
}