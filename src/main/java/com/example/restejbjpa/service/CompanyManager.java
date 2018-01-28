package com.example.restejbjpa.service;

import com.example.restejbjpa.domain.Company;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CompanyManager {
  @PersistenceContext
  EntityManager em;

  public void addCompany(Company company) {
    em.persist(company);
  }

  public Company getCompany(int id) {
    return em.find(Company.class, id);
  }

  @SuppressWarnings("unchecked")
  public List<Company> getAll() {
    return em.createNamedQuery("company.all").getResultList();
  }

  public Boolean deleteCompany(int id) {
    Company company = em.find(Company.class, id);
    if (company == null) return false;
    em.remove(company);
    return true;
  }

  public void deleteAll() {
    em.createNamedQuery("company.deleteAll").executeUpdate();
  }
}
