package lab6;

public class WeightConverter implements Converter {
    @Override
    public double convert(double value) {
        return value * 2.20462; // Kg to Lbs
    }

    @Override
    public double reverseConvert(double value) {
        return value / 2.20462; // Lbs to Kg
    }
}
