/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bichinho;

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

    private String nome;
    private String comida;
    private String idade;
    private Integer casanco;
    private Integer level;
    private Random r = new Random(System.currentTimeMillis());

    private MemcachedClient mc;

    public MeuBichinho(String nome) {

        try {
            mc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
            mc.add("nome", 0, nome);
            mc.set("idade", 0, "0");
            mc.set("level", 0, "0");
            mc.set("comida", 0, "3");
            mc.set("saude", 0, "3");
            mc.set("cansaco", 0, "0");
            
            idade();
            funcoesVitais();

        } catch (IOException ex) {
            Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getNome() {
        return ((String) mc.get("nome"));
    }

    private void idade() {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    
                    mc.incr("idade", 1);
                    System.out.println("Tenho " + mc.get("idade") + " nanoAnos de idade!");
                    if (Integer.parseInt((String) mc.get("idade")) % 10 == 0) {
                        mc.incr("level", 1);
                        System.out.println("UAAUUUUU!!! Estou no Level " + mc.get("level"));
                    }
                    Thread.sleep(20000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        t.start();
    }


     public void verificarComida() {
        Thread t = new Thread(() -> {
            while (true) {
                if (Integer.parseInt((String) mc.get("comida")) <= 2 && Integer.parseInt((String) mc.get("comida")) > 0) {
                    System.out.println("Estou com fome!");
                }else if(Integer.parseInt((String) mc.get("comida")) <= 0){
                    morrer();
                }else if(Integer.parseInt((String) mc.get("comida")) >= 10){
                    morrer();
                    System.out.println(mc.get("nome") + " morreu por obesidade morbida!");
                }
//                    Thread.sleep(3000);
            }
        });

        t.start();
    }
    
    public void verificarCansaco() {
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    if (Integer.parseInt((String) mc.get("cansaco")) >= 5 && Integer.parseInt((String) mc.get("cansaco")) < 10) {
                        System.out.println("Preciso descansar!");
                    }else if(Integer.parseInt((String) mc.get("cansaco")) >= 10)
                        morrer();
                    Thread.sleep(3000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        t.start();
    } 
     
    public void verificarSaude() {
        Thread t = new Thread(() -> {
            while (true) {
                if (Integer.parseInt((String) mc.get("saude")) <= 2 && Integer.parseInt((String) mc.get("saude")) > 0) {
                    System.out.println("Estou doente!");
                }else if(Integer.parseInt((String) mc.get("saude")) <= 0){
                    morrer();
                }
//                    Thread.sleep(3000);
            }
        });

        t.start();
    }
    
    private void funcoesVitais(){
        Thread t = new Thread(() -> {
            try {
                while (true) {
                    
                    mc.decr("comida", 2);                  
                    mc.decr("saude", 2);
                    mc.incr("cansaco", 2);
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
        mc.incr("comida", 1);
        if(r.nextBoolean()){
            System.out.println("Aiiiii! A comida nao me fez bem!");
            mc.decr("saude", 1);
        }
    }

    @Override
    public void dormir() {
        mc.decr("cansaco", 1);
        mc.decr("comida", 1);
    }

    @Override
    public void vacinar() {
        mc.incr("saude", 1);
    }

    @Override
    public void brincar() {
        mc.decr("comida", 1);
        mc.incr("cansaco", 1);
        if(r.nextBoolean()){
            System.out.println("Aiiiii! Me machuquei!");
            mc.decr("saude", 1);
        }
    }

    public void morrer() {
        
        System.out.println("Que pena, " + mc.get("nome") + " morreu!");
        mc.flush();
        mc.shutdown();
        System.exit(1);
        
    }

}
