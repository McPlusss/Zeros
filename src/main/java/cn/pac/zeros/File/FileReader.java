package cn.pac.zeros.File;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FileReader {
    public FileReader() {
    }

    public static String ReadFile(File File) {
        long fileLen = File.length();
        byte[] fileContent = new byte[(int)fileLen];

        try {
            FileInputStream in = new FileInputStream(File);
            in.read(fileContent);
            in.close();
            return new String(fileContent, StandardCharsets.UTF_8);
        } catch (IOException var5) {
            var5.printStackTrace();
            return var5.getMessage();
        }
    }
}