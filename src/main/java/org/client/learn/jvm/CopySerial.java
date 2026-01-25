package org.client.learn.jvm;

import java.io.*;

/**
 * 深拷贝 浅拷贝  序列化 反序列化
 */
public class CopySerial {

    class User implements Serializable {
        int id;
        CoopyGouzao.Address address;
    }

    public static <T> T deepCopy(T obj) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);

            ObjectInputStream ois = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray())
            );
            return (T) ois.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
