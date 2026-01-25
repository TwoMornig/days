package org.client.learn.jvm;

/**
 * 深拷贝 浅拷贝
 */
public class CopyObjOverWrite {

    class Address implements Cloneable {
        String city;

        @Override
        protected Address clone() throws CloneNotSupportedException {
            return (Address) super.clone();
        }
    }

    class User implements Cloneable {
        int id;
        Address address;

        @Override
        protected User clone() throws CloneNotSupportedException {
            User copy = (User) super.clone();   // 浅拷贝
            copy.address = address.clone();     // 关键：手动深拷贝
            return copy;
        }
    }

}
