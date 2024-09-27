import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        while(true){
            System.out.println("Введите номер задачи");
            int num = in.nextInt();
            in.nextLine();
            switch(num){
                case 1:
                    first();
                    break;
                case 2:
                    second();
                    break;
                case 3:
                    third();
                    break;
                case 4:
                    fourth();
                    break;
                case 5:
                    fifth();
                    break;

            }
        }

    }

    public static void first() {
        System.out.println("Введите число: ");
        int n = in.nextInt();
        int steps = 0;

        while(n != 1)
        {
            steps++;
            if(n%2 == 0)
            {
                n /= 2;
            }
            else
            {
                n = 3*n + 1;
            }
        }
        System.out.println("Кол-во шагов: " + steps);
    }


    public static void second(){
        System.out.println("Введите кол-во чисел: ");
        int n = in.nextInt();

        System.out.println("Введите числа: ");
        int first_num = in.nextInt();
        int mult = -1;

        for(int i = 0;i< n-1 ;i++)
        {
            int next_num = in.nextInt();
            first_num = first_num + mult*next_num;
            mult *= -1;
        }

        System.out.println(first_num);

    }

    public static void third()
    {
        System.out.println("Введите координату X: ");
        int x = Integer.parseInt(in.nextLine());
        System.out.println("Введите координату Y: ");
        int y = Integer.parseInt(in.nextLine());

        String line = " ";
        int step = 0;
        int steps = 0 ;


        while(true){
           System.out.println("Введите направление: ");
           line = in.nextLine();


           if(Objects.equals(line, "стоп")) {
               System.out.println("Map is wrong");
               break;
           }

           System.out.println("Введите шаг: ");
           step = Integer.parseInt(in.nextLine());


            switch (line){              // вместо того, чтобы двигаться из (0,0) в (x,y),
                case "север"->
                    y -= step;          // будем двигаться из (x,y) до тех пор
                case "юг"->
                    y += step;          // пока координаты не обнулятся
                case "запад"->
                    x += step;          // при этом движемся в "обратном" направлении
                case "восток"->
                    x -= step;          // что обуславливает знаки
            }
           steps++;
           if((x == 0) && (y == 0))
           {
               System.out.println("Кол-во указаний: " + steps);
               break;
           }

        }

    }
    public static void fourth() {

        System.out.println("Введите кол-во дорог: ");
        int road_count = in.nextInt();
        int max_height = 0;
        int road = 0;

        for(int i = 1; i <= road_count;i++ )
        {
            System.out.println("Введите кол-во туннелей: ");
            int tunnel_count = in.nextInt();
            int MIN = Integer.MAX_VALUE;

            for(int j = 1; j <= tunnel_count; j++)
            {
                int curr_tunnel = in.nextInt();
                if(curr_tunnel<MIN)
                {
                    MIN = curr_tunnel;
                }
            }
            if(MIN > max_height) {
                max_height = MIN;
                road = i;
            }
        }
        System.out.println(road);
        System.out.println(max_height);
    }

    public static void fifth()
    {
        System.out.println("Введите число: ");
        int num = in.nextInt();

        int sum = 0;
        int mult = 1;

        while(num != 0)
        {
            sum += num%10;
            mult *= num%10;
            num /= 10;
        }
        if(sum % 2 == 0 && mult % 2 == 0){
            System.out.println("Является");
        }
        else{
            System.out.println("Не является");
        }
    }

}


