%.class: %.java
	javac -cp ../lib/hamcrest-core-1.3.jar:../lib/junit-4.12.jar:. $<

%: %.class
	java -cp ../lib/hamcrest-core-1.3.jar:../lib/junit-4.12.jar:. org.junit.runner.JUnitCore $@
