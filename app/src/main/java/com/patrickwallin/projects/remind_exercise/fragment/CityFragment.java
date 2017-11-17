package com.patrickwallin.projects.remind_exercise.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrickwallin.projects.remind_exercise.R;
import com.patrickwallin.projects.remind_exercise.adapter.MyCityRecyclerViewAdapter;
import com.patrickwallin.projects.remind_exercise.data.CityItem;
import com.patrickwallin.projects.remind_exercise.listener.OnCityListFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityFragment extends Fragment {
    @BindView(R.id.city_recycler_view)
    RecyclerView mCityRecyclerView;

    private OnCityListFragmentInteractionListener mListener;
    private Context mContext;

    public CityFragment() {}

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CityFragment newInstance(int columnCount) {
        CityFragment fragment = new CityFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);
        ButterKnife.bind(this,view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        mCityRecyclerView.setLayoutManager(linearLayoutManager);
        mCityRecyclerView.setHasFixedSize(true);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                mCityRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        mCityRecyclerView.addItemDecoration(mDividerItemDecoration);

        mCityRecyclerView.setAdapter(new MyCityRecyclerViewAdapter(getListOfCity(),mListener,mContext));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        if (context instanceof OnCityListFragmentInteractionListener) {
            mListener = (OnCityListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private List<CityItem> getListOfCity() {
        List<CityItem> cityItemList = new ArrayList<>();
        String[] cityArray = getResources().getStringArray(R.array.city_array);
        if(cityArray != null && cityArray.length > 0) {
            for(int i = 0; i < cityArray.length; i++) {
                String[] splitCityValue = cityArray[i].split("\\|",-1);
                cityItemList.add(new CityItem(String.valueOf(i),splitCityValue[0],splitCityValue[1]));
            }
        }
        return cityItemList;
    }

}
