package lab;

// HM62
// Обратная матрица Гаусса

public class Main {

    public static void main(String[] args) {
        final int N = 4;

        double[][] a = new double[][] {
                {2.0,4.0,2.0,4.0},
                {1.0,1.0,6.0,3.0},
                {-1.0,-4.0,1.0,1.0},
                {4.0,5.0,9.0,12.0}
        };

        printM("Матрица A", a, N);

        double[][] R = new double[N][N];

        gaussR(R,a,N);

        double[][] E = new double[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                double s = 0.0;

                for (int m = 0; m < N; m++) {
                    s += R[i][m] * a[m][j];
                }
                E[i][j] = s;
            }
        }

        printM("Matrix E = R * A", E, N); //что-то странное...
    }

    public static void printMV(String text, double[][] a, double[] b, int n) {
        System.out.println(text);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) System.out.print("(");
                System.out.print (String.format("%8.2f", a[i][j]));
                if (j == (n-1)) System.out.print(")");
            }
            System.out.println(String.format("%16.2f", b[i]));
        }
        System.out.println(String.format("%24s", "-------------------------------------------------------"));
    }

    public static void printM(String text, double[][] a, int n) {
        System.out.println(text);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) System.out.print("(");
                System.out.print (String.format("%8.2f", a[i][j]));
                if (j == (n-1)) System.out.print(")\n");
            }
        }
        System.out.println(String.format("%24s", "----------------------------------"));
    }

    public static void gaussR(double[][] R, double[][] z, int n) {
        double[][] a = new double[n][n];
        a = z.clone();
        printM("GaussR", a, n);

        for (int i = 0; i < n; i++) {
            for (int j=0; j< n; j++) {
                R[i][j] = 0.0;
            }
            R[i][i] = 1.0;
        }
        printM("Matrix R", R, n);

        // Часть 1

        for (int k = 0; k < n; k++) {
            double d = a[k][k];

            for(int j = k; j < n; j++) {
                a[k][j] /= d;
            }

            for (int j = 0; j < n; j++) {
                R[k][j] /= d;
            }

            for (int i = k + 1; i < n; i++) {
                double r = a[i][k];

                for (int j = k; j < n; j++) {
                    a[i][j] -= a[k][j]*r;
                }

                for (int j = 0; j < n; j++) {
                    R[i][j] -= R[k][j]*r;
                }
            }
        }

        printM("Top A", a,n);
        printM("Matrix R", R, n);

        // Часть 2

        for (int k = n - 1; k > 0; k--) {
            for (int i = k -1; i >= 0; i--) {
                double r = a[i][k];
                a[i][k] = 0.0;
                for (int j = 0; j < n; j++) {
                    R[i][j] -= R[k][j]*r;
                }
            }
        }

        printM("Top And Bottom A", a,n);
        printM("Matrix R", R, n);
    }
}
