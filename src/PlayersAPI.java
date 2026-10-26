import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A collection of queries and updates against the Player table in players.db.
 * Each method opens its own connection, runs one SQL statement, and prints the
 * results to standard output. Any SQL errors are printed instead of thrown.
 * <p>
 * Run {@code CreateDB} first to create and populate the database.
 */
public class PlayersAPI {

    /** JDBC connection URL for the SQLite database. */
    private static final String URL = "jdbc:sqlite:players.db";

    /** Not used; all methods are static, so no object is needed. */
    private PlayersAPI() {
    }

    /**
     * Prints every player's id, name, team, and position, ordered by id.
     * Returns nothing; results are printed to standard output.
     */
    public static void printAllPlayers() {
        String sql = "SELECT id, name, team, position FROM Player ORDER BY id";

        try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("name") + " - "
                        + rs.getString("team") + " (" + rs.getString("position") + ")");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints all details for the player with the given id,
     * or "No player found" if no player has that id.
     * Returns nothing; results are printed to standard output.
     *
     * @param id the player's id
     */
    public static void printPlayerById(int id) {
        String sql = "SELECT * FROM Player WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                // getInt returns 0 for NULL, so wasNull() tells us the player went undrafted.
                int draftYear = rs.getInt("draftYear");
                String draft = rs.wasNull() ? "Undrafted" : String.valueOf(draftYear);

                System.out.println("Name:     " + rs.getString("name"));
                System.out.println("Team:     " + rs.getString("team"));
                System.out.println("Position: " + rs.getString("position"));
                System.out.println("Jersey:   #" + rs.getInt("jerseyNumber"));
                System.out.println("Birthday: " + rs.getString("birthday"));
                System.out.println("College:  " + rs.getString("college"));
                System.out.println("Height:   " + rs.getInt("heightInches") + " in");
                System.out.println("Weight:   " + rs.getInt("weightLbs") + " lbs");
                System.out.println("Drafted:  " + draft);
                System.out.println("Pro Bowl: " + (rs.getBoolean("proBowl") ? "Yes" : "No"));
            } else {
                System.out.println("No player found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints one column's value for every player on the given team.
     * Only the Name, Position, College, and Birthday columns are allowed;
     * any other column prints "Invalid column" and nothing is queried.
     * Returns nothing; results are printed to standard output.
     *
     * @param column the column to print: Name, Position, College, or Birthday (case-insensitive)
     * @param team   the team nickname to filter by, e.g. "Chiefs"
     */
    public static void printColumnByTeam(String column, String team) {
        if (!column.equalsIgnoreCase("Name") && !column.equalsIgnoreCase("Position")
                && !column.equalsIgnoreCase("College") && !column.equalsIgnoreCase("Birthday")) {
            System.out.println("Invalid column");
            return;
        }

        // Column names can't be bound with ?, so the column is checked against the list above first.
        String sql = "SELECT " + column + " FROM Player WHERE team = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, team);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the names of all players at a position, filtered by Pro Bowl status.
     * Returns nothing; results are printed to standard output.
     *
     * @param position the position abbreviation, e.g. "QB"
     * @param proBowl  {@code true} for players who have made a Pro Bowl,
     *                 {@code false} for players who have not
     */
    public static void printNamesByProBowl(String position, boolean proBowl) {
        String sql = "SELECT name FROM Player WHERE position = ? AND proBowl = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, position);
            statement.setBoolean(2, proBowl);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints how many players play the given position, as "position: count".
     * Returns nothing; results are printed to standard output.
     *
     * @param position the position abbreviation, e.g. "WR"
     */
    public static void countPosition(String position) {
        String sql = "SELECT COUNT(*) FROM Player WHERE position = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, position);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                System.out.println(position + ": " + rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the name and team of every player who attended the given college.
     * Returns nothing; results are printed to standard output.
     *
     * @param college the college name, e.g. "Alabama"
     */
    public static void printPlayersFromCollege(String college) {
        String sql = "SELECT name, team FROM Player WHERE college = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, college);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getString("team"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the name and team of every player who went undrafted
     * (players whose draft year is NULL).
     * Returns nothing; results are printed to standard output.
     */
    public static void printUndraftedPlayers() {
        String sql = "SELECT name, team FROM Player WHERE draftYear IS NULL";

        try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getString("team"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the name and draft year of every player drafted in the given year.
     * Returns nothing; results are printed to standard output.
     *
     * @param year the draft year, e.g. 2024
     */
    public static void printPlayersByDraftYear(int year) {
        String sql = "SELECT name, draftYear FROM Player WHERE draftYear = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, year);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getInt("draftYear"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the average weight, in pounds, of players at the given position.
     * Returns nothing; results are printed to standard output.
     *
     * @param position the position abbreviation, e.g. "DT"
     */
    public static void averageWeightByPosition(String position) {
        String sql = "SELECT AVG(weightLbs) FROM Player WHERE position = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, position);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                System.out.printf("%s: %.1f lbs%n", position, rs.getDouble(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the tallest players with their heights in inches, tallest first.
     * Returns nothing; results are printed to standard output.
     *
     * @param howMany the number of players to print
     */
    public static void printTallestPlayers(int howMany) {
        String sql = "SELECT name, heightInches FROM Player ORDER BY heightInches DESC LIMIT ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, howMany);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getInt("heightInches") + " in");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the name and birthday of every player born after the given date.
     * Returns nothing; results are printed to standard output.
     *
     * @param date the cutoff date in YYYY-MM-DD format; players born on this date are not included
     */
    public static void printPlayersBornAfter(String date) {
        String sql = "SELECT name, birthday FROM Player WHERE birthday > ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, date);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name") + " - " + rs.getString("birthday"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints a team's roster sorted by jersey number, as "#number name (position)".
     * Returns nothing; results are printed to standard output.
     *
     * @param team the team nickname, e.g. "Lions"
     */
    public static void printRosterByJersey(String team) {
        String sql = "SELECT jerseyNumber, name, position FROM Player WHERE team = ? ORDER BY jerseyNumber";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, team);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                System.out.println("#" + rs.getInt("jerseyNumber") + " " + rs.getString("name")
                        + " (" + rs.getString("position") + ")");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Prints the number of players on each team, as "team: count".
     * Returns nothing; results are printed to standard output.
     */
    public static void countPlayersPerTeam() {
        String sql = "SELECT team, COUNT(*) FROM Player GROUP BY team";

        try (Connection connection = DriverManager.getConnection(URL);
            Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getString("team") + ": " + rs.getInt(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Moves a player to a new team by updating their team in the database,
     * then prints how many rows were updated (0 if no player has that name).
     * Returns nothing; results are printed to standard output.
     *
     * @param name    the player's full name, e.g. "Davante Adams"
     * @param newTeam the team nickname to move the player to
     */
    public static void tradePlayer(String name, String newTeam) {
        String sql = "UPDATE Player SET team = ? WHERE name = ?";

        try (Connection connection = DriverManager.getConnection(URL);
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newTeam);
            statement.setString(2, name);
            int rowsUpdated = statement.executeUpdate();

            System.out.println(rowsUpdated + " row(s) updated");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs each query once with sample values to demonstrate the API.
     * Returns nothing; results are printed to standard output.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("--- Chiefs player names ---");
        printColumnByTeam("Name", "Chiefs");

        System.out.println("--- Pro Bowl quarterbacks ---");
        printNamesByProBowl("QB", true);

        System.out.println("--- Number of wide receivers ---");
        countPosition("WR");

        System.out.println("--- Players from Alabama ---");
        printPlayersFromCollege("Alabama");

        System.out.println("--- Undrafted players ---");
        printUndraftedPlayers();

        System.out.println("--- 2024 draft class ---");
        printPlayersByDraftYear(2024);

        System.out.println("--- Average DT weight ---");
        averageWeightByPosition("DT");

        System.out.println("--- 5 tallest players ---");
        printTallestPlayers(5);

        System.out.println("--- Born after 2002-01-01 ---");
        printPlayersBornAfter("2002-01-01");

        System.out.println("--- Lions roster by jersey number ---");
        printRosterByJersey("Lions");

        System.out.println("--- Players per team ---");
        countPlayersPerTeam();

        System.out.println("--- Trade ---");
        tradePlayer("Davante Adams", "Packers");
    }
}
