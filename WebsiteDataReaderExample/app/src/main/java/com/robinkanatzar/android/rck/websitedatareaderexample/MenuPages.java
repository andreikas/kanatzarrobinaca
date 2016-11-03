package com.robinkanatzar.android.rck.websitedatareaderexample;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import bobik.BobikClient;
import bobik.Job;
import bobik.JobListener;

public class MenuPages {

    static String YOUR_AUTHENTICATION_TOKEN = "AUDAifLPPbx12FxpDsUE";

    // Test-drives the application
    public static void test() throws Exception {
        BobikClient bobik = new BobikClient(YOUR_AUTHENTICATION_TOKEN);
        // Imagine we get some restaurants through some search of local businesses (Google or Yahoo)
        // First, we need to determine the urls for all requested restaurants
        HashMap urls_to_restaurants = find_menu_urls(bobik, new String[]{"Ebisu", "Cafe Bastille", "Cafe Metropol", "B44", "Belden Taverna", "Cafe Tiramisu"});

        // Next, we want to parse menus for all these restaurants
        JSONObject restaurants_to_menus = find_menus(bobik, urls_to_restaurants);

        Log.d("RCK", "inside test()");

        System.out.println(restaurants_to_menus.toString());
    }


    // Since we don't expect any errors and don't care about progress (in this example),
    // stub 2 functions with simple loggers
    private abstract static class JobListenerImpl extends JobListener {
        @Override
        public void onProgress(float currentProgress) {
            Log.d(log_tag, "Current progress for job " + job.id() + " is " + currentProgress*100 + "%");
        }

        @Override
        public void onErrors(Collection<String> errors){
            for (String s : errors)
                Log.e(log_tag, "Error for job " + job.id() + ": " + s);
        }

        private final String log_tag = "Pair Wise";
    };

    // Finds menu urls for an array of restaurants.
    // Returns a hash: restaurant url --> restaurant name
    private static HashMap find_menu_urls(BobikClient bobik, final String restaurant_names[]) throws Exception {
        // This is the object we'll be building to return
        final HashMap<String,String> menupages_urls_to_restaurants = new HashMap<String, String>();
        // This map will help us figure out how to match Bobik results to original list or restaurants
        final HashMap<String,String> google_urls_to_restaurants = new HashMap<String, String>();
        // Tell Bobik about what to scrape
        JSONObject request_urls = new JSONObject();
        // Here, we'll ignore Google's robots.txt for simplicity.
        // While this is ok for testing purposes, the production version of this
        // app uses a private search key that allows it to use Google's search API directly.
        request_urls.put("ignore_robots_txt", true);
        for (String restaurant : restaurant_names) {
            String google_search_url = getGoogleSearchUrl(restaurant);
            request_urls.accumulate("urls", google_search_url);
            google_urls_to_restaurants.put(google_search_url, restaurant);
        }
        request_urls.put("queries", "//cite");

        Job job = bobik.scrape(request_urls, new JobListenerImpl() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // Bobik returns results by url.
                // Even though restaurant names are not listed in the result,
                // we'll still be able to match urls to names
                // as they are listed in the same order as names in our original array
                Iterator urls = jsonObject.keys();
                for (int i=0; urls.hasNext(); i++) {
                    String google_url = (String)urls.next();
                    try {
                        String menupages_url = jsonObject.getJSONObject(google_url).getJSONArray("//cite").getString(0); // get first result returned by G
                        if (!menupages_url.endsWith("menu"))
                            menupages_url += "menu";
                        if (!menupages_url.startsWith("http://"))
                            menupages_url = "http://" + menupages_url;
                        // Each result looks like: "http://sanfrancisco.menupages.com/restaurants/ebisu-2/menu",
                        // where everything but the restaurant name stays the same
                        String restaurant = google_urls_to_restaurants.get(google_url);
                        menupages_urls_to_restaurants.put(menupages_url, restaurant);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        });
        job.waitForCompletion();
        return menupages_urls_to_restaurants;
    }

    // Finds menus for all restaurants listed in the urls_to_restaurants argument.
    // Returns results in the form: restaurant name --> [{"Item": "green salad", "Price": "3"}, {"Item": "tasty burger", "Price": "8"}, ...]
    private static JSONObject find_menus(BobikClient bobik, final HashMap<String, String> menupages_urls_to_restaurants) throws  Exception {
        // Tell Bobik about what to scrape
        JSONObject request_menus = new JSONObject();
        // Ignore MenuPages's robots.txt
        request_menus.put("ignore_robots_txt", true);
        // Iterator over a collection of "http://sanfrancisco.menupages.com/restaurants/cafe-metropol/menu"-like urls
        for (String menupages_url : menupages_urls_to_restaurants.keySet())
            request_menus.accumulate("urls", menupages_url);
        /*
          Prior to writing this code, I pre-build these queries at usebobik.com and saved them as "MenuPages":
            Item: "//table[@class='prices-three']//th[not(@colspan)]/cite" - all item titles (the 'not' expression is here to filter out occasional section subtitles)
            Price: "//table[@class='prices-three']//td[last()]" - item prices
        */
        request_menus.put("query_set", "MenuPages");
        final JSONObject restaurants_to_menus = new JSONObject();
        Job job = bobik.scrape(request_menus, new JobListenerImpl() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                // Collect results for all restaurant urls
                Iterator restaurant_urls = jsonObject.keys();
                while (restaurant_urls.hasNext()) {
                    String restaurant_url = (String)restaurant_urls.next();
                    String restaurant = menupages_urls_to_restaurants.get(restaurant_url);
                    try {
                        JSONObject restaurant_menu = jsonObject.getJSONObject(restaurant_url);
                        // While this object has both items and their prices, they are stored in 2 separate arrays
                        // rather than an array of grouped items and prices. So, fix that.
                        JSONArray menu = transpose_menu(restaurant_menu);
                        restaurants_to_menus.put(restaurant, menu);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        });
        job.waitForCompletion();
        return restaurants_to_menus;
    }

    // Find the restaurant's MenuPages page on Google
    private static String getGoogleSearchUrl(String restaurant) {
        String query = restaurant + " menu San Francisco";
        String search_url = null;
        try {
            search_url = "http://www.google.com/search?q=" + java.net.URLEncoder.encode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        search_url += "+site:menupages.com";
        return search_url;
    }

    // Receives a JSON object {"Item" : [items], "Price" : [prices]} and converts it to
    // an array of {"Item" : item, "Price" : price} elements
    private static JSONArray transpose_menu(JSONObject result_for_single_restaurant) throws JSONException {
        JSONArray items = result_for_single_restaurant.getJSONArray("Item");
        JSONArray prices = result_for_single_restaurant.getJSONArray("Price");
        JSONArray priced_items = new JSONArray();
        for (int i=0; i<items.length(); i++) {
            JSONObject priced_item = new JSONObject();
            priced_item.put("Item", items.getString(i));
            priced_item.put("Price", prices.getString(i));
            priced_items.put(priced_item);
        }
        return priced_items;
    }
}
