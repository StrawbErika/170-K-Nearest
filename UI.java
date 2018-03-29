import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import java.util.*;
import javax.imageio.*;

public class UI {
    private JFrame frame;
    public Knearest nearest;
    public UI() {
        this.nearest = new Knearest();
        this.initializeUI();
    }

    public void initializeUI() {
      JLabel stats02= new JLabel();
      JFrame frame = new JFrame("Ham & Spam");//creates a frame/window; javax.swing.JFrame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setPreferredSize(new Dimension(1200, 700));//sets initial size resolution of the frame...; java.awt.Dimensio

      Container con = frame.getContentPane();
      JPanel contentPanel= new JPanel();

      JPanel tabPanel= new JPanel();
      tabPanel.setLayout(new FlowLayout());
      tabPanel.setPreferredSize(new Dimension(250,500));
      // tabPanel.setBackground(Color.WHITE);

      JTextArea wordsSpam = new JTextArea();
      JTextArea frequenciesSpam = new JTextArea();
      JButton information= new JButton("Select Training text");

      information.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          JFileChooser fileChooser = new JFileChooser();
          int result = fileChooser.showOpenDialog(frame);
          if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            nearest.loadTrainingFile(selectedFile.getAbsolutePath());
          }
          frame.requestFocus();
          }
      });

      information.setPreferredSize(new Dimension(200,50));

      JPanel wordFrequencySpamPanel= new JPanel();
      wordFrequencySpamPanel.setPreferredSize(new Dimension(550,550));
      wordFrequencySpamPanel.setBackground(Color.WHITE);

      tabPanel.add(information);
      tabPanel.add(wordFrequencySpamPanel);


      contentPanel.setLayout(new CardLayout());
      CardLayout cardLayout= (CardLayout) contentPanel.getLayout();

      //panel 1

      JPanel hamPanel= new JPanel();
      hamPanel.setLayout(new FlowLayout());
      hamPanel.setPreferredSize(new Dimension(250,550));

      JPanel filler = new JPanel();
      filler.setPreferredSize(new Dimension(700,50));

      JTextField wordPanel= new JTextField();
      wordPanel.setPreferredSize(new Dimension(550,250));
      wordPanel.setBackground(Color.WHITE);

      JButton classify= new JButton("Classify");
      classify.setPreferredSize(new Dimension(200,45));

      JPanel pointClass= new JPanel();
      pointClass.setPreferredSize(new Dimension(550,50));
      pointClass.setBackground(Color.GRAY);
      pointClass.setLayout(new GridLayout(1,2));
      JLabel point = new JLabel("Point");
      JLabel className = new JLabel("Class");
      pointClass.add(point);
      pointClass.add(className);

      JPanel pointClassPanel= new JPanel();
      pointClassPanel.setPreferredSize(new Dimension(550,190));
      pointClassPanel.setBackground(Color.WHITE);
      pointClassPanel.setLayout(new GridLayout(1,2));
      JLabel pointPanel = new JLabel();
      JLabel classPanel = new JLabel();
      pointClassPanel.add(pointPanel);
      pointClassPanel.add(classPanel);

      classify.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
          String data01= wordPanel.getText();
          pointPanel.setText(data01);
          nearest.getX(data01);
          nearest.x.print();
          nearest.getAllDistance();
          nearest.getKNearest();
          nearest.getC();
          String classData = Integer.toString(nearest.x.c);
          classPanel.setText(classData);
          nearest.saveFile();
        }
      });


      hamPanel.add(filler);
      hamPanel.add(wordPanel);
      hamPanel.add(classify);
      hamPanel.add(pointClass);
      hamPanel.add(pointClassPanel);

  //--

      con.setLayout(new GridLayout(1,2));
      con.add(tabPanel);
      con.add(hamPanel);

      frame.pack();
  		frame.setVisible(true);
    }
  }
