/** 
 * plot all points provided in the provided txt
 */

public class PlotFilter{
	public static void main(String[] args){
		// read in bouding box and rescale
		In in = new In("./data/USA.txt");
		double x0 = in.readDouble();
		double y0 = in.readDouble();
		double x1 = in.readDouble();
		double y1 = in.readDouble();
		System.out.println(x0 +" " + y0 + " " + x1 + " "+ y1);

		StdDraw.setXscale(x0, x1);
		StdDraw.setYscale(y0, y1);

		// for bigger points
		StdDraw.setPenRadius(0.05);

		// to speed up performance, defer displaying points
		StdDraw.enableDoubleBuffering();

		// plot points, one at a time
		while(!in.isEmpty()){
			double x = in.readDouble();
			double y = in.readDouble();
			StdDraw.point(x, y);
		}
		StdDraw.show();
	}
}