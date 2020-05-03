import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * game
 */
public class Main {

    private static Integer[][] map = new Integer[5][5];
    private static int player_pos_x, player_pos_y, goal_pos_x, goal_pos_y, move_count = 0;
    private static boolean endOfGame = false;
    private static String input, nick;
    private static Scanner sc = new Scanner(System.in);
    private static PlayerCube player_cube = new PlayerCube();
    private static Map<String, Integer> scores = new HashMap<String, Integer>();

    /**
     * Reads the map from a file called "map.txt".
     * 
     * @param map is a 2D array represents the map.
     */
    private static void readMap() {
        try {
            Scanner sc = new Scanner(new File("map.txt"));

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void turnLeft() {
        if (player_pos_y != 0)
            if (map[player_pos_x][player_pos_y - 1] == 6 || map[player_pos_x - 1][player_pos_y] == 8) {
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
            } else
                System.out.println("You cant go there.");
        else
            System.out.println("You cant go there.");
    }

    private static void turnUp() {
        if (player_pos_x != 0)
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
            } else
                System.out.println("You cant go there.");
        else
            System.out.println("You cant go there.");
    }

    private static void turnRight() {
        if (player_pos_y != 4)
            if (map[player_pos_x][player_pos_y + 1] == 6 || map[player_pos_x + 1][player_pos_y] == 8) {
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
            } else
                System.out.println("You cant go there.");
        else
            System.out.println("You cant go there.");
    }

    private static void turnDown() {
        if (player_pos_x != 4)
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
            } else
                System.out.println("You cant go there.");
        else
            System.out.println("You cant go there.");
    }

    private static void turn(String string) {
        switch (input) {
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
            PrintWriter writer = new PrintWriter("playerscores.txt", "UTF-8");
            writer.println(nick + " " + move_count);
            writer.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Exception: UnsupportedEncodingException");
        } catch (FileNotFoundException e) {
            System.out.println("Exception: FileNotFoundException");
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
            default: 
                System.out.println("Invalid command.");
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
            System.out.println("If you want to go back to the menu the game enter: \"menu\"\n");
            System.out.print("Enter: ");
            input = sc.next();
        }

        menuPage();
    }

    public static void main(String[] args) {             
        readMap();
        
        starterPage();
             
        sc.close();
    }
}