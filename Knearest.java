import java.io.*;
import java.util.*;

public class Knearest {

    public ArrayList<ArrayList<Double>> trainingData;
    public ArrayList<ArrayList<Double>> answers;
    public ArrayList<Double> x;
    public Integer k;

    public Knearest(){
        this.k = 0;
        this.trainingData = new ArrayList<ArrayList<Double>>();
        this.x = new ArrayList<Double>();
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
                    ArrayList<Double> training = new ArrayList<Double>();
                    for (String str : values) {
                        input = Double.parseDouble(str);
                        training.add(input);
                    }
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
              for (String str : values) {
                  input = Double.parseDouble(str);
                  x.add(input);
              }
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
            writer.write("Nearest Neighbors of (" + x.get(0) +  x.get(1)+ "):");
            for(int i = 0; i < answers.size(); i++){
                writer.write("("+ answers.get(i).get(0)+","+ answers.get(i).get(2) +"),");
            }
            writer.write("Class of (" + x.get(0) + x.get(1)+ "):" + x.get(2));

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
