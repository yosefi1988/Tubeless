package ir.sajjadyosefi.android.tubeless.asyncTask.config;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.tubeless.classes.model.Car.Car;
import ir.sajjadyosefi.android.tubeless.classes.model.Company;
import ir.sajjadyosefi.android.tubeless.classes.model.Config.CarCategoriesByCompanyResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.Config.CategoryItem;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.activity.uploadPicture.CategorySelectActivity;
import ir.sajjadyosefi.android.tubeless.widget.StickyTestAdapter;


/**
 * Created by Other on 10/23/2016.
 */
public class AsyncCarCategoriesByCompany extends AsyncTask<String, Void, CarCategoriesByCompanyResponse> {
    private StickyTestAdapter stickyTestAdapter;
    DilatingDotsProgressBar dilatingDotsProgressBar;
    private final Context context;

    private List<CategoryItem> categoryList;
    private List<CategoryItem> orginalCategoryList = new ArrayList<CategoryItem>();;;

    private List<CategoryItem> categoryHeaderList;
    private List<CategoryItem> orginalcategoryHeaderList = new ArrayList<CategoryItem>();;


    EditText etSearch ;
    int sortType;
    int listType;
    TextView textView;
    RecyclerView list;

    public AsyncCarCategoriesByCompany(Context context, RecyclerView list, TextView textView, DilatingDotsProgressBar dilatingDotsProgressBar, List<CategoryItem> categoryList0, List<CategoryItem> categoryHeaderList0, StickyTestAdapter stickyTestAdapter, final EditText etSearch, int listType, int sortType) {
        this.context = context;
        this.categoryList = categoryList0;
        this.categoryHeaderList = categoryHeaderList0;
        this.stickyTestAdapter = stickyTestAdapter;
        this.dilatingDotsProgressBar = dilatingDotsProgressBar;
        this.etSearch = etSearch;
        this.sortType = sortType;
        this.listType = listType;
        this.textView = textView;
        this.list = list;

        if(etSearch.getText().length()>0){
            search(etSearch.getText().toString());
        }
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                search(etSearch.getText().toString());
            }
        });
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dilatingDotsProgressBar.showNow();
    }

    @Override
    protected CarCategoriesByCompanyResponse doInBackground(String... params) {
        RestApiHelper myParser = new RestApiHelper();
        switch (listType) {
            case (CategorySelectActivity.RECENTLY_CAR_LIST):
                if(Global.mUser == null)
                    return myParser.carCategoriesByCompanyRecently();
                else
                    return myParser.carCategoriesByCompanyRecently(Global.mUser.getUserID());

            case (CategorySelectActivity.CAR_CATEGORY_BY_COMPANY):
                return myParser.carCategoriesByCompany();

            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(CarCategoriesByCompanyResponse carCategoriesByCompanyResponse) {
        switch (sortType) {
            case CategorySelectActivity.SORT_BY_ALPHABET: {
                categoryList.removeAll(categoryList);
            }
        }

        int headersId = 0;
        if(carCategoriesByCompanyResponse != null){
            for (Company item:carCategoriesByCompanyResponse.getCompanyList()) {
                if(item.getCarList().size() >= 1) {
                    CategoryItem categoryItem = new CategoryItem();
                    categoryItem.setHeader(true);
                    categoryItem.setID(item.getID());
                    categoryItem.setTitle(item.getNameFa());
                    categoryItem.setPicture(item.getLogo());
                    categoryItem.setIdHeader(headersId);
                    categoryItem.setDirectoryName(item.getNameEn().replace(" ","_"));
                    Log.i("company", item.getNameFa());
                    categoryHeaderList.add(categoryItem);

                    for (Car itemCar : item.getCarList()) {
                        CategoryItem categoryitemCar = new CategoryItem();
                        categoryitemCar.setHeader(false);
                        categoryitemCar.setIdHeader(categoryItem.getID());
                        categoryitemCar.setDirectoryName(categoryItem.getDirectoryName() + "/" + itemCar.getNameEn().replace(" ","_"));
                        categoryitemCar.setID(itemCar.getID());

                        if(itemCar.getCarClass().getNameFa() == null)
                            categoryitemCar.setTitle(itemCar.getCarClass().getName());
                        else
                            categoryitemCar.setTitle(itemCar.getCarClass().getNameFa());

                        categoryitemCar.setText(itemCar.getNameFa());
                        categoryitemCar.setPicture(itemCar.getCarClass().getLogo());
                        Log.i("car", itemCar.getNameFa());
                        categoryList.add(categoryitemCar);
                    }
                    headersId++;
                }else
                    continue;
            }

            switch (sortType){
                case CategorySelectActivity.SORT_DEFAULT:
                {

                    break;
                }
                case CategorySelectActivity.SORT_BY_ALPHABET:
                {
                    //string
//                    Collator collator = Collator.getInstance(new Locale("fa", "IR"));
//                    collator.setStrength(Collator.PRIMARY);
//                    Collections.sort(categoryList, collator);

                    Collections.sort(categoryList, new Comparator<CategoryItem>() {
                        @Override
                        public int compare(CategoryItem a1, CategoryItem a2) {
                            return ((int)(a1.getText().charAt(0))) - ((int) a2.getText().charAt(0));
                        }
                    });

                    break;
                }
                default:{

                }
            }

            for (CategoryItem ci:categoryList) {
                orginalCategoryList.add(ci);
            }
            for (CategoryItem ci:categoryHeaderList) {
                orginalcategoryHeaderList.add(ci);
            }


            stickyTestAdapter.notifyDataSetChanged();
        }else {
            Global.ShowMessageDialog(context,"","System error");
        }
        dilatingDotsProgressBar.hideNow();

        if (etSearch.getText().toString().length() >= 1) {
            search(etSearch.getText().toString());
        }

        if(categoryList.size()>1){
            textView.setVisibility(View.INVISIBLE);
            list.setVisibility(View.VISIBLE);
        }
        else {
            textView.setVisibility(View.VISIBLE);
            list.setVisibility(View.INVISIBLE);
        }
    }

    private void search(String searchTerm) {

        //Clear
//        categoryHeaderList = new ArrayList<CategoryItem>();
        categoryList.removeAll(categoryList);

        //Header
//        CategoryItem categoryItem = new CategoryItem();
//        categoryItem.setHeader(true);
//        categoryItem.setID(100);
//        categoryItem.setTitle("نتایج جستجو");
//        categoryItem.setText("نتایج جستجو");
//        //categoryItem.setPicture(item.getLogo());
//        categoryItem.setIdHeader(0);
//        //categoryItem.setDirectoryName(item.getNameEn().replace(" ","_"));
//        categoryHeaderList.add(categoryItem);

        //Content
        for (CategoryItem ci:orginalCategoryList) {
            if(ci.getText().contains(searchTerm)) {
                //ci.setIdHeader(0);
                categoryList.add(ci);
            }

            if(ci.getTitle().equals(searchTerm)) {
                //ci.setIdHeader(0);
                categoryList.add(ci);
            }
        }
//        categoryHeaderList = new ArrayList<CategoryItem>();
//        categoryList = new ArrayList<CategoryItem>();
//        //stickyTestAdapter.notifyDataSetChanged();


//        categoryHeaderList.add(orginalcategoryHeaderList.get(0));
//        categoryList.add(orginalCategoryList.get(0));
//        categoryList.remove(0);
        stickyTestAdapter.notifyDataSetChanged();
    }
}
