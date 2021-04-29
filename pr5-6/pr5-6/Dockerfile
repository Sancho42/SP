FROM ubuntu
RUN apt-get install openjdk-11-jdk-headless
COPY . .
RUN javac -d . Main.java
RUN jar cvmf MANIFEST.MF main.jar com/company/Main.class com/company/MyThread.class com/company/Sphere.class 
CMD java -jar main.jar
