package Model;

import java.awt.*;
import java.io.Serializable;

public class WordleCharAndColor implements Serializable {

    private final Color backColor;
    private final Color frontColor;

    private final char c;


    public WordleCharAndColor(char c, Color back, Color front) {
        this.c = c;
        this.backColor = back;
        this.frontColor = front;
    }

    public char getC() {
        return this.c;
    }


    public Color getBackColor() {
        return this.backColor;
    }

    public Color getFrontColor() {
        return this.frontColor;
    }

}
