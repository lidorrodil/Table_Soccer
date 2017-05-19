package userInterface;
import static java.nio.file.StandardCopyOption.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class copyFiles {
	// PrintWriter writer = new PrintWriter("another.txt", "UTF-8");
	//FileWriter writer = null;
	// BufferedReader br = new BufferedReader(new FileReader("file.txt"));
	//String line;

	public copyFiles()  {
		//writer = new FileWriter(new File("sortByAge.txt"), true);
	}

	public void sortByAge() throws IOException {
		Map<Integer, List> map=new HashMap<Integer, List>();
	    BufferedReader reader = new BufferedReader(new FileReader("./database/file.txt"));
	      
	    //reader.reset();
	    //reader.
	    //System.out.println("Check: "+reader.readLine());
	    List<String> values = new ArrayList<String>();
	    String line;
	    String condition;
	    while((line = reader.readLine())!=null){
	    	values = Arrays.asList(line, reader.readLine(),reader.readLine() , (condition=reader.readLine()),
        			reader.readLine(), reader.readLine(), reader.readLine(),reader.readLine(),
        			reader.readLine(),reader.readLine());
	    	//System.out.println(values.get(0));
	    	int replace=Integer.parseInt(condition);
	    	// the condition of sorting
	    	while (map.containsKey(replace)){
	    		replace++;
	    	}
	    	map.put(replace, values);
	    	//System.out.println("Example age: "+age);
	    }
	    
	    
	    
	    
	    Map<Integer, List> treeMap = new TreeMap<Integer, List>(
                new Comparator<Integer>() {

                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }

                });
	    
	    treeMap.putAll(map);
	//    System.out.println("Treee: "+treeMap);
	    //Collections.sort(rows);
	   // FileWriter writer = new FileWriter(new File("sortAge.txt"), true);
	    FileWriter writer = new FileWriter(new File("./database/sortAge.txt"));
	    for(Integer val : treeMap.keySet()){
	        writer.write((String)treeMap.get(val).get(0)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(1)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(2)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(3)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(4)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(5)); writer.write(System.getProperty( "line.separator" ));
	    }
	    reader.close();
	    //writer.flush();
	    writer.close();
	    
	
	}
	
	public void sortByRank() throws IOException {
		Map<Integer, List> map=new HashMap<Integer, List>();
	    BufferedReader reader = new BufferedReader(new FileReader("./database/file.txt"));
	      
	    //reader.reset();
	    //reader.
	    //System.out.println("Check: "+reader.readLine());
	    List<String> values = new ArrayList<String>();
	    String line;
	    String condition;
		    while((line = reader.readLine())!=null){
		    	values = Arrays.asList(line, reader.readLine(), reader.readLine(),
	        			reader.readLine(), (condition = reader.readLine()), reader.readLine());

		    	int replace=Integer.parseInt(condition);
		    	// the condition of sorting
		    	while (map.containsKey(replace)){
		    		replace++;
		    	}
		    	map.put(replace, values);
		    	//System.out.println("Example age: "+age);
		    }
		    

	    Map<Integer, List> treeMap = new TreeMap<Integer, List>(
                new Comparator<Integer>() {

                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o2.compareTo(o1);
                    }

                });
	    
	    treeMap.putAll(map);
	//    System.out.println("Treee: "+treeMap);
	    //Collections.sort(rows);
	    FileWriter writer = new FileWriter("./database/sortRank.txt");
	    for(Integer val : treeMap.keySet()){
	        writer.write((String)treeMap.get(val).get(0)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(1)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(2)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(3)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(4)); writer.write(System.getProperty( "line.separator" ));
	        writer.write((String)treeMap.get(val).get(5)); writer.write(System.getProperty( "line.separator" ));
	    }
	    reader.close();
	    writer.close();
		

	}
	
	
	
}
