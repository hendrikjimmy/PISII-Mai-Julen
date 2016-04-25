package ochoreinas;

import java.awt.*;
//
//Eight Reinas puzzle written in Java
//Written by Tim Budd, January 1996
//revised for 1.3 event model July 2001
//

class Reina {
	// datos
	private int fila;
	private int columna;
	private Reina vecina;

	// constructor
	Reina(int c, Reina n) {
		setFila(1);
		setColumna(c);
		setVecina(n);
	}

	public boolean buscaSolucion() {
		while (getVecina() != null && getVecina().puedeAtacar(getFila(), getColumna()))
			if (!avanza())
				return false;
		return true;
	}

	public boolean avanza() {
		if (getFila() < 8) {
			setFila(getFila() + 1);
			return buscaSolucion();
		}
		if (getVecina() != null) {
			if (!getVecina().avanza())
				return false;
			if (!getVecina().buscaSolucion())
				return false;
		} else
			return false;
		setFila(1);
		return buscaSolucion();

	}

	private boolean puedeAtacar(int testfila, int testcolumna) {
		int columnaDiferencia = testcolumna - getColumna();
		if ((getFila() == testfila) || (getFila() + columnaDiferencia == testfila) || (getFila() - columnaDiferencia == testfila))
			return true;
		if (getVecina() != null)
			return getVecina().puedeAtacar(testfila, testcolumna);
		return false;
	}

	public void paint(Graphics g) {
		// primero dibuja la vecina vecina
		if (getVecina() != null)
			getVecina().paint(g);
		// despues a ella misna
		// x, y is upper left corner
		int x = (getFila() - 1) * 50 + 10;
		int y = (getColumna() - 1) * 50 + 40;
		g.drawLine(x + 5, y + 45, x + 45, y + 45);
		g.drawLine(x + 5, y + 45, x + 5, y + 5);
		g.drawLine(x + 45, y + 45, x + 45, y + 5);
		g.drawLine(x + 5, y + 35, x + 45, y + 35);
		g.drawLine(x + 5, y + 5, x + 15, y + 20);
		g.drawLine(x + 15, y + 20, x + 25, y + 5);
		g.drawLine(x + 25, y + 5, x + 35, y + 20);
		g.drawLine(x + 35, y + 20, x + 45, y + 5);
		g.drawOval(x + 20, y + 20, 10, 10);
	}

	public Reina getVecina() {
		return vecina;
	}

	public void setVecina(Reina vecina) {
		this.vecina = vecina;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int fila) {
		this.fila = fila;
	}

	public int getColumna() {
		return columna;
	}

	public void setColumna(int columna) {
		this.columna = columna;
	}

}