package com.example.pertemuan_3;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class UserDataSource {
    private static List<User> userList = new ArrayList<>();

    public static List<User> getAllUsers(Context context) {
        if (userList.isEmpty()) {
            generateDummyUsers(context);
        }
        return userList;
    }

    public static void generateDummyUsers(Context context) {
        if (!userList.isEmpty()) return;

        userList.add(new User("jane.kend", "Jane Kon", "1,612", "990", "Photographer", "blessed mess", "https://open.spotify.com/playlist/3bjsYdeSxLKTcVeAVHTuZy?si=a8e7a6f757554563", R.drawable.jane_profile));
        userList.add(new User("oyen", "orien here", "1.6M", "754", "Animals", "tes", "", R.drawable.keza_profile));
        userList.add(new User("alifrost", "al", "984", "772", "Personal Blog",
                "mari sebuah tanpa bercerita, HAHAHAHA",
                "https://youtu.be/8xFV4kdMtx4?si=nNGnKuFqT-u7yjys", R.drawable.ali_profile));
        userList.add(new User("joe_keery", "DjoB ", "1.3M", "76", "Musician/band", "", "https://open.spotify.com/artist/5p9HO3XC5P3BLxJs5Mtrhm?si=ajJbNv4rSvSIGJlMRtcvFA", R.drawable.narcissa_profile));
        userList.add(new User("sergiozz", "black car", "1.9M", "612", "Animals", "ini bio", "", R.drawable.missie_profile));
        userList.add(new User("joe.kend", "Joe Kendal", "100", "519", "Personal Blog", "", "https://open.spotify.com/playlist/3bjsYdeSxLKTcVeAVHTuZy?si=bbf2037543b8483c", R.drawable.joe_profile));
        userList.add(new User("shin.yeeun", "Ye Eun", "1.8M", "904", "Actor", "https://youtu.be/3XeSSNs_2pQ?si=-vIAVQJ7bUxpQQXD", "", R.drawable.putri_profile));
        userList.add(new User("abunsungkar", "buN", "2.6M", "512", "Actor", "Business Inquiries", "linktr.ee/abunsungkar", R.drawable.angga_profile));
        userList.add(new User("gogo_younjung", "고윤정", "8M", "1", "Actor",
                "",
                "https://www.youtube.com/@KikiraraVivi", R.drawable.winter_profile));
        userList.add(new User("dimas_s", "Dimas", "625", "586", "Doctor",
                "03",
                "", R.drawable.dimas_profile));

    }

    public static User getUserByUsername(String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}
