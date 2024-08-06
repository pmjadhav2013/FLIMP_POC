package Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleOutputCapture {
    private static ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private static PrintStream previousOut = System.out;
    private static PrintStream previousErr = System.err;
    private static PrintStream customOut;
    private static PrintStream customErr;

    public static void start() {
        customOut = new PrintStream(new TeeOutputStream(previousOut, baos));
        customErr = new PrintStream(new TeeOutputStream(previousErr, baos));
        System.setOut(customOut);
        System.setErr(customErr);
    }

    public static String stop() {
        System.setOut(previousOut);
        System.setErr(previousErr);
        return baos.toString();
    }

    public static void reset() {
        baos.reset();
    }

    private static class TeeOutputStream extends OutputStream {
        private final OutputStream outputStream1;
        private final OutputStream outputStream2;

        public TeeOutputStream(OutputStream outputStream1, OutputStream outputStream2) {
            this.outputStream1 = outputStream1;
            this.outputStream2 = outputStream2;
        }

        @Override
        public void write(int b) {
            try {
                outputStream1.write(b);
                outputStream2.write(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void write(byte[] b) {
            try {
                outputStream1.write(b);
                outputStream2.write(b);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void write(byte[] b, int off, int len) {
            try {
                outputStream1.write(b, off, len);
                outputStream2.write(b, off, len);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


