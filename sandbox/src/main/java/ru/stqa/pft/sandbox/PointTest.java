package ru.stqa.pft.sandbox;

public class PointTest {
  public static void main(String[] args) {
    Point p1 = new Point(2, 1);
    Point p2 = new Point(-1, -3);
    System.out.println("Через функцию класса PointTest: Расстояние между двумя точка P1 (" + p1.x +", " + p1.y + ")  и P2 ("+ p2.x +", " + p2.y + ") = " + distance(p1,p2));
    System.out.println("Через метод класса Point: Расстояние между двумя точка P1 (" + p1.x +", " + p1.y + ")  и P2 ("+ p2.x +", " + p2.y + ") = " + p2.distance(p1));
  }
  public static double distance(Point p1, Point p2){
    return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y));
  }
}
