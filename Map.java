import java.util.Random;


/**
 * Reads and contains in memory the map of the game.
 *
 */
public class Map {

	/** Representation of the map */
	public char[][] map;
	
	/**Contains section of Map as an Array which the player can see and will be printed*/
	public char[][] mapToBePrinted =  new char[5][5];
	
	/** Map name */
	private String mapName;
	
	/** Gold required for the human player to win */
	private int goldRequired;
	
	/**Size of Map along y axis*/
	public int numberOfRows;
	
	/**Size of Map along x axis*/
	public int numberOfCols;
	
	
	
	/**
	 * Default constructor, creates the default map "Very small Labyrinth of doom".
	 */
	public Map() {
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 2;
		map = new char[][]{
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
		{'#','.','.','.','.','.','.','.','.','G','.','.','.','.','.','.','#','#','.','#'},
		{'#','#','.','.','.','.','.','G','.','.','.','.','.','.','.','.','.','E','.','#'},
		{'#','.','.','.','.','.','#','.','.','.','.','.','.','.','.','.','.','#','.','#'},
		{'#','.','.','E','#','.','.','.','#','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','#','.','.','.','G','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','#','.','.','#','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','G','.','.','.','.','#','.','.','.','.','.','G','.','.','#','.','#'},
		{'#','.','.','.','.','.','.','#','#','#','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
		};
		numberOfRows = map.length;
		numberOfCols = map[0].length;
	}
	

    /**
     * <p> Returns the gold required to win </p>
     * @return : Gold required to exit the current map.
     */
    public int getGoldRequired() {
        return goldRequired;
    }

    /**
     * @return : The map as stored in memory.
     */
    public char[][] getMap() {
        return map;
    }


    /**
     * @return : The name of the current map.
     */
    public String getMapName() {
        return mapName;
    }
    
    
    /**<p>Prints 5x5 map section visible to the player</p>
     * @param map Map Object
     * 
     */
    public void printMap(Map map) {
    	
    	for(int i = 0;i < 5;i++) {
    		
    		for(int k = 0;k < 5;k++) {
    		char element = map.mapToBePrinted[i][k];
    		
    		if(k != 4) {
    			System.out.print(element);
    			}
    		
    		else {
				System.out.print(element);
				System.out.print("\n");
    		}
    		}
    	}
    }
    
    
    /**<p>Creates the 5x5 map visible to the player and assigns it to the maptToBePrinted array</p>
     * @param map Map Object
     * @param player HumanPlayer Object
     **/
    public void map5x5(Map map, HumanPlayer player) {
    	
    	for(int i = 0;i<5;i++) {
    		char[] storedMapRowArray = new char[5];
    		for(int k = 0;k<5;k++) {
    			
    			if(player.xStartIndex+k<0 || player.xStartIndex+k > numberOfCols-1 || player.yStartIndex+i<0 || player.yStartIndex+i > numberOfRows-1) {
    				
    				storedMapRowArray[k] = '#';
    			}
    			else {
    				
    				storedMapRowArray[k] = map.map[player.yStartIndex+i][player.xStartIndex+k];
    			}
    		}
    		mapToBePrinted[i] = storedMapRowArray;
    	}	
    }
    
    /**<p>Initializes a random starting position for the player in the map</p>
     @param map Map Object
     @param player HumanPlayer Object*/
    public void playerStartingPosition(Map map, HumanPlayer player) {
    	Random rand = new Random(); 
    	
    	int randomXPos = rand.nextInt(numberOfCols-1);
    	int randomYPos = rand.nextInt(numberOfRows-1);
    	
    	boolean isNotValidPos = true;
    	
    	while(isNotValidPos) {
    		if(map.map[randomYPos][randomXPos] == '.' || map.map[randomYPos][randomXPos] == 'E') {
    			player.playerXPos = randomXPos;
    			player.playerYPos = randomYPos;
    			
    			if(map.map[randomYPos][randomXPos] == '.') {
    			player.elementUnderPlayer = '.';
    			}
    			
    			else {
    				map.map[randomYPos][randomXPos] = 'E';
    			}
    			
    			map.map[player.playerYPos][player.playerXPos] = 'P';
    			isNotValidPos = false;
    			
    		}
    		else {
    			randomXPos = rand.nextInt(numberOfCols-1);
    	    	randomYPos = rand.nextInt(numberOfRows-1);
    		}
    	}
    }
    
    /**<p>Changes player's X and Y position</p>
     @param map Map Object
     @param player HumanPlayer Object*/
    public void changePlayerPosition(Map map, HumanPlayer player) {
    	map.map[player.playerYPos][player.playerXPos] = 'P';
    }
}

	