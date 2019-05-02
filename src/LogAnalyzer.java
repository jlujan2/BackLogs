import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.duke.FileResource;

import java.lang.*;

public class LogAnalyzer {
	private ArrayList<LogEntry> records;
	private WebLogParser wlp = new WebLogParser();
	
	LogAnalyzer() {
		this.records = new ArrayList<LogEntry>();
	}
	
	public void readFile(FileResource file) {
		LogEntry le;
		for(String s:file.lines()) {
			le = this.wlp.parseEntry(s);
			this.records.add(le);
		}
	}
	
	public void printAll() {
		System.out.println(this.records.size());
		for(LogEntry le:this.records) {
			System.out.println(le.printLog());
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int countUniqueIPs() {
		Set set = new HashSet();
		for(LogEntry le:this.records) {
			set.add(le.getIpAddress());
		}
		return set.size();
	}
	
	public void printAllHigherThanNum(int num) {
		for(LogEntry le:this.records) {
			if(le.getStatusCode()>num)
				System.out.println(le.printLog());
		}
	}
	
	public ArrayList<LogEntry> uniqueIPVisitsOnDay(String someday) {
		ArrayList<LogEntry> result = new ArrayList<LogEntry>();
		Set set = new HashSet();
		String month = someday.substring(0, 3);
		String day = someday.substring(4, 6);
		for(LogEntry le:this.records) {
			if(month.equals(le.getAccessTime().toString().substring(4, 7))&&
					day.equals(le.getAccessTime().toString().substring(8, 10))) {
				if(set.add(le.getIpAddress())) {
					result.add(le);
				}
			}
			
		}
		
		return result;
	}
	
	public int countUniqueIPsInRange(int low, int high) {
		Set set = new HashSet();
		for(LogEntry le:this.records) {
			if(le.getStatusCode()>=low && le.getStatusCode()<=high) 
				set.add(le.getIpAddress());
		}
		return set.size();
	}
	
	public HashMap<String, Integer> countVisitsPerIp() {
		HashMap<String, Integer> counts = new HashMap<String, Integer>();
		for(LogEntry le: this.records) {
			String ip = le.getIpAddress();
			if(!counts.containsKey(ip)) {
				counts.put(ip, 1);
			}
			else {
				counts.put(ip, counts.get(ip)+1);
			}
		}
		return counts;
	}
	
	public int mostNumberVisitsByIP(HashMap<String, Integer> hm) {
		int []values = new int[hm.size()];
		int i=0;
		for(String val:hm.keySet()) {
			values[i] = hm.get(val);
			i++;
		}
		Arrays.sort(values);
		return values[hm.size()-1];
	}
	
	public ArrayList<String> iPsMostVisits(HashMap<String, Integer> hm) {
		ArrayList<String> result = new ArrayList<String>();
		int []values = new int[hm.size()];
		int i=0;
		for(String val:hm.keySet()) {
			values[i] = hm.get(val);
			i++;
		}
		Arrays.sort(values);
		int maxResult1 = values[hm.size()-2];
		int maxResult2 = values[hm.size()-1];
		System.out.println(maxResult1 +" " +maxResult2);
		i=0;
		for(String val:hm.keySet()) {
			if(i==2)
				break;
			if(maxResult1==hm.get(val)) {
				result.add(val);
				hm.put(val, -1);
				i++;
			}
			if(maxResult2==hm.get(val)) {
				result.add(val);
				hm.put(val, -1);
				i++;
			}
		}
		
		return result;
	}
	
	public HashMap<String, ArrayList<String>> iPsForDays() {
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		for(int i=0; i < this.records.size(); i++) {
			ArrayList<String> al = new ArrayList<String>();
			LogEntry temp = this.records.get(i);
			al.add(temp.getIpAddress());
			this.records.remove(i);
			for(int j=0; j<this.records.size();j++) {
				if(temp.getAccessTime().equals(this.records.get(j).getAccessTime() ) ) {
					al.add(this.records.get(j).getIpAddress());
					this.records.remove(j);
				}
			}
			result.put(temp.getAccessTime().toLocaleString().substring(0,6), al);
		}
		
		return result;
	}
	
	public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> hm) {
		String s = "";
		int i=0;
		for(String key:hm.keySet()) {
			if(hm.get(key).size()>i) {
				i = hm.get(key).size();
				s = key;
			}
		}
		return s;
	}
	
	public ArrayList<LogEntry> getRecords() {
		return this.records;
	}

	public void setRecords(ArrayList<LogEntry> records) {
		this.records = records;
	}
	
	
}
