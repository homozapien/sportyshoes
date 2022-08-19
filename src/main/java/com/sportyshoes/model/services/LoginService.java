package com.sportyshoes.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.interfaces.LoginProcess;
import com.sportyshoes.model.beans.UserMgmt;
import com.sportyshoes.model.repository.LoginRepository;

@Service
public class LoginService implements LoginProcess
{
	@Autowired
    private LoginRepository loginRepository;
    
	@Override
	public boolean checkLoggedOnUser(UserMgmt user) 
	{
		return loginRepository.validateLoginDetails(user);
	}

	@Override
	public int changeLoggedOnUserPassword(UserMgmt user) {
		
		return loginRepository.changeAdminPassword(user);
	}

}
