import java.util.Collections;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the game 'Memory Matching'!");

        initializeGrid();
        String selectedTheme = selectTheme();
        System.out.println("You have selected " + selectedTheme);
        shuffleAndPopulateGrid(selectedTheme);
        displayGrid();
        int matchedPairs = 0;

    }

    private static final int GRID_SIZE = 6;
    private static final String THEME_1 = "Blocks";
    private static final String THEME_2 = "Food";

    private static String[][] grid = new String[GRID_SIZE][GRID_SIZE];

    private static void initializeGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = "?";
            }
        }
    }

    private static void displayGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
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
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                grid[i][j] = termList.get(index);
                index++;
            }
        }

    }
}