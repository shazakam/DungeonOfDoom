
/**
 * Contains the main logic part of the game, as it processes.
 *
 */

public class GameLogic {
	
	static boolean isGameRunning = true;
	
	/**
	 * Constructor for GameLogic
	 */
	public GameLogic() {
	}
	
	/**
	 * @param goldOwnedByPlayer Gold currently owned by player
	 * @return the gold currently owned by the player in the form of a string.
     */
    public String getGold(int goldOwnedByPlayer) {
    	String goldOwned = Integer.toString(goldOwnedByPlayer);
        return goldOwned;
    }

    /**
     * <p>Takes the second element in the storedPlayer Action Array and compares it with the directions
     * and proceeds with the protocol and changes player position based on input</p>
     *@param player HumanPlayer Object
     *@param storedPlayerActionArray Array containing user inputs
     *@param map Map Object
     *@return Protocol if success or not.
     */
    public static String move(HumanPlayer player,String[] storedPlayerActionArray,Map map) {
    	int playerXPos = player.playerXPos;
    	int playerYPos = player.playerYPos;
    	char elementUnderPlayer = player.elementUnderPlayer;
    	if(storedPlayerActionArray[1].equalsIgnoreCase("N") && Character.compare(map.map[playerYPos-1][playerXPos],'#') != 0) {
			
			map.map[playerYPos][playerXPos] = elementUnderPlayer ; 
			player.elementUnderPlayer = map.map[playerYPos-1][playerXPos];
			player.playerYPos -= 1;
			
			return "Success";
		}
		
		else if(storedPlayerActionArray[1].equalsIgnoreCase("S") && Character.compare(map.map[playerYPos+1][playerXPos],'#') != 0) {
			
			map.map[playerYPos][playerXPos] = elementUnderPlayer ; 
			player.elementUnderPlayer = map.map[playerYPos+1][playerXPos];
			player.playerYPos += 1;
			return "Success";
		}
		
		else if(storedPlayerActionArray[1].equalsIgnoreCase("E") && Character.compare(map.map[playerYPos][playerXPos+1],'#') != 0) {
			
			map.map[playerYPos][playerXPos] = elementUnderPlayer ; 
			player.elementUnderPlayer = map.map[playerYPos][playerXPos+1];
			player.playerXPos += 1;
			return "Success";
		}
		
		else if(storedPlayerActionArray[1].equalsIgnoreCase("W") && Character.compare(map.map[playerYPos][playerXPos-1],'#') != 0) {
			
			map.map[playerYPos][playerXPos] = elementUnderPlayer ; 
			player.elementUnderPlayer = map.map[playerYPos][playerXPos-1];
			player.playerXPos -= 1;
			return "Success";
		}
		return "Failure";
    }

    /**
     * <p>Changes center of 5x5 to where the player is situated</p>
     * @param player HumanPlayer Object
     */
    public void look(HumanPlayer player) {
    	player.xStartIndex = player.playerXPos-2;
		player.yStartIndex = player.playerYPos-2;
        
    }

    /**
     * <p>Checks if element player is on is a coin or not and responds according to the protocol </p>
     * @param player HumanPlayer Object
     * @return If the player successfully picked-up gold or not.
     */
    public static String pickup(HumanPlayer player) {
    	if(Character.compare(player.elementUnderPlayer,'G') == 0) {
			player.elementUnderPlayer = '.';
			player.goldOwnedByPlayer += 1;
		
		return "Success. Gold Owned: "+player.goldOwnedByPlayer;
		}
		else {
			return "Fail. Gold Owned: "+player.goldOwnedByPlayer;
		}
    }

    /**
     * <p> Checks if player is on Exit tile and checks win condition </p>
     * @param map Map Object
     * @param player HumanPlayer Object
     * @return Either Winning statement or Losing statement.
     */
    public static String quitGame(Map map, HumanPlayer player) {
    	
    	if(Character.compare(player.elementUnderPlayer,'E')==0) {
    		
    		if(map.getGoldRequired() <= player.goldOwnedByPlayer) {
    			isGameRunning = false;
    			return "WIN. Great job, I hope you are happy.";
    		}
    	}
    	
    	isGameRunning = false;
    	return"LOSE";
    }
	
	public static void main(String[] args) {
		
		GameLogic logic = new GameLogic();
		Map map = new Map();
		HumanPlayer player = new HumanPlayer();
		
		map.playerStartingPosition(map,player);
		logic.look(player);
		map.map5x5(map, player);
		
		
		map.printMap(map);
		
		
		while(isGameRunning) {
			
			String userInput = player.getInputFromConsole();
			String nextAction = player.getNextAction(userInput,map,logic,player);
			System.out.println(nextAction);
			map.changePlayerPosition(map, player);
			
			if(isGameRunning == true) {
				map.map5x5(map, player);
				map.printMap(map);
			}
		}
	}
	
   }
