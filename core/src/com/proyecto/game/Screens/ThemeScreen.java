package com.proyecto.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.proyecto.game.Theme;

/**
 * Created by frederick on 18/06/16.
 */
public class ThemeScreen extends BaseScreen {

    private Stage stage;
    //tabla para contener los botones
    private Table table;
    private Skin skin;
    private Texture background;
    private SpriteBatch batch;
    private BitmapFont font;

    public ThemeScreen(final MainGame game) {
        super(game);
        batch = game.batch;
        background = new Texture("background.png");
        stage = new Stage(new FitViewport(MainGame.camWidth,MainGame.camHeigth));
        skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));
        table = new Table();
        font = new BitmapFont(Gdx.files.internal("Jokerman.fnt"),Gdx.files.internal("Jokerman.png"),false);
        final TextButton squares = new TextButton("Squares", skin);
        final TextButton circles = new TextButton("Circles",skin);
        final TextButton rhombus = new TextButton("Rhombus", skin);
        //Cuando tocamos los botones realiza l accion descrita dentro
        squares.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen.assets = "Squares/";
                MainGame.theme = Theme.SQUARES;
                game.setScreen(new MenuScreen(game));
                squares.setDisabled(true);
                circles.setDisabled(true);
                rhombus.setDisabled(true);
            }
        });
        circles.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen.assets = "Circles/";
                MainGame.theme = Theme.CIRCLES;
                game.setScreen(new MenuScreen(game));
                squares.setDisabled(true);
                circles.setDisabled(true);
                rhombus.setDisabled(true);
            }
        });
        rhombus.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameScreen.assets = "Rhombus/";
                MainGame.theme = Theme.RHOMBUS;
                game.setScreen(new MenuScreen(game));
                squares.setDisabled(true);
                circles.setDisabled(true);
                rhombus.setDisabled(true);

            }
        });

        //Llenamos la taba que es una coleccion de actores
        table.setFillParent(true);
        table.add(squares).width(200).height(100);
        table.row();
        table.add(circles).width(200).height(100);
        table.row();
        table.add(rhombus).width(200).height(100);

        stage.addActor(table);

        font.setColor(Color.BLACK);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(background,0,0,MainGame.camWidth,MainGame.camHeigth);
        font.draw(batch,"THEMES", 10 + MainGame.camWidth/2 - ("THEMES".length() * font.getSpaceWidth()),MainGame.camHeigth - font.getXHeight());
        batch.end();

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        stage.dispose();
        batch.dispose();
        background.dispose();
        font.dispose();
    }
}
