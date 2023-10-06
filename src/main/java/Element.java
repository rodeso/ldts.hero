public abstract class Element {
    //attributes
    private Position position;
    //constructor
    public Element(Position p) {
        position = p;
    }
    //setter
    public void setPosition(Position p) {
        position = p;
    }
    //getter
    public Position getPosition() {
        return position;
    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
}
