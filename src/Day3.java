import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day3 {
    public static void main(String[] args) {

        List<String> strings = Utils.readAllLineesAOCFile(3);
        char[][] strings2dArray = Utils.convertStringListTo2dCharArray(strings);

        //p1
        List<Integer> values = processPart1(strings2dArray);
        System.out.println(values.stream().reduce(0,Integer::sum));

        //p2
        List<Integer> values2 = processPart2(strings2dArray);
        System.out.println(values2.stream().reduce(0,Integer::sum));

    }

    private static List<Integer> processPart1(char[][] array) {
        List<Integer> returnValues = new ArrayList<>();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                boolean alreadyAdded = false;
                StringBuilder digits = new StringBuilder();
                int lengthInteger = 0;
                int startX = j;
                while (j < array[i].length && Character.isDigit(array[i][j])) {
                    lengthInteger++;
                    digits.append(array[i][j]);
                    j++;
                }
                if (lengthInteger != 0) {
                    int number = Integer.parseInt(digits.toString());

                    //upper
                    int tempCordX = startX - 1;
                    int tempCordY = i - 1;
                    int howManyLeft = lengthInteger + 2;
                    while (tempCordY >= 0 && howManyLeft != 0) {
                        if (!alreadyAdded && tempCordX >= 0 && tempCordX < array[i].length && array[tempCordY][tempCordX] != '.' && !Character.isDigit(array[tempCordY][tempCordX])) {
                            alreadyAdded = true;
                            returnValues.add(number);
                            break;
                        }
                        tempCordX++;
                        howManyLeft--;
                    }
                    //lower
                    tempCordX = startX - 1;
                    tempCordY = i + 1;
                    howManyLeft = lengthInteger + 2;
                    while (tempCordY < array.length && howManyLeft != 0) {
                        if (!alreadyAdded && tempCordX >= 0 && tempCordX < array[i].length && array[tempCordY][tempCordX] != '.' && !Character.isDigit(array[tempCordY][tempCordX])) {
                            alreadyAdded = true;
                            returnValues.add(number);
                            break;
                        }
                        tempCordX++;
                        howManyLeft--;
                    }

                    //sides
                    if (!alreadyAdded && startX - 1 >= 0 && array[i][startX - 1] != '.' && !Character.isDigit(array[i][startX - 1])) {
                        alreadyAdded = true;
                        returnValues.add(number);
                    }
                    if (!alreadyAdded && startX + lengthInteger < array[i].length && array[i][startX + lengthInteger] != '.' && !Character.isDigit(array[i][startX + lengthInteger])) {
                        alreadyAdded = true;
                        returnValues.add(number);
                    }
                }
            }
        }
        return returnValues;
    }

    private static List<Integer> processPart2(char[][] array) {
        List<Integer> returnValues = new ArrayList<>();
        Map<Cords, Integer> valueMap = new HashMap<>();
        List<Cords> stars = new ArrayList<>();
        Map<Cords, List<Integer>> calculationMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                StringBuilder digits = new StringBuilder();
                int lengthInteger = 0;
                int startX = j;
                int startY = i;
                boolean added = false;
                if (array[i][j] == '*') {
                    stars.add(new Cords(j, i, 1));
                    added = true;
                }
                while (j < array[i].length && Character.isDigit(array[i][j])) {
                    lengthInteger++;
                    digits.append(array[i][j]);
                    j++;
                }
                if (j < array[i].length && array[i][j] == '*' && !added) {
                    stars.add(new Cords(j, i, 1));
                }
                if (lengthInteger != 0) {
                    int number = Integer.parseInt(digits.toString());
                    valueMap.put(new Cords(startX, startY, lengthInteger), number);
                }
            }
        }

        for (Cords value : valueMap.keySet()) {
            for (Cords star : stars) {
                if (isAdjacentLowerAndRight(value, star)) {
                    if (!calculationMap.containsKey(star)) {
                        List<Integer> list = new ArrayList<>();
                        list.add(valueMap.get(value));
                        calculationMap.put(star, list);
                    } else {
                        List<Integer> temp = calculationMap.get(star);
                        temp.add(valueMap.get(value));
                        calculationMap.put(star, temp);
                    }
                }
            }
        }
        for (Cords kmsThisCodeIsGarbage : calculationMap.keySet()) {
            List<Integer> intList = calculationMap.get(kmsThisCodeIsGarbage);
            if (intList.size() == 2) {
                returnValues.add(intList.get(0) * intList.get(1));
            }
        }
        return returnValues;
    }

    private static boolean isAdjacentLowerAndRight(Cords value, Cords star) {
        //right
        if (value.getX() == star.getX() + value.getLength() && value.getY() == star.getY()) {
            return true;
        }
        //left
        if (value.getX() == star.getX() - 1 && value.getY() == star.getY()) {
            return true;
        }
        //upper/lower
        if (value.getX() - 1 <= star.getX() && value.getX() + value.length >= star.getX() && value.getY() == star.getY() + 1) {
            return true;
        }
        return value.getX() - 1 <= star.getX() && value.getX() + value.length >= star.getX() && value.getY() == star.getY() - 1;
    }

    private static class Cords {
        int length;
        private final int x;
        private final int y;

        public Cords(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getLength() {
            return length;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cords cords = (Cords) o;
            return x == cords.x && y == cords.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
