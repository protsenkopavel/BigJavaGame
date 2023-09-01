import java.util.ArrayList;
import java.util.Random;

public class Game {

    private int rows;
    private int columns;
    private int amountOfEnemies;
    private int transistorNeeded;
    private int turnsLeft;
    private int transistorsGathered;
    private Field field;
    private boolean isGameFinished = false;
    private int amountOfFlowers;
    private ArrayList<Flower> flowerArrayList = new ArrayList<Flower>();
    private Random randomNumber = new Random();

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

    public Field getField(){
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
                field.setFieldable(i, j, new Empty());
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
            computerTurn();
            checkIfGameNotFinished();

        }

    }

    private void possesFlowers() {

        generateFlowers();

    }

    private void possesEnemies() {

    }

    private void possesPlayer() {

    }

    private void generateFlowers() {

        for (int i = amountOfFlowers - flowerArrayList.size(); i > 0; ) {

            int flowerAmountOfTransistors = randomNumber.nextInt(9) + 1;
            int flowerRowPosition = randomNumber.nextInt(rows);
            int flowerColumnPosition = randomNumber.nextInt(columns);

            if (field.getFieldable(flowerRowPosition, flowerColumnPosition) instanceof Player) {

                transistorsGathered = transistorsGathered + flowerAmountOfTransistors;
                i--;

            } else if (field.getFieldable(flowerRowPosition, flowerColumnPosition)
                    instanceof Enemy) {

                Flower flower = new Flower(flowerAmountOfTransistors, flowerRowPosition, flowerColumnPosition);
                field.setFieldable(flowerRowPosition, flowerColumnPosition, flower);
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

    private void computerTurn() {

    }

    private void playerTurn() {

    }

    private void showField() {

        System.out.println("\n\nTurns left: " + turnsLeft
        + ", transistors gathered: " + transistorsGathered
        + "/" + transistorNeeded);
        field.showField();

    }
}
