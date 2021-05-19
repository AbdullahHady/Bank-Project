package com.revature.presentation;

import java.util.InputMismatchException;
import java.util.Scanner;
import com.revature.service.Office;
import com.revature.service.ServiceLayer;

public class FrontOffice implements PresentationLayer 
{
	ServiceLayer sLayer=new Office();
	int accountNumber;
	double chosenAmount;
	int option;
	//int option2;
	
	public void displayMenue() 

	{
		System.out.println("Welcome To Capital One Bank");
		System.out.println("Please Enter your credential");
		boolean using=true;
		while(using)
		{
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter Your Username ");
			String userName=sc.nextLine();
			System.out.println("Enter Your password ");
			String password=sc.nextLine();
			
			int checkUserNum =sLayer.checkUser(userName);
			//System.out.println("The Value is "+checkUserNum);
			
			
			if(checkUserNum==3) // If the User is Client
			{
				
				//System.out.println("Not yet");
			    int flagNumber= sLayer.login(userName,password); //Calling Login Method
			    //System.out.println("The number is "+flagNumber);
			    if(flagNumber==1)
			    {
			    	boolean logged=true;
				    while(logged)
				    {
					//System.out.println("Operation Successfull !!!!!");
                    System.out.println("Welcome To our Bank!");
					System.out.println("[1] See Your Account Informations");
					System.out.println("[2] Withdraw money!");
					System.out.println("[3] Deposit money!");
					System.out.println("[4] Transfer money to another account!");
					System.out.println("[5] Log Out");
					System.out.println("Please select an option");
					//option=sc.nextInt();
					try {
						option=sc.nextInt();
					}catch(InputMismatchException e)
					{
						//e.printStackTrace();
						System.out.println("Please enter a valid key");
					}catch (Exception e)
					{
						e.printStackTrace();
					}
					
					/*try {
						 option=Integer.parseInt(sc.nextLine());
					}
					catch(NumberFormatException e){
						e.printStackTrace();
						System.out.println("You put an invalid credential , try again!");
					}catch(Exception e)
					{
						e.printStackTrace();
					}*/
					switch(option)
					{
						case 1:
						      sLayer.displayAccount(userName); //Calling method to show all info
							  break;
						case 2:
                            System.out.println("Please enter the amount you want to withdraw");
						    chosenAmount=sc.nextDouble();
							System.out.println("Your entered amount is "+chosenAmount);
							System.out.println("Do you wish to continue? For Yes type 1 || for No type 2");
					        
							/*try {
								  option2=(int) Double.parseDouble(sc.nextLine());
							}
							catch(NumberFormatException e){
								e.printStackTrace();
								System.out.println("You put an invalid credential , try again!");
							}catch(Exception e)
							{
								e.printStackTrace();
							}*/
							
							int option2=sc.nextInt();
							System.out.println("Your selected option is "+option2);
							
							
							//String option2=sc.nextLine();
							//option2.toUpperCase();
							//System.out.println(option2);
							
							if(option2==1)
							{
								sLayer.withdrawMoney(userName,chosenAmount);//Calling withdraw method by Client
							}
							else
							{
								logged=false;
								System.out.println("You have been logged out.Please log in to continue");
							}
							
							break;
						case 3:
						     System.out.println("Please enter the amount of money you want to deposit\n");
						     chosenAmount=sc.nextDouble();
						     System.out.println("Your entered amount is "+chosenAmount);
						     System.out.println("Do you wish to continue? Press 1 for Yes || Press 2 for No");
						     option2=sc.nextInt();
						     if(option2==1)
						     {
						    	 sLayer.depositMoney(userName,chosenAmount);// Calling deposit method by Client
						     }
						     else
						     {
						    	    logged=false;
									System.out.println("You have been logged out.Please log in to continue");
						     }
						    break;
						case 4:
							System.out.println("Please enter your account number.");
							int sourceAccount=sc.nextInt();
							System.out.println("Please enter the account number in which you want to transfer money.");
							int destinationAccount=sc.nextInt();
							System.out.println("Please enter your amount you want to transfer");
							chosenAmount=sc.nextDouble();
							sLayer.transferMethod(sourceAccount,destinationAccount,chosenAmount);
							break;
						case 5:
							System.out.println("You have successfully logged out!");
							logged=false;
							using=false;
							break;
							
							
					}
				
				   
			}
		}
							
						
	}
		else if(checkUserNum==2) // If the user is Employee
		{
			int myTemp=0;
			myTemp=sLayer.employeeLoginMethod(userName,password);
			if(myTemp==1)
			{
				System.out.println("Hello Employee!");
			    System.out.println("Please enter the account number to see the information");
			    accountNumber=sc.nextInt();
			    sLayer.displayClientAccount(accountNumber);//Showing client info to Employee 
			    System.out.println("You have successfully logged out!");
			}
			
		    
		    
		    
		}
		else if (checkUserNum==1) // If the User is Admin
		{
			
			int myTemp2=0;
			myTemp2=sLayer.adminLoginMethod(userName,password);
			if(myTemp2==1)
			{
				System.out.println("Hello Admin!");
				System.out.println("Please enter the account number ");
			    accountNumber=sc.nextInt();
			    System.out.println("Please select which operation you want to do ");
				System.out.println("[1] Withdraw money from this account");
				System.out.println("[2] Deposit money to this account");
				System.out.println("Please type 1 or 2");
				option=sc.nextInt();
				switch(option)
				{
				case 1:
					System.out.println("Please enter the amount of money you want to withdraw from this account\n");
					chosenAmount=sc.nextDouble(); 
					sLayer.withdrawMoneyFromClientAccount(accountNumber,chosenAmount);//Withdrawl money by Admin
					System.out.println("You have successfully logged out!");
					break;
				case 2:
					System.out.println("Please enter the amount of money you want to deposit to this account\n");
					chosenAmount=sc.nextDouble();
					sLayer.depositMoneytoClientAccount(accountNumber,chosenAmount);//Depositing money by Admin
					System.out.println("You have successfully logged out!");
					break;
					
				
				}
			}
			
			
			
			
		    
		}
			
			
		else
		{
			System.out.println("Invalid Username.Put your credentials again");
			System.out.println("Would You try again?");
			System.out.println("Please choose 'Yes' or 'No'");
			
			String tryAgain=sc.nextLine();
			tryAgain.toUpperCase();
			switch(tryAgain)
			{
			case"YES" :
				break;
			case "NO" :
				using=false;
				break;
			
			
			}
		}
	}
}

    

}
