package ru.stqa.pft.github;
import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;
/**
 * Created by oasmir12 on 17.08.2017.
 */
public class GithubTests {

  @Test

  public void testCommits() throws IOException {
    Github github = new RtGithub("7d70c27a441afb2c297ae1273fe7a0d0529ae896");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("olgasm3011", "ProgramTest")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}