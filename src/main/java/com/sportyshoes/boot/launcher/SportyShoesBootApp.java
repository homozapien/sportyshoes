package com.sportyshoes.boot.launcher;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.sportyshoes.model.beans.ProductBrand;
import com.sportyshoes.model.beans.ProductUsage;
import com.sportyshoes.model.beans.UserMgmt;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com.sportyshoes.model.beans")
public class SportyShoesBootApp implements CommandLineRunner {

	private static final Logger logger = LogManager.getLogger(SportyShoesBootApp.class);

	public static void main(String[] args) {
		SpringApplication.run(SportyShoesBootApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		doColumnDefaultInserts();
	}

	@Autowired
	private EntityManagerFactory emf;

	private void doColumnDefaultInserts() {
		System.out.println("........Start of attempt to performing default admin table inserts......");
		EntityManager manager = emf.createEntityManager();

		try {

			UserMgmt dbUser = manager.find(UserMgmt.class, "a@a.com");

			if (null == dbUser) 
			{
				EntityTransaction tran = manager.getTransaction();
				tran.begin();

				dbUser = new UserMgmt();
				dbUser.setEmailid("a@a.com"); //default value for Admin
				
				ProductBrand brand = new ProductBrand();
				brand.setBrand_id("Nike");
				
				ProductUsage usage = new ProductUsage();
				usage.setUsage_id("Athletics");
				
				manager.merge(dbUser);
				manager.merge(brand);
				manager.merge(usage);

				tran.commit();
			}
			else
			{
				dbUser = null;
				System.out.println("........ Admin tables default column inserts not applicable / required......");
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		System.out.println("........End of performing default admin table inserts......");

	}

}
