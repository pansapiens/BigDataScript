package ca.mcgill.mcb.pcingola.bigDataScript.lang;

import org.antlr.v4.runtime.tree.ParseTree;

import ca.mcgill.mcb.pcingola.bigDataScript.compile.CompilerMessage.MessageType;
import ca.mcgill.mcb.pcingola.bigDataScript.compile.CompilerMessages;
import ca.mcgill.mcb.pcingola.bigDataScript.run.BigDataScriptThread;
import ca.mcgill.mcb.pcingola.bigDataScript.scope.Scope;
import ca.mcgill.mcb.pcingola.bigDataScript.scope.ScopeSymbol;

/**
 * An expression having an implicit type variable initialization ( varName := expression )
 * 
 * @author pcingola
 */
public class ExpressionVariableInitImplicit extends Expression {

	VariableInitImplicit vInit;

	public ExpressionVariableInitImplicit(BigDataScriptNode parent, ParseTree tree) {
		super(parent, tree);
	}

	@Override
	public Object eval(BigDataScriptThread csThread) {
		// Evaluating the expression consists of initializing the variable and getting the result of that initialization

		// Add variable to scope
		csThread.getScope().add(new ScopeSymbol(vInit.getVarName(), returnType));

		// Evaluate assignment
		vInit.run(csThread);

		return csThread.getScope().getSymbol(vInit.getVarName());
	}

	@Override
	protected boolean isReturnTypesNotNull() {
		return vInit.getExpression().getReturnType() != null;
	}

	@Override
	protected void parse(ParseTree tree) {
		vInit = new VariableInitImplicit(this, tree);
	}

	@Override
	public Type returnType(Scope scope) {
		if (returnType != null) return returnType;

		returnType = vInit.getExpression().returnType(scope);
		return returnType;
	}

	@Override
	protected void typeCheck(Scope scope, CompilerMessages compilerMessages) {
		vInit.typeCheck(scope, compilerMessages);

		// Already declared?
		String varName = vInit.varName;
		if (scope.hasSymbol(varName, true)) compilerMessages.add(this, "Duplicate local name " + varName, MessageType.ERROR);

		// Calculate implicit data type
		Type type = vInit.getExpression().returnType(scope);

		// Add variable to scope
		scope.add(new ScopeSymbol(varName, type));

	}

}
