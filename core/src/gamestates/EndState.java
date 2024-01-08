package gamestates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.Game;

import org.w3c.dom.Text;

import managers.GameStateManager;
import managers.PlayStateController;

public class EndState extends GameState{
    private final Sound soundWin = Gdx.audio.newSound(Gdx.files.internal("sound/se/win.mp3"));
    private final Sound soundLose = Gdx.audio.newSound(Gdx.files.internal("sound/se/fail.mp3"));
    private final Texture btnExit = new Texture("img/btnExit.png");
    private final Texture btnAgain = new Texture("img/btnAgain.png");
    private final Texture btnExitbright = new Texture("img/btnExitbright.png");
    private final Texture btnAgainbright = new Texture("img/btnAgainbright.png");
    private final Texture sceneWin = new Texture("img/scene_win.png");
    private final Texture sceneLose = new Texture("img/scene_lose.png");
    private final Texture phraseWin = new Texture("img/youwin.png");
    private final Texture phraseLose = new Texture("img/youlose.png");
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private boolean ready = true;
    private float X = 0;
    private float Y = 0;
    private OrthogonalTiledMapRenderer renderer;
    public EndState (GameStateManager gsm){
        super(gsm);
        init();
    }

    @Override
    public void init() {
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("end/end_map.tmx");
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

        Game.batch.begin();

        renderer.render();

        if(Game.endResult == "win"){
            Game.batch.draw(sceneWin,120,320);
            Game.batch.draw(phraseWin,300,230);
            if(ready){
                soundWin.play(0.25f);
                ready = false;
            }
        }else{
            Game.batch.draw(sceneLose,120,320);
            Game.batch.draw(phraseLose,280,230);
            if(ready){
                soundLose.play(0.25f);
                ready = false;
            }
        }

        X = Gdx.input.getX();
        Y = Gdx.graphics.getHeight() - Gdx.input.getY();

        if(160 < X && X < 160 + 280 && 80 < Y && Y < 80 + 120){
            Game.batch.draw(btnAgainbright, 160,80);
        }else{
            Game.batch.draw(btnAgain, 160,80);
        }

        if(560 < X && X < 560 + 280 && 80 < Y && Y < 80 + 120){
            Game.batch.draw(btnExitbright, 560,80);
        }else{
            Game.batch.draw(btnExit, 560,80);
        }

        Game.batch.end();


        handleInput();
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            if(560 < X && X < 560 + 280 && 80 < Y && Y < 80 + 120){
                System.exit(0);
            }
            if(160 < X && X < 160 + 280 && 80 < Y && Y < 80 + 120){
                Game.endResult = "";
                this.gsm.setState(gsm.PLAY);
            }
        }
    }

    @Override
    public void dispose() {
        sceneWin.dispose();
        sceneLose.dispose();
        phraseWin.dispose();
        phraseLose.dispose();
    }
}
