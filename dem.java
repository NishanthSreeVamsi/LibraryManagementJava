package labbb;
import java.sql.*;
import java.util.*;
import java.io.*;
public class dem {
	public static void welcome(){
		System.out.println("--------------------------------------------------------------------");
		System.out.println("**************ENTER YOUR CREDENTIALS*********************************");
		System.out.println("**************Enter USERID and PASSWORD******************************");
		System.out.println("--------------------------------------------------------------------");
	}
	
	public static void addUser() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		 System.out.println("Enter id: ");
		 String id = sc.nextLine();
		 System.out.println("Enter Name:");
		 String nm = sc.nextLine();
		 System.out.println("Enter City:");
		 String city = sc.nextLine();
		 System.out.println("Enter Starting Date");
		 String dt = sc.nextLine();
		 System.out.println("Enter Ending Date:");
		 String dte = sc.nextLine();
		 System.out.println("Enter mem type");
		 String type = sc.nextLine();
		 
		 String sql = "insert into LMS_MEMBERS "
					+ " (MEMBER_ID, MEMBER_NAME, CITY, DATE_REGISTER, DATE_EXPIRE,MEMBERSHIP_STATUS)" + " values (?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			// set param values
			st.setString(1,id);
			st.setString(2, nm);
			st.setString(3, city);
			st.setString(4, dt);
			st.setString(5, dte);
			st.setString(6, type);
			
			// 3. Execute SQL query
			int r = st.executeUpdate();
			System.out.println(r);
			con.close();
			
		 
	}
	
	public static void updateUser() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		 System.out.println("Enter id: ");
		 String id = sc.nextLine();
		 System.out.println("Enter Name:");
		 String nm = sc.nextLine();
		 System.out.println("Enter City:");
		 String city = sc.nextLine();
		 System.out.println("Enter Starting Date");
		 String dt = sc.nextLine();
		 System.out.println("Enter Ending Date:");
		 String dte = sc.nextLine();
		 System.out.println("Enter mem type");
		 String type = sc.nextLine();
		 
		 String sql = "update LMS_MEMBERS set MEMBER_NAME=?,CITY=?, DATE_REGISTER=?,DATE_EXPIRE=?,MEMBERSHIP_STATUS=? where MEMBER_ID=?";
					
			PreparedStatement st = con.prepareStatement(sql);
			// set param values
			st.setString(6,id);
			st.setString(1, nm);
			st.setString(2, city);
			st.setString(3, dt);
			st.setString(4, dte);
			st.setString(5, type);
			
			// 3. Execute SQL query
			int r = st.executeUpdate();
			if(r>0) {
				System.out.println("Updated succesfully!");
			}
			con.close();
			
		 
	}
	
	public static boolean isValid(String uid,String pswd)throws ClassNotFoundException ,SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");  
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/libms","root","Nishanth");
		PreparedStatement pst=con.prepareStatement("select * from LMS_VAL where MEMBER_ID=? and PASSWORD=?");
		pst.setString(1, uid);
		pst.setString(2, pswd);
    	ResultSet rs=pst.executeQuery();
    	if(rs.next()) {return true;}
    	return false;
	}
	
	public static void getbooks() throws SQLException {
		
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		 System.out.println("Enter Category: ");
		 String mid = sc.next();
		 
		 String sql = "select BOOK_TITLE from LMS_BOOK_DETAILS where CATEGORY=?";
			PreparedStatement st = con.prepareStatement(sql);
			// set param values
			st.setString(1,mid);
			
			
			// 3. Execute SQL query
			ResultSet rs = st.executeQuery();
			while(rs.next()) 
			{
		
			System.out.print(rs.getString("BOOK_TITLE") + "\t\t");
			
			System.out.println();
			}
			
			con.close();
		 
	}
