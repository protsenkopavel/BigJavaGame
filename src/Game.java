public class Game {

    private int sizeX;
    private int sizeY;
    private int amountOfEnemies;
    private int transistorNeeded;
    private int movesLeft;
    private int flowersGathered;
    private Field field;
    private boolean isGameFinished = false;

    public Game(int sizeX, int sizeY, int amountOfEnemies,
                int transistorNeeded, int movesLeft) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.amountOfEnemies = amountOfEnemies;
        this.transistorNeeded = transistorNeeded;
        this.movesLeft = movesLeft;

        field = new Field(sizeX, sizeY);
    }

    public void fillFieldWithEmptyObjects(){

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                field.setFieldable(i, j, new Empty());
            }
        }

    }

    public void startGame(){

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

    }

    private void possesEnemies() {

    }

    private void possesPlayer() {

    }

    private void checkIfGameNotFinished() {
    }

    private void computerTurn() {
        
    }

    private void playerTurn() {
        
    }

    private void showField() {
        
    }
}
