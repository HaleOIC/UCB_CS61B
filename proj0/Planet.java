public class Planet {
	/**
	 * 	@double xxPos: Its current x position
	 * 	@double yyPos: Its current y position
	 * 	@double xxVel: Its current velocity in the x direction
	 * 	@double yyVel: Its current velocity in the y direction
	 * 	@double mass: Its mass
	 * 	@String imgFileName: The name of the file that
	 *  	corresponds to the image that depicts the planet 
	 * 		(for example, jupiter.gif)
	 */
	public double xxPos, yyPos, xxVel, yyVel, mass;
	public String imgFileName;

	/**the constant variable for calculate */
	private static final double G = 6.67 * 1e-11;

	/** the constructor for the class planet */
	public Planet(double xP, double yP, double xV,
					double yV, double m, String img) {
		this.xxPos = xP;
		this.yyPos = yP;
		this.xxVel = xV;
		this.yyVel = yV;
		this.mass = m;
		this.imgFileName = img;
	}

	/** the copy constructor fo the class planet.*/
	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	/** calculate the distance between two planets.*/
	public double calcDistance(Planet other) {
		double dx = this.xxPos - other.xxPos;
		double dy = this.yyPos - other.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	/** calculate the force exerted by the other planet.*/
	public double calcForceExertedBy(Planet other) {
		double dis = this.calcDistance(other);
		return G * this.mass * other.mass / (dis * dis);
	}

	/** calculate the force exerted by the other planet on the x-axis */
	public double calcForceExertedByX(Planet other) {
		double dx = other.xxPos - this.xxPos;
		double dis = this.calcDistance(other);
		return this.calcForceExertedBy(other) * dx / dis;
	}

	/** calculate the force exerted by the other planet on the y-axis */
	public double calcForceExertedByY(Planet other) {
		double dy = other.yyPos - this.yyPos;
		double dis = this.calcDistance(other);
		return this.calcForceExertedBy(other) * dy / dis;
	}

	/** calculate the net force exerted by other planets on the X-axis*/
	public double calcNetForceExertedByX(Planet[] array) {
		double totForceX = 0.0;
		for (Planet each : array){
			if (this.equals(each)) {
				continue;
			}
			totForceX += this.calcForceExertedByX(each);
		}
		return totForceX;
	}

	/** calculate the net force exerted by other planets on the Y-axis*/
	public double calcNetForceExertedByY(Planet[] array) {
		double totForceY = 0.0;
		for (Planet each : array){
			if (this.equals(each)) {
				continue;
			}
			totForceY += this.calcForceExertedByY(each);
		}
		return totForceY;
	}

	/** update the velocity of the planet */
	public void update(double dt, double forceX, double forceY) {
		/**
		 * @dt: the time for the force last.
		 * @forceX: the force put on the X-axis
		 * @forceY: the force put on the Y-axis.
		 */
		double aX = forceX / this.mass, aY = forceY / this.mass;
		this.xxVel = this.xxVel + dt * aX;
		this.yyVel = this.yyVel + dt * aY;
		this.xxPos = this.xxPos + dt * this.xxVel;
		this.yyPos = this.yyPos + dt * this.yyVel;
	}

	/** draw the planet on the screen*/
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
	}

}