package com.kantoniak.greendeer.data;

import com.kantoniak.greendeer.proto.AddRunsRequest;
import com.kantoniak.greendeer.proto.AddRunsResponse;
import com.kantoniak.greendeer.proto.DeleteRunsRequest;
import com.kantoniak.greendeer.proto.DeleteRunsResponse;
import com.kantoniak.greendeer.proto.EditRunsRequest;
import com.kantoniak.greendeer.proto.GetListRequest;
import com.kantoniak.greendeer.proto.GetListResponse;
import com.kantoniak.greendeer.proto.GetStatsRequest;
import com.kantoniak.greendeer.proto.Run;
import com.kantoniak.greendeer.proto.RunList;
import com.kantoniak.greendeer.proto.RunServiceGrpc;
import com.kantoniak.greendeer.proto.Stats;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class DataProvider {

    private static final Logger logger = Logger.getLogger(DataProvider.class.getName());
    private static final String HOST = "52.169.31.95";
    private static final int PORT = 50051;
    private static final int CALL_DEADLINE_SECS = 5;

    private final ManagedChannel channel;
    private final RunServiceGrpc.RunServiceBlockingStub blockingStub;

    public DataProvider() {
        this.channel = ManagedChannelBuilder.forAddress(HOST, PORT)
            .usePlaintext(true) // Turn off SSL
            .build();
        this.blockingStub = RunServiceGrpc.newBlockingStub(channel);
    }

    public Stats getStats() throws StatusRuntimeException {
        GetStatsRequest request = GetStatsRequest.newBuilder().build();
        return blockingStub.withDeadlineAfter(CALL_DEADLINE_SECS, TimeUnit.SECONDS)
                .getStats(request).getStats();
    }

    /**
     * Fetches the list of all trainings. Makes a blocking RPC call;
     * @return list of trainings
     */
    public List<Run> getListOfRuns() throws StatusRuntimeException {
        GetListRequest request = GetListRequest.newBuilder().build();
        return blockingStub.withDeadlineAfter(CALL_DEADLINE_SECS, TimeUnit.SECONDS)
                .getList(request).getRunList().getRunsList();
    }

    public Run addRun(Run run) {
        AddRunsRequest request = AddRunsRequest.newBuilder()
                .setRunsToAdd(RunList.newBuilder().addRuns(run))
                .build();
        return blockingStub.withDeadlineAfter(CALL_DEADLINE_SECS, TimeUnit.SECONDS)
                .addRuns(request).getAddedRuns().getRunsList().get(0);
    }

    public Run editRun(Run run) {
        EditRunsRequest request = EditRunsRequest.newBuilder()
                .setRunsToEdit(RunList.newBuilder().addRuns(run))
                .build();
        return blockingStub.withDeadlineAfter(CALL_DEADLINE_SECS, TimeUnit.SECONDS)
                .editRuns(request).getChangedRuns().getRunsList().get(0);
    }

    public void deleteRun(int id) {
        DeleteRunsRequest request = DeleteRunsRequest.newBuilder()
                .addIds(id)
                .build();
        blockingStub.withDeadlineAfter(CALL_DEADLINE_SECS, TimeUnit.SECONDS)
                .deleteRuns(request);
    }

    /**
     * Closes the channel if opened. Wait time is limited.
     * @throws InterruptedException on wait timeout
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(3, TimeUnit.SECONDS);
    }
}
