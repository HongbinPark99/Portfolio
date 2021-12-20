import java.util.Scanner;

public class 이팔팔사 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int h = in.nextInt();
        int m = in.nextInt();
        in.close();

        if (m < 45) {
            h--; // 시(hour) 1 감소
            m = 60 - (45 - m); // 분(min) 감소
            if (h < 0) {
                h = 23;
            }
            System.out.println(h + " " + m);
        } else {
            System.out.println(h + " " + (m - 45));
        }

    }
}
