package com.example.inditex.mothers;

import java.time.Instant;

public class InstantMother {

    public Instant june_14th_2020_00_00() {
        return Instant.parse("2020-06-14T00:00:00Z");
    }

    public Instant june_14th_2020_10_00() {
        return Instant.parse("2020-06-14T10:00:00.00Z");
    }

    public Instant december_31th_2020_23_59() {
        return Instant.parse("2020-12-31T23:59:59Z");
    }
}
