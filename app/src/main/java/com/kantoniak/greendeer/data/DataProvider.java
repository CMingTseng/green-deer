package com.kantoniak.greendeer.data;

import com.kantoniak.greendeer.proto.GetListRequest;
import com.kantoniak.greendeer.proto.GetListResponse;
import com.kantoniak.greendeer.proto.Run;
import com.kantoniak.greendeer.proto.RunServiceGrpc;

import java.net.SocketException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;

public class DataProvider {

    private static final Logger logger = Logger.getLogger(DataProvider.class.getName());
    private static final String HOST = "52.169.31.95";
    private static final int PORT = 50051;

    private final ManagedChannel channel;
    private final RunServiceGrpc.RunServiceBlockingStub blockingStub;

    public DataProvider() {
        this.channel = ManagedChannelBuilder.forAddress(HOST, PORT)
            .usePlaintext(true) // Turn off SSL
            .build();
        this.blockingStub = RunServiceGrpc.newBlockingStub(channel);
    }

    /**
     * Fetches the list of all trainings. Makes a blocking RPC call;
     * @return list of trainings
     */
    public List<Run> getListOfRuns() throws StatusRuntimeException {
        GetListRequest request = GetListRequest.newBuilder().build();
        GetListResponse response;
        return blockingStub.getList(request).getRunList().getRunsList();
    }

    /**
     * Closes the channel if opened. Wait time is limited.
     * @throws InterruptedException on wait timeout
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(3, TimeUnit.SECONDS);
    }
}
