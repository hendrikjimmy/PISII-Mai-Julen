package ochoreinas;

//Eight Reinas puzzle written in Java
//Written by Tim Budd, January 1996
//revised for 1.3 event model July 2001
//

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class VentanaReinas extends Frame {
	
	private Reina ultimaReina = null;
	
	public VentanaReinas() {
		setTitle("Problema de las ocho reinas");
		setSize(600, 500);
		for (int i = 1; i <= 8; i++) {
			ultimaReina = new Reina(i, ultimaReina);
			ultimaReina.buscaSolucion();
		}
		addMouseListener(new MouseKeeper());
		addWindowListener(new CloseQuit());
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		VentanaReinas world = new VentanaReinas();
		world.show();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// dibuja el tablero
		for (int i = 0; i <= 8; i++) {
			g.drawLine(50 * i + 10, 40, 50 * i + 10, 440);
			g.drawLine(10, 50 * i + 40, 410, 50 * i + 40);
		}
		g.drawString("Pulse con para una nueva solución", 20, 470);
		// draw queens
		paint(g, ultimaReina);
	}
	
	public void paint(Graphics g, Reina reina) {
		// primero dibuja la vecina vecina
		if (reina.getVecina() != null)
			reina.getVecina().paint(g);
		// despues a ella misna
		// x, y is upper left corner
		int x = (reina.getFila() - 1) * 50 + 10;
		int y = (reina.getColumna() - 1) * 50 + 40;
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

	
	private class CloseQuit extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}

	private class MouseKeeper extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			ultimaReina.avanza();
			repaint();
		}
	}
}