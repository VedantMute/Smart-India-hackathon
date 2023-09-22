// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.util.Scanner;

public class firstDB {
    public static void main(String[] args) {
        // MySQL Database Configuration
        String dbUrl = "jdbc:mysql://localhost/bus_data";
        String dbUser = "root";
        String dbPassword = "#orange#7711";

        try {
            // Connect to the MySQL database
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            // Simulate Arduino data input (replace this with actual Arduino data input)
            String simulatedData = "Destination: Stop A"; // Simulated data

            // Process simulated data and query the database
            if (simulatedData != null && simulatedData.startsWith("Destination: ")) {
                String destination = simulatedData.substring("Destination: ".length());

                // Create a SQL query to retrieve buses for the given destination
                String sql = "SELECT bus_number FROM buses WHERE bus_destination = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, destination);

                // Execute the query and retrieve results
                ResultSet resultSet = preparedStatement.executeQuery();

                // Display the available buses
                System.out.println("Available buses to " + destination + ":");
                while (resultSet.next()) {
                    String busNumber = resultSet.getString("bus_number");
                    System.out.println(busNumber);
                }

                // Close resources
                resultSet.close();
                preparedStatement.close();
            }

            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
