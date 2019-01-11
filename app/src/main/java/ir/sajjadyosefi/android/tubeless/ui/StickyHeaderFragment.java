
package ir.sajjadyosefi.android.tubeless.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.asyncTask.config.AsyncCarCategoriesByCompany;
import ir.sajjadyosefi.android.tubeless.classes.model.Config.CategoryItem;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.widget.StickyTestAdapter;
import ir.sajjadyosefi.android.tubeless.widget.recyclerview.decoration.StickyHeaderDecoration;

@SuppressLint("ValidFragment")
public class StickyHeaderFragment extends BaseDecorationFragment implements RecyclerView.OnItemTouchListener {

    private List<CategoryItem> categoryList = new ArrayList<>();
    private List<CategoryItem> categoryHeaderList = new ArrayList<>();
    public StickyTestAdapter adapter = null;
    EditText etSearch ;
    int sortType ;
    int listType ;

    private StickyHeaderDecoration decor;
    public Context context;
    DilatingDotsProgressBar dilatingDotsProgressBar;



    public StickyHeaderFragment(Context context, DilatingDotsProgressBar dilatingDotsProgressBar, EditText etSearch,int listType,int sortType) {
        this.context = context ;
        this.dilatingDotsProgressBar = dilatingDotsProgressBar;
        this.etSearch = etSearch;
        this.sortType = sortType;
        this.listType = listType;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setAdapterAndDecor(RecyclerView list,TextView textView) {

//
//
//        movieList.add("String 13");
//        movieList.add("String 12");
//        movieList.add("String 153");
//        movieList.add("String 61");
//        movieList.add("String 19");
//        movieList.add("String 15");
//        movieList.add("String 184");
//        movieList.add("String 178");
//        movieList.add("String 981");
//        movieList.add("String 981");
//        movieList.add("String 91");
//        movieList.add("String 51");
//        movieList.add("String 178");
//        movieList.add("String 981");
//        movieList.add("String 981");
//        movieList.add("String 91");
//        movieList.add("String 51");

        adapter = new StickyTestAdapter(this.getActivity(),categoryList,categoryHeaderList,sortType);
        decor = new StickyHeaderDecoration(adapter);
        setHasOptionsMenu(true);

        list.setAdapter(adapter);
        list.addItemDecoration(decor, 1);
        list.addOnItemTouchListener(this);
//
//        AsyncCarCategoriesByCompany asyncCarCategoriesByCompany = new AsyncCarCategoriesByCompany(context,list,textView, dilatingDotsProgressBar, categoryList, categoryHeaderList, adapter, etSearch,listType, sortType);
//        asyncCarCategoriesByCompany.execute();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_clear_cache) {
            decor.clearHeaderCache();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        // really bad click detection just for demonstration purposes
        // it will not allow the list to scroll if the swipe motion starts
        // on top of a header
        View v = rv.findChildViewUnder(e.getX(), e.getY());
        return v == null;
//        return rv.findChildViewUnder(e.getX(), e.getY()) != null;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        // only use the "UP" motion event, discard all others
        if (e.getAction() != MotionEvent.ACTION_UP) {
            return;
        }

        // find the header that was clicked
        View view = decor.findHeaderViewUnder(e.getX(), e.getY());

        if (view instanceof TextView) {
            Toast.makeText(this.getActivity(), ((TextView) view).getText() + " clicked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
        // do nothing
    }
}
