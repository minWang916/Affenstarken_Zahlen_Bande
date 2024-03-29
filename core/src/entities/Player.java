package entities;

import static managers.PlayStateController.PHASE_ELEPHANT;
import static managers.PlayStateController.PHASE_MONKEY;
import static managers.PlayStateController.PHASE_SPECIAL;
import static managers.PlayStateController.phase;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import managers.PlayStateController.*;

import entities.Cords;
import entities.Card;

public class Player {
    private BitmapFont font;
    private static SpriteBatch batch;

    public String name;
    private int id;

    private String state = "";

    private boolean playing = false;

    private Texture avatar;

    public Card[] cards = new Card[4];

    public Player (String name, int avatarIndex, int id, SpriteBatch batch) {
        this.name = name;
        this.id = id;
        this.batch = batch;
        avatar = new Texture("img/P"+String.valueOf(avatarIndex+1) +"_avatar.png");
        System.out.println("Player created with the name " + this.name + " and id: " + this.id);

        init();
    }

    public void init(){
        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(
                Gdx.files.internal("font/04B_03__.ttf")
        );
        FreeTypeFontGenerator.FreeTypeFontParameter param_titleFont = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param_titleFont.size = 20;
        font = gen.generateFont(param_titleFont);

        //Deal cards
        for (int i = 0; i<4; i++) {
            cards[i] = new Card((int)(Math.random()*11 + 5), Cords.colors[(int)(Math.random()*4)], batch, id, i);
        }
    }

    public void update(){
        for (int i = 0; i<4; i++){
            cards[i].update();
        }
    }

    public void draw(){
        // Draw name of the player corresponding to the phase
        if (playing == true) {
            if(phase == PHASE_SPECIAL){
                font.setColor(Color.GREEN);
            }
            if(phase == PHASE_MONKEY){
                font.setColor(Color.YELLOW);
            }
            if(phase == PHASE_ELEPHANT){
                font.setColor(Color.ORANGE);
            }
        }
        int offset = (int)(name.length() * 4);
        font.draw(batch, name, Cords.all_player_cord[id][0][0] + 20 - offset, Cords.all_player_cord[id][0][1] + 80);
        font.setColor(Color.WHITE);

        // Draw avatar of the player
        batch.draw(avatar, Cords.all_player_cord[id][0][0], Cords.all_player_cord[id][0][1]);

        // Draw cards of the player
        for (int i = 0; i<4; i++) {
            cards[i].draw();
        }
    }

    public void startTurn(){
        System.out.println("This is my turn and I am " + name);
        playing = true;
    }

    public void endTurn(){
        System.out.println("I am ending my turn and I am " + name);
        playing = false;
    }
}

