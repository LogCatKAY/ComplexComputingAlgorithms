package lab;

//HM42
//Метод Гаусса
//Проверка диагональных элементов на 0
//Перестановка строк

public class Main {

    public static void main(String[] args) {

        final int N = 4;

//	    double[][] a = new double[][] {
//	            {2.0,4.0,2.0,4.0},
//                {1.0,1.0,6.0,3.0},
//                {-1.0,-4.0,1.0,1.0},
//                {4.0,5.0,9.0,12.0}
//        };

        double[][] a = new double[][] {
                {0.0, 0.0, 2.0, 0.0},
                {0.0, 0.0, 6.0, 1.0},
                {0.0, 3.0, 0.0, 1.0},
                {4.0, 2.0, 0.0, 0.0},
        };

	    //вектор свободных членов
//	    double[] b = new double[]{22.0, 19.0, -10.0, 49.0};

	    double[] b = new double[]{8.0, 27.0, 6.0, 22.0};

	    printMV("Matrix A, Vector B", a, b, N);
	    double[] x = new double[N];

	    x = gaussExchange(x,a,b,N);

        System.out.println("Roots");
        for (int i = 0; i < N; i++) {
            System.out.println(String.format("%8.2f", x[i]));
        }



//        Scanner sc = new Scanner(System.in);
//        System.out.println("Wait for a symbol>>>");
//        sc.next();


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
    }

    public static double[] gaussExchange(double[] x, double[][] z, double[] y, int n) {
        double[][] a = new double[n][n];
        a = z.clone();
        double[] b = new double[n];
        b = y.clone();
        printMV("Gauss", a, b, n);

        //Часть 1
        for (int k = 0; k < n -1; k++) {
            exchange(a, b, n, k);
            for (int i = k + 1; i < n; i++) {
                double d = a[k][k];
                double r = a[i][k] / d;     //d - ?
                for (int j = k; j < n; j++) {
                    a[i][j] -= a[k][j] * r;
                }
                b[i] -= b[k] * r;
            }
            printMV("Gauss", a, b, n);
        }

        //Часть 2
        x[n-1] = b[n-1] / a[n-1][n-1];
        for (int i = n - 2; i >= 0; i--) {
            double s = 0.0;
            for (int j = i + 1; j < n; j++) {
                s += a[i][j] * x[j];
            }
            x[i] = (b[i] - s) / a[i][i];
        }
        return x;
    }


    static void exchange(double[][] a, double[] b, int n, int k) {
        if (a[k][k] != 0) {
            return;
        }

        int m = k + 1;

        for (; m < n; m++) {
            if (a[m][k] != 0.0) {
                break;
            }
        }

        //поменяли строки
        for (int j = 0; j < n; j++) {
            double u = a[k][j];
            a[k][j] = a[m][j];
            a[m][j] = u;
        }
        double v = b[k];
        b[k] = b[m];
        b[m] = v;
    }
}
