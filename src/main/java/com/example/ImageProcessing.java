package com.example;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class ImageProcessing {
	public static void main(String[] args) {
    // loading my own image using a URL!

		int[][] imageData = imgToTwoD("C:/Users/amale/Documents/Java Projects/image-processing/src/satoru_gojo_jujutsu_kaisen.jpg/");

    //		//int[][] imageData = imgToTwoD("https://content.codecademy.com/projects/project_thumbnails/phaser/bug-dodger.png");
		//viewImageData(imageData);
		int[][] trimmed = trimBorders(imageData, 60);

		//pasing our image to the negativeColor() method 

		int[][] negativeImage =  negativeColor(trimmed);

		

    /*twoDToImage() accepts a 2D array of integers and a String for the file name.Then it converts the 2D array of int pixel data into an image and saves it.*/
		twoDToImage(negativeImage, "C:/Users/amale/Documents/Java Projects/image-processing/src/gojo_negative.jpg");

		// int[][] allFilters = stretchHorizontally(shrinkVertically(colorFilter(negativeColor(trimBorders(invertImage(imageData), 50)), 200, 20, 40)));
		// Painting with pixels
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

	/**This method will replace the color of each pixel in the image 
	 * with the negative version of the pixel. This means that each color 
	 * component of the pixel (R, G, and B) will be replaced with 255 minus the current value.  */
	public static int[][] negativeColor(int[][] imageTwoD) {
		//creating a new 2d array to store the negative image -> same size as imageTwoD

		int[][] negativeImage = new int[imageTwoD.length ][imageTwoD[0].length] ;
		//3 colours : red, green ,blue ====> int[] rgba = new int[3];
		//int[] rgba = new int[3];

		int[][] transformedImage = new int[imageTwoD.length][imageTwoD[0].length];

		for (int i = 0; i<imageTwoD.length;i++)
		{
			for (int j =0;j<imageTwoD[i].length;j++)
			{
					int[] rgba = getRGBAFromPixel(imageTwoD[i][j]); 
					////3 colours : red, green ,blue
					rgba[0] = 255 - rgba[0];
					rgba[1] = 255 - rgba[1];
					rgba[2] = 255 - rgba[2];

					//gets the int hexadecimal pixel data from the RGBA array
					transformedImage[i][j] = getColorIntValFromRGBA(rgba);
			}
		}
		return transformedImage;
		
	}
	public static int[][] stretchHorizontally(int[][] imageTwoD) {
		// TODO: Fill in the code for this method
		return null;
	}
	public static int[][] shrinkVertically(int[][] imageTwoD) {
		// TODO: Fill in the code for this method
		return null;
	}
	public static int[][] invertImage(int[][] imageTwoD) {
		// TODO: Fill in the code for this method
		return null;
	}
	public static int[][] colorFilter(int[][] imageTwoD, int redChangeValue, int greenChangeValue, int blueChangeValue) {
		// TODO: Fill in the code for this method
		return null;
	}
	// Painting Methods
	public static int[][] paintRandomImage(int[][] canvas) {
		// TODO: Fill in the code for this method
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
			BufferedImage image = null;
			if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
				URL imageUrl = new URL(inputFileOrLink);
				image = ImageIO.read(imageUrl);
				if (image == null) {
					System.out.println("Failed to get image from provided URL.");
				}
			} else {
				image = ImageIO.read(new File(inputFileOrLink));
			}
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

  /*The following method accepts an int value representing the pixel hexadecimal value and returns a 4 element int array consisting of the R, G, B, and A values (between 0 and 255). */
	public static int[] getRGBAFromPixel(int pixelColorValue) {
		Color pixelColor = new Color(pixelColorValue);
		return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
	}
  /*IMPORTANT NOTE :In Java, pixels are stored as hexadecimal values. The previous method is used to extract the color components from the hexadecimal value for the pixel. */
	public static int getColorIntValFromRGBA(int[] colorData) {
		if (colorData.length == 4) {
			Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
			return color.getRGB();
		} else {
			System.out.println("Incorrect number of elements in RGBA array.");
			return -1;
		}
	}

  /*method used for extracting a 3x3 section from the top left of the image. For viewing the structure of the image data in both the raw pixel form and the extracted RGBA form*/
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