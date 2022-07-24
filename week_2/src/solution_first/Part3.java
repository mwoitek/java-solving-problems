package solution_first;

public class Part3 {

  public boolean twoOccurrences(String stringa, String stringb) {
    int idx = stringb.indexOf(stringa);
    if (idx == -1) {
      return false;
    }
    idx = stringb.indexOf(stringa, idx + stringa.length());
    return idx != -1;
  }

  public String lastPart(String stringa, String stringb) {
    int idx = stringb.indexOf(stringa);
    if (idx == -1) {
      return stringb;
    }
    return stringb.substring(idx + stringa.length());
  }

  public void testing() {
    System.out.println("twoOccurrences");
    System.out.println();

    String stringa = "by";
    String stringb = "A story by Abby Long";
    System.out.println("stringa = " + stringa + ", stringb = " + stringb);
    System.out.println("output = " + Boolean.toString(twoOccurrences(stringa, stringb)));
    System.out.println();

    stringa = "a";
    stringb = "banana";
    System.out.println("stringa = " + stringa + ", stringb = " + stringb);
    System.out.println("output = " + Boolean.toString(twoOccurrences(stringa, stringb)));
    System.out.println();

    stringa = "atg";
    stringb = "ctgtatgta";
    System.out.println("stringa = " + stringa + ", stringb = " + stringb);
    System.out.println("output = " + Boolean.toString(twoOccurrences(stringa, stringb)));
    System.out.println();

    System.out.println("lastPart");
    System.out.println();

    stringa = "an";
    stringb = "banana";
    System.out.println("stringa = " + stringa + ", stringb = " + stringb);
    System.out.println("output = " + lastPart(stringa, stringb));
    System.out.println();

    stringa = "zoo";
    stringb = "forest";
    System.out.println("stringa = " + stringa + ", stringb = " + stringb);
    System.out.println("output = " + lastPart(stringa, stringb));
  }

  public static void main(String[] args) {
    Part3 p3 = new Part3();
    p3.testing();
  }

}
