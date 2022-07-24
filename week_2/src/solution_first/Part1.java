package solution_first;

public class Part1 {

  public String findSimpleGene(String dna) {
    int idxStart = dna.indexOf("ATG");
    if (idxStart == -1) {
      return "";
    }
    int idxStop = dna.indexOf("TAA", idxStart + 3);
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
    System.out.println("Gene: " + findSimpleGene(dna));
    System.out.println();

    dna = "ATGGGTTGAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna));
    System.out.println();

    dna = "TTGGGTTGAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna));
    System.out.println();

    dna = "ATGGGTTAAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna));
    System.out.println();

    dna = "GATGGTTAAGTC";
    System.out.println("DNA: " + dna);
    System.out.println("Gene: " + findSimpleGene(dna));
  }

  public static void main(String[] args) {
    Part1 p1 = new Part1();
    p1.testSimpleGene();
  }

}
