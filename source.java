import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.program.*;
import acm.util.MediaTools;
import com.sun.media.jfxmedia.AudioClip;
import java.awt.Color;
import java.awt.event.MouseEvent;
public class BlankClass extends GraphicsProgram {
  private static final double GRAVITY = 0.2;
  private static final int DELAY = 10;
  private static final double BALL_SIZE = 100;
  public static final int APPLICATION_WIDTH = 420;
  public static final int APPLICATION_HEIGHT = 600;
  private GImage ball;
  private double vx = 0;
  private double vy = 5;
  private GLabel inGameScore;
  private GLabel endGameScore;
  private int score;
  public void run() {

    addMouseListeners();
    // ball animation
    while (true) {
      GImage backround = new GImage(
          "760036eeb7cb921a390df7d1f64f9923--white-brick-wallpaper-white-brick-walls.jpg");
      backround.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
      add(backround, 0, 0);
      ball = new GImage("soccerTrump.png");
      ball.setSize(BALL_SIZE, BALL_SIZE);
      add(ball, (getWidth() - BALL_SIZE) / 2, (getHeight() - BALL_SIZE) / 2);
      startOfTheGame();
      inGameScore();

      // bouncing of the side walls + GRAVITY
      while (ball.getY() <= getHeight()) {
        vy = vy + GRAVITY;
        ball.move(vx, vy);
        if (ball.getX() >= (getWidth() - BALL_SIZE)) {
          vx = -vx;
        } else if (ball.getX() <= 0) {
          vx = -vx;
        }
        pause(DELAY);
      }

      // when the ball falls
      vy = 5;
      remove(ball);
      remove(inGameScore);
      gameOver();
      endGameScore();
      meme();
      pause(4000);
      remove(endGameScore);
      score = 0;
    }
  }

  // when mouse is pressed
  public void mousePressed(MouseEvent e) {
    double mouseX = e.getX();
    double mouseY = e.getY();
    double ballLocationX = ball.getX();
    double ballLocationY = ball.getY();

    // change here the directions and the speed of the ball
    if (mouseX >= ballLocationX && mouseX <= ballLocationX + BALL_SIZE / 5 &&
        mouseY >= ballLocationY && mouseY <= ballLocationY + BALL_SIZE) {
      vx = 10;
      vy = -8;
      score = score + 1;
      inGameScore.setLabel("Score: " + score);

    } else if (mouseX >= ballLocationX + BALL_SIZE / 5 &&
               mouseX <= ballLocationX + BALL_SIZE / 5 * 2 &&
               mouseY >= ballLocationY && mouseY <= ballLocationY + BALL_SIZE) {
      vx = 9;
      vy = -9;
      score = score + 1;
      inGameScore.setLabel("Score: " + score);

    } else if (mouseX >= ballLocationX + BALL_SIZE / 5 * 2 &&
               mouseX <= ballLocationX + BALL_SIZE / 5 * 3 &&
               mouseY >= ballLocationY && mouseY <= ballLocationY + BALL_SIZE) {
      vx = 0;
      vy = -10;
      score = score + 1;
      inGameScore.setLabel("Score: " + score);
      // soundEfect();
    } else if (mouseX >= ballLocationX + BALL_SIZE / 5 * 3 &&
               mouseX <= ballLocationX + BALL_SIZE / 5 * 4 &&
               mouseY >= ballLocationY && mouseY <= ballLocationY + BALL_SIZE) {
      vx = -9;
      vy = -9;
      score = score + 1;
      inGameScore.setLabel("Score: " + score);

    } else if (mouseX >= ballLocationX + BALL_SIZE / 5 * 4 &&
               mouseX <= ballLocationX + BALL_SIZE && mouseY >= ballLocationY &&
               mouseY <= ballLocationY + BALL_SIZE) {
      vx = -10;
      vy = -8;
      score = score + 1;
      inGameScore.setLabel("Score: " + score);
    }
  }

  // make a startButton
  private void startOfTheGame() {
    GLabel startButton = new GLabel("Click on Trump to start the game!");
    startButton.setFont("Papyrus-25");
    add(startButton, (getWidth() - startButton.getWidth()) / 2,
        (getHeight() - startButton.getHeight()) / 2 - BALL_SIZE / 2);
    waitForClick();
    remove(startButton);
  }

  // make a inGameScore
  private void inGameScore() {
    inGameScore = new GLabel("Score: " + score);
    inGameScore.setFont("Papyrus-20");
    add(inGameScore, 0, inGameScore.getHeight());
  }

  // make a endGameScore
  private void endGameScore() {
    endGameScore = new GLabel("Final score: " + score);
    endGameScore.setFont("Papyrus-30");
    add(endGameScore, (getWidth() - endGameScore.getWidth()) / 2,
        (getHeight() - endGameScore.getHeight()) / 2 + 10);
  }

  // make a meme
  private void meme() {
    GImage meme = new GImage("meme.png");
    meme.setSize(310, 200);
    add(meme, (getWidth() - meme.getWidth()) / 2,
        (getHeight() - meme.getHeight()) / 2 + 100);
  }

  // make a gameOverSign
  private void gameOver() {
    GLabel gameOverSign = new GLabel("GAME OVER");
    gameOverSign.setFont("Papyrus-50");
    add(gameOverSign, (getWidth() - gameOverSign.getWidth()) / 2,
        (getHeight() - gameOverSign.getHeight()) / 2 - 40);
  }
}
