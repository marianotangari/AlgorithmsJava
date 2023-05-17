public class Vertex<T> {

    enum Color { WHITE, GRAY, BLACK }

    private Color color;
    private int distance;
    private Vertex<T> prev;
    private T value;

    public Vertex(Color color, T value) {
        this.color = color;
        this.distance = -1;
        this.prev = null;
        this.value = value;
    }

    private Vertex() {
    }

    public T getValue() {
        return value;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex<T> getPrev() {
        return prev;
    }

    public void setPrev(Vertex<T> vertex) {
        this.prev = vertex;
    }
}