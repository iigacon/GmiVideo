package com.gomind.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Duc on 9/13/16.
 */
public class PersonPopulars {
    @SerializedName("page")
    private double page;
    @SerializedName("results")
    private List<PersonPopular> results;
    @SerializedName("total_pages")
    private double total_pages;
    @SerializedName("total_results")
    private double total_results;

    public PersonPopulars(double page, List<PersonPopular> results, double total_pages, double total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public double getPage() {
        return page;
    }

    public void setPage(double page) {
        this.page = page;
    }



    public double getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(double total_pages) {
        this.total_pages = total_pages;
    }

    public double getTotal_results() {
        return total_results;
    }

    public void setTotal_results(double total_results) {
        this.total_results = total_results;
    }


    public List<PersonPopular> getResults() {
        return results;
    }

    public void setResults(List<PersonPopular> results) {
        this.results = results;
    }
}
