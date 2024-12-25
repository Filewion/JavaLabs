package Figures;

public class Pawn extends Figure {
    private  boolean isFirstStep = true;
    public Pawn(String name, char color) {
        super(name, color);
    }

    @Override
    public boolean canMove(int row, int col, int row1, int col1) {
        if (!super.canMove(row, col, row1, col1)){
            return false;
        }
        if(this.isFirstStep){
            if (((((row+2==row1)||(row+1==row1)) && this.getColor()=='w') ||
                    (((row-2==row1)||(row-1==row1)) && this.getColor()=='b') ) && (col==col1)){
                this.isFirstStep = false;
                return true;
            }
        }else{
            if (((((row+1==row1)) && this.getColor()=='w') && (col==col1) || (((row-1==row1)) && this.getColor()=='b' && (col==col1)))){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canAttack(int row, int col, int row1, int col1) {
        if( !((row>= 0 && row < 8) && (col>=0 && col<8) &&
                (row1>= 0 && row1 < 8) && (col1>=0 && col1<8)&&
                !((col==col1) && (row==row1)) )){
            return false;
        }
        if ( ( (row - row1) == 1 && (col - col1) == 1 && this.getColor() == 'b') ||
             ( (row1 - row) == 1 && (col1 - col) == 1 && this.getColor() == 'w') )
        { return true; };

        return false;
    }
}
