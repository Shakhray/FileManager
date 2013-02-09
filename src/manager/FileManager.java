package manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import open.Opening;

import search.Search;

import model.Model;

import command.*;

import filesystem.Node;

public class FileManager {
	//private Collection<Node> node = new ArrayList<Node>();
	private Stack<Command> commands = new Stack<Command>();
	private Model model;
	
	public FileManager(){
		useModel();
	}
	
	public FileManager(String modelkey){
		useModel(modelkey);
	}
	public void useModel(){
		model = new Model();
	}
	public void useModel(String modelkey){
		model = new Model(modelkey);
	}
	//----------commands------------
	public void copy(String copyTo){
		Command copy = new Copy();
		copy.execute();
		commands.push(copy);
	}
	public void replace(String replaceTo){
		Command replace = new Replace();
		replace.execute();
		commands.push(replace);
	}
	public void rename(String renameTo){
		Command rename = new Rename();
		rename.execute();
		commands.push(rename);
	}
	public void create(){
		Command create = new Create();
		create.execute();
		commands.push(create);
	}
	public void delete(String deletingNode){
		Command delete = new Delete();
		delete.execute();
		commands.push(delete);
	}
	public void undo(){
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
