package ir.sajjadyosefi.android.tubeless.activity.yafteha;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity._BaseDrawerActivity;
import ir.sajjadyosefi.android.tubeless.adapter.list.SearchResultAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.yafte.search.SearchResultObject;


public class SearchResultActivity extends _BaseDrawerActivity {

    public Context context;
    public static List<SearchResultObject> searchResponse;

    RecyclerView recyclerView;
    private LinearLayoutManager mLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout._activity_search_result);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ViewGroup viewGroup = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        if (searchResponse != null) {
            RecyclerView.Adapter searchResultAdapter = new SearchResultAdapter(context,viewGroup);
            mLinearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(mLinearLayoutManager);
            recyclerView.setAdapter(searchResultAdapter);
            searchResultAdapter.notifyDataSetChanged();
        }

    }
}
