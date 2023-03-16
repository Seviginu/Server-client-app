package org.example;


    public class Address {
        private String street;
        private String city;
        private String zip;

        @Override
        public String toString(){
            return street + " " + city + " " + zip;
        }
    }

