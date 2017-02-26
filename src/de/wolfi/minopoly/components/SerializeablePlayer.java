package de.wolfi.minopoly.components;

import java.io.Serializable;

import de.wolfi.minopoly.components.fields.Field;
import de.wolfi.minopoly.utils.FigureType;

public class SerializeablePlayer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8329892097368700190L;
	private final FigureType f;
	private final Field loc;

	public SerializeablePlayer(Field loc, FigureType f) {
		this.loc = loc;
		this.f = f;
	}

	public FigureType getF() {
		return this.f;
	}

	public Field getLoc() {
		return this.loc;
	}

}