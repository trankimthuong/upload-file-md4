package com.codegym.service.impl;

import com.codegym.model.Landscape;
import com.codegym.service.ILandscapeService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LandscapeServiceImpl implements ILandscapeService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Iterable<Landscape> findAll() {
        String queryStr = "SELECT c FROM Landscape AS c";
        TypedQuery<Landscape> query = entityManager.createQuery(queryStr, Landscape.class);
        return query.getResultList();
    }

    @Override
    public Landscape findById(Long id) {
        String queryStr = "SELECT c FROM Landscape AS c WHERE c.id = :id";
        TypedQuery<Landscape> query = entityManager.createQuery(queryStr, Landscape.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Landscape update(Landscape model) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(model);
            transaction.commit();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public Landscape save(Landscape model) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(model);
            transaction.commit();
            return model;
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    @Override
    public void remove(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Landscape landscape = findById(id);
            transaction = session.beginTransaction();
            session.delete(landscape);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
