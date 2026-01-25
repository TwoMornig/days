package org.client.learn.jvm;

public class CoopyGouzao {
    class User {
        int id;
        Address address;

        User(User other) {
            this.id = other.id;
            this.address = new Address(other.address);
        }
    }

    class Address {
        String city;

        Address(Address other) {
            this.city = other.city;
        }
    }

}
