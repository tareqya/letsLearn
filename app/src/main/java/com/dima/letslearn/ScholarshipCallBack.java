package com.dima.letslearn;

import java.util.ArrayList;

public interface ScholarshipCallBack {
    void onScholarshipsFetchDone(ArrayList<Scholarship> scholarships, boolean requestStatus);
}
