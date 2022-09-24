import java.util.Scanner;

public class Matrix {
    private int row;
    private int col;
    private double[][] mem;
    /* ****** PROTOTIPE PRIMITIF ****** *?
    /* *** Konstruktor *** */ 
    public Matrix(){}
    public Matrix(int n, int m)
    // Constructor dari row dan column
    {
        this.row = n;
        this.col = m;
        mem = new double[n][m];
    }
    public Matrix(Matrix A) {
    // Constructor copy dari matrix lain
        this.row = A.row;
        this.col = A.col;
        this.mem = A.mem;
    }
    /* *** Selektor *** */
    public double getELMT(int i,int j){
        return this.mem[i][j];
    }
    public void setELMT(int i,int j, double val){
        this.mem[i][j] = val;
    }
    public int getRow(){
        return this.row;
    }
    public int getCol(){
        return this.col;
    }
    /* *** Selektor : Untuk Matrix terdefinisi *** */
    public int getLastIdxRow(){
    // Mengembalikan index baris terbesar
        return this.row-1;
    }
    public int getLastIdxCol(){
    // Mengembalikan indeks kolom terbesar
        return this.col-1;
    }
    public double getElmtDiagonal(int i){
    // Mengembalikan elemen diagonal m(i,i)
        return this.mem[i][i];
    }
    public Matrix getSubMatrix(int sRow,int sCol,int fRow, int fCol)
    // Mengembalikan sub matriks m yang indeks awalnya m(sRow,sCol) dan indeks akhirnya m(fRow,fCol)
    {
        Matrix tmp = new Matrix(fRow-sRow+1,fCol-sCol+1);
        for (int i = 0;i < fRow-sRow+1;i++){
            for (int j = 0;j < fCol-sCol+1;j++){
                tmp.setELMT(i, j, this.getELMT(i+sRow, j+sCol));
            }
        }
        return tmp;
    }

    /* ****** IO ****** */
    public void read()
    // Membaca elemen dari matriks saat ini sesuai ukuran yang sudah didefinisikan
    {
        Scanner in = new Scanner(System.in);
        for (int i = 0;i <= this.getLastIdxRow();i++){
            for (int j = 0;j <= this.getLastIdxCol();j++){
                mem[i][j] = in.nextDouble();
            }
        }
        in.close();
    }
    public void print()
    // Mengoutputkan elemen Matriks saat ini sesuai ukuran yang sudah didefinisikan
    {
        for (int i = 0;i <= this.getLastIdxRow();i++){
            for (int j = 0;j <= this.getLastIdxCol();j++){
                System.out.print(mem[i][j]);
                if (j != this.getLastIdxRow()) System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    /* ****** Checker ****** */
    public boolean isSquare(){
        if (this.getRow() == this.getCol()){
            return true;
        }
        return false;
    }

    public boolean isIdentity(){
        for (int i = 0;i < this.getRow();i++){
            for (int j = 0;j < this.getCol();j++){
                if (i == j && this.mem[i][j] != 1) return false;
                if (i != j && this.mem[i][j] != 0) return false;
            }
        }
        return true;
    }

    /* ****** Operator ****** */
    public static void assignMatrix(Matrix mIn, Matrix mOut)
    // Assign matrix 
    {
        mOut.row = mIn.row;
        mOut.col = mIn.col;
        mOut.mem = mIn.mem;
    }
    public static Matrix addM(Matrix m1,Matrix m2){
        Matrix m3 = new Matrix(m1.getRow(),m2.getCol());
        for (int i = 0;i < m3.getRow();i++){
            for (int j = 0;j < m3.getCol();j++){
                m3.setELMT(i, j, m3.getELMT(i, j)+m2.getELMT(i, j));
            }
        }
        return m3;
    }
    public static Matrix minM(Matrix m1,Matrix m2){
        Matrix m3 = new Matrix(m1.getRow(),m2.getCol());
        for (int i = 0;i < m3.getRow();i++){
            for (int j = 0;j < m3.getCol();j++){
                m3.setELMT(i, j, m1.getELMT(i, j)-m2.getELMT(i, j));
            }
        }
        return m3;
    }
    public static Matrix multM(Matrix m1,Matrix m2)
    {
        Matrix m3 = new Matrix(m1.getRow(),m2.getCol());
        for (int i = 0;i < m1.getRow();i++){
            for (int j = 0;j < m2.getCol();j++){
                double tmp = 0;
                for (int k = 0;k < m1.getCol();k++){
                    tmp += m1.getELMT(i, k)*m2.getELMT(k, j);
                }
                m3.setELMT(i, j, tmp);
            }
        }
        return m3;
    }
}   