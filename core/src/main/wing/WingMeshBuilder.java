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

    public double[] generateSection(int nacaNumber, double chord, int numberOfPoints, double distance) {

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
            double x = ( (0 + chord) / 2 )
                     + ( (chord - 0) / 2 )
                     * Math.cos( (i * pi)/(numberOfPoints - 1) );

            interval[i] = x;
        }

        // need to implement section point (x , y) generation

        // calculating mean camber line and its slope
        double[] y_c = new double[numberOfPoints]; // mean camber line points
        double[] slopes = new double[numberOfPoints]; // slopes


        //for 0 <= x <= p*c  meaning from 0 untill/included p*c point
        int i = 0;
        while (interval[i] <= p * chord) {

            double x = interval[i];

            y_c[i] = ( m/Math.pow(p, 2) ) *
                    ( 2 * p * (x/chord) - Math.pow(x/chord, 2) );

            slopes[i] = 2 * m / Math.pow(p, 2) * ( p - x / chord );

            i += 1;
        }

        i += 1;

        //for p*c < x <= c meaning from p*c up last point in interval
        while (interval[i] <= chord) {

            double x = interval[i];

            y_c[i] = (m / Math.pow(1 - p, 2) ) *
                    ( (1 - 2 * p) + 2 * p * (x / chord) - Math.pow(x / chord, 2) );

            slopes[i] = 2 * m / Math.pow(1 - p, 2) * ( p - x / chord );

            i += 1;
        }

        //calculating half-thicknesses
        double[] y_t = new double[numberOfPoints];

        for (int idx = 0; i < y_t.length; i++) {

            double x = interval[idx];

            y_t[idx] =  t / 0.2 *
                        ( 0.2969 * Math.sqrt(x / chord)
                        - 0.1260 * (x / chord)
                        - 0.3516 * Math.pow( (x / chord), 2)
                        + 0.2843 * Math.pow( (x / chord), 3)
                        - 0.1036 * Math.pow( (x / chord), 4) );
        }



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
