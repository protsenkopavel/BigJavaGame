import java.util.Scanner;

public class Main {

    public static int sizeX = 3;
    public static int sizeY = 4;
    public static int amountOfEnemies = 10;
    public static int transistorNeeded = 100;
    public static int moves = 40;

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

        Game game = new Game(sizeX, sizeY, amountOfEnemies,
                transistorNeeded, moves);
        game.fillFieldWithEmptyObjects();
        game.startGame();

    }

    private static void openOptionsMenu() {

    }

    private static void showCredits() {
        System.out.println("""

                Created by Dmitry Finashkin, copied by Pavel Protsenko
                version 1.0, last modified 31.08.23
                """);
    }
}