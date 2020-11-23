package fr.hugosimony.tetris;

import java.util.TimerTask;

public class Deplacement extends TimerTask {

	public int difficulty;
	
	Game game;
	
	public Deplacement(Game game, int difficulty) {
		this.game = game;
		this.difficulty = difficulty;
	}
	
	@Override
	public void run() {
		if(game.stop) {
			this.cancel();
			game.dispose();
		}else {
			if(game.pause) {
				//Do nothing
			}else {
				game.deplacerPiece(game.piece_actuelle, game.DIR_BAS);
				if(!game.ok_deplacement) {
					game.retirerPieceTab(game.piece_memoire, game.position_memoire);
					game.VerifLigne();
					game.placerPiece(game.piece_next1);
					game.placerNextPiece(game.piece_next2, 1);
					game.placerNextPiece(game.piece_next3, 2);
					game.placerNextPiece(game.randomPiece(3), 3);
					game.ok_deplacement = true;
					game.memoire_utilisee = false;
					if(game.IA) {
						game.label_score.setText("Score : " + game.score + " --- Meilleur score : " + "meilleur_score" + " --- Score Moyen : " + "score_moyen");
					}else {
						game.label_score.setText("Score : " + game.score);
					}
				}
			}
		}
	}
}
