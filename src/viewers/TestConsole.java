package viewers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import manager.FileManager;

public class TestConsole {
	public void runConsole() {

		FileManager filemanager = new FileManager();
		Viewer viewer = new Viewer();
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		
		while(true){
		
			viewer.comandLine();
			String command = null;
			try {
				command = reader.readLine();
			} catch (IOException e){
				viewer.programError();
				e.printStackTrace();
			}
			String[] arr = command.split(" ");
			try{
				Commands key = Commands.valueOf(arr[0].toUpperCase());
				switch(key){
				case ALLDIRS: {
					
				}
					case COPY :{
						
					}
					case DELETE :{
						
					}
					case CREATE :{

					}
					case RENAME :{

					}
					case REPLACE : {
						
					}
					case EXIT : System.exit(0);
					case UNDO : {
						filemanager.undo();
						break;
					}	
				}
			}
			catch(IllegalArgumentException e){
				viewer.badCommand();
			} 
			
		}
	}
}
