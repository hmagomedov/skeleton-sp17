public class NBody {
    public static double readRadius(String fileName){
        In in = new In(fileName);
        int num = in.readInt();
        return(in.readDouble());
    }
    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int nums = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[nums];
        int i = 0;
        while(i < 5){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String name = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
            i++;
        }
        return planets;
    }
	public static void main(String[] args){

		double T =  Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet [] planets = readPlanets(filename);

		String imgloc = "images/";
		String bgdimg = "starfield.jpg";

		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, imgloc + bgdimg, 2 * radius, 2 * radius);
		StdAudio.play("audio/2001.mid");

		for (Planet p : planets){
			p.draw();
		}

		for (double t = 0; t < T; t += dt){
			double [] xForces;
			double [] yForces;
			xForces = new double[planets.length];
			yForces = new double[planets.length];
			for (int n = 0; n < planets.length; n++){
				xForces[n] = planets[n].calcNetForceExertedByX(planets);
				yForces[n] = planets[n].calcNetForceExertedByY(planets);				
			}			

			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0, 0, imgloc + bgdimg, 2 * radius, 2 * radius);

			for (int n = 0; n < planets.length; n++){
				planets[n].update(dt, xForces[n], yForces[n]);
				planets[n].draw();
			}

			StdDraw.show(10);
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (Planet p : planets){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
		}
	}
}
