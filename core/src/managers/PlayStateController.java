package managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Game;
import entities.*;
import java.util.ArrayList;


public class PlayStateController {
    public static ArrayList<Card> allMonkeyCards = new ArrayList<>();
    public static ArrayList<Card> selectedMonkeyCards = new ArrayList<>();
    public static Elephant_Card elephant1 = new Elephant_Card(1,3,"img/ele1.png","img/ele3.png",Cords.elephant_1_x, Cords.elephant_1_y,0);
    public static Elephant_Card elephant2 = new Elephant_Card(2,4,"img/ele2.png","img/ele4.png",Cords.elephant_2_x, Cords.elephant_2_y,1);
    public static Elephant_Card[] Elephant_cards = {elephant1, elephant2};

    public static Texture cordMap = new Texture("img/cordMap.png");

    public static Texture frame = new Texture("img/frame_v.png");
    public static Texture special_frame = new Texture("img/frame_c.png");
    public static Texture elephant_frame = new Texture("img/frame_v.png");
    public static int phase = 0;
    public static int selected_special = 99;
    public static int numOfPlayers = 4;
    public static Player currentPlayer;
    public static int currentPlayerIndex;
    public static Player[] players = new Player[4];

    public static boolean selected = false;
    public static int selected_plus_or_minus = 99;

    public static Monkey blue;
    public static Monkey orange;
    public static Monkey pink;
    public static Monkey green;
    public static Monkey[] monkeys = new Monkey[4];

    public static Elephant elephant;

    private static final Texture holdOn = new Texture("img/special_1.png");
    private static final Texture breakTime = new Texture("img/special_2.png");
    private static final Texture monkeySwap = new Texture("img/special_3.png");
    private static final Texture turboElephant = new Texture("img/special_4.png");
    private static final Texture freeMove = new Texture("img/special_5.png");
    private static final Texture exchange = new Texture("img/special_6.png");
    public static int[] useable_special = {0,0,0,0,0,0};
    public static final int PHASE_SPECIAL = 0;
    public static final int PHASE_MONKEY = 1;
    public static final int PHASE_ELEPHANT = 2;
    public static void init(){
        //-------------------- Entities------------------------------------------
        blue = new Monkey("blue");
        orange = new Monkey("orange");
        green = new Monkey("green");
        pink = new Monkey("pink");
        monkeys = new Monkey[]{orange, green, blue, pink};
        elephant = new Elephant();
        //-------------------- Entities------------------------------------------

        //------------------- Turn based-----------------------------------------
        String[] playerNames = {"Toby", "Thomas", "Kevin", "Michael"};
        for(int i = 0; i< 4; i++){
            players[i] = new Player(playerNames[i], i, Game.batch);
        }
        currentPlayerIndex = 0;
        currentPlayer = players[0];
        currentPlayer.startTurn();
        //------------------- Turn based-----------------------------------------
    }

    public static void handleInput(){

        if(phase == PHASE_SPECIAL){
            GameInputHandler.special();
            Confirm_button.handleInput();
        }else if(phase == PHASE_MONKEY){
            Plus_Button.handleClick();
            Minus_Button.handleClick();
            Confirm_button.handleInput();
        }else if(phase == PHASE_ELEPHANT){
            elephant2.handleClick();
            elephant1.handleClick();
            Confirm_button.handleInput();
        }

    }

    public static void update(){

        handleInput();


        //---------- Leaves -----------------------

        //---------- Leaves ------------------------


        //---- Monkeys and elephant-----------------
        checkWeight();
        //---- Monkeys and elephant-----------------


        //--- Player (and cards - inside players)----

        for (int i = 0; i<4; i++){
            players[i].update();
        }

        //--- Player (and cards - inside players)----


        //------------ Special ----------------------

        //------------ Special ----------------------
    }

