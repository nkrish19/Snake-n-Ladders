import java.util.*;
import java.lang.*;



class MovementException extends Exception {
    
    public MovementException(String message) {
        super(message);
    }
}


class SnakeBiteException extends Exception {

    public SnakeBiteException(String message) {
        super(message);
    }
}


class CricketBiteException extends Exception {

    public CricketBiteException(String m) {
        super(m);
    }
}



class VultureBiteException extends Exception {

    public VultureBiteException(String m) {
        super(m);
    }
}


class TrampolineException extends Exception {

    public TrampolineException(String m) {
        super(m);
    }
}



class GameWinnerException extends Exception {
    
    public GameWinnerException(String message) {
        super(message);
    }
}



class TrackLengthException extends Exception {

    public TrackLengthException(String message) {
        super(message);
    }
}



class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}



class Tile{

    private final int JumpVal;
    final String ExceptionMsg; 

    public Tile(int j, String m) {
        this.JumpVal = j;
        this.ExceptionMsg = m;
    }

    public int GetJumpVal() {
        return this.JumpVal;
    }

    public void MvExcep() throws MovementException {
        System.out.println(this.ExceptionMsg);
        throw new MovementException(this.ExceptionMsg);
    }
}



class Snake extends Tile {

    public Snake(int j, String m) {
        super(j, m);
    }
}



class Cricket extends Tile {

    public Cricket(int j, String m) {
        super(j, m);
    }
}



class Vulture extends Tile {

    public Vulture(int j, String m) {
        super(j, m);
    }
}



class Trampoline extends Tile {

    public Trampoline(int j, String m) {
        super(j, m);
    }
}



class White extends Tile {

    public White(int j, String m) {
        super(j, m);
    }
}



class Application {

    private final int NumTiles;
    private final String UserName;
    private final int TotalNumSnake;
    private final int TotalNumCricket;
    private final int TotalNumVulture;
    private final int TotalNumTrampoline;
    private int SnakeBites;
    private int CricketBites;
    private int VultureBites;
    private int TrampolineJumps;
    private int TotalMoves;
    private final ArrayList<Tile> RaceTrack;
    Scanner sc;

