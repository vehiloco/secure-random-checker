import java.util.Random;
import java.security.SecureRandom;

class Basic {
    public static void main(String [] args) {
        // :: error: insecure.random.use
        Random r = new Random();
        int x = r.nextInt();
        System.out.println(x);

        SecureRandom sr = new SecureRandom();
        byte[] bytes = {(byte) 100};
        // :: error: set.constant.seed
        sr.setSeed(bytes);
    }
}
