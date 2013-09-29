/**
 * AI class which handles non-human paddles and statistics. The AI object must have it's move()
 * function called. It must use the single constructor to get coordinates to move the paddle.
 * @author pyro-auger
 */

package prototype;

//NOTE: For this class to work, you must specify all the arguments for the constructor
public class AI  {
    Player stats;
    Paddle paddle;
    Ball ball;
    Pong board;

    public AI() {
        this(null, null, null);     // RED_FLAG: just to get AITest to compile
    }

    /**
     * Creates a new AI class with paddle,ball, and pong board specified, and makes a new stats object
     * @param paddle_temp AI's paddle is specified from board
     * @param ball_temp AI's ball is specified from board
     * @param board_temp AI's pong board is specified
     */
    public AI(Paddle paddle_temp, Ball ball_temp, Pong board_temp)  {
        this.paddle = paddle_temp;
        this.stats = new Player("AI", this.paddle);
        this.ball = ball_temp;
        this.board = board_temp;
    }

    /**
     * Checks to see that the ball is moving towards paddle
     * @return is it necessary to move
     */
    public boolean needToMove() {    //Pong or Polygon needs to be able to tell where the paddle is
        return true; //STUB                        //on the board
    }

    /**
     * Finds which direction to move paddle
     * @return needs to move left if True, right if otherwise
     */
    public boolean findDirectionToMove() {    //Pong/Polygon needs to give more info on positioning
        return true; //STUB
    }

    /**
     * Moves the paddle
     */
    public void move() {//Will not work correctly until needToMove and findDirectionToMove work correctly
        if(this.needToMove()) {
            if(findDirectionToMove()) paddle.move(true, 1);
            else paddle.move(false, 3);
        }
    }


}
