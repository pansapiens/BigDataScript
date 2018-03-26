package org.bds.lang.value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bds.lang.statement.ClassDeclaration;
import org.bds.lang.statement.FieldDeclaration;
import org.bds.lang.statement.VariableInit;
import org.bds.lang.type.Type;
import org.bds.lang.type.TypeClass;

/**
 * Define a value of an object (i.e. a class)
 * @author pcingola
 */
public class ValueClass extends ValueComposite {

	Map<String, Value> fields; // TODO: Should I change to array for efficiency?

	public ValueClass(Type type) {
		super(type);
	}

	@Override
	public Value clone() {
		throw new RuntimeException("!!! UNIMPLEMENTED");
	}

	/**
	 * Get native object (raw data)
	 */
	@Override
	public Object get() {
		return fields;
	}

	public Value getValue(String name) {
		if (isNull()) throw new RuntimeException("Null pointer: Cannot access field '" + getType() + "." + name + "'");
		return fields.get(name);
	}

	/**
	 * Initialize fields (by default the fields are null)
	 */
	public void initializeFields() {
		fields = new HashMap<>();
		TypeClass tc = (TypeClass) type;

		// Fields for this class and all parent classes
		for (ClassDeclaration cd = tc.getClassDeclaration(); cd != null; cd = cd.getClassParent()) {
			FieldDeclaration fieldDecls[] = cd.getFieldDecl();
			for (FieldDeclaration fieldDecl : fieldDecls) { // Add all fields
				Type vt = fieldDecl.getType();
				for (VariableInit vi : fieldDecl.getVarInit()) {
					String fname = vi.getVarName();
					if (!fields.containsKey(fname)) { // Don't overwrite values 'shadowed' by a child class
						fields.put(fname, vt.newDefaultValue());
					}
				}
			}
		}
	}

	public boolean isNull() {
		return fields == null;
	}

	@Override
	public void parse(String str) {
		throw new RuntimeException("String parsing unimplemented for type '" + this + "'");
	}

	@SuppressWarnings("unchecked")
	@Override
	public void set(Object v) {
		fields = (Map<String, Value>) v;
	}

	public void setValue(String name, Value v) {
		if (isNull()) throw new RuntimeException("Null pointer: Cannot set field '" + getType() + "." + name + "'");
		fields.put(name, v);
	}

	@Override
	public String toString() {
		if (isNull()) return "null";

		StringBuilder sb = new StringBuilder();
		sb.append("{");
		if (fields != null) {
			List<String> fnames = new ArrayList<>(fields.size());
			fnames.addAll(fields.keySet());
			Collections.sort(fnames);
			for (int i = 0; i < fnames.size(); i++) {
				String fn = fnames.get(i);
				sb.append((i > 0 ? ", " : " ") + fn + ": " + fields.get(fn));
			}
		}
		sb.append(" }");
		return sb.toString();
	}

}
