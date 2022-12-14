package code.grid;


import java.util.HashSet;


public class GridGenerator {

    public static String genGrid(){
        StringBuilder grid=new StringBuilder();

          /* this hashset in responsible for check if a position is occupied or not
           it will have its elements on the form i+" "+j */
        HashSet<String> positionsInGrid=new HashSet<>();

        // initialize a random size for the grid between 5 and 15
        int m= genRandomNum(5,15);
        int n= genRandomNum(5,15);
        grid.append(m+","+n);

        // initialize the max capacity of passengers  the coast guard boat can carry at time
        int c= genRandomNum(30,100);
        grid.append(";"+c);

        // initialize the coat guard boat position
        grid.append(genCoastGuardBoatPos(m,n,positionsInGrid));

        // initialize the stations positions
        grid.append(genStationsPositions(m,n,positionsInGrid));

        // initialize the ships positions
        grid.append(genShipsPositions(m,n,positionsInGrid));

        return grid.toString();
    }

    // generate a random number through a given range
    private static int genRandomNum(int min, int max){
        return min + (int)(Math.random() * ((max - min) + 1));
    }

    // generate a random initial position for the coast guard boat
    private static String genCoastGuardBoatPos(int m,int n,HashSet<String> positionsInGrid){
        int x=genRandomNum(0,m-1);
        int y=genRandomNum(0,n-1);
        positionsInGrid.add(x+" "+y);
        return ";"+x+","+y+";";
    }

    // generate  random stations positions
    private static StringBuilder genStationsPositions(int m,int n,HashSet<String> positionsInGrid){
        int stationsNum=genRandomNum(1,m);
        StringBuilder positions=new StringBuilder();
        while(stationsNum-->0){
            while(true){
                int x=genRandomNum(0,m-1);
                int y=genRandomNum(0,n-1);
                if(!positionsInGrid.contains(x+" "+y)){
                    positions.append(x+","+y+",");
                    positionsInGrid.add(x+" "+y);
                    break;
                }
            }
        }
        positions.deleteCharAt(positions.length()-1);
        positions.append(";");
        return positions;
    }

    private static StringBuilder genShipsPositions(int m,int n,HashSet<String> positionsInGrid){
        int shipsNum=genRandomNum(1,m);
        StringBuilder positions=new StringBuilder();
        while(shipsNum-->0){
            while(true){
                int x=genRandomNum(0,m-1);
                int y=genRandomNum(0,n-1);
                if(!positionsInGrid.contains(x+" "+y)){
                    int cap=genRandomNum(1,100);
                    positions.append(x+","+y+","+cap+",");
                    positionsInGrid.add(x+" "+y);
                    break;
                }
            }
        }
        positions.deleteCharAt(positions.length()-1);
        positions.append(";");
        return positions;
    }

    public static Object[] buildGrid (String stringGrid) {

        // 0 is the boat, 1 is the grid
        Object [] objects=new Object[2];

        String [] gridPartitions = stringGrid.split(";") ;

        String gridSize[] = gridPartitions[0].split(",");
        Grid grid=new Grid(Integer.parseInt(gridSize[1]),Integer.parseInt(gridSize[0]));

        int capacity = Integer.parseInt(gridPartitions[1]) ;
        String boatPosition [] = gridPartitions[2].split(",") ;
        RescueBoat boat = new RescueBoat(Integer.parseInt(boatPosition[0]), Integer.parseInt(boatPosition[1]), capacity) ;
        objects[0]=boat;

        String stations [] = gridPartitions[3].split(",") ;
        for (int i=0; i<stations.length; i+=2) {
            Station station = new Station(Integer.parseInt(stations[i]), Integer.parseInt(stations[i+1])) ;
            grid.addStation(station);
        }

        String  [] ships= gridPartitions[4].split(",") ;
        for (int i=0 ; i<ships.length; i+=3) {
            Ship ship = new Ship(Integer.parseInt(ships[i]), Integer.parseInt(ships[i+1]), Integer.parseInt(ships[i+2]));
            grid.addShip(ship);
        }
        objects[1]=grid;
        return objects;

    }

}
