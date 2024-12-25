package Figures;
import Board.Board;


public abstract class Figure {
    private  String name;
    private  char color;
    private  int row;
    private  int col;
    private boolean isEaten = false;

    public Figure(String name, char color){
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getColor() {
        return color;
    }

    public void setEaten()
    {
        this.isEaten = true;
    }
    public boolean getEaten()
    {
        return isEaten;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setRow(int row) { this.row = row;}

    public int getRow() {return row;}

    public void setCol(int col) {this.col = col;}

    public int getCol() {return col;}


    public boolean canMove(int row, int col, int row1, int col1){
        return  (row>= 0 && row < 8) && (col>=0 && col<8) &&
                (row1>= 0 && row1 < 8) && (col1>=0 && col1<8)&&
                !((col==col1) && (row==row1));
    }

    public boolean canAttack(int row, int col, int row1, int col1){
        return this.canMove(row, col, row1, col1);
    }
}
