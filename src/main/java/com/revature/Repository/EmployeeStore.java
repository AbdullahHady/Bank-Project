package com.revature.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.ConnectionFactory;

public class EmployeeStore implements EmployeeRepo {
	int flag;
	int checkValue=0;
	boolean myValue=false;
	AdminRepo ar=new AdminStore();
	//String[] employeeNames= {"Ben2021","Stokes1100","Richard125"};

	@Override
	public int checkEmployee(String userName) {
		// TODO Auto-generated method stub
		int checkEmployeeExist= employeeMatchMethod(userName);
		return checkEmployeeExist;
	}

	private int employeeMatchMethod(String userName) {
		// TODO Auto-generated method stub
		
        String myString=null;
        //System.out.println("Test print 2");
		String sql= "SELECT * FROM bank_employee WHERE employee_username= (?)";
		try(Connection conn= ConnectionFactory.getConnection()) {
			PreparedStatement ps=conn.prepareStatement(sql);
			//System.out.println("Vai asos ?");
			ps.setString(1, userName);
			ResultSet rs= ps.executeQuery();
			
			while(rs.next())
			{
				//System.out.println("Tui asos eikhane ?");
				myString= rs.getString("employee_username");
				flag=2;
				//System.out.println("value dekhi"+flag);
				//checkValue=1;
				myValue=true;
				//System.out.println("employee try block ");
			}
			
		}catch(NullPointerException e)
		{
			//System.out.println("Ami catch block e ");
			//System.out.println("employee catch block ");
			flag=ar.checkAdmin(userName);
			
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		if(myValue==false)
		{
			
			flag=ar.checkAdmin(userName);
			
		}
		
		return flag;
		
	}


	

}
