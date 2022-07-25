package solution_second;

public class Part2 {

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

  public void testHowMany() {
    String stringa = "GAA";
    String stringb = "ATGAACGAATTGAATC";
    System.out.println("stringa = " + stringa + ", stringb = " + stringb);
    System.out.println("output = " + howMany(stringa, stringb));
    System.out.println();

    stringa = "AA";
    stringb = "ATAAAA";
    System.out.println("stringa = " + stringa + ", stringb = " + stringb);
    System.out.println("output = " + howMany(stringa, stringb));
  }

  public static void main(String[] args) {
    Part2 p2 = new Part2();
    p2.testHowMany();
  }

}
