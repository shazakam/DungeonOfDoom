import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 */
public class HumanPlayer {
	
	Scanner sc = new Scanner(System.in);
	
	/**Gold owned by Player*/
	public int goldOwnedByPlayer = 0;
	
	/**Player X position*/
	public int playerXPos;
	
	/**Player Y position*/
	public int playerYPos;
	
	/**Center of 5x5 area which the player can see*/
	int xStartIndex = playerXPos-2;
	int yStartIndex = playerYPos-2;
	
	/**Array with all valid game Protocol Commands*/
	public String[] possibleActions = {"HELLO","GOLD","MOVE","PICKUP","LOOK","QUIT"};
	
	/**Stored Player Action*/
	public ArrayList<String> storedPlayerActionList = new ArrayList<>();
	
	/**Possible Directions*/
	String[] possibleDirections = {"N","S","W","E"};
	
	/**Element which the Player is currently on*/
	public char elementUnderPlayer;
	
    /**
     * <p>Reads player's input from the console.</p>
     * @return : A string containing the input the player entered.
     */
    public String getInputFromConsole() {
    	
    	String userInput = sc.nextLine();
    	
        return userInput;
    }

    /**
     * <p>Checks the inputed action and returns a String response according to the protocol.</p>
     * @param userInput User's Input
     * @param map Map object
     * @param gameLogic GameLogic Object
     * @param player HumanPlayer Object
     * @return String protocol response.
     */
    public String getNextAction(String userInput, Map map, GameLogic gameLogic, HumanPlayer player) {
    	
    	boolean wasItValid = isAValidAction(userInput);
    	String[] storedPlayerActionArray = new String[storedPlayerActionList.size()];
    	storedPlayerActionArray = storedPlayerActionList.toArray(storedPlayerActionArray);
    	storedPlayerActionList.clear();
    	
    	if(wasItValid == false) {
    		return "Invalid";
    	}
    	
    	else {
    		
    		if(storedPlayerActionArray[0].equalsIgnoreCase("HELLO")) {
    			String goldRequired = Integer.toString(map.getGoldRequired());
    			return "Gold to win: "+ goldRequired;
    		}
    		
    		else if(storedPlayerActionArray[0].equalsIgnoreCase("GOLD")) {
    			String goldOwned = gameLogic.getGold(goldOwnedByPlayer); 
    			return "Gold owned: " + goldOwned;
    		}
    		
    		else if(storedPlayerActionArray[0].equalsIgnoreCase("MOVE")) { 
    			return GameLogic.move(player, storedPlayerActionArray, map);
    			
    		}
    		
    		else if(storedPlayerActionArray[0].equalsIgnoreCase("PICKUP")) {
    			return GameLogic.pickup(player);
    		}
    		
    		else if(storedPlayerActionArray[0].equalsIgnoreCase("QUIT")){
				return GameLogic.quitGame(map,player);
			}
    		
    		else if(storedPlayerActionArray[0].equalsIgnoreCase("LOOK")) {
    			gameLogic.look(player);
    			return "Success";
    		}
    		return null;
    	} 
    }
    
    /**
     *<p>Checks and looks for a valid input</p>
     *
     *@param userInput User's Input
     *@return boolean value
     */
    public boolean isAValidAction(String userInput) {
    	String[] splitUserInput = userInput.split("\\s+");
    	
    	int listLength = splitUserInput.length;
    	
    	if(listLength < 2) {
    		
    		for(int i = 0; i < possibleActions.length;i++) {
    			
    			if(splitUserInput[0].equalsIgnoreCase(possibleActions[i]) && splitUserInput[0].equalsIgnoreCase("MOVE") == false) {
    				storedPlayerActionList.add(userInput);
    				return true;
    			}
    			
    		}
    		return false;
    	}
    	
    	else {
			 for(int k = 0; k < listLength;k++){
				 
				 for(int i = 0; i < possibleActions.length;i++){
    				
    				if(splitUserInput[k].equalsIgnoreCase(possibleActions[i])) {
    					storedPlayerActionList.add(splitUserInput[k]);
    					
    					if(splitUserInput[k].equalsIgnoreCase("MOVE")) {
    						
    						for(int j = k+1; j < listLength;j++) {
    							
    							for(int a = 0; a < possibleDirections.length;a++) {
    								
    								if(splitUserInput[j].equalsIgnoreCase(possibleDirections[a])) {
    									storedPlayerActionList.add(splitUserInput[j]);
    									return true;
    								}
    							}
    						}
    						return false;
    					}
        				return true;
        			}
    			}
    		}
    	}
    	return true;
    }
}
