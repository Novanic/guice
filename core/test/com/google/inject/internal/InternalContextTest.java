package com.google.inject.internal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class InternalContextTest {

    private static final InjectorImpl.InjectorOptions DUMMY_INJECTOR_OPTIONS =
            new InjectorImpl.InjectorOptions(null, false, false, false, false);

    @Test
    public void testGetConstructionContext() {
        try(InternalContext context = new InternalContext(DUMMY_INJECTOR_OPTIONS, new Object[1])) {

            ConstructionContext<Object> constructionContext = context.getConstructionContext("TEST");
            assertNotNull(constructionContext);

            assertSame(constructionContext, context.getConstructionContext("TEST"));
            assertNotSame(constructionContext, context.getConstructionContext("TEST2"));
        }
    }
}
