import java.lang.Object;
import java.util.*;
import java.io.*;

public class ClassifierMfl4an extends Classifier {
	String[] workClassList = {"Private", "Self-emp-not-inc", "Self-emp-inc", "Federal-gov", "Local-gov", "State-gov", "Without-pay", "Never-worked"};
	String[] educationList = {"Bachelors", "Some-college", "11th", "HS-grad", "Prof-school", "Assoc-acdm", "Assoc-voc", "9th", "7th-8th", "12th", "Masters", "1st-4th", "10th", "Doctorate", "5th-6th", "Preschool"};
	String[] maritalStatusList = {"Married-civ-spouse", "Divorced", "Never-married", "Separated", "Widowed", "Married-spouse-absent", "Married-AF-spouse"};
	String[] occupationList = {"Tech-support", "Craft-repair", "Other-service", "Sales", "Exec-managerial", "Prof-specialty", "Handlers-cleaners", "Machine-op-inspct", "Adm-clerical", "Farming-fishing", "Transport-moving", "Priv-house-serv", "Protective-serv", "Armed-Forces"};
	String[] relationshipList = {"Wife", "Own-child", "Husband", "Not-in-family", "Other-relative", "Unmarried"};
	String[] raceList = {"White", "Asian-Pac-Islander", "Amer-Indian-Eskimo", "Other", "Black"};
	String[] sexList = {"Female", "Male"};
	String[] countries = {"United-States", "Cambodia", "England", "Puerto-Rico", "Canada", "Germany", "Outlying-US(Guam-USVI-etc)", "India", "Japan", "Greece", "South", "China", "Cuba", "Iran", "Honduras", "Philippines", "Italy", "Poland", "Jamaica", "Vietnam", "Mexico", "Portugal", "Ireland", "France", "Dominican-Republic", "Laos", "Ecuador", "Taiwan", "Haiti", "Columbia", "Hungary", "Guatemala", "Nicaragua", "Scotland", "Thailand", "Yugoslavia", "El-Salvador", "Trinadad&Tobago", "Peru", "Hong", "Holand-Netherlands"};

	//age groups: 0-50, 50-100
	public float age050Y;
	public float age50100Y;
	public float age050NotY;
	public float age50100NotY;

	//work class groups:
	public float wCprivate;
	public float wCseni;
	public float wCsei;
	public float wCfg;
	public float wClg;
	public float wCsg;
	public float wCwp;
	public float wCnw;

	//education groups
	public float eB;
	public float eSC;
	public float e11th;
	public float eHSG;
	public float ePS;
	public float eAA;
	public float eAV;
	public float e9th;
	public float e78th;
	public float e12th;
	public float eM;
	public float e14st;
	public float e10th;
	public float eD;
	public float e56th;
	public float ePre;
	
	//marital status groups
	public float mSmcs;
	public float mSd;
	public float mSnm;
	public float mSs;
	public float mSw;
	public float mSmsa;
	public float mSmas;

	//occupation groups
	public float oTS;
	public float oCR;
	public float oOS;
	public float oS;
	public float oEM;
	public float oProfSpec;
	public float oHC;
	public float oMOI;
	public float oAC;
	public float oFF;
	public float oTM;
	public float oPHS;
	public float oPS;
	public float oAF;

	//relationship groups
	public float rW;
	public float rOC;
	public float rH;
	public float rNIF;
	public float rOR;
	public float rU;

	//race groups
	public float raceW;
	public float raceAPI;
	public float raceAIE;
	public float raceO;
	public float raceB;

	//gender groups
	public float male;
	public float female;

	//country groups
	public float cUS;
	public float cCambodia;
	public float cEngland;
	public float cPuertoRico;
	public float cCanada;
	public float cGermany;
	public float cOutUS;
	public float cIndia;
	public float cJapan;
	public float cGreece;
	public float cSouth;
	public float cChina;
	public float cCuba;
	public float cIran;
	public float cHonduras;
	public float cPhillippines;
	public float cItaly;
	public float cPoland;
	public float cJamaica;
	public float cVietnam;
	public float cMexico;
	public float cPortugal;
	public float cIreland;
	public float cFrance;
	public float cDomRep;
	public float cLaos;
	public float cEcuador;
	public float cTaiwan;
	public float cHaiti;
	public float cColumbia;
	public float cHungary;
	public float cGuatemala;
	public float cNicaragua;
	public float cScotland;
	public float cThailand;
	public float cYugoslavia;
	public float cElSalv;
	public float cTrinandTobag;
	public float cPeru;
	public float cHong;
	public float cHolNeth;

