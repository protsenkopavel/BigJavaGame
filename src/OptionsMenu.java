import java.util.Scanner;

public class OptionsMenu {

    static Scanner scanner = new Scanner(System.in);
    static int command;
    public static void showOptionsMenu(){

        do {

            System.out.println("""
                    Make your choice:
                    1: Show current settings
                    2: Change settings
                    3: Exit""");
            command = scanner.nextInt();

            switch (command){

                case 1:
                    System.out.println("\nCurrent settings:\n" +
                            "rows: " + Main.sizeX +
                            "\ncolumns: " + Main.sizeY +
                            "\nenemies: " + Main.amountOfEnemies +
                            "\ntransistors: " + Main.transistorNeeded +
                            "\nmoves: " + Main.moves);
                    break;
                case 2:
                    System.out.println("Enter new value of rows: ");
                    Main.sizeX = scanner.nextInt();
                    System.out.println("Enter new value of columns: ");
                    Main.sizeY = scanner.nextInt();
                    System.out.println("Enter new value of enemies: ");
                    Main.amountOfEnemies = scanner.nextInt();
                    System.out.println("Enter new value of transistors: ");
                    Main.transistorNeeded = scanner.nextInt();
                    System.out.println("Enter new value of moves: ");
                    Main.moves = scanner.nextInt();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Command nor recognized!");

            }

        } while (command != 3);

    }

}
