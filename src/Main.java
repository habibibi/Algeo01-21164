import java.util.Scanner;
import static java.lang.System.out;
import java.io.*;

public class Main {
    public static void clrscr(){
        System.out.println("\033[H\033[2J");
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
            out.println("7. Keluar");
            out.print("Masukkan pilihan anda : ");
            int input = in.nextInt();
            clrscr();
            switch (input) {
                case 1:
                    out.println("Pilih Metode yang akan digunakan :");
                    out.println("1. Eliminasi Gauss");
                    out.println("2. Eliminasi Gauss-Jordann");
                    out.println("3. Matriks balikan");
                    out.println("4. Kaidah Cramer");
                    out.println("5. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    int input2 = in.nextInt();
                    clrscr();
                    switch (input2){
                        case 1:
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        case 2:
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        case 3:
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        case 4:
                            Matrix A;
                            double[] B;
                            out.println("Jenis input :");
                            out.println("1. Keyboard");
                            out.println("2. File");
                            out.println("Masukkan pilihan Anda :");
                            int input3 = in.nextInt();
                            if (input3 == 1){
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
                                in.nextLine();
                                out.println("Masukkan nama file input (termasuk ekstensi) :");
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
                        case 5:
                            break;
                    }
                    break;
                case 2:
                    out.println("Pilih Metode yang akan digunakan :");
                    out.println("1. Reduksi baris");
                    out.println("2. Ekspansi kofaktor");
                    out.println("3. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    input2 = in.nextInt();
                    out.println();
                    switch (input2){
                        case 1:
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        case 2:
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        case 3:
                            break;
                    }
                case 3:
                    out.println("Pilih Metode yang akan digunakan: ");
                    out.println("1. Eliminasi Gauss Jordan");
                    out.println("2. Matriks Adjoin");
                    out.println("3. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    input2 = in.nextInt();
                    out.println();
                    switch (input2){
                        case 1:
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        case 2:
                            out.print("Tekan ENTER untuk kembali ke menu utama");
                            System.in.read();
                            break;
                        case 3:
                            break;
                    } 
                case 4:
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                case 5:
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                case 6:
                    out.print("Tekan ENTER untuk kembali ke menu utama");
                    System.in.read();
                    break;
                case 7:
                    lanjut = false;
                    break;              
            }
        }
        in.close();
    }
}
