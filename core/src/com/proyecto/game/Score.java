package com.proyecto.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.proyecto.game.Screens.MainGame;


public class Score{
	public static int score;
	private BitmapFont font, bestscore;
	private String mytext;

	public Score(){
		score=0;;
		font = new BitmapFont(Gdx.files.internal("verdana39.fnt"),Gdx.files.internal("verdana39.png"),false);
		bestscore = new BitmapFont(Gdx.files.internal("Jokerman.fnt"),Gdx.files.internal("Jokerman.png"),false);
	}


	public void setScore(int adition){
		this.score += adition;
	}

	public void draw(Batch batch, int highScore){
		mytext = "SCORE  ";
		mytext = mytext + score;
		String text = "BEST:  " + highScore;
		font.setColor(1,.5f,0,1);
		bestscore.setColor(0,0,1,1);
		font.draw(batch,mytext, 10 + MainGame.camWidth/2 - (mytext.length() * font.getSpaceWidth()),MainGame.camHeigth - font.getXHeight());
		bestscore.draw(batch, text ,10 + MainGame.camWidth/2 - (text.length() * bestscore.getSpaceWidth()),MainGame.camHeigth - font.getXHeight() - bestscore.getXHeight() - 20);
	}

	public static void act(MainGame game){
		int finalScore = score;
		int lowestScore=game.saveManager.loadDataValue("Score10", int.
				class);
		if(finalScore>lowestScore){
			int[] scores = new int[10];
			for(int i=1;i<=10;i++){
				scores[i-1]=game.saveManager.loadDataValue("Score"+i, int.class);
			}
			scores[9]=finalScore;
			for(int i=9;i>0;i--){
				if(scores[i]>scores[i-1]){
					finalScore=scores[i-1];
					scores[i-1]=scores[i];
					scores[i]=finalScore;
				}else{
					break;
				}
			}

			for(int i=1;i<=10;i++){
				game.saveManager.saveDataValue("Score" + i, scores[i-1]); //Salvando el JSON
				MainGame.getPrefs().putInteger("Score" + i, scores[i-1]); //Salvando las preferencias para poder tener local Leaderboard
			}
		}
		MainGame.getPrefs().flush();
	}

	public void dispose(){
		font.dispose();
		bestscore.dispose();
	}
}
