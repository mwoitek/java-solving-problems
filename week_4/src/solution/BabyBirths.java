package solution;

import org.apache.commons.csv.CSVRecord;
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

  public static void main(String[] args) {
    BabyBirths bb = new BabyBirths();
    // bb.testTotalBirths();
    bb.testGetRank();
  }

}
