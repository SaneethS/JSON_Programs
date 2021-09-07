package com.jsoninventory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class JSONInventory {
	public static Map<String,Double> map = new HashMap<String,Double>();
	
	public static void jsonInventory() {
		try {
			Reader reader = new FileReader("data/inventory.json");
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(reader);
			JSONObject inventory = (JSONObject) obj;
			JSONArray array = (JSONArray) inventory.get("inventory");
			System.out.println(array);
			
			
			Iterator<JSONObject> iterator = array.iterator();
			while(iterator.hasNext()) {
				JSONObject item = iterator.next();
				String type = (String) item.get("type");
				System.out.println("Type = "+type);
				String name = (String) item.get("name");
				System.out.println("Name = "+name);
				double weight = (double) item.get("weight");
				System.out.println("Weight = "+weight);
				double price = (double) item.get("pricePerKG");
				System.out.println("PricePerKg = "+price);
				
				double totalPrice = weight * price;
				
				map.put(name, totalPrice);
				
				System.out.println("");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		getOutput();
	}

	private static void getOutput() {
		try {
			Writer writer = new FileWriter("data/resultsInventory.json");
			JSONArray array = new JSONArray();
			Set<Entry<String,Double>> set = map.entrySet();
			
			for(Entry<String,Double> entry : set) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("name",entry.getKey());
				jsonObject.put("totalPrice", entry.getValue());
				array.add(jsonObject);
			}
			JSONObject mainObject = new JSONObject();
			mainObject.put("resultsInventory", array);
			System.out.println(mainObject);
			writer.write(mainObject.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
