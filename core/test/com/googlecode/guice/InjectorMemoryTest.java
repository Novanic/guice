package com.googlecode.guice;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import jakarta.inject.Provider;
import jakarta.inject.Singleton;

public class InjectorMemoryTest {

    private final Injector injector;
    private final Provider<GuiceDummyInterface> provider;

    public InjectorMemoryTest() {
        injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(GuiceDummyInterface.class).to(GuiceDummy.class);
            }
        });
        provider = injector.getProvider(GuiceDummyInterface.class);
    }

    @Test
    public void testInjector() throws Exception {
        // Initial access to ignore the first access while profiling
        assertSame(injector.getInstance(GuiceDummyInterface.class), injector.getInstance(GuiceDummyInterface.class));

//    //Enable to profile the test
//    System.out.println("Injector created");
//    Thread.sleep(30000L); //time to connect to the profiler
//    System.out.println("Test started");

        GuiceDummyInterface object = null;
        for (int i = 0; i < 10000; i++) {
            object = injector.getInstance(GuiceDummyInterface.class);
        }

//    //Enable to profile the test
//    System.out.println("Test finished");
//    Thread.sleep(70000L); // time to monitor the memory with the profiler

        assertTrue(object instanceof GuiceDummy);
    }

    @Test
    public void testProvider() throws Exception {
        // Initial access to ignore the first access while profiling
        assertSame(provider.get(), provider.get());

//    //Enable to profile the test
//    System.out.println("Injector created");
//    Thread.sleep(30000L); // time to connect to the profiler
//    System.out.println("Test started");

        GuiceDummyInterface object = null;
        for (int i = 0; i < 10000; i++) {
            object = provider.get();
        }

//    //Enable to profile the test
//    System.out.println("Test finished");
//    Thread.sleep(70000L); // time to monitor the memory with the profiler

        assertTrue(object instanceof GuiceDummy);
    }

    public interface GuiceDummyInterface {
    }

    @Singleton
    public static class GuiceDummy implements GuiceDummyInterface {
    }
}