package State;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.KeyEvent;

import Map.Background;
import Audio.AudioPlayer;

public class RahwanaState extends State{
    private AudioPlayer music;
    private Background slide1, slide2, display;

    public RahwanaState (StateManager stateManager){
        this.stateManager = stateManager;
        music = new AudioPlayer("/SFX/music_rahwanastate.wav");

        try{
            slide1 = new Background("/Backgrounds/Storyline_BG/4. CERITA_EMPAT.png");
            slide2 = new Background("/Backgrounds/Storyline_BG/5. CERITA_LIMA.png");
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        display = slide1;
        init();
    }

    @Override
    public void init() {
        music.play();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Graphics2D g) {
        display.draw(g);
        
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER){
            if (display == slide2){
                stateManager.setState(StateManager.MENUSTATE);
            }else {
                display = slide2;
            }
        }
        
    }

    @Override
    public void keyReleased(int k) {
        // TODO Auto-generated method stub
        
    }
}
