public class Player implements Fieldable {
    private final static String MOVE_LEFT = "a";
    private final static String MOVE_RIGHT = "d";
    private final static String MOVE_UP = "w";
    private final static String MOVE_DOWN = "s";
    private final static String NO_MOVE = "e";

    private int rowIndex;
    private int columnIndex;
    private Field field;
    private Game game;

    @Override
    public String getSymbol() {
        return " @ ";
    }

    public Player(int rowIndex, int columnIndex, Game game) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.game = game;
        this.field = game.getField();
        field.setFieldable(rowIndex, columnIndex, this);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
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

        if ((newRowIndex >= 0) && (newRowIndex <= field.getSizeX())
                && (newColumnIndex >= 0) && (newColumnIndex <= field.getSizeY())
                && !((field.getFieldable(newRowIndex, newColumnIndex) instanceof Enemy))) {

            if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Flower) {

                Flower flower = (Flower) field.getFieldable(newRowIndex, newColumnIndex);
                game.setTransistorsGathered(flower.getTransistors());
                game.getFlowerArrayList().remove(flower);
                swapPlayer(newRowIndex, newColumnIndex);

            }

            if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Enemy) {
                swapPlayer(newColumnIndex, newRowIndex);
            }
            return false;

        }
        else {
            return true;
        }

    }

    private void swapPlayer(int newRowIndex, int newColumnIndex) {
        field.setFieldable(newRowIndex, newColumnIndex, this);
        field.setFieldable(rowIndex, columnIndex, new Empty());
        rowIndex = newRowIndex;
        columnIndex = newColumnIndex;
    }
}
