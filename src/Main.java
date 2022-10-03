import java.util.Scanner;
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
        out.println("Masukkan pilihan Anda :");
        return in.nextInt();
    }

    public static String inputNamaFile(Scanner in)
    // Memberikan dialog untuk menerima nama file dari pengguna
    // I.S : in merupakan scanner input utama. Pada layar terdapat \n yang belum dibaca scanner in
    // F.S : Kembalian berupa nama file beserta alamat lengkapnya relatif terhadap program saat ini.
    {
        out.println("Masukkan nama file input yang sudah diletakkan di test/ (termasuk ekstensi) :");
        in.nextLine();
        String namafile = in.nextLine();
        namafile = "test/" + namafile; //asumsi sementara program berada di root. Nanti sebelum dikumpul test/ diubah jd ../test/
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
                        String namafile = inputNamaFile(in);
                        augM.readFromFile(namafile);
                    }
                    switch (metode){
                        case 1: //Eliminasi Gauss
                        {
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        }
                        case 2: //Eliminasi Gauss-Jordan
                        {
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        }
                        case 3: //Matriks Balikan
                        {
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        }
                        case 4: //Kaidah Cramer
                        {                           
                            FileWriter writer = new FileWriter("test/out.txt");
                            double[] solusi = SPL.solKaidahCramer(augM);
                            if (solusi != null){
                                out.println("Solusi SPL-nya adalah :");
                                // Cetak solusi ke layar serta ke sebuah file out.txt
                                for (int i = 0;i < solusi.length;i++){ 
                                    out.println("x" + i + " : " + solusi[i]);
                                    writer.write("x" + i + " : " + Double.toString(solusi[i]) + "\n");
                                }
                                writer.close();
                                out.println("Solusi sudah dicetak di out.txt!");
                            } else {
                                out.println("Tidak ada solusi unik!");
                            }
                            break;
                        }
                        case 0: //Return
                        {
                            break;
                        }

                    }
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 2: //Determinan
                {
                    out.println("Pilih Metode yang akan digunakan: ");
                    out.println("1. Reduksi Baris");
                    out.println("2. Ekspansi Kofaktor");
                    out.println("0. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    int metode = in.nextInt();
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
                        String namafile = inputNamaFile(in);
                        M.readFromFile(namafile);
                    }
                    double det;
                    switch (metode){
                        case 1: //Reduksi Baris
                        {
                            break;
                        }
                        case 2: //Ekspansi Kofaktor
                        {
                            det = Determinan.detCofactorExpansion(M);
                            break;
                        }
                        case 0: //Return
                        {
                            break;
                        }
                    }
                    
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                }
                case 3: //Matriks balikan
                {
                    out.println("Pilih Metode yang akan digunakan: ");
                    out.println("1. Eliminasi Gauss-Jordan");
                    out.println("2. Matriks Adjoin");
                    out.println("0. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    int metode = in.nextInt();    
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
                        String namafile = inputNamaFile(in);
                        M.readFromFile(namafile);
                    }
                    switch (metode){
                        case 1: //Eliminasi Gauss-Jordan
                        {
                            Matrix invers = Invers.invGaussJordan(M);
                            if (invers != null){
                                out.println("Invers matriks tersebut adalah : ");
                                invers.printToScr();
                                invers.printToFile("src/out.txt");
                                out.println("Output sudah dicetak ke out.txt!");
                            } else {
                                out.println("Matriks tidak memiliki invers!");
                            }

                        }
                        case 2: //Matriks Adjoin
                        {
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        }
                        case 0: //Return
                            break;
                    } 
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 4: // Interpolasi polinom
                {
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 5: // Interpolasi bicubic
                {
                    //input
                    String namafile = inputNamaFile(in);
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
                    out.printf("Nilai dari f(%.2f,%.2f) adalah %.2f\n",x,y,res);
                    FileWriter writer = new FileWriter("test/out.txt");
                    writer.write(Double.toString(res)+"\n");
                    writer.close();
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 6: // Regresi linier berganda
                {
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
                        String namafile = inputNamaFile(in);
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
                    out.printf("%d %d\n",n,m);
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
                    augM.printToScr();
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
