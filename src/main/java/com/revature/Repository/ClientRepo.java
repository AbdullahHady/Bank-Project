package com.revature.Repository;

public interface ClientRepo {

	

    int matchMethod(String clientName);

	void fetchInfo(String clientName);

	void makeWithdrawl(String userName, double chosenAmount);

	void makeDeposit(String userName,double chosenAmount);

	int checkClient(String userName);

	void fetchAccountInfo(int accountNum);

	//void withdrawMoneyByAdmin(int accountNumber, double chosenAmount);

	//void depositMoneyByAdmin(int accountNumber, double chosenAmount);

	void clientTransferMethod(int sourceAccount, int destinationAccount, double newBalance1,double newBalance2);

	void updateBalanceByAdmin(int accountNumber, double newBalance1);
	
	
	
	
	
}