    public static void draw(){
        //---------- Players -----------------------
        for (int i = 0; i < numOfPlayers; i++) { players[i].draw(); }
        //---------- Players -----------------------

        //---------- Leaves ------------------------


        //---------- Leaves ------------------------


        //---------- Cord map ----------------------
        Game.batch.draw(cordMap,Cords.cordMap_x,Cords.cordMap_y);
        //---------- Cord map ----------------------


        //---- Monkeys and elephant-----------------
        for (int i=0; i<4; i++) { monkeys[i].draw(); }
        elephant.draw();
        //---- Monkeys and elephant-----------------


        //-------- Player and cards-------------------
        for (int i = 0; i < numOfPlayers; i++) { players[i].draw(); }
        //-------- Player and cards-------------------


        //------------ Special ----------------------
        float X = Gdx.input.getX();
        float Y = Gdx.graphics.getHeight() - Gdx.input.getY();
        if(useable_special[0] == 0){
            if(202 < X && X < 263 && 529 < Y && Y < 583){
                Game.batch.draw(GameInputHandler.hint0,GameInputHandler.x,GameInputHandler.y);
            }
            Game.batch.draw(holdOn, Cords.special[0][0], Cords.special[0][1]);
        }

        if(useable_special[1] == 0){
            if(202 < X && X < 263 && 367 < Y && Y < 426){
                Game.batch.draw(GameInputHandler.hint1,GameInputHandler.x,GameInputHandler.y);
            }
            Game.batch.draw(breakTime, Cords.special[1][0], Cords.special[1][1]);
        }

        if(useable_special[5] == 0){
            if(739 < X && X < 797 && 209 < Y && Y < 267){
                Game.batch.draw(GameInputHandler.hint5,GameInputHandler.x,GameInputHandler.y);
            }
            Game.batch.draw(exchange, Cords.special[5][0], Cords.special[5][1]);
        }

        if(useable_special[3] == 0){
            if(739 < X && X < 797 && 529 < Y && Y < 583){
                Game.batch.draw(GameInputHandler.hint3,GameInputHandler.x,GameInputHandler.y);
            }
            Game.batch.draw(turboElephant, Cords.special[3][0], Cords.special[3][1]);
        }

        if(useable_special[2] == 0){
            if(202 < X && X < 263 && 209 < Y && Y < 267){
                Game.batch.draw(GameInputHandler.hint2,GameInputHandler.x,GameInputHandler.y);
            }
            Game.batch.draw(monkeySwap, Cords.special[2][0], Cords.special[2][1]);
        }

        if(useable_special[4] == 0){
            if(739 < X && X < 797 && 367 < Y && Y < 426){
                Game.batch.draw(GameInputHandler.hint4,GameInputHandler.x,GameInputHandler.y);
            }
            Game.batch.draw(freeMove, Cords.special[4][0], Cords.special[4][1]);
        }

        if(selected_special != 99){
            Game.batch.draw(special_frame, Cords.special[selected_special][0] - (float)7, Cords.special[selected_special][1] - (float)7);
        }
        //------------ Special ----------------------


        //-------- Confirm Button ----------------------
        Confirm_button.draw();
        //-------- Confirm Button ----------------------


        //-------- Plus and Minus Button ----------------------
        if(phase == PHASE_MONKEY){
            if(selected_plus_or_minus == 99 || selectedMonkeyCards.size() != 2){
                Plus_Button.draw_dark();
                Minus_Button.draw_dark();
            }else if(selected_plus_or_minus == 0){
                Plus_Button.draw_bright();
                Minus_Button.draw_dark();
            }else if(selected_plus_or_minus == 1){
                Plus_Button.draw_dark();
                Minus_Button.draw_bright();
            }
        }
        //-------- Plus and Minus Button ----------------------


        //-------- Elephant Card ----------------------
            elephant1.draw();
            elephant2.draw();
            if(Elephant_Card.selected != 99){
                Game.batch.draw(elephant_frame, Elephant_cards[Elephant_Card.selected].x - 9, Elephant_cards[Elephant_Card.selected].y - 9);
            }
        //-------- Elephant Card ----------------------

    }

    public static void confirmSpecialPhase(){
        if(selected_special != 99){
            useable_special[selected_special] = 1;
        }
        selected_special = 99;

        afterConfirm();
    }

