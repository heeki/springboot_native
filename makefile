include etc/execute_env.sh

mvn.run:
	./mvnw spring-boot:run
mvn.clean:
	mvn clean
mvn.compile:
	mvn compile
mvn.package:
	mvn package

docker: docker.native.clean docker.native.build docker.native.run
docker.native.clean:
	docker rmi heeki/oci_springboot_native
docker.native.build:
	docker build -f dockerfile.native -t heeki/oci_springboot_native .
docker.native.run:
	docker run -p 8081:8080 heeki/oci_springboot_native
