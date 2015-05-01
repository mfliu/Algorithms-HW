javac ClassifierMfl4an.java
javac ClassifierOther.java
java ClassifierDriver mfl4an > output.txt
java ClassifierDriver other > other.txt 
diff other.txt output.txt
