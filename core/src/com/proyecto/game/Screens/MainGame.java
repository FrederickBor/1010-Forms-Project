package com.proyecto.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.proyecto.game.SaveManager;
import com.proyecto.game.Score;
import com.proyecto.game.Theme;



import java.io.File;

/**
 * Created by frederick on 18/06/16.
 */

public class MainGame extends Game{
    public static float camWidth;
    public static float camHeigth;
    public static Sound fillPieceBarSound;
    public static Sound emptySound;
    public static Sound endGameSound;
    public static Sound beginSound;
    public static int version;
    public OrthographicCamera camera;
    public SpriteBatch batch;
    public SaveManager saveManager;
    public static Preferences preferences;
    public static Theme theme;


    public static Preferences getPrefs() {
        if (preferences == null) {
            preferences = Gdx.app.getPreferences("1010 Forms");
        }
        if (preferences == null) {
            System.out.println("null preferences");
        }
        return preferences;
    }

    private void prepareLocalScores() {
        for(int i=1;i<=10;i++){
            saveManager.saveDataValue("Score"+i, getPrefs().getInteger("Score" + i,0));
            System.out.println(saveManager.loadDataValue("Score"+i, int.class));
        }
    }

    private void loadLocalScores(){
        for(int i=1;i<=10;i++){
            System.out.println(saveManager.loadDataValue("Score"+i, int.class));
        }
    }

    @Override
    public void pause() {
        super.pause();

    }

    @Override
    public void create() {
        FileHandle file = Gdx.files.local("bin/scores.json");
        saveManager = new SaveManager();
        loadLocalScores();

        prepareLocalScores();

        //Para que no tenga errores al iniciar
        GameScreen.assets = "Squares/";
        theme = Theme.SQUARES;
        MainGame.version = 19;
        //Sonidos
        beginSound = Gdx.audio.newSound(Gdx.files.internal("start.mp3"));
        fillPieceBarSound = Gdx.audio.newSound(Gdx.files.internal("HolePunch.mp3"));
        emptySound = Gdx.audio.newSound(Gdx.files.internal("Pew.mp3"));
        endGameSound = Gdx.audio.newSound(Gdx.files.internal("EndOfGame.mp3"));
        //agregando valores a la camara
        camWidth = Gdx.graphics.getWidth();
        camHeigth = Gdx.graphics.getHeight();
        //Camara
        camera = new OrthographicCamera(camWidth,camHeigth);
        camera.position.set(camWidth/2,camHeigth/2,0);
        //SpriteBatch
        batch = new SpriteBatch();

        setScreen(new InitialScreen(this));
    }


    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width, height);
    }

    public void dispose() {
        super.dispose();
        batch.dispose();

        endGameSound.dispose();
        beginSound.dispose();
        fillPieceBarSound.dispose();
        emptySound.dispose();
        getPrefs().flush();
    }
}