public static void getmem() throws SQLException {
		
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		
		 
		 String sql = "select MEMBER_NAME from LMS_MEMBERS where DATE_REGISTER like '2020%' ";
			PreparedStatement st = con.prepareStatement(sql);
			
			
			
			
			// 3. Execute SQL query
			ResultSet rs = st.executeQuery();
			while(rs.next()) 
			{
		
			System.out.print(rs.getString(1) + "\t\t");
			
			System.out.println();
			}
			
			con.close();
		 
	}
	

	
	public static void deleteUser() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		 System.out.println("Enter id: ");
		 String mid = sc.next();
		 
		 String sql = "delete from LMS_MEMBERS where MEMBER_ID=?";
			PreparedStatement st = con.prepareStatement(sql);
			// set param values
			st.setString(1,mid);
			
			
			// 3. Execute SQL query
			int r = st.executeUpdate();
			if(r>0) {
				System.out.println("Deleted succesfully");
			}
			con.close();
			
		 
	}
	
	public static void addStock() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libms","root","Nishanth");
		 System.out.println("Enter BOOK CODE: ");
		 String id = sc.next();
		 System.out.println("Enter STOCK");
		 int type = sc.nextInt();
		 PreparedStatement pst=con.prepareStatement("select STOCK from LMS_BOOK_DETAILS where BOOK_CODE=?");
			pst.setString(1, id);
			ResultSet rs=pst.executeQuery();
			String temp="";
			while(rs.next())
			{
				temp=rs.getString(1);
			}
			int num=Integer.valueOf(temp);
			num+=type;
			 PreparedStatement pt=con.prepareStatement("update LMS_BOOK_DETAILS set STOCK=? where BOOK_CODE=?");
             pt.setInt(1,num);
             pt.setString(2,id);
             pt.executeUpdate();
			con.close();
	}
	
	public static boolean updateBorrowStock(String BID) throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/libms","root","Nishanth");

		 
		 PreparedStatement pst=con.prepareStatement("select STOCK from LMS_BOOK_DETAILS where BOOK_CODE=?");
			pst.setString(1, BID);
			ResultSet rs=pst.executeQuery();
			String temp="";
			while(rs.next())
			{
				temp=rs.getString(1);
			}
			int num=Integer.valueOf(temp);
			if(num>0) {
			num-=1;
			 PreparedStatement pt=con.prepareStatement("update LMS_BOOK_DETAILS set STOCK=? where BOOK_CODE=?");
             pt.setInt(1,num);
             pt.setString(2,BID);
             pt.executeUpdate();
             return true;
			}
			else {
			
				return false;
			}
			
	}
	
	public static void addBook() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		 System.out.println("Enter Book id: ");
		 String id = sc.nextLine();
		 System.out.println("Enter Book Title:");
		 String nm = sc.nextLine();
		 System.out.println("Enter Category:");
		 String city = sc.nextLine();
		 System.out.println("Enter Author");
		 String dt = sc.nextLine();
		
		 System.out.println("Enter SupplierID");
		 String type = sc.nextLine();
		 System.out.println("Enter Stock");
		 int stk = sc.nextInt();
		 
		 String sql = "insert into LMS_BOOK_DETAILS "
					+ " (BOOK_CODE, BOOK_TITLE, CATEGORY, AUTHOR, PRICE, SUPPLIER_ID,STOCK)" + " values (?,?,?,?,500.00,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
	
			st.setString(1,id);
			st.setString(2, nm);
			st.setString(3, city);
			st.setString(4, dt);
			
			st.setString(5, type);
			st.setInt(6, stk);
			
			// 3. Execute SQL query
			int r = st.executeUpdate();
			if(r>0) {
				System.out.println("Added Succesfully");
			}
			con.close();
			
		 
	}
	
	public static void deleteBook() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		 System.out.println("Enter id: ");
		 String mid = sc.next();
		 
		 String sql = "delete from LMS_BOOK_DETAILS where BOOK_CODE=?";
			PreparedStatement st = con.prepareStatement(sql);
			// set param values
			st.setString(1,mid);
			
			
			// 3. Execute SQL query
			int r = st.executeUpdate();
			if(r>0) {
				System.out.println("Deleted succesfully");
			}
			con.close();
			
		 
	}
	
	public static void borrowBook() throws SQLException{
		Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		Scanner sc = new Scanner(System.in);
		System.out.println("enter book issue id");
        String s1=sc.nextLine();
        System.out.println("enter member id");
        String s2=sc.nextLine();
        System.out.println("enter book id");
        String s3=sc.nextLine();
        boolean a = updateBorrowStock(s3);
        if(a) {
	    System.out.println("enter todays date");
	    String s4=sc.nextLine();
	    System.out.println("enter date to be returned");
	    String s5=sc.nextLine();
	    
	    System.out.println("Fine range");
	    String s7= sc.nextLine();
	    
		
		String sql = "insert into LMS_BOOK_ISSUE(BOOK_ISSUE_NO, MEMBER_ID, BOOK_CODE, DATE_ISSUE, DATE_RETURN, FINE_RANGE) values(?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		// set param values
		st.setString(1,s1);
		st.setString(2, s2);
		st.setString(3, s3);
		st.setString(4, s4);
		st.setString(5, s5);
		
		st.setString(6, s7);
		
		// 3. Execute SQL query
		int r = st.executeUpdate();
		if(r>0) {
			System.out.println("Borrowed succesfully!");
		}
		st.close();
		con.close();
        }
        else {
        	System.out.println("Book Not Available");
        }
	}
	
	public static void returnBook() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		 System.out.println("Enter Issueid: ");
		 String mid = sc.next();
		 
		 String sql = "delete from LMS_BOOK_ISSUE where BOOK_ISSUE_NO=?";
			PreparedStatement st = con.prepareStatement(sql);
			// set param values
			st.setString(1,mid);
			
			
			// 3. Execute SQL query
			int r = st.executeUpdate();
			if(r>0) {
				System.out.println("Returned  succesfully");
			}
			con.close();
			
		 
	}
	
	public static void calFine() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		
		 
		 String sql = "SELECT BOOK_ISSUE_NO, DATEDIFF(CURDATE(),DATE_RETURN) as FINE  FROM LMS_BOOK_ISSUE;";
			PreparedStatement st = con.prepareStatement(sql);
			// set param values
			
			
			
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				System.out.print("IssueNo:" + rs.getString("BOOK_ISSUE_NO"));
				System.out.println("Rs" + rs.getString("FINE"));
			}
			
			con.close();
			
		 
	}
	
	public static void viewBook() throws SQLException {
		Scanner sc = new Scanner(System.in);
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/libms","root","Nishanth" );
		
		 
		 String sql = "select * from LMS_BOOK_DETAILS";
			PreparedStatement st = con.prepareStatement(sql);
			
			// set param values
			ResultSet rs = st.executeQuery(sql);
			System.out.println("Result of executing query3 to display updated records");
			System.out.println("ID " + "\t\t" + "Title\t\t\t" +  "\tCategory\t" +  "\tAuthor\t" + "\tPrice\t" + "\tSupplierID");

			//looping through the number of row/rows retrieved after executing SELECT query3
			while(rs.next()) 
			{
			System.out.print(rs.getString("BOOK_CODE") + "\t");
			System.out.print(rs.getString("BOOK_TITLE") + "\t\t");
			System.out.print(rs.getString("CATEGORY")+ "\t" +  "\t");
			System.out.print(rs.getString("AUTHOR") + "\t\t");
			System.out.print(rs.getFloat("PRICE") + "\t\t");
			System.out.println(rs.getString("SUPPLIER_ID") + "\t");
			System.out.println("Stock available:" + rs.getInt("STOCK"));
			}
			
			con.close();
			
		 
	}
	
	


	
	static String admpwd = "asdf";
	
	public static void main(String[] args)throws ClassNotFoundException ,SQLException{
		
		 welcome();
		 Scanner sc = new Scanner(System.in);
		 System.out.println("Enter Username");
		String nm = sc.nextLine();
		System.out.println("Enter password");
		String pwd = sc.nextLine();
		
		if(isValid(nm,pwd)) {
		 
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db_pro","root","Nishanth" );
		System.out.println("Connection Established");
		Class.forName("com.mysql.cj.jdbc.Driver");
		 Statement stmt = con.createStatement( );
         if(pwd.equalsIgnoreCase(admpwd)){
        	 System.out.println("Succesfully logged in as Admin!");
        	 while (true)    // Way to Admin Portal
             {
         

                 System.out.println("--------------------------------------------------------");
                 System.out.println("\tWelcome Admin");
                 System.out.println("--------------------------------------------------------");
                 System.out.println("Choose a task to perform: \n");

                 System.out.println("\t1- Add User");
                 System.out.println("\t2-  Update User Details"); 
                 System.out.println("\t3- Delete User");  
                 System.out.println("\t4- Add Book Details"); 
                 System.out.println("\t5- Delete Book Details");
                 System.out.println("\t6-View Book Details");
                 System.out.println("\t7- Get Members of Batch 2020");
                 System.out.println("\t8- Add Stock");
                 System.out.println("\t9-Exit");

                 System.out.println("---------------------------------------------");

                 int choice = sc.nextInt();

                 if (choice == 9)
                     break;

                 if (choice == 1) {
                     System.out.println("Add User");
                     addUser();
                 }
                 else if (choice == 2) {
                     System.out.println("Update user");
                     updateUser();
                 }
                 else if (choice == 3) {
                     System.out.println("Delete user");
                     deleteUser();
                 }

                 else if (choice == 4) {
                     System.out.println("Add Book Details");
                     addBook();
                 }
                 else if(choice == 5)
                 {
                	 System.out.println("Delete book details");
                	 deleteBook();
                 }
                 else if(choice == 6)
                 {
                	 System.out.println("View Book Details");
                	 viewBook();
                 }
                 else if(choice==7) {
                	 System.out.println("Members of this batch:");
                	 getmem();
                 }
                 else if(choice==8) {
                	 System.out.println("Adding Stock");
                	 addStock();
                 }
                                       
             }
         }
         else {
        	 System.out.println("Succesfully logged in as User!");
        	 while (true)    // Way to Admin Portal
             {
         

                 System.out.println("--------------------------------------------------------");
                 System.out.println("\tWelcome User");
                 System.out.println("--------------------------------------------------------");
                 System.out.println("Choose a task to perform: \n");

                 System.out.println("\t1- View Books");
                 System.out.println("\t2-  Borrow Book"); 
                 System.out.println("\t3- View Books by category");  
                 System.out.println("\t4- ReturnBook"); 
                 System.out.println("\t5- CalculateFine");
                 System.out.println("\t6-Exit");

                 System.out.println("---------------------------------------------");

                 int choice = sc.nextInt();

                 if (choice == 6)
                     break;

                 if (choice == 1) {
                     System.out.println("View Books");
                     viewBook();
                 }
                 else if (choice == 2) {
                     System.out.println("Borrow book");
                     borrowBook();
                 }
                 else if (choice == 3) {
                     System.out.println("Books by Category:");
                     getbooks();
                 }

                 else if (choice == 4) {
                     System.out.println("Return Book");
                     returnBook();
                 }
                 else if(choice == 5)
                 {
                	 System.out.println("Calculating fine");
                	 System.out.println("1 RS per extra day");
                	 calFine();
                 }
                
                                       
             }
        	 
         }
		}
		else {
			System.out.println("Invalid credentials!");
		}
		
		
		// TODO Auto-generated method stub
		/*Class.forName("com.mysql.jdbc.Driver");  
		 Connection con = DriverManager.getConnection( "jdbc:mysql://localhost:3306/db_pro","root","Nishanth" );
		System.out.println("Connection Established");
		Statement st=con.createStatement();
		
		
		System.out.println( rows+ "No of rows effected");
		con.close();
		System.out.println("connection closed");*/
	}

}
