/*
Name: Nathaniel Bates
Course: CNT 4714 - Fall 2017
Assignment title: Program 2 - Multi-threaded Programming in Java
Date: Sunday October 1, 2017
*/

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Each station object is created to handle the workloads and check the lock availability.
// The constructor forms each station and the corresponding conveyor belts based off the station
// number and workload is assigned.
 
public class Station
{

	private final Lock beltLock = new ReentrantLock();
	
	public int numStation;
	public int workLoad;
	public int conBelt1;
	public int conBelt2;

	public Station(int numStation, int conBelt1, int workLoad)
	{		
		this.conBelt1 = conBelt1;
		this.conBelt2 = numStation;
		this.numStation = numStation;
		this.workLoad = workLoad;
	}

// The checkLock method checks to see if the conveyor belts are available for the next station to use.
// It tries the locks in sequential order to aid in avoiding a deadlock as per assignment instructions.
// If the tryLock method returns true, then access is granted to the conveyor belts.
// Finally the belts are released through the unlock method for use by the next station.
// Boolean value for the states of both locks is returned at the end.

	public boolean checkLock(Station nextStation)
	{
		
		boolean beltLock1 = false;		
		boolean beltLock2 = false;
		
		try
		{
			if(beltLock1 = this.beltLock.tryLock()) 
			{
				System.out.println("Routing Station "+numStation+": Granted access to conveyor "+this.conBelt1);
				writeFile("Routing Station "+numStation+": Granted access to conveyor "+this.conBelt1);
			}
			if(beltLock2 = nextStation.beltLock.tryLock())
			{
				System.out.println("Routing Station "+numStation+": Granted access to conveyor "+this.conBelt2);
				writeFile("Routing Station "+numStation+": Granted access to conveyor "+this.conBelt2);
			}
		}
		
		finally
		{
			if(!(beltLock1 && beltLock2))
			{
				if(beltLock1)
				{
					this.beltLock.unlock();
					System.out.println("Routing Station "+numStation+": Released access to conveyor "+this.conBelt1);
					writeFile("Routing Station "+numStation+": Released access to conveyor "+this.conBelt1);
				}
				if(beltLock2)
				{
					nextStation.beltLock.unlock();
					System.out.println("Routing Station "+numStation+": Released access to conveyor "+this.conBelt2);
					writeFile("Routing Station "+numStation+": Released access to conveyor "+this.conBelt2);
				}
			}
		}
		
		return beltLock1 && beltLock2;
	}

// The doWork method takes in a station and checks the locks to see if both belts for the station are available.
// If checkLock return true the status is printed to the console as well as the output.txt file.  
// I implement the sleep method, decrement the workload, unlock the stations, and print out the number of package groups left.

	public void doWork(Station nextStation)
	{
		
		if(checkLock(nextStation))
		{
			try
			{
				System.out.println("Routing Station "+numStation+": Successfully moves packages on conveyor "+ conBelt1);
				writeFile("Routing Station "+numStation+": Successfully moves packages on conveyor "+ conBelt1);
				System.out.println("Routing Station "+numStation+": Successfully moves packages on conveyor "+ conBelt2);
				writeFile("Routing Station "+numStation+": Successfully moves packages on conveyor "+ conBelt2);
				Random r = new Random();
				try 
				{
					Thread.sleep(r.nextInt(10));
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				
				workLoad--;
				System.out.println("Routing station " +numStation+": has "+workLoad+" package groups left to move.");
				writeFile("Routing station " +numStation+": has "+workLoad+" package groups left to move.");
			}
			finally
			{
				this.beltLock.unlock();
				nextStation.beltLock.unlock();
				System.out.println("Routing Station "+numStation+": Released access to conveyor "+this.conBelt1);
				System.out.println("Routing Station "+numStation+": Released access to conveyor "+this.conBelt2);
				writeFile("Routing Station "+numStation+": Released access to conveyor "+this.conBelt1);
				writeFile("Routing Station "+numStation+": Released access to conveyor "+this.conBelt2);
			}
			
		}
	}

// Method writes the output to the output.txt file.

	private void writeFile(String dataOut)
	{
		String FILE = "output.txt";
		try 
		{
			FileWriter output = new FileWriter(FILE,true);
			BufferedWriter buffOutput = new BufferedWriter(output);
			buffOutput.write(dataOut);
			buffOutput.newLine();
			buffOutput.close();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}