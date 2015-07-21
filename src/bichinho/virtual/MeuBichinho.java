/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bichinho.virtual;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.spy.memcached.MemcachedClient;

/**
 *
 * @author JárioJosé
 */
public class MeuBichinho implements Serializable, FuncoesVitais {

    private Random r = new Random(System.currentTimeMillis());

    private MemcachedClient mc;

    public MeuBichinho(String nome) {

            
            // realizar conexao
            
            //add nome

            //add idade 0
            //add level 0
            //add comida 5
            //add saude 5
            //add cansaco 0

            idade();
            verificarCansaco();
            verificarComida();
            verificarSaude();
            funcoesVitais();

    }

    public String getNome() {
        return ((String) "");
    }

    public String getLevel() {
        return ((String) "");
    }

    private void idade() {
        Thread t = new Thread(() -> {
            try {
                while (true) {

                    //Add incremento idade
                    //pegar idade
                    String id = null;
                    int idade = Integer.parseInt(id);

                    if (idade % 3 == 0) {
                        mc.incr("level", 1);
                        System.out.println("UAAUUUUU!!! Estou no Level " + getLevel());
                    }
                    if (idade > 15) {
                        System.out.println(getNome() + " morreu de velhice!");
                        morrer();
                    }
                    Thread.sleep(20000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        t.start();
    }

    private void verificarComida() {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    //Add incremento comida

                    //pegar comida
                    String c = null;
                    int comida = Integer.parseInt(c);

                    if (comida <= 2 && comida > 0) {
                        System.out.println("Estou com fome! " + comida + " de comida!");
                    } else if (comida <= 0) {
                        morrer();
                    } else if (comida >= 10) {
                        morrer();
                        System.out.println(getNome() + " morreu por obesidade morbida!");
                    }
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        t.start();
    }

    private void verificarCansaco() {
        Thread t = new Thread(() -> {
            try {
                while (true) {

                    //Add incremento cansaco
                    //pegar cansaco
                    String cs = null;
                    int cansaco = Integer.parseInt(cs);

                    if (cansaco >= 5 && cansaco < 10) {
                        System.out.println("Preciso descansar! " + cansaco + " de cansaco!");
                    } else if (cansaco >= 10) {
                        morrer();
                    }
                    Thread.sleep(3000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        t.start();
    }

    private void verificarSaude() {
        Thread t = new Thread(() -> {
            while (true) {

                 //Add incremento saude
                
                //pegar saude
                String s = null;
                int saude = Integer.parseInt(s);

                if (saude <= 2 && saude > 0) {
                    System.out.println("Estou doente!" + saude + " de saude!");
                } else if (saude <= 0) {
                    morrer();
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        t.start();
    }

    private void funcoesVitais() {
        Thread t = new Thread(() -> {
            try {
                while (true) {

                    
                    //decremento comida
                    //decremento saude
                    //decremento cansaco
                    
                    Thread.sleep(30000);
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        t.start();
    }

    @Override
    public void comer() {
        
        //incrementar comida
        if (r.nextBoolean()) {
            System.out.println("Aiiiii! A comida nao me fez bem!");
            
            //decrementar saude
        }
    }

    @Override
    public void dormir() {
        //decrementar cansaco
        //decrementar comida
    }

    @Override
    public void vacinar() {
        //incrementar saude
    }

    @Override
    public void brincar() {
        //decrementar comida
     
        //incrementar cansaco
        
        if (r.nextBoolean()) {
            System.out.println("Aiiiii! Me machuquei!");
            // decrementa saude
        }
    }

    public void status() {

        //pegar cada um dos campos
        
        System.out.println("Barra de Progresso:");
        System.out.println("Comida: ");
        System.out.println("Cansaco: ");
        System.out.println("Saude: ");
        System.out.println("Idade: ");
        System.out.println("Level: ");

    }

    public void morrer() {
        

        System.out.println("Que pena, " + getNome() + " morreu!");
        //ativar flush
        //ativar shutdown
        System.exit(1);

    }

}
