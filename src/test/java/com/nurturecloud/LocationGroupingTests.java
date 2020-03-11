package com.nurturecloud;

import com.nurturecloud.model.Location;
import com.nurturecloud.model.LocationGrouping;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LocationGroupingTests extends LocationBaseTest {

  LocationGrouping grouping;

  @Before
  public void init() {
    grouping = new LocationGrouping(10, 50);
  }

  @Test
  public void testToString() {
    String expectedOutput = "Nearby Suburbs:\n"
        + "\tDARWIN 800\r\n"
        + "\r\n"
        + "Fringe Suburbs:\n"
        + "\tDARWIN 800\r\n";
    Location firstLocation = locations.get(0);
    grouping.addLocation(firstLocation, 5);
    grouping.addLocation(firstLocation, 40);
    Assert.assertEquals(expectedOutput, grouping.toString());
  }

  @Test
  public void testRelativeSizing() {
    List<Location> sublist = locations.subList(0, 20);
    sublist.forEach(location -> grouping.addLocation(location, 55));
    sublist.forEach(location -> grouping.addLocation(location, 45));
    sublist.forEach(location -> grouping.addLocation(location, 10));
    grouping.curateListSizes();
    Assert.assertEquals(15, grouping.getNearbyLocations().size());
    Assert.assertEquals(15, grouping.getFringeLocations().size());
  }

}
