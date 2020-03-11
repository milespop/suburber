package com.nurturecloud.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties
/**
 * Immutable representation of the locations found in the given suburbs json file
 */
public class Location {

  private final int postCode;
  private final String locality;
  private final String state;
  private final String comments;
  private final String category;
  private final Double longitude;
  private final Double latitude;

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public Location(
      @JsonProperty("Pcode") int postCode,
      @JsonProperty("Locality") String locality,
      @JsonProperty("State") String state,
      @JsonProperty("Comments") String comments,
      @JsonProperty("Category") String category,
      @JsonProperty("Longitude") Double longitude,
      @JsonProperty("Latitude") Double latitude) {
    this.postCode = postCode;
    this.locality = locality;
    this.state = state;
    this.comments = comments;
    this.category = category;
    this.longitude = longitude;
    this.latitude = latitude;
  }

  public int getPostCode() {
    return postCode;
  }

  public String getLocality() {
    return locality;
  }

  public Double getLongitude() {
    return longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

}
