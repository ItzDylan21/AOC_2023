import java.io.File;
import java.io.FileNotFoundException;
import java.lang.invoke.CallSite;
import java.util.*;
import java.util.concurrent.Callable;

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

            System.gc();
            long startTime = System.currentTimeMillis();
            System.out.print("Output part 1: " + Part1(input));
            long endTime = System.currentTimeMillis();
            System.out.println(" (" + (endTime - startTime) + "ms)");

            System.gc();
            startTime = System.currentTimeMillis();
            // It's really slow
            System.out.print("Output part 2: " + Part2(input));
            endTime = System.currentTimeMillis();
            System.out.println(" (" + (endTime - startTime) + "ms)");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static int Part1(ArrayList<String> input) {
        int sumOfCards = 0;

        for(String currentInput : input) {
            int amountOfMatches = 0;
            currentInput = currentInput.replaceAll("\\bCard\\s+\\d+([1-9]|[1-9]|[0-9]): |\\bCard\\s+[1-9]\\d*: ", "");
            currentInput = currentInput.replaceAll("  ", " ");
            currentInput = currentInput.replaceAll("^\\s", "");

            String[] seperatedString = currentInput.split("[|]");

            String[] pulls = seperatedString[0].split(" ");
            String[] cardNumbers = seperatedString[1].split(" ");

            for(String p : pulls) {
                for(String n : cardNumbers) {
                    if(!Objects.equals(n, "") && !Objects.equals(p, "")) {
                        if (Integer.parseInt(p) == Integer.parseInt(n)) {
                            amountOfMatches++;
                        }
                    }
                }
            }

            if(amountOfMatches != 0) {
                sumOfCards += (int) Math.pow(2, amountOfMatches - 1);
            }
        }
        return sumOfCards;
    }

    private static int Part2(ArrayList<String> input) {
        int sumOfCards = 0;
        ArrayList<Integer> instancesOfCards = new ArrayList<>();

        for (int i = 0; i < input.size(); i++) {
            instancesOfCards.add(1);
        }

            for (int i = 0; i < input.size(); i++) {
                for (int j = 0; j < instancesOfCards.get(i); j++) {
                    int amountOfMatches = 0;

                    String currentInput = input.get(i).replaceAll("\\bCard\\s+\\d+([1-9]|[1-9]|[0-9]): |\\bCard\\s+[1-9]\\d*: ", "");

                    currentInput = currentInput.replaceAll("  ", " ");
                    currentInput = currentInput.replaceAll("^\\s", "");

                    String[] seperatedString = currentInput.split("[|]");

                    String[] pulls = seperatedString[0].split(" ");
                    String[] cardNumbers = seperatedString[1].split(" ");

                    Set<String> pullsSet = new HashSet<>(Arrays.asList(pulls));
                    Set<String> cardNumbersSet = new HashSet<>(Arrays.asList(cardNumbers));

                    for (String p : pullsSet) {
                        if (cardNumbersSet.contains(p)) {
                            amountOfMatches++;
                        }
                    }

                    for (int k = 0; k < amountOfMatches; k++) {
                        instancesOfCards.set(i + 1 + k, (instancesOfCards.get(i + 1 + k) + 1));
                    }
                }
            }

        for (Integer instancesOfCard : instancesOfCards) {
            sumOfCards += instancesOfCard;
        }
        return sumOfCards;
    }
}