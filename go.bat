@ECHO OFF

javac -d classes\  src\*.java

java -cp classes\; FileCounter %1 %2 %3 %4 %5 %6 %7 %8 %9

ECHO ON