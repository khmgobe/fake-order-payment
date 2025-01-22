package io.backend.assignment.common;

import io.backend.assignment.common.api.RegisterCartApi;
import io.backend.assignment.common.api.RegisterCustomerApi;
import io.backend.assignment.common.api.RegisterProductApi;

public class TestScenario {

    public static RegisterProductApi registerProductApi() {
        return new RegisterProductApi();
    }

    public static RegisterCustomerApi registerCustomerApi() {
        return new RegisterCustomerApi();
    }

    public static RegisterCartApi registerCartApi() {
        return new RegisterCartApi();
    }
}
