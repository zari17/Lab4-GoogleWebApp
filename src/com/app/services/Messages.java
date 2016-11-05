package com.app.services;

public class Messages 
{
	private boolean IsValid = true;
	private String Message = "";
	
	public void AddError(String error)
	{
		this.IsValid = false;
		this.Message += error + "\n";
	}
	
	public boolean GetValidationResult()
	{
		return this.IsValid;
	}
	
	public String GetMessage()
	{
		return this.Message;
	}
}
