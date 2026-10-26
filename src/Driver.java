import java.util.Scanner;

/**
 * Console-based driver that lets the user interact with players.db through
 * {@code PlayersAPI}. Presents a menu, runs the chosen query, and repeats
 * until the user chooses Exit.
 * <p>
 * Run {@code CreateDB} first to create and populate the database.
 */
public class Driver {

    /** Reads all user input from the console. */
    private static final Scanner input = new Scanner(System.in);

    /** Not used; all functionality is in {@link #main(String[])}. */
    private Driver() {
    }

    /**
     * Shows the menu in a loop and calls the matching PlayersAPI method
     * for each choice until the user exits.
     * Returns nothing; results are printed to standard output.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1:
                    PlayersAPI.printAllPlayers();
                    break;
                case 2:
                    PlayersAPI.printPlayerById(readInt("Enter player ID: "));
                    break;
                case 3:
                    String column = readLine("Enter column (Name, Position, College, Birthday): ");
                    String team = readLine("Enter team (e.g. Chiefs): ");
                    PlayersAPI.printColumnByTeam(column, team);
                    break;
                case 4:
                    String position = readLine("Enter position (e.g. QB): ").toUpperCase();
                    String answer = readLine("Pro Bowl players? (y/n): ");
                    PlayersAPI.printNamesByProBowl(position, answer.equalsIgnoreCase("y"));
                    break;
                case 5:
                    PlayersAPI.countPosition(readLine("Enter position (e.g. WR): ").toUpperCase());
                    break;
                case 6:
                    PlayersAPI.printPlayersFromCollege(readLine("Enter college (e.g. Alabama): "));
                    break;
                case 7:
                    PlayersAPI.printUndraftedPlayers();
                    break;
                case 8:
                    PlayersAPI.printPlayersByDraftYear(readInt("Enter draft year (e.g. 2024): "));
                    break;
                case 9:
                    PlayersAPI.averageWeightByPosition(readLine("Enter position (e.g. DT): ").toUpperCase());
                    break;
                case 10:
                    PlayersAPI.printTallestPlayers(readInt("How many players? "));
                    break;
                case 11:
                    PlayersAPI.printPlayersBornAfter(readLine("Enter date (YYYY-MM-DD): "));
                    break;
                case 12:
                    PlayersAPI.printRosterByJersey(readLine("Enter team (e.g. Lions): "));
                    break;
                case 13:
                    PlayersAPI.countPlayersPerTeam();
                    break;
                case 14:
                    String name = readLine("Enter player's full name: ");
                    String newTeam = readLine("Enter new team: ");
                    PlayersAPI.tradePlayer(name, newTeam);
                    break;
                case 15:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 15.");
            }

            System.out.println();
        }

        input.close();
    }

    /**
     * Prints the list of menu options.
     * Returns nothing; the menu is printed to standard output.
     */
    private static void printMenu() {
        System.out.println("===== NFL Players Database =====");
        System.out.println("1. Display all players");
        System.out.println("2. Find a player by ID");
        System.out.println("3. Display a column for a team");
        System.out.println("4. Display players at a position by Pro Bowl status");
        System.out.println("5. Count players at a position");
        System.out.println("6. Display players from a college");
        System.out.println("7. Display undrafted players");
        System.out.println("8. Display players by draft year");
        System.out.println("9. Display average weight by position");
        System.out.println("10. Display the tallest players");
        System.out.println("11. Display players born after a date");
        System.out.println("12. Display a team's roster by jersey number");
        System.out.println("13. Display number of players per team");
        System.out.println("14. Trade a player to a new team");
        System.out.println("15. Exit");
    }

    /**
     * Prompts the user and returns the line they type, trimmed.
     *
     * @param prompt the message to show the user
     * @return the user's input with leading and trailing spaces removed
     */
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }

    /**
     * Prompts the user until they enter a whole number, then returns it.
     *
     * @param prompt the message to show the user
     * @return the integer the user entered
     */
    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
}
