package com.proyecto.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * Created by frederick on 18/06/16.
 */
public class GameOverScreen extends  BaseScreen{

    private boolean firstTime;
    private Texture gameOver;
    private SpriteBatch batch;
    private Stage stage;
    private Texture background;

    public GameOverScreen(final MainGame game) {
        super(game);
        background = new Texture("background.png");
        gameOver = new Texture("GameOver.png");
        firstTime = true;
        stage = new Stage(new FitViewport(MainGame.camWidth,MainGame.camHeigth));
        batch = game.batch;
        Skin skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));
        Table table = new Table();
        final TextButton play = new TextButton("Play", skin);
        final TextButton main = new TextButton("Menu",skin);
        //Cuando tocamos los botones realiza l accion descrita dentro
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                play.setDisabled(true);
                main.setDisabled(true);
            }
        });
        main.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
                play.setDisabled(true);
                main.setDisabled(true);
            }
        });

        table.setY(MainGame.camHeigth/8 - gameOver.getHeight());

        table.setFillParent(true);
        table.add(play).width(100).height(100);
        table.add(main).width(100).height(100);
        //colocamos la tabla en el stage para luego imprimirla
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        if (firstTime) {
            MainGame.endGameSound.play();
            firstTime = false;
        }

        batch.begin();
        batch.draw(background,0,0,MainGame.camWidth,MainGame.camHeigth);
        batch.end();

        stage.draw();

        batch.begin();
        GameScreen.score.draw(game.batch, (Integer) game.saveManager.loadDataValue("Score1",int.class));
        game.batch.draw(gameOver,MainGame.camWidth/2 - gameOver.getWidth()/2,MainGame.camHeigth - gameOver.getWidth() - 100);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        batch.dispose();
        gameOver.dispose();

    }
}
