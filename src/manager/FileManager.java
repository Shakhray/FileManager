package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import navigation.CD;

import open.Opening;

import search.Search;

import model.Model;

import command.*;

import exception.NodeAlreadyExistsException;
import exception.NodeNotFoundException;
import exception.OperationNotSupportedException;
import filesystem.Node;

public class FileManager {
	//private Collection<Node> node = new ArrayList<Node>();
	private Stack<Command> commands = new Stack<Command>();
	private Model model;
	private Node currentDir;
	private Stack<CD> downDir = new Stack<CD>();
	
	
	public FileManager() throws NodeAlreadyExistsException, OperationNotSupportedException{
		useModel();
		currentDir = model.getDirDao().getCurrentDir();
	}
	
	public FileManager(String modelkey) throws NodeAlreadyExistsException, OperationNotSupportedException{
		useModel(modelkey);
		currentDir = model.getDirDao().getCurrentDir();
	}
	public void useModel() throws NodeAlreadyExistsException, OperationNotSupportedException{
		model = new Model();
	}
	public void useModel(String modelkey) throws NodeAlreadyExistsException, OperationNotSupportedException{
		model = new Model(modelkey);
	}
	public String getCurrentDir(){
		return currentDir.getName();
	}
	//----------commands------------
	public void cd (String dir) throws OperationNotSupportedException, NodeNotFoundException, NodeAlreadyExistsException{
		/*boolean b =true;
		for (Node node : currentDir.getInsertedNode()){
			if (node.getName().equals(dir)) {
				downDir.push(currentDir);
				currentDir=node; 
				b = false;
			}
		}
		if (b) throw new NodeNotFoundException();*/
		CD cd = new CD(model.getDirDao(),currentDir,dir);
		cd.execute();
		downDir.push(cd);
		currentDir = model.getDirDao().getCurrentDir();
	}
	public void downDir(){
		if (!downDir.isEmpty()) downDir.pop().downDir();
		currentDir = model.getDirDao().getCurrentDir();
	}
	public Collection<Node> dir() throws OperationNotSupportedException{
		return currentDir.getInsertedNode();
	}
	public void copy(String copyTo) throws OperationNotSupportedException, NodeAlreadyExistsException{
		Command copy = new Copy(null);
		copy.execute();
		commands.push(copy);
	}
	public void replace(String replaceTo) throws OperationNotSupportedException, NodeAlreadyExistsException{
		Command replace = new Replace(null);
		replace.execute();
		commands.push(replace);
	}
	public void rename(String renameTo) throws OperationNotSupportedException, NodeAlreadyExistsException{
		Command rename = new Rename(null);
		rename.execute();
		commands.push(rename);
	}
	public void create(String name) throws OperationNotSupportedException, NodeAlreadyExistsException{
		Command create = new Create(model.getDirDao(), name);
		create.execute();
		commands.push(create);
	}
	public void delete(String deletingNode) throws OperationNotSupportedException, NodeAlreadyExistsException{
		Command delete = new Delete(null);
		delete.execute();
		commands.push(delete);
	}
	public void undo() throws IOException{
		if (!commands.isEmpty()) 
			commands.pop().undo();
	}
	//---------------------------
	
	//template
	public Node find(String findTo){
		Search search = new Search();
		search.execute();
		
		Node node = null;
		return node;
	}
	//template
	public Node opening(String openNode){
		Opening open = new Opening();
		open.execute();
		Node node = null;
		return node;
	}
}
