import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Text file reader
            File myObj = new File("src/input.txt");
            Scanner myReader = new Scanner(myObj);
            ArrayList<String> input = new ArrayList<>();

            // Put all lines into an ArrayList
            while (myReader.hasNextLine()) {
                input.add(myReader.nextLine());
            }

            System.out.println("Output part 1: " + Part1(input));
            System.out.println("Output part 2: " + Part2(input));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static int Part1(ArrayList<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {
            sum += GetCurrentSum(GetDigits(input.get(i).toCharArray()));
        }
        return sum;
    }

    private static int Part2(ArrayList<String> input) {
        int sum = 0;

        // Not proud of this one, but couldn't figure out how to do this with just 1 to 9...
        Map<String, String> numMap = new HashMap<>();
        numMap.put("oneight", "18");
        numMap.put("twone", "21");
        numMap.put("fiveight", "15");
        numMap.put("threeight", "38");
        numMap.put("sevenine", "79");
        numMap.put("eightwo", "82");
        numMap.put("eighthree", "83");
        numMap.put("nineight", "98");
        numMap.put("one", "1");
        numMap.put("two", "2");
        numMap.put("three", "3");
        numMap.put("four", "4");
        numMap.put("five", "5");
        numMap.put("six", "6");
        numMap.put("seven", "7");
        numMap.put("eight", "8");
        numMap.put("nine", "9");

        for (int i = 0; i < input.size(); i++) {
            final int index = i;
            // Keys are sorted in descending order, so blended words are replaced first.
            numMap.keySet().stream()
                    .sorted((s1, s2) -> Integer.compare(s2.length(), s1.length()))
                    .forEach(key -> input.set(index, input.get(index).replace(key, numMap.get(key))));

            sum += GetCurrentSum(GetDigits(input.get(i).toCharArray()));
        }
        return sum;
    }

    private static String GetDigits(char[] chars) {
        String numbers = "";
        for (char c : chars) {
            if(Character.isDigit(c)) {
                numbers += c;
            }
        }
        return numbers;
    }

    private static int GetCurrentSum(String digits) {
        String firstAndLast = String.valueOf(digits.charAt(0)) + digits.charAt(digits.length() - 1);

        return Integer.parseInt(firstAndLast);
    }
}