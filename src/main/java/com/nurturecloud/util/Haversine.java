package com.nurturecloud.util;

/**
 * Based off Haversine as seen at https://en.wikipedia.org/wiki/Haversine_formula
 * where
 * φ1, φ2: latitude of point 1 and latitude of point 2 (in radians),
 * λ1, λ2: longitude of point 1 and longitude of point 2 (in radians).
 * Δφ = φ2 - φ1
 * Δλ = λ2 - λ1
 * The formula parts follows:
 * harvesine = sin²(Δ/2)
 * chord length = harvesine(latitude) + cos φ1 ⋅ cos φ2 ⋅ harvesine(long)
 * angular distance = 2 ⋅ atan2( √a, √(1−a) )
 * distance = Radius ⋅ angular distance
 */
public class Haversine {
  private static final int RADIUS = 6371;

  /**
   * Calculates the Harvesine distance between two points
   * @param firstLatitude The first location's latitude coordinates
   * @param firstLongitude The first locations longitudinal coordinates
   * @param secondLatitude The second locations latitude coordinates
   * @param secondLongitude The second locations longitudinal coordinates
   * @return distance between two points (in kilometers)
   */
  public static double distance(double firstLatitude, double firstLongitude,
      double secondLatitude, double secondLongitude) {
    assertPoints(firstLatitude, firstLongitude, secondLatitude, secondLongitude);

    double chordLength = haversine(secondLatitude, firstLatitude)
        + Math.cos(Math.toRadians(firstLatitude))
        * Math.cos(Math.toRadians(secondLatitude))
        * haversine(secondLongitude, firstLongitude);
    double angularDistance = 2 * Math.atan2(Math.sqrt(chordLength), Math.sqrt(1 - chordLength));
    return RADIUS * angularDistance;
  }

  /**
   * Application of the formula sin²(Δφ/2)
   * Converts the input values to radians
   * @param startPoint double point  representing lat/long
   * @param endPoint double point representing lat/long point
   * @return double representing haversine output
   */
  public static double haversine(double startPoint, double endPoint) {
    double delta = Math.toRadians(endPoint - startPoint);
    return Math.pow(Math.sin(delta / 2), 2);
  }

  private static void assertPoints(double a, double b, double c, double d) {
    if (Math.abs(b) > 180 || Math.abs(d) > 180) {
      throw new IllegalArgumentException("Longitude Points have to be between -180 and 180");
    }
    if (Math.abs(a) > 90 || Math.abs(c) > 90) {
      throw new IllegalArgumentException("Latitude Points have to be between -90 and 90, got " + b + " and " + d);
    }
  }

}
