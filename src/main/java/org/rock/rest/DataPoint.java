package org.rock.rest;

public class DataPoint {

  String name;
  int value;
  int colorValue;

  public DataPoint(String name, int value, int colorValue) {
    this.name = name;
    this.value = value;
    this.colorValue = colorValue;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getColorValue() {
    return colorValue;
  }

  public void setColorValue(int colorValue) {
    this.colorValue = colorValue;
  }
  
}
