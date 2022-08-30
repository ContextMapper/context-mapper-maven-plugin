package org.contextmapper.maven;

import org.apache.maven.project.MavenProject;

import java.io.File;
import java.io.FileNotFoundException;

public class FileUtils {

  public static File resolveInputFile(MavenProject project, String filename) throws FileNotFoundException {
    File file = new File(filename);
    if (file.isAbsolute() && file.exists() && file.isFile()) {
      return file;
    }
    if (!file.isAbsolute()) {
      file = project.getBasedir().toPath().resolve(filename).toFile();
      if (file.exists() && file.isFile()) {
        return file;
      }
    }
    throw new FileNotFoundException("Could not find input file: " + filename);
  }


  public static File resolveOutputDirectory(MavenProject project, String dirName) throws FileNotFoundException {
    if (dirName == null || "".equals(dirName)) {
      throw new FileNotFoundException("Output directory must be provided.");
    }
    // try absolute path
    File dir = new File(dirName);
    if (dir.isAbsolute()) {
      if (dir.exists() && dir.isDirectory()) {
        return dir;
      } else {
        throw new FileNotFoundException("Could not find output directory: " + dirName);
      }
    } else {
      // try relative path to the project basedir
      dir = project.getBasedir().toPath().resolve(dirName).toFile();
      if (dir.exists() && dir.isDirectory()) {
        return dir;
      } else {
        if (dir.mkdirs()) {
          return dir;
        } else {
          throw new FileNotFoundException("Could not create output directory: " + dirName);
        }
      }
    }
  }

}
