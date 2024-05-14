package com.example.tareeqy_componentnew;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.util.List;
import java.util.ArrayList;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class cameraActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_camera);

        ViewPager2 pager = findViewById(R.id.pager);
        List<Fragment> fragmentList = new ArrayList<>();

        fragmentList.add(new CameraFragment());
        pager.setCurrentItem(0);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmentList.get(0))
                .commit();
    }
}
