import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {

        while(true){
            System.out.println("Введите номер задачи: ");
            int n = in.nextInt();
            in.nextLine();

            switch(n){
                case 1:
                    System.out.println("Введите строку: ");
                    String str = in.nextLine();
                    System.out.println(first(str));
                    break;
                case 2:
                    System.out.println("Введите размер первого массива: ");
                    int n1 = in.nextInt();
                    int[] mas1 = new int[n1];

                    System.out.println("Введите первый массив: ");
                    for(int i = 0; i < n1;i++)
                        mas1[i] = in.nextInt();

                    System.out.println("Введите размер второго массива: ");
                    int n2 = in.nextInt();
                    int[] mas2 = new int[n2];

                    System.out.println("Введите второй массив: ");
                    for(int i = 0; i < n2;i++)
                        mas2[i] = in.nextInt();

                    System.out.println(Arrays.toString(second(mas1,mas2)));
                    break;

                case 3:
                    System.out.println("Введите размер массива: ");
                    int n3 = in.nextInt();
                    int[] mas = new int[n3];
                    System.out.println("Введите массив: ");
                    for(int i = 0; i < n3;i++)
                        mas[i] = in.nextInt();
                    System.out.println(third(mas));
                    break;


                case 4:
                    System.out.println("Введите кол-во строк: ");
                    int n4 = in.nextInt();
                    System.out.println("Введите кол-во столбцов: ");
                    int m4 = in.nextInt();
                    int[][] mas4 = new int[n4][m4];
                    System.out.println("Введите элементы массива(построчно)");
                    for(int i = 0;i<n4;i++){
                        for(int j = 0;j<m4;j++)
                        {
                            mas4[i][j] = in.nextInt();
                        }
                    }
                    fourth(mas4);
                    break;
                case 5:
                    System.out.println("Введите длину массива");
                    int n5 = in.nextInt();
                    int[] mas5 = new int[n5];
                    System.out.println("Введите массив: ");
                    for(int i = 0; i < n5; i++){
                        mas5[i] = in.nextInt();
                    }
                    System.out.println("Введие таргет: ");
                    int target = in.nextInt();
                    System.out.println(Arrays.toString(fifth(mas5, target)));
                    break;
                case 6:
                    System.out.println("Введите кол-во строк: ");
                    int n6 = in.nextInt();
                    System.out.println("Введите кол-во столбцов: ");
                    int m6 = in.nextInt();
                    int[][] mas6 = new int[n6][m6];
                    System.out.println("Введите элементы массива(построчно)");
                    for(int i = 0;i<n6;i++){
                        for(int j = 0;j<m6;j++)
                        {
                            mas6[i][j] = in.nextInt();
                        }
                    }
                    System.out.println(sixth(mas6));
                    break;

                case 7:
                    System.out.println("Введите кол-во строк: ");
                    int n7 = in.nextInt();
                    System.out.println("Введите кол-во столбцов: ");
                    int m7 = in.nextInt();
                    int[][] mas7 = new int[n7][m7];
                    System.out.println("Введите элементы массива(построчно)");
                    for(int i = 0;i<n7;i++){
                        for(int j = 0;j<m7;j++)
                        {
                            mas7[i][j] = in.nextInt();
                        }
                    }
                    System.out.println(Arrays.toString(seventh(mas7)));
                    break;
                case 8:
                    System.out.println("Введите кол-во строк: ");
                    int n8 = in.nextInt();
                    System.out.println("Введите кол-во столбцов: ");
                    int m8 = in.nextInt();
                    int[][] mas8 = new int[n8][m8];
                    System.out.println("Введите элементы массива(построчно)");
                    for(int i = 0;i<n8;i++){
                        for(int j = 0;j<m8;j++)
                        {
                            mas8[i][j] = in.nextInt();
                        }
                    }
                    eleventh(mas8);
                    break;
            }
        }
    }

    public static String first(String str){ // 1. Найти наибольшую подстроку без повторяющихся символов.

        int left = 0; // левый указатель
        int maxLen = 0; // максимальная длина подстроки
        String unique = "";
        String current = "";

        for(int right = 0; right < str.length();right++)
        {
            while(current.contains(Character.toString(str.charAt(right)))){
                current = current.substring(1);//удаляем элементы слева, пока не удалим повторившийся
                left++;
            }
            current += str.charAt(right);

            if(unique.length() < current.length()){
                unique = current;
            }
        }
        return unique;

    }

    public static int[] second(int[] mas1, int[] mas2){ // 2.Объединить два отсортированных массива
        int[] mas = new int[mas1.length + mas2.length];
        for(int i = 0; i < mas1.length;i++){
            mas[i] = mas1[i];
        }
        for(int i = 0; i < mas2.length;i++){
            mas[mas1.length+i] = mas2[i];
        }
        Arrays.sort(mas);
        return mas;
    }

    public static int third(int[] mas){ // 3.Найти максимальную сумму подмассива
        int sum = mas[0];
        int temp_max = mas[0];

        for(int i = 1; i <mas.length;i++){
            temp_max = Math.max(temp_max + mas[i],mas[i]);// смысл в том, что если сумма становится отрицательной,
            sum = Math.max(sum,temp_max);                 // то лучше начинать со следующего элемента, бОльшего этой суммы

        }
        return sum;
    }

    public static int[][] fourth(int[][] mas){ // 4.Повернуть массив на 90 градусов по часовой стрелке.
        int m = mas[0].length; // m - кол-во столбцов в исходном двумерном массиве
        int n = mas.length;    // n - кол-во строк в исходном двумерном массиве
        int[][] arr = new int[m][n]; // новая матрица будет размерностью [m][n]

        for(int i = 0; i < m;i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = mas[n - j - 1][i];
            }
        }
        System.out.println("Исходный массив: ");
        for(int i = 0; i < mas.length;i++){
            int[] temp = mas[i];
            System.out.println(Arrays.toString(temp));
        }
        System.out.println("Повёрнутый массив: ");
        for(int i = 0; i < arr.length;i++){
            int[] temp = arr[i];
            System.out.println(Arrays.toString(temp));
        }

        return arr;
    }

    public static int[] fifth(int[] mas, int target){ // 5.Найти пару элементов в массиве, сумма которых равна заданному числу.
        int[] res = new int[2];
        for (int i = 0; i < mas.length; i++)
            for (int j = 0; j < mas.length; j++){
                if ( (i != j) && (mas[i] + mas[j] == target)){
                    res[0] = mas[i];
                    res[1] = mas[j];
                    return res;
                }
            }
        return null;
    }

    public static int sixth(int[][] mas){ // 6.Найти сумму всех элементов в двумерном массиве
        int sum = 0;
        for(int i = 0; i < mas.length;i++){
            for(int j = 0; j < mas[0].length; j++){
                sum += mas[i][j];
            }
        }
        return sum;
    }

    public static int[] seventh(int[][] mas){ // 7.Найти максимальный элемент в каждой строке двумерного массива

        int[] arr = new int[mas.length];
        for(int i = 0; i < mas.length;i++){
            int elem = mas[i][0];
            for(int j = 1; j < mas[0].length; j++)
            {
               if(elem < mas[i][j])
               {
                   elem = mas[i][j];
               }
            }
            arr[i] = elem;
        }
        return arr;
    }

    public static int[][] eleventh(int[][] mas){ // 8. Повернуть двумерный массив на 90 градусов против часовой стрелке
        int m = mas[0].length; // m - кол-во столбцов в исходном двумерном массиве
        int n = mas.length;    // n - кол-во строк в исходном двумерном массиве
        int[][] arr = new int[m][n]; // новая матрица будет размерностью [m][n]

        for(int i = 0; i < m;i++){
            for(int j = 0; j < n; j++){
                arr[i][j] = mas[j][m-i-1];
            }
        }
        System.out.println("Исходный массив: ");
        for(int i = 0; i < mas.length;i++){
            int[] temp = mas[i];
            System.out.println(Arrays.toString(temp));
        }
        System.out.println("Повёрнутый массив: ");
        for(int i = 0; i < arr.length;i++){
            int[] temp = arr[i];
            System.out.println(Arrays.toString(temp));
        }

        return arr;



    }

}
