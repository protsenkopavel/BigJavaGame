import java.util.Objects;

public class Flower implements Fieldable{

    private final int transistors;
    private final int rowIndex;
    private final int columnIndex;

    public Flower(int transistors, int rowIndex, int columnIndex) {
        this.transistors = transistors;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getTransistors() {
        return transistors;
    }

    @Override
    public String getSymbol() {
        return " " + transistors + " ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flower flower = (Flower) o;
        return rowIndex == flower.rowIndex && columnIndex == flower.columnIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rowIndex, columnIndex);
    }
}
