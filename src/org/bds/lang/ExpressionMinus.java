package org.bds.lang;

import org.antlr.v4.runtime.tree.ParseTree;
import org.bds.compile.CompilerMessages;
import org.bds.run.BdsThread;
import org.bds.scope.Scope;

/**
 * A subtraction
 *
 * @author pcingola
 */
public class ExpressionMinus extends ExpressionMath {

	public ExpressionMinus(BdsNode parent, ParseTree tree) {
		super(parent, tree);
	}

	@Override
	protected String op() {
		return "-";
	}

	@Override
	public void runStep(BdsThread bdsThread) {
		if (right == null) {
			// Unary minus operator
			bdsThread.run(left);
			if (bdsThread.isCheckpointRecover()) return;

			// This should be an unary expression!
			if (isInt()) {
				bdsThread.push(-bdsThread.popInt());
				return;
			}

			if (isReal()) {
				bdsThread.push(-bdsThread.popReal());
				return;
			}
		} else {
			// Binary minus operator: Subtraction
			bdsThread.run(left);
			bdsThread.run(right);
			if (bdsThread.isCheckpointRecover()) return;

			if (isInt()) {
				long r = bdsThread.popInt();
				long l = bdsThread.popInt();
				bdsThread.push(l - r);
				return;
			}

			if (isReal()) {
				double r = bdsThread.popReal();
				double l = bdsThread.popReal();
				bdsThread.push(l - r);
				return;
			}

		}

		throw new RuntimeException("Unknown return type " + returnType + " for expression " + getClass().getSimpleName());
	}

	@Override
	public void typeCheckNotNull(Scope scope, CompilerMessages compilerMessages) {
		left.checkCanCastIntOrReal(compilerMessages);
		if (right != null) right.checkCanCastIntOrReal(compilerMessages);
	}

}
