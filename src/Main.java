import java.util.Scanner;
import Matriks.Matrix;
import Matriks.Invers;
import Matriks.Determinan;
import Matriks.SPL;
import static java.lang.System.out;
import java.io.*;

public class Main {
    public static void clrscr()
    // I.S : bebas
    // F.S : layar dibersihkan
    {
        System.out.println("\033[H\033[2J");
    }

    public static int inputJenisMasukan(Scanner in)
    // Menampilkan menu untuk menginput jenis masukan yang diinginkan
    // I.S : in merupakan scanner input utama
    // F.S : Kembalian berupa jenis masukan yang diinginkan, 1 untuk keyboard dan 2 untuk file.
    {
        out.println("Jenis masukan :");
        out.println("1. Keyboard");
        out.println("2. File");
        out.print("Masukkan pilihan Anda : ");
        return in.nextInt();
    }

    public static int inputJenisKeluaran(Scanner in)
    {
        out.println("Jenis keluaran :");
        out.println("1. Layar");
        out.println("2. File");
        out.print("Masukkan pilihan Anda : ");
        return in.nextInt();
    }

    public static String inputNamaFileMasukan(Scanner in)
    // Memberikan dialog untuk menerima nama file dari pengguna
    // I.S : in merupakan scanner input utama. Pada layar terdapat \n yang belum dibaca scanner in
    // F.S : Kembalian berupa nama file beserta alamat lengkapnya relatif terhadap program saat ini.
    {
        out.println("Masukkan nama file input yang sudah diletakkan di test/input/ (termasuk ekstensi) :");
        in.nextLine();
        String namafile = in.nextLine();
        namafile = "../test/input/" + namafile;
        return namafile;
    }

    public static String inputNamaFileKeluaran(Scanner in)
    // Memberikan dialog untuk menerima nama file dari pengguna
    // I.S : in merupakan scanner input utama. Pada layar terdapat \n yang belum dibaca scanner in
    // F.S : Kembalian berupa nama file beserta alamat lengkapnya relatif terhadap program saat ini.
    {
        out.println("Masukkan nama file keluaran yang akan diletakan pada test/output/ (termasuk ekstensi) :");
        in.nextLine();
        String namafile = in.nextLine();
        namafile = "../test/output/" + namafile;
        return namafile;
    }

