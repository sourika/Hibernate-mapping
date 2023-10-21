package org.example.dao;

import org.example.entity.Payment;
import org.hibernate.Session;

public class PaymentDAO extends GenericDAO{
    public PaymentDAO(Session session) {
        super(Payment.class, session);
    }
}
