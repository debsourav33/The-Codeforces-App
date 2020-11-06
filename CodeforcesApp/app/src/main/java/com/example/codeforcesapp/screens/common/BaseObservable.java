package com.example.codeforcesapp.screens.common;

import androidx.lifecycle.ViewModel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class BaseObservable<ListenerType> implements IBaseObservable<ListenerType> {
    Set<ListenerType> listeners= new HashSet<>();


    @Override
    public void registerListener(ListenerType listener) {
        listeners.add(listener);
    }


    @Override
    public void unregisterListener(ListenerType listener) {
        listeners.remove(listener);
    }

    protected Set<ListenerType> getListeners() {
        return Collections.unmodifiableSet(listeners);
    }
}
