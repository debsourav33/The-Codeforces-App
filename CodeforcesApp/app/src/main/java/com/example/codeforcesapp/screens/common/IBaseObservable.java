package com.example.codeforcesapp.screens.common;

interface IBaseObservable<ListenerType> {
    void registerListener(ListenerType listener);

    void unregisterListener(ListenerType listener);
}
