package matriks;
import java.util.Scanner;
import java.io.*;


public class Matrix {
    private int row;
    private int col;
    public double[][] mem;
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
        this.row = A.getRow();
        this.col = A.getCol();
        this.mem = new double[this.row][this.col];
        for (int i = 0;i < A.getRow();i++){
            for (int j = 0;j < A.getCol();j++){
                this.mem[i][j] = A.mem[i][j];
            }
        }
    }
    /* *** Selektor *** */
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
    public Matrix getSubMatrix(int sRow,int fRow,int sCol, int fCol)
    // Mengembalikan sub matriks m yang barisnya dari indeks sRow hingga fRow dan kolomnya dari indeks sCol hinggal fCol
    {
        Matrix tmp = new Matrix(fRow-sRow+1,fCol-sCol+1);
        for (int i = 0;i < fRow-sRow+1;i++){
            for (int j = 0;j < fCol-sCol+1;j++){
                tmp.mem[i][j] = this.mem[i+sRow][j+sCol];
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
        in.nextLine();
    }
    public void readFromFile(String fileDir)  throws FileNotFoundException
    { // Membaca isi matriks dari file
        Scanner inputfile = new Scanner(new File(fileDir));
        int rows = 0;
        int columns = -1;
        while (inputfile.hasNextLine()){
            int tmpcol = 0;
            Scanner colReader = new Scanner(inputfile.nextLine());
            while (colReader.hasNextDouble()){
                colReader.nextDouble();
                tmpcol++;
            }
            if (columns == -1) columns = tmpcol;
            else if (tmpcol != columns) break;
            rows++;
        }
        this.row = rows;
        this.col = columns;
        this.mem = new double[rows][columns];
        inputfile.close();
        inputfile = new Scanner(new File(fileDir));
        for (int i = 0;i < this.row;i++){
            for (int j = 0;j < this.col;j++){
                if (inputfile.hasNextDouble()){
                    this.mem[i][j] = inputfile.nextDouble();
                }
            }
        }
    }
    public void printToScr()
    // Menuliskan isi Matriks saat ini ke layar
    {
        for (int i = 0;i <= this.getLastIdxRow();i++){
            for (int j = 0;j <= this.getLastIdxCol();j++){
                System.out.print(mem[i][j]);
                if (j != this.getLastIdxCol()) System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    public void printToFile(String fileDir) 
    // Menuliskan isi Matriks saat ini ke sebuah file dengan direktori fileDir
    throws IOException
    {
        FileWriter writer = new FileWriter(fileDir);
        for (int i = 0;i <= this.getLastIdxRow();i++){
            for (int j = 0;j <= this.getLastIdxCol();j++){
                writer.write(Double.toString(mem[i][j]));
                if (j != this.getLastIdxCol()) writer.write(" ");
            }
            writer.write("\n");
        }
        writer.close();
    }

    /* ****** Checker ****** */
    public boolean isSquare()
    // Mengembalikan true jika objek merupakan matriks persegi, false jika sebaliknya
    {
        if (this.getRow() == this.getCol()){
            return true;
        }
        return false;
    }

    public boolean isIdentity()
    // Mengembalikan true jika objek merupakan matriks identitas, false jika sebaliknya
    {
        for (int i = 0;i < this.getRow();i++){
            for (int j = 0;j < this.getCol();j++){
                if (i == j && this.mem[i][j] != 1) return false;
                if (i != j && this.mem[i][j] != 0) return false;
            }
        }
        return true;
    }

    /* ****** Operator ****** */
    public static void assign(Matrix m1, Matrix m2)
    // m2 := m1;
    {
        m2.row = m1.getRow();
        m2.col = m1.getCol();
        m2.mem = new double[m2.row][m2.col];
        for (int i = 0;i < m1.getRow();i++){
            for (int j = 0;j < m1.getCol();j++){
                m2.mem[i][j] = m1.mem[i][j];
            }
        }
    }
    public static Matrix add(Matrix m1,Matrix m2)
    // Mengembalikan matriks hasil dari m1 + m2
    {
        Matrix m3 = new Matrix(m1.getRow(),m2.getCol());
        for (int i = 0;i < m3.getRow();i++){
            for (int j = 0;j < m3.getCol();j++){
                m3.mem[i][j] = m1.mem[i][j] + m2.mem[i][j];
            }
        }
        return m3;
    }
    public static Matrix min(Matrix m1,Matrix m2)
    // Mengembalikan matriks hasil dari m1-m2
    {
        Matrix m3 = new Matrix(m1.getRow(),m2.getCol());
        for (int i = 0;i < m3.getRow();i++){
            for (int j = 0;j < m3.getCol();j++){
                m3.mem[i][j] = m1.mem[i][j] - m2.mem[i][j];
            }
        }
        return m3;
    }
    public static Matrix mult(Matrix m1,Matrix m2)
    // Mengembalikan matriks hasil dari m1*m2
    {
        Matrix m3 = new Matrix(m1.getRow(),m2.getCol());
        for (int i = 0;i < m1.getRow();i++){
            for (int j = 0;j < m2.getCol();j++){
                double tmp = 0;
                for (int k = 0;k < m1.getCol();k++){
                    tmp += m1.mem[i][k]*m2.mem[k][j];
                }
                m3.mem[i][j] = tmp;
            }
        }
        return m3;
    }

    public void timesConst(double k)
    // Mengembalikan matriks hasil dari m1*m2
    {
        for (int i  = 0;i < this.col;i++){
            for (int j = 0;j < this.row;j++){
                this.mem[i][j] *= k;
            }
        }
    }

    /* *** Operasi lain **** */
    public static Matrix combine(Matrix A,Matrix B)
    // I.S : A dan B terdefinisi
    // F.S : A diaugment dengan B menjadi AB
    {
        Matrix tmp = new Matrix(A.getRow(),A.getCol()+B.getCol());
        for (int i = 0;i < A.getRow();i++){
            for (int j = 0;j < A.getCol();j++){
                tmp.mem[i][j] = A.mem[i][j];
            }
        }
        for (int i = 0;i < B.getRow();i++){
            for (int j = 0;j < B.getCol();j++){
                tmp.mem[i][j+A.getCol()] = B.mem[i][j];
            }
        }
        return tmp;
    }

    public static Matrix SPLtoAug(Matrix A,double[] B)
    // I.S : A dan B terdefinisi
    // F.S : A diaugment dengan B menjadi AB
    {
        Matrix tmp = new Matrix(A.getRow(),A.getCol()+1);
        for (int i = 0;i < A.getRow();i++){
            for (int j = 0;j < A.getCol();j++){
                tmp.mem[i][j] = A.mem[i][j];
            }
        }
        for (int i = 0;i < B.length;i++){
            tmp.mem[i][A.getCol()+1] = B[i];
        }
        return tmp;
    }

    private void swapRow(int i1,int i2)
    // I.S : Matriks terdefinsi
    // F.S : Lolom i1 dan i2 dari matriks menjadi tertukar
    {
        for (int j = 0;j < this.col;j++){
            double tmp = this.mem[i1][j];
            this.mem[i1][j] = this.mem[i2][j];
            this.mem[i2][j] = tmp;
        }
    }

    private void addRowtoRow(int i1,int i2,double k)
    // I.S : Matriks terdefinisi
    // F.S : Kolom i2 ditambah dengan kolom i2 yang sudah dikali k
    {
        for (int j = 0;j < this.col;j++){
            this.mem[i2][j] += k*this.mem[i1][j];
        }
    }

    public static void echeForm(Matrix m, Matrix mOut, boolean isReduced)
    // I.S : Matriks m dan isReduced terdefenisi. mOut bebas.
    // F.S : mOut merupakan bentuk echelon form aatu reduced echelon form dari m tergantung input. Selain itu 
    //          prosedur juga mengembalikan sebuah konstanta K yang menunjukkan konstanta determinan matriks setelah
    //          dilakukannya OBE pada matriks
    {
        assign(m,mOut);
        for (int i1 = 0;i1 < Math.min(mOut.getRow(),mOut.getCol());i1++){
            int jStart = i1;
            while (jStart < mOut.getCol() && mOut.mem[i1][jStart] == 0){
                int swapidx = i1+1;
                while (swapidx < mOut.getRow() && mOut.mem[swapidx][jStart] == 0){
                    swapidx++;
                }
                if (swapidx != m.row){
                    mOut.swapRow(i1, swapidx);
                    break;
                } else {
                    jStart++;
                }
            }
            if (jStart == m.getCol()) break;

            if (mOut.mem[i1][jStart] != 1){
                double divisor = mOut.mem[i1][jStart];
                for (int j = jStart;j < m.col;j++){
                    mOut.mem[i1][j] /= divisor;
                }
            }
            if (isReduced){
                for (int i2 = 0;i2 < m.row;i2++){
                    if (i2 == i1) continue;
                    mOut.addRowtoRow(i1, i2, mOut.mem[i2][jStart]*(-1));
                }
            } else {
                for (int i2 = i1+1;i2 < mOut.row;i2++){
                    mOut.addRowtoRow(i1, i2, mOut.mem[i2][jStart]*(-1));
                }
            }
        }
    }

    public static void transpose(Matrix m)
    // I.S : m terdefinisi
    // F.S : m ditranspos
    {
        Matrix tmp = new Matrix(m.col,m.row);
        for (int i = 0;i < m.row;i++){
            for (int j = 0;j < m.col;j++){
                tmp.mem[j][i] = m.mem[i][j];
            }
        }
        assign(tmp,m);
    }
}   