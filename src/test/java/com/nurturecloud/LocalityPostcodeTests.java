package com.nurturecloud;

import com.nurturecloud.model.LocalityPostcodeTuple;
import com.nurturecloud.model.Location;
import org.junit.Assert;
import org.junit.Test;

public class LocalityPostcodeTests {

  @Test
  public void testMapping() {
    LocalityPostcodeTuple expected = new LocalityPostcodeTuple(0, "");
    Location location = new Location(0, "", "", "", "", 0.0, 0.0);
    LocalityPostcodeTuple output = LocalityPostcodeTuple.fromLocation(location);
    Assert.assertEquals(expected, output);
    Assert.assertEquals(expected.hashCode(), output.hashCode());
  }

}
