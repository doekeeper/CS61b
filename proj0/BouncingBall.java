/** 
 * Implementation of 2-d bouncing ball in the box from (-1, -1) to (1, 1)
 */

public class BouncingBall{
	public static void main(String[] args){
		// set the scale of the coordinate system
		StdDraw.setXscale(-10.0, 10.0);
		StdDraw.setYscale(-10.0, 10.0);
		StdDraw.enableDoubleBuffering();

		// initial values
		double rx = 0.48, ry = 0.86;	// position
		double vx = 0.015, vy = 0.023;	// velocity
		double radius = 0.05;			// radius

		// main animation loop
		while(true){

			// bounce off wall according to low of elastic collision
			if (Math.abs(rx+vx)>1.0 - radius){
				vx = -vx;
			}
			if (Math.abs(ry+vy)>1.0 - radius){
				vy = -vy;
			}

			// update position
			rx = rx + vx;
			ry = ry + vy;

			// clear the background
			StdDraw.clear(StdDraw.GREEN);

			// draw ball on the screen
			StdDraw.picture(rx, ry, "./images/StdDraw/TennisBall.gif");

			// copy offscreen buffer to onscreen
			StdDraw.show();

			// pause for 20 ms
			StdDraw.pause(20);
		}
	}
}