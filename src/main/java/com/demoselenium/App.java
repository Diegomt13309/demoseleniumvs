package com.demoselenium;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Random rand = new Random();

        List<Integer> list1 = new ArrayList<>();

        for(int i =0; i < 10; i++){
            list1.add(rand.nextInt(101));
        }

        for(int i =0; i<list1.size(); i++){
            if(i==0)
                System.out.println("Primera Lista");
            System.out.println(list1.get(i));
        }

        System.out.println(list1 = list1.stream().sorted().toList());
        System.out.println("Ya esta sorteada?");

      
        list1.stream().forEach(e -> {
            System.out.println("Lista -> "+ e*11);
        });
      
        
        
        
    }
}
