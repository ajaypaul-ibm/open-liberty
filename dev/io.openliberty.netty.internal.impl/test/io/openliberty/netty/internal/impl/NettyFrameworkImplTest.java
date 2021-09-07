/*******************************************************************************
 * Copyright (c) 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package io.openliberty.netty.internal.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.openliberty.netty.internal.BootstrapExtended;
import io.openliberty.netty.internal.ServerBootstrapExtended;
import io.openliberty.netty.internal.tcp.TCPChannelInitializerImpl;
import io.openliberty.netty.internal.tcp.TCPConfigurationImpl;
import test.common.SharedOutputManager;

/**
 * Basic unit tests for {@link NettyFrameworkImpl}
 */
public class NettyFrameworkImplTest {

    private static SharedOutputManager outputMgr = SharedOutputManager.getInstance();
    private List<Channel> testChannels = null;
    NettyFrameworkImpl framework = null;
    private String host = "localhost";
    private int port = 9080;
    Map<String, Object> options;

    @Rule
    public TestRule rule = outputMgr;

    @Rule
    public TestName name = new TestName();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        outputMgr.captureStreams();
    }

    @Before
    public void setup() {
        testChannels = new ArrayList<Channel>();
        framework = new NettyFrameworkImpl();
        framework.activate(null, null);
        options = new HashMap<String, Object>();
        options.put(TCPConfigurationImpl.PORT_OPEN_RETRIES, 10);
    }

    @After
    public void tearDown() throws Exception {
        framework.deactivate(null, null);
        framework = null;
        testChannels = null;
        outputMgr.resetStreams();
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        outputMgr.dumpStreams();
        outputMgr.restoreStreams();
    }

    /**
     * Verify that a TCP boostrap can be created as expected
     * 
     * @throws Exception
     */
    @Test
    public void testCreateTCPBootstrap() throws Exception {
        options.put("soReuseAddr", "true");
        ServerBootstrapExtended bootstrap =  framework.createTCPBootstrap(options);
        Assert.assertTrue((boolean) bootstrap.config().options().get(ChannelOption.SO_REUSEADDR));
        Assert.assertTrue(bootstrap.getBaseInitializer() instanceof TCPChannelInitializerImpl);
        framework.deactivate(null, null);
    }

    /**
     * Start listening on a TCP channel 
     * 
     * @throws Exception
     */
    @Test
    public void testTCPStart() throws Exception {
        ServerBootstrapExtended bootstrap =  framework.createTCPBootstrap(options);
        bootstrap.childHandler(bootstrap.getBaseInitializer());
        final CountDownLatch latch = new CountDownLatch(1);
        framework.start(bootstrap, host, port, future -> {
            if (future.isSuccess()) {
                testChannels.add(future.channel());
                latch.countDown();
            } else {
                Assert.fail("framework failed to start");
                latch.countDown();
            }
        });
        framework.setServerStarted(null);
        latch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue(testChannels.size() == 1);
        Assert.assertTrue(testChannels.get(0).isActive());
        Assert.assertTrue(framework.getActiveChannels().contains(testChannels.get(0)));
    }

    /**
     * Start listening on a UDP channel 
     * 
     * @throws Exception
     */
    @Test
    public void testUDPStart() throws Exception {
        BootstrapExtended bootstrap =  framework.createUDPBootstrap(options);
        bootstrap.handler(new SimpleChannelInboundHandler<ByteBuf>() {
            @Override
            protected void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
                // do nothing
            }
        });
        final CountDownLatch latch = new CountDownLatch(1);
        framework.start(bootstrap, host, port, future -> {
            if (future.isSuccess()) {
                testChannels.add(future.channel());
                latch.countDown();
            } else {
                Assert.fail("framework failed to start");
                latch.countDown();
            }
        });
        framework.setServerStarted(null);
        latch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue(testChannels.size() == 1);
        Assert.assertTrue(testChannels.get(0).isActive());
        Assert.assertTrue(framework.getActiveChannels().contains(testChannels.get(0)));
    }

    /**
     * Start listening on a TCP channel and verify stop()
     * 
     * @throws Exception
     */
    @Test
    public void testAsyncStop() throws Exception {
        ServerBootstrapExtended bootstrap =  framework.createTCPBootstrap(options);
        bootstrap.childHandler(bootstrap.getBaseInitializer());
        final CountDownLatch latch = new CountDownLatch(1);
        framework.start(bootstrap, host, port, future -> {
            if (future.isSuccess()) {
                testChannels.add(future.channel());
                latch.countDown();
            } else {
                Assert.fail("framework failed to start");
                latch.countDown();
            }
        });
        framework.setServerStarted(null);
        latch.await(10, TimeUnit.SECONDS);
        Assert.assertTrue(testChannels.get(0) != null);
        Assert.assertTrue(testChannels.get(0).isActive());
        Assert.assertEquals(1, framework.getActiveChannels().size());
        Assert.assertTrue(framework.getActiveChannels().contains(testChannels.get(0)));
        ChannelFuture future = framework.stop(testChannels.get(0));
        future.await(10, TimeUnit.SECONDS);
        Assert.assertFalse(testChannels.get(0).isActive());
        Assert.assertTrue(future.isSuccess());
        Assert.assertFalse(framework.getActiveChannels().contains(testChannels.get(0)));
        Assert.assertTrue(framework.getActiveChannels().isEmpty());

    }

    /**
     * Start listening on a TCP channel and verify stop()
     * 
     * @throws Exception
     */
    @Test
    public void testSyncStop() throws Exception {
        ServerBootstrapExtended bootstrap =  framework.createTCPBootstrap(options);
        bootstrap.childHandler(bootstrap.getBaseInitializer());
        final CountDownLatch latch = new CountDownLatch(1);
        framework.start(bootstrap, host, port, future -> {
            if (future.isSuccess()) {
                testChannels.add(future.channel());
                latch.countDown();
            } else {
                Assert.fail("framework failed to start");
                latch.countDown();
            }
        });
        framework.setServerStarted(null);
        latch.await(15, TimeUnit.SECONDS);
        Assert.assertTrue(testChannels.get(0) != null);
        Assert.assertTrue(testChannels.get(0).isActive());
        framework.stop(testChannels.get(0), 10000);
        Assert.assertFalse(testChannels.get(0).isActive());
        Assert.assertFalse(framework.getActiveChannels().contains(testChannels.get(0)));
        Assert.assertTrue(framework.getActiveChannels().isEmpty());
    }

    /**
     * Start listening on three TCP channel, then tear them down individually
     * 
     * @throws Exception
     */
    @Test
    public void testMultipleChannels() throws Exception {
        int secondPort = port + 1;
        int thirdPort = port + 2;
        ServerBootstrapExtended bootstrap =  framework.createTCPBootstrap(options);
        bootstrap.childHandler(bootstrap.getBaseInitializer());
        final CountDownLatch latch = new CountDownLatch(3);
        framework.start(bootstrap, host, port, future -> {
            if (future.isSuccess()) {
                testChannels.add(future.channel());
                latch.countDown();
            } else {
                Assert.fail("framework failed to start");
                latch.countDown();
            }
        });
        framework.start(bootstrap, host, secondPort, future -> {
            if (future.isSuccess()) {
                testChannels.add(future.channel());
                latch.countDown();
            } else {
                Assert.fail("framework failed to start");
                latch.countDown();
            }
        });
        framework.start(bootstrap, host, thirdPort, future -> {
            if (future.isSuccess()) {
                testChannels.add(future.channel());
                latch.countDown();
            } else {
                Assert.fail("framework failed to start");
                latch.countDown();
            }
        });
        framework.setServerStarted(null);
        latch.await(15, TimeUnit.SECONDS);
        Assert.assertTrue(testChannels.get(0) != null);
        Assert.assertTrue(testChannels.get(0).isActive());
        Assert.assertEquals(3, testChannels.size());
        Assert.assertEquals(3, framework.getActiveChannels().size());
        framework.stop(testChannels.get(0), 10000); // sync stop
        Assert.assertFalse(testChannels.get(0).isActive());
        Assert.assertFalse(framework.getActiveChannels().contains(testChannels.remove(0)));
        Assert.assertEquals(2, framework.getActiveChannels().size());
        framework.stop(testChannels.get(0), 10000);
        Assert.assertFalse(testChannels.get(0).isActive());
        Assert.assertFalse(framework.getActiveChannels().contains(testChannels.remove(0)));
        Assert.assertEquals(1, framework.getActiveChannels().size());
        framework.stop(testChannels.get(0), 10000);
        Assert.assertFalse(testChannels.get(0).isActive());
        Assert.assertFalse(framework.getActiveChannels().contains(testChannels.remove(0)));
        Assert.assertTrue(framework.getActiveChannels().isEmpty());
    }
}
