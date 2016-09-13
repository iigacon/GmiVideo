package com.gomind.gmivideo.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gomind.data.entities.PersonPopular;
import com.gomind.gmivideo.GmiVideoApplication;
import com.gomind.gmivideo.Injector.Component.DaggerPersonPopularComponent;
import com.gomind.gmivideo.Injector.Module.ActivityModule;
import com.gomind.gmivideo.Injector.Module.PersonPopularModule;
import com.gomind.gmivideo.R;
import com.gomind.gmivideo.view.activity.PersonDetailActivity;
import com.gomind.gmivideo.view.adapter.PersonPopularAdapter;
import com.gomind.gmivideo.vmp.presenter.PersonPopularPresenter;
import com.gomind.gmivideo.vmp.view.PersonPopularView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FragmentPersonPopular extends Fragment implements PersonPopularView{

    @BindView(R.id.recycler_people)
    RecyclerView recyclerView;

    PersonPopularAdapter adapter;
    @Inject
    PersonPopularPresenter popularPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_person_popular, container,false);
        ButterKnife.bind(this,view);
        initilizeInjector();
        return view;
    }

    public void initilizeInjector() {
        GmiVideoApplication gmiVideoApplication = (GmiVideoApplication) getActivity().getApplication();
        DaggerPersonPopularComponent.builder()
                .activityModule(new ActivityModule(getActivity()))
                .appComponent(gmiVideoApplication.getAppComponent())
                .personPopularModule(new PersonPopularModule())
                .build().inject(this);
        popularPresenter.attachView(this);
        popularPresenter.onCreate();
    }


    @Override
    public void binPerson(List<PersonPopular> personPopulars) {
        adapter=new PersonPopularAdapter(personPopulars,getContext(),id -> {
            Intent intent=new Intent(getActivity(), PersonDetailActivity.class);
            intent.putExtra("person.id",id);
            getActivity().startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(mOnScrollListener);
        recyclerView.setHasFixedSize(true);
        recyclerView.setFitsSystemWindows(true);
    }

    @Override
    public void startDetailPerson(String id) {

    }

    @Override
    public void countLoadMore(int count) {
        adapter.notifyItemRangeInserted(adapter.getItemCount()+count,count);
    }

    @Override
    public void error(Throwable e) {
        e.printStackTrace();
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            int visiableItemsCount = layoutManager.getChildCount();
            int totalItemsCount = layoutManager.getItemCount();
            int firstVisiableItemPos = layoutManager.findFirstVisibleItemPosition();
            if ((visiableItemsCount + firstVisiableItemPos + 16) >= totalItemsCount) {
                popularPresenter.loadMore();
                System.out.println("load More");
            }
        }
    };
}
