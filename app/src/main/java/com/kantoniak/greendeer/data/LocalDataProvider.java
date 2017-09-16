package com.kantoniak.greendeer.data;

import com.kantoniak.greendeer.proto.Run;

import java.util.Arrays;
import java.util.List;

public class LocalDataProvider implements DataProvider {

    @Override
    public List<Run> getListOfRuns() {
        return Arrays.asList(
                Run.newBuilder().setMeters(7000).setTimeInSeconds(2620).build(),
                Run.newBuilder().setMeters(3100).setTimeInSeconds(991).build(),
                Run.newBuilder().setMeters(3100).setTimeInSeconds(921).build()
        );
    }
}
