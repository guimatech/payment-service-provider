package io.github.guimatech.paymentserviceprovider.util;

public class MathUtil {

    private MathUtil(){}

    public static double getValueDiscount(double value, double percentage) {
        return value * (percentage / 100);
    }
}
