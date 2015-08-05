package com.gdgssu.android_deviewsched.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {

    public ArrayList<Track> tracks;

}
