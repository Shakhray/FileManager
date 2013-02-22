package dao;

import java.util.arraylist;

import filesystem.dir;
import filesystem.node;

public interface nodedao<pk> {
    void update(node node);
    pk create(node node);
    void delete(node node);
    void setroot(dir root);
    
    public dir getroot();
    public void setcurrentdir(dir currentdir);
    public dir getcurrentdir();
    
	public void undo(arraylist<node> backup);

	public void copy(node copynode, arraylist<string> copyto);
	public void copy(node copynode, integer id);
	public void replace(node replacenode, arraylist<string> replaceto);
	public void replace(node replacenode, integer id);
}