    public static void main(String[] args)
    throws IOException
    {
        Scanner in = new Scanner(System.in);
        boolean lanjut = true;

        while (lanjut){
            clrscr();
            out.println("********* TUBES 1 MATRIX *********");
            out.println("Menu :");
            out.println("1. Sistem Persamaan Linier");
            out.println("2. Determinan");
            out.println("3. Matriks balikan");
            out.println("4. Interpolasi Polinom");
            out.println("5. Interpolasi Bicubic");
            out.println("6. Regresi linier berganda");
            out.println("0. Keluar");
            out.print("Masukkan pilihan anda : ");
            int input = in.nextInt();
            switch (input) {
                case 1: //SPL
                {
                    clrscr();
                    out.println("Pilih Metode yang akan digunakan :");
                    out.println("1. Eliminasi Gauss");
                    out.println("2. Eliminasi Gauss-Jordann");
                    out.println("3. Matriks balikan");
                    out.println("4. Kaidah Cramer");
                    out.println("0. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    int metode = in.nextInt();
                    if (metode == 0) break;
                    clrscr();
                    int jenisMasukan = inputJenisMasukan(in);
                    Matrix augM = new Matrix();
                    if (metode == 3 || metode == 4) out.println("Ukuran matriks harus berupa persegi!");
                    if (jenisMasukan == 1){
                        int m,n;
                        out.print("Masukkan banyak baris (m): ");
                        m = in.nextInt();
                        out.print("Masukkan banyak kolom (n): ");
                        n = in.nextInt();
                        Matrix A = new Matrix(m,n);
                        out.println("Masukkan nilai Aij secara berurut :");
                        A.read();
                        out.println("Masukkan nilai Bi secara berurut :");
                        Matrix B =  new Matrix(m,1);
                        B.read();
                        augM = Matrix.combine(A, B);
                    } else {
                        String namafile = inputNamaFileMasukan(in);
                        augM.readFromFile(namafile);
                    }
                    double[] solusi = null;
                    switch (metode){
                        case 1: //Eliminasi Gauss
                        {
                            solusi = SPL.solGauss(augM);
                            break;
                        }
                        case 2: //Eliminasi Gauss-Jordan
                        {
                            solusi = SPL.solGaussJordan(augM);
                            break;
                        }
                        case 3: //Matriks Balikan
                        {
                            Matrix A = new Matrix();
                            Matrix B = new Matrix();
                            A = new Matrix(augM.getRow(), augM.getCol()-1);
                            B = new Matrix(augM.getRow(), 1);
                            for (int i = 0; i < augM.getRow(); ++i){
                                for (int j = 0; j < augM.getCol()-1; ++j){
                                    A.mem[i][j] = augM.mem[i][j];
                                }
                                B.mem[i][0] = augM.mem[i][augM.getCol()-1];
                            }
                            solusi = SPL.solInverse(A, B);
                            break;
                        }
                        case 4: //Kaidah Cramer
                        {                           
                            solusi = SPL.solKaidahCramer(augM);
                            break;
                        }

                    }
                    out.println();
                    int jenisKeluaran = inputJenisKeluaran(in);
                    if (jenisKeluaran == 1){
                        if (solusi != null){
                            for (int i = 0;i < solusi.length;i++){ 
                                out.println("x" + i + " : " + solusi[i]);
                            }
                        } else {
                            if (SPL.isThereSol(augM)){
                                SPL.printParaToScr(augM);
                            } else {
                                out.println("SPL tidak konsisten, tidak ada solusi!");
                            }
                        }
                    } else {
                        if (solusi != null){
                            String fileOut = inputNamaFileKeluaran(in);
                            FileWriter writer = new FileWriter(new File (fileOut));
                            for (int i = 0;i < solusi.length;i++){
                                writer.write(String.format("x%i : %.2f",i,solusi[i]));
                            }
                            out.println("Solusi sudah dicetak ke "+ fileOut);
                            writer.close();
                        } else {
                            if (SPL.isThereSol(augM)){
                                String fileOut = inputNamaFileKeluaran(in);
                                SPL.printParaToFile(augM, fileOut);
                                out.println("Solusi sudah dicetak ke "+ fileOut);
                            } else {
                                out.println("SPL tidak konsisten, tidak ada solusi!");
                            }
                        }

                    }
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 2: //Determinan
                {
                    clrscr();
                    out.println("Pilih Metode yang akan digunakan: ");
                    out.println("1. Reduksi Baris");
                    out.println("2. Ekspansi Kofaktor");
                    out.println("0. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    int metode = in.nextInt();
                    if (metode == 0) break;
                    clrscr();
                    int jenisMasukan = inputJenisMasukan(in);
                    Matrix M = new Matrix();
                    if (jenisMasukan == 1){
                        int n;
                        out.print("Masukkan ukuran matriks persegi (n) : ");
                        n = in.nextInt();
                        M = new Matrix(n,n);
                        out.println("Masukkan isi matriks secara berurut (n x n) : ");
                        M.read();
                    } else {
                        String namafile = inputNamaFileMasukan(in);
                        M.readFromFile(namafile);
                    }
                    double det = 0;
                    switch (metode){
                        case 1: //Reduksi Baris
                        {
                            det = Determinan.detReductionRow(M);
                            break;
                        }
                        case 2: //Ekspansi Kofaktor
                        {
                            det = Determinan.detCofactorExpansion(M);
                            break;
                        }
                    }
                    out.println();
                    int jenisKeluaran = inputJenisKeluaran(in);
                    if (jenisKeluaran == 1){
                        out.println("Nilai determinan Matriks tersebut adalah :");
                        out.println(det);
                    } else {
                        String fileDir = inputNamaFileKeluaran(in);
                        FileWriter writer = new FileWriter(new File(fileDir));
                        writer.write(Double.toString(det));
                        writer.close();
                    }
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 3: //Matriks balikan
                {
                    clrscr();
                    out.println("Pilih Metode yang akan digunakan: ");
                    out.println("1. Eliminasi Gauss-Jordan");
                    out.println("2. Matriks Adjoin");
                    out.println("0. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    int metode = in.nextInt();    
                    if (metode == 0) break;
                    clrscr();
                    int jenisMasukan = inputJenisMasukan(in);
                    Matrix M = new Matrix();
                    if (jenisMasukan == 1){
                        int n;
                        out.print("Masukkan ukuran matriks persegi : ");
                        n = in.nextInt();
                        M = new Matrix(n,n);
                        out.println("Masukkan isi matriks secara berurut : ");
                        M.read();
                    } else {
                        String namafile = inputNamaFileMasukan(in);
                        M.readFromFile(namafile);
                    }
                    Matrix invers = null;
                    switch (metode){
                        case 1: //Eliminasi Gauss-Jordan
                        {
                            invers = Invers.invGaussJordan(M);
                            break;
                        }
                        case 2: //Matriks Adjoin
                        {
                            invers = Invers.invAdjoint(M);
                            break;
                        }
                    } 
                    out.println();
                    int jenisKeluaran = inputJenisKeluaran(in);
                    if (jenisKeluaran == 1){
                        if (invers != null){
                            out.println("Invers matriks tersebut adalah : ");
                            invers.printToScr();
                        } else {
                            out.println("Matriks tidak memiliki invers!");
                        }
                    } else {
                        String fileOut = inputNamaFileKeluaran(in);
                        if (invers != null){
                            invers.printToFile(fileOut);
                            out.println("Solusi sudah dicetak ke "+ fileOut);
                        } else {
                            out.println("Matriks tidak memiliki invers!");
                        }
                    }
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    
                    break;
                }
                case 4: // Interpolasi polinom
                {
                    clrscr();
                    int jenisMasukan = inputJenisMasukan(in);
                    Matrix dataXY = new Matrix();
                    Matrix dataX = new Matrix();
                    Matrix dataY = new Matrix();
                    Matrix koefA = new Matrix();
                    double[] dataA;
                    int n;
                    double[] x, y;
                    double inputX, nilaifx;
                    if (jenisMasukan == 1){
                        out.println("Masukkan nilai n : ");
                        n = in.nextInt();
                        x = new double[n+1];
                        y = new double[n+1];
                        out.println("Masukkan titik-titik x dan y (cukup dipisah spasi tanpa kurung)");
                        out.println("Dimulai dari x0 y0, x1 y1, ... xn yn :");
                        for (int i = 0; i <= n; ++i){
                            x[i] = in.nextDouble();
                            y[i] = in.nextDouble();
                        }
                    } else{
                        String namafile = inputNamaFileMasukan(in);
                        dataXY.readFromFile(namafile);
                        n = dataXY.getRow()-1;
                        x = new double[n+1];
                        y = new double[n+1];
                        for (int i = 0; i <= n; ++i){
                            x[i] = dataXY.mem[i][0];
                            y[i] = dataXY.mem[i][1];
                        }
                    }
                    dataX = new Matrix(n+1, n+1);
                    dataY = new Matrix(n+1, 1);
                    for (int i = 0; i <= n; ++i){
                        for (int j = 0; j <= n; ++j){
                            dataX.mem[i][j] = Math.pow(x[j], j);
                        }
                        dataY.mem[i][0] = y[i];
                    }
                    dataA = new double[n+1];
                    koefA = new Matrix(n+1, 1);
                    for (int i=0; i<n+1; i++){
                        koefA.mem[i][1] = dataA[i];
                    }
                    if (Invers.isExistInv(koefA)){
                        dataA = SPL.solInverse(dataX, dataY);
                        String fungsi = "";
                        String elmtA, elmtX;
                        for (int k = 0; k <= n; ++k){
                            elmtA = Double.toString(dataA[k]);
                            elmtX = Double.toString(k);
                            fungsi = fungsi.concat(elmtA + "x^" + elmtX + " ");
                        }
                        out.println("Persamaan f(x) = " + fungsi);
                        out.println("Masukkan nilai x yang ingin dicari nilainya : ");
                        inputX = in.nextDouble();
                        nilaifx = 0.0;
                        for (int l = 0; l <= n; ++l){
                            nilaifx += dataA[l]*Math.pow(inputX, l);
                        }
                        out.printf("Nilai dari f(%lf) = %lf\n", x, nilaifx);
                        FileWriter writer = new FileWriter("test/out.txt");
                        writer.write(fungsi);
                        writer.write(Double.toString(nilaifx)+"\n");
                        writer.close();
                    } else {
                        out.println("Tidak dapat ditemukan solusi uniknya");
                    }
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 5: // Interpolasi bicubic
                {
                    clrscr();
                    //input
                    String namafile = inputNamaFileMasukan(in);
                    Matrix M = new Matrix();
                    M.readFromFile(namafile);
                    Scanner inputfile = new Scanner(new File(namafile));
                    for (int i = 0;i < M.getCol();i++) inputfile.nextLine();
                    double x = inputfile.nextDouble();
                    double y = inputfile.nextDouble();
                    //Algoritma
                    //Generate Matriks A
                    Matrix A = new Matrix(16,16);
                    int x0 = -1, y0 = -1;
                    for (int corY = 0;corY < 4;corY++){
                        for (int corX = 0;corX < 4;corX++){
                            for (int j = 0;j < 4;j++){
                                for (int i = 0;i < 4;i++){
                                    A.mem[corY*4+corX][j*4+i] = Math.pow(x0+corX,i)*Math.pow(y0+corY,j);
                                }
                            }
                        }
                    }
                    Matrix B = new Matrix(16,1);
                    for (int i = 0;i < 4;i++){
                        for (int j = 0;j < 4;j++){
                            B.mem[i*4+j][0] = M.mem[i][j];
                        }
                    }
                    double[] sol = SPL.solInverse(A, B);
                    double res = 0;
                    for (int j = 0;j < 4;j++){
                        for (int i = 0;i < 4;i++){
                            res += sol[j*4+i]*Math.pow(x,i)*Math.pow(y,j);
                        }
                    }
                    //output
                    int jenisKeluaran = inputJenisKeluaran(in);
                    if (jenisKeluaran == 1){
                        out.printf("Nilai dari f(%.2f,%.2f) adalah %.2f\n",x,y,res);
                    }else {
                        String fileDir = inputNamaFileKeluaran(in);
                        FileWriter writer = new FileWriter(new File(fileDir));
                        writer.write(Double.toString(res)+"\n");
                        writer.close();
                    }
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 6: // Regresi linier berganda
                {
                    clrscr();
                    int jenisMasukan = inputJenisMasukan(in);
                    Matrix data = new Matrix();
                    double[] xk;
                    int n,m;
                    if (jenisMasukan == 1){
                        out.println("Masukkan banyak peubah (n) : ");
                        n = in.nextInt();
                        out.println("Masukkan banyak sampel (m) :");
                        m = in.nextInt();
                        data = new Matrix(m,n+1);
                        out.println("Masukkan data tiap sampel secara berurut (x1i,x2i,....,xni,yi) : ");
                        data.read();
                        xk = new double[n];
                        out.println("Masukkan nilai-nilai xk yang akan ditafsir nilainya secara berurut (x1k,x2k,...,xnk) : ");
                        for (int i = 0;i < n;i++) xk[i] = in.nextDouble();
                    } else {
                        String namafile = inputNamaFileMasukan(in);
                        data.readFromFile(namafile);
                        n = data.getCol()-1;
                        m = data.getRow();
                        xk = new double[n];
                        Scanner inputfile = new Scanner(new File(namafile));
                        for (int i = 0;i < m;i++) inputfile.nextLine();
                        for (int i = 0;i < n;i++){
                            xk[i] = inputfile.nextDouble();
                        }
                        inputfile.close();
                    }
                    Matrix augM = new Matrix(n+1,n+2);
                    for (int i = 0;i < n+1;i++){
                        for (int j = 0;j < n+2;j++){
                            if (j < i) augM.mem[i][j] = augM.mem[j][1];
                            augM.mem[i][j] = 0;
                            for (int k = 0;k < m;k++){
                                double tmp;
                                if (j == 0) tmp = 1;
                                else tmp = data.mem[k][j-1];
                                if (i > 0) tmp *= data.mem[k][i-1];
                                augM.mem[i][j] += tmp;
                            }
                        }
                    }
                    int jenisKeluaran = inputJenisKeluaran(in);
                    double[] sol = SPL.solGauss(augM);
                    if (sol != null){
                        if (jenisKeluaran == 1){
                            out.println("Rumus y yang didapat :");
                            out.print("y =");
                            for (int i = 0;i < sol.length;i++){
                                if (i == 0)out.printf(" %f",sol[i]);
                                else{
                                    if (sol[i] >= 0){
                                        out.printf(" + %fx%d",sol[i],i);
                                    } else {
                                        out.printf(" - %fx%d",-sol[i],i);
                                    }
                                }
                            }
                            out.println();
                            double res = 0;
                            for (int i = 0;i < sol.length;i++){
                                if (i == 0) res += sol[0];
                                else res += sol[i]*xk[i-1];
                            }
                            out.println("Nilai yang didapatkan dari f(x1k,x2k,..,xnk)  : ");
                            out.println(res);
                        } else {
                            String fileOut = inputNamaFileKeluaran(in);
                            FileWriter writer = new FileWriter(new File (fileOut));
                            writer.write("Rumus y yang didapat :\n");
                            writer.write("y =");
                            for (int i = 0;i < sol.length;i++){
                                if (i == 0)writer.write(String.format(" %f",sol[i]));
                                else{
                                    if (sol[i] >= 0){
                                        writer.write(String.format(" + %fx%d",sol[i],i));
                                    } else {
                                        writer.write(String.format(" - %fx%d",-sol[i],i));
                                    }
                                }
                            }
                            writer.write("\n");
                            double res = 0;
                            for (int i = 0;i < sol.length;i++){
                                if (i == 0) res += sol[0];
                                else res += sol[i]*xk[i-1];
                            }
                            writer.write("Nilai yang didapatkan dari f(x1k,x2k,..,xnk)  :\n");
                            writer.write(Double.toString(res));
                            writer.close();
                        }
                        
                    } else {
                        out.println("Tidak ada solusi unik!");
                    }
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 0:
                    lanjut = false;
                    break;              
            }
        }
        in.close();
    }
}
