public class OptionParser {
    public MoveDirection[] parse(String tab[]){
        int size=tab.length;
        MoveDirection output[]=new MoveDirection[size];
        int counter=0;
        for(String element:tab){
            switch(element){
                case "forward":
                case "f":
                    output[counter]=MoveDirection.FORWARD;
                    counter++;
                    break;
                case "backward":
                case "b":
                    output[counter]=MoveDirection.BACKWARD;
                    counter++;
                    break;
                case "right":
                case "r":
                    output[counter]=MoveDirection.RIGHT;
                    counter++;
                    break;
                case "left":
                case "l":
                    output[counter]=MoveDirection.LEFT;
                    counter++;
                    break;
                default:
                    throw new IllegalArgumentException(element + " is not legal move specification");
            }
        }
        MoveDirection finalOutput[]=new MoveDirection[counter];
        for(int i=0;i<counter;i++)finalOutput[i]=output[i];
        return finalOutput;
    }
}
