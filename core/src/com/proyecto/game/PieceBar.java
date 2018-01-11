package com.proyecto.game;

import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.game.Screens.MainGame;

public class PieceBar{
	private Piece space1;
	private Piece space2;
	private Piece space3;
	private Boolean space1Touch, space2Touch,space3Touch;
	//Constructor de PieceBar
	public PieceBar(){
		space1Touch = false;
		space2Touch = false;
		space3Touch = false;
		fillPieceBar();
	}
	//Metodos que colocan los valores de los espacios en NULL una vez ue la pieza esta colocada
	public void setSpace1Null(){
		space1 = null;
	}
	public void setSpace2Null(){
		space2 = null;
	}
	public void setSpace3Null(){
		space3 = null;
	}
	//Getters
	public Piece getSpace1(){
		return space1;
	}
	public Piece getSpace2(){
		return space2;
	}
	public Piece getSpace3(){
		return space3;
	}

	public Boolean emptyPieceBar(){
		return ((space1 == null) && (space2 == null) && (space3 == null));
	}
	//Llena el PieceBar con nuevas piezas cuando este vacio
	public void fillPieceBar(){
		space1 = new Piece((com.proyecto.game.Screens.MainGame.camWidth/ 6), com.proyecto.game.Screens.MainGame.camHeigth/20);
		space2 = new Piece((com.proyecto.game.Screens.MainGame.camWidth/ 2), com.proyecto.game.Screens.MainGame.camHeigth/20);
		space3 = new Piece((com.proyecto.game.Screens.MainGame.camWidth * 5/6), com.proyecto.game.Screens.MainGame.camHeigth/20);
		com.proyecto.game.Screens.MainGame.fillPieceBarSound.play();
	}



	//Muestra las piezas que quedan en el PieceBar
	public void draw( SpriteBatch batch, Board board, Score score){
		if (space1 !=null) {
			if (space1.justPressed() && (!space2Touch) && (!space3Touch)) {
				space1.draw(batch);
				space1Touch = true;
			} else if (space1.pressed() && (!space2Touch) && (!space3Touch)) {
				space1.move();
				space1.growth(batch);
			} else{
				if (board.confirmMapping(space1.getX(),space1.getY()) && (board.mapping(space1.getX(), space1.getY(), space1, score))) {
					setSpace1Null();
					space1Touch = false;
				}else {
					space1.setX(space1.getInitialX());
					space1.setY(space1.getInitialY());
					space1.draw(batch);
					space1Touch = false;
				}
			}
		}


		if (space2 !=null) {
			if (space2.justPressed() && (!space1Touch) && (!space3Touch)) {
				space2.draw(batch);
				space2Touch = true;
			} else if (space2.pressed() && (!space1Touch) && (!space3Touch)) {
				space2.move();
				space2.growth(batch);
			} else {
				if (board.confirmMapping(space2.getX(),space2.getY()) && (board.mapping(space2.getX(), space2.getY(), space2, score))) {
					setSpace2Null();
					space2Touch = false;
				}else{
					space2.setX(space2.getInitialX());
					space2.setY(space2.getInitialY());
					space2.draw(batch);
					space2Touch = false;
				}
			}
		}

		if (space3 !=null) {
			if (space3.justPressed() && (!space2Touch) && (!space1Touch)) {
				space3.draw(batch);
				space3Touch = true;
			} else if (space3.pressed() && (!space2Touch) && (!space1Touch)) {
				space3.move();
				space3.growth(batch);
			} else{
				if (board.confirmMapping(space3.getX(),space3.getY()) && (board.mapping(space3.getX(), space3.getY(), space3, score))) {
					setSpace3Null();
					space3Touch = false;
				}else {
					space3.setX(space3.getInitialX());
					space3.setY(space3.getInitialY());
					space3.draw(batch);
					space3Touch = false;
				}
			}
		}

	}

	public void dispose(){
		space1.dispose();
		space2.dispose();
		space3.dispose();
	}
}