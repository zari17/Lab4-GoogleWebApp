package com.app.services;

public class Validation 
{
	public Messages ValidateUserRecord(String userName, String userPassword)
	{
		Messages result = new Messages();
		
		if(userName == null)
		{
			result.AddError("User name cannot be empty!");
		}
		else
		{
			if(userName.length() < 3)
			{
				result.AddError("User name must be longer than 6 characters");
			}
		}
		
		if(userPassword == null)
		{
			result.AddError("User password cannot be empty!");
		}
		else
		{
			if(userPassword.length() < 5)
			{
				result.AddError("User password must be longer than 6 characters");
			}		
		}
		
		return result;
	}
}
