package com.example.hotelmanagementsystemfx.Models;

import com.example.hotelmanagementsystemfx.Views.ViewFactory;

public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    private boolean managerLoginSuccessFlag;
    private boolean administratorLoginSuccessFlag;
    private boolean maidLoginSuccessFlag;
    private Model(){
        this.viewFactory = new ViewFactory();
    }
    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
