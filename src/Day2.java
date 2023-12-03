import java.util.ArrayList;
import java.util.List;

public class Day2 {

    static final int REDS = 12;
    static final int GREENS = 13;
    static final int BLUES = 14;

    public static void main(String[] args) {
        List<String> strings = Utils.readAllLineesAOCFile(2);

        List<Integer> values = new ArrayList<>();
        List<Integer> values2 = new ArrayList<>();
        //p1
        strings.stream().forEach((s) -> values.add(processPart1Line(s)));
        System.out.println(values.stream().reduce(0,Integer::sum));

        //p2
        strings.stream().forEach((s) -> values2.add(processPart2Line(s)));
        System.out.println(values2.stream().reduce(0,Integer::sum));

    }

    private static Integer processPart1Line(String s) {
        String[] splits = s.split(":");
        int id = Integer.parseInt(splits[0].split(" ")[1]);
        String[] balls = splits[1].replace(";",",").split(",");

        for(String ball : balls){
            String[] ballComponents = ball.split(" ");
            //index 0 empty
            int value= 0;
            switch (ballComponents[2]){
                case "green":
                    value = GREENS;
                    break;
                case "red":
                    value = REDS;
                    break;
                case "blue":
                    value=BLUES;
                    break;
            }
            if(Integer.valueOf(ballComponents[1]) > value){
                return 0;
            }
        }

        return id;
    }
    private static Integer processPart2Line(String s) {
        String[] splits = s.split(":");
        String[] balls = splits[1].replace(";",",").split(",");

        int maxRed=0;
        int maxBlue=0;
        int maxGreen=0;
        for(String ball : balls){
            String[] ballComponents = ball.split(" ");
            //index 0 empty
            int value=Integer.valueOf(ballComponents[1]);
            switch (ballComponents[2]){
                case "green":
                    maxGreen = maxGreen<value ? value : maxGreen;
                    break;
                case "red":
                    maxRed = maxRed<value ? value : maxRed;
                    break;
                case "blue":
                    maxBlue = maxBlue<value ? value : maxBlue;
                    break;
            }
        }
        System.out.println(maxBlue*maxGreen*maxRed);
        return maxBlue*maxGreen*maxRed;
    }
}
