import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates players.db and populates the Player table with a sample of 150 NFL players.
 * Running this program again resets the table and its contents.
 * <p>
 * Note: rosters, numbers, and stats are approximate (2025 season); this is sample data for practice.
 */
public class CreateDB {

    /** Not used; all functionality is in {@link #main(String[])}. */
    private CreateDB() {
    }

    /**
     * Drops and recreates the Player table, inserts the sample players,
     * and prints each player's name, team, position, and id.
     * Returns nothing; any SQL error is printed to standard output.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        String url = "jdbc:sqlite:players.db";

        // try-with-resources automatically closes the connection and statement.
        try (Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement()) {

            // WARNING: Deletes the existing Player table and all its records.
            // This allows us to start with the same sample data each time.
            statement.executeUpdate("DROP TABLE IF EXISTS Player");

            String sql = "CREATE TABLE Player (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "team TEXT, " +           // Team nickname, e.g. 'Chiefs', '49ers'
                    "position TEXT, " +       // QB, RB, WR, TE, OT, OG, DE, DT, LB, CB, S, K
                    "jerseyNumber INTEGER, " +
                    "birthday TEXT, " +       // Dates stored as YYYY-MM-DD
                    "college TEXT, " +
                    "heightInches INTEGER, " +
                    "weightLbs INTEGER, " +
                    "draftYear INTEGER, " +   // NULL means the player went undrafted
                    "proBowl BOOLEAN" +       // Has made at least one Pro Bowl. SQLite stores true/false as 1/0
                    ")";
            statement.executeUpdate(sql);

            // Insert the sample players into the database.
            // Note: an apostrophe inside a SQL string is written twice, e.g. 'De''Von Achane'
            statement.executeUpdate("insert into Player values(1, 'Patrick Mahomes', 'Chiefs', 'QB', 15, '1995-09-17', 'Texas Tech', 74, 225, 2017, true)");
            statement.executeUpdate("insert into Player values(2, 'Travis Kelce', 'Chiefs', 'TE', 87, '1989-10-05', 'Cincinnati', 77, 250, 2013, true)");
            statement.executeUpdate("insert into Player values(3, 'Chris Jones', 'Chiefs', 'DT', 95, '1994-07-03', 'Mississippi State', 78, 310, 2016, true)");
            statement.executeUpdate("insert into Player values(4, 'Trent McDuffie', 'Chiefs', 'CB', 21, '2000-09-13', 'Washington', 71, 193, 2022, true)");
            statement.executeUpdate("insert into Player values(5, 'Harrison Butker', 'Chiefs', 'K', 7, '1995-07-14', 'Georgia Tech', 76, 196, 2017, true)");
            statement.executeUpdate("insert into Player values(6, 'Josh Allen', 'Bills', 'QB', 17, '1996-05-21', 'Wyoming', 77, 237, 2018, true)");
            statement.executeUpdate("insert into Player values(7, 'James Cook', 'Bills', 'RB', 4, '1999-09-25', 'Georgia', 71, 190, 2022, true)");
            statement.executeUpdate("insert into Player values(8, 'Dalton Kincaid', 'Bills', 'TE', 86, '1999-10-18', 'Utah', 76, 240, 2023, false)");
            statement.executeUpdate("insert into Player values(9, 'Khalil Shakir', 'Bills', 'WR', 10, '2000-02-03', 'Boise State', 72, 190, 2022, false)");
            statement.executeUpdate("insert into Player values(10, 'Tua Tagovailoa', 'Dolphins', 'QB', 1, '1998-03-02', 'Alabama', 73, 217, 2020, true)");
            statement.executeUpdate("insert into Player values(11, 'Tyreek Hill', 'Dolphins', 'WR', 10, '1994-03-01', 'West Alabama', 70, 191, 2016, true)");
            statement.executeUpdate("insert into Player values(12, 'Jaylen Waddle', 'Dolphins', 'WR', 17, '1998-11-25', 'Alabama', 70, 185, 2021, true)");
            statement.executeUpdate("insert into Player values(13, 'De''Von Achane', 'Dolphins', 'RB', 28, '2001-10-13', 'Texas A&M', 69, 188, 2023, false)");
            statement.executeUpdate("insert into Player values(14, 'Drake Maye', 'Patriots', 'QB', 10, '2002-08-30', 'North Carolina', 76, 225, 2024, true)");
            statement.executeUpdate("insert into Player values(15, 'Rhamondre Stevenson', 'Patriots', 'RB', 38, '1998-02-23', 'Oklahoma', 72, 227, 2021, false)");
            statement.executeUpdate("insert into Player values(16, 'Christian Gonzalez', 'Patriots', 'CB', 0, '2002-05-22', 'Oregon', 73, 197, 2023, false)");
            statement.executeUpdate("insert into Player values(17, 'Stefon Diggs', 'Patriots', 'WR', 8, '1993-11-29', 'Maryland', 72, 191, 2015, true)");
            statement.executeUpdate("insert into Player values(18, 'Garrett Wilson', 'Jets', 'WR', 5, '2000-07-22', 'Ohio State', 72, 192, 2022, false)");
            statement.executeUpdate("insert into Player values(19, 'Breece Hall', 'Jets', 'RB', 20, '2001-05-31', 'Iowa State', 71, 217, 2022, false)");
            statement.executeUpdate("insert into Player values(20, 'Jermaine Johnson', 'Jets', 'DE', 11, '1999-01-11', 'Florida State', 77, 254, 2022, true)");
            statement.executeUpdate("insert into Player values(21, 'Justin Fields', 'Jets', 'QB', 7, '1999-03-05', 'Ohio State', 75, 228, 2021, false)");
            statement.executeUpdate("insert into Player values(22, 'Lamar Jackson', 'Ravens', 'QB', 8, '1997-01-07', 'Louisville', 74, 205, 2018, true)");
            statement.executeUpdate("insert into Player values(23, 'Derrick Henry', 'Ravens', 'RB', 22, '1994-01-04', 'Alabama', 75, 247, 2016, true)");
            statement.executeUpdate("insert into Player values(24, 'Mark Andrews', 'Ravens', 'TE', 89, '1995-09-06', 'Oklahoma', 77, 256, 2018, true)");
            statement.executeUpdate("insert into Player values(25, 'Kyle Hamilton', 'Ravens', 'S', 14, '2001-03-16', 'Notre Dame', 76, 220, 2022, true)");
            statement.executeUpdate("insert into Player values(26, 'Roquan Smith', 'Ravens', 'LB', 0, '1997-04-08', 'Georgia', 73, 232, 2018, true)");
            statement.executeUpdate("insert into Player values(27, 'Zay Flowers', 'Ravens', 'WR', 4, '2000-09-15', 'Boston College', 69, 182, 2023, true)");
            statement.executeUpdate("insert into Player values(28, 'Joe Burrow', 'Bengals', 'QB', 9, '1996-12-10', 'LSU', 76, 221, 2020, true)");
            statement.executeUpdate("insert into Player values(29, 'Ja''Marr Chase', 'Bengals', 'WR', 1, '2000-03-01', 'LSU', 72, 201, 2021, true)");
            statement.executeUpdate("insert into Player values(30, 'Tee Higgins', 'Bengals', 'WR', 5, '1999-01-18', 'Clemson', 76, 219, 2020, false)");
            statement.executeUpdate("insert into Player values(31, 'Trey Hendrickson', 'Bengals', 'DE', 91, '1994-12-05', 'Florida Atlantic', 76, 270, 2017, true)");
            statement.executeUpdate("insert into Player values(32, 'Myles Garrett', 'Browns', 'DE', 95, '1995-12-29', 'Texas A&M', 76, 272, 2017, true)");
            statement.executeUpdate("insert into Player values(33, 'Denzel Ward', 'Browns', 'CB', 21, '1997-04-28', 'Ohio State', 71, 190, 2018, true)");
            statement.executeUpdate("insert into Player values(34, 'David Njoku', 'Browns', 'TE', 85, '1996-07-10', 'Miami', 76, 246, 2017, true)");
            statement.executeUpdate("insert into Player values(35, 'Shedeur Sanders', 'Browns', 'QB', 12, '2002-02-07', 'Colorado', 74, 212, 2025, false)");
            statement.executeUpdate("insert into Player values(36, 'T.J. Watt', 'Steelers', 'LB', 90, '1994-10-11', 'Wisconsin', 76, 252, 2017, true)");
            statement.executeUpdate("insert into Player values(37, 'Aaron Rodgers', 'Steelers', 'QB', 8, '1983-12-02', 'California', 74, 223, 2005, true)");
            statement.executeUpdate("insert into Player values(38, 'DK Metcalf', 'Steelers', 'WR', 4, '1997-12-14', 'Ole Miss', 76, 235, 2019, true)");
            statement.executeUpdate("insert into Player values(39, 'Cameron Heyward', 'Steelers', 'DT', 97, '1989-05-06', 'Ohio State', 77, 295, 2011, true)");
            statement.executeUpdate("insert into Player values(40, 'Chris Boswell', 'Steelers', 'K', 9, '1991-03-16', 'Rice', 74, 185, NULL, true)");
            statement.executeUpdate("insert into Player values(41, 'Jaylen Warren', 'Steelers', 'RB', 30, '1998-11-02', 'Oklahoma State', 68, 215, NULL, false)");
            statement.executeUpdate("insert into Player values(42, 'C.J. Stroud', 'Texans', 'QB', 7, '2001-10-03', 'Ohio State', 75, 218, 2023, true)");
            statement.executeUpdate("insert into Player values(43, 'Nico Collins', 'Texans', 'WR', 12, '1999-03-19', 'Michigan', 76, 222, 2021, true)");
            statement.executeUpdate("insert into Player values(44, 'Will Anderson Jr.', 'Texans', 'DE', 51, '2001-09-02', 'Alabama', 75, 253, 2023, true)");
            statement.executeUpdate("insert into Player values(45, 'Danielle Hunter', 'Texans', 'DE', 55, '1994-10-29', 'LSU', 77, 263, 2015, true)");
            statement.executeUpdate("insert into Player values(46, 'Derek Stingley Jr.', 'Texans', 'CB', 24, '2001-06-20', 'LSU', 72, 195, 2022, true)");
            statement.executeUpdate("insert into Player values(47, 'Jonathan Taylor', 'Colts', 'RB', 28, '1999-01-19', 'Wisconsin', 70, 226, 2020, true)");
            statement.executeUpdate("insert into Player values(48, 'Daniel Jones', 'Colts', 'QB', 17, '1997-05-27', 'Duke', 77, 230, 2019, false)");
            statement.executeUpdate("insert into Player values(49, 'Michael Pittman Jr.', 'Colts', 'WR', 11, '1997-10-05', 'USC', 76, 223, 2020, false)");
            statement.executeUpdate("insert into Player values(50, 'Quenton Nelson', 'Colts', 'OG', 56, '1996-03-19', 'Notre Dame', 77, 330, 2018, true)");
            statement.executeUpdate("insert into Player values(51, 'DeForest Buckner', 'Colts', 'DT', 99, '1994-03-17', 'Oregon', 79, 295, 2016, true)");
            statement.executeUpdate("insert into Player values(52, 'Trevor Lawrence', 'Jaguars', 'QB', 16, '1999-10-06', 'Clemson', 78, 220, 2021, true)");
            statement.executeUpdate("insert into Player values(53, 'Travis Hunter', 'Jaguars', 'WR', 12, '2003-05-18', 'Colorado', 72, 188, 2025, false)");
            statement.executeUpdate("insert into Player values(54, 'Brian Thomas Jr.', 'Jaguars', 'WR', 7, '2002-10-08', 'LSU', 75, 209, 2024, true)");
            statement.executeUpdate("insert into Player values(55, 'Josh Hines-Allen', 'Jaguars', 'DE', 41, '1997-07-13', 'Kentucky', 77, 255, 2019, true)");
            statement.executeUpdate("insert into Player values(56, 'Cam Ward', 'Titans', 'QB', 1, '2002-05-25', 'Miami', 74, 219, 2025, false)");
            statement.executeUpdate("insert into Player values(57, 'Calvin Ridley', 'Titans', 'WR', 0, '1994-12-20', 'Alabama', 73, 190, 2018, false)");
            statement.executeUpdate("insert into Player values(58, 'Jeffery Simmons', 'Titans', 'DT', 98, '1997-07-28', 'Mississippi State', 76, 305, 2019, true)");
            statement.executeUpdate("insert into Player values(59, 'Bo Nix', 'Broncos', 'QB', 10, '2000-02-25', 'Oregon', 74, 214, 2024, false)");
            statement.executeUpdate("insert into Player values(60, 'Patrick Surtain II', 'Broncos', 'CB', 2, '2000-04-14', 'Alabama', 74, 202, 2021, true)");
            statement.executeUpdate("insert into Player values(61, 'Courtland Sutton', 'Broncos', 'WR', 14, '1995-10-10', 'SMU', 76, 216, 2018, true)");
            statement.executeUpdate("insert into Player values(62, 'Nik Bonitto', 'Broncos', 'LB', 15, '1999-10-25', 'Oklahoma', 75, 240, 2022, true)");
            statement.executeUpdate("insert into Player values(63, 'Justin Herbert', 'Chargers', 'QB', 10, '1998-03-10', 'Oregon', 78, 236, 2020, true)");
            statement.executeUpdate("insert into Player values(64, 'Ladd McConkey', 'Chargers', 'WR', 15, '2001-11-11', 'Georgia', 72, 185, 2024, false)");
            statement.executeUpdate("insert into Player values(65, 'Derwin James Jr.', 'Chargers', 'S', 3, '1996-08-03', 'Florida State', 74, 215, 2018, true)");
            statement.executeUpdate("insert into Player values(66, 'Khalil Mack', 'Chargers', 'LB', 52, '1991-02-22', 'Buffalo', 75, 269, 2014, true)");
            statement.executeUpdate("insert into Player values(67, 'Rashawn Slater', 'Chargers', 'OT', 70, '1999-03-26', 'Northwestern', 76, 315, 2021, true)");
            statement.executeUpdate("insert into Player values(68, 'Cameron Dicker', 'Chargers', 'K', 11, '2000-05-06', 'Texas', 73, 215, NULL, false)");
            statement.executeUpdate("insert into Player values(69, 'Maxx Crosby', 'Raiders', 'DE', 98, '1997-08-22', 'Eastern Michigan', 77, 255, 2019, true)");
            statement.executeUpdate("insert into Player values(70, 'Brock Bowers', 'Raiders', 'TE', 89, '2002-12-13', 'Georgia', 75, 230, 2024, true)");
            statement.executeUpdate("insert into Player values(71, 'Geno Smith', 'Raiders', 'QB', 7, '1990-10-10', 'West Virginia', 75, 221, 2013, true)");
            statement.executeUpdate("insert into Player values(72, 'Ashton Jeanty', 'Raiders', 'RB', 2, '2003-12-02', 'Boise State', 68, 211, 2025, false)");
            statement.executeUpdate("insert into Player values(73, 'Daniel Carlson', 'Raiders', 'K', 8, '1995-01-23', 'Auburn', 77, 220, 2018, true)");
            statement.executeUpdate("insert into Player values(74, 'Dak Prescott', 'Cowboys', 'QB', 4, '1993-07-29', 'Mississippi State', 74, 238, 2016, true)");
            statement.executeUpdate("insert into Player values(75, 'CeeDee Lamb', 'Cowboys', 'WR', 88, '1999-04-08', 'Oklahoma', 74, 200, 2020, true)");
            statement.executeUpdate("insert into Player values(76, 'Trevon Diggs', 'Cowboys', 'CB', 7, '1998-09-20', 'Alabama', 74, 207, 2020, true)");
            statement.executeUpdate("insert into Player values(77, 'George Pickens', 'Cowboys', 'WR', 3, '2001-03-04', 'Georgia', 75, 200, 2022, false)");
            statement.executeUpdate("insert into Player values(78, 'Brandon Aubrey', 'Cowboys', 'K', 17, '1995-03-14', 'Notre Dame', 75, 207, NULL, true)");
            statement.executeUpdate("insert into Player values(79, 'Tyler Smith', 'Cowboys', 'OG', 73, '2001-04-03', 'Tulsa', 77, 332, 2022, true)");
            statement.executeUpdate("insert into Player values(80, 'Jalen Hurts', 'Eagles', 'QB', 1, '1998-08-07', 'Oklahoma', 73, 223, 2020, true)");
            statement.executeUpdate("insert into Player values(81, 'Saquon Barkley', 'Eagles', 'RB', 26, '1997-02-09', 'Penn State', 72, 232, 2018, true)");
            statement.executeUpdate("insert into Player values(82, 'A.J. Brown', 'Eagles', 'WR', 11, '1997-06-30', 'Ole Miss', 73, 226, 2019, true)");
            statement.executeUpdate("insert into Player values(83, 'DeVonta Smith', 'Eagles', 'WR', 6, '1998-11-14', 'Alabama', 72, 170, 2021, false)");
            statement.executeUpdate("insert into Player values(84, 'Lane Johnson', 'Eagles', 'OT', 65, '1990-05-08', 'Oklahoma', 78, 325, 2013, true)");
            statement.executeUpdate("insert into Player values(85, 'Jalen Carter', 'Eagles', 'DT', 98, '2001-04-04', 'Georgia', 75, 314, 2023, false)");
            statement.executeUpdate("insert into Player values(86, 'Zack Baun', 'Eagles', 'LB', 53, '1996-12-30', 'Wisconsin', 74, 225, 2020, true)");
            statement.executeUpdate("insert into Player values(87, 'Malik Nabers', 'Giants', 'WR', 1, '2003-07-28', 'LSU', 72, 200, 2024, true)");
            statement.executeUpdate("insert into Player values(88, 'Jaxson Dart', 'Giants', 'QB', 6, '2003-05-13', 'Ole Miss', 74, 223, 2025, false)");
            statement.executeUpdate("insert into Player values(89, 'Dexter Lawrence II', 'Giants', 'DT', 97, '1997-11-12', 'Clemson', 76, 340, 2019, true)");
            statement.executeUpdate("insert into Player values(90, 'Brian Burns', 'Giants', 'DE', 0, '1998-04-23', 'Florida State', 77, 250, 2019, true)");
            statement.executeUpdate("insert into Player values(91, 'Abdul Carter', 'Giants', 'LB', 51, '2003-11-11', 'Penn State', 75, 250, 2025, false)");
            statement.executeUpdate("insert into Player values(92, 'Jayden Daniels', 'Commanders', 'QB', 5, '2000-12-18', 'LSU', 76, 210, 2024, true)");
            statement.executeUpdate("insert into Player values(93, 'Terry McLaurin', 'Commanders', 'WR', 17, '1995-09-15', 'Ohio State', 72, 210, 2019, true)");
            statement.executeUpdate("insert into Player values(94, 'Zach Ertz', 'Commanders', 'TE', 86, '1990-11-10', 'Stanford', 77, 250, 2013, true)");
            statement.executeUpdate("insert into Player values(95, 'Bobby Wagner', 'Commanders', 'LB', 54, '1990-06-27', 'Utah State', 72, 242, 2012, true)");
            statement.executeUpdate("insert into Player values(96, 'Austin Ekeler', 'Commanders', 'RB', 30, '1995-05-17', 'Western Colorado', 70, 200, NULL, true)");
            statement.executeUpdate("insert into Player values(97, 'Jordan Love', 'Packers', 'QB', 10, '1998-11-02', 'Utah State', 76, 219, 2020, false)");
            statement.executeUpdate("insert into Player values(98, 'Josh Jacobs', 'Packers', 'RB', 8, '1998-02-11', 'Alabama', 70, 223, 2019, true)");
            statement.executeUpdate("insert into Player values(99, 'Micah Parsons', 'Packers', 'LB', 1, '1999-05-26', 'Penn State', 75, 245, 2021, true)");
            statement.executeUpdate("insert into Player values(100, 'Xavier McKinney', 'Packers', 'S', 29, '1999-08-09', 'Alabama', 72, 201, 2020, true)");
            statement.executeUpdate("insert into Player values(101, 'Tucker Kraft', 'Packers', 'TE', 85, '2000-11-03', 'South Dakota State', 77, 254, 2023, false)");
            statement.executeUpdate("insert into Player values(102, 'Caleb Williams', 'Bears', 'QB', 18, '2001-11-18', 'USC', 73, 215, 2024, false)");
            statement.executeUpdate("insert into Player values(103, 'DJ Moore', 'Bears', 'WR', 2, '1997-04-14', 'Maryland', 72, 210, 2018, false)");
            statement.executeUpdate("insert into Player values(104, 'Rome Odunze', 'Bears', 'WR', 15, '2002-06-03', 'Washington', 75, 215, 2024, false)");
            statement.executeUpdate("insert into Player values(105, 'Montez Sweat', 'Bears', 'DE', 98, '1996-09-04', 'Mississippi State', 78, 262, 2019, false)");
            statement.executeUpdate("insert into Player values(106, 'Jaylon Johnson', 'Bears', 'CB', 1, '1999-04-19', 'Utah', 72, 195, 2020, true)");
            statement.executeUpdate("insert into Player values(107, 'Jared Goff', 'Lions', 'QB', 16, '1994-10-14', 'California', 76, 217, 2016, true)");
            statement.executeUpdate("insert into Player values(108, 'Amon-Ra St. Brown', 'Lions', 'WR', 14, '1999-10-24', 'USC', 72, 202, 2021, true)");
            statement.executeUpdate("insert into Player values(109, 'Jahmyr Gibbs', 'Lions', 'RB', 0, '2002-03-20', 'Alabama', 69, 200, 2023, true)");
            statement.executeUpdate("insert into Player values(110, 'Aidan Hutchinson', 'Lions', 'DE', 97, '2000-08-09', 'Michigan', 79, 265, 2022, true)");
            statement.executeUpdate("insert into Player values(111, 'Penei Sewell', 'Lions', 'OT', 58, '2000-10-09', 'Oregon', 77, 335, 2021, true)");
            statement.executeUpdate("insert into Player values(112, 'Sam LaPorta', 'Lions', 'TE', 87, '2001-01-12', 'Iowa', 75, 245, 2023, true)");
            statement.executeUpdate("insert into Player values(113, 'Justin Jefferson', 'Vikings', 'WR', 18, '1999-06-16', 'LSU', 73, 195, 2020, true)");
            statement.executeUpdate("insert into Player values(114, 'J.J. McCarthy', 'Vikings', 'QB', 9, '2003-01-20', 'Michigan', 75, 219, 2024, false)");
            statement.executeUpdate("insert into Player values(115, 'Andrew Van Ginkel', 'Vikings', 'LB', 43, '1995-12-07', 'Wisconsin', 76, 242, 2019, true)");
            statement.executeUpdate("insert into Player values(116, 'Aaron Jones', 'Vikings', 'RB', 33, '1994-12-02', 'UTEP', 69, 208, 2017, true)");
            statement.executeUpdate("insert into Player values(117, 'Bijan Robinson', 'Falcons', 'RB', 7, '2002-01-30', 'Texas', 71, 215, 2023, true)");
            statement.executeUpdate("insert into Player values(118, 'Drake London', 'Falcons', 'WR', 5, '2001-07-24', 'USC', 76, 213, 2022, false)");
            statement.executeUpdate("insert into Player values(119, 'Michael Penix Jr.', 'Falcons', 'QB', 9, '2000-05-08', 'Washington', 74, 216, 2024, false)");
            statement.executeUpdate("insert into Player values(120, 'Kyle Pitts', 'Falcons', 'TE', 8, '2000-10-06', 'Florida', 78, 247, 2021, true)");
            statement.executeUpdate("insert into Player values(121, 'Jessie Bates III', 'Falcons', 'S', 3, '1997-02-26', 'Wake Forest', 73, 200, 2018, true)");
            statement.executeUpdate("insert into Player values(122, 'Bryce Young', 'Panthers', 'QB', 9, '2001-07-25', 'Alabama', 70, 204, 2023, false)");
            statement.executeUpdate("insert into Player values(123, 'Tetairoa McMillan', 'Panthers', 'WR', 4, '2003-04-05', 'Arizona', 76, 219, 2025, false)");
            statement.executeUpdate("insert into Player values(124, 'Derrick Brown', 'Panthers', 'DT', 95, '1998-04-15', 'Auburn', 77, 320, 2020, false)");
            statement.executeUpdate("insert into Player values(125, 'Alvin Kamara', 'Saints', 'RB', 41, '1995-07-25', 'Tennessee', 70, 215, 2017, true)");
            statement.executeUpdate("insert into Player values(126, 'Chris Olave', 'Saints', 'WR', 12, '2000-06-27', 'Ohio State', 73, 187, 2022, false)");
            statement.executeUpdate("insert into Player values(127, 'Cameron Jordan', 'Saints', 'DE', 94, '1989-07-10', 'California', 76, 287, 2011, true)");
            statement.executeUpdate("insert into Player values(128, 'Tyler Shough', 'Saints', 'QB', 6, '1999-09-28', 'Louisville', 77, 219, 2025, false)");
            statement.executeUpdate("insert into Player values(129, 'Baker Mayfield', 'Buccaneers', 'QB', 6, '1995-04-14', 'Oklahoma', 73, 215, 2018, true)");
            statement.executeUpdate("insert into Player values(130, 'Mike Evans', 'Buccaneers', 'WR', 13, '1993-08-21', 'Texas A&M', 77, 231, 2014, true)");
            statement.executeUpdate("insert into Player values(131, 'Bucky Irving', 'Buccaneers', 'RB', 7, '2002-08-24', 'Oregon', 69, 195, 2024, false)");
            statement.executeUpdate("insert into Player values(132, 'Tristan Wirfs', 'Buccaneers', 'OT', 78, '1999-01-24', 'Iowa', 77, 320, 2020, true)");
            statement.executeUpdate("insert into Player values(133, 'Antoine Winfield Jr.', 'Buccaneers', 'S', 31, '1998-08-16', 'Minnesota', 69, 203, 2020, true)");
            statement.executeUpdate("insert into Player values(134, 'Brock Purdy', '49ers', 'QB', 13, '1999-12-27', 'Iowa State', 73, 220, 2022, true)");
            statement.executeUpdate("insert into Player values(135, 'Christian McCaffrey', '49ers', 'RB', 23, '1996-06-07', 'Stanford', 71, 210, 2017, true)");
            statement.executeUpdate("insert into Player values(136, 'George Kittle', '49ers', 'TE', 85, '1993-10-09', 'Iowa', 76, 250, 2017, true)");
            statement.executeUpdate("insert into Player values(137, 'Fred Warner', '49ers', 'LB', 54, '1996-11-19', 'BYU', 75, 230, 2018, true)");
            statement.executeUpdate("insert into Player values(138, 'Nick Bosa', '49ers', 'DE', 97, '1997-10-23', 'Ohio State', 76, 266, 2019, true)");
            statement.executeUpdate("insert into Player values(139, 'Trent Williams', '49ers', 'OT', 71, '1988-07-19', 'Oklahoma', 77, 320, 2010, true)");
            statement.executeUpdate("insert into Player values(140, 'Matthew Stafford', 'Rams', 'QB', 9, '1988-02-07', 'Georgia', 75, 214, 2009, true)");
            statement.executeUpdate("insert into Player values(141, 'Puka Nacua', 'Rams', 'WR', 12, '2001-05-29', 'BYU', 74, 210, 2023, true)");
            statement.executeUpdate("insert into Player values(142, 'Davante Adams', 'Rams', 'WR', 17, '1992-12-24', 'Fresno State', 73, 215, 2014, true)");
            statement.executeUpdate("insert into Player values(143, 'Kyren Williams', 'Rams', 'RB', 23, '2000-08-26', 'Notre Dame', 69, 194, 2022, true)");
            statement.executeUpdate("insert into Player values(144, 'Sam Darnold', 'Seahawks', 'QB', 14, '1997-06-05', 'USC', 75, 225, 2018, true)");
            statement.executeUpdate("insert into Player values(145, 'Jaxon Smith-Njigba', 'Seahawks', 'WR', 11, '2002-02-14', 'Ohio State', 72, 197, 2023, false)");
            statement.executeUpdate("insert into Player values(146, 'Devon Witherspoon', 'Seahawks', 'CB', 21, '2000-12-14', 'Illinois', 71, 185, 2023, true)");
            statement.executeUpdate("insert into Player values(147, 'Kyler Murray', 'Cardinals', 'QB', 1, '1997-08-07', 'Oklahoma', 70, 207, 2019, true)");
            statement.executeUpdate("insert into Player values(148, 'Marvin Harrison Jr.', 'Cardinals', 'WR', 18, '2002-08-11', 'Ohio State', 75, 209, 2024, false)");
            statement.executeUpdate("insert into Player values(149, 'Trey McBride', 'Cardinals', 'TE', 85, '1999-11-22', 'Colorado State', 75, 246, 2022, true)");
            statement.executeUpdate("insert into Player values(150, 'James Conner', 'Cardinals', 'RB', 6, '1995-05-05', 'Pittsburgh', 73, 233, 2017, true)");

            //display the contents of the Player table
            String sqlQuery = "SELECT * FROM Player"; // Return all (*) rows from Player table
            ResultSet rs = statement.executeQuery(sqlQuery);
                //loop through all of the results
                while(rs.next())
                {
                    // read the result set
                    System.out.print(rs.getString("name"));
                    System.out.print(" " + rs.getString("team"));
                    System.out.print(" " + rs.getString("position"));
                    System.out.println(" id: " + rs.getInt("id"));
                }



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
