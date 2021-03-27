
import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class practiceA implements dbi {
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		boolean dec = true;
		while (dec) {
			System.out.print(
					"Welcome to the NFL database manager! Please select an action from below: \n1-Add record\n2-Delete all records\n3-Display a Query\n4-Exit\nYour Action: ");
			int decision = inp.nextInt();
			while (decision < 1 || decision > 4) {
				System.out.print("Please select a valid option : ");
				decision = inp.nextInt();
			}
			if (decision == 1) {
				System.out.print(
						"How would you like to add the data?:\n1-Single entry\n2-Multiple entry(using .txt)\n3-Random entry\nYour Action: ");
				int decision2 = inp.nextInt();
				while (decision < 1 || decision > 3) {
					System.out.println("Please select a valid option :");
					decision = inp.nextInt();
				}
				if (decision2 == 1)
					addsingle();
				else if (decision2 == 2)
					add();
				else
					random();
			} else if (decision == 2) {
				remove();
			} else if (decision == 3) {
				System.out.println(
				"Display a table"+
				"\n2.)Display the wins and losses of all teams in their respected division sorted by wins in descending order."+
				"\n3.)Display the top teams in every conference that have 15 wins"+
				"\n4.) Find the the average stadium revenue and wins of the top 16 teams and bottom 16 in the nfl this season ."+
				"\n5.)Find and display all matches by ID on a specific day and also display names of teams participating."+
				"\n6.)Find and display name and years of experience of the referee directing the match between team X and Team Y ."+
				"\n7.)Find the number of teams with X+ losses and display their names and average betting odds."+
				"\n8.)Find the average flags thrown by all nfl referees"+
				"\n9.) Display the names and salaries of nfl team captains with the highest Salaries in their respective division ."+
				"\n10.)Display the sunny days in x conference in a season."+
				"\n22.)The NFL wants to research if there is a significant effect that location team's performance. Find the average wins for both the nfc and afc-north,east,west and south divisions.");

				System.out.print("Your selection: ");
				int chs = inp.nextInt();
				while (chs < 1 || chs > 11) {
					System.out.println("Please select a valid option :");
					chs = inp.nextInt();
				}
				switch (chs) {
				case 1: {
					q1();
					break;
				}
				case 2: {
					q2();
					break;
				}
				case 3: {
					q3();
					break;
				}
				case 4: {
					q4();
					break;
				}
				case 5: {
					q5();
					break;
				}
				case 6: {
					q6();
					break;
				}
				case 7: {
					q7();
					break;
				}
				case 8: {
					q8();
					break;
				}
				case 9: {
					q9();
					break;
				}
				case 10: {
					q10();
					break;
				}
				case 11: {
					q11();
					break;
				}

				}

			} else {
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nGoodbye!");
				dec = false;
			}
		}
	}

	public static void add() {
		try {
			// Connection con=
			// DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Scanner scan = new Scanner(System.in);

			System.out.print("Which enter the name of the txt file(with.txt): ");
			String dat = scan.nextLine();

			File file = new File(dat);

			Scanner sc = new Scanner(file);
			System.out.print("Which table would you like to add to: ");
			dat = scan.nextLine();
			while (!(dat.equals("nfl_teams") || dat.equalsIgnoreCase("stadiums") || dat.equalsIgnoreCase("matches")
					|| dat.equalsIgnoreCase("referees") || dat.equalsIgnoreCase("captains"))) {
				System.out.print("Enter a valid table name: ");
				dat = scan.nextLine();
			}
			while (sc.hasNextLine()) {
				String str = sc.nextLine();
				String[] cont = str.split(",");

				switch (dat) 
				{

				case "nfl_teams": 
				{
					Statement stmt = conn.createStatement();
					String sql = "Insert Into nfl_teams values('" + cont[0] + "','" + cont[1] + "','" + cont[2] + "','"
							+ cont[3] + "','" + cont[4] + "','" + cont[5] + "','" + cont[6] + "')";
					Boolean ret = stmt.execute(sql);
					
					break;
				}
				case "stadiums": 
				{
					Statement stmt = conn.createStatement();
					String sql = "Insert Into stadiums values('" + cont[0] + "','" + cont[1] + "','" + cont[2] + "','"
							+ cont[3] + "','" + cont[4] + "','" + cont[5] + "')";
					System.out.println("test") ;
					Boolean ret = stmt.execute(sql);
					
					break;
				}
				case "matches": 
				{
					Statement stmt = conn.createStatement();
					String sql = "Insert Into matches values('" + cont[0] + "','" + cont[1] + "','" + cont[2] + "','"
							+ cont[3] + "','" + cont[4] + "','" + cont[5] + "')";
					Boolean ret = stmt.execute(sql);
					break;
				}
				case "referees": 
				{
					Statement stmt = conn.createStatement();
					String sql = "Insert Into referees values('" + cont[0] + "','" + cont[1] + "','" + cont[2] + "','"
							+ cont[3] + "','" + cont[4] + "','" + cont[5] + "')";
					
					System.out.println(cont[0]+ cont[1] +cont[2]+cont[3]+cont[4]+cont[5]);
					
					Boolean ret = stmt.execute(sql);
					break;
				}
				case "captains": 
				{
					Statement stmt = conn.createStatement();
					String sql = "Insert Into captains values('" + cont[0] + "','" + cont[1] + "','" + cont[2] + "','"
							+ cont[3] + "')";
					System.out.println( cont[0] + "," + cont[1] + "," + cont[2] + ","
							+ cont[3]);
					System.out.println("\nThe above queries have been added");
					Boolean ret = stmt.execute(sql);
					break;
				}

				}
				
				
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void addsingle() {
		try {
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Scanner scan = new Scanner(System.in);
			System.out.print("Which table would you like to add to: ");
			String dat = scan.nextLine();
			System.out.print(
					"Please write the data inside brackets, with commas in between(Data must match schema format: ");
			String str = scan.nextLine();
			Statement stmt = conn.createStatement();
			String sql = "Insert Into " + dat + " values(" + str + ")";
			Boolean ret = stmt.execute(sql);

			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void remove() {
		try {
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
      
			String sql = "TRUNCATE referees;" ;
      int ret = stmt.executeUpdate(sql);
			
      String sql1 = "TRUNCATE nfl_teams;" ;
      ret = stmt.executeUpdate(sql1);
      
      String sql2 = "TRUNCATE stadiums;" ;
      ret = stmt.executeUpdate(sql2);
      
      String sql3 = "TRUNCATE matches;" ;
       ret = stmt.executeUpdate(sql3);
      
      String sql4 = "TRUNCATE captains;" ;
       ret = stmt.executeUpdate(sql4);
      
       System.out.println("\n tables deleted \n");
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void q1() 
	  {
			try {
				Scanner scan = new Scanner(System.in);
				Connection conn = getConnection();
				Statement stmt = conn.createStatement();
				System.out.print("Which table would you like to use: ");
				String dat = scan.nextLine();                             //table input
				ResultSet rs = stmt.executeQuery("select * from " + dat);
				if(rs.next())
	      { 
	      	do{
	        switch(dat)
	        {
	        		case "nfl_teams":
	          		System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7));
								break;
	          	case"captains":
	          		System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4));
								break;
	        		case "matches":
	          		System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6));
								break;
	          	case "referees":
	          		System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6));
							break;
	          	case "stadiums":
	        			System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6));
								break;
	        	}
	        }while(rs.next());
				}
				else{
					System.out.println("Record Not Found...");
				}
				conn.close();
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("Operation failed. See above for details...");
			}
		}


	public static void q2() {
		try {
			Scanner scan = new Scanner(System.in);
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			System.out.print("GENERATING RESULTS");
			ResultSet rs = stmt.executeQuery(
					"select wins,losses,team_name,division\r\n" + 
					"from nfl_teams \r\n" + 
					" order by \r\n" + 
					" division desc,\r\n" + 
					" wins desc");
			while (rs.next()) {
				

				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void q3() {
		try {
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select team_name,wins from nfl_teams where wins = (select max(wins)from nfl_teams)group by division");
			while (rs.next()) {
				

				System.out.println(rs.getString(1)+","+rs.getString(2));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void q4() {
		try {
			Scanner scan = new Scanner(System.in);
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select avg(w.revenue_generated) as average_high,avg(x.revenue_generated) as average_low\r\n" + 
					"from (select (revenue_generated)\r\n" + 
					"from nfl_teams as n,stadiums as s\r\n" + 
					"where s.primary_team = n.team_name\r\n" + 
					"order by wins desc limit 16) as w,(select (revenue_generated)\r\n" + 
					"from nfl_teams as n,stadiums as s\r\n" + 
					"where s.primary_team = n.team_name\r\n" + 
					"order by wins asc limit 16) as x;");
			while (rs.next()) {
				
				System.out.println("AVG HIGH"+"       AVG LOW");
				System.out.println(rs.getString(1)+","+rs.getString(2));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void q5() {
		try {
			Scanner scan = new Scanner(System.in);
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			System.out.print("Please enter a valid date: ");
			String dat = scan.nextLine();
			ResultSet rs = stmt.executeQuery(
					"select t.team_name,m.dates,m.m_id from nfl_teams AS t, matches AS m where m.team_name=t.team_name AND m.dates='"
							+ dat + "'");
			while (rs.next()) {
				int mid = rs.getInt("m.m_id");
				String tname = rs.getString("t.team_name");
				String mdate = rs.getString("m.dates");

				System.out.println(mid + " " + tname + " " + mdate);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void q6() {
		try {
			Scanner scan = new Scanner(System.in);
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			System.out.print("Please write the names of 2 teams competing: ");
			String dat = scan.nextLine();
			String dat2 = scan.nextLine();
			ResultSet rs = stmt.executeQuery("select r.name_,r.years_of_xp from referees AS r, matches AS m where r.r_id=m.r_id AND m.team_name='Albuquerque Canid'union select r.name_,r.years_of_xp from referees AS r, matches AS m where r.r_id=m.r_id AND m.team_name='Detroit African leopard';");

			while (rs.next()) {

				System.out.println(rs.getString(1)+","+rs.getString(2));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void q7() {
		try {
			Scanner scan = new Scanner(System.in);
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			System.out.print("Please specify the amount of minimum losses: ");
			String dat = scan.nextLine();
			ResultSet rs = stmt.executeQuery(
					"select count(t.team_name) as teamNames,avg(m.betting_odds) as Average_Betting_Odds from nfl_teams AS t, matches AS m where t.team_name=m.team_name AND t.losses >= "
							+ dat+";");
			while (rs.next()) {

				System.out.println(rs.getString(1)+","+rs.getString(2));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void q8() {
		try {
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select c.c_name, MAX(c.salary) AS maxsal,n.team_name,n.division\r\n" + 
					"from captains AS c, nfl_teams AS n\r\n" + 
					"where c.t_name = n.team_name\r\n" + 
					"group by n.division;");
			while (rs.next()) {
				

				System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}
	

	public static void q9() {
		try {
			Scanner scan = new Scanner(System.in);
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			System.out.print("GENERATING RESULTS");
			ResultSet rs = stmt.executeQuery("select avg(r.number_of_penalties)from referees AS r");
			while (rs.next()) {
				

				System.out.println(rs.getString(1));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
		
	}

	public static void q10() {
		try {
			System.out.println("please enter confrence");
			Scanner scan = new Scanner(System.in);
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			String kb =scan.next();
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select count('sunny') as " +kb+ " from(select division, weather from nfl_teams as n,stadiums as s,matches as m where n.team_name = s.primary_team and n.team_name = m.team_name) as w where w.division ='" +kb+"';");
			
			while (rs.next()) {
				

				System.out.println(rs.getString(1));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static void q11() {
		try {
//			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/mycompany","root","Password");
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select avg(wins) as AVG_WINS,division from(select division, weather,wins from nfl_teams as n,stadiums as s,matches as m where n.team_name = s.primary_team and n.team_name = m.team_name) as w group by w.division;") ;
			
			while (rs.next()) {
				

				System.out.println(rs.getString(1)+","+rs.getString(2));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Operation failed. See above for details...");
		}
	}

	public static String random()
	{
		String format="";
		System.out.println("POPULATING DATABASE");
		
		int table =0;
		//arrays to be filled with data
		String[]fNames={};
		String[]cities= {};
		String[]animals= {};
		String[] lName = {};
		String[] weather= {"sunny","fog","sleet","snow"};
		
		//arrays for related data
		
		
		
		//
		String str="";
		String[] tableNames= {"captains","matches","nfl_teams","referees","stadiums"};
		//Fills Arrays with txt data
		try {
			Scanner rdr = new Scanner(new File("CityNames.txt"));
			while(rdr.hasNext()){
			str =str +rdr.nextLine()+"!";
			cities=str.split("!");
		}
			rdr = new Scanner(new File("AnimalNames.txt"));
		str="";
		while(rdr.hasNext()){
			str=str+rdr.nextLine()+"!";
			animals=str.split("!");
		}
		rdr = new Scanner(new File("FirstNames.txt"));
		str="";
		while(rdr.hasNext()){
			str=str+rdr.nextLine()+"!";
			fNames=str.split("!");
		}
		rdr = new Scanner(new File("LastName.txt"));
		str="";
		while(rdr.hasNext()){
			str=str+rdr.nextLine()+"!";
			lName=str.split("!");
		}
		
		

		}
		catch(FileNotFoundException fnf){
			System.out.println("the file was not found"+fnf);
		}
		
		try {
		
		//Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db,un,ps);
			Connection conn = getConnection();
			Statement stmt =conn.createStatement();
			String date=((int)(Math.random() * 11) + 1 )+"-"+((int)(Math.random() * 28) + 1)+"-"+"2019";
			String rstadiumName = "'" + lName[0]  + " " + "field" + "'";
			String wt=weather[(int)(Math.random()*4)];
			String rRName="'"+fNames[(int)(Math.random()*100)]+" "+lName[(int)(Math.random()*200)]+"'";
			int yop =((int)(Math.random()*30));
			int z=1;
		for(int i =0;i<300;i++)
		{
			int m_id=i+1;
			int r_id=(i+100);
			int wins = (int)(Math.random() * 16);
            int losses = 16 - wins;
            int draws = 16 - (wins + losses);
			String rCName="'"+fNames[(int)(Math.random()*100)]+" "+lName[(int)(Math.random()*200)]+"'";
			String rCoName="'"+fNames[(int)(Math.random()*100)]+" "+lName[(int)(Math.random()*200)]+"'";
			//String rRName="'"+fNames[(int)(Math.random()*100)]+" "+lName[(int)(Math.random()*200)]+"'";
			String rCity="'"+cities[(int)(Math.random()*100)];
			String rTeam= rCity +" "+ animals[(int)(Math.random()*100)]+"'";
			String[] Div= {"AFC_North","AFC_South","AFC_East","AFC_West","NFC_North","NFC_South","NFC_East","NFC_West"};
			if(m_id%2==0) 
			{
				m_id=m_id-1;
				
			}
			if(z>2) {
				date=((int)(Math.random() * 11) + 1 )+"-"+((int)(Math.random() * 28) + 1)+"-"+"2019";
				rstadiumName = "'" + lName[i]  + " " + "field" + "'";
				wt=weather[(int)(Math.random()*4)];
				rRName="'"+fNames[(int)(Math.random()*100)]+" "+lName[(int)(Math.random()*200)]+"'";
				yop =((int)(Math.random()*30));
				z=1;
			}
			z++;
			
			
			
			for(int j=0;j<5;j++) {
				switch(j) {
				case 0://captains - c_name(varChar), T_name(varChar), Yards_per_match(int),             salary(double)
					format="("+rCName+","+rTeam+",'"+ (70 + (int)(Math.random() * 100)) +"','"+(250000 +(int)(Math.random() *11000000))+"')";
					break;
				case 1://matches - m_id(varChar),r_id(varChar),date(varChar),weather(varChar),betting_odds(double),team_name(varChar)
					format ="('"+m_id+"','"+r_id+"','"+date+"'," +"'"+ wt+"',"+"'"+(int)(Math.random()*100)+"',"+rTeam+")";
					break;

				case 2://nfl_teams - team_name(varChar), coach(varChar), division(varChar), wins(int),    losses(int),draws(int), captain(varChar)
					format ="("+rTeam+","+rCoName+",'"+ Div[(int)(Math.random()*8)] +"','" + wins +"','" + losses+"','"+draws+"',"+rCName+")" ;
					break;
				case 3://referees - r_id(int),  years_of_xp(int),              name(varChar),                                                             games_played(int),                  salary(double),                 number_of_penalties(int)
					format ="('"+(r_id )+"','"+yop+"',"+rRName+",'"+(int)((Math.random()*1000))+"','"+(Math.random()*(1000021))+"','"+((int)(Math.random()*1000))+"')";
					break;
				//case 4://stadiums - stadium_name(varChar), primary_team(varChar), city(varChar),revenue_generated(double),capacity(int),year_built(int) 
					//format ="("+ rstadiumName +",'"+cities[(int)(Math.random()*100)]+" "+ animals[(int)(Math.random()*100)]+"','"+cities[(int)(Math.random()*100)]+"','"+((int)(Math.random()*300000))+"','"+((int)(Math.random()*30000))+"','"+((int)(Math.random()*2000))+"')";
					//break;
				}
				if(j==4 && z==2) {
					
					format ="("+ rstadiumName +","+rTeam+",'"+cities[(int)(Math.random()*100)]+"','"+((int)(Math.random()*300000))+"','"+((int)(Math.random()*30000))+"','"+((int)(Math.random()*2000))+"')";
					String values="Insert Into "+tableNames[j]+" values"+format;
					System.out.println("Insert Into "+tableNames[j]+" values"+format);
					boolean ret = stmt.execute(values);
				}
				else if(j<4) {
				String values="Insert Into "+tableNames[j]+" values"+format;
				System.out.println("Insert Into "+tableNames[j]+" values"+format);
				boolean ret = stmt.execute(values);
				}
				
			
			}
		}
		conn.close();
		}
		catch(  Exception e) {
		System.out.println("SQL CONNECTION FAILED"+e);
		}
		System.out.println("\n random data succesfully generated.\n");
		return "cool";
	}
	public static Connection getConnection() {
		Connection con = null;
		try {
			String url = dtb;
			String admin = adm;
			String password = pwd;

			con = DriverManager.getConnection(url, admin,password);
			System.out.println("Successfull Connection");

		} catch (Exception e) {
			System.out.println("Connection failed");
		} 

		return con;
	}

}
