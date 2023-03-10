package ga.rugal;

import java.lang.instrument.Instrumentation;

import lombok.extern.slf4j.Slf4j;

/**
 * Sample transformer.
 *
 * @author sally
 */
@Slf4j
public class SampleMain {

  /**
   * Pre main function.
   *
   * @param agentArgs agent argument
   * @param inst      instrumentation
   */
  public static void premain(final String agentArgs, final Instrumentation inst) {
    LOG.info("[Agent] In premain method");

    inst.addTransformer(new SampleTransformer());
  }

  /**
   * Attach to JVM after startup.
   *
   * @param agentArgs agent arguments
   * @param inst      instrumentation
   */
  public static void agentmain(final String agentArgs, final Instrumentation inst) {
    LOG.info("[Agent] In agentmain method");

    inst.addTransformer(new SampleTransformer());
  }
}
