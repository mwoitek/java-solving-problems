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

  // HERE

  public static void main(String[] args) {
    ParsingWeatherData pwd = new ParsingWeatherData();

    pwd.testColdestHourInFile();
    System.out.println();

    pwd.testFileWithColdestTemperature();
    // System.out.println();

    //
  }

}
