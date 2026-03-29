Image Processing Application (Java + Maven + Docker)
Overview

This project is a Java-based image processing application built with Maven and packaged using Docker. It performs a series of transformations on an input image, including trimming borders, applying filters, and drawing shapes.

The application demonstrates:

Core Java programming (OOP, arrays, loops)
Basic image processing techniques
Maven project structure
Docker containerization
⚙️ Processing Pipeline

The application follows this pipeline:

image file
   ↓
imgToTwoD()
   ↓
trimBorders()
   ↓
negativeColor()
   ↓
paintRectangle()
   ↓
twoDToImage()
   ↓
saved image


Features
 Trim Borders – Removes pixels from image edges
 Negative Filter – Inverts RGB color values
 Draw Rectangle – Paints a colored rectangle on the image
 Random Image Generator – Generates random pixel images
 Geometric Art Generator – Creates abstract art using random rectangles
 
 Project Structure
image-processing/
├── src/
│   └── main/
│       ├── java/com/example/ImageProcessing.java
│       └── resources/images/
│           └── satoru_gojo_jujutsu_kaisen.jpg
├── output/
├── pom.xml
├── Dockerfile

 How to Run (Maven)
1. Compile the project
mvn clean compile
2. Package into JAR
mvn package
3. Run the application
java -jar target/image-processing-1.0-SNAPSHOT.jar

 Run with Docker
1. Build Docker image
docker build -t image-processing-app .
2. Run container
docker run --rm -v ${PWD}/output:/app/output image-processing-app

The processed images will be saved in the output/ folder.
