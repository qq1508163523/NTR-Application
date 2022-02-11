package com.ntr.util;

import com.ntr.api.Resource;
import com.ntr.entity.Account;
import com.ntr.entity.Branch;
import com.ntr.entity.Carousel;
import com.ntr.entity.Episode;

import java.util.List;


public class ResourceUtil{


    public static List<Branch> resolveBranchList(List<Branch> branchList){
        for (Branch branch : branchList) {
            resolveBranch(branch);
        }
        return branchList;
    }

    public static List<Carousel> resolveCarouselList(List<Carousel> carouselList){
        for (Carousel carousel : carouselList) {
            resolveCarousel(carousel);
        }
        return carouselList;
    }

    public static List<Episode> resolveEpisodeList(List<Episode> episodeList,String folderLocation){
        for (Episode episode : episodeList) {
            resolveEpisode(episode,folderLocation);
        }
        return episodeList;
    }

    public static Branch resolveBranch(Branch branch){
        branch.setImgUrl(Resource.IMG_URL.getRoot() +"/"+branch.getImgName());
        return branch;
    }

    public static Carousel resolveCarousel(Carousel carousel){
        carousel.setImgUrl(Resource.IMG_URL.getRoot()+"slides/"+carousel.getImgName());
        return carousel;
    }

    public static Episode resolveEpisode(Episode episode,String folderLocation){
        episode.setVideoUrl(Resource.VIDEO_URL.getRoot()+folderLocation+"/"+episode.getMd5()+"."+episode.getFileType());
        return episode;
    }

    public static Account resolveAccount(Account account){
        account.setPassword(null);
        account.setProfilePicUrl(Resource.IMG_URL.getRoot()+"userProfiles/"+account.getProfilePicName());
        return account;
    }
}
