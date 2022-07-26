package solution;

import java.io.File;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class ParsingWeatherData {

  private static final double ABSOLUTE_ZERO = -459.67;

  public CSVRecord coldestHourInFile(CSVParser parser) {
    CSVRecord coldest = null;
    double temperature;
    double minTemperature = Double.MAX_VALUE;
    for (CSVRecord record : parser) {
      temperature = Double.parseDouble(record.get("TemperatureF"));
      if (temperature > ABSOLUTE_ZERO && temperature < minTemperature) {
        coldest = record;
        minTemperature = temperature;
      }
    }
    return coldest;
  }

  public void testColdestHourInFile() {
    FileResource fr = new FileResource("../data/nc_weather/2014/weather-2014-01-03.csv");
    CSVParser parser = fr.getCSVParser();
    CSVRecord coldest = coldestHourInFile(parser);
    System.out.println("Coldest temperature: " + coldest.get("TemperatureF"));
    System.out.println("Date and time: " + coldest.get("DateUTC"));
  }

  public String fileWithColdestTemperature() {
    String fileName = "";
    double temperature;
    double minTemperature = Double.MAX_VALUE;
    FileResource fr;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      fr = new FileResource(f);
      temperature =
          Double.parseDouble(coldestHourInFile(fr.getCSVParser()).get("TemperatureF"));
      if (temperature < minTemperature) {
        fileName = f.getName();
        minTemperature = temperature;
      }
    }
    return fileName;
  }

  public void testFileWithColdestTemperature() {
    String fileName = fileWithColdestTemperature();
    System.out.println("Coldest day was in file " + fileName);

    int idxDash = fileName.indexOf("-");
    String year = fileName.substring(idxDash + 1, idxDash + 5);
    FileResource fr = new FileResource("../data/nc_weather/" + year + "/" + fileName);

    CSVParser parser = fr.getCSVParser();
    System.out.println("Coldest temperature on that day was "
        + coldestHourInFile(parser).get("TemperatureF"));

    parser = fr.getCSVParser();
    System.out.println("All the temperatures on the coldest day were:");
    for (CSVRecord record : parser) {
      System.out.println(record.get("DateUTC") + ": " + record.get("TemperatureF"));
    }
  }

  public CSVRecord lowestHumidityInFile(CSVParser parser) {
    CSVRecord lowestHumidity = null;
    String humidityStr;
    double humidityDbl;
    double minHumidity = Double.MAX_VALUE;
    for (CSVRecord record : parser) {
      humidityStr = record.get("Humidity");
      if (humidityStr.equals("N/A")) {
        continue;
      }
      if ((humidityDbl = Double.parseDouble(humidityStr)) < minHumidity) {
        lowestHumidity = record;
        minHumidity = humidityDbl;
      }
    }
    return lowestHumidity;
  }

  public void testLowestHumidityInFile() {
    FileResource fr = new FileResource("../data/nc_weather/2014/weather-2014-01-20.csv");
    CSVRecord lowestHumidity = lowestHumidityInFile(fr.getCSVParser());
    System.out.println("Lowest humidity was " + lowestHumidity.get("Humidity") + " at "
        + lowestHumidity.get("DateUTC"));
  }

  public CSVRecord lowestHumidityInManyFiles() {
    CSVRecord lowestHumidity = null;
    CSVRecord lowestHumidityFile;
    double humidity;
    double minHumidity = Double.MAX_VALUE;
    FileResource fr;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      fr = new FileResource(f);
      lowestHumidityFile = lowestHumidityInFile(fr.getCSVParser());
      humidity = Double.parseDouble(lowestHumidityFile.get("Humidity"));
      if (humidity < minHumidity) {
        lowestHumidity = lowestHumidityFile;
        minHumidity = humidity;
      }
    }
    return lowestHumidity;
  }

  public void testLowestHumidityInManyFiles() {
    CSVRecord lowestHumidity = lowestHumidityInManyFiles();
    System.out.println("Lowest humidity was " + lowestHumidity.get("Humidity") + " at "
        + lowestHumidity.get("DateUTC"));
  }

  public double averageTemperatureInFile(CSVParser parser) {
    double sum = 0.0;
    int count = 0;
    double temperature;
    for (CSVRecord record : parser) {
      temperature = Double.parseDouble(record.get("TemperatureF"));
      if (temperature > ABSOLUTE_ZERO) {
        sum += temperature;
        count++;
      }
    }
    return sum / count;
  }

  public void testAverageTemperatureInFile() {
    FileResource fr = new FileResource("../data/nc_weather/2014/weather-2014-01-20.csv");
    System.out.println(
        "Average temperature in file is " + averageTemperatureInFile(fr.getCSVParser()));
  }

  public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
    double sum = 0.0;
    int count = 0;
    String humidityStr;
    double humidityDbl;
    double temperature;
    for (CSVRecord record : parser) {
      humidityStr = record.get("Humidity");
      if (humidityStr.equals("N/A")) {
        continue;
      }
      humidityDbl = Double.parseDouble(humidityStr);
      temperature = Double.parseDouble(record.get("TemperatureF"));
      if (humidityDbl >= value && temperature > ABSOLUTE_ZERO) {
        sum += temperature;
        count++;
      }
    }
    if (count > 0) {
      return sum / count;
    }
    return -Double.MAX_VALUE;
  }

  public void testAverageTemperatureWithHighHumidityInFile() {
    FileResource fr = new FileResource("../data/nc_weather/2014/weather-2014-03-20.csv");
    double averageTemperature =
        averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
    if (averageTemperature > ABSOLUTE_ZERO) {
      System.out
          .println("Average temperature when high humidity is " + averageTemperature);
    } else {
      System.out.println("No temperatures with that humidity");
    }
  }

  public static void main(String[] args) {
    ParsingWeatherData pwd = new ParsingWeatherData();
    // pwd.testColdestHourInFile();
    // pwd.testFileWithColdestTemperature();
    // pwd.testLowestHumidityInFile();
    // pwd.testLowestHumidityInManyFiles();
    // pwd.testAverageTemperatureInFile();
    pwd.testAverageTemperatureWithHighHumidityInFile();
  }

}
