package ga.rugal;

import java.security.ProtectionDomain;

/**
 * To generate the concrete class.<BR>
 * A concrete class is basically the original one, but with a version number in class name.
 *
 * @author sally
 */
public class ConcreteGenerator {

  /**
   * Generate a concrete class by incrementing the version number.
   *
   * @param loader target class loader
   * @param className original class name
   * @param classBeingRedefined original class object
   * @param protectionDomain the protection domain
   * @param classfileBuffer the class byte code
   */
  public void generate(final ClassLoader loader,
                       final String className,
                       final Class<?> classBeingRedefined,
                       final ProtectionDomain protectionDomain,
                       final byte[] classfileBuffer) {
    // 1. Get the latest version
    // 2. generate new class with new name
  }
}
