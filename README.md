
Image Processing REST API (Spring Boot)

A lightweight Java Spring Boot REST API for performing image transformations such as filtering, resizing, and procedural image generation.

This project demonstrates backend engineering fundamentals, including REST API design, image manipulation algorithms, and clean architecture.

 Features

 Upload and process images via REST endpoints

Image transformations:
Negative filter
Color adjustment (RGB filters)
Border trimming
Horizontal stretch / vertical shrink
Image inversion
Procedural image generation:
Random pixel canvas
Rectangle-based abstract art generator
 Output processed images to disk
 Modular architecture (Controller → Service → Utility)
 Tech Stack
Java 21
Spring Boot
Maven
BufferedImage (Java AWT) for pixel-level processing

 Project Structure

src/main/java/com/example
│
├── controller/
│   └── ImageController.java       # REST endpoints
│
├── service/
│   └── ImageService.java          # Business logic
│
├── util/
│   └── ImageProcessingUtil.java   # Core image algorithms
│
├── dto/
│   └── FilterRequest.java         # Request payload
│
└── ImageProcessingApplication.java  # Entry point


How It Works

Processing Pipeline
Image → 2D Pixel Array → Transformations → Output Image

Internally:

Convert image → int[][]
Apply transformations (e.g., negative, trim)
Convert back → BufferedImage
Save or return result

API Endpoints

1. Health Check
GET /api/images/ping

Response:

pong

2. Apply Filters to Image
POST /api/images/process

Request:

multipart/form-data
file: image file
filters: list of filters

Example filters:

negative
trim
invert
stretch
shrink

Example (cURL):

curl -X POST http://localhost:8080/api/images/process \
  -F "file=@image.jpg" \
  -F "filters=negative,trim"

 Key Technical Concepts
2D Pixel Manipulation
Images are represented as int[][] arrays
Each pixel encodes RGBA values

Color Transformation

newRed = 255 - red;
Bounds-safe Image Operations
Prevents overflow/underflow in RGB values
Handles edge cases for resizing and trimming
Stateless REST API Design
Each request is independent
No server-side session required

How to Run

1. Build the project
mvn clean install
2. Run the application
mvn spring-boot:run
3. Access API
http://localhost:8080

Potential Improvements
Return processed images directly (instead of saving to disk)
Add async processing for large images
Add unit + integration tests (JUnit)
- Deploy container to cloud (e.g., AWS, GCP, or Kubernetes)
Add authentication (Spring Security)
Support more filters (blur, grayscale, edge detection)






 Author

Amanda Alexopoulou 
Junior Computer Engineer

