package State;

import Main.GamePanel;
import Utility.*;
import Map.Background;
import Entity.*;
import Entity.Playable.Playable;
import Entity.Playable.Rama;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Objects;

public class TangkapkijangState extends State {
    private Background bg;
    private Playable main_character = new Rama();

    public TangkapkijangState(StateManager stateManager) {
        this.stateManager = stateManager;

        try {
            bg = new Background("/Backgrounds/bg_about_state.png");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {}

    @Override
    public void update() {
        main_character.update();
    }

    @Override
    public void draw(Graphics2D g) {
        bg.draw(g);
        main_character.draw(g);
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
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_UP ||
        k == KeyEvent.VK_DOWN ||
        k == KeyEvent.VK_LEFT ||
        k == KeyEvent.VK_RIGHT) {
            main_character.set_x_speed(0);
            main_character.set_y_speed(0);
        }
    }

}
