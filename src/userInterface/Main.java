package userInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception{
		Map<Integer, List> map=new HashMap<Integer, List>();
	    BufferedReader reader = new BufferedReader(new FileReader("database/file2.txt"));
	    
	    List<String> values = new ArrayList<String>();
	    String line;
	    String age;
	    while((line = reader.readLine())!=null){
	    	values = Arrays.asList(line, reader.readLine(), (age=reader.readLine()),
        			reader.readLine(), reader.readLine(), reader.readLine());
	    	
	    	int replace=Integer.parseInt(age);
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
	    for (List val : treeMap.values()){
	    	System.out.println("First value: "+treeMap.keySet());
	    }
	    
	    for (Integer val : treeMap.keySet()){
	    	System.out.println("First value: "+treeMap.get(val).get(0));
	    }
	    
	   // System.out.println("First value: "+treeMap.get(2));
	   // System.out.println("First value: "+treeMap.get(3));
	    FileWriter writer = new FileWriter("fileToWrite.txt");
	    for(List val : treeMap.values()){
	        writer.write(val.get(0)+"\n"); writer.write(System.getProperty( "line.separator" ));
	        writer.write(val.get(1)+"\n"); writer.write(System.getProperty( "line.separator" ));
	        writer.write(val.get(2)+"\n"); writer.write(System.getProperty( "line.separator" ));
	        writer.write(val.get(3)+"\n"); writer.write(System.getProperty( "line.separator" ));
	        writer.write(val.get(4)+"\n"); writer.write(System.getProperty( "line.separator" ));
	        writer.write(val.get(5)+"\n"); writer.write(System.getProperty( "line.separator" ));
	    }
	    reader.close();
	    writer.close();
		

	}

}
