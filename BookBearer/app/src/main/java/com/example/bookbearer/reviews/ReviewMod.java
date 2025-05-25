package com.example.bookbearer.reviews;

import java.util.ArrayList;
import java.util.Map;

public interface ReviewMod {

    ArrayList<Map<String,Object>> readReviews(String rISBN);

}
