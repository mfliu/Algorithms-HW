import java.lang.Object;
import java.util.*;
import java.io.*;

public class ClassifierMfl4an extends Classifier {
	public int age = -1;
	public String workclass = "";
	public String education = "";
	public int educationNum = -1;
	public String maritalStatus = "";
	public String occupation = "";
	public String relationship = "";
	public String race = "";
	public String sex = "";
	public int capitalGain = -1;
	public int capitalLoss = -1;
	public int hoursPerWeek = -1;
	public String nativeCountry = "";
	public Boolean moreThan50k = false;
	public ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

    private final int L = 4;         // arbitrarily chosen number
    
	public ClassifierMfl4an(String namesFilePath) {
		super(namesFilePath);
		parseData(namesFilePath, true);
	}

    private int getNumMatches(String s, List<Object> list) {
        int i = 0;
        for ( Object o : list ) {
            if( s. equals((String)o)) ++i;
        }
        return i;
    }

    private int getNumMatches(int s, List<Object> list) {
        int i = 0;
        for ( Object o : list ) {
            if( s == o )  ++i;
        }
        return i;
    }

    private ArrayList<Object> getFeatureList(int index) {
        ArrayList<Object> ret = new ArrayList<Object>();
        for(int i = 0; i < data.size(); ++i) {
            ret.add(data.get(i).get(index));
        }
        return ret;
    }

    /**
     * Returns an array list of the column at index i where the corresponding Y value is passed in
     *
     * String Y : either >50K or <=50K
     *
     */
    private ArrayList<Object> getFeatureList(int index, String Y) {
        ArrayList<Object> ret = new ArrayList<Object>();
        for(int i = 0; i < data.size(); ++i) {
            if ( data.get(i).get(data.get(i).size()-1).equals(Y) )
                ret.add(data.get(i).get(index));
        }
        return ret;
    }

	public void train(String trainingDataFilePath) {
        
	}

	public void makePredictions(String testDataFilePath) {
		
	}

	public void parseData(String fileName, Boolean training) {
		try {
			String[] workClassList = {"Private", "Self-emp-not-inc", "Self-emp-inc", "Federal-gov", "Local-gov", "State-gov", "Without-pay", "Never-worked"};
			String[] educationList = {"Bachelors", "Some-college", "11th", "HS-grad", "Prof-school", "Assoc-acdm", "Assoc-voc", "9th", "7th-8th", "12th", "Masters", "1st-4th", "10th", "Doctorate", "5th-6th", "Preschool"};
			String[] maritalStatusList = {"Married-civ-spouse", "Divorced", "Never-married", "Separated", "Widowed", "Married-spouse-absent", "Married-AF-spouse"};
			String[] occupationList = {"Tech-support", "Craft-repair", "Other-service", "Sales", "Exec-managerial", "Prof-specialty", "Handlers-cleaners", "Machine-op-inspct", "Adm-clerical", "Farming-fishing", "Transport-moving", "Priv-house-serv", "Protective-serv", "Armed-Forces"};
			String[] relationshipList = {"Wife", "Own-child", "Husband", "Not-in-family", "Other-relative", "Unmarried"};
			String[] raceList = {"White", "Asian-Pac-Islander", "Amer-Indian-Eskimo", "Other", "Black"};
			String[] sexList = {"Female", "Male"};
			String[] countries = {"United-States", "Cambodia", "England", "Puerto-Rico", "Canada", "Germany", "Outlying-US(Guam-USVI-etc)", "India", "Japan", "Greece", "South", "China", "Cuba", "Iran", "Honduras", "Philippines", "Italy", "Poland", "Jamaica", "Vietnam", "Mexico", "Portugal", "Ireland", "France", "Dominican-Republic", "Laos", "Ecuador", "Taiwan", "Haiti", "Columbia", "Hungary", "Guatemala", "Nicaragua", "Scotland", "Thailand", "Yugoslavia", "El-Salvador", "Trinadad&Tobago", "Peru", "Hong", "Holand-Netherlands"};
			Scanner sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] info = line.split(" ");
				for(int i = 0; i < info.length; i++) {
					ArrayList<Object> person = new ArrayList<Object>();
					//System.out.println(info[i]);
					if(i == 0) {
						try { 
							Integer.parseInt(info[i]); 
							age = Integer.parseInt(info[i]); 
							//System.out.println(age);
							person.add(age);
						}
						catch (Exception e) { 
							System.out.println(e);
						}
					}
					else if(searchArray(info[i], workClassList) != -1) {
						workclass = info[i].toLowerCase();
						person.add(workclass);
					}
					else if(searchArray(info[i], educationList) != -1) {
						education = info[i];
						educationNum = Integer.parseInt(info[i+1]);
						i = i+1;
						person.add(education);
						person.add(educationNum);
					}
					else if(searchArray(info[i], maritalStatusList) != -1) {
						maritalStatus = info[i].toLowerCase();
						person.add(maritalStatus);
					}
					else if(searchArray(info[i], occupationList) != -1) {
						occupation = info[i].toLowerCase();
						person.add(occupation);
					}
					else if(searchArray(info[i], relationshipList) != -1) {
						relationship = info[i].toLowerCase();
						person.add(relationship);
					}
					else if(searchArray(info[i], raceList) != -1) {
						race = info[i].toLowerCase();
						person.add(race);
					}
					else if(searchArray(info[i], sexList) != -1) {
						sex = info[i].toLowerCase();
						person.add(sex);
					}
					else if(searchArray(info[i], countries) != -1) {
						nativeCountry = info[i].toLowerCase();
						person.add(nativeCountry);
					}
					else if(i == info.length - 1 && training == true) {
						if(info[info.length-1].equals("<=50k")) {
							moreThan50k = false;
						}
						else {
							moreThan50k = true; 
						}
					}
					else {
						//System.out.println("Looking for: " + info[i]);
						try {
							Integer.parseInt(info[i]); 
							capitalGain = Integer.parseInt(info[i]); 
							capitalLoss = Integer.parseInt(info[i+1]); 
							hoursPerWeek = Integer.parseInt(info[i+2]);
							i = i + 2;
							person.add(capitalGain);
							person.add(capitalLoss);
						}
						catch (Exception e) {
							System.out.println(e);
						}
					}
					person.add(moreThan50k);
					data.add(person);
				}
			}
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	public int searchArray(String toFind, String[] contents) {
		for(int i = 0; i < contents.length; i++) {
			if(contents[i].toLowerCase().equals(toFind.toLowerCase())) {
				//System.out.println("Found " + contents[i] + " " + toFind);
				return i;
			}
		}
		//System.out.print("Couldn't find " + toFind.toLowerCase() + " in ");
		//printArray(contents);
		return -1;
	}	

	public void printArray(String[] contents) {
		System.out.print("[");
		for(int i = 0; i < contents.length; i++) {
			System.out.print(contents[i]);
			System.out.print(", ");
		}
		System.out.println("]");
	}
}
