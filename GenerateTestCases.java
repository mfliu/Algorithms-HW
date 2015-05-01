import java.util.*;
import java.io.*;

public class GenerateTestCases {

    static Random rand = new Random();
    static String[] workclass = {"Private", "Self-emp-not-inc", "Self-emp-inc", "Federal-gov", "Local-gov", "State-gov", "Without-pay", "Never-worked"};
    static String[] education = {"10th", "Bachelors", "Some-college", "11th", "HS-grad", "Prof-school", "Assoc-acdm", "Assoc-voc", "9th", "7th-8th", "12th", "Masters", "1st-4th", "10th", "Doctorate", "5th-6th", "Preschool"};
    static String[] marital_status = {
        "Married-civ-spouse",
        "Divorced",
        "Never-married",
        "Separated",
        "Widowed",
        "Married-spouse-absent",
        "Married-AF-spouse"};
    static String[] occupation = {
        "Tech-support",
        "Craft-repair",
        "Other-service",
        "Sales",
        "Exec-managerial",
        "Prof-specialty",
        "Handlers-cleaners",
        "Machine-op-inspct",
        "Adm-clerical",
        "Farming-fishing",
        "Transport-moving",
        "Priv-house-serv",
        "Protective-serv",
        "Armed-Forces"};
    static String[] relationship = {
        "Wife",
        "Own-child",
        "Husband",
        "Not-in-family",
        "Other-relative",
        "Unmarried"};
    static String[] race = {
        "White",
        "Asian-Pac-Islander",
        "Amer-Indian-Eskimo",
        "Other",
        "Black"};
    static String[] sex = {"Male", "Female"};
    static String[] native_country = {
        "United-States",
        "Cambodia",
        "England",
        "Puerto-Rico",
        "Canada",
        "Germany",
        "Outlying-US(Guam-USVI-etc)",
        "India",
        "Japan",
        "Greece",
        "South",
        "China",
        "Cuba",
        "Iran",
        "Honduras",
        "Philippines",
        "Italy",
        "Poland",
        "Jamaica",
        "Vietnam",
        "Mexico",
        "Portugal",
        "Ireland",
        "France",
        "Dominican-Republic",
        "Laos",
        "Ecuador",
        "Taiwan",
        "Haiti",
        "Columbia",
        "Hungary",
        "Guatemala",
        "Nicaragua",
        "Scotland",
        "Thailand",
        "Yugoslavia",
        "El-Salvador",
        "Trinadad&Tobago",
        "Peru",
        "Hong",
        "Holand-Netherlands"};

    public static int getAge() {
        return (int) (Math.random() * 50 + 20);
    }

    public static String getWorkclass() {
        int i = rand.nextInt(workclass.length);
        return workclass[i];
    }

    public static String getEducation() {
        int i = rand.nextInt(education.length);
        return education[i];
    }

    public static int getEducationNum(String ed) {
        if (ed.equals("Preschool") ||
                ed.equals("1st-4th") ||
                ed.equals("5th-6th") ||
                ed.equals("7th-8th") ||
                ed.equals("9th"))  return 4;
        if (ed.equals("10th") ||
               ed.equals( "11th") ||
               ed.equals("12th")) return 7;
        if (ed.equals("HS-grad")) return 9;
        if (ed.equals("Bachelors")) return 13;
        if (ed.equals("Some-college")) return 10;
        if (ed.equals("Prof-school")) return 15;
        if (ed.equals("Assoc-acdm") ||
            ed.equals("Assoc-voc")) return 11;
        if (ed.equals("Masters")) return 14;
        if (ed.equals("Doctorate")) return 15;
        return -1;
    }

    public static String getMaritalStatus() {
        return marital_status[rand.nextInt(marital_status.length)];
    }

    public static String getOccupation() {
        return occupation[rand.nextInt(occupation.length)];
    }

    public static String getRelationship() {
        return relationship[rand.nextInt(relationship.length)];
    }

    public static String getRace() {
        return race[rand.nextInt(race.length)];
    }

    public static String getSex() {
        return sex[rand.nextInt(sex.length)];
    }

    public static int getCapGain() {
        return 0;
    }

    public static int getCapLoss() {
        return 0;
    }

    public static int hoursPerWeek() {
        int[] hours = {16, 30, 35, 40, 45, 50, 60, 65, 70};
        return hours[rand.nextInt(hours.length)];
    }

    public static String getNativeCountry() {
        return native_country[rand.nextInt(native_country.length)];
    }

    public static void main(String[] args) throws Exception{
        PrintWriter pw = new PrintWriter("census.test");
        int NUM_CASES = 500;
        for (int i = 0; i < NUM_CASES; ++i) {
            int age = getAge();
            String work = getWorkclass();
            String ed = getEducation();
            int ednum = getEducationNum(ed);
            String marital = getMaritalStatus();
            String occ = getOccupation();
            String rel = getRelationship();
            String ra = getRace();
            String s = getSex();
            int capg = getCapGain();
            int capl = getCapLoss();
            int hrs = hoursPerWeek();
            String nat = getNativeCountry();
            pw.println(
                    age + " " +
                    work + " " +
                    ed + " " + 
                    ednum + " " +
                    marital + " " +
                    occ + " " +
                    rel + " " +
                    ra + " " +
                    s + " " +
                    capg + " " +
                    capl + " " +
                    hrs + " " +
                    nat + " ");
        }
        pw.close();
    }

}
