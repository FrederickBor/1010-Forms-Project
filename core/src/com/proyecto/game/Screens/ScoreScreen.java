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
import com.badlogic.gdx.utils.Align;
import com.proyecto.game.Score;

/**
 * Created by frederick on 19/06/16.
 **/

public class ScoreScreen extends BaseScreen {

    private Stage stage;
    private BitmapFont font;
    private Texture background;
    private SpriteBatch batch;

    public ScoreScreen(final MainGame game) {
        super(game);
        font = new BitmapFont(Gdx.files.internal("Jokerman.fnt"),Gdx.files.internal("Jokerman.png"),false);
        batch = game.batch;
        background = new Texture("background.png");
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("Skins/uiskin.json"));
        final TextButton button = new TextButton("Return",skin);

        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
        Table table = new Table();

        table.setFillParent(true);

        table.add(new Label("",skin));
        table.row();

        for(int i=1;i<=10;i++){
            Label score = new Label(i+". "+ game.saveManager.loadDataValue("Score"+i, int.class), skin);
            if (i == 1){
                score.setFontScale(1.2f);
            }
            score.setColor(Color.BLACK);
            table.add(score).padBottom(2).align(Align.center);
            table.row();
        }

        table.add(button).width(100).height(50);

        stage.addActor(table);

        font.setColor(Color.BLACK);

        Gdx.input.setInputProcessor(stage);
    }



    @Override
    public void render(float delta) {

        batch.begin();
        batch.draw(background,0,0,MainGame.camWidth,MainGame.camHeigth);
        font.draw(batch,"LEADERBOARD", 10 + MainGame.camWidth/2 - ("LEADERBOARD".length() * font.getSpaceWidth()),MainGame.camHeigth - font.getXHeight());
        batch.end();



        stage.act();
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