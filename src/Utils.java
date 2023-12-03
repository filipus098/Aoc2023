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
}
