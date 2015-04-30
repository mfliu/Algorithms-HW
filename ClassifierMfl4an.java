import java.lang.Object;
import java.util.*;
import java.io.*;
import java.text.*;

public class ClassifierMfl4an extends Classifier {
	String[] workClassList = {"Private", "Self-emp-not-inc", "Self-emp-inc", "Federal-gov", "Local-gov", "State-gov", "Without-pay", "Never-worked"};
	String[] educationList = {"Bachelors", "Some-college", "11th", "HS-grad", "Prof-school", "Assoc-acdm", "Assoc-voc", "9th", "7th-8th", "12th", "Masters", "1st-4th", "10th", "Doctorate", "5th-6th", "Preschool"};
	String[] maritalStatusList = {"Married-civ-spouse", "Divorced", "Never-married", "Separated", "Widowed", "Married-spouse-absent", "Married-AF-spouse"};
	String[] occupationList = {"Tech-support", "Craft-repair", "Other-service", "Sales", "Exec-managerial", "Prof-specialty", "Handlers-cleaners", "Machine-op-inspct", "Adm-clerical", "Farming-fishing", "Transport-moving", "Priv-house-serv", "Protective-serv", "Armed-Forces"};
	String[] relationshipList = {"Wife", "Own-child", "Husband", "Not-in-family", "Other-relative", "Unmarried"};
	String[] raceList = {"White", "Asian-Pac-Islander", "Amer-Indian-Eskimo", "Other", "Black"};
	String[] sexList = {"Female", "Male"};
	String[] countries = {"United-States", "Cambodia", "England", "Puerto-Rico", "Canada", "Germany", "Outlying-US(Guam-USVI-etc)", "India", "Japan", "Greece", "South", "China", "Cuba", "Iran", "Honduras", "Philippines", "Italy", "Poland", "Jamaica", "Vietnam", "Mexico", "Portugal", "Ireland", "France", "Dominican-Republic", "Laos", "Ecuador", "Taiwan", "Haiti", "Columbia", "Hungary", "Guatemala", "Nicaragua", "Scotland", "Thailand", "Yugoslavia", "El-Salvador", "Trinadad&Tobago", "Peru", "Hong", "Holand-Netherlands"};

	//group divisions
	public int numAgeGroups = 20;
	public int numCapGainsGroups = 20;
	public int numCapLossesGroups = 20;
	public int numHoursWorkedGroups = 20;

    public int[] ageRange = {0, 100};
    public int[] capGainsRange = {0, 10000};
    public int[] capLossesRange = {0, 10000};
    public int[] hoursWorkedRange = {15, 70};


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

	//capital gains capital losses groups
	public float capGain05000;
	public float capGain500010000;
	public float capLoss05000;
	public float capLoss500010000;

	//hours worked groups
	public float hoursWork030;
	public float hoursWork30More;

  	public String moreThan50k = "";
	public ArrayList<ArrayList<Object>> data = new ArrayList<ArrayList<Object>>();

    int lt50k;
    int gt50k;

    private final int L = 444444;         // arbitrarily chosen number
    
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
        } else if (i == 8) {
            return sexList.length;
        } else if (i == 12) {
            return countries.length;
        } else if (i == 0) {
            return numAgeGroups;
        } else if (i == 10) {
            return numCapLossesGroups;
        } else if (i == 11) {
            return numHoursWorkedGroups;
        } else if (i == 9) {
            return numCapGainsGroups;
        } 
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

    private int[] getBounds(int i, int val) {
        int bottom = 0;
        int top = 0;
        double delta = 0;
        int[] ret = new int[2];
        if (i == 0) {
            delta = (ageRange[1] - ageRange[0])/(double)numAgeGroups;
            top = ageRange[1];
            bottom = ageRange[0];
        } else if (i == 10) {
            top = capLossesRange[1];
            bottom = capLossesRange[0];
            delta = (top-bottom)/(double)numCapLossesGroups;
        } else if (i == 11) {
            top = ageRange[1];
            bottom = ageRange[0];
            delta = (top - bottom)/(double)numHoursWorkedGroups;
        } else if (i == 9) {
            delta = (capGainsRange[1] - capGainsRange[0])/(double)numCapGainsGroups;
            top = capGainsRange[1];
            bottom = capGainsRange[0];
        }
        while((int)(bottom + delta) + 1 < top) {
            if (bottom <= val && val <= (int)(bottom+delta)) {
                ret[0] = bottom;
                ret[1] = (int)(bottom+delta);
                return ret;
            } 
            bottom =(int)(bottom+delta) + 1;
        }
        ret[0] = bottom;
        ret[1] = top;
        return ret;

    }

    private double getProb(int index, int val, String Y) {
        List<Object> list = getFeatureList(index, Y);
        int[] bounds = getBounds ( index, val ) ;
        int matches = getNumMatches(bounds[0], bounds[1], list);
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
            if( lower <= (Integer)o  && (Integer)o <= upper)  ++i;
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
            if (((String) data.get(i).get(data.get(i).size()-1)).equalsIgnoreCase(Y) )
                ret.add(data.get(i).get(index));
        
        }
        return ret;
    }

	public void train(String trainingDataFilePath) {
        parseData(trainingDataFilePath, true);
       for (int i = 0 ; i < data.size(); ++i) {
        System.out.println(data.get(i));
       } 
        lt50k = getFeatureList(data.get(0).size()-1, "<=50k").size();
        gt50k = getFeatureList(data.get(0).size()-1, ">50k").size();
    }

    private double getVariance(String[] info, String Y) {
        double var = getY(Y);
            System.out.println(var);
        for(int i = 0; i < info.length; i++) {
            // TODO : make sure that info[i] != -1 and != null
            var *= getProb(i, info[i], Y);
        }
        return var;
    }

	public void makePredictions(String testDataFilePath) {
        try {
            Scanner sc = new Scanner(new File(testDataFilePath));
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.print(line+ " ");
                String[] info = line.split(" ");
                double var_LT50 = getVariance(info, "<=50k");
                double var_GT50 = getVariance(info, ">50k");
                if (var_LT50 > var_GT50) {
                    System.out.println("<=50k");
                } else {
                    System.out.println(">50k");
                }
            }
        } catch (Exception e) {

        }

	}

	public void parseData(String fileName, boolean training) {
		try {
			Scanner sc = new Scanner(new File(fileName));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] info = line.split(" ");
			    ArrayList<Object> person = new ArrayList<Object>();
				for(int i = 0; i < info.length; i++) {
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
				}
					person.add(moreThan50k);
					data.add(person);
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
