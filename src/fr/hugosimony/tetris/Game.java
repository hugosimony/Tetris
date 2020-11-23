package fr.hugosimony.tetris;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game extends JFrame {

	private static final long serialVersionUID = 1L;
	public int difficulty;
	public boolean IA;
	public boolean pause;
	public boolean stop;
	
	public int score = 0;
	
	public JLabel label_score = new JLabel();
	
	Bouton[][] tableau_bouton;
	
	Bouton tableau_bouton_next1[][];
	Bouton tableau_bouton_next2[][];
	Bouton tableau_bouton_next3[][];
	
	Bouton tableau_bouton_memoire[][];
	
	Font font1 = new Font("Arial", Font.BOLD, 40);
	Font font2 = new Font("Arial", Font.BOLD, 15);
	Font font3 = new Font("Arial", Font.BOLD, 25);
	Font font4 = new Font("Arial", Font.BOLD, 10);
	
	public int first_piece;
	public int piece_actuelle;
	public int position = 0;
	
	public int piece_memoire = 0;
	public int position_memoire = 0;
	
	public int piece_next1 = 0;
	public int piece_next2 = 0;
	public int piece_next3 = 0;
	
	public int piece_memory = 0;
	
	public boolean memoire_utilisee = false;
	
	public boolean ok_deplacement = true;
	
	public final int TETRIS = 1;
	public final int LIGNE = 2;
	public final int LDROITE = 3;
	public final int JGAUCHE = 4;
	public final int CARRE = 5;
	public final int SDROITE = 6;
	public final int ZGAUCHE = 7;
	
	public final int BASIQUE = 1;
	public final int DROITE = 2;
	public final int BASIQUE_INVERSEE = 3;
	public final int GAUCHE = 4;
	
	public final int DIR_BAS = 1;
	public final int DIR_DROITE = 2;
	public final int DIR_GAUCHE = 3;
	
	private static Color noir = new Color(0,0,0);
	public final Color mauve = new Color(160,0,100);
	private static final URL carre_mauveURL = Main.class.getResource("images/carré_mauve.jpg");
	public final ImageIcon carre_mauve = new ImageIcon(carre_mauveURL);
	public final Color orange = new Color(255,100,0);
	public final URL carre_orangeURL = Main.class.getResource("images/carré_orange.jpg");
	public final ImageIcon carre_orange = new ImageIcon(carre_orangeURL);
	public final Color bleu_fonce = new Color(0,0,255);
	public final URL carre_bleu_fonceURL = Main.class.getResource("images/carré_bleu_foncé.jpg");
	public final ImageIcon carre_bleu_fonce = new ImageIcon(carre_bleu_fonceURL);
	public final Color bleu_ciel = new Color(0,148,255);
	public final URL carre_bleu_cielURL = Main.class.getResource("images/carré_bleu_ciel.jpg");
	public final ImageIcon carre_bleu_ciel = new ImageIcon(carre_bleu_cielURL);
	public final Color jaune = new Color(255,216,0);
	public final URL carre_jauneURL = Main.class.getResource("images/carré_jaune.jpg");
	public final ImageIcon carre_jaune = new ImageIcon(carre_jauneURL);
	public final Color rouge = new Color(255,0,0);
	public final URL carre_rougeURL = Main.class.getResource("images/carré_rouge.jpg");
	public final ImageIcon carre_rouge = new ImageIcon(carre_rougeURL);
	public final Color vert = new Color(0,255,0);
	public final URL carre_vertURL = Main.class.getResource("images/carré_vert.jpg");
	public final ImageIcon carre_vert = new ImageIcon(carre_vertURL);
	private static final Color fond = new Color(100,100,100);
	private static final Color fond2 = new Color(170,230,255);
	
	public int tab[][] = {
			{11,11,11,11,11,11,11,11,11,11,11,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,0,0,0,0,0,0,0,0,0,0,11,},
			{11,11,11,11,11,11,11,11,11,11,11,11,},
		 };

	public Game(int difficulty, boolean IA) {
		
		this.difficulty = difficulty;
		this.IA = IA;
		
		this.setTitle("Tetris [Difficulté " + difficulty + "]     -----    Made by Hugo Simony-Jungo");
		this.setSize(680, 750);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
	    	public void windowClosed(WindowEvent e) {
	    		if(isVisible()){
	    			System.exit(0);
	    		}
	    	}
		});
		
		/*
		JFrame frame = new JFrame("test");
		frame.setSize(500,500);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		JLabel tetris;
		tetris = new JLabel(new ImageIcon("src/fr/hugosimony/tetris/images/tetris.jpg"));    
		JPanel panIcon = new JPanel();    
		panIcon.setBackground(Color.white);    
		panIcon.setLayout(new BorderLayout());    
		panIcon.add(tetris); 
		
		frame.add(panIcon);*/
		
		JPanel panel_principal = new JPanel();
		panel_principal.setLayout(new BorderLayout());
		panel_principal.setBackground(fond2);
		
		if(IA) {
			label_score.setText("Score : " + score + " --- Meilleur score : " + "meilleur_score" + " --- Score Moyen : " + "score_moyen");
			label_score.setFont(font2);
		}else {
			label_score.setText("Score : " + score);
			label_score.setFont(font3);
		}
		label_score.setHorizontalAlignment(JLabel.CENTER);
		label_score.setVerticalAlignment(JLabel.CENTER);
		
		panel_principal.add("North", label_score);
		
		JPanel panel_next = new JPanel();
		panel_next.setLayout(new GridLayout(4,1));
		panel_next.setBackground(fond2);
		
		JLabel label_next = new JLabel(" Pièces en attente : ");
		label_next.setFont(font2);
		label_next.setHorizontalAlignment(JLabel.CENTER);
		label_next.setVerticalAlignment(JLabel.CENTER);
		
		JPanel panel_next1 = new JPanel();
		panel_next1.setLayout(new GridLayout(6,5));
		panel_next1.setBackground(fond2);
		JPanel panel_next2 = new JPanel();
		panel_next2.setLayout(new GridLayout(6,5));
		panel_next2.setBackground(fond2);
		JPanel panel_next3 = new JPanel();
		panel_next3.setLayout(new GridLayout(6,5));
		panel_next3.setBackground(fond2);
		
		tableau_bouton_next1 = new Bouton[6][5];
		tableau_bouton_next2 = new Bouton[6][5];
		tableau_bouton_next3 = new Bouton[6][5];
		
		for(int i=0;i<6;i++) { 	
			for(int j=0;j<5;j++) {
				Bouton bouton = new Bouton(i,j,this);
				bouton.setBorder(null);
				if(i == 0 || i == 5 || j == 0 || j == 4) {
					bouton.setBackground(noir);
				}else
					bouton.setBackground(fond);
				panel_next1.add(bouton);
				tableau_bouton_next1[i][j] = bouton;
			}
		}
		
		for(int i=0;i<6;i++) { 	
			for(int j=0;j<5;j++) {
				Bouton bouton = new Bouton(i,j,this);
				bouton.setBorder(null);
				if(i == 0 || i == 5 || j == 0 || j == 4) {
					bouton.setBackground(noir);
				}else
					bouton.setBackground(fond);
				panel_next2.add(bouton);
				tableau_bouton_next2[i][j] = bouton;
			}
		}
		
		for(int i=0;i<6;i++) { 	
			for(int j=0;j<5;j++) {
				Bouton bouton = new Bouton(i,j,this);
				bouton.setBorder(null);
				if(i == 0 || i == 5 || j == 0 || j == 4) {
					bouton.setBackground(noir);
				}else
					bouton.setBackground(fond);
				panel_next3.add(bouton);
				tableau_bouton_next3[i][j] = bouton;
			}
		}
		
		panel_next.add(label_next);
		panel_next.add(panel_next1);
		panel_next.add(panel_next2);
		panel_next.add(panel_next3);
		
		panel_principal.add("East", panel_next);
		
		JPanel panel_memoire = new JPanel();
		panel_memoire.setLayout(new GridLayout(4,1));
		panel_memoire.setBackground(fond2);
		
		JLabel label_memoire = new JLabel(" Pièce en mémoire : ");
		label_memoire.setFont(font2);
		label_memoire.setHorizontalAlignment(JLabel.CENTER);
		label_memoire.setVerticalAlignment(JLabel.CENTER);
		
		JPanel panel_memoire1 = new JPanel();
		panel_memoire1.setLayout(new GridLayout(6,5));
		panel_memoire1.setBackground(fond2);
		
		tableau_bouton_memoire = new Bouton[6][5];
		
		for(int i=0;i<6;i++) { 	
			for(int j=0;j<5;j++) {
				Bouton bouton = new Bouton(i,j,this);
				bouton.setBorder(null);
				if(i == 0 || i == 5 || j == 0 || j == 4) {
					bouton.setBackground(noir);
				}else
					bouton.setBackground(fond);
				panel_memoire1.add(bouton);
				tableau_bouton_memoire[i][j] = bouton;
			}
		}
		
		panel_memoire.add(label_memoire);
		panel_memoire.add(panel_memoire1);
		
		panel_principal.add("West", panel_memoire);
		
		JPanel panel_game = new JPanel();
		panel_game.setLayout(new GridLayout(22,12));
		panel_game.setBackground(fond2);
		
		tableau_bouton = new Bouton[22][12];
		
		for(int i=0;i<22;i++) { 	
			for(int j=0;j<12;j++) {
				Bouton bouton = new Bouton(i,j,this);
				bouton.setBorder(null);
				panel_game.add(bouton);
				tableau_bouton[i][j] = bouton;
			}
		}
		
		panel_principal.add("Center", panel_game);
		
		JPanel panel_control = new JPanel();
		panel_control.setLayout(new GridLayout(3,1));
		panel_control.setBackground(fond2);
		
		JLabel label_control1 = new JLabel(" Utilisez les flêches droite/gauche/bas pour bouger la pièce, ");
		label_control1.setFont(font4);
		label_control1.setHorizontalAlignment(JLabel.CENTER);
		label_control1.setVerticalAlignment(JLabel.CENTER);
		JLabel label_control2 = new JLabel(" \"R\" pour tourner la pièce, espace pour descendre instantannément, ");
		label_control2.setFont(font4);
		label_control2.setHorizontalAlignment(JLabel.CENTER);
		label_control2.setVerticalAlignment(JLabel.CENTER);
		JLabel label_control3 = new JLabel(" utilisez \"E\" pour garder la pièce en mémoire et faîtes pause avec \"P\". ");
		label_control3.setFont(font4);
		label_control3.setHorizontalAlignment(JLabel.CENTER);
		label_control3.setVerticalAlignment(JLabel.CENTER);
		
		panel_control.add(label_control1);
		panel_control.add(label_control2);
		panel_control.add(label_control3);
		
		panel_principal.add("South", panel_control);
		
		this.add(panel_principal);
		
		first_piece = randomPiece(0);
		piece_memoire = first_piece;
		placerPiece(first_piece);
		placerNextPiece(randomPiece(1), 1);
		placerNextPiece(randomPiece(2), 2);
		placerNextPiece(randomPiece(3), 3);
		piece_actuelle = first_piece;
		
		StartTimer start_timer = new StartTimer(difficulty, this);
		start_timer.start();
		
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	public int randomPiece(int position) {
		int alea = 1 + (int)(Math.random() * 7);
		if(position == 1) {
			if(alea == piece_memoire)
				alea++;
			if(alea > 7)
				alea = alea-2;
		}
		if(position == 2) {
			if(alea == piece_next1)
				alea++;
			if(alea > 7)
				alea = alea-2;
		}
		if(position == 3) {
			if(alea == piece_next2)
				alea++;
			if(alea > 7)
				alea = alea-2;
		}
		piece_memoire = alea;
		return alea;
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	public void placerPiece(int piece) {
		
		piece_actuelle = piece;
		position = BASIQUE;
		boolean place_ok1 = true;
		boolean place_ok2 = false;
		
		if(piece == TETRIS) {
			
			for(int i=4; i<7; i++) {
				if(tab[1][i] != 0) {
					place_ok1 = false;
				}
				if(tab[1][i] == 0 && place_ok1) {
					tableau_bouton[1][i].setBackground(mauve);
					tableau_bouton[1][i].setIcon(carre_mauve);
					tab[1][i] = 2;
				}
			}
			if(place_ok1)
				tab[1][5] = 1;
			if(tab[2][5] == 0 && place_ok1) {
				tableau_bouton[2][5].setBackground(mauve);
				tableau_bouton[2][5].setIcon(carre_mauve);
				tab[2][5] = 2;
				place_ok2 = true;
			}
		}
		if(piece == LIGNE) {
			for(int i=4; i<8; i++) {
				if(tab[1][i] == 0 && place_ok1) {
					tableau_bouton[1][i].setBackground(bleu_ciel);
					tableau_bouton[1][i].setIcon(carre_bleu_ciel);
					tab[1][i] = 2;
					place_ok2 = true;
				}
			}
			if(place_ok1)
				tab[1][5] = 1;
		}
		if(piece == LDROITE) {
			for(int i=4; i<7; i++) {
				if(tab[1][i] != 0) {
					place_ok1 = false;
				}
				if(tab[1][i] == 0 && place_ok1) {
					tableau_bouton[1][i].setBackground(orange);
					tableau_bouton[1][i].setIcon(carre_orange);
					tab[1][i] = 2;
				}
			}
			if(place_ok1)
				tab[1][5] = 1;
			if(tab[2][4] == 0 && place_ok1) {
				tableau_bouton[2][4].setBackground(orange);
				tableau_bouton[2][4].setIcon(carre_orange);
				tab[2][4] = 2;
				place_ok2 = true;
			}
		}
		if(piece == JGAUCHE) {
			for(int i=4; i<7; i++) {
				if(tab[1][i] != 0) {
					place_ok1 = false;
				}
				if(tab[1][i] == 0 && place_ok1) {
					tableau_bouton[1][i].setBackground(bleu_fonce);
					tableau_bouton[1][i].setIcon(carre_bleu_fonce);
					tab[1][i] = 2;
				}
			}
			if(place_ok1)
				tab[1][5] = 1;
			if(tab[2][6] == 0 && place_ok1) {
				tableau_bouton[2][6].setBackground(bleu_fonce);
				tableau_bouton[2][6].setIcon(carre_bleu_fonce);
				tab[2][6] = 2;
				place_ok2 = true;
			}
		}
		if(piece == CARRE) {
			for(int i=1; i<3; i++) {
				for(int j=5; j<7; j++) {
					if(tab[i][j] != 0) {
						place_ok1 = false;
					}
					if(tab[i][j] == 0 && place_ok1) {
						tableau_bouton[i][j].setBackground(jaune);
						tableau_bouton[i][j].setIcon(carre_jaune);
						place_ok2 = true;
					}
				}
			}
			if(place_ok1)
				tab[1][5] = 1;
		}
		if(piece == SDROITE) {
			for(int i=5; i<7; i++) {
				if(tab[1][i] != 0) {
					place_ok1 = false;
				}
				if(tab[1][i] == 0 && place_ok1) {
					tableau_bouton[1][i].setBackground(rouge);
					tableau_bouton[1][i].setIcon(carre_rouge);
					tab[1][i] = 2;
				}
			}
			if(place_ok1)
				tab[1][5] = 1;
			place_ok2 = true;
			for(int j=4; j<6; j++) {
				if(tab[2][j] != 0) {
					place_ok2 = false;
				}
				if(tab[2][j] == 0 && place_ok2) {
					tableau_bouton[2][j].setBackground(rouge);
					tableau_bouton[2][j].setIcon(carre_rouge);
					tab[2][j] = 2;
				}
			}
		}
		if(piece == ZGAUCHE) {
			for(int i=4; i<6; i++) {
				if(tab[1][i] != 0) {
					place_ok1 = false;
				}
				if(tab[1][i] == 0 && place_ok1) {
					tableau_bouton[1][i].setBackground(vert);
					tableau_bouton[1][i].setIcon(carre_vert);
					tab[1][i] = 2;
				}
			}
			if(place_ok1)
				tab[1][5] = 1;
			place_ok2 = true;
			for(int j=5; j<7; j++) {
				if(tab[2][j] != 0) {
					place_ok2 = false;
				}
				if(tab[2][j] == 0 && place_ok2) {
					tableau_bouton[2][j].setBackground(vert);
					tableau_bouton[2][j].setIcon(carre_vert);
					tab[2][j] = 2;
				}
			}
		}
		
		if(!place_ok1 || !place_ok2) {
			stop = true;
			Fin fin = new Fin(score);
			fin.setVisible(true);
		}
		
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	public void tournerPiece(int piece, int position) {
		
		int i_memoire = 0;
		int j_memoire = 0;
		
		for(int i=0;i<22;i++) { 	
			for(int j=0;j<12;j++) {
				if(tab[i][j] == 1) {
					i_memoire = i;
					j_memoire = j;
				}
			}
		}
		
		boolean ok = false;
		
		if(piece == TETRIS) {
			
			if(position == BASIQUE) {
				
				if(tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+1][j_memoire+1] == 0)
					ok = true;
				
				if(ok) {
					
					tab[i_memoire][j_memoire-1] = 0;
					tab[i_memoire][j_memoire+1] = 0;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
					tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
					
					tab[i_memoire+2][j_memoire] = 2;
					tab[i_memoire+1][j_memoire+1] = 2;
					tableau_bouton[i_memoire+2][j_memoire].setBackground(mauve);
					tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_mauve);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(mauve);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_mauve);
					
					this.position = DROITE;
				}
			}else if(position == DROITE) {
				
				if(tab[i_memoire+1][j_memoire-1] == 0)
					ok = true;
				
				if(ok) {
					
					tab[i_memoire+2][j_memoire] = 0;
					tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);	
					tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
					tab[i_memoire+1][j_memoire-1] = 2;
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(mauve);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_mauve);
					
					this.position = BASIQUE_INVERSEE;
				}
			}else if(position == BASIQUE_INVERSEE) {
				
				if(tab[i_memoire+2][j_memoire] == 0)
					ok = true;
				
				if(ok) {
					
					tab[i_memoire+1][j_memoire+1] = 0;
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
					tab[i_memoire+2][j_memoire] = 2;
					tableau_bouton[i_memoire+2][j_memoire].setBackground(mauve);
					tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_mauve);
					
					this.position = GAUCHE;
				}
			}else if(position == GAUCHE) {
				
				if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire][j_memoire+1] == 0)
					ok = true;
				
				if(ok) {
					
					tab[i_memoire+1][j_memoire-1] = 0;
					tab[i_memoire+2][j_memoire] = 0;
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
					tab[i_memoire][j_memoire-1] = 2;
					tab[i_memoire][j_memoire+1] = 2;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(mauve);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_mauve);
					tableau_bouton[i_memoire][j_memoire+1].setBackground(mauve);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_mauve);
					
					this.position = BASIQUE;
				}
			}
		}
		
		if(piece == LIGNE) {
			
			if(position == BASIQUE) {
				
				ok = true;
				for(int i=i_memoire+1;i<i_memoire+4;i++) {
					if(tab[i][j_memoire] != 0)
						ok = false;
				}
				
				if(ok) {
					
					tab[i_memoire][j_memoire-1] = 0;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
					for(int j=j_memoire+1;j<j_memoire+3;j++) {
						tab[i_memoire][j] = 0;
						tableau_bouton[i_memoire][j].setBackground(fond);
						tableau_bouton[i_memoire][j].setIcon(null);
					}
					for(int i=i_memoire;i<i_memoire+4;i++) {
						tab[i][j_memoire] = 2;
						tableau_bouton[i][j_memoire].setBackground(bleu_ciel);
						tableau_bouton[i][j_memoire].setIcon(carre_bleu_ciel);
					}
					tab[i_memoire][j_memoire] = 1;
					
					this.position = BASIQUE_INVERSEE;
				}
			}else if (position == BASIQUE_INVERSEE) {
				
				ok = true;
				for(int j=j_memoire+1;j<j_memoire+4;j++) {
					if(tab[i_memoire][j] != 0)
						ok = false;
				}
				if(tab[i_memoire][j_memoire-1] != 0)
					ok = false;
				
				if(ok) {
					
					for(int i=i_memoire;i<i_memoire+4;i++) {
						tab[i][j_memoire] = 0;
						tableau_bouton[i][j_memoire].setBackground(fond);
						tableau_bouton[i][j_memoire].setIcon(null);
					}
					tab[i_memoire][j_memoire-1] = 2;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(bleu_ciel);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_bleu_ciel);
					for(int j=j_memoire;j<j_memoire+3;j++) {
						tab[i_memoire][j] = 2;
						tableau_bouton[i_memoire][j].setBackground(bleu_ciel);
						tableau_bouton[i_memoire][j].setIcon(carre_bleu_ciel);
					}
					tab[i_memoire][j_memoire] = 1;
					
					this.position = BASIQUE;
				}
			}
		}
		
		if(piece == LDROITE) {
			
			if(position == BASIQUE) {
				
				if(tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+2][j_memoire+1] == 0) 
					ok = true;
					
				if(ok) {
					
					tab[i_memoire][j_memoire-1] = 0;
					tab[i_memoire][j_memoire+1] = 0;
					tab[i_memoire+1][j_memoire-1] = 0;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
					tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
					tab[i_memoire+1][j_memoire] = 2;
					tab[i_memoire+2][j_memoire] = 2;
					tab[i_memoire+2][j_memoire+1] = 2;
					tableau_bouton[i_memoire+1][j_memoire].setBackground(orange);
					tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_orange);
					tableau_bouton[i_memoire+2][j_memoire].setBackground(orange);
					tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_orange);
					tableau_bouton[i_memoire+2][j_memoire+1].setBackground(orange);
					tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_orange);
					
					this.position = DROITE;
				}
			}else if(position == DROITE) {
				
				if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire-1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire][j_memoire] = 0;
					tab[i_memoire+2][j_memoire] = 0;
					tab[i_memoire+2][j_memoire+1] = 0;
					tableau_bouton[i_memoire][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);
					tab[i_memoire][j_memoire+1] = 2;
					tab[i_memoire+1][j_memoire+1] = 2;
					tab[i_memoire+1][j_memoire-1] = 2;
					tableau_bouton[i_memoire][j_memoire+1].setBackground(orange);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_orange);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(orange);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_orange);
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(orange);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_orange);
					tab[i_memoire+1][j_memoire] = 1;
				
					this.position = BASIQUE_INVERSEE;
				}
			}else if(position == BASIQUE_INVERSEE) {
				
				if(tab[i_memoire-1][j_memoire] == 0 && tab[i_memoire+1][j_memoire+1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire][j_memoire] = 0;
					tab[i_memoire][j_memoire-1] = 0;
					tableau_bouton[i_memoire][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire].setIcon(null);
					tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
					tab[i_memoire-1][j_memoire] = 2;
					tab[i_memoire+1][j_memoire+1] = 2;
					tableau_bouton[i_memoire-1][j_memoire].setBackground(orange);
					tableau_bouton[i_memoire-1][j_memoire].setIcon(carre_orange);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(orange);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_orange);
					tab[i_memoire-1][j_memoire] = 1;
				
					this.position = GAUCHE;
				}
				
			}else if(position == GAUCHE) {
				
				if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire+1][j_memoire+1] = 0;
					tab[i_memoire+2][j_memoire+1] = 0;
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);
					tab[i_memoire][j_memoire-1] = 2;
					tab[i_memoire+1][j_memoire-1] = 2;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(orange);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_orange);
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(orange);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_orange);
				
					this.position = BASIQUE;
				}
				
			}
		}
		
		if(piece == JGAUCHE) {
			
			if(position == BASIQUE) {
			
				if(tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+2][j_memoire-1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire][j_memoire+1] = 0;
					tab[i_memoire][j_memoire-1] = 0;
					tab[i_memoire+1][j_memoire+1] = 0;
					tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
					tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
					tab[i_memoire+1][j_memoire] = 2;
					tab[i_memoire+2][j_memoire] = 2;
					tab[i_memoire+2][j_memoire-1] = 2;
					tableau_bouton[i_memoire+1][j_memoire].setBackground(bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_bleu_fonce);
					tableau_bouton[i_memoire+2][j_memoire].setBackground(bleu_fonce);
					tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_bleu_fonce);
					tableau_bouton[i_memoire+2][j_memoire-1].setBackground(bleu_fonce);
					tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_bleu_fonce);
				
					this.position = DROITE;
				}
			}else if(position == DROITE) {
				
				if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire+1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire][j_memoire] = 0;
					tab[i_memoire+2][j_memoire] = 0;
					tab[i_memoire+2][j_memoire-1] = 0;
					tableau_bouton[i_memoire][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
					tab[i_memoire][j_memoire-1] = 2;
					tab[i_memoire+1][j_memoire-1] = 2;
					tab[i_memoire+1][j_memoire+1] = 2;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(bleu_fonce);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_bleu_fonce);
					tab[i_memoire+1][j_memoire] = 1;
				
					this.position = BASIQUE_INVERSEE;
				}
			}else if(position == BASIQUE_INVERSEE) {
				
				if(tab[i_memoire-1][j_memoire] == 0 && tab[i_memoire+1][j_memoire-1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire][j_memoire] = 0;
					tab[i_memoire][j_memoire+1] = 0;
					tableau_bouton[i_memoire][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire].setIcon(null);
					tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
					tab[i_memoire-1][j_memoire] = 2;
					tab[i_memoire+1][j_memoire-1] = 2;
					tableau_bouton[i_memoire-1][j_memoire].setBackground(bleu_fonce);
					tableau_bouton[i_memoire-1][j_memoire].setIcon(carre_bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_bleu_fonce);
					tab[i_memoire-1][j_memoire] = 1;
				
					this.position = GAUCHE;
				}
			}else if(position == GAUCHE) {
				
				if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire+1][j_memoire-1] = 0;
					tab[i_memoire+2][j_memoire-1] = 0;
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
					tab[i_memoire][j_memoire+1] = 2;
					tab[i_memoire+1][j_memoire+1] = 2;
					tableau_bouton[i_memoire][j_memoire+1].setBackground(bleu_fonce);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(bleu_fonce);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_bleu_fonce);
				
					this.position = BASIQUE;
				}
			}
		}
		
		if(piece == CARRE) {
			//Do nothing
		}
		
		if(piece == SDROITE) {
			
			if(position == BASIQUE) {
				
				if(tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+2][j_memoire+1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire][j_memoire+1] = 0;
					tab[i_memoire+1][j_memoire-1] = 0;
					tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
					tab[i_memoire+1][j_memoire+1] = 2;
					tab[i_memoire+2][j_memoire+1] = 2;
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(rouge);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_rouge);
					tableau_bouton[i_memoire+2][j_memoire+1].setBackground(rouge);
					tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_rouge);
				
					this.position = BASIQUE_INVERSEE;
				}
			}else if(position == BASIQUE_INVERSEE) {
				
				if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire-1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire+1][j_memoire+1] = 0;
					tab[i_memoire+2][j_memoire+1] = 0;
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);
					tab[i_memoire][j_memoire+1] = 2;
					tab[i_memoire+1][j_memoire-1] = 2;
					tableau_bouton[i_memoire][j_memoire+1].setBackground(rouge);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_rouge);
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(rouge);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_rouge);
				
					this.position = BASIQUE;
				}
			}
		}
		
		if(piece == ZGAUCHE) {
			
			if(position == BASIQUE) {
				
				if(tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+2][j_memoire-1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire][j_memoire-1] = 0;
					tab[i_memoire+1][j_memoire+1] = 0;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
					tab[i_memoire+1][j_memoire-1] = 2;
					tab[i_memoire+2][j_memoire-1] = 2;
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(vert);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_vert);
					tableau_bouton[i_memoire+2][j_memoire-1].setBackground(vert);
					tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_vert);
				
					this.position = BASIQUE_INVERSEE;
				}
			}else if(position == BASIQUE_INVERSEE) {
				
				if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire+1] == 0) 
					ok = true;
				
				if(ok) {
					
					tab[i_memoire+1][j_memoire-1] = 0;
					tab[i_memoire+2][j_memoire-1] = 0;
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
					tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);
					tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
					tab[i_memoire][j_memoire-1] = 2;
					tab[i_memoire+1][j_memoire+1] = 2;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(vert);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_vert);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(vert);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_vert);
				
					this.position = BASIQUE;
				}
			}
		}
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	public void deplacerPiece(int piece, int direction) {
		
		int i_memoire = 0;
		int j_memoire = 0;
		
		for(int i=0;i<22;i++) {
			for(int j=0;j<12;j++) {
				if(tab[i][j] == 1) {
					i_memoire = i;
					j_memoire = j;
				}
			}
		}
		
		boolean ok = false;
		
		if(direction == DIR_BAS) {
			
			if(piece == TETRIS) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire-1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tab[i_memoire+2][j_memoire] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tableau_bouton[i_memoire+2][j_memoire].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_mauve);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_mauve);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				
				if(position == DROITE) {
					
					if(tab[i_memoire+3][j_memoire] == 0 && tab[i_memoire+2][j_memoire+1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tab[i_memoire+3][j_memoire] = 2;
						tab[i_memoire+2][j_memoire+1] = 2;
						tableau_bouton[i_memoire+3][j_memoire].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire+3][j_memoire].setIcon(carre_mauve);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_mauve);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+2][j_memoire+1] == 0 && tab[i_memoire+2][j_memoire-1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tab[i_memoire+2][j_memoire] = 2;
						tab[i_memoire+2][j_memoire+1] = 2;
						tab[i_memoire+2][j_memoire-1] = 2;
						tableau_bouton[i_memoire+2][j_memoire].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_mauve);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_mauve);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == GAUCHE) {
				
					if(tab[i_memoire+2][j_memoire-1] == 0 && tab[i_memoire+3][j_memoire] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tab[i_memoire+2][j_memoire-1] = 2;
						tab[i_memoire+3][j_memoire] = 2;
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire+3][j_memoire].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+3][j_memoire].setIcon(carre_mauve);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
			}
			
			if(piece == LIGNE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+2] == 0) 
						ok = true;
					
					if(ok) {
						
						for(int j = j_memoire-1; j<j_memoire+3; j++) {
							tab[i_memoire][j] = 0;
							tableau_bouton[i_memoire][j].setBackground(fond);
							tableau_bouton[i_memoire][j].setIcon(null);
						}
						for(int j = j_memoire-1; j<j_memoire+3; j++) {
							tab[i_memoire+1][j] = 2;
							tableau_bouton[i_memoire+1][j].setBackground(bleu_ciel);
							tableau_bouton[i_memoire+1][j].setIcon(carre_bleu_ciel);
						}
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
					
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire+4][j_memoire] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tab[i_memoire+4][j_memoire] = 2;
						tableau_bouton[i_memoire+4][j_memoire].setBackground(bleu_ciel);
						tableau_bouton[i_memoire+4][j_memoire].setIcon(carre_bleu_ciel);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
			}
			
			if(piece == LDROITE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire+2][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+1][j_memoire+1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tab[i_memoire+2][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(orange);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_orange);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == DROITE) {
					
					if(tab[i_memoire+3][j_memoire] == 0 && tab[i_memoire+3][j_memoire+1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+2][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);
						tab[i_memoire+3][j_memoire] = 2;
						tab[i_memoire+3][j_memoire+1] = 2;
						tableau_bouton[i_memoire+3][j_memoire].setBackground(orange);
						tableau_bouton[i_memoire+3][j_memoire+1].setBackground(orange);
						tableau_bouton[i_memoire+3][j_memoire].setIcon(carre_orange);
						tableau_bouton[i_memoire+3][j_memoire+1].setIcon(carre_orange);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == BASIQUE_INVERSEE) {
	
					if(tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+1][j_memoire+1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire-1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);	
						tableau_bouton[i_memoire-1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire-1][j_memoire+1].setIcon(null);
						tab[i_memoire+1][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_orange);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == GAUCHE) {
	
					if(tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+3][j_memoire+1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tab[i_memoire+1][j_memoire] = 2;
						tab[i_memoire+3][j_memoire+1] = 2;
						tableau_bouton[i_memoire+1][j_memoire].setBackground(orange);
						tableau_bouton[i_memoire+3][j_memoire+1].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_orange);
						tableau_bouton[i_memoire+3][j_memoire+1].setIcon(carre_orange);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
			}
			
			if(piece == JGAUCHE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire+2][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+1][j_memoire-1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tab[i_memoire+2][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_bleu_fonce);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == DROITE) {
					if(tab[i_memoire+3][j_memoire] == 0 && tab[i_memoire+3][j_memoire-1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+2][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);	
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
						tab[i_memoire+3][j_memoire-1] = 2;
						tab[i_memoire+3][j_memoire] = 2;
						tableau_bouton[i_memoire+3][j_memoire-1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+3][j_memoire].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+3][j_memoire-1].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+3][j_memoire].setIcon(carre_bleu_fonce);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+1][j_memoire+1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire-1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire-1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire-1][j_memoire-1].setIcon(null);
						tab[i_memoire+1][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_bleu_fonce);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == GAUCHE) {
					if(tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+3][j_memoire-1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);	
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tab[i_memoire+1][j_memoire] = 2;
						tab[i_memoire+3][j_memoire-1] = 2;
						tableau_bouton[i_memoire+1][j_memoire].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+3][j_memoire-1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+3][j_memoire-1].setIcon(carre_bleu_fonce);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
			}
			
			if(piece == CARRE) {
				
				if(tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+2][j_memoire+1] == 0) 
					ok = true;
					
				if(ok) {
						
					tab[i_memoire][j_memoire] = 0;
					tab[i_memoire][j_memoire+1] = 0;
					tableau_bouton[i_memoire][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire].setIcon(null);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
					tab[i_memoire+2][j_memoire] = 2;
					tab[i_memoire+2][j_memoire+1] = 2;
					tableau_bouton[i_memoire+2][j_memoire].setBackground(jaune);
					tableau_bouton[i_memoire+2][j_memoire+1].setBackground(jaune);
					tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_jaune);
					tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_jaune);
					tab[i_memoire+1][j_memoire] = 1;
						
				}else {
					ok_deplacement = false;
					position_memoire = position;
					piece_memoire = piece;
				}
			}
			
			if(piece == SDROITE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+2][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire+1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tab[i_memoire+2][j_memoire-1] = 2;
						tab[i_memoire+2][j_memoire] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tableau_bouton[i_memoire+2][j_memoire].setBackground(rouge);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(rouge);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(rouge);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_rouge);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_rouge);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_rouge);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+3][j_memoire+1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);	
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);	
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tab[i_memoire+2][j_memoire] = 2;
						tab[i_memoire+3][j_memoire+1] = 2;
						tableau_bouton[i_memoire+2][j_memoire].setBackground(rouge);
						tableau_bouton[i_memoire+3][j_memoire+1].setBackground(rouge);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_rouge);
						tableau_bouton[i_memoire+3][j_memoire+1].setIcon(carre_rouge);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
			}
			
			if(piece == ZGAUCHE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+2][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire-1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);	
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tab[i_memoire+2][j_memoire] = 2;
						tab[i_memoire+2][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tableau_bouton[i_memoire+2][j_memoire].setBackground(vert);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(vert);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(vert);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_vert);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_vert);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_vert);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire+2][j_memoire] == 0 && tab[i_memoire+3][j_memoire-1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);	
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tab[i_memoire+2][j_memoire] = 2;
						tab[i_memoire+3][j_memoire-1] = 2;
						tableau_bouton[i_memoire+2][j_memoire].setBackground(vert);
						tableau_bouton[i_memoire+3][j_memoire-1].setBackground(vert);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_vert);
						tableau_bouton[i_memoire+3][j_memoire-1].setIcon(carre_vert);
						tab[i_memoire+1][j_memoire] = 1;
						
					}else {
						ok_deplacement = false;
						position_memoire = position;
						piece_memoire = piece;
					}
				}
			}
		}
		
		if(direction == DIR_DROITE) {
			
			if(piece == TETRIS) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire+2] == 0 && tab[i_memoire+1][j_memoire+1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire+2] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tableau_bouton[i_memoire][j_memoire+2].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire][j_memoire+2].setIcon(carre_mauve);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_mauve);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire+1] = 1;
					}
				}	
				if(position == DROITE) {
					
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+2] == 0 && tab[i_memoire+2][j_memoire+1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+2] = 2;
						tab[i_memoire+2][j_memoire+1] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire+2].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+1][j_memoire+2].setIcon(carre_mauve);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_mauve);
						tab[i_memoire][j_memoire+1] = 1;
					}			
				}	
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire+1][j_memoire+2] == 0 && tab[i_memoire][j_memoire+1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+2] = 2;
						tableau_bouton[i_memoire+1][j_memoire+2].setBackground(mauve);
						tableau_bouton[i_memoire][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire+2].setIcon(carre_mauve);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_mauve);
						tab[i_memoire][j_memoire+1] = 1;
					}
				}	
				if(position == GAUCHE) {
					
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+2][j_memoire+1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tab[i_memoire+2][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tab[i_memoire+2][j_memoire+1] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(mauve);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_mauve);
						tab[i_memoire][j_memoire+1] = 1;
					}
				}	
			}
			
			if(piece == LIGNE) {
				
				if(position == BASIQUE) {
					if(tab[i_memoire][j_memoire+3] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire+3] = 2;
						tableau_bouton[i_memoire][j_memoire+3].setBackground(bleu_ciel);
						tableau_bouton[i_memoire][j_memoire+3].setIcon(carre_bleu_ciel);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire+1] = 1;
						
					}
				}
				if(position == BASIQUE_INVERSEE) {
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+2][j_memoire+1] == 0 && tab[i_memoire+2][j_memoire+1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire] = 0;
						tab[i_memoire+3][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+3][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+3][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tab[i_memoire+2][j_memoire+1] = 2;
						tab[i_memoire+3][j_memoire+1] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(bleu_ciel);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(bleu_ciel);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(bleu_ciel);
						tableau_bouton[i_memoire+3][j_memoire+1].setBackground(bleu_ciel);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_bleu_ciel);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_bleu_ciel);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_bleu_ciel);
						tableau_bouton[i_memoire+3][j_memoire+1].setIcon(carre_bleu_ciel);
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
			}
			
			if(piece == LDROITE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire+2] == 0 && tab[i_memoire+1][j_memoire] == 0) 
						ok = true;
				
					if(ok) {
						
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire+2] = 2;
						tab[i_memoire+1][j_memoire] = 2;
						tableau_bouton[i_memoire][j_memoire+2].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(orange);
						tableau_bouton[i_memoire][j_memoire+2].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_orange);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
				if(position == DROITE) {
					
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+2][j_memoire+2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tab[i_memoire+2][j_memoire+2] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(orange);
						tableau_bouton[i_memoire+2][j_memoire+2].setBackground(orange);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_orange);
						tableau_bouton[i_memoire+2][j_memoire+2].setIcon(carre_orange);
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
				if(position == BASIQUE_INVERSEE) {
	
					if(tab[i_memoire][j_memoire+2] == 0 && tab[i_memoire-1][j_memoire+2] == 0) 
						ok = true;
				
					if(ok) {
						
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire-1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire-1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire-1][j_memoire+1].setIcon(null);
						tab[i_memoire-1][j_memoire+2] = 2;
						tab[i_memoire][j_memoire+2] = 2;
						tableau_bouton[i_memoire-1][j_memoire+2].setBackground(orange);
						tableau_bouton[i_memoire][j_memoire+2].setBackground(orange);
						tableau_bouton[i_memoire-1][j_memoire+2].setIcon(carre_orange);
						tableau_bouton[i_memoire][j_memoire+2].setIcon(carre_orange);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
				if(position == GAUCHE) {
	
					if(tab[i_memoire][j_memoire+2] == 0 && tab[i_memoire+1][j_memoire+2] == 0 && tab[i_memoire+2][j_memoire+2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tab[i_memoire+2][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire+2] = 2;
						tab[i_memoire+1][j_memoire+2] = 2;
						tab[i_memoire+2][j_memoire+2] = 2;
						tableau_bouton[i_memoire][j_memoire+2].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire+2].setBackground(orange);
						tableau_bouton[i_memoire+2][j_memoire+2].setBackground(orange);
						tableau_bouton[i_memoire][j_memoire+2].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire+2].setIcon(carre_orange);
						tableau_bouton[i_memoire+2][j_memoire+2].setIcon(carre_orange);
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
			}
			
			if(piece == JGAUCHE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire+2] == 0 && tab[i_memoire+1][j_memoire+2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire+2] = 2;
						tab[i_memoire+1][j_memoire+2] = 2;
						tableau_bouton[i_memoire][j_memoire+2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire+2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire][j_memoire+2].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire+2].setIcon(carre_bleu_fonce);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
				if(position == DROITE) {
					
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+2][j_memoire+1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tab[i_memoire+2][j_memoire+1] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(carre_bleu_fonce);
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire-1][j_memoire] == 0 && tab[i_memoire][j_memoire+2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire-1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire-1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire-1][j_memoire-1].setIcon(null);
						tab[i_memoire-1][j_memoire] = 2;
						tab[i_memoire][j_memoire+2] = 2;
						tableau_bouton[i_memoire-1][j_memoire].setBackground(bleu_fonce);
						tableau_bouton[i_memoire][j_memoire+2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire-1][j_memoire].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire][j_memoire+2].setIcon(carre_bleu_fonce);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
				if(position == GAUCHE) {
					
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+2][j_memoire] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tab[i_memoire+2][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire] = 2;
						tab[i_memoire+2][j_memoire] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(bleu_fonce);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_bleu_fonce);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire+1] = 1;
					}
				}
			}
			
			if(piece == CARRE) {
				
				if(tab[i_memoire][j_memoire+2] == 0 && tab[i_memoire+1][j_memoire+2] == 0) 
					ok = true;
					
				if(ok) {
						
					tab[i_memoire][j_memoire] = 0;
					tab[i_memoire+1][j_memoire] = 0;
					tableau_bouton[i_memoire][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire].setIcon(null);
					tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
					tab[i_memoire][j_memoire+2] = 2;
					tab[i_memoire+1][j_memoire+2] = 2;
					tableau_bouton[i_memoire][j_memoire+2].setBackground(jaune);
					tableau_bouton[i_memoire+1][j_memoire+2].setBackground(jaune);
					tableau_bouton[i_memoire][j_memoire+2].setIcon(carre_jaune);
					tableau_bouton[i_memoire+1][j_memoire+2].setIcon(carre_jaune);
					tab[i_memoire][j_memoire+1] = 1;
						
				}
			}
			
			if(piece == SDROITE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire+2] == 0 && tab[i_memoire+1][j_memoire+1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);	
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);	
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);	
						tab[i_memoire][j_memoire+2] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tableau_bouton[i_memoire][j_memoire+2].setBackground(rouge);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(rouge);
						tableau_bouton[i_memoire][j_memoire+2].setIcon(carre_rouge);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_rouge);
						tab[i_memoire][j_memoire+1] = 1;
						
					}
				}
				if(position == BASIQUE_INVERSEE) {
				
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+2] == 0 && tab[i_memoire+2][j_memoire+2] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);	
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);	
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);	
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);	
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+2] = 2;
						tab[i_memoire+2][j_memoire+2] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(rouge);
						tableau_bouton[i_memoire+1][j_memoire+2].setBackground(rouge);
						tableau_bouton[i_memoire+2][j_memoire+2].setBackground(rouge);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_rouge);
						tableau_bouton[i_memoire+1][j_memoire+2].setIcon(carre_rouge);
						tableau_bouton[i_memoire+2][j_memoire+2].setIcon(carre_rouge);
						tab[i_memoire][j_memoire+1] = 1;
						
					}
				}
			}
			
			if(piece == ZGAUCHE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+2] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire-1] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+2] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(vert);
						tableau_bouton[i_memoire+1][j_memoire+2].setBackground(vert);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_vert);
						tableau_bouton[i_memoire+1][j_memoire+2].setIcon(carre_vert);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire+1] = 1;
						
					}
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire][j_memoire+1] == 0 && tab[i_memoire+1][j_memoire+1] == 0 && tab[i_memoire+2][j_memoire] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tab[i_memoire+2][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);	
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire+1] = 2;
						tab[i_memoire+1][j_memoire+1] = 2;
						tab[i_memoire+2][j_memoire] = 2;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(vert);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(vert);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(vert);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(carre_vert);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(carre_vert);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_vert);
						tab[i_memoire][j_memoire+1] = 1;
						
					}
				}
			} 
		}

		if(direction == DIR_GAUCHE) {
			
			if(piece == TETRIS) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire-2] == 0 && tab[i_memoire+1][j_memoire-1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire-2] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tableau_bouton[i_memoire][j_memoire-2].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire][j_memoire-2].setIcon(carre_mauve);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_mauve);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire-1] = 1;
					}
				}	
				if(position == DROITE) {
					
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+2][j_memoire-1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+2][j_memoire] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tab[i_memoire+2][j_memoire-1] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_mauve);
						tab[i_memoire][j_memoire-1] = 1;
					}						
				}	
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire+1][j_memoire-2] == 0 && tab[i_memoire][j_memoire-1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-2] = 2;
						tableau_bouton[i_memoire+1][j_memoire-2].setBackground(mauve);
						tableau_bouton[i_memoire][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire-2].setIcon(carre_mauve);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_mauve);
						tab[i_memoire][j_memoire-1] = 1;
					}
				}	
				if(position == GAUCHE) {
					
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-2] == 0 && tab[i_memoire+2][j_memoire-1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-2] = 2;
						tab[i_memoire+2][j_memoire-1] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire+1][j_memoire-2].setBackground(mauve);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(mauve);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_mauve);
						tableau_bouton[i_memoire+1][j_memoire-2].setIcon(carre_mauve);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_mauve);
						tab[i_memoire][j_memoire-1] = 1;
					}
				}	
			}
			
			if(piece == LIGNE) {
				
				if(position == BASIQUE) {
					if(tab[i_memoire][j_memoire-2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire+2] = 0;
						tableau_bouton[i_memoire][j_memoire+2].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+2].setIcon(null);
						tab[i_memoire][j_memoire-2] = 2;
						tableau_bouton[i_memoire][j_memoire-2].setBackground(bleu_ciel);
						tableau_bouton[i_memoire][j_memoire-2].setIcon(carre_bleu_ciel);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire-1] = 1;
						
					}
				}
				if(position == BASIQUE_INVERSEE) {
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+2][j_memoire-1] == 0 && tab[i_memoire+2][j_memoire-1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire] = 0;
						tab[i_memoire+3][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+3][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+3][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tab[i_memoire+2][j_memoire-1] = 2;
						tab[i_memoire+3][j_memoire-1] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(bleu_ciel);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(bleu_ciel);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(bleu_ciel);
						tableau_bouton[i_memoire+3][j_memoire-1].setBackground(bleu_ciel);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_bleu_ciel);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_bleu_ciel);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_bleu_ciel);
						tableau_bouton[i_memoire+3][j_memoire-1].setIcon(carre_bleu_ciel);
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
			}
			
			if(piece == LDROITE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire-2] == 0 && tab[i_memoire+1][j_memoire-2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire-2] = 2;
						tab[i_memoire+1][j_memoire-2] = 2;
						tableau_bouton[i_memoire+1][j_memoire-2].setBackground(orange);
						tableau_bouton[i_memoire][j_memoire-2].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire-2].setIcon(carre_orange);
						tableau_bouton[i_memoire][j_memoire-2].setIcon(carre_orange);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
				if(position == DROITE) {
					
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+2][j_memoire-1] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tab[i_memoire+2][j_memoire-1] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(orange);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(orange);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_orange);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(carre_orange);
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
				if(position == BASIQUE_INVERSEE) {
	
					if(tab[i_memoire][j_memoire-2] == 0 && tab[i_memoire-1][j_memoire] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire-1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire-1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire-1][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire-2] = 2;
						tab[i_memoire-1][j_memoire] = 2;
						tableau_bouton[i_memoire][j_memoire-2].setBackground(orange);
						tableau_bouton[i_memoire-1][j_memoire].setBackground(orange);
						tableau_bouton[i_memoire][j_memoire-2].setIcon(carre_orange);
						tableau_bouton[i_memoire-1][j_memoire].setIcon(carre_orange);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
				if(position == GAUCHE) {
					
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire] == 0 && tab[i_memoire+2][j_memoire] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tab[i_memoire+2][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire] = 2;
						tab[i_memoire+2][j_memoire] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(orange);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(orange);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(orange);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_orange);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_orange);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_orange);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
			}
			
			if(piece == JGAUCHE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire-2] == 0 && tab[i_memoire+1][j_memoire] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire-2] = 2;
						tab[i_memoire+1][j_memoire] = 2;
						tableau_bouton[i_memoire][j_memoire-2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(bleu_fonce);
						tableau_bouton[i_memoire][j_memoire-2].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(carre_bleu_fonce);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
				if(position == DROITE) {
					
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+2][j_memoire-2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(null);
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tab[i_memoire+2][j_memoire-2] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire-2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire-2].setIcon(carre_bleu_fonce);
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire-1][j_memoire-2] == 0 && tab[i_memoire][j_memoire-2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire-1][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire-1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
						tableau_bouton[i_memoire-1][j_memoire-1].setIcon(null);
						tab[i_memoire-1][j_memoire-2] = 2;
						tab[i_memoire][j_memoire-2] = 2;
						tableau_bouton[i_memoire-1][j_memoire-2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire][j_memoire-2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire-1][j_memoire-2].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire][j_memoire-2].setIcon(carre_bleu_fonce);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
				if(position == GAUCHE) {
					
					if(tab[i_memoire][j_memoire-2] == 0 && tab[i_memoire+1][j_memoire-2] == 0 && tab[i_memoire+2][j_memoire-2] == 0) 
						ok = true;
					
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire-1] = 0;
						tab[i_memoire+2][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire-2] = 2;
						tab[i_memoire+1][j_memoire-2] = 2;
						tab[i_memoire+2][j_memoire-2] = 2;
						tableau_bouton[i_memoire][j_memoire-2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire-2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire-2].setBackground(bleu_fonce);
						tableau_bouton[i_memoire][j_memoire-2].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+1][j_memoire-2].setIcon(carre_bleu_fonce);
						tableau_bouton[i_memoire+2][j_memoire-2].setIcon(carre_bleu_fonce);
						tab[i_memoire][j_memoire-1] = 1;
					}
				}
			}
			
			if(piece == CARRE) {
				
				if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-1] == 0) 
					ok = true;
					
				if(ok) {
						
					tab[i_memoire][j_memoire+1] = 0;
					tab[i_memoire+1][j_memoire+1] = 0;
					tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);
					tableau_bouton[i_memoire][j_memoire+1].setIcon(null);
					tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
					tab[i_memoire][j_memoire-1] = 2;
					tab[i_memoire+1][j_memoire-1] = 2;
					tableau_bouton[i_memoire][j_memoire-1].setBackground(jaune);
					tableau_bouton[i_memoire+1][j_memoire-1].setBackground(jaune);
					tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_jaune);
					tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_jaune);
					tab[i_memoire][j_memoire] = 2;
					tab[i_memoire][j_memoire-1] = 1;
						
				}
			}
			
			if(piece == SDROITE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-2] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire+1] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tableau_bouton[i_memoire][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire+1].setIcon(null);	
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);	
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-2] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(rouge);
						tableau_bouton[i_memoire+1][j_memoire-2].setBackground(rouge);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_rouge);
						tableau_bouton[i_memoire+1][j_memoire-2].setIcon(carre_rouge);
						tab[i_memoire][j_memoire] = 2;
						tab[i_memoire][j_memoire-1] = 1;
						
					}
				}
				if(position == BASIQUE_INVERSEE) {
				
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-1] == 0 && tab[i_memoire+2][j_memoire] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tab[i_memoire+2][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire+2][j_memoire+1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);	
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);		
						tableau_bouton[i_memoire+2][j_memoire+1].setIcon(null);	
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tab[i_memoire+2][j_memoire] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(rouge);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(rouge);
						tableau_bouton[i_memoire+2][j_memoire].setBackground(rouge);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_rouge);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_rouge);
						tableau_bouton[i_memoire+2][j_memoire].setIcon(carre_rouge);
						tab[i_memoire][j_memoire-1] = 1;
						
					}
				}
			}
			
			if(piece == ZGAUCHE) {
				
				if(position == BASIQUE) {
					
					if(tab[i_memoire][j_memoire-2] == 0 && tab[i_memoire+1][j_memoire-1] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire+1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire+1].setBackground(fond);	
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire+1].setIcon(null);
						tab[i_memoire][j_memoire-2] = 2;
						tab[i_memoire+1][j_memoire-1] = 2;
						tableau_bouton[i_memoire][j_memoire-2].setBackground(vert);
						tableau_bouton[i_memoire+1][j_memoire-1].setBackground(vert);
						tableau_bouton[i_memoire][j_memoire-2].setIcon(carre_vert);
						tableau_bouton[i_memoire+1][j_memoire-1].setIcon(carre_vert);
						tab[i_memoire][j_memoire-1] = 1;
						
					}
				}
				if(position == BASIQUE_INVERSEE) {
					
					if(tab[i_memoire][j_memoire-1] == 0 && tab[i_memoire+1][j_memoire-2] == 0 && tab[i_memoire+2][j_memoire-2] == 0)
						ok = true;
										
					if(ok) {
						
						tab[i_memoire][j_memoire] = 0;
						tab[i_memoire+1][j_memoire] = 0;
						tab[i_memoire+2][j_memoire-1] = 0;
						tableau_bouton[i_memoire][j_memoire].setBackground(fond);
						tableau_bouton[i_memoire+1][j_memoire].setBackground(fond);	
						tableau_bouton[i_memoire+2][j_memoire-1].setBackground(fond);
						tableau_bouton[i_memoire][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+1][j_memoire].setIcon(null);
						tableau_bouton[i_memoire+2][j_memoire-1].setIcon(null);
						tab[i_memoire][j_memoire-1] = 2;
						tab[i_memoire+1][j_memoire-2] = 2;
						tab[i_memoire+2][j_memoire-2] = 2;
						tableau_bouton[i_memoire][j_memoire-1].setBackground(vert);
						tableau_bouton[i_memoire+1][j_memoire-2].setBackground(vert);
						tableau_bouton[i_memoire+2][j_memoire-2].setBackground(vert);
						tableau_bouton[i_memoire][j_memoire-1].setIcon(carre_vert);
						tableau_bouton[i_memoire+1][j_memoire-2].setIcon(carre_vert);
						tableau_bouton[i_memoire+2][j_memoire-2].setIcon(carre_vert);
						tab[i_memoire][j_memoire-1] = 1;
						
					}
				}
			}
		}
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	public void descenteInstant(int piece) {
		
		
		
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	public void placerNextPiece(int piece, int classement) {
		
		if(classement == 1) {
			piece_next1 = piece;
			
			for(int i=1;i<5;i++) { 	
				for(int j=1;j<4;j++) {
					tableau_bouton_next1[i][j].setBackground(fond);
				}
			}
			
			if(piece == TETRIS) {
				tableau_bouton_next1[2][1].setBackground(mauve);
				tableau_bouton_next1[2][2].setBackground(mauve);
				tableau_bouton_next1[2][3].setBackground(mauve);
				tableau_bouton_next1[3][2].setBackground(mauve);
			}
			if(piece == LIGNE) {
				tableau_bouton_next1[1][2].setBackground(bleu_ciel);
				tableau_bouton_next1[2][2].setBackground(bleu_ciel);
				tableau_bouton_next1[3][2].setBackground(bleu_ciel);
				tableau_bouton_next1[4][2].setBackground(bleu_ciel);
			}
			if(piece == LDROITE) {
				tableau_bouton_next1[1][2].setBackground(orange);
				tableau_bouton_next1[2][2].setBackground(orange);
				tableau_bouton_next1[3][2].setBackground(orange);
				tableau_bouton_next1[3][3].setBackground(orange);
			}
			if(piece == JGAUCHE) {
				tableau_bouton_next1[1][2].setBackground(bleu_fonce);
				tableau_bouton_next1[2][2].setBackground(bleu_fonce);
				tableau_bouton_next1[3][2].setBackground(bleu_fonce);
				tableau_bouton_next1[3][1].setBackground(bleu_fonce);
			}
			if(piece == CARRE) {
				tableau_bouton_next1[2][1].setBackground(jaune);
				tableau_bouton_next1[2][2].setBackground(jaune);
				tableau_bouton_next1[3][1].setBackground(jaune);
				tableau_bouton_next1[3][2].setBackground(jaune);
			}
			if(piece == SDROITE) {
				tableau_bouton_next1[2][3].setBackground(rouge);
				tableau_bouton_next1[2][2].setBackground(rouge);
				tableau_bouton_next1[3][2].setBackground(rouge);
				tableau_bouton_next1[3][1].setBackground(rouge);
			}
			if(piece == ZGAUCHE) {
				tableau_bouton_next1[2][1].setBackground(vert);
				tableau_bouton_next1[2][2].setBackground(vert);
				tableau_bouton_next1[3][2].setBackground(vert);
				tableau_bouton_next1[3][3].setBackground(vert);
			}
		}
		
		if(classement == 2) {
			piece_next2 = piece;
			
			for(int i=1;i<5;i++) { 	
				for(int j=1;j<4;j++) {
					tableau_bouton_next2[i][j].setBackground(fond);
				}
			}
			
			if(piece == TETRIS) {
				tableau_bouton_next2[2][1].setBackground(mauve);
				tableau_bouton_next2[2][2].setBackground(mauve);
				tableau_bouton_next2[2][3].setBackground(mauve);
				tableau_bouton_next2[3][2].setBackground(mauve);
			}
			if(piece == LIGNE) {
				tableau_bouton_next2[1][2].setBackground(bleu_ciel);
				tableau_bouton_next2[2][2].setBackground(bleu_ciel);
				tableau_bouton_next2[3][2].setBackground(bleu_ciel);
				tableau_bouton_next2[4][2].setBackground(bleu_ciel);
			}
			if(piece == LDROITE) {
				tableau_bouton_next2[1][2].setBackground(orange);
				tableau_bouton_next2[2][2].setBackground(orange);
				tableau_bouton_next2[3][2].setBackground(orange);
				tableau_bouton_next2[3][3].setBackground(orange);
			}
			if(piece == JGAUCHE) {
				tableau_bouton_next2[1][2].setBackground(bleu_fonce);
				tableau_bouton_next2[2][2].setBackground(bleu_fonce);
				tableau_bouton_next2[3][2].setBackground(bleu_fonce);
				tableau_bouton_next2[3][1].setBackground(bleu_fonce);
			}
			if(piece == CARRE) {
				tableau_bouton_next2[2][1].setBackground(jaune);
				tableau_bouton_next2[2][2].setBackground(jaune);
				tableau_bouton_next2[3][1].setBackground(jaune);
				tableau_bouton_next2[3][2].setBackground(jaune);
			}
			if(piece == SDROITE) {
				tableau_bouton_next2[2][3].setBackground(rouge);
				tableau_bouton_next2[2][2].setBackground(rouge);
				tableau_bouton_next2[3][2].setBackground(rouge);
				tableau_bouton_next2[3][1].setBackground(rouge);
			}
			if(piece == ZGAUCHE) {
				tableau_bouton_next2[2][1].setBackground(vert);
				tableau_bouton_next2[2][2].setBackground(vert);
				tableau_bouton_next2[3][2].setBackground(vert);
				tableau_bouton_next2[3][3].setBackground(vert);
			}
		}
		
		if(classement == 3) {
			piece_next3 = piece;
			
			for(int i=1;i<5;i++) { 	
				for(int j=1;j<4;j++) {
					tableau_bouton_next3[i][j].setBackground(fond);
				}
			}
			
			if(piece == TETRIS) {
				tableau_bouton_next3[2][1].setBackground(mauve);
				tableau_bouton_next3[2][2].setBackground(mauve);
				tableau_bouton_next3[2][3].setBackground(mauve);
				tableau_bouton_next3[3][2].setBackground(mauve);
			}
			if(piece == LIGNE) {
				tableau_bouton_next3[1][2].setBackground(bleu_ciel);
				tableau_bouton_next3[2][2].setBackground(bleu_ciel);
				tableau_bouton_next3[3][2].setBackground(bleu_ciel);
				tableau_bouton_next3[4][2].setBackground(bleu_ciel);
			}
			if(piece == LDROITE) {
				tableau_bouton_next3[1][2].setBackground(orange);
				tableau_bouton_next3[2][2].setBackground(orange);
				tableau_bouton_next3[3][2].setBackground(orange);
				tableau_bouton_next3[3][3].setBackground(orange);
			}
			if(piece == JGAUCHE) {
				tableau_bouton_next3[1][2].setBackground(bleu_fonce);
				tableau_bouton_next3[2][2].setBackground(bleu_fonce);
				tableau_bouton_next3[3][2].setBackground(bleu_fonce);
				tableau_bouton_next3[3][1].setBackground(bleu_fonce);
			}
			if(piece == CARRE) {
				tableau_bouton_next3[2][1].setBackground(jaune);
				tableau_bouton_next3[2][2].setBackground(jaune);
				tableau_bouton_next3[3][1].setBackground(jaune);
				tableau_bouton_next3[3][2].setBackground(jaune);
			}
			if(piece == SDROITE) {
				tableau_bouton_next3[2][3].setBackground(rouge);
				tableau_bouton_next3[2][2].setBackground(rouge);
				tableau_bouton_next3[3][2].setBackground(rouge);
				tableau_bouton_next3[3][1].setBackground(rouge);
			}
			if(piece == ZGAUCHE) {
				tableau_bouton_next3[2][1].setBackground(vert);
				tableau_bouton_next3[2][2].setBackground(vert);
				tableau_bouton_next3[3][2].setBackground(vert);
				tableau_bouton_next3[3][3].setBackground(vert);
			}
		}
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	public void placerPieceMemoire(int piece) {
		
		if(!memoire_utilisee) {
			for(int i=0;i<22;i++) {
				for(int j=0;j<12;j++) {
					if(tab[i][j] == 1 || tab[i][j] == 2) {
						tab[i][j] = 0;
						tableau_bouton[i][j].setBackground(fond);
						tableau_bouton[i][j].setIcon(null);
					}
				}
			}
			
			if(piece_memory == 0) {
				placerPiece(piece_next1);
				placerNextPiece(piece_next2, 1);
				placerNextPiece(piece_next3, 2);
				placerNextPiece(randomPiece(3), 3);
				ok_deplacement = true;
			}else
				placerPiece(piece_memory);
			
			piece_memory = piece;
			memoire_utilisee = true;
			
			for(int i=1;i<5;i++) { 	
				for(int j=1;j<4;j++) {
					tableau_bouton_memoire[i][j].setBackground(fond);
					tableau_bouton_memoire[i][j].setIcon(null);
				}
			}
			
			if(piece == TETRIS) {
				tableau_bouton_memoire[2][1].setBackground(mauve);
				tableau_bouton_memoire[2][2].setBackground(mauve);
				tableau_bouton_memoire[2][3].setBackground(mauve);
				tableau_bouton_memoire[3][2].setBackground(mauve);
			}
			if(piece == LIGNE) {
				tableau_bouton_memoire[1][2].setBackground(bleu_ciel);
				tableau_bouton_memoire[2][2].setBackground(bleu_ciel);
				tableau_bouton_memoire[3][2].setBackground(bleu_ciel);
				tableau_bouton_memoire[4][2].setBackground(bleu_ciel);
			}
			if(piece == LDROITE) {
				tableau_bouton_memoire[1][2].setBackground(orange);
				tableau_bouton_memoire[2][2].setBackground(orange);
				tableau_bouton_memoire[3][2].setBackground(orange);
				tableau_bouton_memoire[3][3].setBackground(orange);
			}
			if(piece == JGAUCHE) {
				tableau_bouton_memoire[1][2].setBackground(bleu_fonce);
				tableau_bouton_memoire[2][2].setBackground(bleu_fonce);
				tableau_bouton_memoire[3][2].setBackground(bleu_fonce);
				tableau_bouton_memoire[3][1].setBackground(bleu_fonce);
			}
			if(piece == CARRE) {
				tableau_bouton_memoire[2][1].setBackground(jaune);
				tableau_bouton_memoire[2][2].setBackground(jaune);
				tableau_bouton_memoire[3][1].setBackground(jaune);
				tableau_bouton_memoire[3][2].setBackground(jaune);
			}
			if(piece == SDROITE) {
				tableau_bouton_memoire[2][3].setBackground(rouge);
				tableau_bouton_memoire[2][2].setBackground(rouge);
				tableau_bouton_memoire[3][2].setBackground(rouge);
				tableau_bouton_memoire[3][1].setBackground(rouge);
			}
			if(piece == ZGAUCHE) {
				tableau_bouton_memoire[2][1].setBackground(vert);
				tableau_bouton_memoire[2][2].setBackground(vert);
				tableau_bouton_memoire[3][2].setBackground(vert);
				tableau_bouton_memoire[3][3].setBackground(vert);
			}
		}
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************

	public void retirerPieceTab(int piece_memoire, int position_memoire) {
		
		int i_memoire = 0;
		int j_memoire = 0;
		
		for(int i=0;i<22;i++) {
			for(int j=0;j<12;j++) {
				if(tab[i][j] == 1) {
					i_memoire = i;
					j_memoire = j;
				}
			}
		}
		
		if(piece_memoire == TETRIS) {
			
			if(position_memoire == BASIQUE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire+1] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire][j_memoire-1] = 10;
			}
			if(position_memoire == DROITE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+1][j_memoire+1] = 10;
				tab[i_memoire+2][j_memoire] = 10;
			}
			if(position_memoire == BASIQUE_INVERSEE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire+1][j_memoire-1] = 10;
				tab[i_memoire+1][j_memoire+1] = 10;
				tab[i_memoire+1][j_memoire] = 10;
			}
			if(position_memoire == GAUCHE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+1][j_memoire-1] = 10;
				tab[i_memoire+2][j_memoire] = 10;
			}
		}
		
		if(piece_memoire == LIGNE) {
			
			if(position_memoire == BASIQUE) {
				tab[i_memoire][j_memoire-1] = 10;
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire+1] = 10;
				tab[i_memoire][j_memoire+2] = 10;
			}
			if(position_memoire == BASIQUE_INVERSEE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+2][j_memoire] = 10;
				tab[i_memoire+3][j_memoire] = 10;
			}
			
		}
		
		if(piece_memoire == LDROITE) {
			
			if(position_memoire == BASIQUE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire-1] = 10;
				tab[i_memoire][j_memoire+1] = 10;
				tab[i_memoire+1][j_memoire-1] = 10;
			}
			if(position_memoire == DROITE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+2][j_memoire] = 10;
				tab[i_memoire+2][j_memoire+1] = 10;
			}
			if(position_memoire == BASIQUE_INVERSEE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire-1] = 10;
				tab[i_memoire][j_memoire+1] = 10;
				tab[i_memoire-1][j_memoire+1] = 10;
			}
			if(position_memoire == GAUCHE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire+1] = 10;
				tab[i_memoire+1][j_memoire+1] = 10;
				tab[i_memoire+2][j_memoire+1] = 10;
			}
		}
		
		if(piece_memoire == JGAUCHE) {
			
			if(position_memoire == BASIQUE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire-1] = 10;
				tab[i_memoire][j_memoire+1] = 10;
				tab[i_memoire+1][j_memoire+1] = 10;
			}
			if(position_memoire == DROITE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+2][j_memoire] = 10;
				tab[i_memoire+2][j_memoire-1] = 10;
			}
			if(position_memoire == BASIQUE_INVERSEE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire-1] = 10;
				tab[i_memoire][j_memoire+1] = 10;
				tab[i_memoire-1][j_memoire-1] = 10;
			}
			if(position_memoire == GAUCHE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire-1] = 10;
				tab[i_memoire+1][j_memoire-1] = 10;
				tab[i_memoire+2][j_memoire-1] = 10;
			}
		}
		
		if(piece_memoire == CARRE) {
			tab[i_memoire][j_memoire] = 10;
			tab[i_memoire][j_memoire+1] = 10;
			tab[i_memoire+1][j_memoire] = 10;
			tab[i_memoire+1][j_memoire+1] = 10;
		}
		
		if(piece_memoire == SDROITE) {
			
			if(position_memoire == BASIQUE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire+1] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+1][j_memoire-1] = 10;
			}
			if(position_memoire == BASIQUE_INVERSEE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+1][j_memoire+1] = 10;
				tab[i_memoire+2][j_memoire+1] = 10;
			}
		}
		
		if(piece_memoire == ZGAUCHE) {
			
			if(position_memoire == BASIQUE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire][j_memoire-1] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+1][j_memoire+1] = 10;
			}
			if(position_memoire == BASIQUE_INVERSEE) {
				tab[i_memoire][j_memoire] = 10;
				tab[i_memoire+1][j_memoire] = 10;
				tab[i_memoire+1][j_memoire-1] = 10;
				tab[i_memoire+2][j_memoire-1] = 10;
			}
		}
	}
	
	//****************************************************************
	//****************************************************************
	//****************************************************************
	
	public void VerifLigne() {
		int compteur = 0;
		int i_memoire = 0;
		for(int i=1;i<21;i++) {
			for(int j=1;j<11;j++) {
				if(i_memoire!=i) {
					i_memoire = i;
					compteur = 0;
				}
				if(tab[i][j] == 10) {
					compteur++;
				}
			}
			if(compteur == 10) {
				score = score + 10;
				for(int j_=1;j_<11;j_++) {
					tab[i][j_]=0;
					tableau_bouton[i][j_].setBackground(fond);
					tableau_bouton[i][j_].setIcon(null);
				}
				for(int i_= i_memoire;i_>0;i_--) {
					for(int j=1;j<11;j++) {
						tab[i_][j]=tab[i_-1][j];
						tableau_bouton[i_][j].setBackground(tableau_bouton[i_-1][j].getBackground());
						tableau_bouton[i_][j].setIcon(tableau_bouton[i_-1][j].getIcon());
						if(i_==1) {
							tab[i_][j]=0;
							tableau_bouton[i_][j].setBackground(fond);
							tableau_bouton[i_][j].setIcon(null);
						}
					}
				}
			}
		}
	}
}
