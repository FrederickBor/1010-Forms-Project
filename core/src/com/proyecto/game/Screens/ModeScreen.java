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

/**
 * Created by frederick on 18/06/16.
 */
public class ModeScreen extends BaseScreen {

    private Stage stage;
    //tabla para contener los botones
    private Table table;
    private Skin skin;
    private Texture background;
    private SpriteBatch batch;
    private BitmapFont font;

    public ModeScreen(final MainGame game) {
        super(game);
        batch = game.batch;
        background = new Texture("background.png");
        stage = new Stage(new FitViewport(MainGame.camWidth,MainGame.camHeigth));
        skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));
        table = new Table();
        font = new BitmapFont(Gdx.files.internal("Jokerman.fnt"),Gdx.files.internal("Jokerman.png"),false);
        final TextButton normal = new TextButton("Normal", skin);
        final TextButton extended = new TextButton("Extended",skin);
        //Cuando tocamos los botones realiza l accion descrita dentro
        normal.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MainGame.version = 19;
                game.setScreen(new MenuScreen(game));
                normal.setDisabled(true);
                extended.setDisabled(true);
            }
        });
        extended.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                MainGame.version = 29;
                game.setScreen(new MenuScreen(game));
                normal.setDisabled(true);
                extended.setDisabled(true);
            }
        });

        //Llenamos la taba que es una coleccion de actores
        table.setFillParent(true);
        table.add(normal).width(200).height(100);
        table.row();
        table.add(extended).width(200).height(100);

        stage.addActor(table);

        font.setColor(Color.BLACK);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(background,0,0,MainGame.camWidth,MainGame.camHeigth);
        font.draw(batch,"GAME MODE", 10 + MainGame.camWidth/2 - ("GAME MODE".length() * font.getSpaceWidth()),MainGame.camHeigth - font.getXHeight());
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