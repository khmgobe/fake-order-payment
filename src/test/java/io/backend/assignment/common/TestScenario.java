package io.backend.assignment.common;

import io.backend.assignment.common.api.RegisterProductApi;

public class TestScenario {

    public static RegisterProductApi registerProductApi () {
        return new RegisterProductApi();
    }
}
