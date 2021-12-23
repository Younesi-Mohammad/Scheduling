import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        String[] arrTime = line2.split(" ");
        int n = arrTime.length;
        String line3 = scanner.nextLine();
        String[] burTime = line3.split(" ");
        String[] processes = line1.split(" ");
        List<Integer> arrivalTime1 = new ArrayList<>();
        List<Integer> burstTime1 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arrivalTime1.add(Integer.valueOf(arrTime[i]));
        }
        for (int i = 0; i < n; i++) {
            burstTime1.add(Integer.valueOf(burTime[i]));
        }



        Integer[] arrivalTime = new Integer[arrivalTime1.size()];
        arrivalTime = arrivalTime1.toArray(arrivalTime);

        Integer[] burstTime = new Integer[burstTime1.size()];
        burstTime = burstTime1.toArray(burstTime);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //First Come First Serve
        int tmp;
        String sTmp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (arrivalTime[j] > arrivalTime[j + 1]) {
                    tmp = arrivalTime[j];
                    arrivalTime[j] = arrivalTime[j + 1];
                    arrivalTime[j + 1] = tmp;
                    tmp = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = tmp;
                    sTmp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = sTmp;
                } else if (arrivalTime[j] == arrivalTime[j + 1]) {
                    if (processes[j].charAt(0) > processes[j + 1].charAt(0)) {
                        tmp = arrivalTime[j];
                        arrivalTime[j] = arrivalTime[j + 1];
                        arrivalTime[j + 1] = tmp;
                        tmp = burstTime[j];
                        burstTime[j] = burstTime[j + 1];
                        burstTime[j + 1] = tmp;
                        sTmp = processes[j];
                        processes[j] = processes[j + 1];
                        processes[j + 1] = sTmp;
                    } else if (processes[j].charAt(0) == processes[j + 1].charAt(0)) {
                        if (processes[j].charAt(1) > processes[j + 1].charAt(1)) {
                            tmp = arrivalTime[j];
                            arrivalTime[j] = arrivalTime[j + 1];
                            arrivalTime[j + 1] = tmp;
                            tmp = burstTime[j];
                            burstTime[j] = burstTime[j + 1];
                            burstTime[j + 1] = tmp;
                            sTmp = processes[j];
                            processes[j] = processes[j + 1];
                            processes[j + 1] = sTmp;
                        }
                    }
                }
            }
        }

        int time = 0;
        for (int j = 0; j < n; j++) {
            if (time >= arrivalTime[j]) {
                time += burstTime[j];
                for (int i = 0; i < burstTime[j]; i++) {
                    System.out.print(processes[j]);
                }
            }
        }
        System.out.print("\n");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        //Shortest Job First

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (processes[j].charAt(0) > processes[j + 1].charAt(0)) {
                    tmp = arrivalTime[j];
                    arrivalTime[j] = arrivalTime[j + 1];
                    arrivalTime[j + 1] = tmp;
                    tmp = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = tmp;
                    sTmp = processes[j];
                    processes[j] = processes[j + 1];
                    processes[j + 1] = sTmp;
                } else if (processes[j].charAt(0) == processes[j + 1].charAt(0)) {
                        if (processes[j].charAt(1) > processes[j + 1].charAt(1)) {
                            tmp = arrivalTime[j];
                            arrivalTime[j] = arrivalTime[j + 1];
                            arrivalTime[j + 1] = tmp;
                            tmp = burstTime[j];
                            burstTime[j] = burstTime[j + 1];
                            burstTime[j + 1] = tmp;
                            sTmp = processes[j];
                            processes[j] = processes[j + 1];
                            processes[j + 1] = sTmp;
                        }

                }

            }

        }

        boolean completed[]= new boolean[n];
        int total = 0;
        int cTime = 0;
        while (true){
            int min = 1000000;
            int current = n;
            if(total == n){
                break;
            }

            for (int i=0; i < n; i++){
                if((arrivalTime[i]<=cTime) && (!completed[i])&&(burstTime[i]<min)){
                    min = burstTime[i];
                    current = i;
                }
            }

            if(current==n){
                cTime++;
                System.out.print(" ");
            }else{
                cTime += burstTime[current];
                completed[current] = true;
                total++;
                for(int k=0; k<burstTime[current]; k++){
                    System.out.print(processes[current]);
                }
            }



        }
        System.out.print("\n");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Shortest Remaining Time

        boolean completed1[] = new boolean[n];
        total=0;
        cTime=0;

        while (true){
            int min=100000;
            int current=n;

            if(total==n){
                break;
            }
            for (int i=n-1; i> -1; i--){
                if((arrivalTime[i]<=cTime) && (!completed1[i]) && (burstTime[i]<=min)){
                    min = burstTime[i];
                    current = i;
                }
            }
            if (current==n){
                cTime++;
                System.out.print(" ");
            }else{
                cTime++;
                burstTime[current]--;
                System.out.print(processes[current]);
                if(burstTime[current] == 0){
                    completed1[current] = true;
                    total++;
                }
            }

        }
        System.out.print("\n");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //Round Robin

        Integer[] burstTime2 = new Integer[n];
        burstTime2 = burstTime1.toArray(burstTime2);
        Integer[] arrivaltime2 = new Integer[n];
        arrivaltime2 = arrivalTime1.toArray(arrivaltime2);
        String[] processes2 = line1.split(" ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (arrivaltime2[j] > arrivaltime2[j + 1]) {
                    tmp = arrivaltime2[j];
                    arrivaltime2[j] = arrivaltime2[j + 1];
                    arrivaltime2[j + 1] = tmp;
                    tmp = burstTime2[j];
                    burstTime2[j] = burstTime2[j + 1];
                    burstTime2[j + 1] = tmp;
                    sTmp = processes2[j];
                    processes2[j] = processes2[j + 1];
                    processes2[j + 1] = sTmp;
                } else if (arrivaltime2[j] == arrivaltime2[j + 1]) {
                    if (processes2[j].charAt(0) > processes2[j + 1].charAt(0)) {
                        tmp = arrivaltime2[j];
                        arrivaltime2[j] = arrivaltime2[j + 1];
                        arrivaltime2[j + 1] = tmp;
                        tmp = burstTime2[j];
                        burstTime2[j] = burstTime2[j + 1];
                        burstTime2[j + 1] = tmp;
                        sTmp = processes2[j];
                        processes2[j] = processes2[j + 1];
                        processes2[j + 1] = sTmp;
                    } else if (processes2[j].charAt(0) == processes2[j + 1].charAt(0)) {
                        if (processes2[j].charAt(1) > processes2[j + 1].charAt(1)) {
                            tmp = arrivaltime2[j];
                            arrivaltime2[j] = arrivaltime2[j + 1];
                            arrivaltime2[j + 1] = tmp;
                            tmp = burstTime[j];
                            burstTime2[j] = burstTime2[j + 1];
                            burstTime2[j + 1] = tmp;
                            sTmp = processes2[j];
                            processes2[j] = processes2[j + 1];
                            processes2[j + 1] = sTmp;
                        }
                    }
                }
            }
        }


        roundRobin(processes2,arrivaltime2,burstTime2,n,2);
        System.out.print("\n");
        roundRobin(processes2,arrivaltime2,burstTime2,n,4);
        System.out.print("\n");
        roundRobin(processes2,arrivaltime2,burstTime2,n,8);
        System.out.print("\n");
        roundRobin(processes2,arrivaltime2,burstTime2,n,20);

    }


    public static void roundRobin(String[] processes, Integer[] arrival, Integer[]burst, int n, int quantum){
        Integer[] remainBurst = new Integer[n];
        int total=0;
        int cTime=0;

        for (int i=0; i<n ; i++){
            remainBurst[i] = burst[i];
        }

        while (true){
            if(total==n){
                break;
            }

            for (int i=0; i<n; i++){
                if((remainBurst[i]>0) && (cTime>=arrival[i])){
                    if(remainBurst[i] > quantum){
                        remainBurst[i] -= quantum;
                        cTime += quantum;
                        for(int j=0 ; j< quantum ; j++){
                            System.out.print(processes[i]);
                        }
                    }else{
                        for(int j=0; j< remainBurst[i]; j++){
                            System.out.print(processes[i]);
                        }
                        cTime += remainBurst[i];
                        remainBurst[i]=0;
                        total++;
                    }
                }else if(cTime < arrival[i]){
                    System.out.print(" ");
                    cTime++;
                }
            }
        }
    }
}
