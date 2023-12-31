package gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Game;

import org.w3c.dom.Text;

import managers.GameStateManager;

public class SettingState extends GameState{
    private final Texture right = new Texture("img/setting/right.PNG");
    private final Texture left = new Texture("img/setting/left.PNG");
    private final Texture volumeUnit = new Texture("img/setting/volumeUnit.png");

    private final Texture tick = new Texture("img/setting/tick.png");
    private final Texture btnBackENdark = new Texture("img/setting/btnBackENdark.png");
    private final Texture btnBackDEdark = new Texture("img/setting/btnBackDEdark.png");
    private final Texture musicNsoundDE = new Texture("img/setting/MusicNSoundDE.png");
    private final Texture languageDE = new Texture("img/setting/languageDE.png");
    private final Texture languageEN = new Texture("img/setting/languageEN.png");
    private final Texture musicNsoundEN = new Texture("img/setting/MusicNSoundEN.png");
    private final Texture flagEN = new Texture("img/setting/flagEN.png");
    private final Texture flagDE = new Texture("img/setting/flagDE.png");
    private final Texture flagENdark = new Texture("img/setting/flagENdark.png");
    private final Texture flagDEdark = new Texture("img/setting/flagDEdark.png");
    private final Texture masterVolume = new Texture("img/setting/masterVolume.png");
    private final Texture masterVolumeDE = new Texture("img/setting/masterVolumeDE.png");
    private final Texture bgmEN = new Texture("img/setting/bgmEN.png");
    private final Texture sfxEN = new Texture("img/setting/sfxEN.png");
    private final Texture btnBackEN = new Texture("img/setting/btnBackEN.png");
    private final Texture btnBackDE = new Texture("img/setting/btnBackDE.png");
    private final Texture sfxDE = new Texture("img/setting/sfxDE.png");
    private final Texture bgmDE = new Texture("img/setting/bgmDE.png");
    private TmxMapLoader mapLoader;

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    float X = 0;
    float Y = 0;
    public SettingState (GameStateManager gsm){
        super(gsm);
        init();
    }

    @Override
    public void init() {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("setting/setting.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        Game.cam.position.set(Game.gamePort.getWorldWidth()/2, Game.gamePort.getWorldHeight()/2,0);
    }

    @Override
    public void update(float var1) {
        Game.cam.update();
        renderer.setView(Game.cam);
    }

    @Override
    public void draw() {

        X = Gdx.input.getX();
        Y = Gdx.graphics.getHeight() - Gdx.input.getY();


        Game.batch.begin();

        renderer.render();

        Game.batch.draw(right,464, 480);
        Game.batch.draw(left,140, 480);

        if(Game.bgmVolume == 1.0F){
            Game.batch.draw(tick, 445 + 24,400);
        }
        if(Game.sfxVolume == 1.0F){
            Game.batch.draw(tick, 445 + 24, 320);
        }

        for(int i = 0; i < (int)Game.masterVolume;i++){
            Game.batch.draw(volumeUnit, 180 + (i+1)*10 + i*17,480);
        }

        if(Game.language == "en"){
            Game.batch.draw(masterVolume,140,550);
            Game.batch.draw(languageEN, 630,600);
            Game.batch.draw(flagEN, 560,400);
            Game.batch.draw(flagDEdark, 740, 400);
            Game.batch.draw(musicNsoundEN, 167, 610);
            Game.batch.draw(sfxEN, 138, 320);
            Game.batch.draw(bgmEN, 140, 400);
            if(360 < X && X < 360 + 280 && 80 < Y && Y < 80 + 120){
                Game.batch.draw(btnBackEN, 360, 80);
            }else {
                Game.batch.draw(btnBackENdark, 360, 80);
            }
        }else{
            Game.batch.draw(masterVolumeDE,140,545);
            Game.batch.draw(languageDE, 630,600);
            Game.batch.draw(flagENdark, 560,400);
            Game.batch.draw(flagDE, 740, 400);
            Game.batch.draw(musicNsoundDE, 168, 601);
            Game.batch.draw(sfxDE, 138, 320);
            Game.batch.draw(bgmDE, 140, 400);
            if(360 < X && X < 360 + 280 && 80 < Y && Y < 80 + 120){
                Game.batch.draw(btnBackDE, 360, 80);
            }else {
                Game.batch.draw(btnBackDEdark, 360, 80);
            }
        }

        Game.batch.end();



        handleInput();
    }

    @Override
    public void handleInput() {

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if(Game.masterVolume > 0){
                Game.masterVolume -= 1;
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            if(Game.masterVolume < 10){
                Game.masterVolume += 1;
            }
        }

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){



            if(140 < X && X < 140 + 40 && 480 < Y && Y < 480 + 40){
                if(Game.masterVolume > 0){
                    Game.masterVolume -= 1;
                }
            }

            if(464 < X && X < 464 + 40 && 480 < Y && Y < 480 + 40){
                if(Game.masterVolume < 10){
                    Game.masterVolume += 1;
                }
            }

            System.out.println(Game.masterVolume);

            if(360 < X && X < 360 + 280 && 80 < Y && Y < 80 + 120){
                this.gsm.setState(gsm.PLAY);
            }

            if(560 < X && X < 560 + 140 && 400 < Y && Y < 400 + 100){
                Game.language = "en";
            }
            if(740 < X && X < 740 + 140 && 400 < Y && Y < 400 + 100){
                Game.language = "de";
            }
            if(440  + 24< X && X < 440 + 40  + 24&& 400 < Y && Y < 400 + 40){
                if(Game.bgmVolume == 1.0F){
                    Game.bgmVolume = 0.0F;
                }else{
                    Game.bgmVolume = 1.0F;
                }
            }
            if(440  + 24< X && X < 440 + 40  + 24&& 320 < Y && Y < 320 + 40){
                if(Game.sfxVolume == 1.0F){
                    Game.sfxVolume = 0.0F;
                }else{
                    Game.sfxVolume = 1.0F;
                }
            }
        }
    }

    @Override
    public void dispose() {
        masterVolumeDE.dispose();
        bgmDE.dispose();
        btnBackDE.dispose();
        btnBackEN.dispose();
        languageDE.dispose();
        languageEN.dispose();
        musicNsoundEN.dispose();
        flagDE.dispose();
        flagEN.dispose();
        masterVolume.dispose();
        bgmEN.dispose();
        sfxEN.dispose();
        tick.dispose();
        flagDEdark.dispose();
        flagEN.dispose();
        musicNsoundDE.dispose();
        btnBackENdark.dispose();
        btnBackDEdark.dispose();
        sfxDE.dispose();
        right.dispose();
        left.dispose();
        volumeUnit.dispose();
    }
}
