import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

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
        final int MAX_REDS = 12;
        final int MAX_GREENS = 13;
        final int MAX_BLUES = 14;
        int sumOfIDs = 0;

        // Loop through games
        for(String currentInput : input) {
            int gameId = Integer.parseInt(currentInput.substring(5, 8).replaceAll("[: ]", ""));

            currentInput = currentInput.replaceAll("\\bGame\\s([1-9]|[1-9][0-9]|100): ", "");

            String[] sets = currentInput.split("; ");

            boolean isPossible = true;

            // Loop through sets
            for(String currentSet : sets) {
                String[] pulls = currentSet.split(", ");

                for(String pull : pulls) {
                    String[] splitPull = pull.split(" ");

                    int amount = Integer.parseInt(splitPull[0]);
                    String color = splitPull[1];

                    if(Objects.equals(color, "red") && amount > MAX_REDS) {
                        isPossible = false;
                    }
                    if(Objects.equals(color, "green") && amount > MAX_GREENS) {
                        isPossible = false;
                    }
                    if(Objects.equals(color, "blue") && amount > MAX_BLUES) {
                        isPossible = false;
                    }
                }
            }

            if(isPossible) {
                sumOfIDs += gameId;
            }
        }

        return sumOfIDs;
    }

    private static int Part2(ArrayList<String> input) {
        int sumOfPower = 0;

        // Loop through games
        for(String currentInput : input) {
            currentInput = currentInput.replaceAll("\\bGame\\s([1-9]|[1-9][0-9]|100): ", "");

            String[] sets = currentInput.split("; ");

            int highestRed = 0;
            int highestGreen = 0;
            int highestBlue = 0;

            // Loop through sets
            for(String currentSet : sets) {
                String[] pulls = currentSet.split(", ");

                // Loop through individual pulls
                for(String pull : pulls) {
                    String[] splitPull = pull.split(" ");

                    int amount = Integer.parseInt(splitPull[0]);
                    String color = splitPull[1];

                    if(Objects.equals(color, "red") && amount > highestRed) {
                        highestRed = amount;
                    }
                    if(Objects.equals(color, "green") && amount > highestGreen) {
                        highestGreen = amount;
                    }
                    if(Objects.equals(color, "blue") && amount > highestBlue) {
                        highestBlue = amount;
                    }

                }
            }

            sumOfPower += (highestRed * highestGreen * highestBlue);
        }

        return sumOfPower;
    }
}