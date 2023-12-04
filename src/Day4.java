import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day4 {
    public static void main(String[] args) {
        List<String> strings = Utils.readAllLineesAOCFile(4);
        List<Integer> values = new ArrayList<>();
        List<Integer> values2 = new ArrayList<>();

        //p1
        strings.forEach((s) -> values.add(processPart1Line(s)));
        System.out.println(values.stream().reduce(0, Integer::sum));

        //p2
        Map<Integer, Integer> gameCountMap = new HashMap<>();
        strings.forEach((s) -> values2.add(processPart2Line(s)));

        for (int i = 0; i < values2.size(); i++) {
            gameCountMap.put(i, 1);
        }
        for (int i = 0; i < values2.size(); i++) {
            int won = values2.get(i);
            int currentWinnerCount = gameCountMap.get(i);
            for (int j = i + 1; j < i + 1 + won && j < values2.size(); j++) {
                int currentGames = gameCountMap.get(j);
                gameCountMap.put(j, currentGames + currentWinnerCount);
            }
        }
        int count = 0;
        for (int entry : gameCountMap.values()) {
            count += entry;
        }
        System.out.println(count);

    }

    private static int processPart1Line(String s) {
        String[] winners = s.split("\\|")[0].split(":")[1].split(" ");
        String[] mine = s.split("\\|")[1].split(" ");

        int counter = 0;
        for (String m : mine) {
            if (m.equals(""))
                continue;
            for (String winner : winners) {
                if (winner.equals(""))
                    continue;
                if (winner.equals(m)) {
                    counter++;
                    break;
                }
            }
        }

        if (counter == 0)
            return 0;

        return (int) Math.pow(2, counter - 1);
    }

    private static int processPart2Line(String s) {
        String[] winners = s.split("\\|")[0].split(":")[1].split(" ");
        String[] mine = s.split("\\|")[1].split(" ");

        int counter = 0;
        for (String m : mine) {
            if (m.equals(""))
                continue;
            for (String winner : winners) {
                if (winner.equals(""))
                    continue;
                if (winner.equals(m)) {
                    counter++;
                    break;
                }
            }
        }

        return counter;
    }
}
