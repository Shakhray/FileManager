package dao.testfilesystem_dao;

import io.ReadWriteSerial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import dao.DirDao;

import exception.NodeAlreadyExistsException;
import exception.OperationNotSupportedException;
import filesystem.Dir;
import filesystem.Node;
import filesystem.testfilesystem.TestFileSystem;

public class TestFileSystemDaoDir implements DirDao{
	private TestFileSystem fs;
	private Node root;
	private Node currentdir;
	
	public TestFileSystemDaoDir() throws NodeAlreadyExistsException, OperationNotSupportedException{
		fs = new TestFileSystem();
		root = fs.getRoot();
		currentdir = root;
		//ReadWriteSerial rw = new ReadWriteSerial();
		
	/*	try {
			rw.writeToFite(fs);
			root = (Node)rw.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
	}
	public void setRoot(Node root){
		this.root = root;
	}
	public void setFSForDirAndLink(){
		
	}
	public Node getRoot(){
		return root;
	}
	public Node getCurrentDir(){
		return currentdir;
	}
	public void setCurrentDir(Node currentdir){
		this.currentdir = currentdir;  
	}
	private Node findNode(Node root, ArrayList<String> name, Node dir) throws OperationNotSupportedException, NodeAlreadyExistsException{
		int index = -1, k;
		if (name.size()>1){
		for(Node node : root.getInsertedNode()){
			index++;
			if (node.getName().equals(name.get(0))){
				k = index; 
				name.remove(0);
				root.deleteNode(node);
				root.addNode(findNode(node,name, dir));
			}
		}
		}
		else root.addNode(dir);
		return root;
	}
	public void create(String name) throws OperationNotSupportedException, NodeAlreadyExistsException{
		ArrayList<String> path = new ArrayList<String>();
		path.add(currentdir.getName());
		Node dir = new Dir(name,path);
		
		System.out.print(path.get(0));
		path.remove(0);
		root = findNode(root, path, dir);
		
	}
	/*public void write(Node root){
		studs.addStudent(stud);
		ReadWriteSerial rw = new ReadWriteSerial();
		try {
			rw.writeToFite(studs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	public void undo(Node backup) {
		root = backup;
	}
	
	
	
	/*private DataBase studs = null; 
	public StudDaoSerial(){
		studs = new DataBase();
		studs.addDefault();

		ReadWriteSerial rw = new ReadWriteSerial();
		try {
			studs=(DataBase) rw.readFromFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			rw.writeToFite(studs);
			studs = (DataBase)rw.readFromFile();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void write(Student stud){
		studs.addStudent(stud);
		ReadWriteSerial rw = new ReadWriteSerial();
		try {
			rw.writeToFite(studs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Collection<Student> readAll() {
		return studs.getStudents();
	}

	public void delete(Student stud) {
		studs.remove(stud);
		ReadWriteSerial rw = new ReadWriteSerial();
		try {
			rw.writeToFite(studs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update(Student stud, int index){
		DataBase newstud = new DataBase();
		int i=0;
		for(Student st : studs.getStudents()){
			if (i==index) newstud.addStudent(stud);
			else newstud.addStudent(st);
			i++;
		}
		studs=newstud;
		ReadWriteSerial rw = new ReadWriteSerial();
		try {
			rw.writeToFite(studs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void undo(Collection<Student> students){

		DataBase newstud = new DataBase();
		for(Student st : students){
			newstud.addStudent(st);
		}
		studs=newstud;
		ReadWriteSerial rw = new ReadWriteSerial();
		try {
			rw.writeToFite(newstud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public Student findByIndex(int index) {
		Collection<Student> studs = readAll();
		int i=0;
		Student findstud = null;
		for(Student st : studs){
			if (i==index) {findstud = st; break;}
			i++;
		}
		return findstud;
	}
	public Collection<Student> read(String[] fields) {
		Collection<Student> foundstudents = new ArrayList<Student>();
		Collection<Student> students = readAll();
		boolean b = false;
		for(Student stud : students){
			for (String field : fields)
			if (field.equals(stud.getSurname())    	||   field.equals(stud.getName())		||	field.equals(stud.getSecondName())||
				field.equals(stud.getGroupNumber())	||   field.equals(stud.getStudentID())	||	field.equals(stud.getDateOfTransfer()) ||
				field.equals(stud.getFaculty()))
				b=true;
			if (b) foundstudents.add(stud);
			b=false;
		}
	    return foundstudents;
	}
	
	public void copyFile() throws CopyNotSupportException {
			throw new CopyNotSupportException();
	}

	public Student read(Student stud) throws BadXmlFileException, SAXException,	IOException, ParserConfigurationException {
		Student foundstudent = null;
		Collection<Student> students = readAll();
		for(Student stud1 : students)
			if (stud1.equals(stud)) {
				foundstudent = stud1;
				break;
			}
		return foundstudent;
	}*/
}