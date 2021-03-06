package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.screens.PlayScreen;

/**
 * Created by koushik on 10/28/17.
 */

public abstract class Enemy extends Sprite{
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 velocity;
    public Enemy(PlayScreen screen,float x,float y)
    {
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
        velocity = new Vector2(1,0);
    }

    protected abstract void defineEnemy();
    public abstract void hitOnHead();
    public abstract void eat();

    public void reverseVelocity(boolean x,boolean y)
    {

        if(x)
            velocity.x = - velocity.x;
        if(y)
            velocity.y = -velocity.y;
    }
    public abstract void update(float dt);
}
