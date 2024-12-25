package Figures;
import Board.Board;

public class King extends Figure{

    private boolean underAttack = false;
    private boolean Mate = false;

    public King(String name, char color) {
        super(name,color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (!super.canMove(row, col, row1, col1)) {
            return false;
        }
        if (    ((row + 1 == row1) || (row - 1 == row1) || (row == row1)) &&
                ((col + 1 == col1) || (col - 1 == col1) || (col == col1)) &&
                !( (col==col1) && (row==row1) )) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        if(!super.canAttack(row,col,row1,col1)){
            return false;
        }
        if(this.canMove(row,col,row1,col1)){
            return true;

        }
        return false;
    }

    public void canBeMoved()
    {



    }

    public void Check()
    {
        underAttack = true;
    }

    public void notCheck()
    {
        underAttack = false;
    }



}
