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
import filesystem.Dir;
import filesystem.File;
import filesystem.Node;

public class FileManager {

	private Stack<Command> commands = new Stack<Command>();
	private Model model;
	private Dir currentdir;
	private Stack<CD> downDir = new Stack<CD>();
	
	
	public FileManager(){
		useModel();
		currentdir = model.getDirDao().getCurrentDir();
	}
	
	public FileManager(String modelkey){
		useModel(modelkey);
		currentdir = model.getDirDao().getCurrentDir();
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
	public void cd (String dir){
		CD cd = new CD(model.getDirDao(), model.getFileDao(), model.getLinkDao(), currentdir,dir);
		cd.execute();
		downDir.push(cd);
		currentdir = model.getDirDao().getCurrentDir();
	}
	public void downDir(){
		if (!downDir.isEmpty()) downDir.pop().downDir();
		currentdir = model.getDirDao().getCurrentDir();
	}
	public Collection<Node> dir(){
		return currentdir.getInsertedNode();
	}
	public void copy(String name, ArrayList<String> copyto) throws NodeNotFoundException{
		boolean b = true;
		for (Node node : currentdir.getInsertedNode())
			if (node.getName().equals(name)) {
				b = false;
				Command copy = null;
				if (node.isDir()) copy = new Copy(model.getDirDao(), currentdir, (Dir)node, copyto);
				if (node.isFile()) copy = new Copy(model.getFileDao(), currentdir, (File)node, copyto);
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
				Command replace = null;
				if (node.isDir()) replace = new Replace(model.getDirDao(), currentdir, (Dir)node, replaceto);
				if (node.isFile()) replace = new Replace(model.getFileDao(), currentdir, (File)node, replaceto);
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
					b = false;
					Command rename = null;
					if (node.isDir()) rename = new Rename(model.getDirDao(), currentdir, (Dir)node, renameto);
					if (node.isFile()) rename = new Rename(model.getFileDao(), currentdir, (File)node, renameto);
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
					
			Command create = new Create(model.getDirDao(), currentdir, dir);
			create.execute();
			commands.push(create);
		}
		else throw new NodeAlreadyExistsException();
	}
	public void makeFile(String name) throws NodeAlreadyExistsException{
		if (!isExist(name)) {
			ArrayList<String> path = new ArrayList<String>();
			path = currentdir.getPath();
			path.add(currentdir.getName());
			File file = new File(name,path);
					
			Command create = new Create(model.getFileDao(), currentdir, file);
			create.execute();
			commands.push(create);
		}
		else throw new NodeAlreadyExistsException();
	}
	public void delete(String deldir){
		for (Node node : currentdir.getInsertedNode()){
			if (node.getName().equals(deldir)) {
				Command delete = null;
				if (node.isDir()) delete = new Delete(model.getDirDao(), currentdir, (Dir)node);
				if (node.isFile()) delete = new Delete(model.getFileDao(), currentdir, (File)node);
				delete.execute();
				commands.push(delete);
				break;
			}
		}
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
