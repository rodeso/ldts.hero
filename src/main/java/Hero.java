public class Hero {
    private Position position;
    public Hero(Position p) {
        position = p;
    }
    public void setPosition(Position p) {
        position = p;
    }
    public int getX() {
        return position.getX();
    }
    public int getY() {
        return position.getY();
    }
    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }
    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }
    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }


}
