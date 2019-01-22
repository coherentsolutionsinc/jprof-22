package com.issoft.meetup.app2.grpc

import io.grpc.Server
import io.grpc.ServerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GRPCServer @Autowired constructor(
        echoHandler: GRPCEchoHandler) {

    val server: Server = ServerBuilder.forPort(8888)
            .addService(echoHandler)
            .build()
            .start()

    init {
        println("Server started!")
    }
}