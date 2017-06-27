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

/**
 *
 * @author Evan Reilly
 */
public class timediff{

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {

        String line = "";
        String fileName = "gateData.csv";
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            File outputFile = new File("gateData(diff).csv");
            PrintWriter printWriter = new PrintWriter(outputFile);
            String[][] lineArray = new String[180000][];


            int count = 1;
            String[] last = new String[10];

            while ((line = bufferedReader.readLine()) != null) {

                //printWriter.println(line);
                //line = line.replaceAll("\\s+", " ");
                //System.out.println(line);
               
                String[] current = line.split(",");
                //if two id are the same
                if(current[2].equals(last[2])){
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy HH:mm");
                    String time1=last[8]; //timestamp
                    String time2=current[8];
                    Date timeStart=format.parse(time1);
                    Date timeEnd=format.parse(time2);
                    long diff=timeEnd.getTime()-timeStart.getTime();
                    long diffMinutes= diff / (60 * 1000) % 60;
                    System.out.print(diffMinutes + " minutes, ");
                    String strLong = Long.toString(diffMinutes);
                    lineArray[count]=current;
                    lineArray[count][10]=strLong;

                    count += 1;
                    last=current;

                }else{
                    lineArray[count]=current;
                    count += 1;
                    last=current;
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
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '"
                    + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                    + fileName + "'");
        }
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