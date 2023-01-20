package ga.rugal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import lombok.extern.slf4j.Slf4j;

/**
 * Sample transformer.
 *
 * @author sally
 */
@Slf4j
public class SampleTransformer implements ClassFileTransformer {

  @Override
  public byte[] transform(final ClassLoader loader,
                          final String className,
                          final Class<?> classBeingRedefined,
                          final ProtectionDomain protectionDomain,
                          final byte[] classfileBuffer) {
    // LOG.info(className);
    // for every class
    // 1. create proxy class
    // 2. create concrete class
    // 3. for every invocation,
    //      replace the method call with proxy method call
    byte[] byteCode = classfileBuffer;
    if (!className.equals("ga/rugal/Main")) {
      return byteCode;
    }
    try {
      final ClassPool classPool = ClassPool.getDefault();
      final CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
      final CtMethod[] methods = ctClass.getDeclaredMethods();
      for (var method : methods) {
        method.insertAfter(
          """
          LOG.error("Tenjin Descend");
          """
        );
      }
      byteCode = ctClass.toBytecode();
      ctClass.detach();
    } catch (final IOException | RuntimeException | CannotCompileException ex) {
      LOG.error("Exception: " + ex);
    }
    return byteCode;
  }
}
