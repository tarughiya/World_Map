package com.example.splash_screen;

import android.app.Fragment;
import android.os.Bundle;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CountryActivityFragment extends Fragment {
    private RecyclerView rvNewTest;

    NewTestAdapter newTestAdapter;
    View view;
    CountryModel[] myListData;
    CountryModel[] myPeakData;
    CountryModel[] myRiverData;
    CountryModel[] myWonderData;
    private String fromWhere;

    public static Fragment newInstance() {

        Bundle args = new Bundle();

        CountryActivityFragment fragment = new CountryActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_test_activity, container, false);
        findView(view);
        init();
        return view;

    }

    public void findView(View view) {
        rvNewTest = view.findViewById(R.id.rvNewTest);

    }


    private void init() {
        rvNewTest.setLayoutManager(new LinearLayoutManager(getActivity()));


        if (fromWhere != null) {
            if (fromWhere.equalsIgnoreCase("Country")) {
                loadCountryList();
                updateDataInList(myListData);

            } else if (fromWhere.equalsIgnoreCase("Peak")) {
                loadPeakList();
                updateDataInList(myPeakData);
            } else if (fromWhere.equalsIgnoreCase("river")) {
                loadRiverList();
                updateDataInList(myRiverData);

            } else {
                loadWonderList();
                updateDataInList(myWonderData);

            }
        }
    }

    private void loadWonderList() {
        myWonderData = new CountryModel[]{
                new CountryModel("TAJ MAHAL", "INDIA", R.drawable.wondericon, "27.1750", "78.0422"),
                new CountryModel("GREAT PYRAMID OF GIZA", "EGYPT", R.drawable.wondericon, "29.9792", "31.1342"),
                new CountryModel("COLOSSEUM", "ITALY", R.drawable.wondericon, "41.8902", "12.4922"),
                new CountryModel("CHICHEN ITZA", "MEXICO", R.drawable.wondericon, "20.6843", "88.5678"),
                new CountryModel("MACHU PICCHU", "PERU", R.drawable.wondericon, "13.1631", "72.5450"),
                new CountryModel("CHRIST THE REDEEMER", "BRAZIL", R.drawable.wondericon, "22.9519", "43.2105"),
                new CountryModel("PETRA", "JORDAN", R.drawable.wondericon, "30.3285", "35.4444"),
                new CountryModel("GREAT WALL OF CHINA", "CHINA", R.drawable.wondericon, "40.4319", "116.5704"),
                new CountryModel("EIFFEL TOWER", "FRANCE", R.drawable.wondericon, "48.8584", "2.2945"),
        };

    }

    private void loadRiverList() {
        myRiverData = new CountryModel[]{
                new CountryModel("NILE RIVER", "6,650KM", R.drawable.river, " 27", " 30"),
                new CountryModel("AMAZONE RIVER", "6,400KM", R.drawable.river, "-2.163106", "-55.126648"),
                new CountryModel("YANGTZE RIVER", "6,300KM", R.drawable.river, "33.28", "91.11"),
                new CountryModel("MISSISSIPPI RIVER", "6,272KM", R.drawable.river, "45.711273", "-94.223853"),
                new CountryModel("YENISEI RIVER", "5,539KM", R.drawable.river, "50.589134", "99.146102"),
                new CountryModel("YELLOW RIVER", "5,464KM", R.drawable.river, "34.784497", "97.565546"),
                new CountryModel("OB RIVER", "5,410KM", R.drawable.river, "49.708449", "86.619693"),
                new CountryModel("PARANA RIVER", "4,880KM", R.drawable.river, "-19.218746", "-46.179394"),
                new CountryModel("CONGO RIVER", "4,700KM", R.drawable.river, " 28.462785", "-81.460393"),
                new CountryModel("AMUR RIVER", "4,444KM", R.drawable.river, "50.2", "127.3")};

    }


    private void loadPeakList() {
        myPeakData = new CountryModel[]{
                new CountryModel("MOUNT EVEREST", "8,848M", R.drawable.peak, "28.002194", "86.852457"),
                new CountryModel("K2", "8,611M", R.drawable.peak, "35.810749", "76.568012"),
                new CountryModel("KANGCHEN JUNGA", "8,586M", R.drawable.peak, "27.787897", "88.110022"),
                new CountryModel("LHOTSE", "8,516M", R.drawable.peak, "27.936872", "86.806676"),
                new CountryModel("MAKALU", "8,481M", R.drawable.peak, "27.9", "87.1"),
                new CountryModel("CHO OYU", "8,201M", R.drawable.peak, "28.022593", "86.690924"),
                new CountryModel("MANASLU", "8,163", R.drawable.peak, "28.593592", "84.596759"),
                new CountryModel("NANGA PARBAT", "8,126", R.drawable.peak, "35.237015", "74.589867"),
                new CountryModel("ANNAPURNA", "8,091M", R.drawable.peak, "28.376694", "83.808641"),
        };
    }

    private void loadCountryList() {
        myListData = new CountryModel[]{
                new CountryModel("INDIA", "New Delhi", R.drawable.india, "28.61583", "77.208908"),
                new CountryModel("CHINA", "Beijing", R.drawable.china, "39.904564", "116.407066"),
                new CountryModel("UNITED STATES", "Washington,D.C.", R.drawable.unitedstates, "38.910281", " -77.033617"),
                new CountryModel("BRAZIL", "Brasilia", R.drawable.brazil, "-15.794380", "-47.881980"),
                new CountryModel("INDONESIA", "Jakarta", R.drawable.indonesia, "-6.201003", " 106.851543"),
                new CountryModel("JAPAN", "Tokyo", R.drawable.japan, "35.659087", "139.745664"),
                new CountryModel("RUSSIA", "Moscow", R.drawable.russia, "55.753843", "37.620675"),
                new CountryModel("NIGERIA", "Abuja", R.drawable.nigeria, "9.059994", "7.489856"),
                new CountryModel("GERMANY", "Berlin", R.drawable.germany, "52.517752", "13.376353"),
                new CountryModel("MEXICO", "Mexico City", R.drawable.mexico, "19.432857", "-99.133166"),
                new CountryModel("BANGLADESH", "Dhaka", R.drawable.bangladesh, "23.719214", "90.387694"),
                new CountryModel("UNITED KINGDOM", "London", R.drawable.united_kingdom, "51.50094", "-0.124583"),
                new CountryModel("IRAN", "Tehran", R.drawable.iran, "35.699409", "51.337783"),
                new CountryModel("FRANCE", "Paris", R.drawable.france, "48.86169", "2.338549"),
                new CountryModel("PHILLIPINES", "Manila", R.drawable.philippines, "14.600617", "120.988706"),
                new CountryModel("VIETNAM", "Hanoi", R.drawable.vietnam, "21.013956", "105.829223"),
                new CountryModel("TURKEY", "Ankara", R.drawable.turkey, "39.920857", "32.855218"),
                new CountryModel("SOUTH KOREA", "Seoul", R.drawable.southkorea, "37.559959", "126.985696"),
                new CountryModel("THAILAND", "Bangkok", R.drawable.thailand, "13.742918", "100.567892"),
                new CountryModel("ITALY", "Rome", R.drawable.italy, "41.937243", "12.470863")
        };
    }


    private void updateDataInList(CountryModel[] myListData) {
        newTestAdapter = new NewTestAdapter(getActivity(), myListData);
        rvNewTest.setAdapter(newTestAdapter);
        newTestAdapter.notifyDataSetChanged();
    }

    public void setFromWhere(String fromWhere) {
        this.fromWhere = fromWhere;

    }

}



