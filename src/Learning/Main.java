package Learning;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(1, 1);
        System.out.println("Calculator left value: " + calculator.LeftNumber);
        System.out.println(calculator.CalculateSum());

        // Erstellung eines Arrays
        String[] words = new String[]{"hallo", "welt"};
        // Erstellung einer Liste
        List<String> wordsList = new ArrayList();

        // Ändern eines Items in einem Array
        words[0] = "nicht hallo";

        // hinzufügen von Items in die Liste
        wordsList.add("eofmsdof");
        wordsList.add("dfg");
        wordsList.add(1, "dffbg");

        // Ändern/setzen von items in der Liste
        wordsList.set(0, "Hallo");

        for (String word : wordsList) {
            System.out.println(word);
        }
    }
}
