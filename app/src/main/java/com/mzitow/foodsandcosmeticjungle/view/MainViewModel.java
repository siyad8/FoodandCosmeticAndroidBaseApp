package com.mzitow.foodsandcosmeticjungle.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.mzitow.foodsandcosmeticjungle.model.CustomModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

 //   private CustomRepository customRepository;
    MutableLiveData<List<CustomModel>> liveList;
    MutableLiveData<CustomModel> liveUpdate;

    public MainViewModel(@NonNull Application application) {
        super(application);
        liveList = new MutableLiveData();
        liveUpdate = new MutableLiveData<>();
    }

    public void setItems() {
        List<CustomModel> staticData = new ArrayList<>();
        staticData.clear();
        staticData.add(new CustomModel(0,"Name 0"));
        staticData.add(new CustomModel(1,"name 1"));
        staticData.add(new CustomModel(2,"name 2"));
        staticData.add(new CustomModel(3,"name 3"));
        staticData.add(new CustomModel(4,"name 4"));
        staticData.add(new CustomModel(5,"name 5"));
        staticData.add(new CustomModel(6,"name 6"));
        staticData.add(new CustomModel(7,"name 7"));
        staticData.add(new CustomModel(8,"name 8"));
        liveList.setValue(staticData);
    }

    public void setUpdate(CustomModel item) {
        liveUpdate.setValue(item);
    }

    public LiveData<CustomModel> getUpdate() {
        return liveUpdate;
    }

    public void insertItem() {

    }

    public void updateItem() {

    }
    public void deleteItem(CustomModel item) {

    }

    public void deleteAll() {

    }

    public LiveData<List<CustomModel>> getItems() {
        return liveList;
    }
}
