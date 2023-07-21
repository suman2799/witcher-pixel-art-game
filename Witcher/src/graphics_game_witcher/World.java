package graphics_game_witcher;

import java.io.InputStream;
import java.util.Scanner;

public class World {
	
	int[][] world = null;
    int numRows = 0;
    int numCols = 0;
    InputStream inputStream = null;
    
    public World () {
    	try {
			createWorld();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    // Getter function to get world array
    public int[][] getWorldArr() {
    	return world;
    }
    
    // Getter function to get world array rows
    public int getRows() {
    	return numRows;
    }
    
    // Getter function to get world array columns
    public int getColumns() {
    	return numCols;
    }
    
    // Function to create the world
	public void createWorld() throws Exception { 
		// Read the CSV file from Relative Path
		inputStream = World.class.getResourceAsStream("/res/csv/zeros.csv");
		Scanner scanner = new Scanner(inputStream);
		
		// Determine the number of rows and columns in the CSV file
        String[] firstRow = scanner.nextLine().split(",");
        numCols = firstRow.length;
        numRows++;
		
        // Count the number of rows in the CSV file
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            numRows++;
        }
        
        // Create a 2D array to store the CSV data
        world = new int[numRows][numCols];
        
        // Reset the scanner to the beginning of the file
        scanner.close();
        // Read the CSV file from Relative Path again
        inputStream = World.class.getResourceAsStream("/res/csv/zeros.csv");
        scanner = new Scanner(inputStream);
        
        // Read the CSV data into the 2D array
        for (int i = 0; i < numRows; i++) {
            String[] row = scanner.nextLine().split(",");
            for (int j = 0; j < numCols; j++) {
                world[i][j] = Integer.parseInt(row[j]);
            }
        }
        
        scanner.close();  //closes the scanner  
	}  
}
