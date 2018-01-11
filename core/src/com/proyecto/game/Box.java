package com.proyecto.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

/**
 * Created by frederick on 08/06/16.
 */
public class Box {
    public Texture imagen;
    boolean busy;

    public void dispose(){
        imagen.dispose();
    }

    public Box (String color){
        imagen = new Texture (color);
        busy = false;
    }

    public void setTexture(String color){
        imagen = new Texture(color);
    }

    public void setTexture(Texture texture){
        imagen = texture;
    }
}
