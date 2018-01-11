package com.proyecto.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.game.Screens.MainGame;

/**
 * Created by frederick on 08/06/16.
 */
public class Board {
    public Box matrix[][];

    public Board (int dimention){
        matrix = new Box[dimention] [dimention];
        for (int i=0; i<dimention; i++) {
            for (int j=0; j<dimention; j++) {
                matrix[i][j]= new Box (com.proyecto.game.Screens.GameScreen.assets + "gray.png");
            }
        }
    }

    public void dispose(){
        for(int i=0; i < matrix.length; i++){
            for(int j=0; j < matrix[0].length; j++){
                matrix[i][j].dispose();
            }
        }
    }

    public void draw(SpriteBatch batch){
        float x= MainGame.camWidth/2 - (5*(Piece.compensacion + Piece.compensacion/4 + 5));
        float y= MainGame.camHeigth/2 - (5*(Piece.compensacion + Piece.compensacion/4 + 5));
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                switch (MainGame.theme) {
                    case SQUARES:
                        batch.draw(matrix[i][j].imagen, x, y, Piece.compensacion + Piece.compensacion/4, Piece.compensacion + Piece.compensacion/4);
                        break;
                    case CIRCLES:
                        batch.draw(matrix[i][j].imagen, x, y, Piece.compensacion + Piece.compensacion/4, Piece.compensacion + Piece.compensacion/4);
                        break;
                    case RHOMBUS:
                        batch.draw(matrix[i][j].imagen, x, y, Piece.compensacion + Piece.compensacion/4, Piece.compensacion + Piece.compensacion/4);
                        break;
                }
                x = x + Piece.compensacion + Piece.compensacion/4 + 5;
            }
            y = y + Piece.compensacion + Piece.compensacion/4 + 5;
            x = Gdx.graphics.getWidth()/2 - (5*(Piece.compensacion + Piece.compensacion/4 + 5));
        }
    }

    // Obtiene el tamano de las filas
    public int getRowLength(){
        return(matrix.length);
    }
    //Obtiene el tamno de las filas
    public int getColumnLength(){
        return(matrix[0].length);
    }
    // Obtiene el valor de una casilla de la matriz del tablero
    public boolean getMatrixValue(int i, int j){
        return(matrix[i][j].busy);
    }

    //Metodos que verfican las filas y columnas dados el numero de la fila y la columna, para trabajarlo como capas
    public boolean verifyColumn(int col){
        boolean columnFull = true;

        for(int j = 0; j<matrix.length; j++){
            if(!matrix[j][col].busy)
                columnFull = false;
        }
        return(columnFull);
    }

    public boolean verifyRow(int row){
        boolean rowFull = true;

        for (int i = 0; i < matrix[0].length; i++){
            if(!matrix[row][i].busy)
                rowFull = false;
        }

        return(rowFull);
    }

    //métodos que vacían la columna y fila si estas están llenas
    public void emptyColumn(int col){
        for(int j = 0; j < matrix.length; j++){
            matrix[j][col].busy = false;
            matrix[j][col].setTexture(com.proyecto.game.Screens.GameScreen.assets + "gray.png");
        }
    }

    public void emptyRow(int row){
        for(int i = 0; i < matrix[0].length; i++){
            matrix[row][i].busy = false;
            matrix[row][i].setTexture(com.proyecto.game.Screens.GameScreen.assets + "gray.png");
        }
    }
    //Metodo que vacia las filas y las columnas y devuelve el score obtenido
    public int cleanRowsAndColumns(){
        Boolean [] rowToEliminate = new Boolean [10];
        Boolean [] columnToEliminate = new Boolean [10];
        boolean empty;
        empty = false;
        int score = 0, i;

        for (i = 0; i < columnToEliminate.length; i++){
            columnToEliminate[i] = verifyColumn(i);
        }
        for (i = 0; i < rowToEliminate.length; i++){
            rowToEliminate[i] = verifyRow(i);
        }
        for (i = 0; i < columnToEliminate.length && i < rowToEliminate.length; i++){
            if (columnToEliminate[i]){
                emptyColumn(i);
                score += 20;
                empty = true;
            }
            if (rowToEliminate[i]){
                emptyRow(i);
                score += 20;
                empty = true;
            }
        }
        if (empty){
            com.proyecto.game.Screens.MainGame.emptySound.play();
        }
        return score;
    }

    /*Metodo que verifica el espacio donde la pieza será puesta, usando una posY y una posX como pivote y desde allí se compara*/
    public boolean verifySpace(Piece p, int posY, int posX){
        try {
            switch(p.getShape()){
                case box: //Bien
                    if(!this.matrix[posY][posX].busy)
                        return true;
                    else
                        return false;

                case little_L: //Bien
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy && !this.matrix[posY][posX+1].busy)
                        return true;
                    else
                        return false;

                case inverted_Little_L://Corregida
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy && !this.matrix[posY+1][posX+1].busy)
                        return true;
                    else
                        return false;

                case flipped_Little_L: //Corregida
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX+1].busy && !this.matrix[posY][posX+1].busy)
                        return true;
                    else
                        return false;

                case inverted_Flipped_Little_L: //Corregida
                    if(!this.matrix[posY][posX+1].busy && !this.matrix[posY+1][posX].busy && !this.matrix[posY+1][posX+1].busy)
                        return true;
                    else
                        return false;

                case square_2x2:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy && !this.matrix[posY][posX+1].busy && !this.matrix[posY+1][posX+1].busy)
                        return true;
                    else
                        return false;

                case square_3x3:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy && !this.matrix[posY][posX+2].busy &&
                            !this.matrix[posY+1][posX].busy && !this.matrix[posY+1][posX+1].busy && !this.matrix[posY+1][posX+2].busy &&
                            !this.matrix[posY+2][posX].busy && !this.matrix[posY+2][posX+1].busy && !this.matrix[posY+2][posX+2].busy)
                        return true;
                    else
                        return false;

                case line_2x1:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy)
                        return true;
                    else
                        return false;

                case line_1x2:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy)
                        return true;
                    else
                        return false;

                case line_3x1:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy && !this.matrix[posY+2][posX].busy)
                        return true;
                    else
                        return false;

                case line_1x3:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy && !this.matrix[posY][posX+2].busy)
                        return true;
                    else
                        return false;

                case line_4x1:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy && !this.matrix[posY+2][posX].busy && !this.matrix[posY+3][posX].busy)
                        return true;
                    else
                        return false;

                case line_1x4:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy && !this.matrix[posY][posX+2].busy && !this.matrix[posY][posX+3].busy)
                        return true;
                    else
                        return false;

                case line_5x1:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy && !this.matrix[posY+2][posX].busy &&
                            !this.matrix[posY+3][posX].busy && !this.matrix[posY+4][posX].busy)
                        return true;
                    else
                        return false;

                case line_1x5:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy && !this.matrix[posY][posX+2].busy &&
                            !this.matrix[posY][posX+3].busy && !this.matrix[posY][posX+4].busy)
                        return true;
                    else
                        return false;

                case big_L: //Corregido
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy && !this.matrix[posY][posX+2].busy && !this.matrix[posY+1][posX].busy &&
                            !this.matrix[posY+2][posX].busy)
                        return true;
                    else
                        return false;

                case inverted_Big_L://Corregido
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy && !this.matrix[posY+2][posX].busy && !this.matrix[posY+2][posX+1].busy &&
                            !this.matrix[posY+2][posX+2].busy)
                        return true;
                    else
                        return false;

                case flipped_Big_L://Corregido
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy && !this.matrix[posY][posX+2].busy && !this.matrix[posY+1][posX+2].busy &&
                            !this.matrix[posY+2][posX+2].busy)
                        return true;
                    else
                        return false;

                case inverted_Flipped_Big_L:  //Corregido
                    if(!this.matrix[posY][posX+2].busy && !this.matrix[posY+1][posX+2].busy && !this.matrix[posY+2][posX+2].busy && !this.matrix[posY+2][posX+1].busy &&
                            !this.matrix[posY+2][posX].busy)
                        return true;
                    else
                        return false;
                case normal_Z:
                    if(!this.matrix[posY][posX+1].busy && !this.matrix[posY+1][posX+1].busy &&
                            !this.matrix[posY+1][posX].busy && !this.matrix[posY+2][posX].busy)
                        return true;
                    else
                        return false;
                case inverted_Z:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy &&
                            !this.matrix[posY+1][posX+1].busy && !this.matrix[posY+2][posX+1].busy)
                        return true;
                    else
                        return false;
                case up_Z:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy &&
                            !this.matrix[posY+1][posX+1].busy && !this.matrix[posY+1][posX+2].busy)
                        return true;
                    else
                        return false;
                case inverted_up_Z:
                    if(!this.matrix[posY+1][posX].busy && !this.matrix[posY+1][posX+1].busy &&
                            !this.matrix[posY][posX+1].busy && !this.matrix[posY][posX+2].busy)
                        return true;
                    else
                        return false;
                case t_normal:
                    if(!this.matrix[posY+1][posX].busy && !this.matrix[posY][posX+1].busy &&
                            !this.matrix[posY+1][posX+1].busy && !this.matrix[posY+2][posX+1].busy)
                        return true;
                    else
                        return false;
                case t_left:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+1].busy &&
                            !this.matrix[posY][posX+2].busy && !this.matrix[posY+1][posX+1].busy)
                        return true;
                    else
                        return false;
                case t_right:
                    if(!this.matrix[posY+1][posX].busy && !this.matrix[posY+1][posX+1].busy &&
                            !this.matrix[posY+1][posX+2].busy && !this.matrix[posY][posX+1].busy)
                        return true;
                    else
                        return false;
                case t_UpsideDown:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY+1][posX].busy &&
                            !this.matrix[posY+2][posX].busy && !this.matrix[posY+1][posX+1].busy)
                        return true;
                    else
                        return false;
                case cross:
                    if(!this.matrix[posY][posX+1].busy && !this.matrix[posY+1][posX].busy &&
                            !this.matrix[posY+1][posX+2].busy && !this.matrix[posY+2][posX+1].busy)
                        return true;
                    else
                        return false;
                case hashtag:
                    if(!this.matrix[posY][posX].busy && !this.matrix[posY][posX+2].busy && !this.matrix[posY+1][posX+1].busy
                            && !this.matrix[posY+2][posX].busy && !this.matrix[posY+2][posX+2].busy)
                        return true;
                    else
                        return false;

                default: return false;
            }
        }catch(Exception e) {
            //En caso de que la pieza quede afuera del tablero
            return false;
        }
    }

    public void putPiece(Piece p, int posY, int posX){
		/*Metodo que se encarga de poner la pieza, una vez es verificada*/
        switch(p.getShape()){
            case box:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                break;
            case little_L:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                break;
            case inverted_Little_L:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                break;
            case flipped_Little_L:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                break;
            case inverted_Flipped_Little_L:
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                break;
            case square_2x2:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                break;
            case square_3x3:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY+1][posX+2].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY+2][posX+1].busy = true;
                this.matrix[posY+2][posX+2].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+2].setTexture(p.getPieceTexture());
                break;
            case line_2x1:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                break;
            case line_1x2:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;

                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                break;
            case line_3x1:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+2][posX].busy = true;

                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                break;
            case line_1x3:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                break;
            case line_4x1:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY+3][posX].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+3][posX].setTexture(p.getPieceTexture());
                break;
            case line_1x4:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY][posX+3].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+3].setTexture(p.getPieceTexture());
                break;
            case line_5x1:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY+3][posX].busy = true;
                this.matrix[posY+4][posX].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+3][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+4][posX].setTexture(p.getPieceTexture());
                break;
            case line_1x5:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY][posX+3].busy = true;
                this.matrix[posY][posX+4].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+3].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+4].setTexture(p.getPieceTexture());
                break;
            case big_L:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                break;
            case inverted_Big_L:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY+2][posX+1].busy = true;
                this.matrix[posY+2][posX+2].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                break;
            case flipped_Big_L: //
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY+1][posX+2].busy = true;
                this.matrix[posY+2][posX+2].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+2].setTexture(p.getPieceTexture());
                break;
            case inverted_Flipped_Big_L: //
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY+1][posX+2].busy = true;
                this.matrix[posY+2][posX+2].busy = true;
                this.matrix[posY+2][posX+1].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                break;
            case normal_Z:
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                break;
            case inverted_Z:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY+2][posX+1].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+1].setTexture(p.getPieceTexture());
                break;
            case up_Z:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY+1][posX+2].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+2].setTexture(p.getPieceTexture());
                break;
            case inverted_up_Z:
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                break;
            case t_normal:
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY+2][posX+1].busy = true;
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+1].setTexture(p.getPieceTexture());
                break;
            case t_UpsideDown:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                break;
            case t_left:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                break;
            case t_right:
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY+1][posX+2].busy = true;
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                break;
            case hashtag:
                this.matrix[posY][posX].busy = true;
                this.matrix[posY][posX+2].busy = true;
                this.matrix[posY+1][posX+1].busy = true;
                this.matrix[posY+2][posX].busy = true;
                this.matrix[posY+2][posX+2].busy = true;
                this.matrix[posY][posX].setTexture(p.getPieceTexture());
                this.matrix[posY][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+2].setTexture(p.getPieceTexture());
                break;
            case cross:
                this.matrix[posY][posX+1].busy = true;
                this.matrix[posY+1][posX].busy = true;
                this.matrix[posY+1][posX+2].busy = true;
                this.matrix[posY+2][posX+1].busy = true;
                this.matrix[posY][posX+1].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX].setTexture(p.getPieceTexture());
                this.matrix[posY+1][posX+2].setTexture(p.getPieceTexture());
                this.matrix[posY+2][posX+1].setTexture(p.getPieceTexture());
                break;
        }
    }

    public boolean confirmMapping(float x, float y){
        if ((x >= (Gdx.graphics.getWidth()/2 - (5*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 + (5*(Piece.compensacion + Piece.compensacion/4 + 5))))){
            if ((y >= (Gdx.graphics.getHeight()/2 - (5*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 + (5*(Piece.compensacion + Piece.compensacion/4 + 5))))){
                return true;
            }
        }
        return false;
    }

    private int xMapping( float x ){
        if ((x >= (Gdx.graphics.getWidth()/2 - (5*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 - (4 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 0;
        }else if ((x >= (Gdx.graphics.getWidth()/2 - (4*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 - (3 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 1;
        }else if ((x >= (Gdx.graphics.getWidth()/2 - (3*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 - (2 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 2;
        }else if ((x >= (Gdx.graphics.getWidth()/2 - (2*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 - ((Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 3;
        }else if ((x >= (Gdx.graphics.getWidth()/2 - ((Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 ))){
            return 4;
        }else if ((x >= (Gdx.graphics.getWidth()/2 )) && (x <= (Gdx.graphics.getWidth()/2 + ((Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 5;
        }else if ((x >= (Gdx.graphics.getWidth()/2 + ((Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 + (2 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 6;
        }else if ((x >= (Gdx.graphics.getWidth()/2 + (2 *(Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 + (3 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 7;
        }else if ((x >= (Gdx.graphics.getWidth()/2 + (3 *(Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 + (4 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 8;
        }else if ((x >= (Gdx.graphics.getWidth()/2 + (4 *(Piece.compensacion + Piece.compensacion/4 + 5)))) && (x <= (Gdx.graphics.getWidth()/2 + (5 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 9;
        }
        return 0;
    }

    private int yMapping(float y){
        if ((y >= (Gdx.graphics.getHeight()/2 - (5*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 - (4 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 0;
        }else if ((y >= (Gdx.graphics.getHeight()/2 - (4*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 - (3 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 1;
        }else if ((y >= (Gdx.graphics.getHeight()/2 - (3*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 - (2 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 2;
        }else if ((y >= (Gdx.graphics.getHeight()/2 - (2*(Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 - ((Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 3;
        }else if ((y >= (Gdx.graphics.getHeight()/2 - ((Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 ))){
            return 4;
        }else if ((y >= (Gdx.graphics.getHeight()/2 )) && (y <= (Gdx.graphics.getHeight()/2 + ((Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 5;
        }else if ((y >= (Gdx.graphics.getHeight()/2 + ((Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 + (2 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 6;
        }else if ((y >= (Gdx.graphics.getHeight()/2 + (2 *(Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 + (3 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 7;
        }else if ((y >= (Gdx.graphics.getHeight()/2 + (3 *(Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 + (4 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 8;
        }else if ((y >= (Gdx.graphics.getHeight()/2 + (4 *(Piece.compensacion + Piece.compensacion/4 + 5)))) && (y <= (Gdx.graphics.getHeight()/2 + (5 *(Piece.compensacion + Piece.compensacion/4 + 5))))){
            return 9;
        }
        return 0;
    }

    public boolean mapping( float x, float y, Piece piece, Score score){
        int xPos;
        int yPos;
        xPos = xMapping(x);
        yPos = yMapping(y);
        System.out.println(xPos + " " +yPos);
        if (verifySpace(piece,yPos,xPos)) {
            putPiece(piece,yPos,xPos);
            score.setScore(piece.getScore());
            return true;
        }
        else {return false;}
    }

}
