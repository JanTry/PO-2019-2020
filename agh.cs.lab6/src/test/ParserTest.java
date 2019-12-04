//import org.junit.Test;
//import static org.junit.Assert.assertEquals;

//OUTDATED

//public class ParserTest {
//    @Test
//    public void first(){
//        String[] test={"forward","forward","forward","right","r"};
//        Animal kot=new Animal();
//        OptionParser tool=new OptionParser();
//        MoveDirection tab[]=tool.parse(test);
//        for (MoveDirection element:tab) {
//            kot=kot.move(element);
//        }
//        assertEquals(kot.getX(),2);
//        assertEquals(kot.getY(),4);
//        assertEquals(kot.getdir(),MapDirection.SOUTH);
//    }
//    @Test
//    public void second(){
//        String[] test={"f","f","r","l","l","f","f","b","l"};
//        Animal kot=new Animal();
//        OptionParser tool=new OptionParser();
//        MoveDirection tab[]=tool.parse(test);
//        for (MoveDirection element:tab) {
//            kot=kot.move(element);
//        }
//        assertEquals(kot.getX(),1);
//        assertEquals(kot.getY(),4);
//        assertEquals(kot.getdir(),MapDirection.SOUTH);
//    }
//    @Test
//    public void third(){
//        String[] test={"fo","for","forw","forwa","forwar","right","r","r","rig","rl"};
//        Animal kot=new Animal();
//        OptionParser tool=new OptionParser();
//        MoveDirection tab[]=tool.parse(test);
//        for (MoveDirection element:tab) {
//            kot=kot.move(element);
//        }
//        assertEquals(kot.getX(),2);
//        assertEquals(kot.getY(),2);
//        assertEquals(kot.getdir(),MapDirection.WEST);
//    }
//}
