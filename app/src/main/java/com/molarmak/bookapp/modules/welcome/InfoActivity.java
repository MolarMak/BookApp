package com.molarmak.bookapp.modules.welcome;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.molarmak.bookapp.R;
import com.molarmak.bookapp.helper.SetImage;
import com.molarmak.bookapp.modules.general.main.view.MainActivity;
import com.rd.PageIndicatorView;

/**
 * Class intro in app
 * Display information about app
 */
public class InfoActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private LinearLayout skipButton;
    public static final int[] pageImage = {R.drawable.book, R.drawable.add, R.drawable.update};
    public static final int[] pageDescription = {R.string.info_desc1, R.string.info_desc2, R.string.info_desc3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_info);

            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);
            skipButton = findViewById(R.id.skipButton);

            PageIndicatorView pageIndicatorView = (PageIndicatorView) findViewById(R.id.pageIndicatorView);
            pageIndicatorView.setViewPager(mViewPager);

            skipButton.setOnClickListener(view -> {
                try {
                    finish();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {}

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_info, container, false);
            try {
                ImageView helloImage = rootView.findViewById(R.id.helloImage);
                TextView helloDesc = rootView.findViewById(R.id.helloDesc);

                final int counter = getArguments().getInt(ARG_SECTION_NUMBER);

                SetImage.setImage(pageImage[counter], helloImage, getContext());
                helloDesc.setText(pageDescription[counter]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return pageDescription.length;
        }

    }
}
