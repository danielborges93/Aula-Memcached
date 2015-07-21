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

    private Random r = new Random(System.currentTimeMillis());

    private MemcachedClient mc;

    public MeuBichinho(String nome) {

	try {
	    mc = new MemcachedClient(new InetSocketAddress("192.168.25.143", 11211));
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

    public void morrer() {

	mc.flush();
	mc.shutdown();
	System.exit(1);

    }

    private void incrementarComida() {
	long comida = mc.incr("comida", 1);

	if (comida >= 10) {
	    System.out.println(getNome() + " morreu por obesidade morbida! :(");
	    morrer();
	} else if (comida <= 2) {
	    System.out.println("Estou com fome!");
	}
    }

    private void decrementarComida() {
	long comida = mc.decr("comida", 1);

	if (comida <= 0) {
	    System.out.println(getNome() + " morreu por falta de comida :(");
	    morrer();
	} else if (comida <= 2) {
	    System.out.println("Estou com fome!");
	}
    }

    private void incrementarSaude() {
	long saude = mc.decr("saude", 1);

	if (saude <= 0) {
	    System.out.println(getNome() + " morreu porque ficou doente :(");
	    morrer();
	} else if (saude <= 2) {
	    System.out.println("Estou doente!");
	}
    }

    private void decrementarSaude() {
	long saude = mc.decr("saude", 1);

	if (saude <= 2) {
	    System.out.println("Estou doente!");
	}
    }

    private void incrementarCansaco() {
	long cansaco = mc.incr("cansaco", 1);

	if (cansaco >= 10) {
	    System.out.println(getNome() + " morreu porque estava muito cansado :(");
	    morrer();
	} else if (cansaco >= 5) {
	    System.out.println("Preciso descansar!");
	}
    }

    private void decrementarCansaco() {
	long cansaco = mc.decr("cansaco", 1);

	if (cansaco >= 5) {
	    System.out.println("Preciso descansar!");
	}
    }

    private void incrementarIdade() {
	long idade = mc.incr("idade", 1);

	if (idade >= 15) {
	    System.out.println(getNome() + " morreu de velhice :(");
	    morrer();
	}

	if (idade % 3 == 0) {
	    incrementarLevel();
	}

	System.out.println("Tenho " + idade + " nanoAnos de idade!");
    }

    private void incrementarLevel() {
	long level = mc.incr("level", 1);

	System.out.println("UAAUUUUU!!! Estou no Level " + level);
    }

    private void idade() {
	Thread t = new Thread(() -> {
	    try {
		while (true) {

		    Thread.sleep(20000);
		    
		    incrementarIdade();

		}
	    } catch (InterruptedException ex) {
		Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
	    }
	});

	t.start();
    }

    private void funcoesVitais() {
	Thread t = new Thread(() -> {
	    try {
		while (true) {

		    Thread.sleep(15000);
		    
		    // Comida
		    System.out.println("comida");
		    decrementarComida();

		    // Saúde
		    System.out.println("saude");
		    decrementarSaude();

		    // Cansaço
		    System.out.println("cansaco");
		    incrementarCansaco();

		}

	    } catch (InterruptedException ex) {
		Logger.getLogger(MeuBichinho.class.getName()).log(Level.SEVERE, null, ex);
	    }
	});

	t.start();
    }

    @Override
    public void comer() {
	incrementarComida();

	if (r.nextBoolean()) {
	    System.out.println("Aiiiii! A comida nao me fez bem!");
	    decrementarSaude();
	}
    }

    @Override
    public void dormir() {
	decrementarCansaco();
	decrementarComida();
    }

    @Override
    public void vacinar() {
	incrementarSaude();
    }

    @Override
    public void brincar() {
	decrementarComida();
	incrementarCansaco();

	if (r.nextBoolean()) {
	    System.out.println("Aiiiii! Me machuquei!");
	    decrementarSaude();
	}
    }

}
