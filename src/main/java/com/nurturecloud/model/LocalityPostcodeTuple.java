package com.nurturecloud.model;

import java.util.Objects;

public class LocalityPostcodeTuple {

  private final int postCode;
  private final String locality;

  public LocalityPostcodeTuple(int postCode, String locality) {
    this.postCode = postCode;
    this.locality = locality;
  }

  public static LocalityPostcodeTuple fromLocation(Location location) {
    return new LocalityPostcodeTuple(location.getPostCode(), location.getLocality());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LocalityPostcodeTuple that = (LocalityPostcodeTuple) o;
    return postCode == that.postCode &&
        locality.equals(that.locality);
  }

  @Override
  public int hashCode() {
    return Objects.hash(postCode, locality);
  }
}
