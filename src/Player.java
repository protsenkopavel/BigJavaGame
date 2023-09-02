public class Player implements Fieldable {
    private final static String MOVE_LEFT = "a";
    private final static String MOVE_RIGHT = "d";
    private final static String MOVE_UP = "w";
    private final static String MOVE_DOWN = "s";
    private final static String NO_MOVE = "e";

    private int rowIndex;
    private int columnIndex;
    private final Field field;
    private final Game game;

    @Override
    public String getSymbol() {
        return " @ ";
    }

    public Player(int rowIndex, int columnIndex, Game game) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.game = game;
        this.field = game.getField();
        field.setField(rowIndex, columnIndex, this);
    }

    public Boolean makeMove(String command) {

        Boolean isIncorrectMove = true;

        switch (command) {
            case MOVE_LEFT -> isIncorrectMove = movePlayer(0, -1);
            case MOVE_RIGHT -> isIncorrectMove = movePlayer(0, 1);
            case MOVE_UP -> isIncorrectMove = movePlayer(-1, 0);
            case MOVE_DOWN -> isIncorrectMove = movePlayer(1, 0);
            case NO_MOVE -> isIncorrectMove = false;
            default -> showError(command);
        }
        return isIncorrectMove;
    }

    private void showError(String command) {

        System.out.println("Sorry, there's no " + command +
                " command, please try again");

    }

    public Boolean movePlayer(int deltaRowIndex, int deltaColumnIndex) {

        int newRowIndex = rowIndex + deltaRowIndex;
        int newColumnIndex = columnIndex + deltaColumnIndex;

        if ((newRowIndex >= 0) && (newRowIndex < field.getRows())
                && (newColumnIndex >= 0) && (newColumnIndex < field.getColumns())
                && !((field.getField(newRowIndex,newColumnIndex)) instanceof Enemy)) {
            if (field.getField(newRowIndex, newColumnIndex) instanceof Flower flower) {

                game.setTransistorsGathered(flower.getTransistors());
                game.getFlowerArrayList().remove(flower);
                swapPlayer(newRowIndex, newColumnIndex);
            }

            if (field.getField(newRowIndex, newColumnIndex) instanceof Empty) {
                swapPlayer(newRowIndex, newColumnIndex);
            }
            return false;
        }
        else {
            return true;
        }
    }

    private void swapPlayer(int newRowIndex, int newColumnIndex) {
        field.setField(newRowIndex, newColumnIndex, this);
        field.setField(rowIndex, columnIndex, new Empty());
        rowIndex = newRowIndex;
        columnIndex = newColumnIndex;
    }
}
