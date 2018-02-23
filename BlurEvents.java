
import objectdraw.*;
import java.awt.*;

/**
 * Program to test the Blur class.
 * 
 * DON'T CHANGE THIS CLASS!
 */
public class BlurEvents extends FrameWindowController {

    // size of the window
    private static final int WINDOW_SIZE = 1000;

    // reduction in delay between blurs
    private static final int PAUSE_DECREMENT = 50;

    // size of the blur grid
    private static final int NUM_ROWS = 80;
    private static final int NUM_COLS = 100;

    // how long to pause before the next blur
    private int nextPauseTime = 400;

    // the blurring picture
    private Blur myBlur;

    // create the Blur object
    public void begin() {
        resize(WINDOW_SIZE, WINDOW_SIZE);
        myBlur = new Blur(NUM_ROWS, NUM_COLS, canvas);
    }

    // make blurrier as mouse is dragged;
    // start with longer pauses, then decrease
    public void onMouseDrag(Location point) {

        myBlur.makeBlurrier();

        try {
            // pause for pauseTime ms.
            // This is similar to pause() but outside of an ActiveObject
            Thread.sleep(nextPauseTime);
        } catch (InterruptedException e) {}

        if (nextPauseTime >= PAUSE_DECREMENT) {
            nextPauseTime -= PAUSE_DECREMENT;
        }

    } 

}
