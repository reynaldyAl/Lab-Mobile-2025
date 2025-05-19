package com.example.pertemuan_3;

import java.util.ArrayList;
import java.util.List;

public class StoryDataSource {


    public static List<Story>  generateDummyStoriesFeed() {
        List<Story> storyList = new ArrayList<>();

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

        storyList.add(new Story(jane, null, "2h", R.drawable.jane_story6));
        storyList.add(new Story(oyen, null, "12h", R.drawable.oyen_story6));
        storyList.add(new Story(ali, null, "8h", R.drawable.ali_story6));
        storyList.add(new Story(djo, null, "4h", R.drawable.djo_story6));
        storyList.add(new Story(ireng, null, "1h", R.drawable.ireng_story1));
        storyList.add(new Story(joe, null, "2h", R.drawable.joe_story6));
        storyList.add(new Story(yeeun, null, "7h", R.drawable.yeeun_story6));
        storyList.add(new Story(abun, null, "18h", R.drawable.abun_story6));
        storyList.add(new Story(younjung, null, "9h", R.drawable.younjung_story6));
        storyList.add(new Story(dimas, null, "16h", R.drawable.dimas_story6));


        return storyList;
    }

    public static List<Story>  generateDummyStoryHighlights(String username) {
        List<Story> allHighlights = new ArrayList<>();

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

        allHighlights.add(new Story(jane,".", "22 March 2025", R.drawable.jane_story1));
        allHighlights.add(new Story(jane,"..", "22 March 2025", R.drawable.jane_story2));
        allHighlights.add(new Story(jane,"...", "10 March 2025", R.drawable.jane_story3));
        allHighlights.add(new Story(jane,"", "22 November 2024", R.drawable.jane_story4));
        allHighlights.add(new Story(jane,"", "22 June 2024", R.drawable.jane_story5));
        allHighlights.add(new Story(jane,"", "2 January 2023", R.drawable.jane_story6));
        allHighlights.add(new Story(jane,"", "20 July 2022", R.drawable.jane_story7));

        allHighlights.add(new Story(oyen, "o", "5 March 2025", R.drawable.oyen_story1));
        allHighlights.add(new Story(oyen, "y", "7 October 2024", R.drawable.oyen_story2));
        allHighlights.add(new Story(oyen, "e", "25 September 2024", R.drawable.oyen_story3));
        allHighlights.add(new Story(oyen, "n", "15 May 2024", R.drawable.oyen_story4));
        allHighlights.add(new Story(oyen, ".", "4 November 2023", R.drawable.oyen_story5));
        allHighlights.add(new Story(oyen, ".", "1 January 2023", R.drawable.oyen_story6));
        allHighlights.add(new Story(oyen, ".", "5 June 2023", R.drawable.oyen_story7));

        allHighlights.add(new Story(ali, "1", "28 August 2024", R.drawable.ali_story1));
        allHighlights.add(new Story(ali, "2", "12 August 2024", R.drawable.ali_story2));
        allHighlights.add(new Story(ali, "3", "10 August 2024", R.drawable.ali_story3));
        allHighlights.add(new Story(ali, "4", "8 August 2024", R.drawable.ali_story4));
        allHighlights.add(new Story(ali, "5", "15 October 2023", R.drawable.ali_story5));
        allHighlights.add(new Story(ali, "6", "5 September 2023", R.drawable.ali_story6));
        allHighlights.add(new Story(ali, "7", "4 August 2023", R.drawable.ali_story7));

        allHighlights.add(new Story(djo, "w", "12 January 2025", R.drawable.djo_story1));
        allHighlights.add(new Story(djo, "music", "10 November 2024", R.drawable.djo_story2));
        allHighlights.add(new Story(djo, "music2", "8 September 2024", R.drawable.djo_story3));
        allHighlights.add(new Story(djo, "bro", "15 March 2025", R.drawable.djo_story4));
        allHighlights.add(new Story(djo, "manggung", "12 January 2023", R.drawable.djo_story5));
        allHighlights.add(new Story(djo, "", "5 June 2023", R.drawable.djo_story6));
        allHighlights.add(new Story(djo, "", "4 August 2023", R.drawable.djo_story7));

        allHighlights.add(new Story(ireng, " ", "12 January 2025", R.drawable.ireng_story1));
        allHighlights.add(new Story(ireng, "", "10 November 2024", R.drawable.ireng_story2));
        allHighlights.add(new Story(ireng, "", "8 September 2024", R.drawable.ireng_story3));
        allHighlights.add(new Story(ireng, "", "15 March 2025", R.drawable.ireng_story4));
        allHighlights.add(new Story(ireng, "", "12 November 2023", R.drawable.ireng_story5));
        allHighlights.add(new Story(ireng, "", "12 September 2023", R.drawable.ireng_story6));
        allHighlights.add(new Story(ireng, "", "12 January 2023", R.drawable.ireng_story7));

        allHighlights.add(new Story(joe, "", "12 January 2025", R.drawable.joe_story1));
        allHighlights.add(new Story(joe, "", "8 September 2024", R.drawable.joe_story2));
        allHighlights.add(new Story(joe, "", "8 September 2024", R.drawable.joe_story3));
        allHighlights.add(new Story(joe, "", "5 June 2024", R.drawable.joe_story4));
        allHighlights.add(new Story(joe, "", "15 October 2023", R.drawable.joe_story5));
        allHighlights.add(new Story(joe, "", "1 July 2023", R.drawable.joe_story6));
        allHighlights.add(new Story(joe, "", "5 April 2023", R.drawable.joe_story7));

        allHighlights.add(new Story(yeeun, "", "12 January 2025", R.drawable.yeeun_story1));

        allHighlights.add(new Story(abun, "", "12 January 2025", R.drawable.abun_story1));
        allHighlights.add(new Story(abun, "", "10 November 2024", R.drawable.abun_story2));
        allHighlights.add(new Story(abun, "", "8 September 2024", R.drawable.abun_story3));
        allHighlights.add(new Story(abun, "", "5 June 2024", R.drawable.abun_story4));
        allHighlights.add(new Story(abun, "", "15 October 2023", R.drawable.abun_story5));
        allHighlights.add(new Story(abun, "", "1 July 2023", R.drawable.abun_story6));

        allHighlights.add(new Story(younjung, "", "12 March 2025", R.drawable.younjung_story1));
        allHighlights.add(new Story(younjung, "", "8 March 2025", R.drawable.younjung_story2));
        allHighlights.add(new Story(younjung, "", "14 February 2024", R.drawable.younjung_story3));
        allHighlights.add(new Story(younjung, "", "18 March 2025", R.drawable.younjung_story3));
        allHighlights.add(new Story(younjung, "younjung!", "21 January 2025", R.drawable.younjung_story4));
        allHighlights.add(new Story(younjung, "", "1 November 2024", R.drawable.younjung_story5));
        allHighlights.add(new Story(younjung, "", "21 December 2024", R.drawable.younjung_story6));

        allHighlights.add(new Story(dimas, "", "20 April 2025", R.drawable.dimas_story1));
        allHighlights.add(new Story(dimas, "", "20 April 2025", R.drawable.dimas_story2));
        allHighlights.add(new Story(dimas, "", "2 November 2024", R.drawable.dimas_story3));
        allHighlights.add(new Story(dimas, "", "18 March 2025", R.drawable.dimas_story4));
        allHighlights.add(new Story(dimas, "", "1 July 2024", R.drawable.dimas_story5));
        allHighlights.add(new Story(dimas, "", "14 August 2024", R.drawable.dimas_story6));
        allHighlights.add(new Story(dimas, "", "1 June 2024", R.drawable.dimas_story7));

        List<Story> filtered = new ArrayList<>();
        for (Story story : allHighlights) {
            if (story.getUser().getUsername().equals(username)) {
                filtered.add(story);
            }
        }
        return filtered;
    }
}
