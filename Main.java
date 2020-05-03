import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * game
 */
public class Main {

    private static Integer[][] map = new Integer[5][5];
    private static int player_pos_x, player_pos_y/* , player_face = 0, player_leftside = 1 */;
    private static boolean endOfGame = false;
    private static String input; 
    private static Scanner sc = new Scanner(System.in);
    private static PlayerCube player_cube = new PlayerCube();

    /**
     * Reads the map from a file called "map.txt".
     * @param map is a 2D array represents the map.
     */
    private static void readMap() {
        try {
            File file = new File("map.txt");
            Scanner sc = new Scanner(file);

            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    map[i][j] = sc.nextInt();

                    if (map[i][j] == 0) {
                        player_pos_x = i;
                        player_pos_y = j;
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
                } 
            } else 
                System.out.println("You cant go there.");
        else
            System.out.println("You cant go there.");
    }
   
    private static void turnUp() {
        if(player_pos_x != 0)
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
                } 
            } else 
            System.out.println("You cant go there.");
            else
            System.out.println("You cant go there.");
        }
        
    private static void turnDown() {
        if(player_pos_x != 4)
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
                } 
            } else 
                System.out.println("You cant go there.");
        else
            System.out.println("You cant go there.");
    }

    private static void startGame() {
        while (endOfGame == false) {
                
            System.out.print("\033[H\033[2J");
            System.out.println("===============================");
            System.out.println("       ROLLING CUBE GAME       ");
            System.out.println("===============================\n");
            System.out.println("player_pos_x: " + player_pos_x + "\nplayer_pos_y: " + player_pos_y + "\n");
            
            drawGameState();

            System.out.println("What's your next step?: ");
            input = sc.next();

            //   Turn / Roll
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
    }

    public static void main(String[] args) {
        readMap();

        System.out.print("\033[H\033[2J");
        System.out.println("===============================");
        System.out.println("WELCOME TO THE CUBE ROLLER GAME");
        System.out.println("===============================\n");

        System.out.println("If you want to start the game enter: \"start\".");
        System.out.println("For help enter: \"help\".");

        
        input = sc.next();

        switch (input) {
            case "start": 
                startGame(); 
                break; 
            case "help": 
                System.out.print("\033[H\033[2J");
                System.out.println("===============================");
                System.out.println("             HELP              ");
                System.out.println("===============================\n");

                System.out.println("When the game starts you have to roll the cube the way you dont touch the ground with the red side of the cube (0).\n");
                System.out.println("The game wants you to enter a direction for each turn (left, right, up, down).\n");
                
                while (!input.equals("start")) {
                    System.out.println("If you want to start the game enter: \"start\"");
                    input = sc.next();
                    System.out.println(input);
                }

                startGame();
                break;
            default: 
                System.out.println("Invalid command.");
                break;
        }
             
        sc.close();
    }
}