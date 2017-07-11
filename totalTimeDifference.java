/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *///package csvprocessor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 
 * date: July 10th 2017
 */
public class totalTimeDifference{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        String line = "";
        String fileName = "Lekagul Sensor Data (1).csv";
 
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            File outputFile = new File("totalTimeDifferencep.csv");
            PrintWriter printWriter = new PrintWriter(outputFile);
            String[][] lineArray = new String[180000][2S];


            int count = 0;
            String [] currentArr= new String[5];

            ArrayList<String> start= new ArrayList<String>();
            ArrayList<String> end= new ArrayList<String>();





            while ((line = bufferedReader.readLine()) != null) {
                ArrayList<String> current= new ArrayList<String>();
                //printWriter.println(line);
                //line = line.replaceAll("\\s+", " ");
                //System.out.println(line);
               
                currentArr = line.split(",");

                for(int i=0; i<currentArr.length;i++){
                    current.add(currentArr[i]);
                    System.out.println(current.get(i));
                    
                }
                //if it is the first line of the whole csv
                if(start.isEmpty()){
                    start=current;
                }else{ 
                    if(current.get(1).equals(start.get(1))){ //same id
                        end=current;
                        //System.out.println(current.get(1));
                        //System.out.println("here");
                    } else { //different id 
                        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm");
                        String time1=start.get(0); 
                        String time2=end.get(0); 
                        Date timeStart=format.parse(time1);
                        Date timeEnd=format.parse(time2);
                        long diff=timeEnd.getTime()-timeStart.getTime();
                        long diffMinutes = TimeUnit.MILLISECONDS.toMinutes(diff); 
                        String strLong = Long.toString(diffMinutes);

                        lineArray[count][0]=end.get(1);
                        lineArray[count][1]=strLong;
                        
                        count +=1;
                        start=current;
                    }
                }
            }
            

            for (int i = 0; i < lineArray.length; i++) {
                if (lineArray[i] != null) {
                    String[] row = new String[lineArray[i].length];
                    for (int j = 0; j < lineArray[i].length; j++) {
                        row[j] = lineArray[i][j];
                    }
                    printWriter.print(csvRow(row));
                    System.out.print(csvRow(row));
                }
            }

            printWriter.close();
            bufferedReader.close();

    }

    public static String csvRow(String[] array /*String... strings*/) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            sb.append(",");
        }

        sb.append("\n");
        return sb.toString();
    }

}