import java.io.*;
import java.util.*;

public class Vectors {
  public Double x, y, d;
  public int c;
//x, y, classification, distance
  public Vectors(Double x, Double y, int c){
    this.x = x;
    this.y = y;
    this.c = c;
    this.d = 0.0;
  }
  public Vectors(){
    this.x = 0.0;
    this.y = 0.0;
    this.c = 2;
    this.d = 0.0;

  }
  public void print(){
    System.out.println(this.x + " " + this.y + " " +this.c+" "+this.d);
  }

}
