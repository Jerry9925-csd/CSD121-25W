package ca.saultcollege.hello_word;

public class DistanceConverter implements Converter {
    @Override
    public double convert(double value) {
        return value * 1.60934; // Miles to Kilometers
    }

    @Override
    public double reverseConvert(double value) {
        return value / 1.60934; // Kilometers to Miles
    }
}
