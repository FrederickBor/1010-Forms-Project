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

import javafx.scene.layout.Background;

/**
 * Created by frederick on 18/06/16.
 */
public class MenuScreen extends BaseScreen {

    private Stage stage;
    private SpriteBatch batch;
    private Texture logo;
    //tabla para contener los botones
    private Table table;
    private Skin skin;
    private Texture background;

    public MenuScreen(final MainGame game) {
        super(game);
        background = new Texture("background.png");
        stage = new Stage(new FitViewport(MainGame.camWidth,MainGame.camHeigth));
        batch = game.batch;
        logo = new Texture(Gdx.files.internal("logo.png"));
        skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));
        table = new Table();
        final TextButton play = new TextButton("Play", skin);
        final TextButton score = new TextButton("Score",skin);
        final TextButton theme = new TextButton("Themes", skin);
        final TextButton modes = new TextButton("Game Modes", skin);
        //Cuando tocamos los botones realiza l accion descrita dentro
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
                play.setDisabled(true);
                theme.setDisabled(true);
                score.setDisabled(true);
                modes.setDisabled(true);
            }
        });
        theme.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ThemeScreen(game));
                play.setDisabled(true);
                theme.setDisabled(true);
                score.setDisabled(true);
                modes.setDisabled(true);
            }
        });
        score.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ScoreScreen(game));
                play.setDisabled(true);
                theme.setDisabled(true);
                score.setDisabled(true);
                modes.setDisabled(true);
            }
        });
        modes.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new ModeScreen(game));
                play.setDisabled(true);
                theme.setDisabled(true);
                score.setDisabled(true);
                modes.setDisabled(true);
            }
        });

        //Llenamos la taba que es una coleccion de actores
        table.add(play).width(100).height(100);
        table.add(score).width(100).height(100);
        table.row();
        table.add(theme).colspan(2).height(100).fill();
        table.row();
        table.add(modes).colspan(2).height(100).fill();
        table.setPosition(MainGame.camWidth/2 - table.getWidth()/2, MainGame.camHeigth/4 - table.getHeight()/2);
        //colocamos la tabla en el stage para luego imprimirla
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background,0,0,MainGame.camWidth,MainGame.camHeigth);
        batch.draw(logo,MainGame.camWidth/2 - logo.getWidth()/2, MainGame.camHeigth/4 + logo.getHeight()/2);
        batch.end();

        stage.draw();
        stage.act(delta);
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        logo.dispose();
        background.dispose();
    }
}