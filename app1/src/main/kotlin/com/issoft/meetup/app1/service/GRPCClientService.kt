package com.issoft.meetup.app1.service

import com.issoft.meetup.proto.EchoServiceGrpc
import com.issoft.meetup.proto.Rpc
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GRPCClientService {

    private final val channel = ManagedChannelBuilder.forAddress("localhost", 8888)
            .usePlaintext()
            .build()!!

    val stub = EchoServiceGrpc.newStub(channel)!!

    fun echo(value: String): Mono<String> {
        return Mono.create { sink ->
            run {
                val responseObserver = object : StreamObserver<Rpc.EchoResponse> {
                    override fun onNext(echoResponse: Rpc.EchoResponse) {
                        //println("onNext")
                        sink.success(echoResponse.value)
                    }

                    override fun onError(t: Throwable) {
                        //println("onError")
                    }

                    override fun onCompleted() {
                        //println("onCompleted")
                    }
                }

                stub.echo(Rpc.EchoRequest.newBuilder().setValue(value).build(), responseObserver)
            }
        }
    }

    fun destroy() {
        println("destroy")
        channel.shutdown()
    }
}