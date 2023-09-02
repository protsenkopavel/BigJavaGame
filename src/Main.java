import java.util.Scanner;

public class Main {

    public static int rows = 12;
    public static int columns = 20;
    public static int amountOfEnemies = 20;
    public static int transistorNeeded = 100;
    public static int moves = 40;
    public static int AmountOfFlowers = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command;

        do {

            System.out.println("Welcome to BigJavaGame v1.0 \nPlease make your choice:");
            System.out.println("1: Start new game");
            System.out.println("2: Options");
            System.out.println("3: Credits");
            System.out.println("4: Exit");

            command = scanner.nextLine();

            switch (command){
                case "1":
                    startNewGame();
                    break;
                case "2":
                    OptionsMenu.showOptionsMenu();
                    break;
                case "3":
                    showCredits();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Command not recognized! Please, input correct command");
            }

        } while (!command.equals("4"));
    }

    private static void startNewGame() {

        Game game = new Game(rows, columns, amountOfEnemies,
                transistorNeeded, moves, AmountOfFlowers);
        game.fillFieldWithEmptyObjects();
        game.startGame();

    }

    private static void showCredits() {
        System.out.println("""

                Created by Dmitry Finashkin, copied by Pavel Protsenko
                version 1.0, last modified 31.08.23
                """);
    }
}