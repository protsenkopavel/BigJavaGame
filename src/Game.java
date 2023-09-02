import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private final int rows;
    private final int columns;
    private final int amountOfEnemies;
    private final int transistorNeeded;
    private int turnsLeft;
    private int transistorsGathered;
    private final Field field;
    private boolean isGameFinished = false;
    private final int amountOfFlowers;
    private final ArrayList<Flower> flowerArrayList = new ArrayList<>();
    private final ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private final Random randomNumber = new Random();
    private Player player;
    private final Scanner scanner = new Scanner(System.in);
    private Boolean isIncorrectCommand = true;


    public Game(int rows, int columns, int amountOfEnemies,
                int transistorNeeded, int movesLeft, int getAmountOfFlowers) {
        this.rows = rows;
        this.columns = columns;
        this.amountOfEnemies = amountOfEnemies;
        this.transistorNeeded = transistorNeeded;
        this.turnsLeft = movesLeft;
        this.amountOfFlowers = getAmountOfFlowers;

        field = new Field(rows, columns);
    }

    public Field getField() {
        return this.field;
    }

    public ArrayList<Flower> getFlowerArrayList() {
        return this.flowerArrayList;
    }

    public void setTransistorsGathered(int transistorsToAdd) {
        this.transistorsGathered += transistorsToAdd;
    }

    public void fillFieldWithEmptyObjects() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field.setField(i, j, new Empty());
            }
        }

    }

    public void startGame() {

        possesPlayer();
        possesEnemies();
        possesFlowers();

        while (!isGameFinished) {

            showField();
            playerTurn();
            if (isIncorrectCommand) {
                incorrectCommand();
                continue;
            }
            computerTurn();
            checkIfGameNotFinished();

        }

    }

    private void incorrectCommand() {
        System.out.println("You've entered incorrect command");
    }

    private void possesFlowers() {

        generateFlowers();

    }

    private void possesEnemies() {

        generateEnemies();

    }

    private void possesPlayer() {

        int playerRowPosition = randomNumber.nextInt(rows);
        int playerColumnPosition = randomNumber.nextInt(columns);

        player = new Player(playerRowPosition, playerColumnPosition, this);

    }

    private void generateEnemies() {

        for (int i = amountOfEnemies - enemyArrayList.size(); i > 0; ) {

            int enemyRowPosition = randomNumber.nextInt(rows);
            int enemyColumnPosition = randomNumber.nextInt(columns);

            if (field.getField(enemyRowPosition, enemyColumnPosition)
                    instanceof Empty) {

                Enemy enemy = new Enemy(enemyRowPosition, enemyColumnPosition);
                field.setField(enemyRowPosition, enemyColumnPosition, enemy);
                enemyArrayList.add(enemy);
                i--;
            }
        }

    }

    private void generateFlowers() {

        for (int i = amountOfFlowers - flowerArrayList.size(); i > 0; ) {

            int flowerAmountOfTransistors = randomNumber.nextInt(9) + 1;
            int flowerRowPosition = randomNumber.nextInt(rows);
            int flowerColumnPosition = randomNumber.nextInt(columns);

            if (field.getField(flowerRowPosition, flowerColumnPosition)
                    instanceof Empty) {

                Flower flower = new Flower(flowerAmountOfTransistors, flowerRowPosition, flowerColumnPosition);
                field.setField(flowerRowPosition, flowerColumnPosition, flower);
                flowerArrayList.add(flower);
                i--;

            }
        }

    }

    private void checkIfGameNotFinished() {

        if (turnsLeft == 0) {
            System.out.println("No more turns left, you lost!");
        } else if (transistorsGathered >= 100) {
            System.out.println("You have gathered the required number of transistors, congrats!");
            isGameFinished = true;
        }
    }

    private void playerTurn() {

        System.out.println("Please enter command");
        String command = scanner.nextLine();
        isIncorrectCommand = player.makeMove(command);

    }

    private void computerTurn() {

        enemyMove();
        generateFlowers();
        turnsLeft--;

    }

    private void enemyMove() {

        int rowIndex;
        int columnIndex;
        int newRowIndex;
        int newColumnIndex;
        int regenerateIndex = 0;
        boolean isNeededToRegenerate;

        for (Enemy enemy : enemyArrayList) {

            rowIndex = enemy.getRowIndex();
            columnIndex = enemy.getColumnIndex();

            int triesToGenerate = 10;
            do {

                int deltaRow = randomNumber.nextInt(3) - 1;
                int deltaColumn = randomNumber.nextInt(3) - 1;

                newRowIndex = rowIndex + deltaRow;
                newColumnIndex = columnIndex + deltaColumn;

                if ((newRowIndex < 0) || (newColumnIndex < 0) || (newRowIndex >= field.getRows())
                        || (newColumnIndex >= field.getColumns())
                        || field.getField(newRowIndex, newColumnIndex) instanceof Player
                        || field.getField(newRowIndex, newColumnIndex) instanceof Enemy) {
                    regenerateIndex++;
                    isNeededToRegenerate = true;
                } else {
                    if (field.getField(newRowIndex, newColumnIndex) instanceof Flower flower) {
                        flowerArrayList.remove(flower);
                    }
                    isNeededToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);
                }

            } while (isNeededToRegenerate && regenerateIndex <= triesToGenerate);

        }
    }

    private boolean swapEnemy(int rowIndex, int columnIndex, int newRowIndex, int newColumnIndex, Enemy enemy) {
        field.setField(newRowIndex, newColumnIndex, enemy);
        field.setField(rowIndex, columnIndex, new Empty());
        enemy.setRowIndex(newRowIndex);
        enemy.setColumnIndex(newColumnIndex);
        return false;
    }

    private void showField() {

        System.out.println("\n\nTurns left: " + turnsLeft
                + ", transistors gathered: " + transistorsGathered
                + "/" + transistorNeeded);
        field.showField();

    }
}
