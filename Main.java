import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        int frame;
        String algorithm;
        String line;
        int num=0;
        String line11 = scanner.nextLine();
        int pageNums;

        if(isNum(line11)){
            pageNums = Integer.valueOf(line11);
        }else{
            pageNums=0;
            num++;
        }

        line = scanner.nextLine();
        String[] line1 = line.split(" ");
        String[] line2 = scanner.nextLine().split(" ");
        if(line2.length==2 && isNum(line2[0])){
            frame = Integer.valueOf(line2[0]);
            algorithm = line2[1];
        }else{
            frame=0;
            algorithm="null";
        }

//        if(scanner.hasNext()){
//            num++;
//        }



        for (String s:line1){
            if(!isNum(s)){
                num++;
            }
        }


        if(!algorithm.equals("FIFO") && !algorithm.equals("LRU")){
            System.out.println("error");
        }else{
            if(frame!= 3 && frame!=4 && frame!=5){
                System.out.println("error");
            }else if(pageNums!=line1.length){
                System.out.println("error");

            }else if(num!=0 ){
                System.out.println("error");
            }

            else{


                Integer[] pages1 = new Integer[pageNums];
                int z=0;
                while (z< pageNums){
                   pages1[z] = Integer.valueOf(line1[z]);
                   z++;
                }
                if(algorithm.equals("FIFO")){

//                    FileWriter writer = new FileWriter(frame+"FIFO"+fileName);
//                    BufferedWriter buffer = new BufferedWriter(writer);

                    int faults =0;
                    LinkedList<Integer> pages2 = new LinkedList<>();
                    LinkedList<Integer> temp = new LinkedList<>();
                    int size=0;
                    int j=1;
                    pages2.add(pages1[0]);
                    temp.add(pages1[0]);

                    int upp = frame;
                    if(pageNums<frame){
                        upp=pageNums;
                    }

                    while (pages2.size()<= upp){
                        System.out.print( pages1[j-1]+" [");
                        //buffer.write(pages1[j-1]+"  [");
                        for(int i=0; i<pages2.size(); i++){
                            if(i == pages2.size()-1){
                                System.out.print(pages2.get(i));
                               // buffer.write(pages2.get(i)+"");
                            }else{
                                System.out.print(pages2.get(i)+" ");
                               // buffer.write(pages2.get(i)+" ");
                            }
                        }
                        System.out.print("]");
                        //buffer.write("]");
                        //buffer.write("\n");

                        if(size!=pages2.size()){
                            System.out.print(" page fault");
                            faults++;
                            size++;
                        }

                        if(pages2.size() == upp || j==upp){
                            System.out.print("\n");
                            break;
                        }


                        if(!pages2.contains(pages1[j])){

                            pages2.add(pages1[j]);

                        }
                        System.out.print("\n");
                        if(temp.size() < upp && !temp.contains(pages1[j])){
                            temp.add(pages1[j]);
                        }else if(temp.size() == upp && !temp.contains(pages1[j])){
                            temp.removeFirst();
                            temp.addLast(pages1[j]);
                        }

                        j++;
                    }


                    int indx;
                    while (j< pageNums){
                        if(!pages2.contains(pages1[j])){
                            indx = pages2.indexOf(temp.get(0));
                            pages2.remove(indx);
                            pages2.add(indx,pages1[j]);
                            System.out.print( pages1[j]+" [");
                            //buffer.write( pages1[j]+"  [");
                            for(int i=0; i<pages2.size(); i++){
                                if(i == pages2.size()-1){
                                    System.out.print(pages2.get(i));
                                    //buffer.write(pages2.get(i)+"");
                                }else{
                                    System.out.print(pages2.get(i)+" ");
                                    //buffer.write(pages2.get(i)+" ");
                                }
                            }
                            System.out.print("] page fault");
                            //buffer.write("] page fault");
                            faults++;
                        }else{
                            System.out.print( pages1[j]+" [");
                            //buffer.write(pages1[j]+"  [");
                            for(int i=0; i<pages2.size(); i++){
                                if(i == pages2.size()-1){
                                    System.out.print(pages2.get(i));
                                    //buffer.write(pages2.get(i)+"");
                                }else{
                                    System.out.print(pages2.get(i)+" ");
                                    //buffer.write(pages2.get(i)+" ");
                                }
                            }
                            System.out.print("]");
                            //buffer.write("]");
                        }

                        if(!temp.contains(pages1[j])){
                            temp.removeFirst();
                            temp.addLast(pages1[j]);
                        }

                        System.out.print("\n");
                        //buffer.write("\n");
                        j++;
                    }

                    //System.out.println("");
                    //buffer.write("\n");
                    System.out.println("total number of page faults is "+ faults);
                    //buffer.write("Total number of page faults is "+ faults);
                    //buffer.close();


                }else if(algorithm.equals("LRU")){


                    //FileWriter fileWriter = new FileWriter(frame+"LRU"+fileName);
                    int faults =0;
                    LinkedList<Integer> temp = new LinkedList<>();
                    LinkedList<Integer> pages2 = new LinkedList<>();
                    int j=1;
                    pages2.add(pages1[0]);
                    temp.add(pages1[0]);
                    int size =0;
                    while (pages2.size()<= frame){
                        System.out.print( pages1[j-1]+" [");
                        //fileWriter.write( pages1[j-1]+" [");

                        for(int i=0; i<pages2.size(); i++){
                            if(i == pages2.size()-1){
                                System.out.print(pages2.get(i));
                                //fileWriter.write(pages2.get(i)+"");
                            }else{
                                System.out.print(pages2.get(i)+" ");
                                //fileWriter.write(pages2.get(i)+" ");
                            }
                        }
                        System.out.print("]");
                        //fileWriter.write("]");

                        //fileWriter.write("\n");

                        if (size!=pages2.size()){
                            System.out.print(" page fault");
                            faults++;
                            size++;
                        }

                        if(pages2.size() == frame){
                            System.out.print("\n");
                            break;
                        }
                        if(!pages2.contains(pages1[j])){
                            pages2.add(pages1[j]);
                        }

                        System.out.print("\n");
                        if(temp.size() < frame && !temp.contains(pages1[j])){
                            temp.add(pages1[j]);
                        }else if((temp.size() <= frame) && temp.contains(pages1[j])){
                            if( temp.get(0).equals(pages1[j])){
                                temp.removeFirst();
                                temp.addLast(pages1[j]);
                            }else{
                                int tmp1;
                                for(int k=temp.indexOf(pages1[j]);k<temp.size()-1; k++ ){
                                    tmp1 = temp.get(k);
                                    temp.set(k,temp.get(k+1));
                                    temp.set(k+1,tmp1);

                                }
                            }
                        }else{
                            temp.removeFirst();
                            temp.addLast(pages1[j]);
                        }

                        j++;
                    }

                    int indx;
                    while (j< pageNums){

                        if(!pages2.contains(pages1[j])){
                            indx = pages2.indexOf(temp.get(0));
                            pages2.remove(indx);
                            pages2.add(indx,pages1[j]);
                            System.out.print( pages1[j]+" [");
                            //fileWriter.write(pages1[j]+"  [");
                            for(int i=0; i<pages2.size(); i++){
                                if(i == pages2.size()-1){
                                    System.out.print(pages2.get(i));
                                    //fileWriter.write(pages2.get(i)+"");
                                }else{
                                    System.out.print(pages2.get(i)+" ");
                                    //fileWriter.write(pages2.get(i)+" ");
                                }
                            }
                            System.out.print("] page fault");
                            //fileWriter.write("] page fault");
                            faults++;
                        }else{
                            System.out.print( pages1[j]+" [");
                            //fileWriter.write(pages1[j]+"  [");
                            for(int i=0; i<pages2.size(); i++){
                                if(i == pages2.size()-1){
                                    System.out.print(pages2.get(i));
                                    //fileWriter.write(pages2.get(i)+"");
                                }else{
                                    System.out.print(pages2.get(i)+" ");
                                    //fileWriter.write(pages2.get(i)+" ");
                                }
                            }
                            System.out.print("]");
                            //fileWriter.write("]");
                        }
                        if(!temp.contains(pages1[j]) || temp.get(0).equals(pages1[j])){
                            temp.removeFirst();
                            temp.addLast(pages1[j]);
                        }else{
                            int tmp1;
                            for(int k=temp.indexOf(pages1[j]);k<frame-1; k++ ){
                                tmp1 = temp.get(k);
                                temp.set(k,temp.get(k+1));
                                temp.set(k+1,tmp1);

                            }
                        }
                        System.out.print("\n");
                        //fileWriter.write("\n");
                        j++;
                    }

                    //System.out.println("");
                    //fileWriter.write("\n");
                    System.out.println("total number of page faults is "+ faults);
//                    fileWriter.write("Total number of page faults is "+ faults);
//                    fileWriter.close();


                }

            }
        }

    }

    public static boolean isNum(String strNum) {
        boolean ret = true;
        try {

            Double.parseDouble(strNum);

        }catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }
}
