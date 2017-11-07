package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BananaKong;
import com.mygdx.game.GameConstants;
import com.mygdx.game.Scenes.Hud;

//import static com.mygdx.game.Scenes.Hud.isPause;

/**
 * Created by koushik on 10/11/17.
 */

public class PauseScreen implements Screen {
    final BananaKong game;
    private Texture pauseImg;
    private PlayScreen ps;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private Stage stage;
    private Skin myskin;
    private Button playBtn,OptionBtn,MenuBtn;
    public PauseScreen(final BananaKong game)
    {
        this.game = game;

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(game.WIDTH,game.HEIGHT);
        hud = new Hud(game.batch);

        stage =new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        myskin = new Skin(Gdx.files.internal("skin/gold/golden-ui-skin.json"));

        pauseImg = new Texture("pauseImg.jpg");
        //pausemenu();
    }

    public void pausemenu()
    {

        playBtn = new TextButton("Play", myskin , "default");
        playBtn.setSize(GameConstants.col_width*2,GameConstants.row_height);
        playBtn.setPosition(600,GameConstants.centerY);
        playBtn.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                rmv();
               // hud.isPause = false;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });
        OptionBtn = new TextButton("Option", myskin , "default");
        OptionBtn.setSize(GameConstants.col_width*2,GameConstants.row_height);
        OptionBtn.setPosition(600,playBtn.getY() - 100);
        OptionBtn.addListener(new InputListener() {

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


        MenuBtn = new TextButton("Menu", myskin , "default");
        MenuBtn.setSize(GameConstants.col_width*2,GameConstants.row_height);
        MenuBtn.setPosition(600,playBtn.getY() - 200);
        MenuBtn.addListener(new InputListener()
        {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.gotoMenuScreen();
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });


        stage.addActor(playBtn);
        stage.addActor(OptionBtn);
        stage.addActor(MenuBtn);

    }

    public void rmv(){
        playBtn.remove();
        OptionBtn.remove();
        MenuBtn.remove();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //if(isPause)update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

      //  if(isPause)
        {
            //System.out.println("what");
            pausemenu();
            game.batch.begin();
            game.batch.draw(pauseImg,0,0,game.WIDTH,game.HEIGHT);
            game.batch.end();
        }
        //game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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

    }
}
