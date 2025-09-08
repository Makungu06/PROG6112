import java.util.ArrayList;
import java.util.Scanner;

// Series Model class to hold TV series data
class SeriesModel {
    public String SeriesId;
    public String SeriesName;
    public String SeriesAge;
    public String SeriesNumberOfEpisodes;
}

// Main Series class with all required methods
public class Series {
    // ArrayList to store all series data
    private static ArrayList<SeriesModel> seriesList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("LATEST SERIES - 2025");
        System.out.println("******************************************************************");
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
        
        String input = scanner.nextLine();
        if (input.equals("1")) {
            displayMenu();
        } else {
            ExitSeriesApplication();
        }
    }
    
    // Display the main menu and handle user input
    private static void displayMenu() {
        int choice;
        do {
            System.out.println("\nPlease select one of the following menu items:");
            System.out.println("(1) Capture a new series.");
            System.out.println("(2) Search for a series.");
            System.out.println("(3) Update series age restriction");
            System.out.println("(4) Delete a series.");
            System.out.println("(5) Print series report - 2025");
            System.out.println("(6) Exit Application.");
            System.out.print("Enter your choice (1-6): ");
            
            // Validate menu choice input
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1-6.");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1 -> CaptureSeries();
                case 2 -> SearchSeries();
                case 3 -> UpdateSeries();
                case 4 -> DeleteSeries();
                case 5 -> SeriesReport();
                case 6 -> ExitSeriesApplication();
                default -> System.out.println("Invalid choice. Please enter a number between 1-6.");
            }
            
            if (choice != 6) {
                System.out.print("\nEnter (1) to launch menu or any other key to exit: ");
                String input = scanner.nextLine();
                if (!input.equals("1")) {
                    ExitSeriesApplication();
                    break;
                }
            }
        } while (choice != 6);
    }
    
    // Method to capture a new TV series
    public static void CaptureSeries() {
        System.out.println("\nCAPTURE A NEW SERIES");
        System.out.println("***********");
        
        SeriesModel newSeries = new SeriesModel();
        
        // Get Series ID
        System.out.print("Enter the series id: ");
        newSeries.SeriesId = scanner.nextLine();
        
        // Check if series ID already exists
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(newSeries.SeriesId)) {
                System.out.println("Series ID already exists. Please use a different ID.");
                return;
            }
        }
        
        // Get Series Name
        System.out.print("Enter the series name: ");
        newSeries.SeriesName = scanner.nextLine();
        
        // Get Series Age with validation
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter the series age restriction: ");
            String ageInput = scanner.nextLine();
            
            // Check if input is numeric
            if (!ageInput.matches("\\d+")) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
                continue;
            }
            
            // Convert to integer and validate range
            int age = Integer.parseInt(ageInput);
            if (age < 2 || age > 18) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            } else {
                newSeries.SeriesAge = ageInput;
                validAge = true;
            }
        }
        
        // Get Number of Episodes with validation
        boolean validEpisodes = false;
        while (!validEpisodes) {
            System.out.print("Enter the number of episodes for " + newSeries.SeriesName + ": ");
            String episodesInput = scanner.nextLine();
            
            // Check if input is numeric
            if (!episodesInput.matches("\\d+")) {
                System.out.println("Invalid input. Number of episodes must be a number.");
                continue;
            }
            
            // Convert to integer and validate positive
            int episodes = Integer.parseInt(episodesInput);
            if (episodes <= 0) {
                System.out.println("Invalid number of episodes. Please enter a positive value.");
            } else {
                newSeries.SeriesNumberOfEpisodes = episodesInput;
                validEpisodes = true;
            }
        }
        
        // Add the new series to the list
        seriesList.add(newSeries);
        System.out.println("Series processed successfully!!!");
    }
    
    // Method to search for a TV series
    public static void SearchSeries() {
        System.out.println("\nSEARCH FOR A SERIES");
        System.out.println("***********");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series data available to search.");
            return;
        }
        
        System.out.print("Enter the series id to search: ");
        String searchId = scanner.nextLine();
        
        boolean found = false;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(searchId)) {
                System.out.println("---");
                System.out.println("SERIES ID: " + series.SeriesId);
                System.out.println("SERIES NAME: " + series.SeriesName);
                System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
                System.out.println("SERIES NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
                System.out.println("---");
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("---");
            System.out.println("Series with Series Id: " + searchId + " was not found!");
            System.out.println("---");
        }
    }
    
    // Method to update a TV series
    public static void UpdateSeries() {
        System.out.println("\nUPDATE A SERIES");
        System.out.println("***********");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series data available to update.");
            return;
        }
        
        System.out.print("Enter the series id to update: ");
        String updateId = scanner.nextLine();
        
        SeriesModel seriesToUpdate = null;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(updateId)) {
                seriesToUpdate = series;
                break;
            }
        }
        
        if (seriesToUpdate == null) {
            System.out.println("No series data could be found with ID: " + updateId);
            return;
        }
        
        // Update all fields
        System.out.print("Enter the series name: ");
        seriesToUpdate.SeriesName = scanner.nextLine();
        
        // Get Series Age with validation
        boolean validAge = false;
        while (!validAge) {
            System.out.print("Enter the age restriction: ");
            String ageInput = scanner.nextLine();
            
            // Check if input is numeric
            if (!ageInput.matches("\\d+")) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
                continue;
            }
            
            // Convert to integer and validate range
            int age = Integer.parseInt(ageInput);
            if (age < 2 || age > 18) {
                System.out.println("You have entered an incorrect series age!!!");
                System.out.print("Please re-enter the series age >> ");
            } else {
                seriesToUpdate.SeriesAge = ageInput;
                validAge = true;
            }
        }
        
        // Get Number of Episodes with validation
        boolean validEpisodes = false;
        while (!validEpisodes) {
            System.out.print("Enter the number of episodes: ");
            String episodesInput = scanner.nextLine();
            
            // Check if input is numeric
            if (!episodesInput.matches("\\d+")) {
                System.out.println("Invalid input. Number of episodes must be a number.");
                continue;
            }
            
            // Convert to integer and validate positive
            int episodes = Integer.parseInt(episodesInput);
            if (episodes <= 0) {
                System.out.println("Invalid number of episodes. Please enter a positive value.");
            } else {
                seriesToUpdate.SeriesNumberOfEpisodes = episodesInput;
                validEpisodes = true;
            }
        }
        
        System.out.println("Series updated successfully!");
    }
    
    // Method to delete a TV series
    public static void DeleteSeries() {
        System.out.println("\nDELETE A SERIES");
        System.out.println("***********");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series data available to delete.");
            return;
        }
        
        System.out.print("Enter the series id to delete: ");
        String deleteId = scanner.nextLine();
        
        SeriesModel seriesToDelete = null;
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equals(deleteId)) {
                seriesToDelete = series;
                break;
            }
        }
        
        if (seriesToDelete == null) {
            System.out.println("No series data could be found with ID: " + deleteId);
            return;
        }
        
        // Confirm deletion
        System.out.print("Are you sure you want to delete this series? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            seriesList.remove(seriesToDelete);
            System.out.println("Series deleted successfully!");
        } else {
            System.out.println("Deletion cancelled.");
        }
    }
    
    // Method to display all series
    public static void SeriesReport() {
        System.out.println("\nSERIES REPORT - 2025");
        System.out.println("***********");
        
        if (seriesList.isEmpty()) {
            System.out.println("No series data available.");
            return;
        }
        
        for (int i = 0; i < seriesList.size(); i++) {
            SeriesModel series = seriesList.get(i);
            System.out.println("Series " + (i + 1));
            System.out.println("--- SERIES ID: " + series.SeriesId);
            System.out.println("SERIES NAME: " + series.SeriesName);
            System.out.println("SERIES AGE RESTRICTION: " + series.SeriesAge);
            System.out.println("NUMBER OF EPISODES: " + series.SeriesNumberOfEpisodes);
            System.out.println("---");
        }
    }
    
    // Method to exit the application
    public static void ExitSeriesApplication() {
        System.out.println("\nThank you for using the TV Series Management Application!");
        System.out.println("Goodbye!");
        System.exit(0);
    }
} 