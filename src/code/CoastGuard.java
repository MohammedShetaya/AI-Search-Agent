package code;

import code.Algorithms.Search;
import code.node.Node;
import code.grid.*;
import code.state.State;

import java.util.ArrayList;


public class CoastGuard extends GeneralSearch{


    public static String genGrid() {
        return GridGenerator.genGrid();
    }


    public static String solve (String grid, String strategy, boolean visualize) {

        CoastGuard coastGuard=new CoastGuard();
        coastGuard.genInitialState(grid);
        coastGuard.genActionList();

        Search search=new Search(strategy,(State) coastGuard.initialState,coastGuard.actionList,visualize);

        return search.getSolution();
    }
    public void genActionList(){
        actionList=new ArrayList<>();
        actionList.add("up");
        actionList.add("down");
        actionList.add("right");
        actionList.add("left");
        actionList.add("pickup");
        actionList.add("drop");
        actionList.add("retrieve");
    }
    public void genInitialState(String grid){
        Object [] objects=GridGenerator.buildGrid(grid);
        RescueBoat boat=(RescueBoat) objects[0];
        Grid gridObject=(Grid) objects[1];
        initialState=new State(boat,gridObject);
    }
    public static boolean checkGoalTest(Object o){
        Node node=(Node) o;
        State state=node.getState();
        return state.getGrid().isEmpty() && state.getBoat().isEmpty();
    }
    public static int pathCost(Object o){
        Node node=(Node) o;
        State state=node.getState();
        int maxDistance = (state.getGrid().getN()+state.getGrid().getM());
        return maxDistance*(2*state.getDeaths() + state.getDamagedBoxes()) ;
    }

    public static void main(String[] args) {
        String grid0 = "5,6;50;0,1;0,4,3,3;1,1,20;";
        String grid1 = "6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;";
        String grid2 = "7,5;40;2,3;3,6;1,1,10,4,5,90;";
        String grid3 = "8,5;60;4,6;2,7;3,4,37,3,5,93,4,0,40;";
        String grid4 = "5,7;63;4,2;6,2,6,3;0,0,17,0,2,73,3,0,30;";
        String grid5 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
        String grid6 = "7,5;86;0,0;1,3,1,5,4,2;1,1,42,2,5,99,3,5,89;";
        String grid7= "6,7;82;1,4;2,3;1,1,58,3,0,58,4,2,72;";
        String grid8 = "6,6;74;1,1;0,3,1,0,2,0,2,4,4,0,4,2,5,0;0,0,78,3,3,5,4,3,40;";
        String grid9 = "7,5;100;3,4;2,6,3,5;0,0,4,0,1,8,1,4,77,1,5,1,3,2,94,4,3,46;";
        String grid10= "10,6;59;1,7;0,0,2,2,3,0,5,3;1,3,69,3,4,80,4,7,94,4,9,14,5,2,39;";

        ArrayList<String> list=new ArrayList<>();
        list.add(grid0);
        list.add(grid1);
        list.add(grid2);
        list.add(grid3);
        list.add(grid4);
        list.add(grid5);
        list.add(grid6);
        list.add(grid7);
        list.add(grid8);
        list.add(grid9);
        list.add(grid10);
        for(String g:list) System.out.println(solve(g,"ID",false));
//        String grid="9,14;99;6,12;7,6,8,8,0,13,3,1,4,9,4,7;7,13,93,7,3,34,1,12,58,2,5,59,0,7,38,3,9,90;";
//        System.out.println(grid);
//        System.out.println(solve(grid,"AS1",false));


    }
}
