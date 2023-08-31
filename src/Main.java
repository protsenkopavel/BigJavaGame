import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command;

        do {

            System.out.println("Welcome to BigJavaGame v1.0 \n Please make your choice:");
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
                    openOptionsMenu();
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

    }

    private static void openOptionsMenu() {

    }

    private static void showCredits() {
        System.out.println("\nCreated by Dmitry Finashkin, copied by Pavel Protsenko\n" +
                "version 1.0, last modified 31.08.23\n");
    }
}