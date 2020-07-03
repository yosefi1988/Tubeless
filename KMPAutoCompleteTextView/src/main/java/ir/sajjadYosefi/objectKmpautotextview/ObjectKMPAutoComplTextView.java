package ir.sajjadYosefi.objectKmpautotextview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者 : andy
 * 日期 : 15/10/26 10:50
 * 邮箱 : andyxialm@gmail.com
 * 描述 : 实现KMP算法的AutoCompleteTextView, 用于字符串模糊匹配
 */
public class ObjectKMPAutoComplTextView extends AppCompatAutoCompleteTextView {

    private static final int DEFAULT_HIGHLIGHT       = Color.parseColor("#FF4081");
    private static final int DEFAULT_TEXTCOLOR       = Color.parseColor("#80000000");
    private static final int DEFAULT_TEXT_PIXEL_SIZE = 14;

    private float mTextSize;
    private boolean mIsIgnoreCase;
    private KMPAdapter mAdapter;

    private ColorStateList mHighLightColor, mTextColor;
    private List<PopupTextBean> mSourceDatas, mTempDatas;
    private OnPopupItemClickListener mListener;

    public ObjectKMPAutoComplTextView(Context context) {
        this(context, null);
    }

    public ObjectKMPAutoComplTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.autoCompleteTextViewStyle);
    }

    public ObjectKMPAutoComplTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ObjectKMPAutoComplTextView);
            mTextColor = a.getColorStateList(R.styleable.ObjectKMPAutoComplTextView_completionTextColor);
            mHighLightColor = a.getColorStateList(R.styleable.ObjectKMPAutoComplTextView_completionHighlightColor);
            mTextSize = a.getDimensionPixelSize(R.styleable.ObjectKMPAutoComplTextView_completionTextSize, DEFAULT_TEXT_PIXEL_SIZE);
            mIsIgnoreCase = a.getBoolean(R.styleable.ObjectKMPAutoComplTextView_completionIgnoreCase, false);
            a.recycle();
        }
        initListener();
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            onInputTextChanged(s.toString());
        }
    };
    private void initListener() {

        addTextChangedListener(textWatcher);

    }

    private void onInputTextChanged(String input) {
        matchResult(input);

        if (mAdapter.mList.size() == 0) {
            ObjectKMPAutoComplTextView.this.dismissDropDown();
            return;
        }
        mAdapter.notifyDataSetChanged();

        if (!ObjectKMPAutoComplTextView.this.isPopupShowing() || mAdapter.mList.size() > 0) {
            showDropDown();
        }

    }

    /**
     * 设置数据集
     *
     * @param strings
     */

    List<ItemData> itemDataList;
    public void setDatas(final List<ItemData> datas) {
        this.itemDataList = datas;
        mAdapter = new KMPAdapter(getContext(), getResultDatas(itemDataList));
        setAdapter(mAdapter);
    }

    public void setText(String text){
        this.removeTextChangedListener(textWatcher);
        this.setText(text.toString());
        this.addTextChangedListener(textWatcher);
    }

    public int getPosition(String text){
        int index = 0 ;
        for (ItemData itemData : itemDataList) {
            if (itemData.getText().equals(text)){
                return index;
            }
            index++;
        }
        return index;
    }

    public ItemData getPosition(int position){
        return itemDataList.get(position);
    }

    public String getMetaData(String text){
        for (ItemData itemData : itemDataList) {
            if (itemData.getText().equals(text)){
                return itemData.getMeta();
            }
        }
        return null;
    }

    public ItemData getItemData(String text){
        for (ItemData itemData : itemDataList) {
            if (itemData.getText().equals(text)){
                return itemData;
            }
        }
        return null;
    }

    public String getImage(String text){
        for (ItemData itemData : itemDataList) {
            if (itemData.getText().equals(text)){
                return itemData.getImageId();
            }
        }
        return null;
    }



    public void setOnPopupItemClickListener(OnPopupItemClickListener listener) {
        mListener = listener;
        this.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mListener == null) {
                    return;
                }
                mListener.onPopupItemClick(ObjectKMPAutoComplTextView.this.getText().toString());
