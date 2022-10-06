# Frequency

Compiled an run with Java 14+.  If you want to use an earlier version of Java (8 or 11), then change the instanceof
operation in the Frequency::compareTo to the older instance of operation:

if(o instanceof Frequency) {
	result = ((Frequency)o).count - count;
}

COMPILE the source with:

javac -d build src/main/java/sandbox/Main.java

RUN

java -cp build sandbox.Main "the blue cow jumped over the blue moon"

alternatively, you can create a text file and run it with:

java -cp build sandbox.Main -f test.txt
