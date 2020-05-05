import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * game
 */
public class Main {

    private static Integer[][] map;
    private static int player_pos_x, player_pos_y, goal_pos_x, goal_pos_y, move_count, helperInt, n, m;
    private static boolean endOfGame, found;
    private static String input, nick, helperString, lineToRemove, currentLine;
    private static Scanner sc = new Scanner(System.in), pl;
    private static File inputFile = new File("playerscores.txt");
    private static File tempFile = new File("myTempFile.txt");
    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static PlayerCube player_cube;
    private static Map<String, Integer> scores = new HashMap<String, Integer>();

    /**
     * Reads the map from a file called "map.txt".
     * 
     * @param map is a 2D array represents the map.
     */
    private static void readMap() {
        try {
            Scanner sc = new Scanner(new File("map.txt"));
            n = sc.nextInt();
            m = sc.nextInt();
            map = new Integer[m][n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();

                    if (map[i][j] == 0) {
                        player_pos_x = i;
                        player_pos_y = j;
                    } else if (map[i][j] == 8) {
                        goal_pos_x = i;
                        goal_pos_y = j;
                    }
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception: FILE NOT FOUND.");
        }
    }

    /**
     * Writes the current game state to the standard output.
     * 
     * @param map is the array.
     */
    private static void drawGameState() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void turnLeft() {
        if (player_pos_y != 0) {
            if (map[player_pos_x][player_pos_y - 1] == 6 || map[player_pos_x][player_pos_y - 1] == 8) {
                if (player_cube.player_leftside != 0) {
                    int face;
                    face = player_cube.player_face;

                    player_cube.player_face = player_cube.player_rightside;
                    player_cube.player_rightside = player_cube.player_under;
                    player_cube.player_under = player_cube.player_leftside;
                    player_cube.player_leftside = face;

                    map[player_pos_x][player_pos_y] = 6;
                    map[player_pos_x][player_pos_y - 1] = player_cube.player_face;
                    player_pos_y--;
                    move_count++;
                }
            } else {
                System.out.println("You cant go there. (Enter: something to continue.");
                sc.next();
            }  
        } else {
            System.out.println("You cant go there. (Enter: something to continue.");
            sc.next();
        }
    }

    private static void turnUp() {
        if (player_pos_x != 0) {
            if (map[player_pos_x - 1][player_pos_y] == 6 || map[player_pos_x - 1][player_pos_y] == 8) {
                if (player_cube.player_upside != 0) {
                    int face;
                    face = player_cube.player_face;

                    player_cube.player_face = player_cube.player_downside;
                    player_cube.player_downside = player_cube.player_under;
                    player_cube.player_under = player_cube.player_upside;
                    player_cube.player_upside = face;

                    map[player_pos_x][player_pos_y] = 6;
                    map[player_pos_x - 1][player_pos_y] = player_cube.player_face;
                    player_pos_x--;
                    move_count++;
                }
            } else {
                System.out.println("You cant go there. (Enter: something to continue.");
                sc.next();
            }  
        } else {
            System.out.println("You cant go there. (Enter: something to continue.");
            sc.next();
        }
    }

    private static void turnRight() {
        if (player_pos_y != n - 1) {
            if (map[player_pos_x][player_pos_y + 1] == 6 || map[player_pos_x][player_pos_y + 1] == 8) {
                if (player_cube.player_rightside != 0) {
                    int face;
                    face = player_cube.player_face;

                    player_cube.player_face = player_cube.player_leftside;
                    player_cube.player_leftside = player_cube.player_under;
                    player_cube.player_under = player_cube.player_rightside;
                    player_cube.player_rightside = face;

                    map[player_pos_x][player_pos_y] = 6;
                    map[player_pos_x][player_pos_y + 1] = player_cube.player_face;
                    player_pos_y++;
                    move_count++;
                }
            } else {
                System.out.println("You cant go there. (Enter: something to continue.");
                sc.next();
            }  
        } else {
            System.out.println("You cant go there. (Enter: something to continue.");
            sc.next();
        }
    }

    private static void turnDown() {
        if (player_pos_x != m - 1) {
            if (map[player_pos_x + 1][player_pos_y] == 6 || map[player_pos_x + 1][player_pos_y] == 8) {
                if (player_cube.player_downside != 0) {
                    int face;
                    face = player_cube.player_face;

                    player_cube.player_face = player_cube.player_upside;
                    player_cube.player_upside = player_cube.player_under;
                    player_cube.player_under = player_cube.player_downside;
                    player_cube.player_downside = face;

                    map[player_pos_x][player_pos_y] = 6;
                    map[player_pos_x + 1][player_pos_y] = player_cube.player_face;
                    player_pos_x++;
                    move_count++;
                }
            } else {
                System.out.println("You cant go there. (Enter: something to continue.");
                sc.next();
            }  
        } else {
            System.out.println("You cant go there. (Enter: something to continue.");
            sc.next();
        }
    }

    private static void turn(String i) {
        switch (i) {
            case "left":
                turnLeft();
                break;
            case "up":
                turnUp();
                break;
            case "right":
                turnRight();
                break;
            case "down":
                turnDown();
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    private static void startGame() {
        readMap();
        player_cube = new PlayerCube();
        move_count = 0;
        found = false;
        while (endOfGame == false) { 
            System.out.print("\033[H\033[2J");
            System.out.println("===============================");
            System.out.println("       ROLLING CUBE GAME       ");
            System.out.println("===============================\n");
            drawGameState();
            
            System.out.println("What's your next step?: \n");
            
            System.out.print("Enter: ");
            turn(input = sc.next());

            if (player_pos_x == goal_pos_x && player_pos_y == goal_pos_y) 
                endOfGame = true;
        }  

        System.out.print("\033[H\033[2J");
        System.out.println("===============================");
        System.out.println("       ROLLING CUBE GAME       ");
        System.out.println("===============================\n");
        
        drawGameState(); 
        
        System.out.println("Congratulations, you have completed the game in " + move_count + " steps!\n");

        while (!input.equals("menu") && !input.equals("scoreboard")) {
            System.out.println("If you want to go back to the menu the game enter: \"menu\"");
            System.out.println("If you want to check the scoreboard enter: \"scoreboard\"\n");
            System.out.print("Enter: ");
            input = sc.next();
        }

        try {
            pl = new Scanner(new File("playerscores.txt"));

            while (pl.hasNext() && pl.hasNextLine() && found == false) {
                helperString = pl.next();
                helperInt = pl.nextInt();

                if (helperString.equals(nick)) {
                    if (helperInt > move_count) {                       
                        reader = new BufferedReader(new FileReader(inputFile));
                        writer = new BufferedWriter(new FileWriter(tempFile));
                        
                        lineToRemove = helperString + " " + helperInt;
                        
                        while((currentLine = reader.readLine()) != null) {
                            if(currentLine.equals(lineToRemove)) {
                                System.out.println("MEGVAN MIT NEM KELL IRNI: " + currentLine);
                                continue;
                            }
                            writer.write(currentLine + System.getProperty("line.separator"));
                        }
                        writer.write(nick + " " + move_count + System.getProperty("line.separator"));
                        writer.close(); 
                        reader.close(); 
                        tempFile.renameTo(inputFile);
                        
                        found = true;
                    } else  
                        if (helperInt == move_count)
                            found = true;
                }
            }

            if (found == false) {
                reader = new BufferedReader(new FileReader(inputFile));
                writer = new BufferedWriter(new FileWriter(tempFile));

                while((currentLine = reader.readLine()) != null) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                writer.write(nick + " " + move_count + System.getProperty("line.separator"));
                writer.close(); 
                reader.close();
                tempFile.renameTo(inputFile);
            }
            pl.close();

            endOfGame = false;
        } catch (UnsupportedEncodingException e) {
            System.out.println("Exception: UnsupportedEncodingException");
        } catch (FileNotFoundException e) {
            System.out.println("Exception: FileNotFoundException");
        } catch (IOException e) {
            System.out.println("Exception: IOException\nCouldn't rewrite playerscores.txt.");
        }

        if(input.equals("menu")) 
            menuPage();
        else
            scoreBoardPage();

    }

    private static void starterPage() {
        System.out.print("\033[H\033[2J");
        System.out.println("===============================");
        System.out.println("WELCOME TO THE CUBE ROLLER GAME");
        System.out.println("===============================\n");

        System.out.println("Enter your nickname: \n");
        System.out.print("Enter: ");
        nick = sc.next();

        while (nick.equals("start") || nick.equals("help") || nick.equals("scoreboard")) {
            System.out.print("\033[H\033[2J");
            System.out.println("===============================");
            System.out.println("WELCOME TO THE CUBE ROLLER GAME");
            System.out.println("===============================\n");

            System.out.println("Enter your nickname: \n");
            System.out.print("Enter: ");
            nick = sc.next();
        }

        menuPage();
    }

    private static void menuPage() {
        System.out.print("\033[H\033[2J");
        System.out.println("===============================");
        System.out.println("WELCOME TO THE CUBE ROLLER GAME");
        System.out.println("===============================\n");

        System.out.println("If you want to start the game enter: \"start\".");
        System.out.println("For help enter: \"help\".");
        System.out.println("If you want to see the scoreboard enter: \"scoreboard\"\n");

        System.out.print("Enter: ");
        input = sc.next();

        while (!input.equals("start") && !input.equals("help") && !input.equals("scoreboard")){
            System.out.print("\033[H\033[2J");
            System.out.println("===============================");
            System.out.println("WELCOME TO THE CUBE ROLLER GAME");
            System.out.println("===============================\n");
    
            System.out.println("If you want to start the game enter: \"start\".");
            System.out.println("For help enter: \"help\".");
            System.out.println("If you want to see the scoreboard enter: \"scoreboard\"\n");
    
            System.out.print("Enter: ");
            input = sc.next();
        }

        switch (input) {
            case "start": 
                startGame(); 
                break; 
            case "help": 
                helpPage();
                break;
            case "scoreboard":
                scoreBoardPage();
                break;
        }
    }

    private static void helpPage() {
        System.out.print("\033[H\033[2J");
        System.out.println("==============================");
        System.out.println("             HELP              ");
        System.out.println("==============================\n");

        System.out.println("When the game starts you have to roll the cube the way you dont touch the ground with the red side of the cube (0).\n");
        System.out.println("The game wants you to enter a direction for each turn (left, right, up, down).\n");
        
        while (!input.equals("menu")) {
            System.out.println("If you want to go back to the menu the game enter: \"menu\"\n");
            System.out.print("Enter: ");
            input = sc.next();
        }

        menuPage();
    }

    private static void scoreBoardPage() {
        System.out.print("\033[H\033[2J");
        System.out.println("================================");
        System.out.println("           SCOREBOARD           ");
        System.out.println("================================\n");

        try {
            Scanner pl = new Scanner(new File("playerscores.txt"));

            while (pl.hasNext() && pl.hasNextLine()) {
                scores.put(pl.next(), pl.nextInt());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Exception: FILE NOT FOUND.");
        }

        scores = new SortingHashMap().sortMapByValue(scores);

        int nth = 0, max = 0;
        for (String i : scores.keySet()) {
            if (scores.get(i) > max) {
                nth++;
                max = scores.get(i);
            }
            System.out.println(nth + ". " + i + ": " + scores.get(i) + "\n");
        }
        
        while (!input.equals("menu")) {
            System.out.println("If you want to clear the scoreboard enter: \"clear\"");
            System.out.println("If you want to go back to the menu the game enter: \"menu\"\n");
            System.out.print("Enter: ");
            input = sc.next();

            if (input.equals("clear")) {
                try {
                    PrintWriter writer = new PrintWriter("playerscores.txt");
                    writer.print("");
                    writer.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Couldn't clear playerscores.txt\nException: FileNotFoundException");
                }
            }
        }

        menuPage();
    }

    public static void main(String[] args) {             
        starterPage();
        sc.close();
    }
}