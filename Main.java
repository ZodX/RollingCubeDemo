import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * game
 */
public class Main {

    private static Integer[][] map = new Integer[5][5];
    private static int player_pos_x, player_pos_y, player_face = 0, player_leftside = 1;
    private static boolean endOfGame = false;
    private static String input; 
    private static Scanner sc = new Scanner(System.in);

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
        if (map[player_pos_x][player_pos_y - 1] == 6) {
            switch (player_face) {
                case 0:

                    switch (player_leftside) {
                        case 1:
                            player_face = 3;
                            break;

                        case 2:
                            player_face = 4;
                            break;

                        case 3:
                            player_face = 1;
                            break;

                        case 4:
                            player_face = 2;
                            break;
                    }

                    player_leftside = 0;
                    map[player_pos_x][player_pos_y] = 6;
                    map[player_pos_x][player_pos_y - 1] = player_face;
                    break;

                //TODO 
                case 1:

                    break;
                    
                case 2:

                    break;

                case 3:

                    break;

                case 4:

                    break;
            }
        } else {
            System.out.println("You cant go there.");
        }
    }

    private static void startGame() {
        while (endOfGame == false) {
                
            System.out.print("\033[H\033[2J");
            System.out.println("===============================");
            System.out.println("       ROLLING CUBE GAME       ");
            System.out.println("===============================\n");
            
            drawGameState();

            System.out.println("What's your next step?: ");
            input = sc.next();

            //   Turn / Roll
            switch (input) {
                case "left":
                    turnLeft();
                    break;
                case "up":

                    break;
                case "right":

                    break;
                case "down":

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