public class Main {
    public static void main(String[] args) {
//        Animal kot = new Animal();
////        String temp[]=new String[5];
////        temp[0]="forward";
////        temp[1]="for";
////        temp[2]="forward";
////        temp[3]="right";
////        temp[4]="right";
////
////        OptionParser tool=new OptionParser();
////        MoveDirection tab[]=tool.parse(temp);
////        for (MoveDirection element:tab) {
////            kot=kot.move(element);
////        }
////        System.out.println(kot.toString());
        String[] test={"f","b","r","l","f","f","l","r","f","f","f","f","f","f","f","f"};
        MoveDirection[] directions = new OptionParser().parse(test);
        IWorldMap map = new RectangularMap(10, 5);
        map.place(new Animal(map));
        map.place(new Animal(map,new vector2d(3,4)));
        map.run(directions);
        map.draw();
    }
}
