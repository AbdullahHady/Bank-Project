package com.revature.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import com.revature.util.ConnectionFactory;

public class ClientStore implements ClientRepo {
	//String[] clientNames= {"Alif007","Hady123","AwafZaman100","MacKhan222"};
	
	//int[] accountNum= {100,101,102,103};
	//double[]accountBalance= {500.50,700.00,1200.50,1100.00};
	int flag;
	int referenceNum;
	boolean myValue=false;
	int temp;
	double clientBalance;
	int clientAccountNumber;
	
	@Override
	public int checkClient(String userName) {
		int checkClientExist= matchMethod(userName);
		return checkClientExist;
		
	}
    @Override
	public int matchMethod(String userName) {
    	
    	String tempContainer=null;
    	/*
    	for(int i=0;i<clientNames.length;i++)
    	{
    		if(clientNames[i].equals(userName))
    		{
    			flag=1;
    			int temp=i;
    		}
    		
    	}
    	System.out.println("Customer name is" +clientNames[temp]+ "Customer account number is " + accountNum[temp]);
    	return flag;
    	*/
    	
		//System.out.println("Test print 1");
		String sql= "SELECT * FROM client_table WHERE client_username= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			//System.out.println("Vai asos ?");
			ps.setString(1, userName);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				//System.out.println("Tui asos eikhane ?");
				tempContainer= rs.getString("client_username");
				//System.out.println("This is first "+tempContainer);
				//System.out.println("This is second "+rs.getInt("client_id"));
				flag=1;
			}
			
		}catch(NullPointerException e)
		{
			flag=0;
			tempContainer="Username doesn't exist";
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			return flag;
			
		}
	public void fetchInfo(String userName)
	{
        String tempContainer2=null;
		
		String sql= "SELECT * FROM account WHERE username= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			//System.out.println("Vai asos ?");
			ps.setString(1, userName);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				//System.out.println("Tui asos eikhane ?");
				//tempContainer2= rs.getString("username");
				//referenceNum=rs.getInt("number");
				
				System.out.println("Your User Name is "+rs.getString("username")+
						"\nYour account number is "+rs.getInt("account_no")+"\nYour account balance is "
						+rs.getDouble("balance"));
				//flag=1;
			}
			
		}catch(NullPointerException e)
		{
			
			System.out.println("Username doesn't exist");
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
}
	
	@Override
	public void makeWithdrawl(String userName,double chosenAmount) {
        
		
		String sql= "SELECT * FROM account WHERE username= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			//System.out.println("Vai asos ?");
			ps.setString(1, userName);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("The balance is "+rs.getDouble("balance"));
				clientBalance=rs.getDouble("balance");
				if(chosenAmount<clientBalance)
				{
					myValue=true;
					clientBalance=clientBalance-chosenAmount;
					System.out.println("Withdrawl done. Your new balance is "+clientBalance);
					//updateBalance(userName, clientBalance);
					
					
				}
				else
				{
					myValue=false;
					System.out.println("Insufficient balance! Try Again");
				}
				
				//System.out.println("Tui asos eikhane ?");
				//tempContainer2= rs.getString("username");
				//referenceNum=rs.getInt("number");
				
				
			}
			
		}catch(NullPointerException e)
		{
			
			System.out.println("Username doesn't exist");
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(myValue==true)
		{
			updateBalance(userName,clientBalance);
		}
		
		
}
	
	
	@Override
	public void makeDeposit(String userName,double chosenAmount) {
		// TODO Auto-generated method stub
		
		
		String sql= "SELECT * FROM account WHERE username= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				
				clientBalance=(rs.getDouble("balance")+chosenAmount);
				System.out.println("Congratulations!Your deposit request is approved ");
				System.out.println("Your deposited amount is "+chosenAmount);
				System.out.println("Your current balance is "+clientBalance);
				updateBalance(userName,clientBalance);
				
				
				
			}
			
		}catch(NullPointerException e)
		{
			
			System.out.println("Username doesn't exist");
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@Override
	public void fetchAccountInfo(int accountNumber) {
		String sql= "SELECT * FROM account WHERE account_no= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			//System.out.println("Vai asos ?");
			ps.setInt(1, accountNumber);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				
				System.out.println("Client username is "+rs.getString("username")+
						"\nClient's account number is "+rs.getInt("account_no")+"\nClient's account balance is "
						+rs.getDouble("balance")+"$");
				
				
			}
			
		}catch(NullPointerException e)
		{
			
			System.out.println("Account doesn't exist");
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		       
		
		
	}
	/*
	@Override
	public void depositMoneyByAdmin(int accountNumber, double chosenAmount) {
		// TODO Auto-generated method stub
		for(int i=0;i<accountNum.length;i++)
		{
			if(accountNum[i]==accountNumber)
			{
				referenceNum=i;
				temp=1;
				break;
			}
		}
		if(temp==1)
		{
				accountBalance[referenceNum]=accountBalance[referenceNum]+chosenAmount;
				System.out.println("Deposit Successfull! The current balance is "+accountBalance[referenceNum]);
				
			
		}
		else
		{
			System.out.println("Invalid user!");
		}
		
		
	}
	
	
	
	*/
	public void updateBalance(String userName,double clientBalance)
	{
		String mySql= "UPDATE account SET balance=(?) WHERE username=(?)";
		
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(mySql);
			//System.out.println("Vai asos ?");
			ps.setDouble(1,clientBalance);
			ps.setString(2, userName);
			ps.executeQuery();
			
			
			
		}catch(PSQLException e)
		{
			
			System.out.println("Thank You!");
			//conclusionMethod(name);
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	     }
	     
	
		
}
	@Override
	public void clientTransferMethod(int sourceAccount, int destinationAccount, double newBalance1,
			double newBalance2) {
		// TODO Auto-generated method stub
		
		//Updating source account balance
		
		
        String mySql= "UPDATE account SET balance=(?) WHERE account_no=(?)";
		
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(mySql);
			//System.out.println("Vai asos ?");
			ps.setDouble(1,newBalance1);
			ps.setInt(2, sourceAccount);
			ps.executeQuery();
			
			
			
		}catch(PSQLException e)
		{
			
			System.out.println("Thank You!");
			//conclusionMethod(name);
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	     }
		
		//Updating destination account balance
		
       String mySql2= "UPDATE account SET balance=(?) WHERE account_no=(?)";
		
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(mySql2);
			//System.out.println("Vai asos ?");
			ps.setDouble(1,newBalance2);
			ps.setInt(2, destinationAccount);
			ps.executeQuery();
			
			
			
		}catch(PSQLException e)
		{
			
			System.out.println("Thank You!");
			//conclusionMethod(name);
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	     }
		
		
		
		
	}
	@Override
	public void updateBalanceByAdmin(int accountNumber, double newBalance1) {
		// TODO Auto-generated method stub
		
        String mySql= "UPDATE account SET balance=(?) WHERE account_no=(?)";
		
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(mySql);
			//System.out.println("Vai asos ?");
			ps.setDouble(1,newBalance1);
			ps.setInt(2, accountNumber);
			ps.executeQuery();
			
			
			
		}catch(PSQLException e)
		{
			
			System.out.println("Thank You!");
			//conclusionMethod(name);
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		
	     }
	     
	
		
		
		
	}
	
	
	
	
    
		
}
	
	
	
	

