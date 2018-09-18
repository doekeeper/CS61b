// Drawing the background
// https://sp18.datastructur.es/materials/proj/proj0/proj0



/** NBody is a class that actually run the simulation
 * It will simulate a universe specified in one of the data files
 */
public class NBody{
	/** to return radius of the corresponding radius of the universe
	 * In class is used here to read file
	 */
	public static double readRadius(String fileName){
		In in = new In(fileName);
		int n = in.readInt();	// numbers of planets in the universe
		double r = in.readDouble();	// radius of the universe
		return r;
	}

	/** return an array of Planets in a file given a file name
	 */
	public static Planet[] readPlanets(String fileName){
		In in = new In(fileName);
		int n = in.readInt();	// numbers of planets in the universe
		double r = in.readDouble();	// radius of the universe
		Planet[] p_Array = new Planet[n];
		for (int i=0; i<n;i++){
				p_Array[i] = new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
				}
		return p_Array;
	}

	/**
	 * main method
	 */
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];					// 3rd argument is file name
		double r = readRadius(fileName);
		Planet[] p_Arr = readPlanets(fileName);

		// draw the universe and planets
		// set scale for x and y coordinate
		StdDraw.setScale(r*(-1), r);
		StdDraw.clear();		
		// set background picture
		StdDraw.picture(0, 0,"./images/starfield.jpg");

		// draw all of the planets
		for(Planet p:p_Arr){
			p.draw();
		}

		/** create an animation
		 */
		StdDraw.enableDoubleBuffering();		// double buffering

		double t = 0.0;	
		while(t<T){
			double[] xForces = new double[p_Arr.length];
			double[] yForces = new double[p_Arr.length];
			for(int i=0;i<p_Arr.length;i++){
				xForces[i] = p_Arr[i].calcNetForceExertedByX(p_Arr);
				yForces[i] = p_Arr[i].calcNetForceExertedByY(p_Arr);
			}
			for(int i=0;i<p_Arr.length;i++){
				p_Arr[i].update(dt, xForces[i], yForces[i]);
			}
			// draw background image
			StdDraw.picture(0, 0,"./images/starfield.jpg");
			// draw all of the planets
			for(int i=0;i<p_Arr.length;i++){
				p_Arr[i].draw();
			}
			// show offsreen on on-screen canvas
			StdDraw.show();
			// puase the animation for 10ms
			StdDraw.pause(10);
			t += dt;
		}
		StdOut.printf("%d\n", p_Arr.length);
		StdOut.printf("%.2e\n", r);
		for (int i = 0; i < p_Arr.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
				p_Arr[i].xxPos, p_Arr[i].yyPos, p_Arr[i].xxVel,
                p_Arr[i].yyVel, p_Arr[i].mass, p_Arr[i].imgFileName);
		}
	}
}