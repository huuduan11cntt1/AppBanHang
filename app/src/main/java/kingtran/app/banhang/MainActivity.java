package kingtran.app.banhang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView_MHC;
    NavigationView navigationView_MHC;
    ListView listView_MHC;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ActionBar();
        ActionViewFlipper();
    }

    private void ActionViewFlipper() {
        List<String> mang_qc=new ArrayList<>();
        mang_qc.add("https://nongsananphat.com/wp-content/uploads/2020/10/mat-ong-768x576.jpg");
        mang_qc.add("https://cdn.tgdd.vn/2021/09/CookRecipe/CookTipsNote/cach-phan-biet-mat-ong-rung-va-mat-ong-nuoi-don-gian-va-chinh-tipsnote-800x450-7.jpg");
        mang_qc.add("https://hoabanfood.com/wp-content/uploads/Mat-Ong-Rung-Song-Da-2021-5.jpg");
        for (int i = 0; i<mang_qc.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mang_qc.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation animation_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_night);
        Animation animation_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_night);
        viewFlipper.setInAnimation(animation_in);
        viewFlipper.setInAnimation(animation_out);

    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void AnhXa() {
        toolbar = findViewById(R.id.toolbar_mhc);
        viewFlipper = findViewById(R.id.viewFlipper);
        recyclerView_MHC = findViewById(R.id.recyclerView);
        navigationView_MHC= findViewById(R.id.navigationView);
        listView_MHC= findViewById(R.id.listView_manhinchinh);
        drawerLayout= findViewById(R.id.drawerLayout);
    }
}