/*
Name: Nathaniel Bates
Course: CNT 4714 - Fall 2017
Assignment title: Program 2 - Multi-threaded Programming in Java
Date: Sunday October 1, 2017
*/

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

// WorkLoadThread implements the Runnable interface to start running the threads.
// Constructor creates a new thread type for each station and we override the run method from Runnable.
// The run method will continue calling the doWork method for the station while the workload in that
// thread, which is related to a station, reaches 0.  The thread will sleep for a period of time
// if it is unable to gain access to a conveyor belt. The threads will continue to run synchronously until
// the workloads for each station reach 0. The write file method at the bottom writes the output to an 
// external file called output.txt.

class WorkLoadThread implements Runnable
	{
		Station currentStation;
		Station prevStation;
		
		public WorkLoadThread(Station newStationThread, ArrayList<Station> stations)
		{
			this.currentStation = newStationThread;
			int position = stations.indexOf(newStationThread);
			if(position != 0) 
				prevStation = stations.get(position - 1);
			else 
				prevStation = stations.get(stations.size()-1);
		}
		
		@Override
		public void run() 
		{
			System.out.println("Routing Station "+this.currentStation.numStation+": In-connection set to conveyor "+this.currentStation.conBelt1);
			System.out.println("Routing Station "+this.currentStation.numStation+": Out-connection set to conveyor "+this.currentStation.conBelt2);
			System.out.println("Routing Station "+this.currentStation.numStation+": Workload set. Station "+this.currentStation.numStation+" has "+this.currentStation.workLoad+" package groups to move.");

			writeFile("Routing Station "+this.currentStation.numStation+": In-connection set to conveyor "+this.currentStation.conBelt1);
			writeFile("Routing Station "+this.currentStation.numStation+": Out-connection set to conveyor "+this.currentStation.conBelt2);
			writeFile("Routing Station "+this.currentStation.numStation+": Workload set. Station "+this.currentStation.numStation+" has "+this.currentStation.workLoad+" package groups to move.");
			
			while(currentStation.workLoad != 0)
			{
				Random random = new Random();
				try 
				{
                    Thread.sleep(random.nextInt(10));
                } 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
				currentStation.doWork(prevStation);
			}
			if(currentStation.workLoad == 0)
			{
				System.out.println(" * * Station "+this.currentStation.numStation+": Workload successfully completed. * *");
				writeFile(" * * Station "+this.currentStation.numStation+": Workload successfully completed. * *");
			}
			
		}
		
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
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}		
	}