package ga.rugal;

/**
 * For loading class.
 *
 * @author Rugal Bernstein
 */
public class RugalEntrance {

  /**
   * To run class.
   *
   * @throws Exception class loading issue
   */
  public static void run() throws Exception {
    final var path = RugalClassLoader.class.getResource("/").getPath();
    final var loader = new RugalClassLoader(path);
    final var c = loader.loadClass("ga.rugal.Invokee");
    final var newInstance = c.getDeclaredConstructor().newInstance();
    c.getMethod("test").invoke(newInstance);
  }
}
