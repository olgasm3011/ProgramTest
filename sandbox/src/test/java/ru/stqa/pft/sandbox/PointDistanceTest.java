package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import static java.lang.StrictMath.sqrt;

public class PointDistanceTest {
  @Test
  public void testDistance1(){
    Point p1 = new Point(2, 1);
    Point p2 = new Point(-1, -3);
    Assert.assertEquals(p2.distance(p1), 5.0);
  }
  @Test
  public void testDistance2() {
    Point p1 = new Point(5, 2);
    Point p2 = new Point(1, 6);
    Assert.assertEquals(p2.distance(p1), sqrt(32));
  }
}
