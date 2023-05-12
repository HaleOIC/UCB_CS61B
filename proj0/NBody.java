/**
 * NBody is a class that will actually run your simulation. 
 * This class will have NO constructor. 
 * The goal of this class is to simulate a universe specified in 
 * one of the data files.
 */
public class NBody {
	/** read the file and return the radius */
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	/** read the Planets frome the fileName*/
	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int N = in.readInt();
		double radius = in.readDouble();
		Planet[] rv = new Planet[N];
		for (int i = 0; i < N; i = i + 1) {
			Planet newOne = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
										in.readDouble(), in.readDouble(), in.readString());
			rv[i] = newOne;
		}
		return rv;
	}

	/** the main function for draw the initial univser state*/
	public static void main(String args[]) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		Planet[] planets = NBody.readPlanets(filename);
		
		create_animation(T, dt, radius, planets);
		print_universe(planets, radius);
	}

	/** create the animation of the plot, it is really amazing.*/
	private static void create_animation(double T, double dt, double r, Planet[] planets) {
		StdDraw.setXscale(-r, r);
		StdDraw.setYscale(-r, r);
		StdDraw.enableDoubleBuffering();

		double t = 0;
		int num = planets.length;
		while (t < T){
			double[] xForces = new double[num];
			double[] yForces = new double[num];

			for (int i = 0; i < num; i = i + 1) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0, 0, "./images/starfield.jpg");
			for (int i = 0; i < num; i = i + 1){
				planets[i].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

			t = t + dt;
		}
	}

	/** print the universe */
	private static void print_universe(Planet[] planets, double radius) {
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  	planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}