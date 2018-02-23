
import objectdraw.*;
import java.awt.*;
import java.util.Random;

/**
 *
 *  Lab 9 (part 2) - Blur
 *  Erika Sklaver 
 *  05/06/15
 *
 *  A large square will appear on the screen with a black border. Inside the border are randomly colored squares. 
 *  When the user drags the mouse within the large square, the squares become blurrier until the entire square
 *  is completely black. 
 *  
 */
public class Blur  {
    //the size of the squares
    private static final int UPPER_LEFT = 20; 
    private static final int SQUARE_SIZE = 8; 
    
    //allows the squares to be random colors
    private static final int NUM_COLOR_LEVELS = 256; 
    private Random generator = new Random(); 
    
    //the 2 dimensional array: an array of an array of squares
    private FilledRect[][] squares; 

    public Blur(int rows, int cols, DrawingCanvas canvas) {
        //creates the array 
        squares = new FilledRect[rows][cols];
        
        //the y coordinate changes for each row 
        int yCoord = UPPER_LEFT; 
        
        //creates the randomly colored squares 
        for (int r = 0; r < squares.length; r++) {
            
            //the x coordinate changes for each column
            int xCoord = UPPER_LEFT; 
            for (int c = 0; c < squares[r].length; c++){
                
                squares[r][c] = new FilledRect (xCoord, yCoord, SQUARE_SIZE,SQUARE_SIZE,canvas); 
                
                if (r > 0 && c > 0 && r < squares.length-1 && c < squares[r].length -1){
                  squares[r][c].setColor(new Color(generator.nextInt(NUM_COLOR_LEVELS), 
                                                 generator.nextInt(NUM_COLOR_LEVELS),
                                                 generator.nextInt(NUM_COLOR_LEVELS))); 
                }
                //changes the x coordinate for the next column                                 
                xCoord = xCoord + SQUARE_SIZE; 
            }
            //changes the y coordinate for the next row 
            yCoord = yCoord + SQUARE_SIZE; 
            
        }

    }
    
    //Takes the north, south, east and west square and averages their color to give that color to the square 
    //in the middle which will make then entire large square blurrier overall 
    public void makeBlurrier() {
        //goes through each row 
        for (int r = 1; r<squares.length-1; r++){
            //goes through each column 
            for (int c = 1; c<squares[r].length-1; c++){
                //finds the color of the northern square 
                Color north = squares[r-1][c].getColor(); 
                int northRed = north.getRed();
                int northGreen = north.getGreen(); 
                int northBlue = north.getBlue();
                
                //finds the color of the southern square 
                Color south = squares[r+1][c].getColor(); 
                int southRed = south.getRed();
                int southGreen = south.getGreen(); 
                int southBlue = south.getBlue();
               
                //finds the color of the eastern square 
                Color east = squares[r][c+1].getColor(); 
                int eastRed = east.getRed();
                int eastGreen = east.getGreen(); 
                int eastBlue = east.getBlue();
                
                //finds the color of the southern square 
                Color west = squares[r][c-1].getColor(); 
                int westRed = west.getRed();
                int westGreen = west.getGreen(); 
                int westBlue = west.getBlue();
                
                //finds the average of the previous squares red, green and blue values 
                int redAvg = (northRed + southRed + eastRed + westRed)/4; 
                int greenAvg = (northGreen + southGreen + eastGreen + westGreen)/4; 
                int blueAvg = (northBlue + southBlue + eastBlue + westBlue)/4; 
                
                //creates a new color with those averages 
                Color colorAvg = new Color(redAvg, greenAvg, blueAvg); 
                
                //changes that square to the new color 
                squares[r][c].setColor(colorAvg); 
                
            }
            
        }
    }

}