//                mListener.onPopupItemClick(mSourceDatas.get(position).mTarget.getText());
            }
        });

    }

    private void matchResult(String input) {
        List<PopupTextBean> datas = mSourceDatas;
        if (TextUtils.isEmpty(input) || datas == null || datas.size() == 0) {
            return;
        }

        List<PopupTextBean> newDatas = new ArrayList<PopupTextBean>();
        List<ItemData> newDataStrings = new ArrayList<ItemData>();
        for (PopupTextBean resultBean : datas) {
            int matchIndex = matchString(resultBean.mTarget.text, input, mIsIgnoreCase);
            if (-1 != matchIndex) {
                PopupTextBean bean = new PopupTextBean(resultBean.mTarget, matchIndex, matchIndex + input.length());
                newDatas.add(bean);
                newDataStrings.add(resultBean.mTarget);
            }
        }

        mTempDatas = new ArrayList<PopupTextBean>();
        mTempDatas.clear();
        mTempDatas.addAll(newDatas);

        mAdapter.mList.clear();
        mAdapter.mList.addAll(newDataStrings);
    }


    private List<ItemData> getResultDatas(List<ItemData> itemData) {
        if (itemData == null || itemData.size() == 0) {
            return null;
        }

        List<PopupTextBean> list = new ArrayList<PopupTextBean>();
        for (ItemData target : itemData) {
            list.add(new PopupTextBean(target));
        }

        mSourceDatas = new ArrayList<PopupTextBean>();
        mSourceDatas.addAll(list);
        return itemData;
    }

    public void setMatchIgnoreCase(boolean ignoreCase) {
        mIsIgnoreCase = ignoreCase;
    }

    public boolean getMatchIgnoreCase() {
        return mIsIgnoreCase;
    }

    class KMPAdapter extends BaseAdapter implements Filterable {
        private List<ItemData> mList;
        private Context mContext;
        private MyFilter mFilter;

        public KMPAdapter(Context context, List<ItemData> list) {
            mContext = context;
            mList = new ArrayList<ItemData>();

            if (list != null)
                mList.addAll(list);
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList == null ? null : mList.get(position).getText();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                TextView tv = new TextView(mContext);
                int paddingX = DisplayUtils.dp2px(getContext(), 10.0f);
                int paddingY = DisplayUtils.dp2px(getContext(), 5.0f);
                tv.setPadding(paddingX, paddingY, paddingX, paddingY);

                holder.tv = tv;
                convertView = tv;
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            PopupTextBean bean = mTempDatas.get(position);
            SpannableString ss = new SpannableString(bean.mTarget.getText());
            holder.tv.setTextColor(mTextColor == null ? DEFAULT_TEXTCOLOR : mTextColor.getDefaultColor());
            holder.tv.setTextSize(mTextSize == 0 ? DEFAULT_TEXT_PIXEL_SIZE : DisplayUtils.px2sp(getContext(), mTextSize));

            // Change Highlight Color
            if (-1 != bean.mStartIndex) {
                ss.setSpan(new ForegroundColorSpan(mHighLightColor == null ? DEFAULT_HIGHLIGHT : mHighLightColor.getDefaultColor()),
                        bean.mStartIndex, bean.mEndIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                holder.tv.setText(ss);
            } else {
                holder.tv.setText(bean.mTarget.getText());
            }

            return convertView;
        }

        @Override
        public Filter getFilter() {
            if (mFilter == null) {
                mFilter = new MyFilter();
            }
            return mFilter;
        }

        private class ViewHolder {
            TextView tv;
        }

        private class MyFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (mList == null) {
                    mList = new ArrayList<ItemData>();
                }
                results.values = mList;
                results.count = mList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        }
    }

    public interface OnPopupItemClickListener {
        void onPopupItemClick(CharSequence charSequence);
    }

    /**
     * 获得字符串的next函数值
     *
     * @param mode 字符串
     * @return next函数值
     */
    private static int[] next(char[] mode) {
        int[] next = new int[mode.length];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < mode.length - 1) {
            if (j == -1 || mode[i] == mode[j]) {
                i ++;
                j ++;
                if (mode[i] != mode[j]) {
                    next[i] = j;
                } else {
                    next[i] = next[j];
                }
            } else {
                j = next[j];
            }
        }
        return next;
    }

    /**
     * KMP匹配字符串
     *
     * @param source       主串
     * @param modeStr      模式串
     * @param isIgnoreCase 是否忽略大小写
     * @return 若匹配成功，返回下标，否则返回-1
     */
    public int matchString(CharSequence source, CharSequence modeStr, boolean isIgnoreCase) {
        char[] modeArr = modeStr.toString().toCharArray();
        char[] sourceArr = source.toString().toCharArray();
        int[] next = next(modeArr);
        int i = 0;
        int j = 0;
        while (i <= sourceArr.length - 1 && j <= modeArr.length - 1) {
            if (isIgnoreCase) {
                if (j == -1 || sourceArr[i] == modeArr[j] || String.valueOf(sourceArr[i]).equalsIgnoreCase(String.valueOf(modeArr[j]))) {
                    i ++;
                    j ++;
                } else {
                    j = next[j];
                }
            } else {
                if (j == -1 || sourceArr[i] == modeArr[j]) {
                    i ++;
                    j ++;
                } else {
                    j = next[j];
                }
            }
        }
        if (j < modeArr.length) {
            return -1;
        } else
            return i - modeArr.length; // 返回模式串在主串中的头下标
    }

}
