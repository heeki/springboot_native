include etc/execute_env.sh

mvn.run:
	./mvnw spring-boot:run
mvn.compile:
	# mvn clean
	mvn compile
mvn.package:
	# mvn clean
	mvn package

docker.native.build:
	docker rmi heeki/oci_springboot_native
	docker build -f dockerfile.native -t heeki/oci_springboot_native .
docker.native.run:
	docker run -p 8081:8080 heeki/oci_springboot_native

build-container: package
	