package Figures;

public class Bishop extends Figure{
    public Bishop(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (!super.canMove(row, col, row1, col1)) {
            return false;
        }
        if (  Math.abs(row1-row)==Math.abs(col1-col) )
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1){
        if(this.canMove(row, col, row1, col1)) {
            return true;
        }
        return false;
    }



}
