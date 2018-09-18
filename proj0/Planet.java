/** class Planet */

public class Planet{
	/** planet's x and y position */
	public double xxPos;
	public double yyPos;
	/** planet's veolcity along x and y direction */
	public double xxVel;
	public double yyVel;
	/** planet's mass */
	public double mass;
	/** file name of the image that depicts the planet */
	public String imgFileName;
	/** gratitional constant*/
	static final double G = 6.67e-11;

	/** parameterized constructor */
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}
	public Planet(Planet p){
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}
	/**calculate distance to Planet p
	 *	@param p planet of interest
	*/
	public double calcDistance(Planet p){
		double d;
		d = Math.sqrt(Math.pow((this.xxPos-p.xxPos),2)+Math.pow((this.yyPos-p.yyPos),2));
		return d;
	}
	/** 
	 *	calculate force exerted by Planet p
	 *	@param 	p     	planet of interest
	 */
	 public double calcForceExertedBy(Planet p){
	 	double f = G*this.mass*p.mass/Math.pow(this.calcDistance(p),2);
	 	return f;
	 }
	 /**
	  * calculate force exerted along x-direction
	  */
	 public double calcForceExertedByX(Planet p){
	 	double dx = p.xxPos - this.xxPos;	// distance along x-direction
	 	double dy = p.yyPos - this.yyPos;	// distance along y-direction
	 	double r = Math.sqrt(dx*dx+dy*dy);	// distance between two planets
	 	double fx = calcForceExertedBy(p)*dx/r;
	 	return fx;
	 }
	 public double calcForceExertedByY(Planet p){
	 	double dx = p.xxPos - this.xxPos;	// distance along x-direction
	 	double dy = p.yyPos - this.yyPos;	// distance along y-direction
	 	double r = Math.sqrt(dx*dx+dy*dy);	// distance between two planets
	 	double fy = calcForceExertedBy(p)*dy/r;
	 	return fy;
	 }
	 public double calcNetForceExertedByX(Planet[] allPlanet){
	 	double f_net_x = 0;
	 	for(Planet p: allPlanet){
	 		if (this.equals(p)){
	 			continue;
	 		} else {
	 			f_net_x+=this.calcForceExertedByX(p);
	 		}

	 	}
	 	return f_net_x;
	 }
	 public double calcNetForceExertedByY(Planet[] allPlanet){
	 	double f_net_y = 0;
	 	for(Planet p: allPlanet){
	 		if (this.equals(p)){
	 			continue;
	 		} else {
	 			f_net_y+=this.calcForceExertedByY(p);
	 		}

	 	}
	 	return f_net_y;
	 }
	 /**
	  * method 'update' is to determine how much the forces exerted
	  * on the planet will cause that planet to accelerate, and the
	  * resulting change in the planet's velocity and position in a 
	  * a small period of time dt.
	  * @param dt time interval
	  * @param fX net force exerted along x-direction
	  * @param fY net force exerted along y-direction
	  */
	 public void update(double dt, double fX, double fY){
	 	double aX = fX/this.mass;
	 	double aY = fY/this.mass;
	 	this.xxVel +=dt*aX;
	 	this.yyVel +=dt*aY;
	 	this.xxPos +=dt*this.xxVel;
	 	this.yyPos +=dt*this.yyVel;
	 }
	 // draw the planet
	 public void draw(){
	 	StdDraw.picture(this.xxPos, this.yyPos, imgFileName);
	 }
}
