import java.util.Collections;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    private static final String THEME_1 = "Blocks";
    private static final String THEME_2 = "Food";
    private static int gridSize;
    private static String[][] grid;
    private static boolean[][] matched;
    
    public static void main(String[] args) {

        System.out.println("Welcome to the game 'Memory Matching'!");
        int matchedPairs = 0;
        gridSize = selectDifficulty();
        grid = new String[gridSize][gridSize];
        matched = new boolean[gridSize][gridSize];
        initializeGrid();

        String selectedTheme = selectTheme();
        System.out.println("You have selected " + selectedTheme + ".");
        shuffleAndPopulateGrid(selectedTheme);

        if (!playerReady()) {
            System.out.println("Okay, see you later! Made by @calaritooo.");
            return;
        }

        displayGrid(-1, -1, -1, -1);
        boolean playAgain = true;
        Scanner scanner = new Scanner(System.in);
        
        while (playAgain) {
            System.out.println(" ");
            System.out.println("Enter the row and column of your first card: ");
            int row1 = scanner.nextInt();
            int col1 = scanner.nextInt();
            System.out.println("Enter the row and column of your second card: ");
            int row2 = scanner.nextInt();
            int col2 = scanner.nextInt();

            displayGrid(row1, col1, row2, col2);
            
            if (grid[row1][col1].equals(grid[row2][col2])) {
                System.out.println("It's a match!");
                matchedPairs++;
            } else {
                System.out.println("Not a match. Try again!");
            }
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");
            displayGrid(-1, -1, -1, -1);

            if (matchedPairs == (gridSize * gridSize) / 2) {
                System.out.println("Congratulations! All matches have been found.");
                System.out.println("Would you like to play again? (y/n)");
                String response = scanner.next();
                playAgain = response.equalsIgnoreCase("y");
            }
        }
        System.out.println("Thanks for playing. Made by @calaritooo.");
    }

    private static void initializeGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = "?";
                matched[i][j] = false;
            }
        }
    }

    private static void displayGrid(int row1, int col1, int row2, int col2) {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (matched[i][j] || (i == row1 && j == col1) || (i == row2 && j == col2)) {
                    System.out.print(grid[i][j] + " ");
                } else {
                    System.out.print("? ");
                }
            }
            System.out.println();
        }
    }
    
    private static int selectDifficulty() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select difficulty level: ");
        System.out.println("1. Easy (4x4)");
        System.out.println("2. Medium (6x6)");
        System.out.println("3. Hard (8x8)");
        System.out.println("Enter 1, 2, or 3: ");

        int choice = scanner.nextInt();
        while (choice < 1 || choice > 3) {
            System.out.println("Invalid selection, please choose between '1', '2', and '3'!");
            choice = scanner.nextInt();
        }

        return switch (choice) {
            case 1 -> 4;
            case 2 -> 6;
            case 3 -> 8;
            default -> 4;
        };
    }

    private static boolean playerReady() {
        System.out.println("Are you ready to begin? (y/n)");
        Scanner scanner = new Scanner(System.in);
        String isPlayerReady = scanner.next();
        while (!isPlayerReady.equalsIgnoreCase("y") && !isPlayerReady.equalsIgnoreCase("n")) {
            System.out.println("Please select 'y' for 'yes' or 'n' for 'no'.");
            isPlayerReady = scanner.next();
        }
        return isPlayerReady.equalsIgnoreCase("y");
    }

    private static String selectTheme() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose your theme!");
        System.out.println("Theme 1: " + THEME_1);
        System.out.println("Theme 2: " + THEME_2);
        System.out.println("Enter 1 or 2: ");

        int choice = scanner.nextInt();
        while (choice != 1 && choice != 2) {
            System.out.println("Invalid choice, please choose between '1' and '2'!");
            choice = scanner.nextInt();
        }
        return choice == 1 ? THEME_1 : THEME_2;
    }

    private static String[] getTermsForTheme(String name) {
        if (name.equals(THEME_1)) {
            return new String[] {
                    "Dirt", "Grass Block", "Cobblestone", "Sand", "Oak Wood", "Stone", "Obsidian", "Clay", "Leaves", "Water",
                    "Lava", "Netherrack", "Glowstone", "Iron Ore", "Dark Wood", "Birch Wood", "Pale Wood", "Diamond Ore",
                    "Gold Ore", "Coal Ore", "Coal Block", "Gold Block", "Iron Block", "Spruce Wood", "Jungle Wood",
                    "Mossy Cobblestone", "Vines", "Tall Grass", "Beehive", "Glass Block", "White Wool", "Gray Wool"
            };
        } else {
            return new String[] {
                    "Apple", "Carrot", "Baked Potato", "Potato", "Cooked Beef", "Beef", "Mutton", "Cooked Mutton", "Chicken",
                    "Cooked Chicken", "Golden Apple", "Beetroot", "Golden Carrot", "Chorus Fruit", "Salmon", "Cod", "Cooked Cod",
                    "Cooked Salmon", "Bread", "Cake", "Glowberries", "Sweet Berries", "Porkchop", "Cooked Porkchop", "Rabbit Stew",
                    "Spider Eye", "Rotten Flesh", "Dried Kelp", "Cookie", "Mushroom Stew", "Suspicious Stew", "Melon Slice"
            };
        }
    }
    private static void shuffleAndPopulateGrid (String theme) {
        String[] terms = getTermsForTheme(theme);

        List<String> termList = new ArrayList<>();
        for (String term : terms) {
            termList.add(term);
            termList.add(term);
        }
        Collections.shuffle(termList);
        int index = 0;
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                grid[i][j] = termList.get(index);
                index++;
            }
        }
    }
}