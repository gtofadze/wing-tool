package wing;

public class WingMeshBuilder {
    int nacaNumber;
    int numberOfPoints;
    double[] distances;
    double[] chords;

    public WingMeshBuilder(int nacaNumber, int numberOfPoints, double[] distances, double[] chords) {
        this.nacaNumber = nacaNumber;
        this.numberOfPoints = numberOfPoints;
        this.distances = chords;
        this.chords = chords;
    }

    public double[] generateSection(int nacaNumber, double start, double chord, int numberOfPoints, double distance) {

        //Parsing the NACA 4-Digit Code (MPXX)
        int XX = nacaNumber % 100; //last two digits
        int remaining = nacaNumber / 100;
        int P = remaining % 10; //second digit
        int M = remaining / 10; //first digit

        //Converting percentages to standard aerodynamic ratios
        double m = M / 100.0;
        double p = P /10.0;
        double t = XX / 100.0;

        // generating empty array of necessary length for points
        // [x1, y1, z1, .... xn, yn, zn]
        double[] points = new double[numberOfPoints * 2 * 3];

        // generating cosine distribution
        double[] interval = new double[numberOfPoints];
        double pi = 3.14159265359;

        for (int i = 0; i < numberOfPoints; i++) {
            double x = ( (start + chord) / 2 )
                     + ( (chord - start) / 2 )
                     * Math.cos( (i * pi)/(numberOfPoints - 1) );

            interval[i] = x;
        }

        // need to implement section point (x , y) generation

        return points;
    }

    public void generateOrderedMeshPoints() {
        // implementation here
    };

    public void generateVertices() {
        // implementation here
    }

    public void generateNormals() {
        // implementation here
    }

}
