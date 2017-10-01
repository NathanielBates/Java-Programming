/*
Name: Nathaniel Bates
Course: CNT 4714 - Fall 2017
Assignment title: Program 2 - Multi-threaded Programming in Java
Date: Sunday October 1, 2017
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// Conveyors class contains the entry point to the program and reads in the config.txt file, which is hardcoded in.
// An ArrayList of stations is generated based of the information in the config.txt file, including workload amounts.
// At the end of the class, a thread is created for each station to run on.

public class Conveyors
{
	private static ArrayList<Station> stations;
	private static int numOfStations;
		
	public static void main(String args[])
	{		
		FileReader configFile = null;
		BufferedReader configReader = null;		
		String configLine;
		
		stations = new ArrayList<Station>();
		try 
		{
			configFile = new FileReader("config.txt");
			configReader = new BufferedReader(configFile);
			
			numOfStations = Integer.valueOf(configReader.readLine());
	
			int i = 0;
			int conBelt;
			while((configLine = configReader.readLine()) != null){
				if(i == 0) 
				   conBelt = numOfStations - 1;
				else 
					conBelt = i - 1;
				
				Station newStation = new Station(i, conBelt, Integer.valueOf(configLine));
				stations.add(newStation);
				i++;
			}			
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Could not open file for reading!");		
		}
		catch (IOException e) 
		{
			System.out.println("Could not read from file!");
		}
		finally
		{
			try 
			{
				configFile.close();
				configReader.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		for(Station s: stations)
			new Thread(new WorkLoadThread(s, stations)).start();			
		
	}
	
}

