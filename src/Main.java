import java.util.Scanner;
import static java.lang.System.out;
public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        boolean lanjut = true;
        while (lanjut){
            out.println("\"TUBES 1 MATRIX\"");
            out.println("Menu :");
            out.println("1. Siste Persamaan Linier");
            out.println("2. Determinan");
            out.println("3. Matriks balikan");
            out.println("4. Interpolasi Polinom");
            out.println("5. Interpolasi Bicubic");
            out.println("6. Regresi linier berganda");
            out.println("7. Keluar");
            out.print("Masukkan pilihan anda : ");
            int input = in.nextInt();
            int input2;
            switch (input) {
                case 1:
                    out.println("Pilih Metode yang akan digunakan: ");
                    out.println("1. Eliminasi Gauss");
                    out.println("2. Eliminasi Gauss-Jordann");
                    out.println("3. Matriks balikan");
                    out.println("4. Kaidah Cramer");
                    out.println("5. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    input2 = in.nextInt();
                    switch (input2){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                    }
                    break;
                case 2:
                    out.println("Pilih Metode yang akan digunakan: ");
                    out.println("1. Reduksi baris");
                    out.println("2. Ekspansi kofaktor");
                    out.println("3. Kembali");
                    out.print("Masukkan pilihan anda : ");
                    input2 = in.nextInt();
                    switch (input2){
                        case 1:
                            break;
                        case 2:
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
                    switch (input2){
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                    } 
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    lanjut = false;
                    break;              
            }
        }
        in.close();
    }
}
