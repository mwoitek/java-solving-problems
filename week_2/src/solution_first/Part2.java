package solution_first;

public class Part2 {

  public String findSimpleGene(String dna, String startCodon, String stopCodon) {
    if (Character.isUpperCase(dna.charAt(0))) {
      startCodon = startCodon.toUpperCase();
      stopCodon = stopCodon.toUpperCase();
    } else {
      startCodon = startCodon.toLowerCase();
      stopCodon = stopCodon.toLowerCase();
    }
    int idxStart = dna.indexOf(startCodon);
    if (idxStart == -1) {
      return "";
    }
    int idxStop = dna.indexOf(stopCodon, idxStart + 3);
    if (idxStop == -1) {
      return "";
    }
    String gene = dna.substring(idxStart, idxStop + 3);
    if (gene.length() % 3 == 0) {
      return gene;
    }
    return "";
  }

  public void testSimpleGene() {
    String dna = "TTGGGTTAAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna, "ATG", "TAA"));
    System.out.println();

    dna = "ATGGGTTGAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna, "ATG", "TAA"));
    System.out.println();

    dna = "TTGGGTTGAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna, "ATG", "TAA"));
    System.out.println();

    dna = "ATGGGTTAAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna, "ATG", "TAA"));
    System.out.println();

    dna = "GATGGTTAAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna, "ATG", "TAA"));
    System.out.println();

    dna = "atgggttaagtc";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna, "ATG", "TAA"));
    System.out.println();

    dna = "ATGGGTTAAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna, "atg", "taa"));
  }

  public static void main(String[] args) {
    Part2 p2 = new Part2();
    p2.testSimpleGene();
  }

}
