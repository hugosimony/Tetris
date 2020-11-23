package fr.hugosimony.tetris;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class Bouton extends JButton{

	private static final long serialVersionUID = 1L;
	
	Game game;
	
	private static Color noir = new Color(0,0,0);
	private static Color fond = new Color(100,100,100);

	public Bouton(int i, int j, Game game) {
		this.game = game;
		
		placernew(i,j,game.tab);
		
		this.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent event) {
				int keyCode = event.getKeyCode();
				if (keyCode == KeyEvent.VK_LEFT)
					game.deplacerPiece(game.piece_actuelle, game.DIR_GAUCHE);
				if (keyCode == KeyEvent.VK_RIGHT)
					game.deplacerPiece(game.piece_actuelle, game.DIR_DROITE);
				if (keyCode == KeyEvent.VK_DOWN)
					game.deplacerPiece(game.piece_actuelle, game.DIR_BAS);
				if (keyCode == KeyEvent.VK_R)
					game.tournerPiece(game.piece_actuelle, game.position);
				if (keyCode == KeyEvent.VK_E)
					game.placerPieceMemoire(game.piece_actuelle);
				if (keyCode == KeyEvent.VK_P) {
					if(game.pause) {
						game.pause = false;
					}else {
						game.pause = true;
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// Do nothing
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				// Do nothing
			}
		});
		
	}

	private void placernew(int i, int j, int[][] tab) {
		if(tab[i][j] == 11)
			this.setBackground(noir);
	
		if(tab[i][j] == 0) 
			this.setBackground(fond);
		
	}
}
