package com.sportyshoes.interfaces;

import com.sportyshoes.model.beans.UserMgmt;

public interface LoginProcess 
{
	public boolean checkLoggedOnUser(UserMgmt user);
	public String changeLoggedOnUserPassword(UserMgmt user);

}
