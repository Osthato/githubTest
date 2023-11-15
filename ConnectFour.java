import java.util.Scanner;

public class ConnectFour {
  Scanner input = new Scanner(System.in);
  
  private String[][] board;
  private String turn;
  private int lastX;
  private int lastY;
  private boolean won;
  
  public ConnectFour() {
    String[][] placeholder = {
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="}
    };
    board = placeholder;
    turn = "X";
    lastX = 0;
    lastY = 0;
    won = false;
  }

  public String getTurn() {
    return turn;
  }

  public boolean getWon() {
    return won;
  }

  public void reset() {
    String[][] placeholder = {
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="},
      {"=", "=", "=", "=", "=", "=", "="}
    };
    board = placeholder;
    turn = "X";
    lastX = 0;
    lastY = 0;
    won = false; 
  }

//   public void runGame() {
//     while (!won) {
//         takeTurn();
//     }
//   }

  public void printBoard() {
    String boardResult = "";
    for (String[] row : board) {
      for (String column : row) {
        boardResult += column + " ";
      }
      boardResult += "\n";
    }

    System.out.println(boardResult);
  }

  public void takeTurn(int column) {
    // System.out.print("\nIt is " + turn + "'s turn \nWhat column will you place your tile in?: ");
    //int column = input.nextInt();
    // while (!((column <= 7 && column >= 1) && board[0][column-1].equals("="))) {
    //   System.out.print("Invalid Placement\nWhat column will you place your tile in?: ");
    //   column = input.nextInt();
    // }
    if (!(board[0][column-1].equals("="))) {
        if (turn.equals("X")) {
            turn = "O";
        }
        else {
            turn = "X";
        }
        won = true;
        return;
    }
    placeTile(column);
    // printBoard();
    if (checkWin(lastX, lastY)) {
      won = true;
      return;
    }
    if (turn.equals("X")) {
      turn = "O";
    }
    else {
      turn = "X";
    }
  }

  public void placeTile(int column) {
    for (int row = 5; row >= 0; row--) {
      if (board[row][column-1].equals("=")) {
        board[row][column-1] = turn;
        lastY = row;
        lastX = column-1;
        break;
      }
    }
  }
  public boolean checkWin(int x, int y) {
    if (risingDiagonal(x, y) || fallingDiagonal(x, y) || horizontal(x, y) || vertical(x, y)) {
      return true;
    }
    
    return false;
  }

  public boolean risingDiagonal(int xPosition, int yPosition) {
    int count = 1;
    for (int index = 1; index <= xPosition && index <= 5-yPosition; index++) {
      if (board[yPosition+index][xPosition-index].equals(turn)) {
        count++;
      }
      else {
        break;
      }
    }  
    for (int index = 1; index <= 6-xPosition && index <= yPosition; index++) {
      if (board[yPosition-index][xPosition+index].equals(turn)) {
        count++;
      }
      else {
        break;
      }
    }
    if (count >= 4) {
      return true;
    }
    
    return false;
  }

  public boolean fallingDiagonal(int xPosition, int yPosition) {
    int count = 1;
    for (int index = 1; index <= xPosition && index <= yPosition; index++) {
      if (board[yPosition-index][xPosition-index].equals(turn)) {
        count++;
      }
      else {
        break;
      }
    }  
    for (int index = 1; index <= 6-xPosition && index <= 5-yPosition; index++) {
      if (board[yPosition+index][xPosition+index].equals(turn)) {
        count++;
      }
      else {
        break;
      }
    }
    if (count >= 4) {
      return true;
    }
    
    return false;
  }

  public boolean horizontal(int xPosition, int yPosition) {
    int count = 1;
    for (int index = 1; index <= xPosition; index++) {
      if (board[yPosition][xPosition-index].equals(turn)) {
        count++;
      }
      else {
        break;
      }
    }  
    for (int index = 1; index <= 6-xPosition; index++) {
      if (board[yPosition][xPosition+index].equals(turn)) {
        count++;
      }
      else {
        break;
      }
    }
    if (count >= 4) {
      return true;
    }

    return false;
  }
  
  public boolean vertical(int xPosition, int yPosition) {
    int count = 1;
    for (int index = 1; index <= yPosition; index++) {
      if (board[yPosition-index][xPosition].equals(turn)) {
        count++;
      }
      else {
        break;
      }
    }  
    for (int index = 1; index <= 5-yPosition; index++) {
      if (board[yPosition+index][xPosition].equals(turn)) {
        count++;
      }
      else {
        break;
      }
    }
    if (count >= 4) {
      return true;
    }

    return false;
  }

  public double[] convertBoard() {
    double[] result = new double[42];
    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 7; j++) {
            if (board[i][j].equals("=")) {
                result[i*7 + j] = 0;
            }
            else if (board[i][j].equals(turn)) {
                result[i*7 + j] =  1;
            }
            else {
                result[i*7 + j] = -1;
            }
        }
    }

    return result;
  }

}


    