package solution;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import edu.duke.FileResource;

public class ParsingExportData {

  public String countryInfo(CSVParser parser, String country) {
    String currentCountry;
    for (CSVRecord record : parser) {
      currentCountry = record.get("Country");
      if (currentCountry.equals(country)) {
        String info = currentCountry + ": ";
        info += record.get("Exports") + ": ";
        info += record.get("Value (dollars)");
        return info;
      }
    }
    return "NOT FOUND";
  }

  public void listExportersTwoProducts(CSVParser parser, String exportItem1,
      String exportItem2) {
    String exportsLower;
    exportItem1 = exportItem1.toLowerCase();
    exportItem2 = exportItem2.toLowerCase();
    for (CSVRecord record : parser) {
      exportsLower = record.get("Exports").toLowerCase();
      if (exportsLower.contains(exportItem1) && exportsLower.contains(exportItem2)) {
        System.out.println(record.get("Country"));
      }
    }
  }

  public int numberOfExporters(CSVParser parser, String exportItem) {
    int count = 0;
    String exportsLower;
    exportItem = exportItem.toLowerCase();
    for (CSVRecord record : parser) {
      exportsLower = record.get("Exports").toLowerCase();
      if (exportsLower.contains(exportItem)) {
        count++;
      }
    }
    return count;
  }

  public void bigExporters(CSVParser parser, String amount) {
    int amountLength = amount.length();
    String currentAmount;
    for (CSVRecord record : parser) {
      currentAmount = record.get("Value (dollars)");
      if (currentAmount.length() > amountLength) {
        System.out.println(record.get("Country") + " " + currentAmount);
      }
    }
  }

  public void tester() {
    FileResource fr = new FileResource("../data/exports/exportdata.csv");

    CSVParser parser = fr.getCSVParser();
    String country = "Germany";
    System.out.println("country = " + country);
    System.out.println(countryInfo(parser, country));
    System.out.println();

    parser = fr.getCSVParser();
    String exportItem1 = "cotton";
    String exportItem2 = "flowers";
    System.out.println("exportItem1 = " + exportItem1);
    System.out.println("exportItem2 = " + exportItem2);
    listExportersTwoProducts(parser, exportItem1, exportItem2);
    System.out.println();

    parser = fr.getCSVParser();
    String exportItem = "cocoa";
    System.out.println("exportItem = " + exportItem);
    System.out.println("number of exporters = " + numberOfExporters(parser, exportItem));
    System.out.println();

    parser = fr.getCSVParser();
    String amount = "$999,999,999,999";
    System.out.println("amount = " + amount);
    bigExporters(parser, amount);
  }

  public static void main(String[] args) {
    ParsingExportData ped = new ParsingExportData();
    ped.tester();
  }

}