  	public String moreThan50k = "";
	public ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

    int lt50k;
    int gt50k;

    private final int L = 4;         // arbitrarily chosen number
    
	public ClassifierMfl4an(String namesFilePath) {
		super(namesFilePath);
		parseData(namesFilePath, true);
	}

    private int getY(String Y) {
        return Y.equals("<=50k") ? lt50k : gt50k;
    }

    private int getJ(int i) {
        if (i ==1) {
            return workClassList.length;
        } else if ( i == 2) {
            return educationList.length;
        } else if ( i == 4) {
            return maritalStatusList.length;
        } else if (i == 5) {
            return occupationList.length;
        } else if (i == 6) {
            return relationshipList.length;
        } else if (i == 7) {
            return raceList.length;
        } else if (i == 9) {
            return sexList.length;
        } else if (i == 12) {
            return countries.length;
        }
        // TODO: do the buckets for integers
        //
        return -1;
    }

    /**
     * P(i=s|Y)
     */
    private double getProb(int index, String s, String Y) {
        List<Object> list = getFeatureList(index, Y);
        int matches = getNumMatches(s, list);
        return (matches + L) / ((double)getY(Y) + L*getJ(index));
    }

    private double getProb(int index, int lower, int upper, String Y) {
        List<Object> list = getFeatureList(index, Y);
        int matches = getNumMatches(lower, upper, list);
        return (matches + L) / ((double)getY(Y) + L*getJ(index));
    }

    private int getNumMatches(String s, List<Object> list) {
        int i = 0;
        for ( Object o : list ) {
            if( s. equals((String)o)) ++i;
        }
        return i;
    }

    private int getNumMatches(int lower, int upper, List<Object> list) {
        int i = 0;
        for ( Object o : list ) {
            if( lower <= (int) o  && (int) o <= upper)  ++i;
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
        parseData(trainingDataFilePath, true); 
        lt50k = getFeatureList(data.get(0).size()-1, "<=50k").size();
        gt50k = getFeatureList(data.get(0).size()-1, ">50k").size();
	}

	public void makePredictions(String testDataFilePath) {
		
	}

	public void parseData(String fileName, boolean training) {
		try {
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
							//System.out.println(age);
							person.add(Integer.parseInt(info[i]));
						}
						catch (Exception e) { 
							System.out.println(e);
						}
					}
					else if(searchArray(info[i], workClassList) != -1) {
						person.add(info[i].toLowerCase());
					}
					else if(searchArray(info[i], educationList) != -1) {
						person.add(info[i]);
						person.add(Integer.parseInt(info[i+1]));
						i = i+1;
					}
					else if(searchArray(info[i], maritalStatusList) != -1) {
						person.add(info[i].toLowerCase());
					}
					else if(searchArray(info[i], occupationList) != -1) {
						person.add(info[i].toLowerCase());
					}
					else if(searchArray(info[i], relationshipList) != -1) {
						person.add(info[i].toLowerCase());
					}
					else if(searchArray(info[i], raceList) != -1) {
						person.add(info[i].toLowerCase());
					}
					else if(searchArray(info[i], sexList) != -1) {
						person.add(info[i].toLowerCase());
					}
					else if(searchArray(info[i], countries) != -1) {
						person.add(info[i].toLowerCase());
					}
					else if(i == info.length - 1 && training == true) {
						if(info[info.length-1].equals("<=50k")) {
							moreThan50k = "<=50k";
						}
						else {
							moreThan50k = ">50k"; 
						}
					}
					else {
						//System.out.println("Looking for: " + info[i]);
						try {
							person.add(Integer.parseInt(info[i]));
							person.add(Integer.parseInt(info[i+1]));
                            person.add(Integer.parseInt(info[i+2]));
							i = i + 2;
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
