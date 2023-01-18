package ga.rugal;

import java.security.ProtectionDomain;

/**
 * To generate the proxy class.<BR>
 * A proxy class is one that does not actually do the business logic, but to route the request to
 * the correct implementation.
 *
 * @author sally
 */
public class ProxyGenerator {

  public void generate(final ClassLoader loader,
                       final String className,
                       final Class<?> classBeingRedefined,
                       final ProtectionDomain protectionDomain,
                       final byte[] classfileBuffer) {
  }
}
