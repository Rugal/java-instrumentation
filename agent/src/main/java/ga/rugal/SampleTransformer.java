package ga.rugal;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

/**
 * Sample transformer.
 *
 * @author sally
 */
public class SampleTransformer implements ClassFileTransformer {

  @Override
  public byte[] transform(final ClassLoader loader,
                          final String className,
                          final Class<?> classBeingRedefined,
                          final ProtectionDomain protectionDomain,
                          final byte[] classfileBuffer) {

    byte[] byteCode = classfileBuffer;
    if (!className.equals("ga/rugal/Main")) {
      return byteCode;
    }
    try {
      final ClassPool classPool = ClassPool.getDefault();
      final CtClass ctClass = classPool.makeClass(new ByteArrayInputStream(classfileBuffer));
      final CtMethod[] methods = ctClass.getDeclaredMethods();
      for (var method : methods) {
        method.insertAfter("System.out.println(\"adding end line..\");");
      }
      byteCode = ctClass.toBytecode();
      ctClass.detach();
    } catch (final IOException | RuntimeException | CannotCompileException ex) {
      System.out.println("Exception: " + ex);
      ex.printStackTrace();
    }
    return byteCode;
  }
}
