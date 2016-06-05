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

    public ArrayList<TortueModel> estDansChampVision(TortueModel tm, int dist, int angleVision) {
        double [][] champVision = tm.champDeVision(dist, angleVision);
        ArrayList<TortueModel> visibles = new ArrayList<>();
        for (TortueModel temp : tortueModels) {
            if (temp != tm) {

                if (champVision[0][0] >= 0) {
                    if (temp.x <= (champVision[0][0]*temp.x + champVision[0][1]) && (temp.x >= champVision[1][0]*temp.x + champVision[1][1])) {
                        visibles.add(temp);
                    }
                }
                else if (champVision[0][0] < 0) {
                    if (temp.x <= (champVision[1][0]*temp.x + champVision[1][1]) && (temp.x >= champVision[0][0]*temp.x + champVision[0][1])) {
                        visibles.add(temp);
                    }
                }
            }
        }
        return visibles;
    }

}
