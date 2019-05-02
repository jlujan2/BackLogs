import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import edu.duke.FileResource;

public class Test {

	public static void main(String[] args) {
		LogAnalyzer la = new LogAnalyzer();
		FileResource fr = new FileResource("C:\\Users\\v52197\\Desktop\\Java-class-udemy-201\\Week3\\src\\weblog2_log");
		la.readFile(fr);
		//la.printAll();
		//la. printAllHigherThanNum(400);
		System.out.println("countUniqueIPs: " +la.countUniqueIPs());
		System.out.println("countUniqueIPsInRange: " +la.countUniqueIPsInRange(400,499));
		System.out.println("UniqueVisits Sep 27: " +la.uniqueIPVisitsOnDay("Sep 27").size());
		HashMap<String, Integer> hm = la.countVisitsPerIp();
		System.out.println(hm);
		System.out.println(la.iPsMostVisits(hm));
		
		HashMap<String, Integer> hm2 = la.countVisitsPerIp();
		System.out.println("iPsMostVisits" +la.iPsMostVisits(hm2));
		System.out.println("mostNumberVisitsByIP " +la.mostNumberVisitsByIP(hm2));
		
		HashMap<String, ArrayList<String>> hm3 = la.iPsForDays();
		System.out.println(hm3);
		System.out.println(la.dayWithMostIPVisits(hm3));
	
	}

}
