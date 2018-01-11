package com.proyecto.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static java.lang.Thread.sleep;


/**
 * Created by frederick on 29/06/16.
 */
public class InitialScreen extends BaseScreen {
    private BitmapFont font;
    private java.lang.String text;
    private SpriteBatch batch;

    public InitialScreen(final MainGame game) {
        super(game);
        batch = game.batch;
        font = new BitmapFont(Gdx.files.internal("Jokerman.fnt"),Gdx.files.internal("Jokerman.png"),false);
        font.setColor(Color.ORANGE);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        text = "Amaya, Brian";
        font.draw(batch,text, 10 + MainGame.camWidth/2 - (text.length() * font.getSpaceWidth()),MainGame.camHeigth/2 - font.getXHeight());
        text = "Borges, Frederick";
        font.draw(batch,text, 10 + MainGame.camWidth/2 - (text.length() * font.getSpaceWidth()),MainGame.camHeigth/3 - font.getXHeight());
        text = "Press to continue:";
        font.draw(batch,text, 10 + MainGame.camWidth/2 - (text.length() * font.getSpaceWidth()),MainGame.camHeigth - font.getXHeight());
        batch.end();

        if (Gdx.input.justTouched()){
            game.setScreen(new MenuScreen(game));
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
        batch.dispose();
    }
}
