package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.BananaKong;
import com.mygdx.game.GameConstants;

/**
 * Created by koushik on 10/9/17.
 */

public class LvlScreen implements Screen {

    final BananaKong game;
    private Texture texture,cred;
    private Skin myskin;
    private Stage stage;
    private BitmapFont fontlvl;
    private Label gameTitle;
    private Button Lvl1Btn,Lvl2Btn,Lvl3Btn,BackBtn;

    public LvlScreen(final BananaKong game)
    {
        this.game = game;
        texture = new Texture("level.jpg");
        stage = new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        myskin = new Skin(Gdx.files.internal("skin/gold/golden-ui-skin.json"));

        //Title set for Level
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/stngfont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();

        params.size = 80;
        params.color = Color.GREEN;
        params.shadowColor = Color.DARK_GRAY;
        params.shadowOffsetY = 5;
        fontlvl = generator.generateFont(params);
        generator.dispose();
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = fontlvl;

        //Why does public ms.font80 don't work ??


        //Button and extensions
        gameTitle = new Label("Level", labelStyle);
        gameTitle.setSize(GameConstants.col_width*2,GameConstants.row_height*2);
        gameTitle.setPosition(GameConstants.centerX - gameTitle.getWidth()/2,GameConstants.centerY + GameConstants.centerY/4);
        gameTitle.setAlignment(Align.center);

        AddButton();
        stage.addActor(gameTitle);
    }

    //adding buttons
    public void AddButton()
    {
        Lvl1Btn = new TextButton("Amateur", myskin , "default");
        Lvl1Btn.setSize(GameConstants.col_width*2,GameConstants.row_height);
        Lvl1Btn.setPosition(GameConstants.centerX - Lvl1Btn.getWidth()/2,gameTitle.getY()-Lvl1Btn.getHeight());

        Lvl2Btn = new TextButton("Pro", myskin , "default");
        Lvl2Btn.setSize(GameConstants.col_width*2,GameConstants.row_height);
        Lvl2Btn.setPosition(GameConstants.centerX - Lvl2Btn.getWidth()/2,Lvl1Btn.getY() - Lvl2Btn.getHeight());

        Lvl3Btn = new TextButton("Expert", myskin , "default");
        Lvl3Btn.setSize(GameConstants.col_width*2,GameConstants.row_height);
        Lvl3Btn.setPosition(GameConstants.centerX - Lvl3Btn.getWidth()/2,Lvl2Btn.getY() - Lvl3Btn.getHeight());

        BackBtn = new TextButton("Back", myskin , "default");
        BackBtn.setSize(GameConstants.col_width*2,GameConstants.row_height);
        BackBtn.setPosition(0,0);
        BackBtn.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoSettingsScreen();

                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        //adding them to the stage
        stage.addActor(Lvl1Btn);
        stage.addActor(Lvl2Btn);
        stage.addActor(Lvl3Btn);
        stage.addActor(BackBtn);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1,0,0,0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(texture,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //game.batch.draw(cred,0,0,game.WIDTH,game.HEIGHT);
        game.batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
