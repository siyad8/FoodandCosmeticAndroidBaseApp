package com.mzitow.foodsandcosmeticjungle.model;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mzitow.foodsandcosmeticjungle.R;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductDao;
import com.mzitow.foodsandcosmeticjungle.database.FoodProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductEntity;
import com.mzitow.foodsandcosmeticjungle.database.ProductsDao;
import com.mzitow.foodsandcosmeticjungle.database.UserDatabase;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomModel {

    List<ProductEntity> productEntities = new List<ProductEntity>() {
        @Override
        public int size() {
            return productEntities.size();
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<ProductEntity> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] ts) {
            return null;
        }

        @Override
        public boolean add(ProductEntity productEntity) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends ProductEntity> collection) {
            return false;
        }

        @Override
        public boolean addAll(int i, @NonNull Collection<? extends ProductEntity> collection) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public ProductEntity get(int i) {
            return null;
        }

        @Override
        public ProductEntity set(int i, ProductEntity productEntity) {
            return null;
        }

        @Override
        public void add(int i, ProductEntity productEntity) {

        }

        @Override
        public ProductEntity remove(int i) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<ProductEntity> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<ProductEntity> listIterator(int i) {
            return null;
        }

        @NonNull
        @Override
        public List<ProductEntity> subList(int i, int i1) {
            return null;
        }
    };

    ProductsDao productsDao;
    Context context;

    public CustomModel(Context con) {
       // this.productEntities = productEntities;

        this.context = con;
    }

    int id;
    String name;

    int icon;

    public CustomModel(String name) {
        this.name = name;
        this.icon = R.drawable.ic_launcher_foreground;
    }

    public CustomModel(int id, String name) {
        this.id = id;
        this.name = name;
        this.icon = R.drawable.ic_launcher_foreground;
    }

    public CustomModel(int id, String name, int icon) {
        this.id = id;
        this.name = name;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public void delete (ProductEntity productEntity){
        UserDatabase userDatabase = UserDatabase.getUserDatabase(context.getApplicationContext());

        new DeleteAsyncTask(userDatabase.productsDao() ).execute(productEntity);
    }

    public  void deletefood(FoodProductEntity foodProductEntity){
        UserDatabase userDatabase = UserDatabase.getUserDatabase(context.getApplicationContext());

        new DeleteFoodAsyncTask(userDatabase.foodProductDao()).execute(foodProductEntity);

    }

    private class DeleteAsyncTask extends AsyncTask<ProductEntity, Void, Void> {
       // UserDatabase userDatabase = UserDatabase.getUserDatabase(context.getApplicationContext());

        ProductsDao productsDao;

        public DeleteAsyncTask(ProductsDao productsDao) {
            this.productsDao = productsDao;

        }



        @Override
        protected Void doInBackground(ProductEntity... productEntities) {
            productsDao.deleteproduct(productEntities[0]);

            return null;
        }

        public void execute(FoodProductEntity foodProductEntity) {
        }
    }

    private class  DeleteFoodAsyncTask extends AsyncTask<FoodProductEntity, Void , Void>{
        FoodProductDao foodProductDao;

        public DeleteFoodAsyncTask(FoodProductDao foodProductDao) {
            this.foodProductDao = foodProductDao;
        }

        @Override
        protected Void doInBackground(FoodProductEntity... foodProductEntities) {
            return null;
        }
    }
}
