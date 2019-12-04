import java.util.*;

public class UnboundedMap extends AbstractWorldMap {
    public UnboundedMap(List<HayStack> hays) {
        for (HayStack i : hays) {
            if (!canMoveTo(i.getPosition()))
                throw new IllegalArgumentException("position " + i.getPosition().x + " " + i.getPosition().y + " is already occupied");
            else {
                this.hays.add(i);
                this.hashMap.put(i.getPosition(), i);
                this.boundary.add(i);
            }
        }
    }
}