package code.Algorithms;

import code.state.BFSState;
import code.state.Node;
import code.state.State;
import code.grid.GridObjectType;
import code.grid.RescueBoat;
import code.grid.Ship;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFS extends SearchAlgorithm {

    @Override
    public Node search(List<Ship> ships, RescueBoat boat, GridObjectType [][] grid, boolean visualize) {

        this.previousStates = new HashSet<>();
        Queue<Node> queue = new LinkedList<>() ;
        State initialState = new BFSState(ships, boat);
        Node rootNode = new Node(initialState, null, null, grid) ;

        previousStates.add(rootNode.toString());
        queue.add(rootNode) ;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll() ;

            if (visualize) {

                System.out.println("*******************************************************************");
                System.out.println("Leading action: "+ currentNode.getLeadingAction());

                System.out.println(currentNode.toString());
                System.out.println("*******************************************************************");
                System.out.println();
            }

            if (currentNode.getState().checkGoalTest())
                return currentNode;


            for (Ship ship: currentNode.getState().getRemainingShips()){

                if (!ship.isWrecked())
                    currentNode.getState().setDeaths(currentNode.getState().getDeaths()+1);
                ship.update();
            }

            Node up = currentNode.up() ;
            Node down = currentNode.down() ;
            Node left = currentNode.left() ;
            Node right = currentNode.right();
            Node pickup = currentNode.pickup() ;
            Node drop = currentNode.drop() ;

            if(up != null && !previousStates.contains(up.toString())) {
                queue.add(up);
                this.previousStates.add(up.toString());
            }
            if(down != null && !previousStates.contains(down.toString())) {
                queue.add(down);
                this.previousStates.add(down.toString()) ;
            }
            if(left != null && !previousStates.contains(left.toString())) {
                queue.add(left);
                this.previousStates.add(left.toString());
            }
            if(right != null && !previousStates.contains(right.toString())) {
                queue.add(right);
                this.previousStates.add(right.toString()) ;
            }
            if(pickup != null && !previousStates.contains(pickup.toString())) {
                queue.add(pickup);
                this.previousStates.add(pickup.toString()) ;
            }
            if (drop != null && !previousStates.contains(drop.toString())) {
                queue.add(drop);
                this.previousStates.add(drop.toString()) ;
            }


        }
        return null;
    }


}
