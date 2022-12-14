package State;

import Main.GamePanel;
import Map.Background;
import Map.Map;
import Entity.*;
import Entity.Playable.Playable;
import Entity.Playable.Jatayu;
import Entity.HUD;
import Entity.Enemy.Enemy;
import Entity.Enemy.Ghost_Horizontal;
import Entity.Enemy.Ghost_Vertical;
import Utility.*;
import Utility.Time;
import Entity.Collectibles.Collectibles;
import Entity.Collectibles.Flower;
import Audio.AudioPlayer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

public class JatayuState extends State {
    private Background bg;
    private  Map map = new Map(50);
    private Playable main_character;
    private List <Enemy> enemy;
    private List <Collectibles> flowers;

    private Time t;
    private HUD hud;
    private AudioPlayer music;

    public JatayuState(StateManager stateManager){
        this.stateManager  = stateManager;
        music = new AudioPlayer("/SFX/music_labirin.wav");
        enemy = new ArrayList<Enemy>();
        this.flowers = new ArrayList<Collectibles>();
        this.flowers = new ArrayList<Collectibles>();

        try{
            bg = new Background("/Backgrounds/bg_LABIRINJATAYU.png");
        }catch (Exception e){
            e.printStackTrace();
        }
        init();
    }

    @Override
    public void init() {
        this.map.loadMap("/Map/labirinjatayu.map");
        this.map.loadTiles("/Tiles/tile_labirinjatayu.png");
        this.map.setPosition(98, 30);
        main_character = new Jatayu(map);
        t = new Time();
        hud = new HUD(main_character, t);

        enemy.add(new Ghost_Horizontal(450, 75, 200, main_character, 2));
        enemy.add(new Ghost_Vertical(450, 525, 150, main_character, 5));
        enemy.add(new Ghost_Vertical(900, 75, 150, main_character, 5));
        enemy.add(new Ghost_Vertical(800, 315, 100, main_character, 2));
        enemy.add(new Ghost_Vertical(1100, 450, 100, main_character, 5));
        enemy.add(new Ghost_Horizontal(150, 75, 150, main_character, 3));


        flowers.add(new Flower(main_character, 550, 690));
        flowers.add(new Flower(main_character, 1000, 130));
        flowers.add(new Flower(main_character, 650, 330));
        flowers.add(new Flower(main_character, 350, 80));
        flowers.add(new Flower(main_character, 900, 380));
        flowers.add(new Flower(main_character, 1000, 680));

        t.start();
        music.play();
    }

    @Override
    public void update() {
        main_character.update();
        
        for(Enemy rahwana : enemy){
            rahwana.update();
        }

        for (Collectibles flower : flowers) {
            flower.update();
        }

        if (main_character.isDead()) {
            music.stop();
            SaveData.writeLatestLevel(2);
            stateManager.setState(StateManager.DEATHSTATE);
        }
        if (main_character.getX() >= 1300) {
            music.stop();
            SaveData.writeHighScore(2, String.valueOf(t.getSecond() - main_character.getScore()) + "." + String.valueOf(t.getMilisecond()));
            stateManager.setState(StateManager.STORYLINE3);
        }
        
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        map.draw(g);
        main_character.draw(g); 
        
        for(Enemy rahwana: enemy){
            rahwana.draw(g);
        }
        for (Collectibles flower : flowers) {
            flower.draw(g);
        }

        hud.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_RIGHT) {
            main_character.set_x_speed(5);
            main_character.set_y_speed(0);
            main_character.set_direction(k);
        } else if (k == KeyEvent.VK_LEFT) {
            main_character.set_x_speed(-5);
            main_character.set_y_speed(0);
            main_character.set_direction(k);
        } else if (k == KeyEvent.VK_UP) {
            main_character.set_y_speed(-5);
            main_character.set_x_speed(0);
            main_character.set_direction(k);
        } else if (k == KeyEvent.VK_DOWN) {
            main_character.set_y_speed(5);
            main_character.set_x_speed(0);
            main_character.set_direction(k);
        }else if (k == KeyEvent.VK_ESCAPE){
            music.stop();
            SaveData.writeLatestLevel(2);
            stateManager.setState(StateManager.MENUSTATE);
        }
        
    }

    @Override
    public void keyReleased(int k) {
        if(k == KeyEvent.VK_UP ||
        k == KeyEvent.VK_DOWN ||
        k == KeyEvent.VK_LEFT ||
        k == KeyEvent.VK_RIGHT){
            main_character.set_x_speed(0);
            main_character.set_y_speed(0);
        }
        
    }
}
