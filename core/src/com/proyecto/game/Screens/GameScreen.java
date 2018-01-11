package com.proyecto.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.proyecto.game.Board;
import com.proyecto.game.Piece;
import com.proyecto.game.PieceBar;
import com.proyecto.game.Score;
import com.proyecto.game.Theme;


/**
 * Created by frederick on 18/06/16.
 */
public class GameScreen extends BaseScreen {

    public static Score score;
    public Board board;
    public PieceBar pieceBar;
    public static String assets;
    private SpriteBatch batch;
    public static Camera camera;
    private Texture background;
    private Stage stage;
    public static boolean active;

    public GameScreen(final MainGame game) {
        super(game);
        active = true;
        background = new Texture("gamebg.png");
        batch = game.batch;
        camera = game.camera;

       //Objetos
        stage = new Stage(new FitViewport(MainGame.camWidth,MainGame.camHeigth));
        pieceBar = new PieceBar();
        board = new Board(10);
        score = new Score();
        Skin skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));

        final TextButton exit = new TextButton("Exit",skin);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                active = false;
                game.setScreen(new MenuScreen(game));

            }
        });

        Table table = new Table();
        table.setPosition(MainGame.camWidth - 40,MainGame.camHeigth - 20);
        table.add(exit);

        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

        MainGame.beginSound.play();
    }

    public boolean gameOver(PieceBar piecebar, Board board){
        boolean end = true;
        for(int i = 0; i < board.getRowLength();i++){
            for(int j = 0; j < board.getColumnLength();j++){
                if ((board.verifySpace(piecebar.getSpace1(),i,j)) ||(board.verifySpace(piecebar.getSpace2(),i,j))||(board.verifySpace(piecebar.getSpace3(),i,j))){
                    end = false;
                }
            }
        }
        return end;
    }

    @Override
    public void render(float delta) {
        camera.project(new Vector3());
        camera.update();

        score.setScore(board.cleanRowsAndColumns());

        if (pieceBar.emptyPieceBar()){
            pieceBar.fillPieceBar();
        }

        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        batch.draw(background,0,0,MainGame.camWidth,MainGame.camHeigth);

        if (!gameOver(pieceBar,board)) {
            board.draw(batch);

            pieceBar.draw(batch, board, score);

            score.draw(batch, (Integer) game.saveManager.loadDataValue("Score1",int.class));
        }
        else{
            Score.act(game);
            active = false;
            game.setScreen(new GameOverScreen(game));
        }

        batch.end();

        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        super.dispose();
        score.dispose();
        pieceBar.dispose();
        board.dispose();
        stage.dispose();
        background.dispose();
    }
}
