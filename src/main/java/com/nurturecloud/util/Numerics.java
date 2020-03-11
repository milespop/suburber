package com.nurturecloud.util;

public class Numerics {

  public static boolean isValidInt(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
