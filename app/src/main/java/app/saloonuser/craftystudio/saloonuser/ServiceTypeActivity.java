package app.saloonuser.craftystudio.saloonuser;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import utils.Service;
import utils.ServiceTypeExpandableAdapter;
import utils.User;

public class ServiceTypeActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    TabLayout tabLayout ;

   static HashMap<String ,ArrayList<Service>> mServiceHashMap =new HashMap<>() ;
    static ArrayList<String> mServiceSubType = new ArrayList<>();

    ArrayList<Service> serviceArrayList =new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = (TabLayout)findViewById(R.id.serviceType_tabLayout);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),serviceArrayList);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        tabLayout.setupWithViewPager(mViewPager);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        createServiceHashMap();




    }



    private void createServiceHashMap() {

        mServiceHashMap.put("Hair Cut", createServiceList());
        mServiceHashMap.put("Hair color" , createServiceColorList());
        mServiceHashMap.put("Hair stra" , createServiceList());



        mServiceSubType.add("Hair Cut");
        mServiceSubType.add("Hair color");
        mServiceSubType.add("Hair stra");


    }

    private ArrayList<Service> createServiceColorList() {
        ArrayList<Service> serviceArrayList =new ArrayList<>();

        for (int i = 0 ;i<5 ; i++){
            Service service =new Service();
            service.setServiceName("Brown color");
            serviceArrayList.add(service);
        }

        return serviceArrayList;
    }

    private ArrayList<Service> createServiceList() {
        ArrayList<Service> serviceArrayList =new ArrayList<>();

        for (int i = 0 ;i<5 ; i++){
            Service service =new Service();
            service.setServiceName("Bold cut");
            serviceArrayList.add(service);
        }

        return serviceArrayList;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        HashMap<String ,ArrayList<Service>> mServiceHashMap ;
        ArrayList<String> mServiceSubType ;

        public PlaceholderFragment() {
        }



        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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
             View rootView = inflater.inflate(R.layout.fragment_service_type, container, false);

            ExpandableListView expandableListView =(ExpandableListView)rootView.findViewById(R.id.serviceTypeFragment_ExpandableListView);

            ServiceTypeExpandableAdapter serviceTypeExpandableAdapter=new ServiceTypeExpandableAdapter(ServiceTypeActivity.mServiceHashMap
            ,ServiceTypeActivity.mServiceSubType,getContext()
            );

            expandableListView.setAdapter(serviceTypeExpandableAdapter);

            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Service> mServiceArrayList =new ArrayList<>();

        public SectionsPagerAdapter(FragmentManager fm , ArrayList<Service> serviceArrayList) {
            super(fm);
            mServiceArrayList =serviceArrayList;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).



            return PlaceholderFragment.newInstance(position + 1 );
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Hair Care";
                case 1:
                    return "Spa";
                case 2:
                    return "Body care";
            }
            return null;
        }
    }
}