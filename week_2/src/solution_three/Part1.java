package solution_three;

import edu.duke.StorageResource;

public class Part1 {

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

  public StorageResource getAllGenes(String dna) {
    StorageResource genes = new StorageResource();

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

      startFrom = stopIndex + 3;
      genes.add(dna.substring(startIndex, startFrom));
    }

    return genes;
  }

  public static void main(String[] args) {
    Part1 p1 = new Part1();
    String dna = "TTGGGTTAAGTC";
    dna += "ATGGGTTGAGTC";
    dna += "GGGGGGGGATGC";
    dna += "GGATGTTAAATTGA";
    dna += "GATGGTAATAGC";
    for (String gene : p1.getAllGenes(dna).data()) {
      System.out.println(gene);
    }
  }

}
