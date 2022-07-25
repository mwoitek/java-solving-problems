package solution_three;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part3 {

  public void processGenes(StorageResource sr) {
    int refLength = 60;
    double refRatio = 0.35;

    int length;
    int maxLength = -1;

    StorageResource srLength = new StorageResource();
    StorageResource srRatio = new StorageResource();

    Part2 p2 = new Part2();

    for (String gene : sr.data()) {
      length = gene.length();
      if (length > maxLength) {
        maxLength = length;
      }
      if (length > refLength) {
        srLength.add(gene);
      }
      if (p2.cgRatio(gene) > refRatio) {
        srRatio.add(gene);
      }
    }

    System.out.println("Genes that are longer than " + refLength + " characters:");
    for (String gene : srLength.data()) {
      System.out.println(gene);
    }
    System.out.println("Number of genes: " + srLength.size());
    System.out.println();

    System.out.println("Genes whose CG-ratio is higher than " + refRatio);
    for (String gene : srRatio.data()) {
      System.out.println(gene);
    }
    System.out.println("Number of genes: " + srRatio.size());
    System.out.println();

    System.out.println("Length of the longest gene: " + maxLength);
  }

  public void testProcessGenes() {
    // StorageResource genes = new StorageResource();
    // genes.add("ATGAGTAAGTAG");
    // genes.add("ATGTAA");
    // genes.add("ATGCCATAG");
    // genes.add("ATGAAATAG");
    // genes.add("ATGTGA");
    // processGenes(genes);

    FileResource fr = new FileResource("../data/dna/GRch38dnapart.fa");
    String dna = fr.asString();
    Part1 p1 = new Part1();
    StorageResource genes = p1.getAllGenes(dna);
    processGenes(genes);
  }

  public static void main(String[] args) {
    Part3 p3 = new Part3();
    p3.testProcessGenes();
  }

}
