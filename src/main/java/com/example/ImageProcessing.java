package com.example;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 * IMPORTANT!!! THE PROJECT'S PIPELINE : 
 * 
 * image file
   ↓
imgToTwoD()
   ↓
trimBorders()
   ↓
negativeColor()
   ↓
twoDToImage()
   ↓
saved image
 */
public class ImageProcessing {
   public static void main(String[] args) {
    try {
        // Load the image from resources (works inside JAR or Docker)
        // IMPORTANT: use getResourceAsStream to read from JAR
        try (var imageStream = ImageProcessing.class.getResourceAsStream("/images/satoru_gojo_jujutsu_kaisen.jpg")) {
            if (imageStream == null) {
                System.err.println("Image not found in resources!");
                return;
            }

            // Read image
            BufferedImage image = ImageIO.read(imageStream);
            if (image == null) {
                System.err.println("Failed to read image from resources!");
                return;
            }

            // Convert BufferedImage to 2D int array
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] imageData = new int[imgRows][imgCols];
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    imageData[i][j] = image.getRGB(j, i);
                }
            }

            // Trim borders
            int[][] trimmed = trimBorders(imageData, 60);

            // Apply negative color filter
            int[][] negativeImage = negativeColor(trimmed);

            // Save the negative image to output folder
            String outputFile = "output/gojo_negative.jpg";
            File outDir = new File("output");
            if (!outDir.exists()) outDir.mkdirs();

            twoDToImage(negativeImage, outputFile);

            System.out.println("Image processing complete! Saved to: " + outputFile);

        } // auto-close InputStream
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    // Image Processing Methods
    public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
        // Example Method
        if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
            int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
            for (int i = 0; i < trimmedImg.length; i++) {
                for (int j = 0; j < trimmedImg[i].length; j++) {
                    trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                }
            }
            return trimmedImg;
        } else {
            System.out.println("Cannot trim that many pixels from the given image.");
            return imageTwoD;
        }
    }

    public static int[][] negativeColor(int[][] imageTwoD) {
        int[][] transformedImage = new int[imageTwoD.length][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[i].length; j++) {
                int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);
                rgba[0] = 255 - rgba[0];
                rgba[1] = 255 - rgba[1];
                rgba[2] = 255 - rgba[2];
                transformedImage[i][j] = getColorIntValFromRGBA(rgba);
            }
        }
        return transformedImage;
    }

    //STRETCHING THE IMAGE BOTH HORIZONTALLY AND VERTICALLY
    public static int[][] stretchHorizontally(int[][] imageTwoD) {
        int[][] modifiedImageData = new int[imageTwoD.length][imageTwoD[0].length * 2];
        int modImPosition = 0;
        for (int k = 0; k < imageTwoD.length; k++) {
            for (int j = 0; j < imageTwoD[0].length; j++) {
                modImPosition = 2 * j;
                modifiedImageData[k][modImPosition] = imageTwoD[k][j];
                modifiedImageData[k][modImPosition + 1] = imageTwoD[k][j];
            }
        }
        return modifiedImageData;
    }

    public static int[][] shrinkVertically(int[][] imageTwoD) {
        int[][] vertShrinked = new int[imageTwoD.length / 2][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD.length; i += 2) {
            for (int j = 0; j < imageTwoD[0].length; j++) {
                vertShrinked[i / 2][j] = imageTwoD[i][j];
            }
        }
        return vertShrinked;
    }

    public static int[][] invertImage(int[][] imageTwoD) {
        int[][] flipped = new int[imageTwoD.length][imageTwoD[0].length];
        for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[0].length; j++) {
                flipped[i][j] = imageTwoD[(imageTwoD.length - 1) - i][(imageTwoD[i].length - 1) - j];
            }
        }
        return flipped;
    }

    /*
	This method modifies every pixel in the image by provided R, G, and B values as input parameters
	. We must make sure that each color value does not leave the range of 0-255. */
    public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
        int[][] colouredImage = new int[imageTwoD.length][imageTwoD[0].length];
		 for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[0].length; j++) {


				/**
				 * 
						For every pixel in the input image, 
						we extract the RGBA color values using the provided method getRGBAFromPixel()
				 */
				int[] rgba = getRGBAFromPixel(imageTwoD[i][j]);

				
			}
		}


		// store the values of each color plus the modifier value (which can be positive or negative).
					int newRed = rgba[0] + redChangeValue;
					int newGreen = rgba[1] + greenChangeValue;
					int newBlue = rgba[2] + blueChangeValue;


					if(newRed<0 )
					{
						newRed = 0;
					}else if (newRed>255)
					{
						newRed=255;
					}


					if (newGreen<0)
					{
						newGreen = 0;
					}else if (newGreen>255)
					{
						newGreen=255;
					}

					if (newBlue<0)
					{
						newBlue = 0;
					}else if (newBlue>255)
					{
						newBlue=255;
					}

					rgba[0] = newRed;
					rgba[1] = newGreen;
					rgba[2] = newBlue;

					int[][] manipulatedImg = new int[imageTwoD.length][imageTwoD[0].length]; 



					 for (int i = 0; i < imageTwoD.length; i++) {
            for (int j = 0; j < imageTwoD[0].length; j++) {
					manipulatedImg[i][j] = getColorIntValFromRGBA(rgba);
			}
		}


        return manipulatedImg;
    }

    public static int[][] paintRandomImage(int[][] canvas) {
        //It will modify the image passed in by replacing every pixel with a randomly colored pixel.
        return null;
    }

    public static int[][] paintRectangle(int[][] canvas, int width, int height, int rowPosition, int colPosition, int color) {
        // TODO: Fill in the code for this method
        return null;
    }

    public static int[][] generateRectangles(int[][] canvas, int numRectangles) {
        // TODO: Fill in the code for this method
        return null;
    }

    // Utility Methods
    public static int[][] imgToTwoD(String inputFileOrLink) {
        try {
            BufferedImage image = ImageIO.read(new File(inputFileOrLink));
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] pixelData = new int[imgRows][imgCols];
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    pixelData[i][j] = image.getRGB(j, i);
                }
            }
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }

    public static void twoDToImage(int[][] imgData, String fileName) {
        try {
            int imgRows = imgData.length;
            int imgCols = imgData[0].length;
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < imgRows; i++) {
                for (int j = 0; j < imgCols; j++) {
                    result.setRGB(j, i, imgData[i][j]);
                }
            }
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }

    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }

    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of elements in RGBA array.");
            return -1;
        }
    }

    public static void viewImageData(int[][] imageTwoD) {
        if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
            int[][] rawPixels = new int[3][3];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rawPixels[i][j] = imageTwoD[i][j];
                }
            }
            System.out.println("Raw pixel data from the top left corner.");
            System.out.print(Arrays.deepToString(rawPixels).replace("],", "],\n") + "\n");
            int[][][] rgbPixels = new int[3][3][4];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    rgbPixels[i][j] = getRGBAFromPixel(imageTwoD[i][j]);
                }
            }
            System.out.println();
            System.out.println("Extracted RGBA pixel data from top the left corner.");
            for (int[][] row : rgbPixels) {
                System.out.print(Arrays.deepToString(row) + System.lineSeparator());
            }
        } else {
            System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
        }
    }
}