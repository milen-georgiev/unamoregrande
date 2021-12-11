package com.example.unamoregrande.model.view;

public class StatsView {

    private final int authRequests;
    private final int anonRequests;

    public StatsView(int authRequests, int anonRequests) {
        this.authRequests = authRequests;
        this.anonRequests = anonRequests;
    }

    public int getTotalRequest() {
        return anonRequests + authRequests;
    }

    public int getAuthRequests() {
        return authRequests;
    }

    public int getAnonRequests() {
        return anonRequests;
    }

}