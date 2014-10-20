package ca.mcgill.mcb.pcingola.bigDataScript.lang;

import org.antlr.v4.runtime.tree.ParseTree;

import ca.mcgill.mcb.pcingola.bigDataScript.run.BigDataScriptThread;

/**
 * Post increment / decrement operator
 *
 * E.g. :  i++ or i--
 *
 * @author pcingola
 */
public class Post extends Pre {

	public Post(BigDataScriptNode parent, ParseTree tree) {
		super(parent, tree);
	}

	/**
	 * Evaluate an expression
	 */
	@Override
	public Object eval(BigDataScriptThread csThread) {
		Reference ref = (Reference) expr;
		long value = (Long) ref.eval(csThread);

		if (operation == PrePostOperation.INCREMENT) ref.setValue(csThread, value + 1);
		else if (operation == PrePostOperation.DECREMENT) ref.setValue(csThread, value - 1);
		else throw new RuntimeException("Unknown operator " + operation);

		return value;
	}

	@Override
	protected void parse(ParseTree tree) {
		BigDataScriptNode node = factory(tree, 0);
		if (node instanceof Reference) expr = (Expression) node;

		operation = PrePostOperation.parse(tree.getChild(1).getText());
	}

	@Override
	public String toString() {
		return operation + expr.toString();
	}

}
