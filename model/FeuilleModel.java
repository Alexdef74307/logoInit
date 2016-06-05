package model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by thomas on 05/06/16.
 */
public class FeuilleModel {

    public ArrayList<TortueModel> tortueModels;

    public FeuilleModel() {
        tortueModels = new ArrayList<TortueModel>();
    }

    public void addTortueModel(TortueModel o)
    {
        tortueModels.add(o);
    }

    public void reset() {
        for (Iterator it = tortueModels.iterator(); it.hasNext();) {
            TortueModel t = (TortueModel) it.next();
            t.reset();
        }
    }



}
