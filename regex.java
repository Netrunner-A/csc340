import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.*;

public class regex {
	public static void main(String[] args) {
		
//		String text = "This generational and chronological list of Intel processors attempts to present all of Intel's\r\n" + 
//		"processors from the pioneering 4-bit 4004(1971) to the present high-end offerings, which include\r\n" + 
//		"the 64-bit Itanium 2 (1999), Intel Core i7, and Xeon E3 and E5 series processors (2015). Concise \r\n" + 
//		"technical data are given for each product. This text will be sent to k@ksu.edu.sa and \r\n" + 
//		"sss@ksu.edu.sa.";
		
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get("test.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		regexQ1(text);
		regexQ2(text);
		regexQ3(text);
		regexQ4(text);
		regexQ5(text);
		regexQ6(text);
	}
	
//	1. The number of words that are ended with "ies". 
	private static void regexQ1(String text) {
		String regex1 = "[A-Z]*[a-z]*ies";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(text);
		
		int counter = 0;
		while(matcher1.find())
			counter++;
		
		System.out.println("The number of words that are ended with \"ies\" is: " + counter);
	}
	
//	2. The list of KSU email addresses in the text
	private static void regexQ2(String text) {
		String regex2 = "[A-Z0-9a-z]*@ksu.edu.sa";
		Pattern pattern2 = Pattern.compile(regex2);
		Matcher matcher2 = pattern2.matcher(text);
		
		String emails = "";
		while(matcher2.find()) {
			if(!emails.isEmpty())
				emails += ", ";
			emails += matcher2.group();
		}
		
		System.out.println("The list of ksu email addresses are: " + emails);
	}
//	3. The number of lines within the file. 
	private static void regexQ3(String text) {

		String regex3 = "\\n";
		Pattern pattern3 = Pattern.compile(regex3);
		Matcher matcher3 = pattern3.matcher(text);
		
		int counter = 0;
		while(matcher3.find())
			counter++;
		
		System.out.println("The number of lines: " + (counter+1));
		
	}
	private static void regexQ4(String text) {
//		4. Print all years mentioned in the text that precede 2000.
		String regex4 = "1[0-9]{3}";
		Pattern pattern4 = Pattern.compile(regex4);
		Matcher matcher4 = pattern4.matcher(text);
		
		String years = "";
		while(matcher4.find()) {
			if(!years.isEmpty())
				years += ", ";
			years += matcher4.group();
		}
		
		System.out.println("The years before 2000 are: " + years);
		
	}
	private static void regexQ5(String text) {
//		5. Print all words that are preceded by a or A. e.g. a car, A girl
		String regex5 = "\\s[Aa]\\s[A-Za-z]*";
		Pattern pattern5 = Pattern.compile(regex5);
		Matcher matcher5 = pattern5.matcher(text);
		
		String words = "";
		int counter = 0;
		while(matcher5.find()) {
			counter++;
			if(!words.isEmpty())
				words += ", ";
			words += text.substring(matcher5.start()+3, matcher5.end());
		}
		if(counter == 0)
			System.out.println("No words preceded by a or A");
		else
			System.out.println("The words preceded by A or a are: " + words);
		
	}
	private static void regexQ6(String text) {
//		6. Replace all lowercase letters with uppercase letters and store the result in an output
//		file.
		
		String regex6 = "[a-z]*";
		Pattern pattern6 = Pattern.compile(regex6);
		Matcher matcher6 = pattern6.matcher(text);
		String output = "";
		int counter = 0;
		while(matcher6.find()) {
			output += text.substring(counter, matcher6.start());
			output += matcher6.group().toUpperCase();
			counter = matcher6.end();
		}
//		System.out.println(output);
		
		try {
			FileWriter writer = new FileWriter("output.txt");
			writer.write(output);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
