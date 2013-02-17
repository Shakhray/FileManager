package navigation;

import exception.IsNotDirectryException;
import exception.NodeNotFoundException;
import filesystem.Node;

public abstract class Navigation {
	public abstract void downDir();
	public abstract Node getCurrentDir();
	public abstract void execute()  throws  NodeNotFoundException, IsNotDirectryException;
}
