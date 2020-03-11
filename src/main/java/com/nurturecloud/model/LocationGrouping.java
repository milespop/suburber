package com.nurturecloud.model;

import com.nurturecloud.util.RelativeLocationComparator;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LocationGrouping {

  // The upper threshold in KM for nearby locations
  private final double nearbyThreshold;
  // The upper threshold in KM for fringe locations
  private final double fringeThreshold;

  private Set<RelativeLocation> nearbyLocations;
  private Set<RelativeLocation> fringeLocations;

  public LocationGrouping(double nearbyThreshold,
      double fringeThreshold) {
    this.nearbyThreshold = nearbyThreshold;
    this.fringeThreshold = fringeThreshold;
  }

  public Set<RelativeLocation> getNearbyLocations() {
    return nearbyLocations;
  }

  public Set<RelativeLocation> getFringeLocations() {
    return fringeLocations;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (nearbyLocations != null) {
      sb.append("Nearby Suburbs:\n");
      nearbyLocations
          .forEach(location -> {
            sb.append("\t");
            sb.append(location.getLocality());
            sb.append(" ");
            sb.append(location.getPostCode());
            sb.append(System.lineSeparator());
          });
    }
    if (fringeLocations != null) {
      sb.append(System.lineSeparator());
      sb.append("Fringe Suburbs:\n");
      fringeLocations
          .forEach(location -> {
            sb.append("\t");
            sb.append(location.getLocality());
            sb.append(" ");
            sb.append(location.getPostCode());
            sb.append(System.lineSeparator());
          });
    }
    return sb.toString();
  }

  public void addLocation(Location location, double distance) {
    if (distance > fringeThreshold) {
      return;
    } else if (distance > nearbyThreshold && distance <= fringeThreshold) {
      addFringeLocation(location, distance);
    } else {
      addNearbyLocation(location, distance);
    }
  }

  public void curateListSizes() {
    if (this.nearbyLocations != null) {
      this.nearbyLocations = this.nearbyLocations.stream()
          .sorted(RelativeLocationComparator::compare).limit(15).collect(Collectors.toSet());
    }
    if (this.fringeLocations != null) {
      this.fringeLocations = this.fringeLocations.stream()
          .sorted(RelativeLocationComparator::compare).limit(15).collect(Collectors.toSet());
    }
  }

  private void addFringeLocation(Location location, double distance) {
    if (this.fringeLocations == null) {
      this.fringeLocations = new HashSet<>();
    }
    RelativeLocation relativeLocation = RelativeLocation.fromLocation(location, distance);
    if (!this.fringeLocations.contains(relativeLocation)) {
      this.fringeLocations.add(relativeLocation);
    }
  }

  private void addNearbyLocation(Location location, double distance) {
    if (this.nearbyLocations == null) {
      this.nearbyLocations = new HashSet<>();
    }
    RelativeLocation relativeLocation = RelativeLocation.fromLocation(location, distance);
    if (!this.nearbyLocations.contains(relativeLocation)) {
      this.nearbyLocations.add(RelativeLocation.fromLocation(location, distance));
    }
  }

}
