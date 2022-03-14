package com.dima.letslearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String COLLEGE_ID = "college_id";
    private Database db;
    private ArrayList<College> colleges;
    private RecyclerView home_recycleView_colleges;
    private ProgressBar home_progressbar;
    private TextInputLayout home_txtField_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        findViews();
        initVars();

    }

    private void findViews() {
        home_recycleView_colleges = findViewById(R.id.home_recycleView_colleges);
        home_progressbar = findViewById(R.id.home_progressbar);
        home_txtField_search = findViewById(R.id.home_txtField_search);
    }

    private void initVars() {
        db = new Database();
        colleges = new ArrayList<>();
        db.setCollegeCallBack(callBack);
        db.getColleges();
        home_txtField_search.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchForCollage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void searchForCollage(String collegeName){
        ArrayList<College> filteredColleges = new ArrayList<>();

        for(int i = 0; i < this.colleges.size(); i++){
            if(this.colleges.get(i).getName().contains(collegeName)){
                filteredColleges.add(this.colleges.get(i));
            }
        }

        CollegeAdapter collegeAdapter = (CollegeAdapter)home_recycleView_colleges.getAdapter();
        if(collegeName != "")
            collegeAdapter.setColleges(filteredColleges);
        else
            collegeAdapter.setColleges(colleges);

        collegeAdapter.notifyDataSetChanged();
    }

    private CollegeCallBack callBack = new CollegeCallBack() {
        @Override
        public void onCollegesDataFetch(ArrayList<College> arr) {
            colleges = arr;
            CollegeAdapter adapter_collage = new CollegeAdapter(MainActivity.this, arr);
            home_recycleView_colleges.setAdapter(adapter_collage);
            home_recycleView_colleges.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
            home_recycleView_colleges.setHasFixedSize(true);
            home_recycleView_colleges.setItemAnimator(new DefaultItemAnimator());
            home_progressbar.setVisibility(View.GONE);
            adapter_collage.setCollegeAdapter_callBack(new CollegeAdapter_CallBack() {
                @Override
                public void onClick(College college) {
                    Intent intent = new Intent(MainActivity.this, CollegeActivity.class);
                    intent.putExtra(COLLEGE_ID, college.getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onCollegeDataFetch(College college) {

        }
    };


//    private void test(){
//        ArrayList<Faculty> faculties = new ArrayList<>();
//        faculties.add(new Faculty().setName("إدارة أعمال").setBagrotGrade(100).setPsychometricGrade(600));
//        faculties.add(new Faculty().setName("تدقيق حسابات").setBagrotGrade(110).setPsychometricGrade(680));
//        faculties.add(new Faculty().setName("عمل إجتماعيّ").setBagrotGrade(100).setPsychometricGrade(600));
//        faculties.add(new Faculty().setName("علم النفس").setBagrotGrade(105).setPsychometricGrade(650));
//        faculties.add(new Faculty().setName("لغة عبريّة").setBagrotGrade(100).setPsychometricGrade(500));
//        faculties.add(new Faculty().setName("لغة إنجليزيّة").setBagrotGrade(100).setPsychometricGrade(500));
//        faculties.add(new Faculty().setName("طبّ أسنان").setBagrotGrade(115).setPsychometricGrade(720));
//        faculties.add(new Faculty().setName("علوم الحيوان").setBagrotGrade(105).setPsychometricGrade(620));
//        faculties.add(new Faculty().setName("طبّ بيطريّ").setBagrotGrade(110).setPsychometricGrade(700));
//
//        College college = new College();
//        college
//                .setName("الجامعة العبرية في القدس")
//                .setDescription("الجامعة العبرية في القدس (بالعبرية: האוניברסיטה העברית בירושלים بالإنجليزية: The Hebrew University of Jerusalem) هي جامعة إسرائيلية عامة في مدينة القدس. كانت الجامعة العبرية أول جامعة، وثاني مؤسسة أكاديمية في فلسطين، تأسست عام 1925 أيام الانتداب البريطاني بمبادرة الحركة الصهيونية. تتصدر الجامعة المؤسسات الأكاديمية في إسرائيل اليوم، تخرج منها عدة شخصيات بارزة في مجال الأبحاث ومجالات أخرى، ومنهم من حاز أيضا على جائزة نوبل، كما أن هذه الجامعة تصنف 64 على مستوى العالم حسب \"الترتيب الأكاديمي للجامعات حسب جامعة شانغهاي\" [1]، أو 93 حسب \"تصنيف THE\" [2]\n" +
//                        "\n" +
//                        "الجامعة العبرية هي اليوم جامعة جماهيرية ممولة من قبل دولة إسرائيل بالإضافة إلى تبرعات وأرباح بيع الكتب ومنح حقوق نشر على أبحاث. من أهم المصادر المالية المتوفرة للجامعة هو بيع تصاريح لاستخدام اسم ألبرت أينشتاين وصورته بموجب وصية أينشتاين الذي نقل الحقوق على استخدام اسمه وصورته بعد وفاته للجامعة [3] [4].\n" +
//                        "\n" +
//                        "بلغ عدد طلاب الجامعة 24 ألف طالب في 2006 غالبيتهم إسرائيليون (من اليهود والعرب) وبعضهم أجانب، من بينهم عدد كبير من اليهود غير الإسرائيليين. وبلغ عدد الأساتذة المعلمين في الجامعة في ذلك العام 1200 أستاذ. اللغتان الرئيسيتان المستخدمتان للتعليم هما العبرية والإنجليزية. تنتشر دوائر الجامعة في خمسة حرمات جامعية، منهم ثلاثة حرمات في القدس: حرم جبل المشارف - أقدم الحرمات، يشمل كلية الأدبيات، كليات الحقوق وكلية العلوم الاجتماعية، حرم إدموند سافرا في گفعات رام (تلة الشيخ بدر) - يشمل كلية العلوم الطبيعية، الرياضيات، المعلوماتية وغيرها من الدوائر، حرم عين كرم يشمل كلية الطب (ضمن مستشفى هداسا عين كرم). أما الحرمين الجامعيين التابعة للجامعة العبرية خارج القدس فهما: حرم رحوبوت وحرم بيت دجان الذان يشملان كلية تعليم للزراعة العلوم الغذائية والطب البيطري. كذلك هناك مختبر لأبحاث في علم الأحياء البحري تابعة للجامعة العبرية في مدينة إيلات.")
//                .setImageUrl("")
//                .setLogoUrl("")
//                .setCollegeWebSiteUrl("https://new.huji.ac.il/")
//                .setFaculties(faculties);
//
//        //db.insertCollege(college);
//    }
}