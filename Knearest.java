import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class Knearest {

    public ArrayList<Vectors> trainingData;
    public ArrayList<Vectors> trainingDataDuplicate;
    public ArrayList<Vectors> answers;
    public Vectors x;
    public Integer k;

    public Knearest(){
        this.k = 0;
        this.trainingData = new ArrayList<Vectors>();
        this.trainingDataDuplicate = new ArrayList<Vectors>();
        this.answers = new ArrayList<Vectors>();
        this.x = new Vectors();
    }

    public void getC(){
      int one = 0;
      int zero = 0;
      for(int i = 0; i < k; i++){
        if(answers.get(i).c == 1){
          one++;
        }
        else{
          zero++;
        }
      }
      if(one > zero){
        x.c = 1;
      }
      else{
        x.c = 0;
      }
    }

    public void getKNearest(){
      Double distance = 0.0;
      int i = 0;
      while(i!=k){
        Vectors n = getNearest();
        if(i == 0){
          answers.add(n);
          trainingDataDuplicate.remove(n);
          i++;
        }
        else {
          if(!answers.contains(n)){
            answers.add(n);
            trainingDataDuplicate.remove(n);
            i++;
          }
        }
      }
    }

    public Vectors getNearest(){
      Double min = 9999999.9;
      int minIndex = 0;
      for(int i = 0; i < trainingDataDuplicate.size(); i++){
        if(trainingDataDuplicate.get(i).d < min){
          min = trainingDataDuplicate.get(i).d;
          minIndex = i;
        }
      }
      return trainingDataDuplicate.get(minIndex);
    }

    public void getAllDistance(){
      for(int i = 0; i < trainingData.size(); i++){
        computeDistance(trainingData.get(i));
      }
    }

    public void computeDistance(Vectors b){
      Double squareX = (x.x - b.x) * (x.x - b.x);
      Double squareY = (x.y - b.y) * (x.y - b.y);
      b.d = Math.sqrt(squareX + squareY);
    }
    public void loadTrainingFile() {
        String filename = "training.txt";
        try{
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream inData = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(inData));
            String line;
            Double input;
            int in = 0;

            while((line  = br.readLine()) != null)
            {
                if(in > 0){
                    String[] values = line.split(" "); //stores all the words from the line in values
                    Vectors training = new Vectors(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Integer.parseInt(values[2]));
                    trainingData.add(training);
                    trainingDataDuplicate.add(training);
                }
                else{
                  k = Integer.parseInt(line);
                  in++;
                }
            }
            inData.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void loadInputFile() {
        String filename = "input.txt";
        try{
            FileInputStream fstream = new FileInputStream(filename);
            DataInputStream inData = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(inData));
            String line;
            Double input;

            while((line  = br.readLine()) != null){
              String[] values = line.split(" "); //stores all the words from the line in values
              x.x = Double.parseDouble(values[0]);
              x.y = Double.parseDouble(values[1]);
            }

            inData.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void saveFile() { //writes file
        String filename = "output.txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write("Nearest Neighbors of (" + x.x +","+  x.y+ "):");
            for(int i = 0; i < answers.size(); i++){
                writer.write("("+ answers.get(i).x+","+ answers.get(i).y +") ");
            }
            writer.write("\n");
            writer.write("Class of (" + x.x +","+ x.y+ "):" + x.c);

            writer.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filename + "'");
        }
        catch(IOException ex) {
            System.out.println("Error writing file '" + filename + "'");
        }
    }
}
