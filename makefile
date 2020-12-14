include etc/execute_env.sh

run:
	./mvnw spring-boot:run

compile:
	# mvn clean
	mvn compile

package:
	# mvn clean
	mvn package
