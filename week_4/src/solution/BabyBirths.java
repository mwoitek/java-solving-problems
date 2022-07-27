package solution;

import java.io.File;
import org.apache.commons.csv.CSVRecord;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;

public class BabyBirths {

  public void totalBirths(FileResource fr) {
    int numBirths;
    int numGirls = 0;
    int numBoys = 0;
    int numGirlNames = 0;
    int numBoyNames = 0;
    for (CSVRecord record : fr.getCSVParser(false)) {
      numBirths = Integer.parseInt(record.get(2));
      if (record.get(1).equals("F")) {
        numGirls += numBirths;
        numGirlNames++;
      } else {
        numBoys += numBirths;
        numBoyNames++;
      }
    }
    System.out.println("GIRLS");
    System.out.println("Number of births: " + numGirls);
    System.out.println("Number of names: " + numGirlNames);
    System.out.println("BOYS");
    System.out.println("Number of births: " + numBoys);
    System.out.println("Number of names: " + numBoyNames);
    System.out.println("TOTAL");
    System.out.println("Number of births: " + (numGirls + numBoys));
    System.out.println("Number of names: " + (numGirlNames + numBoyNames));
  }

  public void testTotalBirths() {
    FileResource fr = new FileResource("../data/baby_names/example-small.csv");
    totalBirths(fr);
  }

  public int getRank(int year, String name, String gender) {
    int rank = 1;
    boolean found = false;
    FileResource fr = new FileResource("../data/baby_names/yob" + year + ".csv");
    for (CSVRecord record : fr.getCSVParser(false)) {
      if (record.get(1).equals(gender)) {
        if (record.get(0).equals(name)) {
          found = true;
          break;
        }
        rank++;
      }
    }
    if (found) {
      return rank;
    }
    return -1;
  }

  public void testGetRank() {
    int year = 2012;
    String name = "Mason";
    String gender = "M";
    System.out.println("year = " + year + ", name = " + name + ", gender = " + gender);
    System.out.println("rank = " + getRank(year, name, gender));

    year = 1997;
    name = "Sarah";
    gender = "F";
    System.out.println("year = " + year + ", name = " + name + ", gender = " + gender);
    System.out.println("rank = " + getRank(year, name, gender));

    year = 2012;
    name = "xxxxx";
    gender = "M";
    System.out.println("year = " + year + ", name = " + name + ", gender = " + gender);
    System.out.println("rank = " + getRank(year, name, gender));
  }

  public String getName(int year, int rank, String gender) {
    String name = "";
    int currentRank = 1;
    FileResource fr = new FileResource("../data/baby_names/yob" + year + ".csv");
    for (CSVRecord record : fr.getCSVParser(false)) {
      if (record.get(1).equals(gender)) {
        if (currentRank == rank) {
          name = record.get(0);
          break;
        }
        currentRank++;
      }
    }
    if (currentRank == rank) {
      return name;
    }
    return "NO NAME";
  }

  public void testGetName() {
    int year = 2012;
    int rank = 2;
    String gender = "M";
    System.out.println("year = " + year + ", rank = " + rank + ", gender = " + gender);
    System.out.println("name = " + getName(year, rank, gender));

    year = 1997;
    rank = 4;
    gender = "F";
    System.out.println("year = " + year + ", rank = " + rank + ", gender = " + gender);
    System.out.println("name = " + getName(year, rank, gender));

    year = 1997;
    rank = Integer.MAX_VALUE;
    gender = "F";
    System.out.println("year = " + year + ", rank = " + rank + ", gender = " + gender);
    System.out.println("name = " + getName(year, rank, gender));
  }

  public void whatIsNameInYear(String name, int year, int newYear, String gender) {
    int rank = getRank(year, name, gender);
    String newName = getName(newYear, rank, gender);
    String pronoun = gender.equals("F") ? "she" : "he";
    System.out.println(name + " born in " + year + " would be " + newName + " if "
        + pronoun + " was born in " + newYear + ".");
  }

  public int getYearFromFileName(File f) {
    String fileName = f.getName();
    int idxBegin = fileName.indexOf("b") + 1;
    int idxEnd = fileName.indexOf(".");
    return Integer.parseInt(fileName.substring(idxBegin, idxEnd));
  }

  public int yearOfHighestRank(String name, String gender) {
    int currentYear;
    int year = -1;
    int rank;
    int maxRank = Integer.MAX_VALUE;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      currentYear = getYearFromFileName(f);
      rank = getRank(currentYear, name, gender);
      if (rank != -1 && rank < maxRank) {
        year = currentYear;
        maxRank = rank;
      }
    }
    return year;
  }

  public void testYearOfHighestRank() {
    String name = "Mason";
    String gender = "M";
    System.out.println("name = " + name + ", gender = " + gender);
    System.out.println("year = " + yearOfHighestRank(name, gender));

    name = "Emma";
    gender = "F";
    System.out.println("name = " + name + ", gender = " + gender);
    System.out.println("year = " + yearOfHighestRank(name, gender));

    name = "xxxx";
    gender = "F";
    System.out.println("name = " + name + ", gender = " + gender);
    System.out.println("year = " + yearOfHighestRank(name, gender));
  }

  public double getAverageRank(String name, String gender) {
    int sum = 0;
    int count = 0;
    int year;
    int rank;
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      year = getYearFromFileName(f);
      rank = getRank(year, name, gender);
      if (rank != -1) {
        sum += rank;
        count++;
      }
    }
    if (count > 0) {
      return (double) sum / count;
    }
    return -1.0;
  }

  public void testGetAverageRank() {
    String name = "Mason";
    String gender = "M";
    System.out.println("name = " + name + ", gender = " + gender);
    System.out.println("average rank = " + getAverageRank(name, gender));

    name = "Jacob";
    gender = "M";
    System.out.println("name = " + name + ", gender = " + gender);
    System.out.println("average rank = " + getAverageRank(name, gender));

    name = "xxxx";
    gender = "F";
    System.out.println("name = " + name + ", gender = " + gender);
    System.out.println("average rank = " + getAverageRank(name, gender));
  }

  public int getTotalBirthsRankedHigher(int year, String name, String gender) {
    int numBirths = 0;
    FileResource fr = new FileResource("../data/baby_names/yob" + year + ".csv");
    for (CSVRecord record : fr.getCSVParser(false)) {
      if (record.get(1).equals(gender)) {
        if (record.get(0).equals(name)) {
          break;
        }
        numBirths += Integer.parseInt(record.get(2));
      }
    }
    return numBirths;
  }

  public void testGetTotalBirthsRankedHigher() {
    int year = 2012;
    String name = "Ethan";
    String gender = "M";
    System.out.println("year = " + year + ", name = " + name + ", gender = " + gender);
    System.out.println(
        "total number of births = " + getTotalBirthsRankedHigher(year, name, gender));
  }

  public static void main(String[] args) {
    BabyBirths bb = new BabyBirths();
    // bb.testTotalBirths();
    // bb.testGetRank();
    // bb.testGetName();
    // bb.whatIsNameInYear("Isabella", 2012, 2014, "F");
    // bb.testYearOfHighestRank();
    // bb.testGetAverageRank();
    bb.testGetTotalBirthsRankedHigher();
  }

}
