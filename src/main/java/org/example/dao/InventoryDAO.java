package org.example.dao;

import org.example.entity.Inventory;
import org.hibernate.Session;

public class InventoryDAO extends GenericDAO{
    public InventoryDAO(Session session) {
        super(Inventory.class, session);
    }
}
