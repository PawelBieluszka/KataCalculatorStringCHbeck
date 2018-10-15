package kata.calculator.string;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*author: Paweł Biełuszka*/

public class CalculatorString {

    public int add(String strInput) throws Exception {

        if(strInput.endsWith("\n")) throw new Exception(" Tag of the new line is not allowed at the end of the string ");

        String negatives = new String("");
        String [] strInputStream;

        if(strInput.startsWith("//")){
            strInputStream = strInput.split("[^-0-9,]");
        } else strInputStream = strInput.split("[\n,]");

        Supplier<Stream<Integer>> mapStringToInt = () -> Arrays.stream(strInputStream)
                .filter(i -> !i.isEmpty())
                .map(Integer::valueOf);

        negatives = mapStringToInt.get().filter(i -> i < 0)
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        if(!negatives.isEmpty()) throw new Exception(" Negatives not allowed: " + negatives);

        int output = mapStringToInt.get().filter(i -> i < 1001)
                .reduce(0, Integer::sum);

        return output;
    }

}
