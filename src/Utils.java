import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<String> readAllLineesAOCFile(int day){
        List<String> allLines = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("inputs/day"+day+"input.txt"));

            return reader.lines().toList();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static char[][] convertStringListTo2dCharArray(List<String> strings) {
        char[][] stringArray = new char[strings.size()][];
        int i = 0;
        for (String s : strings) {
            stringArray[i] = s.toCharArray();
            i++;
        }
        return stringArray;
    }
}
