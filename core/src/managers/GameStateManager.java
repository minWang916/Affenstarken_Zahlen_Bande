package managers;

import com.mygdx.game.Game;

import gamestates.*;

public class GameStateManager {


    private GameState gameState;

    public static final int MENU = 0;
    public static final int PLAY = 1;
    public static final int END = 2;
    public static final int SETTING = 3;
    public static final int GUIDE = 4;

    public GameStateManager(){


        setState(MENU);
    }

    public void setState(int state) {
        if (this.gameState != null) {
            this.gameState.dispose();
        }

        if (state == 0) {
            this.gameState = new MenuState(this);
        }

        if (state == 1) {
            this.gameState = new PlayState(this);
        }
        if(state == 2){
            this.gameState = new EndState(this);
        }
        if(state == 3){
            this.gameState = new SettingState(this);
        }
        if(state == 4){
            this.gameState = new GuideState(this);
        }
    }

    public void update(float dt) {
        this.gameState.update(dt);
    }

    public void draw() {
        this.gameState.draw();
    }

}
