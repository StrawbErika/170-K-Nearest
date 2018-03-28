import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Knearest nearest = new Knearest();
        nearest.loadInputFile();
        nearest.loadTrainingFile();
        nearest.getAllDistance();
        nearest.getKNearest();
        nearest.getC();
        nearest.saveFile();

    }
}
