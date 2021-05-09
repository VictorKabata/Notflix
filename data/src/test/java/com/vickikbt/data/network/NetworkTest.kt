package com.vickikbt.data.network

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

class NetworkTest {

    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server.start(8080)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

}