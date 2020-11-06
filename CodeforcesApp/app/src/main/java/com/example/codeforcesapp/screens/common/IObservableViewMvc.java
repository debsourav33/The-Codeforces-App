package com.example.codeforcesapp.screens.common;

public interface IObservableViewMvc<ListenerType> extends IViewMvc {
    void registerListener(ListenerType listener);
    void deRegisterListener(ListenerType listener);

}
