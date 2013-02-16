package command;

import java.util.ArrayList;

import dao.DirDao;
import filesystem.Dir;

public class Replace extends Command{

	private Dir replacedir;
	private ArrayList<String> replaceto = new ArrayList<String>();
	
	public Replace(DirDao dirdao, Dir currentdir, Dir replacedir, ArrayList<String> replaceto){
		super(dirdao, currentdir);
		this.replacedir = replacedir;
		this.replaceto = replaceto;
	}

	public void execute(){
		super.dirdao.replace(replacedir, replaceto);
	}
	public void undo(){
		super.undo();
	}
}
