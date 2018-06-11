package com.hepangda.ttms.util;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Utils {
    private static final int BUFFER_SIZE = 1024 * 8;

    private Utils() {}

    public static String read(Reader reader) throws IOException {
        try(StringWriter writer = new StringWriter()) {
            write(reader, writer);
            return writer.getBuffer().toString();
        }
    }

    public static long write(Reader reader, Writer writer) throws IOException {
        int read;
        long total = 0;
        char[] buf = new char[BUFFER_SIZE];
        while ((read = reader.read(buf)) != -1) {
            writer.write(buf, 0, read);
            total += read;
        }
        return total;
    }

    public static <T> ArrayList<T> slice(ArrayList<T> ar, int page, int pageby) {
        ArrayList<T> ret = new ArrayList<>();

        int skip = (page - 1) * pageby;
        for (int i = 0; i < pageby && skip + i < ar.size(); i++) {
            ret.add(ar.get(skip + i));
        }

        return ret;
    }
}
