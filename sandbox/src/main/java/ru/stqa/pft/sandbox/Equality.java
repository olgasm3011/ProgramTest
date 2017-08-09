package ru.stqa.pft.sandbox;

/**
 * Created by oasmir12 on 09.08.2017.
 */
public class Equality {
  public static void main (String[] args) {
    String s1= "firefox";
    String s2 = "firefox" + Math.sqrt(4);

    System.out.println(s1==s2);
    System.out.println(s1.equals(s2));
  }
}
