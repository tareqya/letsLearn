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
import android.widget.Button;
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
    private Button btn_Scholarship;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        findViews();
        initVars();
        //test();
    }

    private void findViews() {
        home_recycleView_colleges = findViewById(R.id.home_recycleView_colleges);
        home_progressbar = findViewById(R.id.home_progressbar);
        home_txtField_search = findViewById(R.id.home_txtField_search);
        btn_Scholarship = findViewById(R.id.btn_Scholarship);
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


        btn_Scholarship.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ScholarshipActivity.class));
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

    private void addScholarship(){
        ArrayList<Scholarship> lst =new ArrayList<>();

        lst.add(new Scholarship()
                .setName("منحة ارتقاء")
                .setDescription("منحة بقيمة 10 الاف شواقل لكل سنة تعليمية، في أول سنتين يحصل الطالب في كل سنة على مبلغ 10,000 شيكل، وإبتداء من السنة الثالثة يتم منح الطالب 8000 شواقل سنويا حتى إنهاء سنين الدراسة للقب الأول ." +
                        "" +
                        "الحصول على المنحة مشروط بعمل تطوعي 40 ساعة في السنة الاولى و80 ساعة في السنة الثانية فما فوق" +
                        "" +
                        "لمزيد من التفاصيل والاستفسار حول تعبئة النموذج: هاتف 6664409-04 / فاكس: 6457788-04 https://irteka.co.il/landing-page-2018/ او يمكنك التوجه لمركز/ة التعليم العالي في بلدك www.rowad.org.il" +
                        "" +
                        "فترة التسجيل للمنحه – تشرين اول(10)تشرين ثاني(11)"));
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

    private void test(){
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

        College college = new College();
        college
                .setName("تخنيون")
                .setDescription("يتواجد التخنيون في مدينة حيفا وتحديدًا على جبل الكرمل، ويطلق عليه عادةً إسم “قرية التخنيون” نسبةً إلى الحيّز الكبير الذي يشغله.\n" +
                        "يقسّم التخنيون إلى قسمين:\n" +
                        "– قرية التخنيون الواقعة على جبل الكرمل، والتي فيها تُعلّم معظم المواضيع التعليميّة.\n" +
                        "– كلّيّة الطب، والتي تقع بجانب مستشفى “رمبام”، وهي كلّيّة منعزلة نسبيًّا عن باقي كلّيّات التخنيون.\n" +
                        "\n" +
                        "نبذة عن تاريخ التخنيون ومستواه:\n" +
                        "تأسّس التخنيون عام 1924. مقرّه الأوّل كان ما يعرف اليوم بمتحف العلوم، التكنولوجيا والفضاء، وقد نُقل إلى موقعه الحالي عام 1953.\n" +
                        "يعتبر التخنيون الجامعة الأولى في البلاد من حيث المستوى التعليميّ في مجالات العلوم والهندسة والفروع المتّصلة بها مثل هندسة العمار، هندسة الحاسوب والهندسة البيو-طبّيّة وغيرها.")
                .setImageUrl("")
                .setLogoUrl("")
                .setCollegeWebSiteUrl("https://new.huji.ac.il/")
                .setFaculties(faculties);

        db.insertCollege(college);
    }
}