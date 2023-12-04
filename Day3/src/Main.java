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
            //System.out.println("Output part 2: " + Part2(input));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int Part1(ArrayList<String> input) {
        boolean isAdjacent = false;
        int sumOfAll = 0;

        for (int i = 0; i < input.size(); i++) {
            String str = input.get(i).replaceAll("[^?0-9]+", " ");
            String[] numbers = (str.trim().split(" "));
            String inputTracker = input.get(i);

            for(String n : numbers) {
                System.out.println("----------------");
                int numberIndex = inputTracker.indexOf(n);
                ArrayList<Integer> positions = new ArrayList<>();


                if(numberIndex != -1) {
                    int numberLength = n.length();

                    for (int k = 0; k < numberLength ; k++) {
                        positions.add(numberIndex + k);
                        char[] temp = inputTracker.toCharArray();
                        temp[numberIndex + k] = ' ';
                        inputTracker = String.valueOf(temp);
                    }


                }

                String numberAdjacentMain = "";
                String numberAdjacentTop = "";
                String numberAdjacentBottom = "";


                if(positions.size() != 0) {
                    numberAdjacentMain += positions.get(0) > 0 ? String.valueOf(input.get(i).toCharArray()[positions.get(0) - 1]) : "";
                    numberAdjacentMain += n;
                    numberAdjacentMain += positions.get(positions.size() - 1) < input.get(i).length() - 1 ? input.get(i).toCharArray()[positions.get(positions.size() - 1) + 1] : "";

                    if(i > 0) {
                        numberAdjacentTop += positions.get(0) > 0 ? String.valueOf(input.get(i - 1).toCharArray()[positions.get(0) - 1]) : "";
                        for(int pos : positions) {
                            numberAdjacentTop += input.get(i - 1).toCharArray()[pos];
                        }
                        numberAdjacentTop += positions.get(positions.size() - 1) < input.get(i).length() - 1 ? input.get(i - 1).toCharArray()[positions.get(positions.size() - 1) + 1] : "";

                    }

                    if(i != input.size() - 1) {
                        numberAdjacentBottom += positions.get(0) > 0 ? String.valueOf(input.get(i + 1).toCharArray()[positions.get(0) - 1]) : "";
                        for(int pos : positions) {
                            numberAdjacentBottom += input.get(i + 1).toCharArray()[pos];
                        }
                        numberAdjacentBottom += positions.get(positions.size() - 1) < input.get(i).length() - 1 ? input.get(i + 1).toCharArray()[positions.get(positions.size() - 1) + 1] : "";

                    }
                }
                System.out.println(numberAdjacentTop);
                System.out.println(numberAdjacentMain);
                System.out.println(numberAdjacentBottom);



                String wholeBox = numberAdjacentTop + numberAdjacentMain + numberAdjacentBottom;

                for(char symbol : wholeBox.toCharArray()) {
                    if(symbol != '.' && !Character.isDigit(symbol)) {
                        isAdjacent = true;
                    }
                }

                if(isAdjacent && !n.equals("")) {
                    sumOfAll += Integer.parseInt(n);
                }

                isAdjacent = false;
            }
        }
        return sumOfAll;
    }
}