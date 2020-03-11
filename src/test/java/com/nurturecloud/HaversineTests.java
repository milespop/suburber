package com.nurturecloud;

import com.nurturecloud.util.Haversine;
import org.junit.Assert;
import org.junit.Test;

public class HaversineTests {

  @Test
  public void testZeroDistance() {
    double distance = Haversine.distance(0, 0, 0, 0);
    Assert.assertEquals(0, distance, 0);
  }

  @Test
  public void testJoburgToSydney() {
    double distance = Haversine.distance(26.2041, 28.0473,
        33.8688, 151.2093);
    Assert.assertEquals(11040, distance, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadLat() {
    Haversine.distance(191, 0,
        0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBadLong() {
    Haversine.distance(180, 91,
        0, 0);
  }

  @Test
  public void testZeroHaversine() {
    double haversine = Haversine.haversine(0, 0);
    Assert.assertEquals(0, haversine, 0);
  }

  @Test
  public void testHaversineNotNegative() {
    double haversine = Haversine.haversine(10, -1000);
    Assert.assertTrue(haversine > 0);
  }



}
