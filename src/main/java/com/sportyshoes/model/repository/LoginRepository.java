package com.sportyshoes.model.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sportyshoes.model.beans.UserMgmt;

@Repository
public class LoginRepository {

	@Autowired
	EntityManager entityMgr;

	private static final Logger logger = LogManager.getLogger(LoginRepository.class);

	public boolean validateLoginDetails(UserMgmt user) {
		logger.debug(">>> Start of validateLoginDetails() in " + this.getClass().getSimpleName() + " <<<<");

		try {
			TypedQuery<UserMgmt> query = entityMgr.createQuery(
					                          "SELECT user FROM UserMgmt user " + " WHERE user.emailId = :emailId "
							          + "       and user.password = :password " + "       and user.typeOfUser = :typeOfUser",
					UserMgmt.class);

			UserMgmt dbUser = query.setParameter("emailId", user.getEmailId())
					.setParameter("password", user.getPassword()).setParameter("typeOfUser", user.getTypeOfUser())
					.getSingleResult();

			boolean result = (dbUser == null) ? false : true;
           
			return result;

		} 
		catch (javax.persistence.NoResultException e) 
		{
			logger.catching(e);
			return false;
		} catch (Exception e) {
			logger.catching(e);
			return false;
		} finally {
			logger.debug("<<<< End of validateLoginDetails() in " + this.getClass().getSimpleName() + " >>>>");
		}

	}

	public int changeAdminPassword(UserMgmt userProfile) {
		
		logger.debug(">>> Start of changeAdminPassword() in " + this.getClass().getSimpleName() + " <<<<");
		try {
			Query query = entityMgr.createQuery(
					"Update UserMgmt set password = :password where emailId = :emailId and typeOfUser = :typeOfUser")
					.setParameter("emailId", userProfile.getEmailId())
					.setParameter("password", userProfile.getPassword())
					.setParameter("typeOfUser", userProfile.getTypeOfUser());

			EntityTransaction tran = entityMgr.getTransaction();

			tran.begin();
			int result = query.executeUpdate();
			tran.commit();
			logger.debug("<<<< Admin Password Updated with return code  " + result + " >>>>");
			return result;

		} catch (Exception e) {
			logger.catching(e);
			return 0;
		} finally {
			logger.debug("<<<< End of changeAdminPassword() in " + this.getClass().getSimpleName() + " >>>>");
		}

	}

}
