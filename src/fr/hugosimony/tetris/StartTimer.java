package fr.hugosimony.tetris;

import java.util.Timer;

public class StartTimer {

	public int difficulty;
	public Game game;

	public StartTimer(int difficulty, Game game) {
		this.difficulty = difficulty;
		this.game = game;
	}

	public void start() {
		Timer timer;
		timer = new Timer();
		timer.schedule(new Deplacement(game, difficulty), 700, 700/difficulty);
	}
}
