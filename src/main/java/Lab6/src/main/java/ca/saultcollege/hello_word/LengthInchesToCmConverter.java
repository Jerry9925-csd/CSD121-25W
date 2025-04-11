package ca.saultcollege.hello_word;

public class LengthInchesToCmConverter implements Converter {
    @Override
    public double convert(double value) {
        return value * 2.54; // Inches to Centimeters
    }

    @Override
    public double reverseConvert(double value) {
        return value / 2.54; // Centimeters to Inches
    }
}
