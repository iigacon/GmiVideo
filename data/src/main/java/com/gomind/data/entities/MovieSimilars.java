package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 8/28/16.
 */
public class MovieSimilars {
    @SerializedName("page")
    private long page;
    @SerializedName("results")
    private List<MovieSimilar> results;
    @SerializedName("total_pages")
    private long total_pages;
    @SerializedName("total_results")
    private long total_results;

    public MovieSimilars(int page, List<MovieSimilar> results, long total_pages, long total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }


    public List<MovieSimilar> getResults() {
        return results;
    }

    public void setResults(List<MovieSimilar> results) {
        this.results = results;
    }

    public long getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(long total_pages) {
        this.total_pages = total_pages;
    }

    public long getTotal_results() {
        return total_results;
    }

    public void setTotal_results(long total_results) {
        this.total_results = total_results;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }
}
