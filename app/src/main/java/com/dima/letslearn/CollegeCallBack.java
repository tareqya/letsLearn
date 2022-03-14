package com.dima.letslearn;

import java.util.ArrayList;

public interface CollegeCallBack {
    void onCollegesDataFetch(ArrayList<College> colleges);
    void onCollegeDataFetch(College college);
}