    /*
    Input NumTiles
    Set NumTiles
    Create the race track (Arraylist)
        Fill it with objects
        Set respective bites to 0
    */
    public Application() throws TrackLengthException, InvalidInputException, SnakeBiteException, CricketBiteException, VultureBiteException, TrampolineException{
        
        this.sc = new Scanner(System.in);
        Random random = new Random();
        this.SnakeBites = 0;
        this.CricketBites = 0;
        this.VultureBites = 0;
        this.TrampolineJumps = 0;
        this.TotalMoves = 0;
        this.RaceTrack = new ArrayList<Tile>();


        // Input NumTiles and Exception Handling
        System.out.println("Enter total number of tiles on the track (length).\nShould be minimum 10 tiles");
        boolean trklencorrect = false;
        int trklen = 0;

        do {
            try {
                trklen = this.sc.nextInt(); 
                if (trklen < 10) {
                    throw new TrackLengthException("Negative integers are invalid");
                }
                else {
                    trklencorrect = true;
                }
            } catch (Exception e) {
                System.out.println("Enter valid integer");
                this.sc.nextLine();
            }
        } while (!(trklencorrect));
        this.NumTiles = trklen;


        /*
        Total non empty tiles are 60% of track length
            0 -> non empty
            1 -> empty
        If non empty, prob of each type if 25%
            0 -> Snake
            1 -> Cricket
            2 -> Vulture
            3 -> Trampoline
        Set jump value of each
        Set num of non empty tiles
        */
        int numsn = 0;
        int numcr = 0;
        int numvt = 0;
        int numtr = 0;

        // Jump values
        int snj = 1 + random.nextInt(10);
        int crj = 1 + random.nextInt(10);
        int vtj = 1 + random.nextInt(10);
        int trj = 1 + random.nextInt(10);

        this.GetRaceTrack().add(new White(0, "I am a White tile!"));
        for (int i=1; i<this.GetNumTiles()-1; i++) {

            Tile obj;

            if (random.nextInt(2) == 0) {

                int tiletype = random.nextInt(4);
                
                if (tiletype == 0) {

                    obj = new Snake(-snj, "Hiss...!! I am a Snake, you go back "+snj+" tiles!");
                    this.GetRaceTrack().add(obj);
                    numsn++;
                }
                else if (tiletype == 1) {
                    
                    obj = new Cricket(-crj, "Chirp...!! I am a Cricket, you go back "+crj+" tiles!");
                    this.GetRaceTrack().add(obj);
                    numcr++;
                }
                else if (tiletype == 2) {

                    obj = new Vulture(-vtj, "Yapping...!! I am a Vulture, you go back "+vtj+" tiles!");
                    this.GetRaceTrack().add(obj);
                    numvt++;
                }
                else {
                    
                    obj = new Trampoline(trj, "PingPong! I am a Trampoline, you advance "+trj+" tiles!");
                    this.GetRaceTrack().add(obj);
                    numtr++;
                }
            }

            else  {
                obj = new White(0, "I am a White tile!");
                this.GetRaceTrack().add(obj);
            }
        }
        this.GetRaceTrack().add(new White(0, "I am a White tile!"));

        this.TotalNumSnake = numsn;
        this.TotalNumCricket = numcr;
        this.TotalNumVulture = numvt;
        this.TotalNumTrampoline = numtr;

        System.out.println("Setting up the race track...");
        System.out.println("Danger: There are "+numsn+", "+numcr+" and "+numvt+" numbers of Snakes, Crickets and Vultures respectively on your track!");
        System.out.println("Danger: Each Snake, Cricket and Vulture can throw you back by "+snj+", "+crj+" and "+vtj+" number of Tiles respectively!");
        System.out.println("Good news: There are "+numtr+" number of Trampolines on your track!");
        System.out.println("Good news: Each Trampoline can help you advance "+trj+" number of Tiles");
        

        // Input UserName and exception handling
        System.out.println("Enter the Player Name"); 
        boolean correctname = false;
        String uname = "";
        int ft = 0;

        while (!(correctname)) {
            try {
                uname = this.sc.nextLine();
                if (uname.equals("")) {
                    throw new InvalidInputException("Invalid Input");
                }
                else {
                    correctname = true;
                }
            } catch (Exception e) {
                if (ft==0) {
                    ft++;
                }
                else {
                    System.out.println("Invalid Input");
                }
            }
        }
        this.UserName = uname;
    }



    // Return an integer between 1 and 6
    public int RollDice() {
         Random random = new Random();
         return (1+random.nextInt(6));
    }



