/** 
 * Draw a blue filled circle whereever the mouse is, in cyan if the mouse is pressed.
 */

public class MouseFollower{
	public static void main(String[] args){
		StdDraw.enableDoubleBuffering();

		while(true){
			// mouse click
			if(StdDraw.mousePressed()) StdDraw.setPenColor(StdDraw.CYAN);
			else                       StdDraw.setPenColor(StdDraw.BLUE);

			// mouse location
			// StdDraw.clear();
			double x = StdDraw.mouseX();
			double y = StdDraw.mouseY();
			StdDraw.filledCircle(x, y, 0.01);
			StdDraw.show();
			StdDraw.pause(10);
		}
	}
}