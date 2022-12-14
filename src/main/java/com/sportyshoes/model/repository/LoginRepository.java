package com.sportyshoes.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sportyshoes.model.beans.UserMgmt;

@Repository
public class LoginRepository {

	@Autowired
	EntityManager entityMgr;

	public boolean validateLoginDetails(UserMgmt user) {

		try {
			TypedQuery<UserMgmt> query = entityMgr.createQuery(
					                          "SELECT user FROM UserMgmt user " + " WHERE user.emailid = :emailid "
							          + "       and user.password = :password " + "       and user.typeOfUser = :typeOfUser",
					UserMgmt.class);
			

			UserMgmt dbUser = query.setParameter("emailid", user.getEmailid())
					.setParameter("password", user.getPassword()).setParameter("typeOfUser", user.getTypeOfUser())
					.getSingleResult();
			

			boolean result = (dbUser == null) ? false : true;
           
			return result;

		} 
		catch (javax.persistence.NoResultException e) 
		{
			System.err.println(e);
			return false;
		} catch (Exception e) {
			System.err.println(e);
			return false;
		} 

	}

	@Transactional
	public int changeAdminPassword(UserMgmt userProfile) {
		
		try {
			Query query = entityMgr.createQuery(
					"Update UserMgmt set password = :password where emailId = :emailId and typeOfUser = :typeOfUser")
					.setParameter("emailId", userProfile.getEmailid())
					.setParameter("password", userProfile.getPassword())
					.setParameter("typeOfUser", userProfile.getTypeOfUser());

			
			return  query.executeUpdate();
		} 
		catch (Exception e)
		{
			System.err.println(e);
			return 0;
		}

	}

}
