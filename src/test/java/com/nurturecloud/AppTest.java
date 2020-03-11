package com.nurturecloud;

import static org.junit.Assert.assertTrue;

import com.nurturecloud.model.LocalityPostcodeTuple;
import com.nurturecloud.model.Location;
import com.nurturecloud.model.LocationGrouping;
import com.nurturecloud.model.Status;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AppTest extends LocationBaseTest {

    App app;
    Map<LocalityPostcodeTuple, Status> statusMap;
    Map<LocalityPostcodeTuple, LocationGrouping> groupingMap;

    @Before
    public void setup() {
        app = new App();
        statusMap = new HashMap<>();
        groupingMap = new HashMap<>();
    }

    @Test
    public void testLocationResultsDONE() {
        Location location = locations.get(0);
        LocalityPostcodeTuple key = LocalityPostcodeTuple.fromLocation(location);
        statusMap.put(key, Status.DONE);
        groupingMap.put(key, new LocationGrouping(10, 50));
        App.getLocationResults(statusMap, groupingMap, locations, location);
        Assert.assertNull(groupingMap.get(key).getFringeLocations());
        Assert.assertNull(groupingMap.get(key).getNearbyLocations());
    }

    @Test
    public void testLocationResultsEMPTY() {
        Location location = locations.get(0);
        LocalityPostcodeTuple key = LocalityPostcodeTuple.fromLocation(location);
        statusMap.put(key, Status.EMPTY);
        groupingMap.put(key, new LocationGrouping(10, 50));
        App.getLocationResults(statusMap, groupingMap, locations, location);
        Assert.assertEquals(15, groupingMap.get(key).getFringeLocations().size());
        Assert.assertEquals(15, groupingMap.get(key).getNearbyLocations().size());
    }
}