    // Play out the complete scenario
    public void PlayGame() throws MovementException, GameWinnerException, SnakeBiteException {

        System.out.println("Starting the game with "+this.GetUserName()+" at Tile-1");
        System.out.println("Control transferred to Computer for rolling the Dice for "+this.GetUserName());
        System.out.println("Hit enter to start the game.");

        boolean correctinp = false;
        String un = "";

        // Hit Enter Error Handling
        while (!(correctinp)) {
            try {
                un = this.sc.nextLine();
                if (!(un.equals(""))) {
                    throw new InvalidInputException("Invalid Input");
                }
                else {
                    correctinp = true;
                }
            } catch (Exception e) {
                System.out.println("Invalid Input");    
            }
        }

        int currindex = 0;  // index 0 -> Tile 1
        boolean incage = true;
        System.out.println("Game Started");


        // Actual Game starts
        while (currindex < this.GetRaceTrack().size()) {

            int dicenum = this.RollDice();
            this.SetTotalMoves();
            System.out.println("[Roll-"+this.GetTotalMoves()+"]: "+this.GetUserName()+
                " rolled "+dicenum+" at Tile-"+(currindex+1));

            // If at starting position
            if ((incage) && (dicenum!=6)) {
                System.out.println("OOPs you need 6 to start");
            }
            
            // At starting position and 6 comes
            else if ((incage) && (dicenum==6)) {
                System.out.println("You are out of the cage! You get a free roll");
                incage = false;
            }

            // Other positions
            else {

                // Array Out of bounds exception
                try {
                    boolean test = this.GetRaceTrack().get(currindex+dicenum) instanceof Tile;
                    currindex = currindex + dicenum;
                    System.out.println("You landed at "+(currindex+1));

                } catch (Exception e) {
                    currindex = this.GetNumTiles() - 2;
                    System.out.println("You landed at "+(currindex+1));
                }
                
                System.out.println("Trying to shake the Tile-"+(currindex+1));
                    
                try {
                    this.GetRaceTrack().get(currindex).MvExcep();

                } catch (Exception e) {

                    if (this.GetRaceTrack().get(currindex) instanceof Snake) {
                        this.SetSnakeBites();
                    }
                    else if (this.GetRaceTrack().get(currindex) instanceof Cricket) {
                        this.SetCricketBites();
                    }
                    else if (this.GetRaceTrack().get(currindex) instanceof Vulture) {
                        this.SetVultureBites();
                    }
                    else if (this.GetRaceTrack().get(currindex) instanceof Trampoline){
                        this.SetTrampolineJumps();
                    }
                        
                    // On target 
                    if (currindex + 1 == this.GetNumTiles()) {
                        try {
                            System.out.println(this.GetUserName()+" wins the race in "+this.GetTotalMoves()+" rolls!\n"+
                            "Total Snake Bites = "+this.GetSnakeBites()+"\nTotal Vulture Bites = "+this.GetVultureBites()+
                            "\nTotal Cricket Bites = "+this.GetCricketBites()+"\nTotal Trampolines = "+this.GetTrampolineJumps());

                            throw new GameWinnerException("GAME OVER");
                                
                        } catch (Exception f) {
                            System.exit(0);
                        }
                    }

                    // Overshot the destination
                    else if ((currindex + 1)+this.GetRaceTrack().get(currindex).GetJumpVal() > this.GetNumTiles()) {
                        System.out.println(this.GetUserName()+" moved to Tile-"+this.GetNumTiles());
                        currindex = this.GetNumTiles()-2;
                    }

                    // Went too back
                    else if ((currindex + 1)+this.GetRaceTrack().get(currindex).GetJumpVal() < 0) {
                        System.out.println(this.GetUserName()+" moved to Tile 1 as it can't go back further");
                        currindex = 0;
                        incage = true;
                    }

                    // Landed before destination
                    else {
                        currindex = currindex+this.GetRaceTrack().get(currindex).GetJumpVal();
                        System.out.println(this.GetUserName()+" moved to Tile-"+(currindex+1));
                    }
                }
            } 
        }
    }



    // Getters and Setters
    public int GetNumTiles() {
        return this.NumTiles;
    }
    public String GetUserName() {
        return this.UserName;
    }
    public int GetTotalNumSnake() {
        return this.TotalNumSnake;
    }
    public int GetTotalNumCricket() {
        return this.TotalNumCricket;
    }
    public int GetTotalNumVulture() {
        return this.TotalNumVulture;
    }
    public int GetTotalNumTrampoline() {
        return this.TotalNumTrampoline;
    }
    public ArrayList<Tile> GetRaceTrack() {
        return this.RaceTrack;
    }
    public int GetSnakeBites() {
        return this.SnakeBites;
    }
    public void SetSnakeBites() {
        this.SnakeBites++;
    }
    public int GetCricketBites() {
        return this.CricketBites;
    }
    public void SetCricketBites() {
        this.CricketBites++;
    }
    public int GetVultureBites() {
        return this.VultureBites;
    }
    public void SetVultureBites() {
        this.VultureBites++;
    }
    public int GetTrampolineJumps() {
        return this.TrampolineJumps;
    }
    public void SetTrampolineJumps() {
        this.TrampolineJumps++;
    }
    public int GetTotalMoves() {
        return this.TotalMoves;
    }
    public void SetTotalMoves() {
        this.TotalMoves++;
    }
}



public class RacingGame {
    public static void main(String[] args) throws MovementException, GameWinnerException, TrackLengthException, InvalidInputException, SnakeBiteException, CricketBiteException, VultureBiteException, TrampolineException {
        
        Application app = new Application();
        app.PlayGame();
    }
}