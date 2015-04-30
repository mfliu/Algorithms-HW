import java.lang.Object;
import java.util.*;
import java.io.*;

public class ClassifierDriver {
	public static void main(String[] args) {
		ClassifierMfl4an moo = new ClassifierMfl4an("census.train");
        moo.train("census.train");
        moo.makePredictions("census.test");
    }
}
