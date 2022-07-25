package solution_three;

import edu.duke.FileResource;

public class Part2 {

  public double cgRatio(String dna) {
    String dnaUpper = dna.toUpperCase();
    int countCG = 0;
    for (char letter : dnaUpper.toCharArray()) {
      if (letter == 'C' || letter == 'G') {
        countCG++;
      }
    }
    return (double) countCG / dna.length();
  }

  public int howMany(String stringa, String stringb) {
    int count = 0;
    int from = 0;
    int idx;
    int lengthA = stringa.length();
    int lengthB = stringb.length();
    while (from < lengthB && (idx = stringb.indexOf(stringa, from)) != -1) {
      count++;
      from = idx + lengthA;
    }
    return count;
  }

  public int countCTG(String dna) {
    return howMany("CTG", dna.toUpperCase());
  }

  public static void main(String[] args) {
    Part2 p2 = new Part2();

    // String dna = "ATGCCATAG";
    // System.out.println("dna = " + dna);
    // System.out.println("cgRatio = " + p2.cgRatio(dna));
    // System.out.println();

    // dna = "cccctgtttctgaaactgggg";
    // System.out.println("dna = " + dna);
    // System.out.println("countCTG = " + p2.countCTG(dna)); // 3

    FileResource fr = new FileResource("../data/dna/GRch38dnapart.fa");
    String dna = fr.asString();
    System.out.println("countCTG = " + p2.countCTG(dna));
  }

}
