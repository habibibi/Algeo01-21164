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

    public static int InputJenisMasukan(Scanner in)
    // Menampilkan menu untuk menginput jenis masukan yang diinginkan
    // I.S : in merupakan scanner input utama
    // F.S : Kembalian berupa jenis masukan yang diinginkan, 1 untuk keyboard dan 2 untuk file.
    {
        clrscr();
        out.println("Jenis masukan :");
        out.println("1. Keyboard");
        out.println("2. File");
        out.println("Masukkan pilihan Anda :");
        return in.nextInt();
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
                            Matrix A = new Matrix();
                            double[] B;
                            int jenisMasukan = InputJenisMasukan(in);
                            if (jenisMasukan == 1){
                                int n;
                                out.print("Masukkan ukuran matriks (n): ");
                                n = in.nextInt();
                                A = new Matrix(n,n);
                                out.println("Masukkan nilai Aij secara berurut :");
                                A.read();
                                out.println("Masukkan nilai Bi secara berurut :");
                                B =  new double[n];
                                for (int i = 0;i < n;i++){
                                    B[i] = in.nextDouble();
                                }
                            } else {
                                out.println("Masukkan nama file input (termasuk ekstensi) :");
                                in.nextLine();
                                String namafile = in.nextLine();
                                Matrix inp = new Matrix();
                                inp.readFromFile("test/"+namafile);
                                A = new Matrix();
                                A = inp.getSubMatrix(0, 0, inp.getLastIdxRow(), inp.getLastIdxCol()-1);
                                B = new double[inp.getRow()];
                                for (int i = 0;i < inp.getRow();i++){
                                    B[i] = inp.mem[i][inp.getLastIdxCol()];
                                }
                            }
                            
                            FileWriter writer = new FileWriter("test/out.txt");
                            double[] solusi = SPL.solKaidahCramer(A, B);
                            if (solusi != null){
                                out.println("Solusi SPL nya adalah :");
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

                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        }
                        case 0: //Return
                        {
                            break;
                        }
                    }
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
                    out.println();
                    switch (metode){
                        case 1: //Reduksi Baris
                        {
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        }
                        case 2: //Ekspansi Kofaktor
                        {
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        }
                        case 0: //Return
                        {
                            break;
                        }
                    }
                }
                case 3: //Matriks balikan
                {
                    out.println("Pilih Metode yang akan digunakan: ");
                    out.println("1. Eliminasi Gauss-Jordan");
                    out.println("2. Matriks Adjoin");
                    out.println("0. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    int metode = in.nextInt();
                    out.println();
                    switch (metode){
                        case 1: //Eliminasi Gauss-Jordan
                        {
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
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
                }
                case 4: // Interpolasi polinom
                {
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 5: // Interpolasi bicubic
                {
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                }
                case 6: // Regresi linier berganda
                {
                    int jenisMasukan = InputJenisMasukan(in);
                    Matrix data = new Matrix();
                    double[] xk;
                    int n,m;
                    if (jenisMasukan == 1){
                        out.println("Masukkan banyak peubah (n) : ");
                        n = in.nextInt();
                        out.println("Masukkan banyak sampel (m) :");
                        m = in.nextInt();
                        data = new Matrix(m,n+1);
                        out.println("Masukkan data tiap sampel secara berurut (x1i,x2,....,xni,yi) : ");
                        data.read();
                        xk = new double[n];
                        out.println("Masukkan nilai-nilai xk yang akan ditafsir nilainya secara berurut (x1k,x2k,...,xnk) : ");
                        for (int i = 0;i < n;i++) xk[i] = in.nextDouble();
                    } else {
                        out.println("Masukkan nama file input (termasuk ekstensi) :");
                        in.nextLine();
                        String namafile = in.nextLine();
                        namafile = "test/" + namafile;
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
