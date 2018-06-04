package com.hepangda.ttms.util;

import java.io.*;

public class Utils {
    private static final int BUFFER_SIZE = 1024 * 8;

    private Utils() {}

    /**
     * read string.
     *
     * @param reader Reader instance.
     * @return String.
     * @throws IOException
     */
    public static String read(Reader reader) throws IOException {
        StringWriter writer = new StringWriter();
        try {
            write(reader, writer);
            return writer.getBuffer().toString();
        } finally {
            writer.close();
        }
    }

    /**
     * write.
     *
     * @param reader     Reader.
     * @param writer     Writer.
     * @return count.
     * @throws IOException
     */
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
}
