package com.example.pertemuan_3;

import java.util.ArrayList;
import java.util.List;

public class PostDataSource {
    public static List<Post> newPosts = new ArrayList<>();
    public static void addNewPost(Post post) {
        newPosts.add(0, post);
    }
    public static List<Post> generateDummyPostsFeed() {
        List<Post> postsList = new ArrayList<>();
        User jane = UserDataSource.getUserByUsername("jane.kend");
        User oyen = UserDataSource.getUserByUsername("oyen");
        User ali = UserDataSource.getUserByUsername("alifrost");
        User djo = UserDataSource.getUserByUsername("joe_keery");
        User ireng = UserDataSource.getUserByUsername("sergiozz");
        User joe = UserDataSource.getUserByUsername("joe.kend");
        User yeeun = UserDataSource.getUserByUsername("shin.yeeun");
        User abun = UserDataSource.getUserByUsername("abunsungkar");
        User younjung = UserDataSource.getUserByUsername("gogo_younjung");
        User dimas = UserDataSource.getUserByUsername("dimas_s");

        postsList.addAll(newPosts);

        postsList.add(new Post(oyen, "apakah aku imuttt??????", "23 April 2025", "23K", "2K", "1.3K", R.drawable.oyen_post1));
        postsList.add(new Post(ali, "hari ini make over dlu, soalnya ada job di penyalin cahaya", "19 April 2025", "32K", "2.3K", "1.2K", R.drawable.ali_post1));
        postsList.add(new Post(djo,  "gonna do all this shii", "27 March 2025", "46K", "4.6K", "7.3K", R.drawable.djo_post1));
        postsList.add(new Post(ireng, "aku adalah anak kicikkk", "12 March 2025", "19K", "1.9K", "1K", R.drawable.ireng_post1));
        postsList.add(new Post(joe, "gotta go to grammy this year", "3 March 2025", "106K", "9.7K", "4.1K", R.drawable.joe_post1));
        postsList.add(new Post(yeeun, "Why am i soo prettyyyy?!?! ", "13 April 2025", "47K", "4.1K", "3.1K", R.drawable.yeeun_post1));
        postsList.add(new Post(abun, "sini aku gandeng", "12 February 2025", "56K", "5.6K", "1.2K", R.drawable.abun_post1));
        postsList.add(new Post(jane, "pagi duniaa, ngopi sambil ngudud duluu","4 January 2025", "45.5k", "5.2k", "10k", R.drawable.jane_post1));
        postsList.add(new Post(younjung, "hoi hoii?!?! how's my glasses guysss ", "4 January 2025", "48K", "3.4K", "2.9K", R.drawable.younjung_post1));
        postsList.add(new Post(dimas, "gotta stay cool for my good ", "28 August 2024", "558K", "26.2K", "14.5K", R.drawable.dimas_post1));

        return postsList;
    }

