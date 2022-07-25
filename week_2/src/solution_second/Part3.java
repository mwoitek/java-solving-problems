package solution_second;

import edu.duke.FileResource;

public class Part3 {

  public int findStopCodon(String dna, int startIndex, String stopCodon) {
    if (Character.isUpperCase(dna.charAt(0))) {
      stopCodon = stopCodon.toUpperCase();
    } else {
      stopCodon = stopCodon.toLowerCase();
    }
    int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
    while (stopIndex != -1) {
      if ((stopIndex - startIndex) % 3 == 0) {
        return stopIndex;
      }
      stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
    }
    return dna.length();
  }

  public int countGenes(String dna) {
    int count = 0;

    String startCodon = "atg";
    if (Character.isUpperCase(dna.charAt(0))) {
      startCodon = startCodon.toUpperCase();
    }

    int startFrom = 0;
    int startIndex;
    int taaIndex;
    int tagIndex;
    int tgaIndex;
    int stopIndex;

    while (true) {
      startIndex = dna.indexOf(startCodon, startFrom);
      if (startIndex == -1) {
        break;
      }

      taaIndex = findStopCodon(dna, startIndex, "TAA");
      tagIndex = findStopCodon(dna, startIndex, "TAG");
      tgaIndex = findStopCodon(dna, startIndex, "TGA");

      stopIndex = Math.min(taaIndex, tagIndex);
      stopIndex = Math.min(stopIndex, tgaIndex);
      if (stopIndex == dna.length()) {
        break;
      }

      count++;
      startFrom = stopIndex + 3;
    }

    return count;
  }

  public void testCountGenes() {
    String dna = "TTGGGTTAAGTC";
    dna += "ATGGGTTGAGTC";
    dna += "GGGGGGGGATGC";
    dna += "GGATGTTAAATTGA";
    dna += "GATGGTAATAGC";
    System.out.println("dna = " + dna);
    System.out.println("count = " + countGenes(dna));
    System.out.println();

    dna = "ATGTAAGATGCCCTAGT";
    System.out.println("dna = " + dna);
    System.out.println("count = " + countGenes(dna));
  }

  public static void main(String[] args) {
    Part3 p3 = new Part3();
    // p3.testCountGenes();
    FileResource fr = new FileResource("../data/dna/GRch38dnapart.fa");
    String dna = fr.asString();
    System.out.println("count = " + p3.countGenes(dna));
  }

}
