package ga.rugal;

import java.lang.instrument.Instrumentation;

/**
 * Sample transformer.
 *
 * @author sally
 */
public class SampleMain {

  /**
   * Pre main function.
   *
   * @param agentArgs agent argument
   * @param inst instrumentation
   */
  public static void premain(final String agentArgs, final Instrumentation inst) {
    System.out.println("[Agent] In premain method");

    inst.addTransformer(new SampleTransformer());
  }
}
