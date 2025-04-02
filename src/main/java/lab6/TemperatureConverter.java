package lab6;

public class TemperatureConverter implements Converter {
    @Override
    public double convert(double value) {
        return (value * 9 / 5) + 32; // Celsius to Fahrenheit
    }

    @Override
    public double reverseConvert(double value) {
        return (value - 32) * 5 / 9; // Fahrenheit to Celsius
    }
}
