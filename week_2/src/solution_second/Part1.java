package solution_second;

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

  public void testFindStopCodon() {
    String dna = "TTTATGGGGGGGGGG";
    int startIndex = 3;
    String stopCodon = "tag";
    System.out.println(
        "dna = " + dna + ", startIndex = " + startIndex + ", stopCodon = " + stopCodon);
    System.out.println("output = " + findStopCodon(dna, startIndex, stopCodon)); // 15
    System.out.println();

    dna = "ATGTTTTAGTAGATG";
    startIndex = 0;
    stopCodon = "TAG";
    System.out.println(
        "dna = " + dna + ", startIndex = " + startIndex + ", stopCodon = " + stopCodon);
    System.out.println("output = " + findStopCodon(dna, startIndex, stopCodon)); // 6
    System.out.println();

    dna = "atgtttgattgaatg";
    startIndex = 0;
    stopCodon = "tga";
    System.out.println(
        "dna = " + dna + ", startIndex = " + startIndex + ", stopCodon = " + stopCodon);
    System.out.println("output = " + findStopCodon(dna, startIndex, stopCodon)); // 9
    System.out.println();

    dna = "ggatggtaaggtaatgg";
    startIndex = 2;
    stopCodon = "TAA";
    System.out.println(
        "dna = " + dna + ", startIndex = " + startIndex + ", stopCodon = " + stopCodon);
    System.out.println("output = " + findStopCodon(dna, startIndex, stopCodon)); // 11
    System.out.println();

    dna = "GGATGGTAAGTGTAAGG";
    startIndex = 2;
    stopCodon = "taa";
    System.out.println(
        "dna = " + dna + ", startIndex = " + startIndex + ", stopCodon = " + stopCodon);
    System.out.println("output = " + findStopCodon(dna, startIndex, stopCodon)); // 17
  }

  public String findGene(String dna) {
    String startCodon = "atg";
    if (Character.isUpperCase(dna.charAt(0))) {
      startCodon = startCodon.toUpperCase();
    }
    int startIndex = dna.indexOf(startCodon);
    if (startIndex == -1) {
      return "";
    }
    int taaIndex = findStopCodon(dna, startIndex, "TAA");
    int tagIndex = findStopCodon(dna, startIndex, "TAG");
    int tgaIndex = findStopCodon(dna, startIndex, "TGA");
    int stopIndex = Math.min(taaIndex, tagIndex);
    stopIndex = Math.min(stopIndex, tgaIndex);
    if (stopIndex == dna.length()) {
      return "";
    }
    return dna.substring(startIndex, stopIndex + 3);
  }

  public void testFindGene() {
    String dna = "TTGGGTTAAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findGene(dna)); // empty string
    System.out.println();

    dna = "atgggttgagtc";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findGene(dna)); // atgggttga
    System.out.println();

    dna = "GGGGGGGGATGC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findGene(dna)); // empty string
    System.out.println();

    dna = "ggatgttaaattga";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findGene(dna)); // atgttaaattga
    System.out.println();

    dna = "GATGGTAATAGC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findGene(dna)); // empty string
  }

  public void printAllGenes(String dna) {
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
      System.out.println(dna.substring(startIndex, startFrom));
    }
  }

  public static void main(String[] args) {
    Part1 p1 = new Part1();

    // p1.testFindStopCodon();
    // p1.testFindGene();

    String dna = "TTGGGTTAAGTC";
    dna += "ATGGGTTGAGTC";
    dna += "GGGGGGGGATGC";
    dna += "GGATGTTAAATTGA";
    dna += "GATGGTAATAGC";
    p1.printAllGenes(dna);
  }

}
