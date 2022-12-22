package foodapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Position {

    int index;
    double probability;

    Position(int index, double probability){
        this.index = index;
        this.probability = probability;
    }
}

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        int T, M , lastIndex, n,loopCount = 0,K;
        long N;
        double P;
        T = sc.nextInt();
        while(T > 0){
            N = sc.nextInt();
            M = sc.nextInt();
            P = sc.nextInt();
            P = P/(double)100;
            ArrayList<Position> positionsOfStrangeLandians = new ArrayList<Position>();
            for(int m = 0; m < M; m ++){
                positionsOfStrangeLandians.add(new Position(sc.nextInt(),1));
            }

            Collections.sort(positionsOfStrangeLandians, new Comparator<Position>(){
                @Override
                public int compare(Position p1, Position p2){
                    return p1.index - p2.index;
                }
            });

            lastIndex = M;  loopCount = 0;
            do{
                loopCount ++;
                n = 0;
                for(int m = 0; m < M; m++){

                    int IndexOfStrangeLandian = positionsOfStrangeLandians.get(m).index;
                    double probOfStrangeLandianAtIndex = positionsOfStrangeLandians.get(m).probability;

                    //odd case
                    if(IndexOfStrangeLandian % 2 == 0){
                        positionsOfStrangeLandians.set(n++, new Position(IndexOfStrangeLandian/2, probOfStrangeLandianAtIndex * P));
                    }
                    else{//even case
                        if(m + 1 < M && positionsOfStrangeLandians.get(m + 1).index == IndexOfStrangeLandian + 1){
                            double probEvenProb = positionsOfStrangeLandians.get(m + 1).probability;
                            double probAtNewPos_n = P*(probOfStrangeLandianAtIndex*(1 - probEvenProb) + probEvenProb*(1-probOfStrangeLandianAtIndex)) +
                                    probOfStrangeLandianAtIndex * probEvenProb;
                            positionsOfStrangeLandians.set(n++, new Position((IndexOfStrangeLandian + 1)/2, probAtNewPos_n ));
                            m++;
                        }
                        else{
                            positionsOfStrangeLandians.set(n++,new Position((IndexOfStrangeLandian + 1)/2, probOfStrangeLandianAtIndex * P ));
                        }

                    }
                }
                M = n;
            }while(n > 1);

            //to add some code here : Multiplying the answer with P,(the number of rounds left) times
            //ans = ans * P^No.of Rounds Left(K - loopCount)
            K = (int) (Math.log(N)/Math.log(2));
            System.out.println((positionsOfStrangeLandians.get(0).probability * Math.pow(P,K - loopCount))*100);

            T --;
        }

    }

}
