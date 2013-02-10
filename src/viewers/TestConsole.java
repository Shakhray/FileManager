package viewers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exception.NodeAlreadyExistsException;
import exception.NodeNotFoundException;
import exception.OperationNotSupportedException;
import filesystem.Node;

import manager.FileManager;

public class TestConsole {
	public void runConsole() {

		FileManager filemanager;
		try {
			filemanager = new FileManager("test");
		
		Viewer viewer = new Viewer();
		InputStreamReader inputStreamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(inputStreamReader);
		
		while(true){
		
			viewer.comandLine();
			viewer.currentDir(filemanager.getCurrentDir());
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
				//viewer.currentDir(filemanager.getCurrentDir());
				switch(key){
				case DIR: {
					for(Node node : filemanager.dir()){
						
						System.out.print(node.getName()+" ");
					} 
					break;
					
				}
				case CD: {
					String dir = reader.readLine();
					try {
						filemanager.cd(dir);
					} catch (NodeNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
				case DOWNDIR: {
					filemanager.downDir();
					break;
				}
				case ALLDIRS: {
					
				}
					case COPY :{
						
					}
					case DELETE :{
						
					}
					case CREATE :{
						String name = reader.readLine();
						filemanager.create(name);
						break;
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		}
		} catch (NodeAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (OperationNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
