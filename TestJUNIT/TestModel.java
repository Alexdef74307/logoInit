package TestJUNIT;

/**
 * Created by thomas on 06/06/16.
 */

import static org.junit.Assert.*;

import model.TortueModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestModel {

    private TortueModel tortueTest;

    @Before
    public void setUp(){

        tortueTest = new TortueModel();
        tortueTest.setPosition(200,400);
    }

    @Test
    public void testAvanceFlocking(){
        TortueModel t1 = new TortueModel();
        t1.setPosition(100,200);
        t1.setDir(-60);
        t1.setVitesse(25);
        TortueModel t2 = new TortueModel();
        t2.setPosition(100,150);
        t2.setDir(60);
        t2.setVitesse(75);
        ArrayList<TortueModel> t = new ArrayList<>();
        t.add(t1);
        t.add(t2);
        tortueTest.avancer(t,400,600);
        assertEquals(tortueTest.getX(),250);
        assertEquals(tortueTest.getY(),400);
    }

    @Test
    public void testAvancerToro(){
        tortueTest.setDir(0);
        tortueTest.avancer(300,400,600);
        assertEquals(tortueTest.getX(),100);
        assertEquals(tortueTest.getY(),400);
    }


}
