package sample;

public class Block {
    boolean processed = false;
    int value;
    Position position;

    Block(Position position, int value) {
        this.value = value;
        this.position = position;
    }

    Block join(Block block) {
        this.value = this.value * 2;
        block.value = 0;
        return this;
    }

    Block move(Direction dir, SquareMap map) {
        Position p = this.position.add(dir.toPosition());
        if (map.bound(p)) this.position.add(dir.toPosition());
        return this;
    }

    void setPosition(Position position) {
        this.position = position;
    }
}
