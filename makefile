include etc/environment.sh

mvn.run:
	./mvnw spring-boot:run
mvn.clean:
	mvn clean
mvn.compile:
	mvn compile
mvn.package:
	mvn package

docker: docker.build docker.login docker.tag docker.push
docker.build:
	docker build -f dockerfile.native -t ${CIMAGE}:${CVERSION} .
docker.login:
	aws ecr get-login-password --region ${REGION} | docker login --username AWS --password-stdin ${ACCOUNTID}.dkr.ecr.${REGION}.amazonaws.com
docker.tag:
	docker tag ${CIMAGE}:${CVERSION} ${ACCOUNTID}.dkr.ecr.${REGION}.amazonaws.com/${CIMAGE}:${CVERSION}
docker.push:
	docker push ${ACCOUNTID}.dkr.ecr.${REGION}.amazonaws.com/${CIMAGE}:${CVERSION}

docker.native.build:
	docker rmi heeki/springboot_native
docker.native.build:
	docker build -f dockerfile.native -t ${CIMAGE}:${CVERSION} .
docker.native.run:
	docker run -p 8081:8080 --env-file etc/environment.docker ${CIMAGE}:${CVERSION}

cert: cert.package cert.deploy
cert.package:
	sam package -t ${CERT_TEMPLATE} --output-template-file ${CERT_OUTPUT} --s3-bucket ${S3BUCKET}
cert.deploy:
	sam deploy -t ${CERT_OUTPUT} --stack-name ${CERT_STACK} --parameter-overrides ${CERT_PARAMS} --capabilities CAPABILITY_NAMED_IAM

sg: sg.package sg.deploy
sg.package:
	sam package -t ${SG_TEMPLATE} --output-template-file ${SG_OUTPUT} --s3-bucket ${S3BUCKET}
sg.deploy:
	sam deploy -t ${SG_OUTPUT} --stack-name ${SG_STACK} --parameter-overrides ${SG_PARAMS} --capabilities CAPABILITY_NAMED_IAM

lb: lb.package lb.deploy
lb.package:
	sam package -t ${LB_TEMPLATE} --output-template-file ${LB_OUTPUT} --s3-bucket ${S3BUCKET}
lb.deploy:
	sam deploy -t ${LB_OUTPUT} --stack-name ${LB_STACK} --parameter-overrides ${LB_PARAMS} --capabilities CAPABILITY_NAMED_IAM

ecs: ecs.package ecs.deploy
ecs.package:
	sam package -t ${ECS_TEMPLATE} --output-template-file ${ECS_OUTPUT} --s3-bucket ${S3BUCKET}
ecs.deploy:
	sam deploy -t ${ECS_OUTPUT} --stack-name ${ECS_STACK} --parameter-overrides ${ECS_PARAMS} --capabilities CAPABILITY_NAMED_IAM

api: api.package api.deploy
api.package:
	sam package -t ${API_TEMPLATE} --output-template-file ${API_OUTPUT} --s3-bucket ${S3BUCKET}
api.deploy:
	sam deploy -t ${API_OUTPUT} --stack-name ${API_STACK} --parameter-overrides ${API_PARAMS} --capabilities CAPABILITY_NAMED_IAM
