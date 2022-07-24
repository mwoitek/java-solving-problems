package solution_first;

import edu.duke.URLResource;

public class Part4 {

  public void printYoutubeLinks() {
    URLResource resource =
        new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
    String wordLower;
    int idxYoutube;
    int idxBegin;
    int idxEnd;
    for (String word : resource.words()) {
      wordLower = word.toLowerCase();
      if ((idxYoutube = wordLower.indexOf("youtube.com")) != -1) {
        idxBegin = wordLower.lastIndexOf("\"", idxYoutube - 1) + 1;
        idxEnd = wordLower.indexOf("\"", idxYoutube + 11);
        System.out.println(word.substring(idxBegin, idxEnd));
      }
    }
  }

  public static void main(String[] args) {
    Part4 p4 = new Part4();
    p4.printYoutubeLinks();
  }

}
