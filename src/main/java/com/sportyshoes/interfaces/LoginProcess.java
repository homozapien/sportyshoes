package com.sportyshoes.interfaces;

import com.sportyshoes.model.beans.UserMgmt;

public interface LoginProcess 
{
	public boolean checkLoggedOnUser(UserMgmt user);
	public int changeLoggedOnUserPassword(UserMgmt user);

}
