package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import com.revature.Repository.ClientRepo;
import com.revature.Repository.ClientStore;
import com.revature.Repository.EmployeeRepo;
import com.revature.Repository.EmployeeStore;
import com.revature.util.ConnectionFactory;

public class Office implements ServiceLayer {
	int flag3;
	int flag4;
	boolean checkNum;
	boolean checkNum3;
	int adminTemp=2;
	int clientTemp=2;
	boolean checkNum2;
	int employeeTemp=2;
    ClientRepo cr=new ClientStore();
    EmployeeRepo er=new EmployeeStore();
    double newBalance1;
    double newBalance2;
    
    
    
	@Override
	public int login(String userName,String password) { //Client Log In Method
		String tempContainer3=null;
		String sql= "SELECT * FROM client_table WHERE client_username= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				tempContainer3= rs.getString("client_password");
				//System.out.println("The password is "+tempContainer3);
				
			}
			
		}catch(NullPointerException e)
		{

			System.out.println("Invalid username");
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkNum3=tempContainer3.equals(password);
		//System.out.println("Checking number "+checkNum);
		if(checkNum3==true)
		{
			clientTemp=1;
		}
		else 
		{
			
			clientTemp=0;
			System.out.println("Invalid username or password . Please try again!");
		}
		return clientTemp;
		
		
	}
	
	@Override
	public int employeeLoginMethod(String userName, String password) {
		String tempContainer=null;
		String sql= "SELECT * FROM bank_employee WHERE employee_username= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				tempContainer= rs.getString("employee_password");
				//System.out.println("The password is "+tempContainer);
				
			}
			
		}catch(NullPointerException e)
		{

			System.out.println("Invalid username");
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkNum=tempContainer.equals(password);
		//System.out.println("Checking number "+checkNum);
		if(checkNum==true)
		{
			employeeTemp=1;
		}
		else 
		{
			
			employeeTemp=0;
			System.out.println("Invalid username or password . Please try again!");
		}
		return employeeTemp;
	}
    
	
	
	@Override
	public int adminLoginMethod(String userName, String password) {
		// TODO Auto-generated method stub
		String tempContainer2=null;
		String sql= "SELECT * FROM admin_table WHERE admin_username= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				tempContainer2= rs.getString("admin_password");
				//System.out.println("The password is "+tempContainer);
				
			}
			
		}catch(NullPointerException e)
		{

			System.out.println("Invalid username");
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		checkNum2=tempContainer2.equals(password);
		//System.out.println("Checking number "+checkNum);
		if(checkNum2==true)
		{
			adminTemp=1;
		}
		else 
		{
			
			adminTemp=0;
			System.out.println("Invalid username or password . Please try again!");
		}
		return adminTemp;
	}


	@Override
	public void displayAccount(String userName) {
		cr.fetchInfo(userName);
		
	}


	@Override
	public void withdrawMoney(String userName,double chosenAmount) {
		// TODO Auto-generated method stub
		cr.makeWithdrawl(userName,chosenAmount);
		
		
	}

	@Override
	public int checkUser(String userName) { //checking whether the user is client/employee/Admin
		// TODO Auto-generated method stub
		 flag3 = cr.checkClient(userName); // 
		 
		 
		if(flag3==1)
		{
			flag4=3;
		}
		else if(flag3==0)
		{
			flag4=er.checkEmployee(userName); //flag4 ==2 hole employee
			//System.out.println("Final value of FLAG "+flag4);
		}
		
		return flag4;
		
	}


	@Override
	public void depositMoney(String userName,double chosenAmount) {
		// TODO Auto-generated method stub
		cr.makeDeposit(userName,chosenAmount);
		
	}


	@Override
	public void displayClientAccount(int accountNumber) {
		cr.fetchAccountInfo(accountNumber);
		
	}

	// Withdrawing money from client account by Admin
	@Override
	public void withdrawMoneyFromClientAccount(int accountNumber, double chosenAmount) {
		// TODO Auto-generated method stub
		
		String sql= "SELECT * FROM account WHERE account_no= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			//System.out.println("Vai asos ?");
			ps.setInt(1, accountNumber);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				 newBalance1=rs.getDouble("balance")-chosenAmount; 
				 //System.out.println("New Account Balance "+newBalance1);
				 cr.updateBalanceByAdmin(accountNumber,newBalance1);
				
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

    // Depositing money to client account by Admin
	@Override
	public void depositMoneytoClientAccount(int accountNumber, double chosenAmount) {
		// TODO Auto-generated method stub
		String sql= "SELECT * FROM account WHERE account_no= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			//System.out.println("Vai asos ?");
			ps.setInt(1, accountNumber);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				 newBalance1=rs.getDouble("balance")+chosenAmount; 
				 //System.out.println("New Account Balance "+newBalance1);
				 cr.updateBalanceByAdmin(accountNumber,newBalance1);
				
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
    
	@Override
	public void transferMethod(int sourceAccount, int destinationAccount, double chosenAmount) {
		//Getting source account balance 
				String sql= "SELECT * FROM account WHERE account_no= (?)";
				try(Connection conn= ConnectionFactory.getConnection()) {
					PreparedStatement ps=conn.prepareStatement(sql);
					//System.out.println("Vai asos ?");
					ps.setInt(1, sourceAccount);
					ResultSet rs= ps.executeQuery();
					
					while(rs.next())
					{
						 newBalance1=rs.getDouble("balance")-chosenAmount; 
						 //System.out.println("New Source Balance "+newBalance1);
						
					}
					
				}catch(NullPointerException e)
				{
					
					System.out.println("Account doesn't exist");
					
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Getting destination account balance
				String sql2= "SELECT * FROM account WHERE account_no= (?)";
				try(Connection conn= ConnectionFactory.getConnection()) {
					PreparedStatement ps=conn.prepareStatement(sql2);
					//System.out.println("Vai asos ?");
					ps.setInt(1, destinationAccount);
					ResultSet rs= ps.executeQuery();
					
					while(rs.next())
					{
						 newBalance2=rs.getDouble("balance")+chosenAmount; 
						 //System.out.println("New Destination Balance "+newBalance2);
						
					}
					
				}catch(NullPointerException e)
				{
					
					System.out.println("Account doesn't exist");
					
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		cr.clientTransferMethod(sourceAccount,destinationAccount,newBalance1,newBalance2);
		
	}

	


	
}
	



