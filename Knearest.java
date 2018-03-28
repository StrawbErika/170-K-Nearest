import java.io.*;
import java.util.*;
import java.lang.Math.*;

public class Knearest {

    public ArrayList<Vectors> trainingData;
    public ArrayList<Vectors> answers;
    public Vectors x;
    public Integer k;

    public Knearest(){
        this.k = 0;
        this.trainingData = new ArrayList<Vectors>();
        this.x = new Vectors();
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
                if(in > 1){
                    String[] values = line.split(" "); //stores all the words from the line in values
                    Vectors training = new Vectors(Double.parseDouble(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2]));
                    trainingData.add(training);
                }else{
                  k = Integer.parseInt(line);
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
            writer.write("Nearest Neighbors of (" + x.x +  x.y+ "):");
            for(int i = 0; i < answers.size(); i++){
                writer.write("("+ answers.get(i).x+","+ answers.get(i).y +"),");
            }
            writer.write("Class of (" + x.x + x.y+ "):" + x.c);

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
