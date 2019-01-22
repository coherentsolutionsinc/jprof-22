package com.issoft.meetup.app2.grpc

import com.issoft.meetup.app2.service.EchoService
import com.issoft.meetup.proto.EchoServiceGrpc
import com.issoft.meetup.proto.Rpc
import io.grpc.stub.StreamObserver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class GRPCEchoHandler @Autowired constructor(
        val echoService: EchoService
) : EchoServiceGrpc.EchoServiceImplBase() {

    override fun echo(request: Rpc.EchoRequest, responseObserver: StreamObserver<Rpc.EchoResponse>) {
        val response = Rpc.EchoResponse.newBuilder()
                .setValue(echoService.echo(request.value)).build()

        responseObserver.onNext(response)
        responseObserver.onCompleted()
    }

}
