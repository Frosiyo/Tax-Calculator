import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.*;

public class TaxCalculator {
    public static String value;

    public static final BigDecimal TAX_IVA = new BigDecimal("0.23");
    public static final BigDecimal TAX_IRC = new BigDecimal("0.21");

    public static BigDecimal valueBig;
    public static BigDecimal valueIVAbig;
    public static BigDecimal valueIRCbig;

    public static void calculate(){
        valueIVAbig = valueBig.multiply(TAX_IVA);
        valueIRCbig = valueBig.multiply(TAX_IRC);

        BigDecimal CalculateIVA = valueBig.multiply(TAX_IVA.add(new BigDecimal("1")));
        BigDecimal CalculateIRC = valueBig.multiply(TAX_IRC.add(new BigDecimal("1")));

        if (valueBig.doubleValue() != 0) {
            out.printf("\n\nValue: %.2f\u20ac\n" + "Value with IVA: %.2f\u20ac\n" + "Value with IRC: %.2f\u20ac\n\n\n",
                valueBig.setScale(2, RoundingMode.HALF_UP), CalculateIVA.setScale(2, RoundingMode.HALF_UP),
                CalculateIRC.setScale(2, RoundingMode.HALF_UP));
        }
        else {
            out.println("\n\nValue cannot be 0\n\n");
        }
    }
    public static void main (String[] args) {

        while (true) {
            try {
                Scanner input = new Scanner(in);
                out.println("Input a value:");
                value = input.nextLine();
                if (value.equalsIgnoreCase("exit")){
                    out.println("\n\nExiting...\n");
                    break;
                } else {
                    valueBig = new BigDecimal(value);
                    calculate();
                }
            } catch (InputMismatchException | NumberFormatException wrongType) {
                if (value.contains(",")) {
                    value = value.replace(",", ".");
                    try{
                        valueBig = new BigDecimal(value);
                        calculate();
                    }catch (NumberFormatException twoCommas){
                        out.println("\n\nERROR: Be careful not to put more than one comma\n\n");
                    }
                } else {
                    out.println("\n\nERROR: Only input numbers\n\n");
                }
            }
        }
    }
}