package com.example.dicoding_made_1.view.base;

public interface Presenter<T extends View> {
    void onAttach(T view);
    void onDetach();
}
