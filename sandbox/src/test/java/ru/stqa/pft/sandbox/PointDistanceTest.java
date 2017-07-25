package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointDistanceTest {
  @Test
  public void testDistance(){
    Point p1 = new Point(2, 1);
    Point p2 = new Point(-1, -3);
    Assert.assertEquals(p2.distance(p1), 5.0);
  }
}
