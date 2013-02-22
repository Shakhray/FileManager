package viewers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import exception.IsNotDirectryException;
import exception.NodeAlreadyExistsException;
import exception.NodeNotFoundException;
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
						System.out.print("enten copied file: ");
						String namenode = reader.readLine();
						System.out.print("copy to: ");
						String path = reader.readLine();
						String[] patharr = path.split("/");
						ArrayList<String> copyto = new ArrayList<String>();
						for(int i=0; i<patharr.length; i++)
							copyto.add(patharr[i]);
						filemanager.copy(namenode, copyto);
						break;
					}
					case DELETE :{
						String name = reader.readLine();
						filemanager.delete(name);
						break;
					}
					case MKDIR :{
						System.out.print("new dir: ");
						String name = reader.readLine();
						filemanager.makeDir(name);
						break;
					}
					case MKFILE :{
						System.out.print("new file: ");
						String name = reader.readLine();
						filemanager.makeFile(name);
						break;
					}
					case MKLINK :{
						System.out.print("new link: ");
						String name = reader.readLine();
						
						System.out.print("link to: ");
						String path = reader.readLine();
						String[] patharr = path.split("/");
						ArrayList<String> linkto = new ArrayList<String>();
						for(int i=0; i<patharr.length; i++)
							linkto.add(patharr[i]);
						filemanager.makeLink(name,linkto);
						break;
					}
					case RENAME :{
						System.out.print("enten renaming file: ");
						String dir = reader.readLine();
						System.out.print("enten new name: ");
						String renameto = reader.readLine();
						filemanager.rename(dir, renameto);
						break;
					}
					case REPLACE : {
						System.out.print("enten repalcing file: ");
						String namenode = reader.readLine();
						System.out.print("replace to: ");
						String path = reader.readLine();
						String[] patharr = path.split("/");
						ArrayList<String> replaceto = new ArrayList<String>();
						for(int i=0; i<patharr.length; i++)
							replaceto.add(patharr[i]);
						filemanager.replace(namenode, replaceto);
						break;
					}
					case OPEN : {
						System.out.print("open: ");
						String name = reader.readLine();
						filemanager.open(name);
						break;
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
				e.printStackTrace();
			} catch(IsNotDirectryException e){
				viewer.badCommand();
				e.printStackTrace();
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NodeNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NodeAlreadyExistsException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
		}
		} catch (NodeAlreadyExistsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
