public class Field {

    private final int rows;
    private final int columns;

    private final Fieldable[][] field;

    public Field(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        field = new Fieldable[rows][columns];
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setField(int x, int y, Fieldable object) {

        field[x][y] = object;

    }

    public Fieldable getField(int x, int y) {
        return field[x][y];
    }

    public void showField() {

        System.out.println();

        for (int i = 0; i < rows; i++) {

            System.out.println();

            for (int j = 0; j < columns; j++) {

                System.out.print(field[i][j].getSymbol());

            }

        }

        System.out.println();

    }
}
