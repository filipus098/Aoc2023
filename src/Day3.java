import java.util.ArrayList;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {
        //p1
        List<String> strings = Utils.readAllLineesAOCFile(3);

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
        return 1;
    }
    private static Integer processPart2Line(String s) {
        return 1;
    }
}
