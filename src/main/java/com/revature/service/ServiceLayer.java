package com.revature.service;



public interface ServiceLayer {

	//client methods
	int login(String userName,String password);
	void displayAccount(String userName);
	void withdrawMoney(String userName,double chosenAmount);
	void depositMoney(String userName,double chosenAmount);
	int checkUser(String userName);
	void transferMethod(int sourceAccount, int destinationAccount, double chosenAmount);
	
	
	//Employee methods
	int employeeLoginMethod(String userName, String password);
	void displayClientAccount(int accountNumber);
	
	//Admin method
	int adminLoginMethod(String userName, String password);
	void withdrawMoneyFromClientAccount(int accountNumber, double chosenAmount);
	void depositMoneytoClientAccount(int accountNumber, double chosenAmount);
	
	
	
	
	//void myTestMethod(String name, double clientBalance);
	
	

}
