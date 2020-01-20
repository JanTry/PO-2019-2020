package sample;

import java.util.LinkedList;


public class SquareMap {
    private Block[][] map;
    private LinkedList<Block> blockList = new LinkedList<Block>();
    private LinkedList<Block> deleteList = new LinkedList<Block>();
    private int size;

    SquareMap(int size) {
        map = new Block[size][size];
        this.size = size;
    }

    int atPosition(int x, int y) {
        if (this.free(new Position(x, y))) {
            return 0;
        } else
            return this.map[x][y].value;
    }

    boolean add(int value, Position position) {
        if (map[position.getX()][position.getY()] != null) return false;
        map[position.getX()][position.getY()] = new Block(position, value);
        this.blockList.add(map[position.getX()][position.getY()]);
        return true;
    }

    private void join(Block block1, Block block2) {
        if (block1 == null || block2 == null) return;
        if (block1.value == block2.value) {
            this.map[block2.position.x][block2.position.y] = null;
            block1 = block1.join(block2);
            this.deleteList.add(block2);
        }
    }

    private boolean free(Position position) {
        if (!this.bound(position)) return false;
        return this.map[position.x][position.y] == null;
    }


    boolean bound(Position position) {
        boolean flag = true;
        if (position.x >= this.size) flag = false;
        if (position.y >= this.size) flag = false;
        if (position.x < 0) flag = false;
        if (position.y < 0) flag = false;
        return flag;
    }

    private void moveBlock(Position fromPosition, Position toPosition) {
        if (!this.free(fromPosition) && this.free(toPosition)) {
            map[toPosition.x][toPosition.y] = map[fromPosition.x][fromPosition.y];
            map[fromPosition.x][fromPosition.y] = null;
            map[toPosition.x][toPosition.y].setPosition(toPosition);
        }
    }


    void moveDirection(Direction dir) {
        for (int times = 0; times < 16; times++) {
            for (Block each : blockList) {
                if (!each.processed) {
                    Position newPosition = each.position.add(dir.toPosition());
                    int i = 0;
                    while (this.free(newPosition) && this.bound(newPosition) && i <= 4) {
                        this.moveBlock(each.position, newPosition);
                        newPosition = each.position.add(dir.toPosition());
                        i++;
                    }
                    if (bound(each.position) && bound(newPosition))
                        join(map[newPosition.x][newPosition.y], map[each.position.x][each.position.y]);
                    each.processed = true;
                }
            }


            for (Block each : this.blockList) {
                each.processed = false;
            }
            for (Block each : this.deleteList) {
                this.blockList.remove(each);
            }
        }

    }
}
