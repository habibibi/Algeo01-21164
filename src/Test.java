
public class Test {
    public static void main(String[] args){
        Matrix test = new Matrix(3,3);
        test.read();
        test.print();
        Matrix kopian = new Matrix();
        Matrix.assignMatrix(test,kopian);
        kopian.print();
        Matrix tmp = new Matrix(kopian.getSubMatrix(0, 0, 1, 1));
        tmp.print();
    }
}
