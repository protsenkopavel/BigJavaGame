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
                            "rows: " + Main.rows +
                            "\ncolumns: " + Main.columns +
                            "\nenemies: " + Main.amountOfEnemies +
                            "\ntransistors: " + Main.transistorNeeded +
                            "\nmoves: " + Main.moves +
                            "\nflowers: " + Main.AmountOfFlowers);
                    break;
                case 2:

                    changeParams();

                    break;
                case 3:
                    break;
                default:
                    System.out.println("Command nor recognized!");

            }

        } while (command != 3);

    }

    private static void changeParams() {
        String value;

        scanner.nextLine();
        System.out.println("Enter new value of rows, current value ["+Main.rows+"]: ");

        value = scanner.nextLine();
        Main.rows = changeValues(value);

        System.out.println("Enter new value of columns, current value ["+Main.columns+"]: ");

        value = scanner.nextLine();
        Main.columns = changeValues(value);

        System.out.println("Enter new value of enemies, current value ["+Main.amountOfEnemies+"]: ");

        value = scanner.nextLine();
        Main.amountOfEnemies = changeValues(value);

        System.out.println("Enter new value of transistors, current value ["+Main.transistorNeeded+"]: ");
        value = scanner.nextLine();
        Main.transistorNeeded = changeValues(value);

        System.out.println("Enter new value of moves, current value ["+Main.moves+"]: ");
        value = scanner.nextLine();
        Main.moves = changeValues(value);

        System.out.println("Enter new value of flowers, current value ["+Main.AmountOfFlowers +"]: ");
        value = scanner.nextLine();
        Main.AmountOfFlowers = changeValues(value);

        if (isValuesNotPlayable()) {
            System.out.println("Values aren't playable, press enter and try again!");
            changeParams();
        }
    }

    private static Boolean isValuesNotPlayable(){

        double fieldSize = Main.rows * Main.columns;
        double allGameObjects = Main.amountOfEnemies + Main.AmountOfFlowers + 1;

        return ((allGameObjects / fieldSize) > 0.75);

    }

    private static int changeValues(String value) {
        if (!value.isBlank()){
            return Integer.parseInt(value);
        }
        return 0;
    }



}
