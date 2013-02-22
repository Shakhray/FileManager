package manager;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import navigation.CD;
import navigation.Navigation;
import navigation.Opening;


import search.Search;

import model.Model;

import command2.*;

import exception.IsNotDirectryException;
import exception.NodeAlreadyExistsException;
import exception.NodeNotFoundException;
import exception.OperationNotSupportedException;
import filesystem.Dir;
import filesystem.File;
import filesystem.Link;
import filesystem.Node;

public class FileManager {

	private Stack<Command> commands = new Stack<Command>();
	private Model model;
	private Dir currentdir;
	private Stack<Navigation> downDir = new Stack<Navigation>();
	
	
	public FileManager(){
		useModel();
		currentdir = model.getNodeDao().getRoot();
	}
	
	public FileManager(String modelkey){
		useModel(modelkey);
		currentdir = model.getNodeDao().getRoot();
	}

	private void useModel(){
		model = new Model();
	}

	private void useModel(String modelkey){
		model = new Model(modelkey);
	}

	public String getCurrentDir(){
		return currentdir.getName();
	}

	//----------commands------------
	public void cd (String dir) throws IsNotDirectryException{
		Navigation cd = new CD(model.getNodeDao(), currentdir,dir);
		cd.execute();
		
		downDir.push(cd);
		currentdir = model.getNodeDao().getCurrentDir();
	}

	public void downDir(){
		if (!downDir.isEmpty()) downDir.pop().downDir();
		currentdir = model.getNodeDao().getCurrentDir();
	}

	public Collection<Node> dir(){
		return currentdir.getInsertedNode();
	}

	public void copy(String name, ArrayList<String> copyto) throws NodeNotFoundException{
		boolean b = true;
		for (Node node : currentdir.getInsertedNode())
			if (node.getName().equals(name)) {
				b = false;
				Command copy = new Copy(model.getNodeDao(),  node, copyto);
				copy.execute();
				commands.push(copy);
			}
		if (b) throw new NodeNotFoundException();
	}

	public void replace(String name, ArrayList<String> replaceto) throws NodeAlreadyExistsException, NodeNotFoundException{
		boolean b = true;
		for (Node node : currentdir.getInsertedNode()){
			if (node.getName().equals(name)) {
				b = false;
				Command replace = new Replace(model.getNodeDao(), node, replaceto);
				replace.execute();
				commands.push(replace);
				break;
			}
		}
		if (b) throw new NodeNotFoundException();
	}

	public void rename(String dir, String renameto) throws NodeNotFoundException{
		if (!isExist(renameto)){
			boolean b = true;
			for (Node node : currentdir.getInsertedNode()){
				if (node.getName().equals(dir)) {
					node.setName(renameto);
					b = false;
					Command rename = new Update(model.getNodeDao(), node);
					rename.execute();
					commands.push(rename);
					break;
				}
			}
			if (b) throw new NodeNotFoundException();
		}
		else throw new NodeAlreadyExistsException();
	}
	private boolean isExist(String name){
		boolean b = false;
		for (Node node : currentdir.getInsertedNode()){
			if (node.getName().equals(name)) {
				b = true;
				break;
			}
		}
		return b;
	}
	public void makeDir(String name) throws NodeAlreadyExistsException{
		if (!isExist(name)) {
			ArrayList<String> path = new ArrayList<String>();
			path = currentdir.getPath();
			path.add(currentdir.getName());
			Dir dir = new Dir(name,path);
					
			createCommand(dir);
		}
		else throw new NodeAlreadyExistsException();
	}

    private void createCommand(Node node){
    	Command create = new Create(model.getNodeDao(), node);
		create.execute();
		commands.push(create);
    }

	public void makeFile(String name) throws NodeAlreadyExistsException{
		if (!isExist(name)) {
			ArrayList<String> path = new ArrayList<String>();
			path = currentdir.getPath();
			path.add(currentdir.getName());
			File file = new File(name,path);
					
			createCommand(file);
		}
		else throw new NodeAlreadyExistsException();
	}

	private Node findNode (Dir root, ArrayList<String> path){
		Node findnode = null;
		if (path.size()>1){
		for (Node node : root.getInsertedNode())
			if (node.getName().equals(path.get(0))) {
				path.remove(0);
				findnode = findNode((Dir)node, path);
				break;
			}
		}
		else
			if (path.size()==1){
				for (Node node : root.getInsertedNode())
					if (node.getName().equals(path.get(0))) {
						findnode = node;
						break;
					}
				}
		if (findnode != null) return findnode;
		else throw new NodeNotFoundException();
	}
	
	public void makeLink(String name, ArrayList<String> linkTo) throws NodeAlreadyExistsException{
		if (!isExist(name)) {
			ArrayList<String> path = new ArrayList<String>();
			path = currentdir.getPath();
			path.add(currentdir.getName());
			
			linkTo.remove(0);
			Node linknode = findNode (model.getNodeDao().getRoot(), linkTo);
			Link link = new Link(name, linknode, path);
					
			createCommand(link);
		}
		else throw new NodeAlreadyExistsException();
	}
	public void delete(String deldir){
		for (Node node : currentdir.getInsertedNode()){
			if (node.getName().equals(deldir)) {
				Command delete = new Delete(model.getNodeDao(), node);
				delete.execute();
				commands.push(delete);
				break;
			}
		}
	}
	public void open(String name) throws IsNotDirectryException{
		if (isExist(name)) {
			for (Node node : currentdir.getInsertedNode()){
				if (node.getName().equals(name)){
					if (node.isDir()) cd(node.getName()); 
					if (node.isFile()) throw new OperationNotSupportedException();
					if (node.isLink()) 	{
						Navigation link = new Opening(model.getNodeDao(), (Link)node);
						link.execute();
						if (((Link)node).linkTo().isDir()){
							downDir.push(link);
							currentdir = model.getNodeDao().getCurrentDir();
						}
					}
						
				}
			}
		}
		else throw new NodeNotFoundException();
	}
	public void undo() throws IOException{
		if (!commands.isEmpty()) 
			commands.pop().undo();
	}
	
	//---------------------------
	
	//template
	/*public Node find(String findTo){
		Search search = new Search();
		search.execute();
		
		Node node = null;
		return node;
	}*/
}
