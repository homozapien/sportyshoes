package com.sportyshoes.boot.launcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import com.sportyshoes.model.beans.UserMgmt;
import com.sportyshoes.model.repository.LoginRepository;

@SpringBootApplication(scanBasePackages = "com")
@EntityScan(basePackages = "com.sportyshoes.model.beans")
public class SportyShoesBootApp implements CommandLineRunner {
	
	private static final Logger logger = LogManager.getLogger(SportyShoesBootApp.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(SportyShoesBootApp.class, args);
		//System.out.println("Server up on port number 8080");
	}

	@Override
	public void run(String... args) throws Exception 
	{
		// TODO Auto-generated method stub
		//logger.debug(">>> Start of CommandLineRunner.run() in " + this.getClass().getSimpleName() + " <<<<");
		//this.quickTest();
		//logger.debug(">>> end of CommandLineRunner.run() in " + this.getClass().getSimpleName() + " <<<<");
		
	}
	
	@Autowired
	private LoginRepository logindao;
	
	private void quickTest()
	{
		UserMgmt user = new UserMgmt();
		
		user.setEmailId("a@a.com");
		user.setPassword("1");
		user.setTypeOfUser("A"); 
		
		boolean result = logindao.validateLoginDetails(user);
		logger.debug("<<<< Logged on found and validated with return code " + result + " >>>>");
		
	}

}
