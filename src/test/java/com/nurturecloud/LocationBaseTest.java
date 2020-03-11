package com.nurturecloud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nurturecloud.model.Location;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LocationBaseTest {

  List<Location> locations = getLocations();

  private List<Location> getLocations() {
    try {
      ObjectMapper mapper = new ObjectMapper();
      URL suburbsJsonURL = LocationBaseTest.class.getClassLoader().getResource("aus_suburbs.json");
      List<Location> locationList = Arrays.asList(mapper.readValue(suburbsJsonURL,
          Location[].class))
          .stream()
          .filter(l -> (l.getLatitude() != null && l.getLongitude() != null))
          .collect(Collectors.toList());
      return locationList;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
