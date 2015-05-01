import java.lang.Object;
import java.util.*;
import java.io.*;

public class ClassifierDriver {
	public static void main(String[] args) {
        if (args[0].equals("mfl4an")) {
		ClassifierMfl4an moo = new ClassifierMfl4an("census.train");
        moo.train("census.train");
        moo.makePredictions("census.test");
        } else {
		ClassifierOther mooshu = new ClassifierOther("census.train");
        mooshu.train("census.train");
        mooshu.makePredictions("census.test");
        }
        }
}
