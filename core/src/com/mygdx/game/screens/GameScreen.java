package com.mygdx.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.MusicLoader;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.BananaKong;
import com.mygdx.game.Drawer.NavigationDrawer;
import com.mygdx.game.GameConstants;
import com.mygdx.game.Scenes.Hud;

/**
 * Created by koushik on 10/7/17.
 */

public class GameScreen implements Screen {
    final BananaKong game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    private Stage stage;
    private Skin myskin;
    private Button playBtn,OptionBtn,MenuBtn;
    public final Button stBtn;
    public static boolean isPause;
    private Texture pauseImg;

    public GameScreen(final BananaKong game)
    {
        this.game = game;

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(game.WIDTH,game.HEIGHT);
        hud = new Hud(game.batch);

        stage =new Stage(game.screenPort);
        Gdx.input.setInputProcessor(stage);
        myskin = new Skin(Gdx.files.internal("skin/gold/golden-ui-skin.json"));

        isPause = false;
        game.music.stop();
        pauseImg = new Texture("pauseImg.jpg");


        stBtn = new TextButton("Pause", myskin , "default");
        stBtn.setSize(GameConstants.col_width*2,GameConstants.row_height);
        stBtn.setPosition(GameConstants.centerX - stBtn.getWidth()/2,GameConstants.centerY);
        stBtn.addListener(new InputListener() {

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(!isPause ) {
                    isPause = true;
                    //game.gotoPauseScreen();
                    stBtn.remove();
                    pausemenu();
                }
                //else isPause = false;
                return true;
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                super.touchUp(event, x, y, pointer, button);
            }
        });

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
                isPause = false;
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

   /* public void handleInput(float dt) {
        if(Gdx.input.justTouched());
        gamecam.position.x += 100* dt;
    }*/

    public void update(float delta)
    {
       // handleInput(delta);
        //gamecam.position.x += 100* delta;

        gamecam.update();
       // renderer.setView(gamecam);

        stage.addActor(stBtn);
        hud.update(delta);
    }
    @Override
    public void show() {
        stage = new Stage(new StretchViewport(1080, 1920));

        // May be you want to make some Actions with NavigationDrawer state
        NavigationDrawer.NavigationDrawerListener listener = new NavigationDrawer.NavigationDrawerListener() {

            @Override
            public void onStart() {
                System.out.println("onStart");
            }

            @Override
            public void onRun() {
                System.out.println("onRun");
            }

            @Override
            public void onFinish(float camX) {
                System.out.println("onFinish: " + camX);
            } };

        // You must be initialize NavigationDrawer Firstly
        NavigationDrawer.initialize(stage, listener);

        // This image is sample to show how navigationDrawer look like on the screen
        Image background= new Image(new Texture(Gdx.files.internal("load1.jpg")));
        background.addListener(new ClickListener() {
            private int clicked = 0;
            public void clicked(InputEvent event, float x, float y) {
                if (clicked % 2 == 0) {
                    clicked++;
                    NavigationDrawer.show(true);
                } else {
                    clicked++;
                    NavigationDrawer.show(false);
                }
            }
        });
        background.setFillParent(true);
        stage.addActor(background);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(1,0,0,0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!isPause) {
            update(delta);

            }
        if(isPause)
        {
            //System.out.println("what");
            game.batch.begin();
            game.batch.draw(pauseImg,0,0,game.WIDTH,game.HEIGHT);
            game.batch.end();
        }
        //game.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        stage.act();
        stage.draw();
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        game.screenPort.update(width, height);
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