    public static List<Post> generateDummyPostProfilePage(String username) {
        List<Post> postsList = new ArrayList<>();
        User jane = UserDataSource.getUserByUsername("jane.kend");
        User oyen = UserDataSource.getUserByUsername("oyen");
        User ali = UserDataSource.getUserByUsername("alifrost");
        User djo = UserDataSource.getUserByUsername("joe_keery");
        User ireng = UserDataSource.getUserByUsername("sergiozz");
        User joe = UserDataSource.getUserByUsername("joe.kend");
        User yeeun = UserDataSource.getUserByUsername("shin.yeeun");
        User abun = UserDataSource.getUserByUsername("abunsungkar");
        User younjung = UserDataSource.getUserByUsername("gogo_younjung");
        User dimas = UserDataSource.getUserByUsername("dimas_s");

        postsList.add(new Post(jane, "dkjfk","4 January 2025", "45.5k", "5.2k", "10k", R.drawable.jane_post1));
        postsList.add(new Post(jane, "🍒🍒","25 December 2024", "60K", "6.6K", "5K", R.drawable.jane_post2));
        postsList.add(new Post(jane,  "sdllmf,ml", "22 March 2024", "76K", "5.6K", "9.8K", R.drawable.jane_post3));
        postsList.add(new Post(jane,  "gihrhjr", "19 July 2023", "65K", "10K", "10K", R.drawable.jane_post4));
        postsList.add(new Post(jane,  "seaaa", "27 September 2021", "50K", "7.6K", "11K", R.drawable.jane_post5));

        postsList.add(new Post(oyen,  "good meowning", "27 March 2025", "46K", "4.6K", "7.3K", R.drawable.oyen_post1));
        postsList.add(new Post(oyen,  "hi!", "11 November 2024", "86K", "7.8K", "6.7K", R.drawable.oyen_post2));
        postsList.add(new Post(oyen,  "ayo gelud", "20 August 2024", "47K", "5.1K", "4.9K", R.drawable.oyen_post3));
        postsList.add(new Post(oyen,  "aaa", "10 May 2024", "53K", "7.1K", "3.4K", R.drawable.oyen_post4));
        postsList.add(new Post(oyen,  "zz", "25 March 2022", "35K", "5.3K", "3.5K", R.drawable.oyen_post5));

        postsList.add(new Post(ali, ";sldmvv", "28 August 2024", "558K", "26.2K", "14.5", R.drawable.ali_post1));
        postsList.add(new Post(ali, ",sdnk", "1 July 2023", "65.6K", "9.8K", "4.5K", R.drawable.ali_post2));
        postsList.add(new Post(ali, "nitip", "18 May 2023", "84.5K", "7.9K", "3.4",R.drawable.ali_post3));
        postsList.add(new Post(ali, "ldmmlkd", "14 April 2023", "44.5K",  "4.5K", "4.5K", R.drawable.ali_post4));
        postsList.add(new Post(ali, "<3", "4 February 2023", "68.7K", "5.7K", "4.4K", R.drawable.ali_post5));

        postsList.add(new Post(djo, "sdnff", "4 January 2025", "48K", "3.4K", "2.9K",R.drawable.djo_post5));
        postsList.add(new Post(djo, ".,mvdf", "20 October 2024", "30K", "3.2K", "1.8K",R.drawable.djo_post1));
        postsList.add(new Post(djo, "dlgkl", "19 September 2024", "24K", "2.9K", "1.6K",R.drawable.djo_post2));
        postsList.add(new Post(djo, "😎", "6 June 2024", "47.5K", "3.9K", "2.1K",R.drawable.djo_post3));
        postsList.add(new Post(djo,  ",ddfndkjf", "1 April 2024", "39.7K", "2K" , "1.7K",R.drawable.djo_post4));

        postsList.add(new Post(ireng, "d,;lfd", "12 February 2025", "56K", "5.6K", "1.2K", R.drawable.ireng_post1));
        postsList.add(new Post(ireng, "pisoo", "22 July 2024", "69K", "4.1K", "2.5K", R.drawable.ireng_post2));
        postsList.add(new Post(ireng, "dlfmflkdd", "21 July 2024", "64K", "3.8K", "2.8K", R.drawable.ireng_post3));
        postsList.add(new Post(ireng, "d,dgldf", "12 October 2023", "76K", "8.5K" , "3.2K", R.drawable.ireng_post4));
        postsList.add(new Post(ireng, "sdlfjlf", "10 August 2023", "45K", "7.8K", "4.3K", R.drawable.ireng_post5));

        postsList.add(new Post(joe, ".d,,fml", "19 April 2025", "32K", "2.3K", "1.2K", R.drawable.joe_post1));
        postsList.add(new Post(joe, "ds,mdlkf", "23 March 2025", "31K", "3.8K", "1.2K", R.drawable.joe_post2));
        postsList.add(new Post(joe, "idfkl;d", "28 September 2024", "54K", "2.9K", "2.1K", R.drawable.joe_post3));
        postsList.add(new Post(joe, "...", "9 July 2024", "86K", "6.7K", "2.4K", R.drawable.joe_post4));
        postsList.add(new Post(joe, "s,dmfdsmfy", "12 Mei 2024", "71K", "4.1K", "1.2K", R.drawable.joe_post5));

        postsList.add(new Post(yeeun, "f,mbf,d", "13 April 2025", "47K", "4.1K", "3.1K", R.drawable.yeeun_post1));
        postsList.add(new Post(yeeun, ".fmgfdue><", "12 April 2025", "78K", "4.6K", "4.1K", R.drawable.yeeun_post2));
        postsList.add(new Post(yeeun, ".d/,gfg", "30 November 2024","49K", "6.4K", "3.3K", R.drawable.yeeun_post3));
        postsList.add(new Post(yeeun, "❤", "10 August 2024","59K", "4.4K", "1.3K", R.drawable.yeeun_post4));
        postsList.add(new Post(yeeun, "adf vcvdfo", "23 July 2024", "51K", "3.4K", "2.3K", R.drawable.yeeun_post5));

        postsList.add(new Post(abun, "s.,,mmlkmg", "3 March 2025", "106K", "9.7K", "4.1K",R.drawable.abun_post1));
        postsList.add(new Post(abun, ":D", "21 February 2025",  "70K", "5.6K", "3.1K",R.drawable.abun_post2));
        postsList.add(new Post(abun, "'!!" , "15 August 2024", "50K", "2K", "1.5K",R.drawable.abun_post3));
        postsList.add(new Post(abun, "ouoouo", "3 July 2024", "39K", "3.1K", "2.2K",R.drawable.abun_post4));
        postsList.add(new Post(abun, "/fgvh", "19 Mei 2024", "43K", "3.9K", "2.1K",R.drawable.abun_post5));

        postsList.add(new Post(younjung, "", "12 March 2025", "19K", "1.9K", "1K", R.drawable.younjung_post1));
        postsList.add(new Post(younjung, "", "20 February 2025", "23K", "2.3K", "1.3K", R.drawable.younjung_post2));
        postsList.add(new Post(younjung, "" , "11 January 2025" , "28K", "2.3K", "1.3K", R.drawable.younjung_post3));
        postsList.add(new Post(younjung, "", "11 Desember 2024", "17K", " 1.7K", "1K", R.drawable.younjung_post4));
        postsList.add(new Post(younjung, "", "24 November 2024", "20K", "2K", "1.1K", R.drawable.younjung_post5));

        postsList.add(new Post(dimas, "jhhbvhj", "23 April 2025", "23K", "2K", "1.3K", R.drawable.dimas_post1));
        postsList.add(new Post(dimas, "><", "20 April 2025","35K", "3.5K", "2.3K", R.drawable.dimas_post2));
        postsList.add(new Post(dimas, "lkklkm", "10  March 2025", "21K", "2K", "1.2K", R.drawable.dimas_post3));
        postsList.add(new Post(dimas, ".mlkn",  "25 Mei 2024", "21K", "2.1K", "2K", R.drawable.dimas_post4));
        postsList.add(new Post(dimas, "pat pat", "12 April 2024", "19K", "1.9K","1K", R.drawable.dimas_post5));


        List<Post> filtered = new ArrayList<>();
        for (Post post : newPosts) {
            if (post.getUser().getUsername().equals(username)) {
                filtered.add(post);
            }
        }

        for (Post post : postsList) {
            if (post.getUser().getUsername().equals(username)) {
                filtered.add(post);
            }
        }
        return filtered;
    }
}