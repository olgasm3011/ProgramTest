package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by oasmir12 on 16.08.2017.
 */

@Entity
@Table(name = "mantis_user_table")
public class UserData {
  @Id
  @Column(name = "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "username")
  private String username;

  @Expose
  @Column(name = "email")
  private String email;

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }


  public UserData withEmail(String email) {
    this.email = email;
    return this;
  }


  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
