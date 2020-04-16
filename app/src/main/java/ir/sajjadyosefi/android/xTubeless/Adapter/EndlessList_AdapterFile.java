package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.File;

public class EndlessList_AdapterFile extends RecyclerView.Adapter<EndlessList_AdapterFile.ParentViewHolder> {

    //val
    public static  final int LAST_ITEM = 99;
    public static final int FILES = 3;
    public static final int TODO = 10;
    private boolean deletable;

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<File> mTimelineItemList;

    EndlessList_AdapterFile adapter;
    public LinearLayoutManager mLayoutManager = null ;

    private int lastPosition = -1;


    //FILES
    public EndlessList_AdapterFile(Context context, LinearLayoutManager mLayoutManager, View rootview, List<File> fileItemList, int type, boolean deletable) {
        if (type == FILES) {
            this.mContext = context;
            this.mLayoutManager = mLayoutManager;
            this.mRecyclerView = rootview.findViewById(R.id.recyclerView);
            this.mTimelineItemList = fileItemList;
            this.adapter = this;
            this.deletable = deletable ;
        }
    }


    ///////////////////////  ViewHolder   /////////////////////////
    public class ParentViewHolder extends RecyclerView.ViewHolder {

        public ParentViewHolder(View itemView) {
            super(itemView);
        }

        public void clearAnimation() {
            itemView.clearAnimation();
        }
    }

    public class ProgressViewHolder extends ParentViewHolder {
        public ProgressViewHolder(View itemView) {
            super(itemView);
//            linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);
        }
    }

    public class EndOfListHolder extends ParentViewHolder {
        public TextView textView;
        public EndOfListHolder (View itemView) {
            super(itemView);
            textView                = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public class FileViewHolder extends ParentViewHolder {
        public View rootView;
        public TextView textView;
        public Button buttonDelete , buttonShow;

        public FileViewHolder(View itemView) {
            super(itemView);
            rootView                    = (View) itemView.findViewById(R.id.rootView);
            textView                = (TextView) itemView.findViewById(R.id.textView);
            buttonDelete            = (Button) itemView.findViewById(R.id.buttonDelete);
            buttonShow                = (Button) itemView.findViewById(R.id.buttonShow);
        }
    }

    public class AddViewHolder extends ParentViewHolder {
        public Button buttonSubmit;


        public AddViewHolder(View itemView) {
            super(itemView);
            buttonSubmit            = (Button) itemView.findViewById(R.id.submit);

        }
    }
    //////////////////// End ViewHolder   /////////////////////////

    @Override
    public ParentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LAST_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_last_item_files, parent, false);
            return new EndOfListHolder(view);
        }
        if (viewType == FILES) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_file, parent, false);
            FileViewHolder viewHolder = new FileViewHolder(view);
            return viewHolder;
        }
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_progress, parent, false);
            ProgressViewHolder holder = new ProgressViewHolder(view);
            return holder;
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(final ParentViewHolder holder, final int position) {
        if (mTimelineItemList.size() > 0 && mTimelineItemList.size() != position && mTimelineItemList.get(position).getType() == FILES) {
            ((File)mTimelineItemList.get(position)).prepareYafteItem(mContext, (FileViewHolder) holder, mTimelineItemList, position,adapter,deletable);
        }else {
            //LAST ITEM
            if (mTimelineItemList.size() == 0 )
                ((EndOfListHolder)holder).textView.setText(R.string.not_any_file);
        }
    }

    ///////////////////////  ok   /////////////////////////
    @Override
    public int getItemCount() {
        if (mTimelineItemList == null){
            return 0;
        }else {
            return mTimelineItemList.size() + 1;
        }
    }

    @Override
    public void onViewDetachedFromWindow(final ParentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
        ((ParentViewHolder)holder).clearAnimation();
    }

    @Override
    public int getItemViewType(int position) {
        return position == mTimelineItemList.size() ? LAST_ITEM : mTimelineItemList.get(position).getType();
    }

}