    public static void confirmMonkeyPhase(){
        selected_plus_or_minus = 99;

        if (selectedMonkeyCards.size() == 1) {
            Card thisMonkeyCard = selectedMonkeyCards.get(0);
            int colorIndex = thisMonkeyCard.getColorIndex();
            monkeys[colorIndex].move(thisMonkeyCard.getNumber());
        }

        // Make the selected monkey cards disappear
        for(int i = 0; i < selectedMonkeyCards.size(); i++){
            selectedMonkeyCards.get(i).empty();
        }

        afterConfirm();
    }

    public static void confirmElephantPhase(){
        if(useable_special[3] == 1){
            int valueLeft = 0;
            int valueRight = 0;
            if(Elephant_cards[0].turn % 2 == 0){
                valueLeft = Elephant_cards[0].value1;
            }else{
                valueLeft = Elephant_cards[0].value2;
            }

            if(Elephant_cards[1].turn % 2 == 0){
                valueRight = Elephant_cards[1].value1;
            }else{
                valueRight = Elephant_cards[1].value2;
            }

            Elephant_cards[0].turn += 1;
            Elephant_cards[1].turn += 1;

            Elephant.move(Elephant.location + valueLeft + valueRight);
            useable_special[3] = 2;
        } else {

            if (Elephant_Card.selected != 99) {
                if (Elephant_cards[Elephant_Card.selected].turn % 2 == 0) {
                    Elephant.move(Elephant.location + Elephant_cards[Elephant_Card.selected].value1);
                    Elephant_cards[Elephant_Card.selected].turn = Elephant_cards[Elephant_Card.selected].turn + 1;
                } else {
                    Elephant.move(Elephant.location + Elephant_cards[Elephant_Card.selected].value2);
                    Elephant_cards[Elephant_Card.selected].turn = Elephant_cards[Elephant_Card.selected].turn + 1;
                }

                Elephant_Card.selected = 99;
            }
        }

        // Pick new monkey cards from the deck
        for(int i = 0; i < selectedMonkeyCards.size(); i++){
            selectedMonkeyCards.get(i).pickFromDeck();
        }
        selectedMonkeyCards = new ArrayList<>();


        currentPlayer.endTurn();
        currentPlayerIndex += 1;
        currentPlayerIndex = currentPlayerIndex % 4;
        currentPlayer = players[currentPlayerIndex];
        currentPlayer.startTurn();
        phase = -1;

        afterConfirm();
    }

    public static void afterConfirm(){
        if(useable_special[1] == 1){
            useable_special[1] = 2;
            selected_special = 99;
            currentPlayer.endTurn();
            currentPlayerIndex += 1;
            currentPlayerIndex = currentPlayerIndex % 4;
            currentPlayer = players[currentPlayerIndex];
            currentPlayer.startTurn();
            phase = 0;
            return;
        }else{
            if(phase != PHASE_MONKEY){
                phase = (phase + 1) % 3;
            }else{
                if(0 < selectedMonkeyCards.size() && selectedMonkeyCards.size() <= 2){
                    phase = (phase + 1) % 3;
                }
            }

        }
    }

    public static void checkWeight(){
        elephant.scaleWeight();
        double x_weight = elephant.x_weight;
        double y_weight = elephant.y_weight;

        for (int i=0; i<4; i++){
            monkeys[i].scaleWeight();
            x_weight += monkeys[i].x_weight;
            y_weight += monkeys[i].y_weight;
        }

//        System.out.println("The current adjusted weight is:");
//        System.out.println("X: " + x_weight);
//        System.out.println("Y: " + y_weight);
    }

    public static void dispose(){
        cordMap.dispose();
        pink.dispose();
        blue.dispose();
        green.dispose();
        orange.dispose();
        Elephant.dispose();
        holdOn.dispose();
        breakTime.dispose();
        monkeySwap.dispose();
        turboElephant.dispose();
        freeMove.dispose();
        exchange.dispose();
        Plus_Button.dispose();
        Minus_Button.dispose();
        Confirm_button.dispose();
        elephant1.dispose();
        elephant2.dispose();
    }

}
