package ga.rugal;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;

/**
 * Load code in a hot deployment fashion.
 *
 *
 * @author Rugal Bernstein
 */
@Slf4j
public class RugalClassLoader extends ClassLoader {

  private final String path;

  private final Set<String> classes = new HashSet<>();

  /**
   * Create Rugal class loader that could load class file from the given path.
   *
   * @param path the path to load class file from
   * @throws Exception file not readable
   */
  public RugalClassLoader(final String path) throws Exception {
    this.path = path;
    this.loadAllClasses(new File(this.path));
  }

  private void loadAllClasses(final File file) throws Exception {
    if (file.isDirectory()) {
      LOG.debug("Look into directory [{}]", file.getName());
      for (File f : file.listFiles()) {
        this.loadAllClasses(f);
      }
    }
    LOG.debug("Look into file [{}]", file.getName());
    if (!file.getName().endsWith(".class")) {
      return;
    }
    final var input = new FileInputStream(file);
    final var bytes = input.readAllBytes();
    final var className = this.toClassName(file.getPath());
    LOG.debug("Define class [{}] file [{}] [{}]", className, file.getName(), file.getPath());
    this.defineClass(className, bytes, 0, bytes.length);
    this.classes.add(className);
  }

  @Override
  public Class<?> loadClass(final String name) throws ClassNotFoundException {
    final var c = this.findLoadedClass(name);
    if (null != c) {
      return c;
    }
    if (this.classes.contains(name)) {
      throw new ClassNotFoundException("This class is not loaded properlys");
    }
    return ClassLoader.getSystemClassLoader().loadClass(name);
  }

  /**
   * Convert file name into class name.
   *
   * @param fileName format be like a/b/c/D.class
   * @return class name a.b.c.D
   */
  private String toClassName(final String fileName) {
    final var pattern = "target/classes/";
    return fileName.substring(fileName.indexOf(pattern) + pattern.length(),
                              fileName.lastIndexOf(".class"))
      .replace("/", ".");
  }
}
