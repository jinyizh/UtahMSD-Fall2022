import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> months = new ArrayList<>();
        float[] rainfalls = new float[12];
        ArrayList<RainData> rainDataList = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("January", 0);
        map.put("February", 1);
        map.put("March", 2);
        map.put("April", 3);
        map.put("May", 4);
        map.put("June", 5);
        map.put("July", 6);
        map.put("August", 7);
        map.put("September", 8);
        map.put("October", 9);
        map.put("November", 10);
        map.put("December", 11);
        Scanner sc = new Scanner(new FileInputStream
                ("/Users/jinyizhou/myGithubRepo/UtahMSD/CS6011/Week1/Day2/Rainfall/src/rainfall_data.txt"));
        sc.nextLine();
        for (int i = 0; i < 240; i++) {
            String month = sc.next();
            int year = sc.nextInt();
            float rainfall = sc.nextFloat();
            if (!months.contains(month)) {
                months.add(month);
            }
            RainData rainData = new RainData(month, year, rainfall);
            rainDataList.add(rainData);
        }
        sc.close();
        float totalRainfall = 0;
        for (RainData rainData : rainDataList) {
            totalRainfall += rainData.rainfall;
        }
        float averageRainfall = (float) totalRainfall / 240;
        System.out.println("The overall average rainfall amount is " + averageRainfall + " inches.");
        PrintWriter pw = new PrintWriter
                ("/Users/jinyizhou/myGithubRepo/UtahMSD/CS6011/Week1/Day2/Rainfall/src/rainfall_results.txt");
        for (int i = 0; i < rainDataList.size(); i++) {
//            tempRainfalls.set(map.get(rainDataList.get(i).month), rainDataList.get(i).rainfall);
            rainfalls[map.get(rainDataList.get(i).month)] += rainDataList.get(i).rainfall;
        }
        for (int i = 0; i < rainfalls.length; i++) {
            pw.println("The average rainfall amount for " + months.get(i) + " is " + (rainfalls[i] / 20) + " inches.");
        }
        pw.close();
    }
}