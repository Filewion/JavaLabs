package Board;

import Figures.Figure;
import Figures.Pawn;
import Figures.Rook;
import Figures.King;
import Figures.Queen;
import Figures.Bishop;
import Figures.Knight;

public class Board {

    private char colorGame;

    public void setColorGame(char colorGame) {
        this.colorGame = colorGame;
    }

    public  char getColorGame(){
        return colorGame;
    }

    private Figure WhiteFigures[];
    private Figure BlackFigures[];
    private Figure fields[][] = new Figure[8][8];


    public void init(){
        WhiteFigures = new Figure[] {
                new Rook("R",'w'), new Knight("k", 'w'), new Bishop("B",'w'),
                new Queen("Q", 'w'), new King("K",'w'), new Bishop("B",'w'),
                new Knight("k", 'w'), new Rook("R",'w') ,
                new Pawn("P", 'w'), new Pawn("P", 'w'),
                new Pawn("P", 'w'),new Pawn("P", 'w'),
                new Pawn("P", 'w'),new Pawn("P", 'w'),
                new Pawn("P", 'w'),new Pawn("P", 'w'),
        };
        for(int i = 0; i < 8;i++)
        {
            this.fields[0][i] = WhiteFigures[i];
            WhiteFigures[i].setRow(0);
            WhiteFigures[i].setCol(i);
        };
        for(int i = 0; i < 8;i++)
        {
            this.fields[1][i] = WhiteFigures[8+i];
            WhiteFigures[i].setRow(1);
            WhiteFigures[i].setCol(i);
        };

        BlackFigures = new Figure[] {
                new Rook("R",'b'), new Knight("k", 'b'), new Bishop("B",'b'),
                new Queen("Q", 'b'), new King("K",'b'), new Bishop("B",'b'),
                new Knight("k", 'b'), new Rook("R",'b') ,
                new Pawn("P", 'b'), new Pawn("P", 'b'),
                new Pawn("P", 'b'),new Pawn("P", 'b'),
                new Pawn("P", 'b'),new Pawn("P", 'b'),
                new Pawn("P", 'b'),new Pawn("P", 'b'),
        };
        for(int i = 0; i < 8;i++)
        {
            this.fields[7][i] = BlackFigures[i];
            BlackFigures[i].setRow(7);
            BlackFigures[i].setCol(i);
        };
        for(int i = 0; i < 8;i++)
        {
            this.fields[6][i] = BlackFigures[8+i];
            BlackFigures[i].setRow(6);
            BlackFigures[i].setCol(i);
        };
    }

    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure ==null){
            return "    ";
        }
        return  " "+figure.getColor()+figure.getName()+" ";
    }
    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for (int row = 7; row > -1 ; row --){
            System.out.print(row);
            for (int col=0; col<8; col++){
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col=0; col< 8; col++){
            System.out.print("    "+col);
        }
    }

    public boolean move_figure(int row, int col, int row1, int col1){ //движение фигуры при перемещении/атаке
      Figure figure = this.fields[row][col];
      if (figure != null && figure.canMove(row, col, row1, col1) && this.fields[row1][col1] == null && figure.getColor() == this.colorGame && (figure.getName().equals("k") || isPathClear(row, col, row1,col1)) ){
          if(figure.getName().equals("K") && nextKingCheck(row,col,row1,col1,figure.getColor()))
          {
             return false;
          }
          this.fields[row1][col1] = figure;
          figure.setRow(row1);
          figure.setCol(col1);
          this.fields[row][col] = null;
          return true;
      }else  if (figure.canAttack(row, col, row1, col1) && this.fields[row1][col1] != null && this.fields[row1][col1].getColor() != this.fields[row][col].getColor() && (isPathClear(row, col, row1,col1) || figure.getName().equals("k")) ){
          if(this.fields[row1][col1].getName().equals("K"))
              return false;
          if(figure.getName().equals("K") && nextKingCheck(row,col,row1,col1,figure.getColor()))
          {
              return false;
          }
          this.fields[row1][col1].setEaten();
          this.fields[row1][col1] = figure;

          figure.setRow(row1);
          figure.setCol(col1);
          this.fields[row][col] = null;
          return true;
      }
        return false;
    }

    
    public boolean kingCheck(char color) // проверка на шах
    {
        if(color == 'w')
        {
            int KRow = findKing('w').getRow();
            int KCol = findKing('w').getCol();
            for(int i = 0; i < 16;i++)
            {
                if(BlackFigures[i].canAttack(BlackFigures[i].getRow(),BlackFigures[i].getCol(),KRow,KCol))
                {
                    return true;
                }
            }
            return false;
        }
        if(color == 'b')
        {
            int KRow = findKing('b').getRow();
            int KCol = findKing('b').getCol();
            for(int i = 0; i < 16;i++)
            {
                if(WhiteFigures[i].canAttack(WhiteFigures[i].getRow(),WhiteFigures[i].getCol(),KRow,KCol))
                {
                    return true;
                }
            }

        }
        return false;
    }

    public Figure findKing(char color) // нахождение короля
    {
        if(color == 'w')
        {
            for(int i = 0; i < 16;i++)
            {
                if(WhiteFigures[i].getName().equals("K"))
                    return WhiteFigures[i];
            }
        }
        if(color == 'b')
        {
            for(int i = 0; i < 16;i++)
            {
                if(BlackFigures[i].getName().equals("K"))
                    return BlackFigures[i];
            }
        }
        return null;
    }
    public boolean nextKingCheck(int row, int col, int row1, int col1,char color) // проверка след. хода на шах
    {
        if(color == 'w') {
            for (int i = 0; i < 16; i++) {
                if (BlackFigures[i].canAttack(row, col, row1, col1)) {
                    return true;
                }
            }
        }
        if(color == 'b')
        {
            for(int i = 0; i < 16;i++) {
                if (WhiteFigures[i].canAttack(row, col, row1, col1)) {
                    return true;
                }
            }
        }
        return false;
    }


    public boolean isCheckmate(char color) // проверка на мат
    {
        int kRow = findKing(color).getRow();
        int kCol = findKing(color).getCol();
        if (!kingCheck(color))
            return false;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 && j != 0 && findKing(color).canMove(kRow, kCol, kRow + i, kCol + j) && !nextKingCheck(kRow, kCol, kRow + i, kCol + j, color)) {
                    return false;
                }
            }
        }
        Figure[][] scndFields = fields.clone();
        Figure[] whites = WhiteFigures.clone();
        Figure[] blacks = BlackFigures.clone();

        if (color == 'w') {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (fields[i][j] != null && fields[i][j].getColor() == 'w') {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (fields[i][j].canMove(fields[i][j].getRow(), fields[i][j].getCol(), k, l)) {
                                    move_figure(fields[i][j].getRow(), fields[i][j].getCol(), k, l);
                                    if (!kingCheck(color)) {
                                        fields = scndFields.clone();
                                        WhiteFigures = whites.clone();
                                        BlackFigures = blacks.clone();
                                        return false;

                                    }
                                    fields = scndFields.clone();
                                    WhiteFigures = whites.clone();
                                    BlackFigures = blacks.clone();
                                }
                            }
                        }
                    }
                }
            }
        }

        if (color == 'b') {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (fields[i][j] != null && fields[i][j].getColor() == 'b') {
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; l < 8; l++) {
                                if (fields[i][j].canMove(fields[i][j].getRow(), fields[i][j].getCol(), k, l)) {
                                    move_figure(fields[i][j].getRow(), fields[i][j].getCol(), k, l);
                                    if (!kingCheck(color)) {
                                        fields = scndFields.clone();
                                        WhiteFigures = whites.clone();
                                        BlackFigures = blacks.clone();
                                        return false;

                                    }
                                    fields = scndFields.clone();
                                    WhiteFigures = whites.clone();
                                    BlackFigures = blacks.clone();
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }



    public boolean isPathClear(int row, int col, int row1, int col1) {
        if (row == row1) { // горизонтальное перемещение
            int step = (col1 > col) ? 1 : -1;
            for (int c = col + step; c != col1; c += step) {
                if (fields[row][c] != null) return false;
            }
        } else if (col == col1) { // вертикальное перемещение
            int step = (row1 > row) ? 1 : -1;
            for (int r = row + step; r != row1; r += step) {
                if (fields[r][col] != null) return false;
            }
        } else if (Math.abs(row1 - row) == Math.abs(col1 - col)) { // диагональное перемещение
            int rowStep = (row1 > row) ? 1 : -1;
            int colStep = (col1 > col) ? 1 : -1;
            int r = row + rowStep, c = col + colStep;
            while (r != row1 && c != col1) {
                if (fields[r][c] != null) return false;
                r += rowStep;
                c += colStep;
            }
        }
        return true;
    }
}
