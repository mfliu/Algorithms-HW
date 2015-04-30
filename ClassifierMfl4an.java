import java.lang.Object;
import java.util.*;
import java.io.*;

public class ClassifierMfl4an extends Classifier {
	public int age;
	public String workclass;
	public String education;
	public int educationNum;
	public String maritalStatus;
	public String occupation;
	public String relationship;
	public String race;
	public String gender;
	public int capitalGain;
	public int capitalLoss;
	public int hoursPerWeek;
	public String nativeCountry;

	public ClassifierMfl4an(String namesFilePath) {
		super(namesFilePath);
		parseData(namesFilePath);
	}

	public void train(String trainingDataFilePath) {

	}

	public void makePredictions(String testDataFilePath) {
		
	}

	public void parseData(String fileName) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] info = line.split(" ");
				for(int i = 0; i < info.length; i++) {
					if(i == 0 && 
				}
				System.out.println("");

			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}	
}