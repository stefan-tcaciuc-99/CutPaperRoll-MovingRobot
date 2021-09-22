//Stefan Tcaciuc R00192770
import java.util.Scanner;

public class PaperRollCuttingBottomUp {
    private static void paperRollCut(double[] p, int n) {
        double[] r = new double[n+1];
        int[] s = new int[n+1];
        r[0]=0;                             //roll of size 0 has 0 cost
        double q;
        for (int j = 1; j <= n; j++) {          //increment problem size by 1
            q = -1;
            for (int i = 1; i <= j; i++) {
                if (q < (p[i] + r[j - i])) {        //if price[i]+ rodlength[j-1] is greater than q;
                    q = p[i] + r[j - i];            //q = price[i]+ rodlength[j-1]
                    s[j] = i;               //store cut position
                }
            }

            r[j] = q;               //set max revenue
        }
        int sIndex = n;
        int sCount = 1;
        System.out.println("For a paper roll of length " + n + " The best price is = €" + r[n]);
        System.out.println("The length of the cut rolls are");
        while (sIndex > 0) {
            System.out.println("Roll " + sCount + " Length = " + s[sIndex]);
            sCount++;
            sIndex = sIndex - s[sIndex];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose the length of the paper roll. Where length >= 0: ");
        int n = scanner.nextInt();
        int size=6;
        if (n>6){
             size =n+1;
        }
        double[] p = new double[size];
        p[1] = 1.2;
        p[2] = 3;
        p[3] = 5.8;
        p[5] = 10.1;
        paperRollCut(p, n);
    }
}
