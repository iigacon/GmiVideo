package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 9/16/16.
 */
public class WatchListMovies{
    @SerializedName("page")
    private int page;
    @SerializedName("results")
    private List<WatchListMovie> results;
    @SerializedName("total_pages")
    private double total_pages;
    @SerializedName("total_results")
    private int total_results;

    public WatchListMovies(int page, List<WatchListMovie> results, double total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }


    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<WatchListMovie> getResults() {
        return results;
    }

    public void setResults(List<WatchListMovie> results) {
        this.results = results;
    }

    public double getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(double total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
