import java.awt.Graphics;
import java.awt.Color;

/* A program to test if I like how to colors in my Selvedge Scarf Plan
 * look a way that I like. */

 public class Selvedge {

    // Select RGB for intended yarn colors.
    public static final Color YARN_A = new Color(209, 206, 202);
    public static final Color YARN_B = new Color(130, 130, 136);
    public static final Color YARN_C = new Color(209, 200, 187);
    public static final Color YARN_D = new Color(172, 124, 99);
    public static final Color YARN_E = new Color(202, 165, 122);
    public static final Color YARN_F = new Color(158, 156, 158);

    public static final int WIDTH = 864;
    public static final int HEIGHT = 384;
    public static final int STITCH_WIDTH = 10;
    public static final int STITCH_HEIGHT = 32;

    public static void main(String args[]) {
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
		Graphics gr = dp.getGraphics();

        // Draw rows 1-160 three times as called in pattern.
        for (int i = 0; i < 3; i++) {
            int startX = i * 32 * 8;
            int k = 0;
            for (int j = 0; j < 256; j += 32) {
                Color colorB = colorSequenceB(k);
                drawRow(colorB, startX + j, gr);
                k++;
            }
        }

        // Draw rows 1-60 once more.
        int startX = 768;
        int k = 0;
        for (int j = 0; j < 128; j += 32) {
            Color colorB = colorSequenceB(k);
            drawRow(colorB, startX + j, gr);
            k++;
        }

        /*saveDrawingPanel(dp, "selvedge");*/
    }

    // Method to draw each square on the scarf of a given color combination.
    // colorA: Yarn color at the first row of this square.
    // colorB: Yarn color at the second row of this square.
    // startX and startY: Where the square will be drawn on the scarf.
    public static void drawSquare(Color colorA, Color colorB, int startX, int startY, Graphics gr) {
        // A loop that draws each row of the square. First it draws the colorA row, then the colorB
        // row. Repeats 8 times to make a total of 16 rows.
        for (int i = startX; i < startX + 32; i += 2) {
            gr.setColor(colorA);
            gr.fillRect(i, startY, STITCH_WIDTH, STITCH_HEIGHT);
            i += 2;
            gr.setColor(colorB);
            gr.fillRect(i, startY, STITCH_WIDTH, STITCH_HEIGHT);
        }
    }

    // Method to draw each row of the scarf. It will create 12 rows of 32 pixel length boxes.
    // colorB: The yarn color that will be consistent throughout this whole row. Corresponds
    //         to colorB of drawSquare.
    // startX: X position that will be consistent for this row.
    public static void drawRow (Color colorB, int startX, Graphics gr) {
        int j = 0;
        for (int startY = 0; startY < 384; startY += 32) {
            Color colorA = colorSequenceA(j);
            drawSquare(colorA, colorB, startX, startY, gr);
            j++;
        }
    }

    // Method to return the proper colorA while working through a row. The order of colors is as
    // prescribed in Wilkens's Selvedge pattern.
    public static Color colorSequenceA (int i) {
        if (i == 0 || i == 2 || i == 9 || i == 11) {
            return YARN_A;
        } else if (i == 1 || i == 5 || i == 10) {
            return YARN_B;
        } else if (i == 3 || i == 8) {
            return YARN_C;
        } else if (i == 4 || i == 6) {
            return YARN_D;
        } else {
            return YARN_E;
        }
    }

    // Same as above but for the columns.
    public static Color colorSequenceB (int i) {
        if (i == 0 || i == 4) {
            return YARN_F;
        } else if (i == 1) {
            return YARN_D;
        } else if (i == 2) {
            return YARN_C;
        } else if (i == 3 || i == 7) {
            return YARN_E;
        } else if (i == 5) {
            return YARN_A;
        } else {
            return YARN_B;
        }
    }
}