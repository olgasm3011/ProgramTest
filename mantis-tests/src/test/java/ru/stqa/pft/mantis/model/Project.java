package ru.stqa.pft.mantis.model;

/**
 * Created by oasmir12 on 17.08.2017.
 */
public class Project {
  private int id;
  private String name;

  public int getId() {
    return id;
  }



  public String getName() {
    return name;
  }

  public Project withName(String name) {
    this.name = name;
    return this;
  }
  public Project withId(int id) {
    this.id = id;
    return this;
  }
}
