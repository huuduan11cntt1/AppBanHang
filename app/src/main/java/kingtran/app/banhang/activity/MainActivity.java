package kingtran.app.banhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import kingtran.app.banhang.R;
import kingtran.app.banhang.adapter.LoaispAdapter;
import kingtran.app.banhang.model.LoaiSp;
import kingtran.app.banhang.retrofit.ApiBanHang;
import kingtran.app.banhang.retrofit.RetrofiClient;
import kingtran.app.banhang.utils.Utils;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerView_MHC;
    NavigationView navigationView_MHC;
    ListView listView_MHC;
    private DrawerLayout drawerLayout;
    LoaispAdapter loaispAdapter;
    List<LoaiSp> mangloaisp;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       apiBanHang = RetrofiClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);

        AnhXa();
        ActionBar();
        ActionViewFlipper();
        if (isConnected(this)){
            Toast.makeText(getApplicationContext(),"OK", Toast.LENGTH_SHORT).show();
            ActionViewFlipper();
            getLoaiSanPham();
        }else{
        Toast.makeText(getApplicationContext(),"Khong co Internet", Toast.LENGTH_SHORT).show();
     }
    }

    private void getLoaiSanPham() {
        compositeDisposable.add(apiBanHang.getLoaiSp().su
        )
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
        //khoi tao list
        mangloaisp = new ArrayList<>();

        //khoi tao adapter
        loaispAdapter = new LoaispAdapter(getApplicationContext(),mangloaisp);
        listView_MHC.setAdapter(loaispAdapter);
    }

    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected()) ){
            return true;
        }
        else {
            return  false;
        }
    }
}