package org.example.util;

import org.example.dao.*;
import org.example.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class EntityManager {

    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory initialization failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static ActorDAO actorDAO;
    private static AddressDAO addressDAO;
    private static CategoryDAO categoryDAO;
    private static CityDAO cityDAO;
    private static CountryDAO countryDAO;
    private static CustomerDAO customerDAO;
    private static FilmDAO filmDAO;
    private static FilmTextDAO filmTextDAO;
    private static InventoryDAO inventoryDAO;
    private static LanguageDAO languageDAO;
    private static PaymentDAO paymentDAO;
    private static RentalDAO rentalDAO;
    private static StaffDAO staffDAO;
    private static StoreDAO storeDAO;

    public static void init(Session session) {
        actorDAO = new ActorDAO(session);
        addressDAO = new AddressDAO(session);
        categoryDAO = new CategoryDAO(session);
        cityDAO = new CityDAO(session);
        countryDAO = new CountryDAO(session);
        customerDAO = new CustomerDAO(session);
        filmDAO = new FilmDAO(session);
        filmTextDAO = new FilmTextDAO(session);
        inventoryDAO = new InventoryDAO(session);
        languageDAO = new LanguageDAO(session);
        paymentDAO = new PaymentDAO(session);
        rentalDAO = new RentalDAO(session);
        staffDAO = new StaffDAO(session);
        storeDAO = new StoreDAO(session);
    }

    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }

    public Customer createCustomer() {
        try (Session session = SESSION_FACTORY.openSession()) {
            init(session);
            Transaction transaction = session.beginTransaction();

            Address address = new Address();
            City city = cityDAO.getByName("Baku");
            address.setCity(city);
            address.setDistrict("Cassandra");
            address.setPhone("542-659-888");
            address.setAddress("ul.Nebesnaya,18-22");
            addressDAO.save(address);

            Customer customer = new Customer();
            customer.setActive(true);
            customer.setAddress(address);
            customer.setEmail("test@gmail.com");
            customer.setFirstName("Ivan");
            customer.setLastName("Ivanov");
            Store store = (Store) storeDAO.findAll().get(0);
            System.out.println(store.toString());
            customer.setStore(store);
            customerDAO.save(customer);

            transaction.commit();
            return customer;
        }
    }

    public void returnRentedFilm() {
        try (Session session = SESSION_FACTORY.openSession()) {
            init(session);
            Transaction transaction = session.beginTransaction();

            Rental rental = rentalDAO.getUnreturnedInventory();
            rental.setReturnDate(LocalDateTime.now());
            session.update(rental);
            transaction.commit();
        }
    }

    public void rentFilm(Customer customer) {
        try (Session session = SESSION_FACTORY.openSession()) {
            init(session);
            Transaction transaction = session.beginTransaction();

            Film film = filmDAO.findSuitableFilmForRent();
            Store store = (Store) storeDAO.findAll().get(0);
            Staff staff = store.getManagerStaff();

            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(store);
            inventoryDAO.save(inventory);

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setCustomer(customer);
            rental.setInventory(inventory);
            rental.setStaff(staff);
            rentalDAO.save(rental);

            Payment payment = new Payment();
            payment.setStaff(staff);
            payment.setRental(rental);
            payment.setPaymentDate(LocalDateTime.now());
            payment.setCustomer(customer);
            payment.setAmount(BigDecimal.valueOf(5.25));
            paymentDAO.save(payment);

            transaction.commit();
        }
    }

    public void newFilmForRent() {
        try (Session session = SESSION_FACTORY.openSession()) {
            init(session);
            Transaction transaction = session.beginTransaction();
            Film film = new Film();
            Language language = (Language) languageDAO.findAll().stream().findAny().get();
            Set<Actor> actors = new HashSet<>(actorDAO.getItems(0, 20));
            Set<Category> categories = new HashSet<>(categoryDAO.getItems(0, 3));

            film.setActors(actors);
            film.setCategories(categories);
            film.setLanguage(language);
            film.setDescription("A romantic story about true love");
            film.setLength((short) 115);
            film.setRating(Rating.R);
            film.setRentalDuration((byte) 4);
            film.setReplacementCost(BigDecimal.valueOf(15.99));
            film.setSpecialFeatures(Set.of(Feature.BEHIND_THE_SCENES, Feature.COMMENTARIES));
            film.setTitle("Moon");
            film.setYear(Year.of(2023));
            film.setRentalRate(BigDecimal.valueOf(2.99));
            filmDAO.save(film);

            FilmText filmText = new FilmText();
            filmText.setId(film.getId());
            filmText.setDescription(film.getDescription());
            filmText.setFilm(film);
            filmText.setTitle(film.getTitle());
            filmTextDAO.save(filmText);

            transaction.commit();
        }
    }

    public ActorDAO getActorDAO() {
        return actorDAO;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public CityDAO getCityDAO() {
        return cityDAO;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public FilmDAO getFilmDAO() {
        return filmDAO;
    }

    public FilmTextDAO getFilmTextDAO() {
        return filmTextDAO;
    }

    public InventoryDAO getInventoryDAO() {
        return inventoryDAO;
    }

    public LanguageDAO getLanguageDAO() {
        return languageDAO;
    }

    public PaymentDAO getPaymentDAO() {
        return paymentDAO;
    }

    public RentalDAO getRentalDAO() {
        return rentalDAO;
    }

    public StaffDAO getStaffDAO() {
        return staffDAO;
    }

    public StoreDAO getStoreDAO() {
        return storeDAO;
    }
}




