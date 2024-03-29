package managers;

import static managers.PlayStateController.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Game;

import managers.PlayStateController.*;

public class GameSpecialHandler {

    static Sound select = Gdx.audio.newSound(Gdx.files.internal("sound/se/select.mp3"));
    static Sound deselect = Gdx.audio.newSound(Gdx.files.internal("sound/se/deselect.mp3"));
    static Texture hint0 = new Texture("img/hint0.png");
    static Texture hint1 = new Texture("img/hint1.png");
    static Texture hint2 = new Texture("img/hint2.png");
    static Texture hint3 = new Texture("img/hint3.png");
    static Texture hint4 = new Texture("img/hint4.png");
    static Texture hint5 = new Texture("img/hint5.png");
    static Texture hint0de = new Texture("img/hint0de.png");
    static Texture hint1de = new Texture("img/hint1de.png");
    static Texture hint2de = new Texture("img/hint2de.png");
    static Texture hint3de = new Texture("img/hint3de.png");
    static Texture hint4de = new Texture("img/hint4de.png");
    static Texture hint5de = new Texture("img/hint5de.png");

    static float x = 340;
    static float y = 275;


    public static void special(){

            float X = Gdx.input.getX();
            float Y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if(useable_special[0]  == 0){

                if(202 < X && X < 263 && 529 < Y && Y < 583){



                    if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {

                        if (selected_special == 0) {
                            selected_special = 99;
                            deselect.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                        } else {
                            selected_special = 0;
                            select.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                        }
                        System.out.println("0");
                    }
                }
            }


            if(useable_special[1] == 0){
            if(202 < X && X < 263 && 367 < Y && Y < 426){
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if(selected_special == 1){
                    selected_special = 99;
                    deselect.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                else{
                    selected_special = 1;
                    select.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                    System.out.println("1");

            }}}

            if(useable_special[2] == 0){
            if(202 < X && X < 263 && 209 < Y && Y < 267){
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if(selected_special == 2){
                    selected_special = 99;
                    deselect.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                else{
                    selected_special = 2;
                    select.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                    System.out.println("2");
            }}}

            if(useable_special[3] == 0){
            if(739 < X && X < 797 && 529 < Y && Y < 583){
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if(selected_special == 3){
                    selected_special = 99;
                    deselect.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                else{
                    selected_special = 3;
                    select.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                    System.out.println("3");

            }}}

            if(useable_special[4] == 0){
            if(739 < X && X < 797 && 367 < Y && Y < 426){
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if(selected_special == 4){
                    selected_special = 99;
                    deselect.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                else{
                    selected_special = 4;
                    select.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                    System.out.println("4");

            }}}

            if(useable_special[5] == 0){
            if(739 < X && X < 797 && 209 < Y && Y < 267){
                if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if(selected_special == 5){
                    selected_special = 99;
                    deselect.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                else{
                    selected_special = 5;
                    select.play(0.1f*Game.sfxVolume*Game.masterVolume/10);
                }
                    System.out.println("5");

            }}}
        }


    public static void dispose(){
        select.dispose();
        deselect.dispose();
        hint0.dispose();
        hint1.dispose();
        hint2.dispose();
        hint3.dispose();
        hint4.dispose();
        hint5.dispose();
        hint0de.dispose();
        hint1de.dispose();
        hint2de.dispose();
        hint3de.dispose();
        hint4de.dispose();
        hint5de.dispose();
    }

}
