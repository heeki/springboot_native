include etc/execute_env.sh

run:
	./mvnw spring-boot:run

compile:
	mvn compile

package:
	mvn clean package
