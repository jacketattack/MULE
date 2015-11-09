default: build run

install:
	mvn clean install

build:
	mvn clean package

run:
	java -cp target/MULE-*-SNAPSHOT.jar GourmetSnacks.App
