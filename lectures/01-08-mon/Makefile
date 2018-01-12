%.class: %.java
	javac -cp hamcrest-core-1.3.jar:junit-4.12.jar:. MyTest.java

%: %.class
	java -cp hamcrest-core-1.3.jar:junit-4.12.jar:. org.junit.runner.JUnitCore $@
