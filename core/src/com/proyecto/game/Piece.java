package com.proyecto.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.game.Screens.MainGame;

import java.util.Random;

/**
 * Created by frederick on 31/05/16.
 */

public class Piece{
    private final int IMAGE_HEIGHT = 50;
    private float initialx, initialy, x, y;
    private Box [][] box;
    public boolean touch;
    public static float compensacion;
    private Shape shape;
    private String name;
    private int score;

    public Texture getPieceTexture(){
        return box[0][0].imagen;
    }

    public Piece (float x, float y){
        compensacion = com.proyecto.game.Screens.MainGame.camWidth / 15;
        this.x = x  - compensacion;
        this.y = y;
        touch = false;

        Random rn = new Random();

        switch(rn.nextInt(MainGame.version)+1) {
            case 1:
                this.shape = shape.box;
                this.name = "Box";
                box = new Box[1][1];
                box[0][0]=new Box(com.proyecto.game.Screens.GameScreen.assets + "red.png");
                box[0][0].busy=true;
                score = 1;
                break;
            case 2:
                this.shape = shape.little_L;
                this.name = "Little L";
                box = new Box[2][2];
                for (int i=0; i < 2; i++){
                    for (int j=0; j<2; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "blue.png");
                        box[i][j].busy=true;
                    }
                }
                box[1][1].busy=false;
                score = 3;
                break;
            case 3:
                this.shape = shape.inverted_Little_L;
                this.name = "Inverted Little L";
                box = new Box[2][2];
                for (int i=0; i < 2; i++){
                    for (int j=0; j<2; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "blue.png");
                        box[i][j].busy=true;
                    }
                }
                box[1][0].busy=false;
                score = 3;
                break;
            case 4:
                this.shape = shape.flipped_Little_L;
                this.name = "Flipped Little L";
                box = new Box[2][2];
                for (int i=0; i < 2; i++){
                    for (int j=0; j<2; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "blue.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][1].busy=false;
                score = 3;
                break;
            case 5:
                this.shape = shape.inverted_Flipped_Little_L;
                this.name = "Inverted Flipped Little L";
                box = new Box[2][2];
                for (int i=0; i < 2; i++){
                    for (int j=0; j<2; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "blue.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][0].busy=false;
                score = 3;
                break;
            case 6:
                this.shape = shape.square_2x2;
                this.name = "Square 2x2";
                box = new Box[2][2];
                for (int i=0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        box[i][j] = new Box(com.proyecto.game.Screens.GameScreen.assets + "yellow.png");
                        box[i][j].busy = true;
                    }
                }
                score = 4;
                break;
            case 7:
                this.shape = shape.square_3x3;
                this.name = "Square 3x3";
                box = new Box[3][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "green.png");
                        box[i][j].busy=true;
                    }
                }
                score = 9;
                break;
            case 8:
                this.shape = shape.line_2x1;
                this.name = "Line 2x1";
                box = new Box[1][2];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "pink.png");
                        box[i][j].busy=true;
                    }
                }
                score = 2;
                break;
            case 9:
                this.shape = shape.line_1x2;
                this.name = "Line 1x2";
                box = new Box[2][1];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "pink.png");
                        box[i][j].busy=true;
                    }
                }
                score = 2;
                break;
            case 10:
                this.shape = shape.line_3x1;
                this.name = "Line 3x1";
                box = new Box[1][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "lgreen.png");
                        box[i][j].busy=true;
                    }
                }
                score = 3;
                break;
            case 11:
                this.shape = shape.line_1x3;
                this.name = "Line 1x3";
                box = new Box[3][1];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "lgreen.png");
                        box[i][j].busy=true;
                    }
                }
                score = 3;
                break;
            case 12:
                this.shape = shape.line_4x1;
                this.name = "Line 4x1";
                box = new Box[1][4];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "blue.png");
                        box[i][j].busy=true;
                    }
                }
                score = 4;
                break;
            case 13:
                this.shape = shape.line_1x4;
                this.name = "Line 1x4";
                box = new Box[4][1];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "blue.png");
                        box[i][j].busy=true;
                    }
                }
                score = 4;
                break;
            case 14:
                this.shape = shape.line_5x1;
                this.name = "Line 5x1";
                box = new Box[1][5];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "orange.png");
                        box[i][j].busy=true;
                    }
                }
                score = 5;
                break;
            case 15:
                this.shape = shape.line_1x5;
                this.name = "Line 1x5";
                box = new Box[5][1];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "orange.png");
                        box[i][j].busy=true;
                    }
                }
                score = 5;
                break;
            case 16:
                this.shape = shape.big_L;
                this.name = "Big L";
                box = new Box[3][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "purple.png");
                        box[i][j].busy=true;
                    }
                }
                box[1][1].busy = false;
                box[1][2].busy = false;
                box[2][1].busy = false;
                box[2][2].busy = false;
                score = 5;
                break;
            case 17:
                this.shape = shape.inverted_Big_L;
                this.name = "Inverted Big L";
                box = new Box[3][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "purple.png");
                        box[i][j].busy=true;
                    }
                }
                box[1][0].busy = false;
                box[1][1].busy = false;
                box[2][0].busy = false;
                box[2][1].busy = false;
                score = 5;
                break;
            case 18:
                this.shape = shape.flipped_Big_L;
                this.name = "Flipped Big L";
                box = new Box[3][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "purple.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][1].busy = false;
                box[0][2].busy = false;
                box[1][1].busy = false;
                box[1][2].busy = false;
                score = 5;
                break;
            case 19:
                this.shape = shape.inverted_Flipped_Big_L;
                this.name = "Inverted Flipped Big L";
                box = new Box[3][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "purple.png");
                        box[i][j].busy=true;
                    }
                }

                box[0][0].busy = false;
                box[0][1].busy = false;
                box[1][0].busy = false;
                box[1][1].busy = false;
                score = 5;
                break;
            case 20:
                this.shape = shape.normal_Z;
                this.name = "Normal Z";
                box = new Box[2][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "red.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][0].busy = false;
                box[1][2].busy = false;
                score = 4;
                break;
            case 21:
                this.shape = shape.inverted_Z;
                this.name = "Inverted Z";
                box = new Box[2][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "red.png");
                        box[i][j].busy=true;
                    }
                }
                box[1][0].busy = false;
                box[0][2].busy = false;
                score = 4;
                break;
            case 22:
                this.shape = shape.up_Z;
                this.name = "Up Z";
                box = new Box[3][2];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "red.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][1].busy = false;
                box[2][0].busy = false;
                score = 4;
                break;
            case 23:
                this.shape = shape.inverted_up_Z;
                this.name = "Inverted Up Z";
                box = new Box[3][2];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "red.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][0].busy = false;
                box[2][1].busy = false;
                score = 4;
                break;
            case 24:
                this.shape = shape.t_normal;
                this.name = "normal T";
                box = new Box [2][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "green.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][0].busy = false;
                box[0][2].busy = false;
                score = 4;
                break;
            case 25:
                this.shape = shape.t_left;
                this.name = "T to the left";
                box = new Box [3][2];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "green.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][1].busy = false;
                box[2][1].busy = false;
                score = 4;
                break;
            case 26:
                this.shape = shape.t_right;
                this.name = "T to the right";
                box = new Box [3][2];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "green.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][0].busy = false;
                box[2][0].busy = false;
                score = 4;
                break;
            case 27:
                this.shape = shape.t_UpsideDown;
                this.name = "T Upside-Down";
                box = new Box [2][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "green.png");
                        box[i][j].busy=true;
                    }
                }
                box[1][0].busy = false;
                box[1][2].busy = false;
                score = 4;
                break;
            case 28:
                this.shape = shape.cross;
                this.name = "Cross";
                box = new Box[3][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "yellow.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][0].busy = false;
                box[0][2].busy = false;
                box[1][1].busy = false;
                box[2][0].busy = false;
                box[2][2].busy = false;
                score = 4;
                break;
            case 29:
                this.shape = shape.hashtag;
                this.name = "Hashtag";
                box = new Box[3][3];
                for (int i=0; i < box.length; i++){
                    for (int j=0; j< box[0].length; j++){
                        box[i][j]=new Box(com.proyecto.game.Screens.GameScreen.assets + "yellow.png");
                        box[i][j].busy=true;
                    }
                }
                box[0][1].busy = false;
                box[1][0].busy = false;
                box[1][2].busy = false;
                box[2][1].busy = false;
                score = 5;
                break;
            default: {
                this.shape = shape.box;
                this.name = "Box";
                box = new Box[1][1];
                box[0][0]=new Box(com.proyecto.game.Screens.GameScreen.assets + "red.png");
                box[0][0].busy=true;
                score = 1;
            }
        }

        this.initialx = (x  - compensacion) - (box.length/(2*IMAGE_HEIGHT));
        this.initialy = (y - (box.length/(2*IMAGE_HEIGHT)));
    }



    public void draw(SpriteBatch batch) {
        for (int i=0;i < box.length;i++) {
            for (int j=0;j < box[0].length;j++) {
                if(box[i][j].busy) {
                    switch (MainGame.theme) {
                        case SQUARES:
                            batch.draw(box[i][j].imagen,x + (i * compensacion),y + (j * compensacion), compensacion - (compensacion/10),compensacion - (compensacion/10));
                            break;
                        case CIRCLES:
                            batch.draw(box[i][j].imagen,x + (i * compensacion),y + (j * compensacion), compensacion - (compensacion/10),compensacion - (compensacion/10));
                            break;
                        case RHOMBUS:

                            batch.draw(box[i][j].imagen,x + (i * compensacion),y + (j * compensacion), compensacion - (compensacion/10),compensacion - (compensacion/10));
                            break;
                    }
                }
            }
        }
    }

    public void growth(SpriteBatch batch){
        for(int i = 0; i < box.length; i++){
            for(int j = 0; j < box[0].length; j++) {
                if(box[i][j].busy) {
                    switch (MainGame.theme) {
                        case SQUARES:
                            batch.draw(box[i][j].imagen, x + (i * (compensacion + compensacion / 3)) - ((compensacion + compensacion / 3) / 2), y + (j * (compensacion + compensacion / 3)) + IMAGE_HEIGHT - ((compensacion + compensacion / 3) / 2), compensacion + compensacion / 4, compensacion + compensacion / 4);
                            break;
                        case CIRCLES:
                            batch.draw(box[i][j].imagen, x + (i * (compensacion + compensacion / 3)) - ((compensacion + compensacion / 3) / 2), y + (j * (compensacion + compensacion / 3)) + IMAGE_HEIGHT - ((compensacion + compensacion / 3) / 2), compensacion + compensacion / 4, compensacion + compensacion / 4);
                            break;
                        case RHOMBUS:
                            batch.draw(box[i][j].imagen, x + (i * (compensacion + compensacion/3)) - ((compensacion + compensacion/3)/2),y + (j * (compensacion + compensacion/3)) + IMAGE_HEIGHT - ((compensacion + compensacion/3)/2), compensacion + compensacion / 4, compensacion + compensacion / 4);
                            break;
                    }
                }
            }
        }

    }

    public boolean justPressed(){
        float dx,dy, imgx, imgy;
        dx = Gdx.input.getX();
        dy = (Gdx.graphics.getHeight())-(Gdx.input.getY());
        imgx = initialx + (compensacion * 3);
        imgy = initialy + (compensacion * 3);
        if((dx>= initialx && dx<= (imgx)) && (dy>=initialy && dy<= (imgy)) && (!touch)) {
            touch = true;
            return true;
        }
        else
            return false;
    }

    public boolean pressed(){
        if(Gdx.input.isTouched() && touch) {
            return true;
        }
        else {
            touch = false;
            return false;
        }
    }

    public float getInitialX(){
        return this.initialx;
    }

    public float getInitialY(){
        return this.initialy;
    }

    public void setX(float x){
        this.x = x;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y + IMAGE_HEIGHT;
    }

    public void move(){
        float dx, dy;
        dx = Gdx.input.getX();
        dy = (Gdx.graphics.getHeight())-(Gdx.input.getY()) + IMAGE_HEIGHT;
        this.x = dx;
        this.y = dy;
    }

    public int getScore(){
        return score;
    }

    public Shape getShape(){
        return(this.shape);
    }

    public String getName(){
        return(this.name);
    }

    public void dispose(){
        for (int i = 0; i< box.length; i++){
            for (int j = 0; j< box[0].length; j++){
                box[i][j].dispose();
            }
        }
    }
}
