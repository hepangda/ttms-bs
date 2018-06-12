package com.hepangda.ttms.util;

import com.hepangda.ttms.annotation.ValidateKey;
import com.hepangda.ttms.exception.FrameworkException;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Utils {
    private static final int BUFFER_SIZE = 1024 * 8;

    private Utils() {
    }

    public static String read(Reader reader) throws IOException {
        try (StringWriter writer = new StringWriter()) {
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

    public static int validate(Object obj, VaildateCondition... conds) {
        try {
            Field[] field = obj.getClass().getDeclaredFields();//拿到所有的域
            for (Field s : field) {
                s.setAccessible(true);
                ValidateKey value = s.getAnnotation(ValidateKey.class);//返回元素注解,但是没有具体的值

                if (value != null) {
                    Object trueValue = s.get(obj);//拿到域具体的值
                    if (s.getType() == int.class) {
                        if (value.minRange() != 0 && ((Integer) trueValue).intValue() < value.minRange())
                            return value.errno();
                        if (value.maxRange() != 0 && ((Integer) trueValue).intValue() > value.maxRange())
                            return value.errno();
                    } else if (s.getType() == String.class) {
                        if (value.enums()[0].length() != 0) {
                            boolean notfound = true;
                            for (String str : value.enums()) {
                                if (str.equals(trueValue)) {
                                    notfound = false;
                                    break;
                                }
                            }
                            if (notfound) {
                                return value.errno();
                            }
                        }
                        if (value.minLen() != 0 && ((String) trueValue).length() < value.minLen()) {
                            return value.errno();
                        }
                        if (value.maxLen() != 0 && ((String) trueValue).length() > value.maxLen()) {
                            return value.errno();
                        }
                    }
                    if (value.spf() != -1 && conds[value.spf()].validate(trueValue) != 0) {
                        return value.errno();
                    }
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new FrameworkException();
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
        return 0;
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
