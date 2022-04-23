package com.dima.letslearn;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Database db;
    public static final String COLLEGE_ID = "college_id";
    private HomeFragment homeFragment;
    private ScholarshipFragment scholarshipFragment;
    private BottomNavigationView bottom_navigation;
    private FrameLayout homeFrame, scholarshipFrame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new Database();

        bottom_navigation = findViewById(R.id.bottom_navigation);
        homeFrame = findViewById(R.id.home_frame_home);
        scholarshipFrame = findViewById(R.id.home_frame_scholarship);

        homeFragment = new HomeFragment(this);
        getSupportFragmentManager().beginTransaction().add(R.id.home_frame_home, homeFragment).commit();
        scholarshipFragment = new ScholarshipFragment(this);
        getSupportFragmentManager().beginTransaction().add(R.id.home_frame_scholarship, scholarshipFragment).commit();

        homeFrame.setVisibility(View.VISIBLE);
        scholarshipFrame.setVisibility(View.INVISIBLE);



        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        homeFrame.setVisibility(View.VISIBLE);
                        scholarshipFrame.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.menu_scholarship:
                        homeFrame.setVisibility(View.INVISIBLE);
                        scholarshipFrame.setVisibility(View.VISIBLE);
                        break;
                }
                return true;
            }
        });


        // addCollage();
        // addScholarship();
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void addScholarship(){
        ArrayList<Scholarship> lst = new ArrayList<>();

        lst.add(new Scholarship()
                .setName("منحة ارتقاء")
                .setDescription(""));
        lst.add(new Scholarship()
        .setName("منحة مجلس التعليم العالي ووزارة المعارف")
        .setDescription("المنحة مخصصة لكل الطلاب الجامعيين عدا تلاميذ مؤسسات تأهيل المعلمين او مؤسسات تعليمية خارج البلاد." +
                "" +
                "منحة بقيمة 12480 ش.ج (درجة أ)" +
                "" +
                "منحة بقيمة 6240 ش.ج (درجة ب)" +
                "" +
                "منحة بقيمة 4000 ش.ج ( درجة ج)" +
                "" +
                "قرض(הלוואה) بقدر 7000 ش.ج مع العلم انها غير ربوية وبدون فوائد (يتم ارجاعها ب 36 قسط)." +
                "" +
                "التسجيل بواسطة نموذج محوسب تجدونه في موقع وزارة المعارف https://loans.education.gov.il/ او يمكنك التوجه لمركز/ة التعليم العالي في بلدك www.rowad.org.il" +
                "" +
                "فترة التسجيل للمنحة نهاية كانون الاول(12)"));
        lst.add(new Scholarship()
                .setName("برنامج هيسيجيم لهايتك")
                .setDescription("برنامج يفتح المجال لدراسة الهندسة والعلوم الدقيقة في الجامعات الرائدة في إسرائيل." +
                        "" +
                        "ملائمه للذين يطمحون لدراسة موضوع شيّق، حديث، مطلوب ومجدٍ ." +
                        "" +
                        "منحة تفوق 100,000 ش.ج لمزيد من التفاصيل والتسجيل: 7963855-073 Hes.hightech@aluma.org.il او يمكنك التوجه لمركز/ة التعليم العالي في بلدك www.rowad.org.il" +
                        "" +
                        "فترة التسجيل يناير (1)- مايو (5)"));
        lst.add(new Scholarship()
                .setName("برنامج سفراء روتشلد")
                .setDescription("يعمل برنامج سفراء روتشيلد على تنمية التفوق الشخصي والاجتماعي لطلاب من الشرائح السكانية النائية جغرافياً واجتماعياً." +
                        "" +
                        "يواصل البرنامج ارشاد وتدريب الجيل القادم من القادة في إسرائيل بمجالات عده ." +
                        "" +
                        "قيمة المنحه 20,000 ₪ سنويا! 60,000 ₪ للقب." +
                        "" +
                        "لمزيد من التفاصيل والتسجيل: 3730417-04 http://rothschildcp.com/ar/%D9%85%D9%86-%D9%86%D8%AD%D9%86/ او يمكنك التوجه لمركز/ة التعليم العالي في بلدك" +
                        "" +
                        "www.rowad.org.il" +
                        "" +
                        "فترة التسجيل تموز(7)-اب(8)"));
        lst.add(new Scholarship()
                .setName("برنامج تسوعاريم متدربون للتربية الا منهجيه في كلية اورانيم")
                .setDescription("برنامج المتدربون للتربية اللا منهجية هو بمثابة مبادرة مشتركة لكل من وزارَة التربية والتعليم؛ قسم المجتمع وأبناء الشبيبة وجمعية شراكات- روتشيلد قيسارية؛ ويعمل على تمكين وتعظيم الموارد البشرية في أجهزة التعليم اللامنهجي من خلال التركيز على الضواحي" +
                        "" +
                        " المنحه تشمل (المساعدة في تمويل رسوم الدراسة, منحة معيشية لتوجيه المهني في نهاية الدراسة والاستيعاب في العمل ( سيتم دمج خريجي البرنامج في وظائف رائدة لمدة 4 سنوات) لمزيد من التفاصيل والتسجيل: http://cadets.rothschildcp.com/%D7%91%D7%99%D7%AA-%D7%A2%D7%A8%D7%91%D7%99%D7%AA/" +
                        "" +
                        "او يمكنك التوجه لمركز/ة التعليم العالي في بلدك www.rowad.org.il" +
                        "" +
                        "فترة التسجيل يناير (1)- مارس(3)"));
        lst.add(new Scholarship()
                .setName("برنامج عتيديم طلاب باييس للحكم المحلي - עתידים לשלטון מקומי")
                .setDescription("برنامج “متدربون للحكم المحلي” برنامجاً خاصاً لأن الهدف منه هو تحسين الحكم المحلي في إسرائيل من خلال تنمية وتأهيل كوادر قيادية للإدارة المهنية في مجالات العمل البلدي. " +
                        "" +
                        "تشمل المنحه (المساعدة في تمويل رسوم الدراسة, منحة معيشية لتوجيه المهني في نهاية الدراسة والاستيعاب في العمل ( تم إستيعاب خريجي البرنامج وتوظيفهم في مناصب مركزية بالسلطات المحلية لفترة 4 أعوام)" +
                        "" +
                        "لمزيد من التفاصيل والتسجيل: https://www.hasama-zoarim.co.il/register-ar" +
                        "" +
                        "او يمكنك التوجه لمركز/ة التعليم العالي في بلدك www.rowad.org.il" +
                        "" +
                        "فترة التسجيل يناير (1)- نيسان (4)"));

        for(Scholarship scholarship : lst){
            this.db.insertScholarship(scholarship);
        }
    }

    private void addCollage(){
        ArrayList<Faculty> faculties = new ArrayList<>();
        faculties.add(new Faculty().setName("بيولوجيا").setBagrotGrade(100).setPsychometricGrade(690));
        faculties.add(new Faculty().setName("هندسة موارد المياه والبيئة").setBagrotGrade(110).setPsychometricGrade(680));
        faculties.add(new Faculty().setName("هندسة بيو-طبيّةّ").setBagrotGrade(100).setPsychometricGrade(600));
        faculties.add(new Faculty().setName("هندسة كيميائيّة").setBagrotGrade(105).setPsychometricGrade(650));
        faculties.add(new Faculty().setName("فيزياء").setBagrotGrade(100).setPsychometricGrade(650));
        faculties.add(new Faculty().setName("كيمياء").setBagrotGrade(100).setPsychometricGrade(600));
        faculties.add(new Faculty().setName("هندسة جغرافيّة").setBagrotGrade(115).setPsychometricGrade(720));
        faculties.add(new Faculty().setName("هندسة الحاسوب").setBagrotGrade(110).setPsychometricGrade(680));
        faculties.add(new Faculty().setName("هندسة البرمجيّات").setBagrotGrade(115).setPsychometricGrade(700));
        faculties.add(new Faculty().setName("هندسة البرمجيّات").setBagrotGrade(115).setPsychometricGrade(700));

        College college = new College();
        college
                .setName("تخنيون")
                .setDescription("")
                .setImageUrl("")
                .setLogoUrl("")
                .setCollegeWebSiteUrl("https://new.huji.ac.il/")
                .setFaculties(faculties);

        db.insertCollege(college);
    }
}