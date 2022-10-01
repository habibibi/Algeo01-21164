import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n,m;
        n = in.nextInt();
        m = in.nextInt();
        Matrix test = new Matrix(n,m);
        test.read();
        Matrix inv = new Matrix();
        Matrix.echeForm(test, inv, true);
        inv.print();
        in.close();
    }
}
