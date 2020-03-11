package com.nurturecloud.model;

public class RelativeLocation {

  private final int postCode;
  private final String locality;
  private final double distance;

  public RelativeLocation(int postCode, String locality, double distance) {
    this.postCode = postCode;
    this.locality = locality;
    this.distance = distance;
  }

  public static RelativeLocation fromLocation(Location location, double distance) {
    return new RelativeLocation(location.getPostCode(), location.getLocality(), distance);
  }

  public int getPostCode() {
    return postCode;
  }

  public String getLocality() {
    return locality;
  }

  public double getDistance() {
    return distance;
  }

}
