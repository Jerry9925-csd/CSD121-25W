package ca.saultcollege.hello_word;

public class LengthConverter implements Converter {
    @Override
    public double convert(double value) {
        return value * 3.28084; // Meters to Feet
    }

    @Override
    public double reverseConvert(double value) {
        return value / 3.28084; // Feet to Meters
    }
}
