package chess_engine.utility;

public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "x: " + this.x + ", y: " + this.y;
    }
}
