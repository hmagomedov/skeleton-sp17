import java.lang.Math;

public class Planet {
    double xxPos = 0;
    double yyPos = 0;
    double xxVel = 0;
    double yyVel = 0;
    double mass = 0;
    String imgFileName;

    public Planet(double xP, double yP, double xV, 
    double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx = (xxPos - p.xxPos);
        double dy = (yyPos - p.yyPos);
        return(Math.sqrt(dx*dx + dy*dy));
    }
    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        double G = 6.67 * Math.pow(10, -11);
        return (G * mass * p.mass / (r*r));
    }
    public double calcForceExertedByX(Planet p){
        double dx = p.xxPos - xxPos;
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        return (F * dx / r);
    }
    public double calcForceExertedByY(Planet p){
        double dy = p.yyPos - yyPos;
        double F = calcForceExertedBy(p);
        double r = calcDistance(p);
        return (F * dy / r);
    }
    public double calcNetForceExertedByX(Planet[] planets){
        double netX = 0;
        for(Planet p : planets){
            if(!p.equals(this)){
                netX += calcForceExertedByX(p);
            }
        }
        return netX;
    }
    public double calcNetForceExertedByY(Planet[] planets){
        double netY = 0;
        for(Planet p : planets){
            if(!p.equals(this)){
                netY += calcForceExertedByY(p);
            }
        }
        return netY;
    }
    public void update(double dt, double fX, double fY){
        double xxAcc = fX / mass;
        double yyAcc = fY / mass;
        xxVel += dt * xxAcc;
        yyVel += dt * yyAcc;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }
    public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}
