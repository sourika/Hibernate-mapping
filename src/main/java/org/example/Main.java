package org.example;


import org.example.dao.*;
import org.example.entity.City;
import org.example.entity.Customer;
import org.hibernate.Session;
import org.example.util.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = new EntityManager();
        Customer customer = entityManager.createCustomer();
        entityManager.returnRentedFilm();
        entityManager.rentFilm(customer);
        entityManager.newFilmForRent();


    }
}





