package com.stormarts.infinityquiz.presentation.presenter;


import com.stormarts.infinityquiz.presentation.view.MenuView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MenuPresenter extends MvpPresenter<MenuView> {


    public void logOut(){
        getViewState().onLoghOut();
    }

}
