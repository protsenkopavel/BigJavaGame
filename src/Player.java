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

    public void makeMove(String command) {

        switch (command) {
            case MOVE_LEFT -> movePlayer(0, -1);
            case MOVE_RIGHT -> movePlayer(0, 1);
            case MOVE_UP -> movePlayer(-1, 0);
            case MOVE_DOWN -> movePlayer(1, 0);
            case NO_MOVE -> movePlayer(0, 0);
            default -> showError(command);
        }

    }

    private void showError(String command) {

        System.out.println("Sorry, there's no " + command +
                " command, please try again");

    }

    public void movePlayer(int deltaRowIndex, int deltaColumnIndex) {

        int newRowIndex = rowIndex + deltaRowIndex;
        int newColumnIndex = columnIndex + deltaColumnIndex;

        if ((newRowIndex > 0) && (newRowIndex < field.getSizeY())
                && (newColumnIndex > 0) && (newColumnIndex < field.getSizeX())
                && !((field.getFieldable(newColumnIndex, newRowIndex) instanceof Enemy))) {

            if (field.getFieldable(newColumnIndex, newRowIndex) instanceof Flower) {

                Flower flower = (Flower) field.getFieldable(newColumnIndex, newRowIndex);
                game.setTransistorsGathered(flower.getTransistors());
                game.getFlowerArrayList().remove(flower);
                swapPlayer(newRowIndex, newColumnIndex);

            }

            if (field.getFieldable(newColumnIndex, newRowIndex) instanceof Enemy) {
                swapPlayer(newRowIndex, newColumnIndex);
            }

        }

    }

    private void swapPlayer(int newRowIndex, int newColumnIndex) {
        field.setFieldable(newColumnIndex, newRowIndex, this);
        field.setFieldable(columnIndex, rowIndex, new Empty());
        rowIndex = newRowIndex;
        columnIndex = newColumnIndex;
    }
}
