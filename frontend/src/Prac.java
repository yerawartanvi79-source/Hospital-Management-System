import java.util.*;
public class Prac {
    public static void main(String[] args) {
        int count=0;
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int target=n;
        int[] arr=new int[10];
        for(int i=0;i<10;i++){
            arr[i]=sc.nextInt();
        }
        for(int i=0;i<10;i++){
            System.out.print(arr[i]+" ");
        
        if (target==arr[i]){
            count++;
        }}
        System.out.println();
        System.out.println(count);

    }
}
