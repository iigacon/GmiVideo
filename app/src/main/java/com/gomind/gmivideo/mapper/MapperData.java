package com.gomind.gmivideo.mapper;

import com.gomind.data.entities.Image;
import com.gomind.data.entities.Video;


import java.util.ArrayList;
import java.util.List;

public class MapperData {
    public static List<com.gomind.gmivideo.view.activity.Image> MapperImages(List<Image> images){
        List<com.gomind.gmivideo.view.activity.Image> imageList=new ArrayList<>();
        for(Image image:images){
            imageList.add(new com.gomind.gmivideo.view.activity.Image(image.getFile_path(),image.getWidth(),image.getHeight(),image.getIso_639_1(),image.getAspect_ratio(),image.getVote_average(),image.getVote_count()));
        }
        return imageList;
    }
    public static List<com.gomind.gmivideo.view.activity.Video> MapperVideos(List<Video> videos){
        List<com.gomind.gmivideo.view.activity.Video> videoList=new ArrayList<>();
        for(Video v: videos){
            videoList.add(new com.gomind.gmivideo.view.activity.Video(v.getId(),v.getIso_639_1(),v.getKey(),v.getName(),v.getSite(),v.getSize(),v.getType()));
        }
        return videoList;
    }
}
