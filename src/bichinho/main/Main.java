/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bichinho.main;

import bichinho.virtual.MeuBichinho;
import java.util.Scanner;

/**
 *
 * @author JárioJosé
 */
public class Main {
     public static void main(String[] args) {

        System.out.println("---\t---\tBem Vindo ao Bichinho Virtual NoSQL\t---\t---");
        System.out.println("---\t---\t-----------------------------------\t---\t---");
        System.out.print("---\t---\tDigite um nome para o seu bichinho: ");

        Scanner sc1 = new Scanner(System.in);
        MeuBichinho bichinho = new MeuBichinho(sc1.next());
        System.out.println("---\t---\t-----------------------------------\t---\t---");
        System.out.println("---\t---\t-----------------------------------\t---\t---");
        System.out.println("---\t---\t-----------------------------------\t---\t---");
        System.out.println("---\t---\tBem Vindo ao Bichinho Virtual NoSQL\t---\t---");
        System.out.println("---\t---\t-----------------------------------\t---\t---");
        System.out.println("---\t---\t(C) Comer                          \t---\t---");
        System.out.println("---\t---\t(B) Brincar                        \t---\t---");
        System.out.println("---\t---\t(D) Dormir                         \t---\t---");
        System.out.println("---\t---\t(V) Vacinar                        \t---\t---");
        System.out.println("---\t---\t-----------------------------------\t---\t---");
        System.out.println("---\t---\t(S) Status                         \t---\t---");
        System.out.println("---\t---\t(A) Sair                           \t---\t---");
        System.out.println("---\t---\t-----------------------------------\t---\t---");
        System.out.println("---\t---\t-----------------------------------\t---\t---");
        System.out.println("---\t---\t-----------------------------------\t---\t---");

        while (sc1.hasNext()) {
            System.out.println("---\t---\t-----------------------------------\t---\t---");
            System.out.println("---\t---\t-----------------------------------\t---\t---");
            System.out.println("---\t---\t-----------------------------------\t---\t---");
            System.out.println("---\t---\tBem Vindo ao Bichinho Virtual NoSQL\t---\t---");
            System.out.println("---\t---\t-----------------------------------\t---\t---");
            System.out.println("---\t---\t(C) Comer                          \t---\t---");
            System.out.println("---\t---\t(B) Brincar                        \t---\t---");
            System.out.println("---\t---\t(D) Dormir                         \t---\t---");
            System.out.println("---\t---\t(V) Vacinar                        \t---\t---");
            System.out.println("---\t---\t-----------------------------------\t---\t---");
            System.out.println("---\t---\t(S) Status                         \t---\t---");
            System.out.println("---\t---\t(A) Sair                           \t---\t---");
            System.out.println("---\t---\t-----------------------------------\t---\t---");
            System.out.println("---\t---\t-----------------------------------\t---\t---");
            System.out.println("---\t---\t-----------------------------------\t---\t---");

            switch (sc1.next().toUpperCase()) {

                case "C":
                    bichinho.comer();
                    break;
                case "B":
                    bichinho.brincar();
                    break;
                case "D":
                    bichinho.dormir();
                    break;
                case "V":
                    bichinho.vacinar();
                    break;
                case "S":
                    bichinho.status();
                    break;
                case "A":
                    System.out.println("Saiuuu!");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Opcao invalida!");
                    break;
            }

        }

    }
}
