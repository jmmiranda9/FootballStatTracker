import java.util.Scanner;
import java.io.*;
public class test {

	public static void main(String[] args)throws FileNotFoundException  
	{
		Scanner in = new Scanner(new File("CityNames.txt"));
		while(in.hasNext())
		{
			String str = in.nextLine();
			System.out.println(str);
		}
		in.close();

	}

}
