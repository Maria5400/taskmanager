# Step 1: Use a tiny version of Java 21 to run our app
FROM eclipse-temurin:21-jre-alpine

# Step 2: Create a folder inside the container to hold our app
WORKDIR /app

# Step 3: Copy the .jar file we just built from our computer into the container
COPY target/taskmanager-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Tell the container to start the Java app when it boots up
ENTRYPOINT ["java", "-jar", "app.jar"]