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

import command.*;
import dao.DirDao;
import dao.FileDao;
import dao.LinkDao;

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
	public void cd (String dir) throws IsNotDirectryException{
		Navigation cd = new CD(model.getDirDao(), model.getFileDao(), model.getLinkDao(), currentdir,dir);
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
				if (node.isLink()) copy = new Copy(model.getLinkDao(), currentdir, (Link)node, copyto);
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
				if (node.isLink()) replace = new Replace(model.getLinkDao(), currentdir, (Link)node, replaceto);
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
					if (node.isLink()) rename = new Rename(model.getLinkDao(), currentdir, (Link)node, renameto);
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
	
	public void makeLink(String name, ArrayList<String> linkto) throws NodeAlreadyExistsException{
		if (!isExist(name)) {
			ArrayList<String> path = new ArrayList<String>();
			path = currentdir.getPath();
			path.add(currentdir.getName());
			
			linkto.remove(0);
			Node linknode = findNode (model.getDirDao().getRoot(), linkto);
			Link link = new Link(name, linknode, path);
					
			Command create = new Create(model.getLinkDao(), currentdir, link);
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
				if (node.isLink()) delete = new Delete(model.getLinkDao(), currentdir, (Link)node);
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
						Navigation link = new Opening(model.getDirDao(), model.getLinkDao(),model.getFileDao(), (Link)node);
						link.execute();
						if (((Link)node).linkTo().isDir()){
							downDir.push(link);
							currentdir = model.getDirDao().getCurrentDir();
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
