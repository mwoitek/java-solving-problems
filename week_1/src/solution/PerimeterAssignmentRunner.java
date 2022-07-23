package solution;

import java.io.File;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import edu.duke.Point;
import edu.duke.Shape;

public class PerimeterAssignmentRunner {

  public double getPerimeter(Shape s) {
    double totalPerim = 0.0;
    Point prevPt = s.getLastPoint();
    for (Point currPt : s.getPoints()) {
      double currDist = prevPt.distance(currPt);
      totalPerim = totalPerim + currDist;
      prevPt = currPt;
    }
    return totalPerim;
  }

  public int getNumPoints(Shape s) {
    int numPoints = 0;
    for (Point pt : s.getPoints()) {
      numPoints += 1;
    }
    return numPoints;
  }

  public double getAverageLength(Shape s) {
    return getPerimeter(s) / getNumPoints(s);
  }

  public double getLargestSide(Shape s) {
    //

    return 0.0;
  }

  public double getLargestX(Shape s) {
    // Put code here
    return 0.0;
  }

  public double getLargestPerimeterMultipleFiles() {
    // Put code here
    return 0.0;
  }

  public String getFileWithLargestPerimeter() {
    // Put code here
    // File temp = null; // replace this code
    // return temp.getName();
    return "temp.getName()";
  }

  public void testPerimeter() {
    FileResource fr = new FileResource("../data/example1.txt");
    Shape s = new Shape(fr);
    double length = getPerimeter(s);
    System.out.println("perimeter = " + length);
    int numPoints = getNumPoints(s);
    System.out.println("number of points = " + numPoints);
    double averageLength = getAverageLength(s);
    System.out.println("the average length of a side in your shape is " + averageLength);
  }

  public void testPerimeterMultipleFiles() {
    // Put code here
  }

  public void testFileWithLargestPerimeter() {
    // Put code here
  }

  // This method creates a triangle that you can use to test your other methods
  public void triangle() {
    Shape triangle = new Shape();
    triangle.addPoint(new Point(0, 0));
    triangle.addPoint(new Point(6, 0));
    triangle.addPoint(new Point(3, 6));
    for (Point p : triangle.getPoints()) {
      System.out.println(p);
    }
    double peri = getPerimeter(triangle);
    System.out.println("perimeter = " + peri);
  }

  // This method prints names of all files in a chosen folder that you can use to test
  // your other methods
  public void printFileNames() {
    DirectoryResource dr = new DirectoryResource();
    for (File f : dr.selectedFiles()) {
      System.out.println(f);
    }
  }

  public static void main(String[] args) {
    PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
    pr.testPerimeter();
  }

}
