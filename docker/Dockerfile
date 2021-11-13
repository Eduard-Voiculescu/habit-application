FROM maven:3.6-jdk-11
MAINTAINER Eduard Voiculescu <eduard.voiculescu1@gmail.com>

# Create new user
RUN useradd -ms /bin/bash springboot

# Set as new user
USER springboot

# Set working directory
WORKDIR /home/springboot/app

# Copy src folder to /app/src/
COPY src ./src

# Copy pom file to /app/.pom.xml
COPY ../pom.xml .

# Resolve maven dependencies
RUN mvn clean package > /dev/null

# Copy jar
COPY ../target/habit-0.0.1-SNAPSHOT.jar /home/springboot/app/habit-0.0.1-SNAPSHOT.jar

# Copy start up shell script
COPY docker/start.sh .

# Run the app
CMD ["sh", "start.sh"]
