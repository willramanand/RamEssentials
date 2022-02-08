package com.gmail.willramanand.RamEssentials.utils;

import java.text.NumberFormat;

public class Formatter {

    public static String formatMoney(double value) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String formatted = formatter.format(value);
        return formatted;
    }
}