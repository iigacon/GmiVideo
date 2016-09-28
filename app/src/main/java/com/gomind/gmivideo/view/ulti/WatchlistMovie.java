package com.gomind.gmivideo.view.ulti;

import com.gomind.data.entities.Movie;

import java.util.ArrayList;
import java.util.List;


public class WatchlistMovie {
    public static List<String> watchListMovies=new ArrayList<>();
    public static List<Movie> Objwatchlist=new ArrayList<>();
    public static List<String> getWatchListMovies(){
        return watchListMovies;
    }
    public static boolean check(String id){
        for(String s:watchListMovies){
            if(s.equals(id)) return true;
        }
        return false;
    }
    public static void add(String id){
        if(!check(id)) watchListMovies.add(id);
    }
    public static void remove(String id){
        if(check(id)) watchListMovies.remove(id);
    }
    public static void store(List<Movie> objwatchlist){
        Objwatchlist=objwatchlist;
        watchListMovies.clear();
        for(Movie w: objwatchlist){
            watchListMovies.add(w.getId());
        }
    }
}
