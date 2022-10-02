import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner in = new Scanner(System.in);
        int n,m; 
        n = in.nextInt();
        m = in.nextInt();
        Matrix test = new Matrix(n,m);
        test.read();
        SPL.solGaussJordan(test);
    } 
}
