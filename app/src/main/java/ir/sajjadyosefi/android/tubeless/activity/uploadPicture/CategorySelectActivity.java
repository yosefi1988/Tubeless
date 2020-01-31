/*
 * Copyright 2014 Eduardo Barrenechea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.sajjadyosefi.android.tubeless.activity.uploadPicture;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.ui.DoubleHeaderFragment;
import ir.sajjadyosefi.android.tubeless.ui.InlineDoubleHeaderFragment;
import ir.sajjadyosefi.android.tubeless.ui.InlineStickyHeaderFragment;
import ir.sajjadyosefi.android.tubeless.ui.StickyHeaderFragment;


public class CategorySelectActivity extends FragmentActivity {

    public Context context;
    DilatingDotsProgressBar dilatingDotsProgressBar;
    EditText etSearch ;

    public static final int SORT_DEFAULT = 0;
    public static final int SORT_BY_ALPHABET = 1;

    public static final int RECENTLY_CAR_LIST = 10;
    public static final int CAR_CATEGORY_BY_COMPANY = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_category_select);
        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);
        etSearch = (EditText) findViewById(R.id.etSearch);

        CategorySelectActivity.HeaderPagerAdapter adapter = new CategorySelectActivity.HeaderPagerAdapter(this.getSupportFragmentManager());

        ViewPager pager = (ViewPager) this.findViewById(R.id.pager);
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.activity_main_select_car_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/edubarr/header-decor"));
            this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class HeaderPagerAdapter extends FragmentPagerAdapter {

        public HeaderPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new StickyHeaderFragment(context,dilatingDotsProgressBar,etSearch,RECENTLY_CAR_LIST,SORT_DEFAULT);
                case 1:
                    return new StickyHeaderFragment(context,dilatingDotsProgressBar,etSearch,CAR_CATEGORY_BY_COMPANY,SORT_DEFAULT);
                case 2:
                    return new StickyHeaderFragment(context,dilatingDotsProgressBar,etSearch,CAR_CATEGORY_BY_COMPANY,SORT_BY_ALPHABET);

                case 9:
                    return new DoubleHeaderFragment();

                case 10:
                    return new InlineDoubleHeaderFragment();
                case 11:
                    return new InlineStickyHeaderFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return context.getString(R.string.CategoryTitleRecent);

                case 1:
                    return context.getString(R.string.CategoryTitle0);

                case 2:
                    return context.getString(R.string.CategoryTitle1);



                case 3:
                    return "Double Header";

                case 4:
                    return "Double Header - Inline";
                case 5:
                    return "Sticky Header - Inline";
                default:
                    return null;
            }
        }
    }
}
