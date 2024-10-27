package chess_engine.model;

public enum Disc {
    WHITE(1),
    BLACK(-1),
    NONE(0);

    private int value;
    Disc(int value) {
        this.value = value;
    }

    public int value() { return this.value; }
}
