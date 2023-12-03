import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day1 {
    public static void main(String[] args) {
        //p1
        List<String> strings = Utils.readAllLineesAOCFile(1);
        List<Integer> values = new ArrayList<>();

        //p1
        strings.stream().forEach((s) -> values.add(processPart1Line(s)));
        System.out.println(values.stream().reduce(0,Integer::sum));

        //p2
        List<Integer> values2 = new ArrayList<>();
        strings.stream().forEach((s) -> values2.add(processPart2Line(s)));
        System.out.println(values2.stream().reduce(0,Integer::sum));
    }

    private static int processPart1Line(String s) {
        String[] digits = s.replaceAll("[^\\d.]", "").split("");
        if (digits[0].equals(""))
        {
            return 0;
        }

        return Integer.parseInt(digits[0] + digits[digits.length-1]);
    }
    private static int processPart2Line(String s) {
        String[] digitsNums = {"one","two","three","four","five","six","seven","eight","nine"};
        char[] sCopy = s.toCharArray();
        String copier = new String();
        String actualValues = new String();
        for (int j = 0; j <sCopy.length ; j++) {
            copier = copier + sCopy[j];
            if(Character.isDigit(sCopy[j])){
                actualValues+=sCopy[j];
            }
            for (int i = 0; i < digitsNums.length; i++) {
                if(copier.contains(digitsNums[i])){
                    copier = copier.replaceAll(digitsNums[i], String.valueOf(i + 1));
                    copier+=digitsNums[i].toCharArray()[digitsNums[i].toCharArray().length-1];
                };
            }
        }

        String[] digits = copier.replaceAll("[^\\d.]", "").split("");
        return Integer.parseInt(digits[0] + digits[digits.length-1]);
    }
}
