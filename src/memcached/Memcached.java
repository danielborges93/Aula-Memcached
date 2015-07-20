/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import net.spy.memcached.MemcachedClient;

/**
 *
 * @author danielborges93
 */
public class Memcached {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
	
	try {
	    // Connecting to Memcached server on localhost
	    MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("192.168.25.89", 11211));
	    System.out.println("Connection to server sucessful.");

	    Future fo = mcc.set("tutorialspoint", 900, "Free Education");

	    // print status of set method
	    System.out.println("set status:" + fo.get());

	    // retrieve and check the value from cache
	    System.out.println("tutorialspoint value in cache - " + mcc.get("tutorialspoint"));

	    System.out.println("a = " + mcc.get("a"));

	    // Shutdowns the memcached client
	    mcc.shutdown();

	} catch (Exception ex) {
	    System.out.println(ex.getMessage());
	}

    }

}
