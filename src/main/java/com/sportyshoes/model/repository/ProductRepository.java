package com.sportyshoes.model.repository;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository 
{

   @Autowired
   EntityManagerFactory emf;
}
