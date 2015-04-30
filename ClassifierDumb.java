import java.lang.Math;
import java.util.*;
import java.io.*;


public class ClassifierDumb extends Classifier {

	public ClassifierDumb(String namesFilepath){ super(namesFilepath); }

	public void train(String trainingDataFilpath) {

	}

	public void makePredictions(String testDataFilepath) {
		try {
			Scanner sc = new Scanner(new File(testDataFilepath));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				//System.out.print(line+ " ");
				double pred = Math.random();
				if(pred >= 0.5) {
					System.out.println(">50K");
				}
				else {
					System.out.println("<=50K");
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}