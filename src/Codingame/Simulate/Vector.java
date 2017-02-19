package Codingame.Simulate;

/**
 * Created by Owner on 2/18/2017.
 */
public class Vector {
    public double magnitudeX;
    public double magnitudeY;
    public double magnitude;

    public Vector(double magnitudeX, double magnitudeY) {
        this.magnitudeX = magnitudeX;
        this.magnitudeY = magnitudeY;
        this.magnitude = Math.sqrt(magnitudeX * magnitudeX + magnitudeY * magnitudeY);
    }

    public Vector add(Vector v) {
        double newX = this.magnitudeX + v.magnitudeX;
        double newY = this.magnitudeY + v.magnitudeY;
        return new Vector(newX, newY);
    }

    public Vector sub(Vector v) {
        double newX = this.magnitudeX - v.magnitudeX;
        double newY = this.magnitudeY - v.magnitudeY;
        return new Vector(newX, newY);
    }

    public Vector scalar(double k) {
        double newX = this.magnitudeX * k;
        double newY = this.magnitudeY * k;
        return new Vector(newX, newY);
    }

    public double dotProduct(Vector v) {
        return this.magnitudeX * v.magnitudeX + this.magnitudeY * v.magnitudeY;
    }

    public double findThetaToVector(Vector v) {
        return dotProduct(v) / (this.magnitude * v.magnitude);
    };
}