package com.nurturecloud;

import com.nurturecloud.util.Numerics;
import org.junit.Assert;
import org.junit.Test;

public class NumericsTests {

  @Test
  public void testInvalidString() {
    String test = "abc";
    boolean validInt = Numerics.isValidInt(test);
    Assert.assertFalse(validInt);
  }

  @Test
  public void testIntString() {
    String test = "123";
    boolean validInt = Numerics.isValidInt(test);
    Assert.assertTrue(validInt);
  }

}
