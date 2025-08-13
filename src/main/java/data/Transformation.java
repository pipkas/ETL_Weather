package data;

//enum which defines the type of data conversion to the desired output format
public enum Transformation {
    WINDSPEED,       // Convert from knots to m/s
    DEGREES,         // Convert from Fahrenheit to Celsius
    FT_TO_M,         // Convert from feet to meters
    INCH_TO_MM,      // Convert from inches to millimeters
    UNIX_TO_ISO8601, // Convert from Unix time (seconds) to ISO 8601 format
    UNIX_TO_LOCAL_DATE, // Convert from Unix time (seconds) to dd.MM.yyyy format
    UNIX_TO_LOCAL_TIME  // Convert from Unix time (seconds) to HH:mm:ss format
}
